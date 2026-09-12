# Session handoff: T.R.S. → SCI0 port

Paste this into a fresh context window to resume. This is a cleaned-up
rewrite (previous version had grown to 3500+ lines of blow-by-blow
debugging history) written at a real milestone: the full six-zone port,
the heap-exhaustion architecture rewrite, the Case Files viewer, and all
the smaller systems are built and confirmed working. The old file's full
history (every bug found and how, every dead end tried) is still in git
history (`git log -- SESSION_HANDOFF.md`) if a specific past incident
ever needs re-reading in detail — this version keeps only what's still
true and still useful going forward.

**Also done since the rewrite above**: comments across the `.sc` source
(and the generator templates in `tools/`) were trimmed for shipping --
they'd accumulated a lot of debugging-narrative detail (references to
this file, "confirmed via the user's own playtest," etc.) that was
useful mid-production but is noise now. What/how/why is kept, the
narrative isn't. `TRS_SCI/src/game.sh` and the hand-written core scripts
(`CaseFiles.sc`, `mechanisms.sc`, `printchoices.sc`, `rm001.sc`,
`rm002.sc`, `CaseFileAccess.sc`, `CaseFileTitles.sc`) were edited
directly; the 196 generated event rooms and the 3 generated Case File
description scripts were fixed at the generator level
(`tools/lib/zone-events.js`, `tools/gen-casefile-descriptions.js`) and
regenerated, not hand-edited. All 247 `.sc` files still pass a
structural sanity check (balanced parens outside strings, paired
quotes) after the pass.

## What this is

Porting "Trauma Response Simulator" (T.R.S.), a browser stat-management
choice game living in `/home/gordonk/WebstormProjects/trauma-response-sim`
(see its `README.md`/`AUTHORING.md`/`js/` for the full original design),
to **Sierra's SCI0 engine** (King's Quest IV / Space Quest III era,
1988-90, 320x200 EGA) as a genuine, real, compilable game — not a
stylistic reskin. Interaction is menu/dialog choice lists (Sierra's
dialogue-tree convention), not the classic text parser. Tone is
in-universe pastiche, played completely straight, as if Sierra genuinely
shipped this in 1990.

## Current state — what's actually built and confirmed working

**All six zones of the original browser game are ported**: WORK (34
events), HOME (32), SOCIAL (33), SELF (33), BODY (32), PUBLIC (32) — 196
events total. Each event is its own SCI0 room (see Architecture below),
reached via `mechanisms.sc`'s `GoToNextEvent()`, which weights zone
selection toward whichever of Repression/Mask/Child is currently worst
(matching the original's `weakZoneWeight`) and avoids repeating an event
already seen in the current run (`gSeenEvent[196]`, reset per-run,
bounded retry loop rather than the original's exact 3-tier fallback —
see `game.sh` for why that simplification is safe).

**Turn loop**: each event room's `init()` shows a `PrintChoices` dialog
(up to 3 authored choices + a ~15% chance fourth "glitch" wildcard
choice with fully randomized, untagged effects), applies the choice's
effects via `mechanisms.sc`'s `ApplyChoiceEffects` (which also layers in
any unlocked coping-mechanism modifier), then calls `EndTurn()` —
increments the turn counter, clamps stats to [0,100], and either ends the
run (any stat hits its failure bound, or `gTurn` exceeds `gMaxTurns`) or
picks the next event.

**Coping mechanisms**: 5 tags (fawn/flight/fight/freeze/secure, matching
`js/content-mechanisms.js`), each unlocking permanently after 3 uses of
that tag in one run (`UNLOCK_THRESHOLD`), after which every further
choice of that tag also applies a passive stat modifier on top of its
own effect, for the rest of the run (and future runs, once unlocked —
mechanism unlock state itself does NOT reset per-run, only the
progress-toward-unlock counters do).

