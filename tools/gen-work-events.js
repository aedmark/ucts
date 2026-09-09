#!/usr/bin/env node
// Reads the original T.R.S. WORK-zone event data (js/events/work.js) and
// generates SCI0 source implementing each event as a standalone procedure,
// using PrintChoices (TRS_SCI/src/printchoices.sc) for the choice dialog.
// Regenerate after editing work.js by re-running this script.
//
// Output is split across CHUNK_COUNT script files (workevents1.sc..N) plus a
// slim workevents.sc dispatcher, rather than one big file: a single-file
// version of all 34 events ran ~46KB of source, well past the ~16KB
// practical SCI0 script-size ceiling documented in
// docs/SCI0-research-findings.md.
'use strict';
const fs = require('fs');
const path = require('path');

const REPO_ROOT = path.resolve(__dirname, '..');
const SRC_JS = path.join(REPO_ROOT, 'js/events/work.js');
const OUT_DIR = path.join(REPO_ROOT, 'TRS_SCI/src');

const BUTTON_WRAP_LEN = 36; // matches the hand-tuned "The Typo" precedent
const DESC_WIDTH = 290; // DText width (px) passed to PrintChoices; kernel TextSize() wraps within it, so widening is safe as long as it stays under the 320px screen (DText starts at x=4)
const CHUNK_COUNT = 4;
// Deliberate scope cut to fix a recurring heap-space exhaustion: cap every
// event to its first MAX_CHOICES authored responses (glitch is a bonus 4th
// slot on top, not counted here) instead of the original's 3-5. Shrinks
// generated code, dialog height, and per-turn heap pressure all at once.
// Changes game balance (fewer options per event) -- accepted tradeoff,
// content/balance to be revisited once the engine side is solid.
const MAX_CHOICES = 3;

function loadEvents() {
	const src = fs.readFileSync(SRC_JS, 'utf8');
	return new Function(src + '\nreturn CONTENT_EVENTS_WORK;')();
}

// The compiled font only has glyphs for standard printable ASCII; a
// non-ASCII byte (even a "harmless"-looking typographic dash or curly
// quote) isn't a missing-glyph placeholder -- it gets read as raw control
// bytes by the text renderer and corrupts the dialog (confirmed in-game:
// an em dash in two WORK choices rendered as a blank line eating the word
// before it). Transliterate the handful of typographic characters that
// show up in prose text; throw on anything else so a future addition to
// work.js can't silently emit broken source.
const ASCII_TRANSLITERATIONS = {
	'—': '-', // em dash
	'–': '-', // en dash
	'‘': "'", '’': "'", // curly single quotes
	'“': '"', '”': '"', // curly double quotes -- still rejected below if left in a string, see the "{}\\ check
	'…': '...', // ellipsis
};

