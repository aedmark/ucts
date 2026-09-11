// Shared generator for T.R.S. zone-event content pipelines (WORK, HOME, and
// whatever zone comes next). Reads a zone's original browser-game event
// data (js/events/<zone>.js) and generates SCI0 source implementing each
// event as a standalone procedure, using PrintChoices
// (TRS_SCI/src/printchoices.sc) for the choice dialog and ApplyChoiceEffects/
// ApplyGlitch (TRS_SCI/src/mechanisms.sc) for stat effects.
//
// Extracted from the original WORK-only generator so new zones don't
// duplicate ~230 lines of logic. Each zone gets its own thin entry script
// (tools/gen-<zone>-events.js) that just calls generateZoneEvents(opts).
//
// Output is split across CHUNK_COUNT script files (<prefix>1.sc..N) plus a
// slim <prefix>.sc dispatcher, rather than one big file -- a single-file
// version of the 34 WORK events alone ran ~46KB of source, well past the
// ~16KB practical SCI0 script-size ceiling documented in
// docs/SCI0-research-findings.md. The dispatcher Load()s only the chunk
// script the requested index lives in and DisposeScript()s it right after --
// see the comment inside genDispatcher() below for why that's load-bearing.
'use strict';
const fs = require('fs');
const path = require('path');
const { sciString } = require('./sci-string');

const DESC_WIDTH = 290; // DText width (px) passed to PrintChoices; kernel TextSize() wraps within it, so widening is safe as long as it stays under the 320px screen (DText starts at x=4)
const CHUNK_COUNT = 4;
// Deliberate scope cut to fix a recurring heap-space exhaustion: cap every
// event to its first MAX_CHOICES authored responses (glitch is a bonus 4th
// slot on top, not counted here) instead of the original's 3-5. Shrinks
// generated code, dialog height, and per-turn heap pressure all at once.
// Changes game balance (fewer options per event) -- accepted tradeoff,
// content/balance to be revisited once the engine side is solid.
const MAX_CHOICES = 3;

const TAG_CONSTANTS = { fawn: 'TAG_FAWN', flight: 'TAG_FLIGHT', fight: 'TAG_FIGHT', freeze: 'TAG_FREEZE', secure: 'TAG_SECURE' };

function signedDelta(effect) {
	return effect.op === 'add' ? effect.value : -effect.value;
}

function genEventProcedure(procName, index, event) {
	const desc = sciString(event.desc);
	const title = sciString(event.title);
	const glitchText = sciString(event.glitch.text);
	const glitchLog = sciString(event.glitch.log);
	const choices = event.choices.slice(0, MAX_CHOICES);

	// No pre-wrapping here anymore -- PrintChoices' own SizeButtonToWidth
	// (printchoices.sc) now wraps button text at render time using the
	// actual font metrics via TextSize(), the same mechanism the
	// description text has always used, instead of a fixed
	// character-per-line guess that ignored the button's real rendered
	// width (BUTTON_WRAP_LEN, removed -- see SESSION_HANDOFF.md).
	const choiceParams = choices
		.map((c, i) => `\t\t"${sciString(c.text)}" ${i}`)
		.join('\n');

	const cases = choices
		.map((c, i) => {
			const rep = signedDelta(c.effects.rep);
			const mask = signedDelta(c.effects.mask);
			const child = signedDelta(c.effects.child);
			const tag = TAG_CONSTANTS[c.tag];
			return `\t\t(case ${i}\n\t\t\tApplyChoiceEffects(${rep} ${mask} ${child} ${tag})\n\t\t\tPrint("${sciString(c.log)}")\n\t\t)`;
		})
		.join('\n');

	return `(procedure public (${procName}${index})
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "${glitchText}"
	)
	= choice PrintChoices(
		"${desc}"
		"${title}"
		${DESC_WIDTH}
		glitchText
${choiceParams}
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("${glitchLog}")
	)(else
		(switch(choice)
${cases}
		)
	)
)`;
}