**Endings — full variant port, not the original one-per-condition scope
cut**: 9 survival-condition pools × 8 variants each, 3 failure-condition
pools (by which stat bottomed out) × 10 variants each = 102 total ending
variants, each tracked individually in Case Files (matches the original
browser game's actual "collect every ending you've ever seen" mechanic).
Picked and printed by `rm002.sc`'s `printEnding()`/`printSurvivalEnding()`
via one `PrintSurvivalEndingN()`/`PrintFailureEndingN()` procedure per
pool (`tools/gen-endings.js`-generated).

**Case Files** — the persistent, cross-run discovery record (matches
`README.md`'s own description) — is fully built out: a category menu
(Survival Endings / Failure Endings / Coping Mechanisms, matching the
original's own `js/codex.js` grouping) opens a scrollable per-category
list; selecting a discovered entry and clicking "View" shows its full
title+description, a sealed entry shows "Sealed. Not yet discovered."
Persists cross-session to `TRS_SCI/TRSCASE.DAT`. Reachable via the
"Case Files" menu item (`` `^f ``) or by clicking the filing cabinet in
the ending room. **Confirmed fully working end to end**, including the
real heap-exhaustion bug found and fixed on its "View" feature (see
Architecture → Load/Dispose discipline below).

**Extended Therapy (New Game+)**: matches the original's real, existing
feature under that name (not invented for this port) — survive one
standard run and it unlocks permanently (tracked as a hidden 108th Case
Files slot); every future run then offers a Standard (10 turns) vs.
Extended Therapy (20 turns, every stat swing scaled ×1.25, including
mechanism modifiers and the glitch wildcard) choice at `rm001.sc`'s
`init()`. Confirmed working end to end, including a full 20-turn run.

**Player portrait**: shown inside the `PrintChoices` dialog itself (a
`DIcon`, not a room-background draw — the room background is almost
always covered by the dialog anyway) via `mechanisms.sc`'s
`GetPortraitMood()`, 4 loops (neutral + one per stat), switching away
from neutral once the worst stat's "danger" value crosses
`PORTRAIT_NEUTRAL_THRESHOLD` (60).

**Stat display**: the status line (always visible, including over an
open dialog) shows live percentages — `T.R.S.    REP: 40 % | MASK: 60 % |
CHILD: 60 %`. (Bar-graph gauge visuals were tried and reverted twice —
see Findings below for the actual dialect-level bug that kept breaking
them — the numeric-percent format is the final, shipped form.)

**Clickable office objects** (ending room, `rm002.sc`): the filing
cabinet opens Case Files, the computer starts a fresh run
(`newRoom(INITROOMS_SCRIPT)` + a manual per-run stat/mechanism reset in
`rm001.sc`'s `init()` — deliberately not a full kernel `RestartGame()`).
Both hit-tested via hand-estimated screenshot rectangles
(`CABINET_X1/Y1/X2/Y2`, `COMPUTER_X1/Y1/X2/Y2` in `game.sh`) — confirmed
working, rectangles never needed adjustment.

**Background music**: a General MIDI driver (`gm.drv`) is wired up and a
real MIDI file has been imported into sound resource 3 (`n003=BGM`),
looping continuously via `rm001.sc`. **Still has an open, unresolved
audio-fidelity issue** — see Open Items below.

## Architecture reference

**One room per event** — the single biggest structural fact about this
codebase. There is no more per-zone dispatcher+chunk system; each of the
196 events is its own SCI0 room, numbers 200-395:

| Zone | Room range | Event count |
|---|---|---|
| WORK | 200-233 | 34 |
| HOME | 234-265 | 32 |
| SOCIAL | 266-298 | 33 |
| SELF | 299-331 | 33 |
| BODY | 332-363 | 32 |
| PUBLIC | 364-395 | 32 |

A room number is `<ZONE>_ROOM_BASE + localIndex` (`game.sh`). This
replaced an earlier dispatcher-script-plus-loaded-chunk design after a
genuine, confirmed SCI0 heap-fragmentation problem (the same
load/use/dispose cycle sometimes fully reclaimed memory and sometimes
didn't, uncorrelated with anything in the game's own code — see Findings
below for the general lesson). The engine's own native room-transition
cleanup now handles all of that automatically; only one room's content
is ever resident at a time.

**Load/Dispose discipline** — every script in this codebase is one of
two kinds, and mixing them up is the single most common source of real
bugs this project has hit:
- **Permanently resident** (loaded once, never disposed): `Main.sc`(0),
  `Controls.sc`, other stock always-on scripts, `PrintChoices.sc`(100),
  `Mechanisms.sc`(106). These are small and called every turn/frequently
  enough that Load/Dispose churn isn't worth it.
- **Load/Dispose-scoped** (loaded right before use, disposed right
  after, every call site): `CaseFiles.sc`(107), `CaseFileAccess.sc`(136),
  `CaseFileTitles.sc`(137), `CaseFileDescriptionsSurvival/Failure/
  Mechanisms.sc`(138/139/140), `CaseFileCategory.sc`(141), all 12
  `EndingSurvivalN`/`EndingFailureN` scripts (162-173). `CaseFiles.sc`
  (the category menu + persistence) and `CaseFileCategory.sc` (the
  actual per-category browsing/View screen) are never resident at the
  same time -- `ShowCaseFiles()` returns which category was picked
  instead of dispatching internally, so callers (`menubar.sc`,
  `rm002.sc`) Dispose one before Loading the other. These are only
  needed in brief, infrequent moments
  (opening Case Files, printing one ending) and would otherwise sit as
  dead weight in the 64KB heap for the whole session.

  **`(use "x")` only resolves symbols at compile time — it has zero
  runtime effect.** A script gets auto-loaded into the heap the first
  time any of its public procedures is called from a script that isn't
  already resident, but nothing ever auto-unloads it. Any new call site
  into a Load/Dispose-scoped script needs its own explicit
  `Load(rsSCRIPT X)`/`DisposeScript(X)` pair — there is no way to get
  this for free, and forgetting it is the single most common way heap
  problems have recurred throughout this project.

**Case Files flat index scheme** (`game.sh`, `CASEFILE_COUNT`=108):
- `0-71`: 9 survival pools × 8 variants (pool N = indices `N*8..N*8+7`)
- `72-101`: 3 failure pools × 10 variants, repression/mask/child order
  (pool N = indices `72+N*10..72+N*10+9`)
- `102-106`: the 5 coping mechanisms, `TAG_FAWN..TAG_SECURE` order,
  offset by `CASEFILE_MECH_BASE`(102)
- `107`: Extended Therapy unlock flag (`CASEFILE_NGPLUS`) — not a real
  case file, rides on this same array purely because it's the one
  proven persistence mechanism in this codebase. `VIEWABLE_CASEFILE_COUNT`
  (107) keeps the viewer from showing it as a bogus 108th entry.
- Backed by 108 separate scalar globals (`gCF0..gCF107` in `Main.sc`),
  **not an array** — a global array declared in `Main.sc` isn't visible
  from another script the way scalars are (see Findings below).
  `GetCaseFile`/`SetCaseFile` (`CaseFileAccess.sc`) give array-like
  access over them.

**Key files**:
- `Main.sc`(0) — globals (`gRepression`/`gMask`/`gChild` start at
  40/60/60; `gTurn`/`gMaxTurns`; `gHardMode`/`gNgPlusUnlocked`;
  `gCF0..gCF107`; the 5 mechanism count/unlocked pairs), the status
  line, boot-time `LoadCaseFiles()`.
- `mechanisms.sc`(106, always resident) — `ApplyChoiceEffects`/
  `ApplyGlitch` (mechanism unlock + modifier logic), `PickZone`/
  `ZoneStatBias`/`GoToNextEvent`/`ResetSeenEvents`/`EndTurn` (turn loop
  and zone/event selection), `GetPortraitMood`.
- `printchoices.sc`(100, always resident) — `PrintChoices`, the vertical
  stacked-`DButton` dialog every event uses instead of stock `Print()`'s
  broken-for-long-text horizontal button row.
- `CaseFiles.sc`(107, persistence + category menu) + `CaseFileCategory.sc`
  (141, the per-category browsing/View screen) + `CaseFileAccess.sc`(136)
  + `CaseFileTitles.sc`(137) + `CaseFileDescriptions{Survival,Failure,
  Mechanisms}.sc`(138-140) — split across this many files purely for
  heap-residency reasons (see Load/Dispose discipline above).
- `EndingSurvival0-8.sc`(162-170) / `EndingFailure0-2.sc`(171-173) — one
  file per ending pool, generated.
- `rm001.sc`(1) — per-run reset, Extended Therapy mode-choice dialog,
  bootstraps the first turn via `EndTurn()`. Never revisited mid-run.
- `rm002.sc`(2) — the ending room: prints the ending, filing
  cabinet/computer hotspots.
- `rm200`-`rm395` — the 196 generated event rooms.
- `game.sh` / `game.ini` — constants and the resource manifest,
  respectively. Every new script needs an entry in **both**.

**Content pipeline** (`tools/`): `tools/lib/zone-events.js` is the
shared generator library (event-room emission, `sciString()`-based ASCII
safety/escaping); `tools/gen-<zone>-events.js` ×6 are thin per-zone
entry points; `tools/gen-endings.js` generates the ending-pool scripts;
`tools/gen-casefile-descriptions.js` generates the three Case File
description scripts; `tools/verify-casefile-indices.js` is a dev-time
check that the generated descriptions stay in index lockstep with
`CaseFileTitles.sc`'s hand-written titles. All are idempotent — re-run
after editing the relevant `js/content*.js` source.

## Decisions already made (don't re-litigate these)

- **Target**: late-SCI0 (Police Quest II / Larry 2-3 era UI conventions),
  built with **SCI Companion 3** (github.com/icefallgames/SCICompanion).
- **Scope**: full 1:1 port of all six zones (this was originally scoped
  as a WORK-only vertical slice; expanded to completion once the core
  mechanic was proven).
- **Seeded runs**: SCI0-side seeds only; not required to match seeds from
  the browser version (the original's mulberry32 RNG is 32-bit and
  doesn't port cleanly to SCI0's 16-bit arithmetic).
- **Permanently cut, not deferred**: runtime-loadable content packs /
  `editor.html` (SCI0 compiles everything at build time), Share Result
  PNG / mobile share sheet (no such OS concept on DOS), ScummVM as a test
  target (its SCI engine can't parse this project's compiled resources —
  confirmed a ScummVM-side limitation, not our output; use DOSBox-X), the
  original's `arcade` mode (infinite turns/escalating multiplier —
  separate from Extended Therapy, never in scope).

## Environment / toolchain

- **Game repo**: `/home/gordonk/WebstormProjects/trauma-response-sim/` —
  the original browser game (`js/`, `css/`, `index.html`) and the SCI0
  port (`TRS_SCI/`) live in the same repo. `TRS_SCI/src/` has the `.sc`
  source + compiled `.sco`; `game.ini` is the resource manifest;
  `SCIV.EXE` is the real period-accurate SCI0 interpreter;
  `resource.map`/`resource.001` are the compiled game output.
  `TRS_SCI/art/` is the user's own working art/audio source (gitignored,
  not build source). Any `.zip` directly under `TRS_SCI/` is a
  regenerated distribution build (gitignored).
- **SCI Companion IDE**: `/home/gordonk/WebstormProjects/sci_companion/`
  — deliberately kept OUT of any git repo (third-party binary tooling).
- **Editing**: done directly on the Linux host with normal file tools.
- **Compiling**: must happen in a Windows VM (QEMU/KVM, domain `win10`,
  NAT `192.168.122.0/24`) — SCI Companion's editor runs fine under Wine,
  but its compiler hangs indefinitely under Wine. No CLI/batch compiler
  exists; a human clicks Compile in the VM each time. Bridge: a Samba
  share on the host, mapped as a network drive in the VM (share should
  point at `~/WebstormProjects/` so both `trauma-response-sim/TRS_SCI`
  and `sci_companion` are visible under one mapped drive). A batch of new,
  cross-referencing scripts often needs 2-3 rounds of "Compile All" +
  "Rebuild Resources" before everything settles — expected, not a bug,
  as long as errors are shrinking/changing each round.
- **Testing compiled output**: **DOSBox-X**, run natively on the Linux
  host (`paru -S dosbox-x`):
  ```
  dosbox-x -c "MOUNT C \"<path to TRS_SCI>\"" -c "C:" -c "SCIV.EXE"
  ```
  ScummVM does not work as a test target (see Decisions above). **Real
  Windows XP hardware (via NTVDM, launching the `.exe` from inside the
  WinXP desktop) doesn't either**: user tested on a period Dell Latitude
  and got music playing but a completely black screen, no picture at
  all. `resource.cfg` targets genuine real-mode EGA (`videoDrv =
  EGA320.DRV`, `mode = real`, INT 10h mode 0Dh) -- NTVDM's EGA graphics
  emulation is well known to be broken/unsupported (its VGA mode 13h
  support is comparatively solid), while sound is a fully separate
  subsystem that works fine. Not a project bug: the same compiled
  resources render correctly in both SCI Companion's own DOSBox
  integration and standalone DOSBox-X, repeatedly, and a real palette/art
  problem would show as garbled colors, not a clean black screen. If
  real period hardware is ever wanted as a test target, run DOSBox/
  DOSBox-X on the WinXP machine itself (sidesteps NTVDM's video emulation
  entirely) rather than launching the compiled game directly, or boot
  that hardware into genuine real-mode DOS instead of through WinXP.
- **MIDI/audio setup, three separate places, each configured
  independently** (a real, confirmed source of "sounds different"
  reports — see Open Items):
  1. Linux-host DOSBox-X: `~/.config/dosbox-x/dosbox-x-*.conf`'s
     `[midi]` section. Fixed this session — was `mididevice = default`
     with no synthesizer actually listening on ALSA (confirmed via
     `aconnect -l`). Now `mididevice = fluidsynth` +
     `fluid.soundfont = /usr/share/soundfonts/FluidR3_GM.sf2`.
  2. `TRS_SCI/dosbox.conf` — the config SCI Companion's own "Run Game"
     button uses inside the VM. Had no `[midi]` section at all (plain
     vanilla DOSBox config, no `fluidsynth` option available). Added:
     `mpu401=intelligent`, `mididevice=win32`, `midiconfig=` — forces the
     Windows MIDI mapper explicitly rather than an ambiguous "default"
     inside a VM with no real MIDI hardware.
  3. SCI Companion's own Sound Editor preview — a third, separate
     playback path with its own quirk (see Open Items).

## Findings / gotchas worth not re-discovering

- **An SCI0 sound resource isn't just an imported MIDI file — it's MIDI
  data plus a per-channel, per-device map.** Each of the 16 MIDI
  channels stores its own driver-device index, required voice count, and
  a per-device enable bitmask (confirmed via `docs/SCI0-research-
  findings.md`'s byte-level breakdown and SCI Companion's own
  `Help/_sources/sounds.txt`). Critically, **the Sound Editor's preview
  button does NOT apply this per-device filtering** — confirmed by the
  user switching the selected device and hearing no change at all. Only
  a real compiled-game launch through actual DOSBox honors the
  per-channel enable bitmask. Practical consequence: you cannot audibly
  A/B-test per-device track data via Preview — the only reliable check
  is visually confirming each track's checkbox in the Toolbox pane is
  checked *for the specific device in use* (General MIDI/`gm.drv` in
  this project), not relying on how Preview sounds.
- **Scripts auto-load on call but never auto-unload** — see Architecture
  → Load/Dispose discipline above. The root cause of nearly every real
  heap bug this project has hit.
- **Global arrays declared in `Main.sc` aren't visible from other
  scripts via `(use "main")` the way scalars are** — only individual
  scalar globals export correctly. A script-LOCAL array (declared once
  near the top of the *same* script that uses it, via a `(local
  arr[N])` block right after its `(use ...)` list) works fine and is the
  established pattern for large buffers (`CaseFiles.sc`'s `buf[3424]`,
  `mechanisms.sc`'s `gSeenEvent[196]`).
- **A per-call procedure `(var arr[N])` local has a much smaller size
  ceiling than the general 64KB heap** — confirmed via a real runtime
  crash ("you did something we didn't expect") at 3424 bytes that
  compiled fine but failed only at runtime. The same array declared as a
  script-level `(local ...)` instead (identical usage syntax, just moved
  out of the procedure's own `(var ...)` list) works. Use script-level
  locals for any buffer above a few hundred bytes. (Stock `Print()`'s
  own `msgBuf[1013]` per-call local is proof ~1KB is fine as a per-call
  local — the ceiling is somewhere between roughly 1KB and 3.4KB, never
  pinned down more precisely than that.)
- **A `DSelector`'s `state` bits 1 and 2 are independent and mean very
  different things.** Bit 1 makes it the dialog's initially-focused
  control (arrow keys/Page Up-Down reach it) with no other effect. Bit 2
  makes `Dialog:handleEvent`'s base loop treat ANY claimed event
  (including just scrolling) as "done, close the modal loop" — correct
  for a `DButton` (which legitimately wants both, default `state=3`),
  wrong for a browse-only scrolling list. Use `state(1)` only for a pure
  browse/scroll selector.
- **`DSelector` has no concept of "N entries, then stop"** — its
  `advance()` just keeps scrolling as long as the next slot's first byte
  is non-zero. A local buffer isn't zero-initialized by default (leftover
  stack garbage); explicitly zero the whole buffer before writing real
  entries into it, or scrolling past the last entry renders garbage as
  more rows.
- **`fOPENFAIL`/`fOPENCREATE` are swapped from what their names
  suggest** in this SCI0 dialect (confirmed via SCI Companion's own
  bundled kernel docs and `sci.sh`'s `#ifdef SCI_0` block) —
  `fOPENCREATE` is actually "open existing, fail if not possible" (the
  safe read flag), `fOPENFAIL` actually triggers create/reset-style
  behavior that can silently wipe a file immediately before the next
  line tries to read it back. This caused a real, very confusing,
  multi-round persistence bug (writes always looked correct on disk,
  reads never worked) before being traced to this exact swap.
- **There's no way to embed a literal `"` inside a `""`-delimited SCI0
  string.** Transliterate to a single quote instead if the source text
  needs one (the project's own `tools/lib/sci-string.js` does this
  automatically for generated content).
- **A non-ASCII byte in a string literal isn't a missing-glyph
  placeholder — it's read as a raw control byte** and corrupts
  rendering (confirmed: an em dash ate an entire word). `sciString()`
  transliterates common typographic Unicode (em/en dash, curly quotes,
  ellipsis) and throws on anything else unmapped.
- **No confirmed precedent anywhere in this codebase for chained
  `(if...)(else...)` 3+ branches deep**, nor for `switch` on anything but
  a plain variable (never a function-call expression directly). Prefer a
  flat sequence of independent single-branch `if`s with early
  `return`/`break`, or assign to a local first before switching on it.
  Also no precedent for `and`-chains longer than 4 terms — split into
  nested 2-term chains instead of extending further.
- **`paramTotal` counts every argument the caller passed, including ones
  already bound to named parameters before a trailing rest-array
  parameter.** Subtract the named-parameter count from `paramTotal`
  before using it as a rest-array loop bound (see `DisposeLoad.sc`'s own
  `(= paramTotal (- paramTotal 2))` for 2 named params, or
  `PrintChoices`'s `(- paramTotal 4)` for 4).
- **A new script file needs both the `.sc` on disk AND a matching
  `game.ini` `[Script]` entry** — but that combination alone hasn't
  always been sufficient for the file to show up in SCI Companion's own
  Scripts panel. When it doesn't, the fix is creating the script via SCI
  Companion's own **"New empty script"** button rather than assuming the
  file is broken or the entry is wrong.
- **A brand-new circular `(use ...)` pair between two scripts that have
  never bootstrapped each other before may need a manual bootstrap**:
  temporarily comment out the new call/symbol on one side, compile that
  script alone (F8), compile the other side alone (now resolves against
  the freshly updated `.sco`), restore the commented line, then Compile
  All. This codebase's `Main.sc`↔`CaseFiles.sc` pair has needed this more
  than once. Where avoidable (a new feature would create a *brand-new*
  circular pair), prefer restructuring to avoid it entirely — several
  features in this project (player portrait, stat gauges, the Case Files
  category menu) were deliberately built to reuse already-stable
  dependency chains instead.
- **`RESOURCE.MAP`/`resource.001` are append-only by design** — every
  compile appends fresh bytecode rather than overwriting; the engine
  always uses the newest matching entry. A resource browser showing many
  duplicate-looking entries per script number is cosmetic clutter, not a
  bug — "Rebuild Resources" repacks it down once compiling is actually
  succeeding.
- **Samba denies "open for execute" on files lacking the Unix `+x` bit**
  even though it serves them fine for read/copy — any `.exe`/`.com`/
  `.dll` under the shared tree needs `chmod +x` on the Linux side.
- **`ufw`'s default deny-incoming policy silently blocks DHCP broadcasts**
  on libvirt's virtual bridge, causing a VM to fall back to an APIPA
  address with no indication why. Fix: `sudo ufw allow in on virbr0`.
- **Modern Windows 10 refuses passwordless/guest SMB** — set a real
  Samba password (`smbpasswd -a <user>`), don't bother with no-auth.

## Open items — what's actually left

1. **Background music sounds different in DOSBox than in SCI Companion's
   own preview — unresolved.** Two real, separate causes found and fixed
   so far (Linux DOSBox-X's unconfigured MIDI output; `TRS_SCI/
   dosbox.conf`'s missing `[midi]` section for SCI Companion's "Run Game"
   button — see Environment above), but the user confirmed the mismatch
   persists even after those. The remaining, most likely cause: the
   Sound Editor's Preview button doesn't apply per-device channel
   filtering at all (see Findings above), so it's not possible to verify
   by ear whether every track is actually enabled for the General MIDI
   device specifically. **Concrete next step**: with "General MIDI"
   selected in the Sound Editor's Toolbox pane, visually check every
   track's enabled checkbox for that device (not by listening), since a
   track only enabled for a different device during the original
   "import + enable all tracks" pass would play in Preview (which
   ignores the filter) but drop out or sound wrong in the real game.
2. **Case Files "View" hit "Out of heap space" again, this time on real
   hardware/86Box, after the earlier per-category-script fix -- fixed
   again, not yet recompiled/tested.** The earlier fix (load one
   description script at a time, split per category) reduced the
   *peak* load during a View click but didn't address the *baseline*:
   `ShowCaseFileCategory()` still lived inside `CaseFiles.sc` itself,
   so `LoadCaseFiles`/`SaveCaseFiles`/`MarkCaseFile`/`UnlockNgPlus`/the
   category menu all stayed resident (~9-10KB) for the entire time a
   category was open, on top of whatever else a real run had already
   used. **Fix**: split `ShowCaseFileCategory()` out into its own new
   script, `CaseFileCategory.sc` (`CASEFILECATEGORY_SCRIPT` = 141) --
   `CaseFiles.sc`'s `ShowCaseFiles()` now just returns which category
   was picked (1/2/3/0) instead of dispatching internally; both call
   sites (`menubar.sc`'s menu item, `rm002.sc`'s filing cabinet) now do
   a two-stage `Load`/call/`Dispose` -- `CaseFiles.sc` first for the
   menu, then `Dispose` it before `Load`ing `CaseFileCategory.sc` for
   the actual browsing/View screen. The two scripts are never resident
   together. Ran the structural sanity check on all touched/new files
   (`game.sh`, `game.ini`, `CaseFiles.sc`, `CaseFileCategory.sc`,
   `menubar.sc`, `rm002.sc`) -- clean; confirmed no duplicate script
   numbers and the new `game.ini` entry matches the new file.
   **`CaseFileCategory.sc` is a brand-new script file** -- per this
   project's own established gotcha, it may need SCI Companion's "New
   empty script" wizard treatment before it shows up in the Scripts
   panel, even with a correct `game.ini` entry already in place (see
   the `CaseFiles.sc` registration saga this project hit early on).
   - **Confirmed by the user: this split alone was NOT enough.**
     Recompiled with the split in place (ruled out a stale build) and
     "Out of heap space" still happened on a "View" click. Real
     remaining cause: the split reduced the *size* of what's Load/
     Dispose-cycled during a View click, but a click still did THREE
     separate cycles back to back (`CaseFileAccess` to check discovered,
     a description script, `CaseFileTitles`) -- and this dialect's
     Load/DisposeScript cycling has never reliably reclaimed memory
     (the exact lesson the original one-room-per-event rewrite was
     built around: "the identical cycle sometimes fully reclaimed its
     memory and sometimes didn't"). Reducing cycle *size* helps
     probabilistically; it doesn't remove the underlying nondeterminism,
     which is consistent with this working on DOSBox-X once and then
     failing on 86Box/real hardware with the same code.
   - **Fix, same file**: cut two of the three cycles entirely by reusing
     data the list-building loop (top of `ShowCaseFileCategory()`)
     already computes, instead of re-fetching it. (1) `discoveredFlags
     [72]` (a small per-call local, safely under the ~1KB known-safe
     per-call-local size) caches each entry's `GetCaseFile()` result
     from that same loop, so the View handler no longer needs a second
     `CaseFileAccess` Load/Dispose. (2) The title text is scanned
     directly out of `buf`'s own row text ("N. Title", already built by
     that same loop) -- find the literal `.` byte, skip it and the
     following space -- instead of a second `CaseFileTitles` Load/
     Dispose to re-fetch a string that was already in memory. A "View"
     click now does exactly ONE Load/Dispose cycle (whichever
     description script matches the open category), down from three.
     Ran the structural sanity check again -- clean. **Not yet
     recompiled/retested.**
3. **Nothing else is currently known-broken.** Everything else in
   "Current state" above is confirmed working by the user's own
   playtesting. If picking this project back up cold, a good sanity
   check is simply: does a standard run complete, does Extended
   Therapy unlock and work, does Case Files show discovered/sealed
   correctly and let you view a description, do both office hotspots
   work — all confirmed at least once, but a regression from an
   unrelated future change is always possible.