function sciString(s) {
	s = s.replace(/[—–‘’“”…]/g, ch => ASCII_TRANSLITERATIONS[ch]);
	if (/["{}\\]/.test(s)) {
		throw new Error(`string contains a character gen-work-events.js can't emit safely: ${JSON.stringify(s)}`);
	}
	if (/[^\x20-\x7E]/.test(s)) {
		throw new Error(`string contains a non-ASCII character with no known transliteration: ${JSON.stringify(s)}`);
	}
	return s;
}

function wrapButtonText(text, maxLineLen) {
	const words = sciString(text).split(' ');
	const lines = [];
	let cur = '';
	for (const w of words) {
		if (cur === '') cur = w;
		else if (cur.length + 1 + w.length <= maxLineLen) cur += ' ' + w;
		else { lines.push(cur); cur = w; }
	}
	if (cur) lines.push(cur);
	return lines.join('\\n');
}

const TAG_CONSTANTS = { fawn: 'TAG_FAWN', flight: 'TAG_FLIGHT', fight: 'TAG_FIGHT', freeze: 'TAG_FREEZE', secure: 'TAG_SECURE' };

function signedDelta(effect) {
	return effect.op === 'add' ? effect.value : -effect.value;
}

function genEventProcedure(index, event) {
	const desc = sciString(event.desc);
	const title = sciString(event.title);
	const glitchText = wrapButtonText(event.glitch.text, BUTTON_WRAP_LEN);
	const glitchLog = sciString(event.glitch.log);
	const choices = event.choices.slice(0, MAX_CHOICES);

	const choiceParams = choices
		.map((c, i) => `\t\t"${wrapButtonText(c.text, BUTTON_WRAP_LEN)}" ${i}`)
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

	return `(procedure public (WorkEvent${index})
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

function genDispatcher(chunks) {
	const chunkUses = chunks.map((_, c) => `(use "workevents${c + 1}")`).join('\n');

	// Each chunk script (~11-13KB of compiled events) gets Load()ed only for
	// the turn that needs it and DisposeScript()d immediately after — same
	// Load(rsType num)/DisposeScript(num) idiom as the stock DisposeLoad.sc.
	// Without this, the interpreter auto-loads each chunk the first time one
	// of its WorkEvent<N> procedures is called but never unloads it, so a
	// run touching all 4 chunks (near-certain within a few turns, since each
	// turn picks a uniformly random event) leaves ~46KB of event bytecode
	// permanently resident on top of everything else — blowing the 64KB
	// heap segment after a handful of turns. Confirmed as the cause of a
	// real "heap space error" after a few turns in-game.
	let globalIndex = 0;
	const branches = chunks.map((chunkEvents, c) => {
		const lo = globalIndex;
		const hi = globalIndex + chunkEvents.length - 1;
		const scriptConst = `WORKEVENTS${c + 1}_SCRIPT`;
		const cases = chunkEvents
			.map((_, i) => `\t\t\t(case ${lo + i} WorkEvent${lo + i}())`)
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
 workevents.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js.
 Dispatcher only: DoWorkEvent(index) is the public entry point other scripts
 call; the 34 WorkEvent<N> implementations live in workevents1.sc..workevents${CHUNK_COUNT}.sc.
 Loads only the chunk script the requested index lives in, and disposes it
 again right after the call -- see the comment on genDispatcher() in
 tools/gen-work-events.js for why that's load-bearing, not just tidiness.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS_SCRIPT)
/******************************************************************************/
${chunkUses}
/******************************************************************************/
(procedure public (DoWorkEvent index)
${branches}
)
/******************************************************************************/
`;
}

function genChunk(chunkIndex, chunkEvents, firstGlobalIndex) {
	const scriptConst = `WORKEVENTS${chunkIndex + 1}_SCRIPT`;
	const procedures = chunkEvents
		.map((e, i) => genEventProcedure(firstGlobalIndex + i, e))
		.join('\n/******************************************************************************/\n');
	return `/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents${chunkIndex + 1}.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original ${path.relative(REPO_ROOT, SRC_JS)}. Re-run that script after
 editing the source event data.

 WORK-zone events ${firstGlobalIndex}-${firstGlobalIndex + chunkEvents.length - 1}: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
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

function main() {
	const events = loadEvents();
	const chunks = chunk(events, CHUNK_COUNT);

	const dispatcherOut = genDispatcher(chunks);
	const dispatcherPath = path.join(OUT_DIR, 'workevents.sc');
	fs.writeFileSync(dispatcherPath, dispatcherOut);
	console.log(`Wrote dispatcher (${dispatcherOut.length} bytes) to ${path.relative(REPO_ROOT, dispatcherPath)}`);

	let globalIndex = 0;
	chunks.forEach((chunkEvents, c) => {
		const out = genChunk(c, chunkEvents, globalIndex);
		const outPath = path.join(OUT_DIR, `workevents${c + 1}.sc`);
		fs.writeFileSync(outPath, out);
		console.log(`Wrote events ${globalIndex}-${globalIndex + chunkEvents.length - 1} (${out.length} bytes) to ${path.relative(REPO_ROOT, outPath)}`);
		globalIndex += chunkEvents.length;
	});
}

main();