function genDispatcher(opts, chunks) {
	const { dispatchProcName, procName, scriptConstPrefix, dispatcherScriptConst, outPrefix, zoneLabel, srcJsRelPath } = opts;
	const chunkUses = chunks.map((_, c) => `(use "${outPrefix}${c + 1}")`).join('\n');

	// Each chunk script (~11-14KB of compiled events) gets Load()ed only for
	// the turn that needs it and DisposeScript()d immediately after -- same
	// Load(rsType num)/DisposeScript(num) idiom as the stock DisposeLoad.sc.
	// Without this, the interpreter auto-loads each chunk the first time one
	// of its <procName><N> procedures is called but never unloads it, so a
	// run touching all chunks (near-certain within a few turns, since each
	// turn picks a uniformly random event) leaves the full zone's event
	// bytecode permanently resident on top of everything else -- blowing
	// the 64KB heap segment after a handful of turns. Confirmed as the
	// cause of a real "heap space error" in the WORK zone before this
	// Load/DisposeScript cycling was added.
	let globalIndex = 0;
	const branches = chunks.map((chunkEvents, c) => {
		const lo = globalIndex;
		const hi = globalIndex + chunkEvents.length - 1;
		const scriptConst = `${scriptConstPrefix}${c + 1}_SCRIPT`;
		const cases = chunkEvents
			.map((_, i) => `\t\t\t(case ${lo + i} ${procName}${lo + i}())`)
			.join('\n');
		globalIndex += chunkEvents.length;
		return `\t(if((>= index ${lo}) and (<= index ${hi}))
		Load(rsSCRIPT ${scriptConst})
		(switch(index)
${cases}
		)
		DisposeScript(${scriptConst})
		return
	)`;
	}).join('\n');

	return `/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 ${outPrefix}.sc
 GENERATED FILE — do not hand-edit. Produced by tools/${opts.genScriptName}.
 Dispatcher only: ${dispatchProcName}(index) is the public entry point other
 scripts call; the ${zoneLabel} events' implementations live in
 ${outPrefix}1.sc..${outPrefix}${chunks.length}.sc.
 Loads only the chunk script the requested index lives in, and disposes it
 again right after the call -- see the comment on genDispatcher() in
 tools/lib/zone-events.js for why that's load-bearing, not just tidiness.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ${dispatcherScriptConst})
/******************************************************************************/
${chunkUses}
/******************************************************************************/
(procedure public (${dispatchProcName} index)
${branches}
)
/******************************************************************************/
`;
}

function genChunk(opts, chunkIndex, chunkEvents, firstGlobalIndex, srcJsRelPath) {
	const { procName, scriptConstPrefix, outPrefix, zoneLabel } = opts;
	const scriptConst = `${scriptConstPrefix}${chunkIndex + 1}_SCRIPT`;
	const procedures = chunkEvents
		.map((e, i) => genEventProcedure(procName, firstGlobalIndex + i, e))
		.join('\n/******************************************************************************/\n');
	return `/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 ${outPrefix}${chunkIndex + 1}.sc
 GENERATED FILE — do not hand-edit. Produced by tools/${opts.genScriptName} from
 the original ${srcJsRelPath}. Re-run that script after
 editing the source event data.

 ${zoneLabel} events ${firstGlobalIndex}-${firstGlobalIndex + chunkEvents.length - 1}: each is a standalone
 ${procName}<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ${scriptConst})
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
${procedures}
/******************************************************************************/
`;
}

function chunk(arr, n) {
	const base = Math.floor(arr.length / n);
	const extra = arr.length % n;
	const chunks = [];
	let start = 0;
	for (let c = 0; c < n; c++) {
		const size = base + (c < extra ? 1 : 0);
		chunks.push(arr.slice(start, start + size));
		start += size;
	}
	return chunks;
}

/**
 * @param {object} opts
 * @param {string} opts.repoRoot - absolute path to the repo root
 * @param {string} opts.srcJsRelPath - path to js/events/<zone>.js, relative to repoRoot
 * @param {string} opts.srcJsGlobal - the global CONTENT_EVENTS_<ZONE> array name in that file
 * @param {string} opts.outPrefix - output filename prefix, e.g. "workevents" / "homeevents"
 * @param {string} opts.procName - per-event procedure name prefix, e.g. "WorkEvent" / "HomeEvent"
 * @param {string} opts.dispatchProcName - dispatcher procedure name, e.g. "DoWorkEvent" / "DoHomeEvent"
 * @param {string} opts.scriptConstPrefix - e.g. "WORKEVENTS" / "HOMEEVENTS" (chunk N uses `${prefix}${N}_SCRIPT`)
 * @param {string} opts.dispatcherScriptConst - e.g. "WORKEVENTS_SCRIPT" / "HOMEEVENTS_SCRIPT"
 * @param {string} opts.zoneLabel - human label for comments, e.g. "WORK-zone" / "HOME-zone"
 */
function generateZoneEvents(opts) {
	const srcJsPath = path.join(opts.repoRoot, opts.srcJsRelPath);
	const outDir = path.join(opts.repoRoot, 'TRS_SCI', 'src');

	const src = fs.readFileSync(srcJsPath, 'utf8');
	const events = new Function(src + `\nreturn ${opts.srcJsGlobal};`)();
	const chunks = chunk(events, CHUNK_COUNT);

	const dispatcherOut = genDispatcher(opts, chunks);
	const dispatcherPath = path.join(outDir, `${opts.outPrefix}.sc`);
	fs.writeFileSync(dispatcherPath, dispatcherOut);
	console.log(`Wrote dispatcher (${dispatcherOut.length} bytes) to TRS_SCI/src/${opts.outPrefix}.sc`);

	let globalIndex = 0;
	chunks.forEach((chunkEvents, c) => {
		const out = genChunk(opts, c, chunkEvents, globalIndex, opts.srcJsRelPath);
		const outPath = path.join(outDir, `${opts.outPrefix}${c + 1}.sc`);
		fs.writeFileSync(outPath, out);
		console.log(`Wrote events ${globalIndex}-${globalIndex + chunkEvents.length - 1} (${out.length} bytes) to TRS_SCI/src/${opts.outPrefix}${c + 1}.sc`);
		globalIndex += chunkEvents.length;
	});

	return { eventCount: events.length, chunkCount: chunks.length };
}

module.exports = { generateZoneEvents };
