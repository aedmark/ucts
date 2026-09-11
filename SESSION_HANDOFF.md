# Session handoff: T.R.S. → SCI0 port

Paste this into a fresh context window to resume. It captures decisions,
environment setup, and exact state — nothing here should need re-deriving.

## What this is

Porting "Trauma Response Simulator" (T.R.S.), a browser stat-management
choice game living in `/home/gordonk/WebstormProjects/trauma-response-sim`
(see its `README.md`/`AUTHORING.md`/`js/content*.js`/`js/events/*.js` for the
full original design), to **Sierra's SCI0 engine** (King's Quest IV / Space
Quest III era, 1988-90, 320x200 EGA) as a genuine, real, compilable game —
not a stylistic reskin.

## Decisions already made (don't re-litigate these)

- **Target**: late-SCI0 (Police Quest II / Larry 2-3 era UI conventions),
  built with **SCI Companion 3** (github.com/icefallgames/SCICompanion).
- **Interaction model**: menu/dialog choice lists (Sierra's dialogue-tree
  convention), not the classic text parser.
- **Scope**: a vertical slice — just the WORK zone (34 events in the original
  `js/events/work.js`), not all six zones. Full 1:1 port deferred.
- **Tone**: in-universe pastiche — played completely straight, as if Sierra
  genuinely shipped this in 1990. No wink to the player.
- **Seeded runs**: SCI0-side seeds only; not required to match seeds from the
  browser version (the original's mulberry32 RNG is 32-bit and doesn't port
  cleanly to SCI0's 16-bit arithmetic — we'd hand-roll a different PRNG).
- **Permanently cut, not just deferred**: runtime-loadable content packs /
  `editor.html` (SCI0 compiles everything at build time, no equivalent),
  Share Result PNG / mobile share sheet (no such OS concept on DOS), ScummVM
  as a test target (see Findings below — use DOSBox-X instead).

## Environment / toolchain (all validated, working)

**⚠️ Project layout was rebased late in this doc's life — the game project
moved repos entirely.** Everything below reflects the current, live layout;
if you see a path elsewhere in this doc under `ucts/` or `sci/TemplateGame/
SCI0/` or `sci/TRS/`, it's stale — translate it using the table below rather
than trusting it literally.

- **Game repo (git-tracked, this file lives here)**:
  `/home/gordonk/WebstormProjects/trauma-response-sim/` — the ORIGINAL
  browser game (`js/`, `css/`, `index.html`, `README.md`/`AUTHORING.md`,
  etc.) **and** the SCI0 port now live in the same repo. This replaces the
  earlier separate `ucts` fork entirely — don't keep editing anything under
  `~/WebstormProjects/ucts/` going forward, it's been superseded.
  - `TRS_SCI/` — the actual SCI0 game project (was `ucts/sci/TRS/`, and
    before that `ucts/sci/TemplateGame/SCI0/`). `TRS_SCI/src/` has the `.sc`
    source + compiled `.sco`, `game.ini` is the resource manifest,
    `SCIV.EXE` is the real period-accurate SCI0 interpreter, `resource.map`/
    `resource.001` are the compiled game output.
  - `docs/SCI0-research-findings.md` — the SCI0 technical research brief
    (unchanged content, just relocated).
  - `tools/gen-work-events.js` — the WORK-zone content-pipeline generator
    (unchanged logic; `OUT_DIR` updated to point at `TRS_SCI/src`, `SRC_JS`
    now resolves to this same repo's own `js/events/work.js` instead of a
    cross-repo copy — one less moving part than before).
  - This `SESSION_HANDOFF.md`.
- **SCI Companion IDE — deliberately NOT in any git repo**:
  `/home/gordonk/WebstormProjects/sci_companion/` (a sibling of
  `trauma-response-sim/`, was `ucts/sci/`). The user moved it out on
  purpose — it's a large third-party binary tool bundle, not project
  content, and doesn't belong in version control. `SCICompanion.exe`, the
  bundled `Help/`/`Samples/`/`Objects/` docs, etc. all live here now.
  **Unverified since the move**: whether the VM's Samba-mapped network
  drive still resolves correctly now that the IDE and the project it needs
  to compile (`TRS_SCI/`) live under two different top-level folders on the
  host instead of one shared tree — worth confirming the VM can still see
  both before assuming compiling still works out of the box. The old share
  point was `\\192.168.122.1\ucts` serving all of `ucts/`; that mapping is
  now stale too (see below).
- **Editing**: done directly on the Linux host with normal file tools. Works
  fine.
- **Compiling**: **must** happen in a Windows VM — SCI Companion's editor
  runs fine under Wine (confirmed, opens and renders correctly), but its
  compiler hangs indefinitely under Wine (confirmed via both "Compile All"
  and single-script F8 — genuine Wine incompatibility in that code path, not
  content-specific). There is no CLI/batch compiler — compiling is GUI-only,
  so a human has to click Compile in the VM each time; nothing to script
  around that.
  - VM: QEMU/KVM libvirt domain named `win10`, default NAT network
    (`192.168.122.0/24`, host at `192.168.122.1`).
  - Bridge: a **Samba share** on the host (Samba user `gordonk`, password
    set via `smbpasswd` during setup — user knows it), mapped as a network
    drive inside the VM so editing on the Linux host is immediately visible
    for compiling in the VM — no manual copy step needed. **The exact share
    path needs re-confirming post-rebase** (see above) — it used to be
    `\\192.168.122.1\ucts` serving `/home/gordonk/WebstormProjects/ucts`;
    now that the project and the IDE are two separate sibling folders under
    `~/WebstormProjects/`, the share most likely needs to point at
    `/home/gordonk/WebstormProjects/` itself (one level up) so both
    `trauma-response-sim/TRS_SCI` and `sci_companion` are visible under one
    mapped drive — but this hasn't been confirmed working since the move.
  - Compiling in the VM is fast once working: ~0.36s for 30 scripts. Any
    apparent "hang" during compilation on the Linux/Wine side is the known
    Wine bug above, not a real slowdown.
- **Testing compiled output**: **DOSBox-X** (installed via AUR: `paru -S
  dosbox-x`), run natively on the Linux host — no VM needed for this step.
  Launch pattern:
  ```
  dosbox-x -c "MOUNT C \"<path to TRS_SCI>\"" -c "C:" -c "SCIV.EXE"
  ```
  **ScummVM does NOT work as a test target** — its SCI engine fails to parse
  resource files built by SCI Companion 3 with `Map version was: 3, retrying
  with: 1!` / `Couldn't find any views!` even on a clean full rebuild, despite
  SCI Companion's own Version Detection dialog reporting everything correctly
  as SCI0/EGA. Confirmed this is a ScummVM-side limitation, not our output
  being wrong, because the exact same compiled output runs correctly in both
  SCI Companion's own DOSBox integration and standalone DOSBox-X. Not worth
  chasing further — just use DOSBox-X.

## Findings / gotchas worth not re-discovering

- **Samba serving Windows executables**: Samba denies "open for execute"
  requests on files lacking the Unix `+x` bit, even though it happily serves
  the same file for read/copy. Any `.exe`/`.com`/`.dll` under the shared tree
  needs `chmod +x` on the Linux side or Windows gets a baffling "you do not
  have permission" error that looks like an ACL problem but isn't.
- **ufw + libvirt VMs**: `ufw`'s default deny-incoming policy silently blocks
  DHCP broadcasts on libvirt's virtual bridge (`virbr0`), causing the VM to
  fall back to an APIPA `169.254.x.x` address with no indication why. Fix:
  `sudo ufw allow in on virbr0` (safe — that interface only carries traffic
  from your own libvirt-managed VMs).
- **Modern Windows 10 refuses passwordless/guest SMB connections** by
  default — don't bother with a no-auth Samba share, just set a real
  password (`smbpasswd -a <user>`).
- **Scripts auto-load on call but never auto-unload**: calling a public
  procedure/class from a script that isn't currently resident causes the
  SCI0 interpreter to load it into the 64KB heap automatically — but nothing
  ever unloads it automatically. `(use "x")` only resolves symbols at
  *compile* time and has no runtime effect by itself. Any content split
  across multiple script files that all get called unpredictably within one
  room/session (like the WORK-zone event chunks) will accumulate permanently
  resident bytecode turn after turn until the heap is exhausted, unless
  something explicitly `DisposeScript(num)`s each one after use — the stock
  template's own idiom for this is `Load(rsType num)` to load /
  `DisposeScript(num)` to unload, either called directly or via the generic
  `DisposeLoad(rsType, ...nums)` helper in `DisposeLoad.sc` (`rsType` = NULL
  disposes instead of loads — read the procedure body, it's not obvious from
  the name alone). Bit us for real once already, in the WORK-zone content
  pipeline — see "Content pipeline built" below for the concrete fix. Will
  recur for every future zone (HOME/SOCIAL/SELF/BODY/PUBLIC) that gets
  split across multiple script files the same way.
- **A batch of new, cross-referencing scripts often needs 2-3 rounds of
  "Compile All" + "Rebuild Resources" before everything settles, not a
  single pass.** Confirmed benign: HOME zone landed 5 new interdependent
  scripts at once (`homeevents` + 4 chunks, all depending on `mechanisms`/
  `casefiles`/`printchoices`, `rm001` depending on the new zone dispatcher
  too), and the user needed several repeated Compile All/Rebuild Resources
  cycles before it all compiled clean — with no code changes in between.
  The compiler appears to resolve cross-script `(use ...)` references
  incrementally across passes rather than in one fully dependency-sorted
  pass. Don't mistake this for a new bug the way `casefiles.sc`'s actual
  registration/circular-dependency problems were — if nothing's changed
  between attempts and errors are shrinking/changing each time, just keep
  recompiling a few more times before troubleshooting further.
- **`.sco` files are pure compiled output — never hand-delete them expecting
  a clean rebuild to just work.** `.sco` = per-script compiled symbol table
  (used to resolve `(use "x")` cross-script references), auto-generated by
  the compiler, one per `.sc`. Deleting them looks like a safe "force a
  clean rebuild" move (they're build artifacts, not source) but **isn't** —
  confirmed the hard way this session: the user deleted ~36 of ~37 `.sco`
  files hoping Compile All would regenerate everything, and instead SCI
  Companion hard-failed compiling *even the untouched stock scripts*
  (Main, Controls, etc.), with neither "Rebuild Resources" nor "Rebuild
  Class Table" fixing it. Several stock scripts have mutual/circular `(use
  ...)` dependencies (Main↔Controls↔Obj, etc.) that the compiler apparently
  isn't designed to bootstrap from a completely empty `.sco` state, only to
  update *incrementally* against an existing one. **Recovery that actually
  worked**: re-extract the stock `.sco` files fresh from the original SCI
  Companion template zip (they ship pre-built) rather than trying to force
  the compiler to regenerate them from nothing. There's no CLI/scripted way
  to do this from the Linux side — it's a manual step in the VM. This repo's
  `sci/` tree was never in git, so there's no git-based recovery path either
  if it happens again — treat `.sco` files as sacred once a project is
  working, and if experimenting with a "clean rebuild," do it in a
  throwaway copy of the project, not the live one.
- **A new script file needs BOTH the `.sc` on disk AND a matching entry in
  `game.ini`'s `[Script]` section — confirmed, not just suspected.**
  Watched this fail concretely: a fresh copy of the project (the `sci/TRS/`
  recreation, see above) had our custom `.sc` files present on disk but
  `game.ini` was the untouched stock version with no entries for them —
  compile failed with `Unknown procedure 'DoWorkEvent'` / `Unable to open
  '...\workevents.sco'`. Adding the same `n100=PrintChoices` /
  `n101=WorkEvents` / etc. lines back to `[Script]` (matching the numbers
  in `game.sh`'s `(define ..._SCRIPT N)` constants) was the entire fix —
  no IDE "Add Existing Item" action, no dependency-order juggling, no
  project reload needed. If a brand-new script file won't compile, check
  `game.ini` before anything else.
- **`RESOURCE.MAP`/`resource.001` are append-only by design, not a bug**:
  every compile appends a fresh copy of each changed script's compiled
  bytecode rather than overwriting previous ones; the engine always uses
  the newest matching entry at runtime (confirmed in
  `docs/SCI0-research-findings.md`). This means a resource browser inside
  SCI Companion will show growing numbers of duplicate-looking entries per
  script number the more you compile (looked alarming — user saw 6 copies
  of `Main` — but is harmless for actual gameplay). "Rebuild Resources"
  should repack it down to one entry each if the clutter matters, but only
  once the underlying compile itself is actually succeeding — it did
  nothing while the real problem was the missing `game.ini` entries above.
- **`pkill -f <pattern>` self-match footgun**: if the pattern you're
  searching for also appears in the literal text of the shell command
  currently running `pkill` itself (e.g. because you're constructing the
  target command's argv as a string in the same script), `pkill -f` can kill
  its own invocation. Use specific PIDs instead of pattern matching when
  cleaning up processes you just launched.
- **The core UI risk, now resolved**: SCI0's stock `Controls.sc` has a public
  `Print(message #button "text" value ...)` procedure that's the natural
  mapping for our choice-menu mechanic, but it lays out buttons in a single
  **non-wrapping horizontal row**. Tested with our real, full-length WORK
  zone choice text (20-50 characters each) — it overlaps into an unreadable
  mess on a 320px-wide EGA screen (confirmed via screenshot). **Fix**: a
  custom `PrintChoices` procedure (added directly in `rm001.sc`, not yet
  extracted to its own file) that reuses the same `Dialog`/`DButton`/`DText`
  primitives but stacks buttons **vertically** instead, with choice text
  manually pre-wrapped using `\n` escapes at natural word breaks.
  `Dialog:setSize()` just measures whatever bounding boxes its children
  already have — there's no baked-in layout algorithm, so this is a
  legitimate, supported approach, not a hack. **Status: CONFIRMED WORKING** —
  compiled and tested in DOSBox-X, all 5 real WORK-zone buttons render
  correctly, readable, and functional. The core interaction mechanic for the
  whole port is now proven end to end.
- **`paramTotal` gotcha** (cost one broken compile/test cycle — an in-game
  "Oops!" runtime error trap, not a compile error): `paramTotal` inside a
  procedure/method counts *every* argument the caller passed, including ones
  bound to named parameters before a trailing "rest array" parameter — not
  just the count of extra/variadic args. If a procedure has N named
  parameters before the one you're treating as a rest-array (indexing it as
  `params[i]`), you must do `= paramTotal (- paramTotal N)` before using
  `paramTotal` as a loop bound, or you'll read N slots past the real end of
  the arguments into garbage stack memory. Confirmed real reference pattern
  in the template itself: `DisposeLoad.sc`'s `(procedure public (DisposeLoad
  rsType rsNumbers) (= paramTotal (- paramTotal 2)) ...)` — 2 named params
  before its rest-array, so it subtracts 2. `Print()` itself never needs this
  because it deliberately has exactly ONE named parameter (`params`)
  capturing the entire call. Our `PrintChoices` has three (`message
  titleText width`) before `params`, so it needs `(- paramTotal 3)`. Not
  documented anywhere in SCI Companion's bundled help (searched — zero hits
  for `paramTotal`) — the only way to get it right is finding a real working
  reference example like `DisposeLoad.sc` and matching its pattern.
- **String escapes — CORRECTION, previous entry here was wrong**: `{}` is
  *not* an alternate string-literal delimiter interchangeable with `""`.
  It's for **identifiers containing characters an identifier normally
  can't have** (spaces, punctuation) — e.g. the stock template's
  `(instance {Test Object} of Iitem ...)` and its inventory-array entry
  `{Test Object}` are an *object name*, not a string. Writing
  `Print({some text "with a quote" in it})` compiles as a reference to an
  undeclared identifier literally named `some text "with a quote" in it`,
  not a string — confirmed the hard way: real compiler output was
  `[Error]: Undeclared identifier 'COPING MECHANISM ACQUIRED: "The Approval
  Loop." This will not be undone.'` when `mechanisms.sc` tried exactly this
  to embed a literal `"` inside a message. **There is no way to embed a
  literal `"` inside a `""`-delimited string in this compiler** — the fix
  used in `mechanisms.sc` was simply to drop the inner quotes from the
  message text instead of trying to escape or delimiter-swap around them.
  The rest of the original entry stands: embedded newline escape is `\n`,
  tab is `\t`, and whitespace/line-breaks in source collapse to single
  spaces (so free-form multi-line source formatting of a string doesn't
  affect the compiled output unless you use `\n` explicitly).
- **`if`/`else` chaining — no confirmed 3+-branch precedent, avoid it**: the
  `(if(cond) then)(else other)` two-branch form is used all over the stock
  template (e.g. `NormalBase:doit`, `theWindow:open`). But a chained
  "else if else if else" — nesting another `(if...)(else...)` pair *inside*
  an `(else ...)` body — has **zero occurrences anywhere in the shipped
  template, Samples, or Objects trees** (checked via grep across all of
  `sci/`). Since compiling requires the Windows VM (no way for an agent to
  test-compile), don't gamble on it compiling correctly. What's confirmed
  instead: (1) `and`/`or` as infix connectors between parenthesized
  sub-expressions chain to arbitrary length no problem — real precedent
  includes a 4-term chain, `(if((<= x1 x) and (< x x2) and (<= y1 y) and (< y
  y2)) ...)` in `Feature.sc`, and `and` inside a `while` condition,
  `(while((< Abs(newY) temp5) and (< Abs(newX) temp5)) ...)`, also
  `Feature.sc` — so multi-condition loop/if guards are fine. (2) For
  multi-branch "pick one of N outcomes" logic, prefer either `switch`
  (used everywhere, e.g. `PrintChoices`' own `(switch(choice) (case 0 ...)
  ...)`) or a flat sequence of independent single-branch `(if(cond) stmt
  return)` blocks with early `return` (bare `return`, no value, is confirmed
  precedent — `Avoid.sc`, `Game.sc`, `syswindow.sc`, `inv.sc`, `obj.sc`,
  `dying.sc`) — both used in `rm001.sc`'s `runShift` and `Main.sc`'s
  `ClampStats` instead of a chained else-if.
- **Globals**: declared as a `(local gName = initialValue /* comment */ ...)`
  block in `Main.sc` (script 0) — any script that `(use "main")` can then
  read/write them. Added so far: `gRepression = 40`, `gMask = 60`, `gChild
  = 60` (matching the original's `startingStats`), plus `gTurn = 1` and
  `gMaxTurns = 10` (matching `content.js`'s `maxTurns`) for the turn loop.
- **Content pipeline built (Not-Yet-Started item 1 — done)**:
  [tools/gen-work-events.js](tools/gen-work-events.js) reads
  `js/events/work.js` directly (loaded via `new Function(...)`, not a JSON
  parse — the file is a bare `const CONTENT_EVENTS_WORK = [...]`, not valid
  JSON, and this needed zero changes to the original game's file) and
  generates SCI0 source for all 34 WORK events. Re-run it after editing
  `work.js`; it's idempotent (fully overwrites its output files). Verified
  before generating: all 402 choice-effects have exactly the three stat keys
  `mask`/`child`/`rep`, all values are non-negative integers, no string
  contains `"`/`{`/`}`/`\` (the generator throws loudly if that ever changes
  rather than emitting broken source) — so the generator didn't need defensive
  handling for cases the real data doesn't have.
  - **Output layout**: `PrintChoices` was extracted out of `rm001.sc` into its
    own file, [printchoices.sc](sci/TemplateGame/SCI0/src/printchoices.sc)
    (script 100, unchanged logic, just moved) per the old item 1 wording. The
    generator itself writes
    [workevents.sc](sci/TemplateGame/SCI0/src/workevents.sc) (script 101, a
    slim `DoWorkEvent(index)` dispatcher) plus
    `workevents1.sc`..`workevents4.sc` (scripts 102-105, ~9 events each, each
    holding standalone `WorkEvent<N>()` procedures). It's split across 4 files
    instead of 1 because a single-file version measured ~46KB of source —
    well past the ~16KB practical per-script ceiling from the research brief
    (see below) — split brought each chunk to ~11-13KB. New script number
    constants are in `game.sh`, entries added to `game.ini`'s `[Script]`
    section so SCI Companion's project browser sees them.
  - **Confirmed compiling and playing in DOSBox-X** (user-tested). The
    `game.ini` `[Script]` entries alone were enough — no manual "add existing
    item" step needed in the IDE.
  - `rm001.sc`'s hand-transcribed `doTypoEvent` proof-of-concept method is
    gone; room-init now calls a `runShift` method (see item 2 below) that
    loops through multiple random events instead of firing just one.
  - **Real bug found and fixed: heap space error after a few turns.** Root
    cause was the chunk split itself — `(use "workevents1")`..`(use
    "workevents4")` only resolves symbols at compile time; at runtime, the
    interpreter auto-loads a script the first time one of its public
    procedures is called, but **never auto-unloads it**. Since each turn
    picks a uniformly random event across all 34, a run touching all 4
    chunks (near-certain within a handful of turns) left ~46KB of event
    bytecode permanently resident on the 64KB heap, on top of everything
    else already loaded (Main, Controls, Cycle, Game, Feature, Obj, Inv,
    Door, Jump, DPath, PrintChoices, plus per-turn Dialog/DText/DButton
    allocations) — confirmed via the user hitting a real heap space error a
    few turns in. **Fix**: `DoWorkEvent(index)` (in
    [workevents.sc](sci/TemplateGame/SCI0/src/workevents.sc)) now
    `Load(rsSCRIPT ...)`s only the one chunk the requested index lives in,
    calls into it, then `DisposeScript(...)`s it immediately after — same
    `Load(rsType num)`/`DisposeScript(num)` idiom the stock template already
    uses in `DisposeLoad.sc` and `Main.sc`'s room-transition code. This is
    generated logic (in `genDispatcher()` inside
    [tools/gen-work-events.js](tools/gen-work-events.js)), so it survives
    regeneration automatically — don't hand-revert it back to a flat
    always-resident switch if re-touching this file.
  - Ran a structural sanity check (paired quotes, balanced parens outside
    string literals) across all generated + hand-edited files after every
    change — clean throughout.
  - **Real bug found and fixed: tall dialogs corrupted the screen.** User
    confirmed (with screenshot) garbled/overlapping text appearing near the
    top of the screen after a few turns, and separately diagnosed it
    themselves: "certain questions are too long and the window overlaps the
    titlebar." Root cause, confirmed by reading `Dialog:center()` in
    `Controls.sc`: it centers within `window:brTop`(0)..`window:brBottom`
    (190) — i.e. a ~190px-tall usable area, not the full 200px screen — via
    `nsTop = brTop + (usableHeight - dialogHeight)/2`. Any dialog taller than
    ~190px (events 0 "The Typo" and 7 "The Performance Review Buzzword" were
    the tallest, ~14-15 wrapped lines total between description + choices)
    produces a **negative** `nsTop`, and rendering above y=0 shows
    garbled/undefined screen content rather than clipping cleanly — exactly
    the reported symptom. **Fix, two parts**: (1)
    [printchoices.sc](sci/TemplateGame/SCI0/src/printchoices.sc) now clamps
    `nsTop` to a minimum of 2px right after `center()` (pins the dialog down
    instead of letting it go negative) — this is the load-bearing fix,
    guaranteed correct regardless of content height; (2)
    `tools/gen-work-events.js`'s `DESC_WIDTH` bumped from 260px to 290px
    (widens the auto-wrapped description, shaving a line off tall
    descriptions for free — safe since it's still comfortably under the
    320px screen and the kernel `TextSize()` call handles the actual
    wrapping, not hand-rolled math). `BUTTON_WRAP_LEN` deliberately left at
    36 chars/line, unchanged — that value is proven safe from the original
    "Typo" test, and widening it without real font-metrics data would trade
    a known, now-fixed vertical bug for an unverified horizontal-overflow
    risk.
  - **User initially confirmed fixed** ("everything stays in bounds, now")
    but this turned out premature — see the follow-up entry below the
    non-ASCII fix; event 7 recurred on a later playtest.
  - **Second real bug found and fixed, same playtest round: non-ASCII
    characters corrupt dialog text.** User reported a button rendering with
    a word replaced by a blank line, guessed (reasonably, but not quite
    right) that an apostrophe was being read as a line break. Actual cause:
    two WORK choices contain a Unicode em dash (U+2014, "Stop—back") in
    `work.js` — real straight apostrophes/quotes elsewhere render fine, only
    this em dash broke. The compiled font only has glyphs for standard
    printable ASCII; a non-ASCII byte isn't a missing-glyph placeholder, the
    text renderer reads it as a raw control byte, which is what ate the word
    and produced the blank line. **Fix**: `sciString()` in
    `tools/gen-work-events.js` now transliterates a small set of typographic
    Unicode characters (em/en dash → `-`, curly quotes → straight quotes,
    ellipsis → `...`) before emitting, and now throws on *any* remaining
    non-ASCII character (was previously only checking for `"`/`{`/`}`/`\`) —
    so any future addition to `work.js` with an unmapped Unicode character
    fails the generator loudly instead of silently corrupting a dialog at
    runtime. Confirmed via full re-scan: zero non-ASCII characters remain in
    any generated string literal (only harmless em dashes/arrows in my own
    file-header *comments*, which compile fine regardless — proven across
    multiple successful builds already). **Confirmed fixed by the user.**
  - User then confirmed both fixes working ("everything seems to be
    functioning as intended") after a full multi-turn run — but on the
    *next* playtest session, reported event 7 ("The Performance Review
    Buzzword") still going out of bounds (screenshot: readable fragments of
    its real description text — "Your manager says 'let's discuss your
    growth areas' in..." — floating near the top of the screen), guessing
    apostrophes-as-quotes were the cause. Checked: the apostrophes in that
    string are plain ASCII (U+27), not curly quotes — ruled out, not the
    same mechanism as the em-dash bug above.
  - **Third real bug / continuation of the tall-dialog issue: the nsTop
    clamp + wider description alone weren't enough for event 7.** It's one
    of the two events (with event 0) already flagged as tallest by line
    count, and apparently still exceeds the safe ~190px budget even after
    the earlier fixes reduced it somewhat. Rather than continuing to guess
    at wrap widths without real font-metrics data, switched levers to the
    one already flagged as the fallback: **button text now renders in
    `SMALL_FONT`** (defined in `game.sh` as font resource 4, part of the
    stock template's default font set, previously unused) **instead of
    `gDefaultFont`** — `printchoices.sc`'s `DButton:font(...)` call changed,
    plus `Load(rsFONT SMALL_FONT)` added to `Main.sc`'s `init` and `newRoom`
    (both places the other fonts get loaded) so it's resident before
    `PrintChoices` ever needs it, same pattern as `gDefaultFont`/`gDeadFont`/
    `gSaveRestoreFont`. A smaller font reduces per-line height directly
    (unlike the wrap-width lever, which only reduces line *count* and risks
    horizontal overflow) — description text (`DText`) deliberately left at
    `gDefaultFont` for readability of the main sentence; only button text
    was shrunk, since buttons contributed the bulk of the height (11-12 of
    ~14-15 total wrapped lines) in both worst-case events.
  - **Not yet verified — needs another compile/playtest pass.** No way to
    test-render or measure actual `SMALL_FONT` glyph dimensions from here.
    If event 7 (and event 0, never specifically reported but structurally
    identical risk) still overflow after this, the remaining levers in
    order of preference: shrink the description font too (currently
    untouched), reduce the inter-button/message-to-button gaps in
    `PrintChoices` (currently 6px/3px), or — the most robust but bigger
    change — split truly oversized events onto a `DSelector` scrollable
    list instead of stacked `DButton`s (see the research brief for the
    stock precedent).
- **Research brief results are in** (was sent to another LLM/Gemini; findings
  now saved at `docs/SCI0-research-findings.md` in this repo, copied there
  from `~/Downloads`). Conclusion: **our
  `PrintChoices` approach is independently validated, not just a workaround**.
  Real Sierra precedent exists: Leisure Suit Larry III's age-verification quiz
  (room 290) used the identical pattern — a custom procedure building a
  `Dialog` with vertically-stacked `DButton` controls, positioned via
  `(- (control nsBottom?) (control nsTop?))` height deltas, `value:` set to
  the choice index, returned via `(dlg doit: firstButton)`. Stock `Print` was
  genuinely never used historically for verbose multi-choice menus — it was
  reserved for short button sets ("Yes"/"No"/"Restore"/"Quit"). No changes
  needed to what we've already built.
  - Corollary/myth-check: Quest for Glory I's often-cited "graphical topic
    list" didn't exist in the 1989 SCI0 EGA original (parser-only, `ask about
    <topic>`) — the mouse-driven topic tree is SCI1.1-only, from the 1992 VGA
    remake. Doesn't change any of our decisions, just confirms we're not
    missing a native SCI0 idiom by using dialogs instead of the parser.
  - **Directly actionable for Not-Yet-Started item 4** (persistence): the
    brief documents QFG1's actual room-840 character-export code as a
    concrete template — `FOpen` w/ `fCREATE`, fields serialized via `Format`
    and written newline-terminated via `FPuts`, plus a tamper-check checksum
    integer computed over the preceding fields, re-parsed on the other end
    via `FGets` + `fOPENFAIL`. Use this shape for the Case-Files-style
    cross-run unlock file (via the template's existing `fileio.sc`).
  - **Worth keeping in mind for item 1** (content pipeline) and general
    structure: hard 64KB heap segment ceiling, 256-global cap, ~16KB
    practical per-script size before hitting the segment ceiling. Argues for
    splitting the 34 WORK events across multiple `.sc` files rather than one
    monolithic script, and disposing dialog/string allocations between
    events rather than accumulating them.
  - Also confirmed, lower priority: `DSelector` (the stock scrollable list
    control used by Save/Restore) exists as an alternative to stacked
    `DButton`s if we ever need a long scrolling list rather than a handful of
    full-sentence choices — not needed for WORK-zone content so far, all
    events there use ≤5 choices.
- **Project rebased into the game's actual repo, 3-choice cap confirmed
  working, status line repurposed for live stats.** Three things landed in
  one go:
  1. **Repo consolidation** — see the rewritten Environment section above.
     Game logic and the SCI0 port now live in the same git repo
     (`trauma-response-sim`); the SCI Companion IDE bundle was deliberately
     moved OUT of any repo (`~/WebstormProjects/sci_companion/`, a sibling
     of this repo) since it's third-party tooling, not project content.
  2. **The `MAX_CHOICES = 3` cap (see above) is confirmed working** — user
     played "half a run" with no heap error. Still being cautious about it
     recurring (chunk sizes are smaller now but not dramatically so), so
     don't casually raise the cap back up without re-testing headroom.
  3. **Status line repurposed to show live stats instead of score**
     (`TRS_SCI/src/Main.sc`): `statusCode`'s `Format` string changed from
     `" Score: %d of %-3d ... T.R.S. "` to
     `" Repression:%d  Mask:%d  Child:%d"` (35 chars worst-case, comfortably
     under `SL:doit()`'s `strBuf[41]`). `ClampStats()` now ends with
     `(SL:doit())` — since every stat mutation in the game already routes
     through `ApplyChoiceEffects`/`ApplyGlitch` in `mechanisms.sc`, both of
     which call `ClampStats()` last, this one call makes the status line
     track all three stats live after *every* choice/glitch, no per-call-site
     wiring needed. The old per-turn `FormatPrint("Repression: %d Mask: %d
     Child: %d" ...)` popup dialog is now **removed** from the generator
     template (`tools/gen-work-events.js`) — it was made redundant by the
     status line and its removal further shrinks generated code (~700 bytes
     per chunk). Regenerated all 4 chunks. Ran the structural sanity check —
     clean. **Not yet compiled/playtested** — this needs a pass in the VM
     like everything else in this doc that says so.
     - Untested edge case: `SL:doit()` fires from deep inside
       `ApplyChoiceEffects`/`ApplyGlitch`, which are themselves called from
       inside `PrintChoices`'/`Print`'s modal `Dialog:doit()` loop. This
       should be safe — `SL:doit()` just calls the kernel `DrawStatus`, a
       simple idempotent screen-text draw, not something with side effects
       or re-entrancy concerns — but hasn't been visually confirmed to
       actually redraw correctly while a dialog is on-screen versus only
       updating once the dialog closes.
- **Confirmed: "half a run" playtest, no heap error, with the `MAX_CHOICES=3`
  cap live.** Then, at the user's suggestion, moved the end-of-run handling
  into its own room — both a heap-hygiene move (a real room transition gets
  the engine's normal room-to-room cleanup, unlike falling through inside
  the same room's own turn loop) and a fix for the "falls through to
  ordinary ego-walking after the run ends" rough edge flagged since item 2.
  - **New file**: `TRS_SCI/src/rm002.sc` — a second room (`ENDING_ROOM = 2`,
    defined in `game.sh`), minimal Rm boilerplate (positions ego, no
    north/east/south/west neighbors — dead-end room, no restart flow yet),
    reusing rm001's own background (`picture 1` — there's no pic resource
    #2, and no art exists yet regardless, see item 5 below). Its `init()`
    calls `printEnding()`, which is the **exact same** failure-threshold +
    `printSurvivalEnding()` logic that used to live in `rm001.sc`'s
    `runShift`, moved verbatim (same condition table/order, same one-
    variant-per-pool scope cut noted earlier — nothing about the actual
    ending logic changed, only where it runs).
  - **`rm001.sc`'s `runShift`** now ends its turn loop with
    `(send gRoom:newRoom(ENDING_ROOM))` instead of handling the ending
    inline — a real, engine-native room transition. Confirmed this is the
    same mechanism the stock template already uses for
    TitleScreen→room1 (`(send gRoom:newRoom(INITROOMS_SCRIPT))` in
    `TitleScreen.sc`), not something new/unproven.
  - Final stat values are untouched by the transition (they're globals, not
    room-local state) — rm002 just re-evaluates the same thresholds/
    conditions against whatever `gRepression`/`gMask`/`gChild` already are.
  - `game.ini` updated: `n002=rm002` added to `[Script]`. No new `[Pic]`
    entry needed since `picture 1` reuses the existing resource.
  - Ran the structural sanity check on both files — clean.
    **Not yet compiled/playtested.**
  - **User's stretch idea, explicitly not done, worth remembering**: "if we
    were peachy keen we could make each question its own room, outright" —
    i.e. one room per WORK event instead of per-chunk-script dispatch. Way
    bigger change (34 rooms instead of 4 chunk scripts + 1 dispatcher),
    not attempted this round — flagging in case heap pressure returns even
    after this fix and that's the next lever to reach for.
- **Started on item 5 (art) — a first-pass WORK-zone room background.**
  User confirmed the game is fully working end to end (compiles, plays,
  ends correctly via the new room), then asked to start on background art.
  Researched the actual pipeline first: SCI Companion has a real, built-in
  **Pic → Import Bitmap to Pic** feature (SCI0-specific — confirmed in the
  bundled Help docs, `pics.html`, "Converting a bitmap to a vector drawing
  (SCI0)") that auto-converts an ordinary image into SCI0's vector fill/line
  pic commands, rather than requiring the vector tools to be used by hand
  from scratch. Constraint to keep in mind for any future art: the
  converted result must stay under 64000 bytes of vector commands, so
  flatter/simpler source images convert far more cleanly than detailed ones.
  - Generated `TRS_SCI/art/work_room_concept.png` (320×200, standard 16-
    color IBM EGA palette, flat-shaded, no gradients/anti-aliasing — by
    design, to match what the importer handles well): office cubicle/
    terminal-desk scene — gray walls with a chair-rail seam, a cyan-paned
    window, a brown filing cabinet, a brown desk with a CRT terminal
    (glowing green screen lines) and keyboard, an office chair, and a
    brown floor with perspective seam lines kept clear of the desk
    footprint (an earlier draft had them cutting through the desk and
    reading as noise — fixed before sending).
  - Generator: `tools/gen-work-room-concept.py` (pure PIL/Pillow, not
    integrated with the SCI0 script generator — this one produces a
    bitmap for manual import, not compilable source). Re-run it after
    editing to regenerate `TRS_SCI/art/work_room_concept.png` in place.
  - **This is a first-pass concept, not final art** — user said their "art
    skills are trash but I'm good at editing," so the expectation is
    they'll refine this (either by editing the PNG directly, or by
    touching up the vector result after SCI Companion's import/convert
    step) rather than it being used as-is.
  - **Not yet imported into SCI Companion or seen converted** — the
    import/convert step is GUI-only in the Windows VM, same as compiling;
    nothing here has been verified to actually survive that conversion
    (palette mapping, fill-tool leaks around white-inclusive dithered
    colors, and the 64000-byte ceiling are all real risks flagged in the
    Help docs that haven't been checked against this specific image).
  - Once a background pic exists for room 1 (rm001), reconsider whether
    `rm002` should keep reusing `picture 1` (current placeholder) or get
    its own distinct look — currently intentionally identical since
    neither has real art yet.
- **User went their own route on art and has both a room pic and a title
  image they're happy with** (didn't end up using the generated concept
  PNG/import pipeline above — their call, noted for the record in case a
  future session wonders why that pipeline was never exercised).
- **Fixed: ego visibly walking around during the ending room.** Once real
  art made room 2 an actual visible scene (rather than blank), it became
  obvious that `rm002`'s `init()` left ego under full player control —
  `SetUpEgo()` calls `PlayerControl()` internally, and rm002 was calling it
  the same way rm001 does, with nothing in the room to actually walk to or
  interact with. **Fix**: added a `ProgramControl()` call right after
  `SetUpEgo()`/`gEgo:init()` in `rm002.sc`, before `printEnding()` runs —
  revokes movement input and clears any in-progress motion
  (`canControl(FALSE)`, `canInput(FALSE)`, `setMotion(NULL)`, all inside
  the stock `ProgramControl()` procedure already used elsewhere in the
  template), leaving ego standing still rather than wandering over the
  ending text. **Follow-up, same room**: user sent a screenshot — frozen
  was an improvement but still read as broken with real art in place
  (ego just standing there, visibly not part of the scene). Added
  `(send gEgo:hide())` right after `ProgramControl()`, same call
  `TitleScreen.sc` already uses to keep ego off the title screen — ego is
  now fully invisible in the ending room, not just immobile.
  **Then**: user pointed out ego shows up in `rm001` too, for the same
  reason — it's a pure dialog-driven stat loop, no walking-around gameplay
  exists there either, so the same `ProgramControl()` +
  `(send gEgo:hide())` pair was added to `rm001.sc`'s `init()` (right after
  `SetUpEgo()`/`gEgo:init()`, before `runShift()` runs). Ego is now hidden
  and non-interactive in both rooms. Ran the sanity check on both files —
  clean. **Not yet compiled/playtested.**
- **Removed stock placeholder text overlaying the title screen.** User has
  their own title art now; `TitleScreen.sc`'s `init()` had a leftover
  `Display("Intro/Opening Screen" dsCOORD 90 80 ...)` call from the
  original Brian Provinciano template, drawn directly on top of whatever
  picture the room shows — harmless before real art existed, an obvious
  eyesore once it did. Deleted the whole `Display(...)` call. Sanity check
  clean. **Not yet compiled/playtested.** Left similar unfinished stock
  placeholders alone since they weren't asked about — e.g. the "How To
  Play" menu item still shows `<Put your how to play stuff here>`
  (`menubar.sc`).
- **Real bug, several rounds to actually fix: adding `casefiles.sc` hit TWO
  separate, unrelated problems stacked on top of each other.** Worth
  reading in full since a future zone's script will likely hit at least the
  second one again.
  - **Problem 1 (a red herring that ate two rounds): global arrays don't
    export from `Main.sc` via `(use "main")`.** First compile after adding
    Case Files failed hard: `Main.sc`'s `gCaseFiles[CASEFILE_COUNT]`
    produced `Unknown variable 'gCaseFiles'` / `type 'int' cannot be
    assigned to type 'Unknown-type'`. Blamed the `#define` used as the
    array size at first (reasoning from `User.sc`'s `inputStr[51]` as "the
    only working precedent uses a literal"), changed it to a literal
    `gCaseFiles[17]`, reported it fixed. **It wasn't** — `inputStr[51]` was
    never actually comparable, it's a **script-local** array inside
    `User.sc` itself, not a `Main.sc` global; there's no real precedent
    anywhere in this codebase for a *global array* being read from another
    script. Rewrote to 17 separate scalars, `gCF0`..`gCF16` (the exact
    pattern already proven solid for mechanism tracking), with
    `GetCaseFile(index)`/`SetCaseFile(index value)` switch-based accessors
    in `casefiles.sc` giving array-like semantics over them —
    `LoadCaseFiles`/`SaveCaseFiles`/`MarkCaseFile` kept the same signatures,
    so `mechanisms.sc`/`rm002.sc` needed zero edits. **This half was a red
    herring** — the errors persisted afterward too, which is what proved it
    wasn't actually the (or at least not the only) problem.
  - **Problem 2 (the actual blocker on this file specifically)**:
    `casefiles.sc` was never visible in SCI Companion's own Scripts
    panel/project index at all, despite the `.sc` file existing on disk and
    a correct `n107=CaseFiles` entry in `game.ini` — the user caught this
    directly ("It does not show up in our scripts list... despite being in
    the folder"), after which repeated file-timestamp/staleness theories
    from this side turned out to be a distraction. **Root cause**: unlike
    every earlier new script this session, this file was never created
    *through* SCI Companion's own **New empty script** button (confirmed
    via the bundled Help docs, `scripts.html`, "Adding a new room and other
    scripts" — that's the actual, only documented way to add a script; no
    "Add Existing Item" feature was ever real, that was this session's bad
    guess). Attempting to create script 107 via that dialog then failed
    with "already exists" (because `game.ini` already claimed the number)
    while still not resolving the "missing from list" problem, because the
    number being claimed in `game.ini` isn't the same thing as the script
    being registered in whatever internal index the Scripts panel reads.
  - **Once registered, a second, genuinely separate issue surfaced**:
    `Main.sc` has `(use "casefiles")` (needed for its `LoadCaseFiles()`
    call) while `casefiles.sc` has `(use "main")` (needed for `gCF0..16`) —
    a real circular dependency between the two, unlike this codebase's
    *other* stable circular pairs (`main`↔`controls`, `main`↔`obj`, etc.),
    which have compiled successfully since long before this project ever
    touched them. This new pair had never successfully bootstrapped even
    once, so neither side could resolve the other's brand-new symbols.
    **Fix that actually worked**: temporarily comment out both
    `(use "casefiles")` and the `LoadCaseFiles()` call in `Main.sc`,
    compile `Main.sc` alone (F8) to get a valid standalone `main.sco` with
    `gCF0..16` finally registered, then compile `casefiles.sc` alone (F8,
    now resolves fine against that valid `main.sco`), then restore both
    lines in `Main.sc` and run Compile All. **Confirmed working end to
    end** by the user after this sequence.
  - **Lessons for later, both worth remembering for the next new zone's
    script**: (1) any brand-new script file needs to be created via SCI
    Companion's own **New empty script** button, not just written to disk
    with a matching `game.ini` entry — that combination has worked for
    every prior file this session except this one, so it's not a reliable
    rule, just a common case; when a new file doesn't show up in the
    Scripts panel despite existing on disk, this is the real gap, not a
    stale-cache theory. (2) If a new script and `Main.sc` need each other
    (a new circular pair, not one of the ones already proven stable), it
    may need a manual bootstrap: temporarily break the cycle, compile each
    side alone once, then restore it.

## Not yet started (the actual remaining work)

**Confirmed by the user**: the generated content pipeline output compiles and
plays in DOSBox-X — a full multi-turn run through real WORK-zone content,
end to end, with the heap-exhaustion, dialog-corruption, and non-ASCII bugs
below all fixed and playtested clean. Item 1 is fully done including the
VM/compile step. Item 2 (below) is functionally working; the "still missing"
sub-items under it are the real remaining scope, not bugs.

1. ~~A real content pipeline; extract `PrintChoices` into its own file~~
   **Done**, compiled and confirmed playable. See "Content pipeline built"
   above.
2. ~~Looping multiple events~~ **Partially done.** `rm001.sc`'s `init` now
   calls a new `(method (runShift))` (globals `gTurn`/`gMaxTurns` added to
   `Main.sc`, default `gMaxTurns = 10`, matching the original's `content.js`
   `maxTurns: 10`) that loops `DoWorkEvent(Random(0 33))` each turn,
   `ClampStats()`s all three stats to [0,100] after each (matching the
   original's per-turn clamp in `engine.js`), and stops on `gRepression>=100`
   / `gMask<=0` / `gChild<=0` (loss) or `gTurn>gMaxTurns` (survival), printing
   one placeholder end-of-run line via `Print()`. This was built in direct
   response to the user's report that after answering one event, they got
   "spit out to a man walking around" — that was room-init falling through
   to normal ego/room control after the single hardcoded `DoWorkEvent(0)`
   call, exactly the known limitation this item describes.
   **Still missing** (not built): zone-weighting event selection (moot for
   now — only WORK-zone content exists), avoiding immediate repeats of the
   same event within a run, and any real end-of-run screen — the "GAME
   OVER"/"You survived the shift." lines are one-line `Print()` placeholders,
   not the pooled failure-ending/survival-ending system from item 3 below.
   Also: after `runShift` returns (loss or survival), execution still falls
   through to normal ego/room walking-around control same as before — there's
   no proper "run over, restart?" flow yet, just a less immediate version of
   the original complaint.
3. ~~The coping-mechanism unlock system, the glitch wildcard, and the ending
   system~~ **Done, not yet compiled/playtested.** Built this session,
   right after items 1/2 were confirmed working:
   - **Coping mechanisms** ([mechanisms.sc](sci/TemplateGame/SCI0/src/mechanisms.sc),
     new script 106): matches `js/content-mechanisms.js` exactly — 5 fixed
     mods (fawn/flight/fight/freeze/secure), each with its own `mod.rep/
     mask/child`. Tracked via 10 new `Main.sc` globals (`gFawnCount`/
     `gFawnUnlocked`, etc. — one count+unlocked pair per tag). `(use
     "mechanisms")` added to all 4 `workevents*.sc` chunks. Single entry
     point `ApplyChoiceEffects(repDelta maskDelta childDelta tag)`: adds the
     mechanism's mod on top of the choice's own effect *if and only if*
     that tag is already unlocked, applies the (possibly-modified) deltas,
     clamps, and — if not yet unlocked — increments that tag's count and
     flips `unlocked` (permanently, once) plus prints an acquisition
     message the first time `UNLOCK_THRESHOLD` (3) is hit. Every generated
     `WorkEvent<N>` choice case now calls this instead of raw
     `= gMask (+ gMask N)`-style assignments — this **replaced**, not
     added to, the old inline effect lines (net code-size neutral to
     smaller per choice, despite the extra logic elsewhere).
   - **Glitch wildcard**: matches `js/engine.js`'s `handleGlitchChoice` —
     15% chance per turn (`GLITCH_CHANCE_PCT`, rolled via
     `Random(0 99) < 15` at the top of each generated `WorkEvent<N>`, before
     building the dialog), fully random effects each in [-25,25]
     (`ApplyGlitch(logMsg)` in `mechanisms.sc`), untagged (never touches
     mechanism tracking, matching original's `tag: null` for glitch).
     `PrintChoices` gained a new 4th parameter, `glitchText` (NULL = no
     glitch this turn, a string = show it as an extra button) — inserted
     before the existing variadic choice-pairs, so `= paramTotal (-
     paramTotal 4)` (was 3). Returns `GLITCH_CHOICE` (99, a dedicated
     sentinel — chosen to avoid colliding with real choice indices 0-4 *or*
     `PrintChoices`' own pre-existing "-1 cancel → 0" behavior) when that
     button is picked. Every WORK event already had its own `glitch.text`/
     `glitch.log` in `work.js` (100% coverage, confirmed during the
     original extraction), so the generic pack-wide `glitchLogs` fallback
     pool from the original JS was skippable — one less thing to port.
   - **Ending system** ([rm001.sc](sci/TemplateGame/SCI0/src/rm001.sc)'s
     new `printSurvivalEnding` method, plus real title/desc text replacing
     the old one-line `runShift` failure placeholders): same condition
     table and order as `js/content-endings.js`'s `CONTENT_ENDINGS` (8
     conditions, first-match-wins, plus an unconditional fallback) and
     `CONTENT_FAILURE_ENDINGS` (one pool per stat). **Deliberate scope cut,
     not yet run past the user**: ported only **one flavor variant per
     pool/condition** instead of the original's 8-10 — same mechanics
     (identical thresholds, identical condition logic, identical ordering),
     far less text. Chosen to stay well clear of the per-script memory
     ceiling after already hitting real heap/rendering limits twice this
     session; picking the game's actual **content variety** back up
     (either restoring full ending-variant pools, or writing brand-new
     flavor text) is a reasonable thing to revisit later if it matters more
     than the memory headroom it costs.
   - Uses stock `Print(desc #title title)` for both failure and survival
     endings — no new dialog machinery needed, `#title` is a real supported
     `Print()` option (confirmed via real usage elsewhere in the template:
     `menubar.sc`, `dying.sc`).
   - All condition checks use the proven flat sequential
     `(if(cond) ... return)` pattern (not chained else-if) and the confirmed
     `and`-chain-in-a-single-if pattern for multi-condition tests — same
     two constructs already validated earlier this session, see the
     if/else gotcha entry above.
   - Ran the full structural sanity check (paired quotes/braces, balanced
     parens outside string literals) across every touched and generated
     file after these changes — clean.
   - **Compiled and confirmed working** (after two real, unrelated build
     problems — see the two new entries below this one). Then hit a
     **second heap-space exhaustion** during actual play once mechanisms +
     glitch were live.
   - **Deliberate scope cut in response, at the user's request**: every
     event is now capped to its first `MAX_CHOICES` = 3 authored responses
     (`tools/gen-work-events.js`) instead of the original 3-5 — the glitch
     stays a bonus 4th slot on top, not counted in that cap. Shrinks
     generated code, dialog height, and per-turn heap pressure simultaneously.
     Chunk sizes dropped modestly (~11.1-12.5KB, down from ~11.2-14.5KB) —
     less dramatic than hoped since most events only had 4 choices to begin
     with (one dropped), not 5. **Explicitly accepted balance regression**:
     several choices per event are now unreachable — the original design's
     tag distribution across choices was not curated with a 3-cap in mind,
     so e.g. an event that had exactly one `secure`-tagged choice at index 3
     or 4 now never offers it at all. Revisit once the engine itself is
     confirmed stable — either restore up to 5 with a smarter fix (see
     below), rebalance which 3 are kept per event, or keep 3 permanently if
     it turns out to feel fine. **Not yet compiled with this change — needs
     another pass.**
   - Given this didn't shrink chunks as much as hoped, if heap exhaustion
     recurs even at 3 choices, the next levers in order of preference: (1)
     more chunks (currently 4, could go to 6-8, shrinking each further —
     cheap, mechanical, no content loss); (2) confirm the per-chunk
     `Load`/`DisposeScript` cycle (see the dispatcher entry above) is
     actually taking effect in this rebuilt `TRS` project — worth
     double-checking now that the project's been recreated from scratch,
     since that's the one thing standing between "one chunk resident at a
     time" and "all four accumulate again."
4. ~~Native SCI0 save/restore + Case-Files-style cross-run persistence~~
   **Done**, both halves:
   - **Native save/restore for in-progress runs turned out to need zero new
     code.** Went looking for `Save`/`Restore` instances to wire up and
     initially couldn't find them (a bad grep — searched for
     `(instance.*Save\b` which doesn't match `(class Save of SRDialog
     ...)`). They're fully present: `syswindow.sc` already defines `Save`
     and `Restore` as **classes** (not instances) built on the stock
     `SRDialog` — sending `doit`/etc. straight to a class this way, using
     the class's own baked-in property defaults as a de-facto singleton, is
     a legitimate, working SCI idiom, not a shortcut. `menubar.sc`'s
     `MENU_SAVE`/`MENU_RESTORE` already call `(send gGame:save())`/
     `(send gGame:restore())`, which already route through this. Since
     `SaveGame`/`RestoreGame` are full VM-heap snapshots (per the research
     brief), every custom global we've added this whole project — stats,
     turn counter, mechanism counts/unlocks, now `gCaseFiles` — gets
     captured/restored automatically, no per-feature save code needed, ever.
     **Not yet actually tested** — this needs the user to Save mid-run,
     Restore, and confirm the stats/turn/mechanisms come back correctly,
     but there's no reason to expect it doesn't already work.
   - **Case Files cross-run persistence** — new file
     [casefiles.sc](TRS_SCI/src/casefiles.sc) (script 107). Matches the
     original browser game's actual "Case Files" feature (README.md: "a
     persistent, cross-run record of every ending and coping mechanism
     you've ever actually seen"), scoped down deliberately to fit what this
     port already has: **12 ending slots** (9 survival + 3 failure, matching
     our one-variant-per-pool ending set — not the original's 102 across
     8-10 variants each) **+ 5 mechanism slots** = `gCaseFiles[17]` (new
     global array in `Main.sc` — global arrays confirmed supported via
     `name[N]` syntax in the `(local ...)` block, real stock precedent:
     `User.sc`'s `inputStr[51]`). Persisted to a plain-text file,
     `TRSCASE.DAT`, one `0`/`1` per line, via `FOpen`/`FGets`/`FPuts`/
     `FClose` — same QFG-style pattern as the research brief, bare filename
     (no path prefix needed; matches how the stock `SRDialog` code itself
     opens files). `LoadCaseFiles()` runs once from `Template:init()` in
     `Main.sc` (before the title screen), `SaveCaseFiles()` fires
     immediately inside `MarkCaseFile(index)` the moment something is *newly*
     discovered — `MarkCaseFile` returns `TRUE` exactly then, so callers
     (the 12 ending branches in `rm002.sc`, the 5 mechanism-unlock branches
     in `mechanisms.sc`) can decide whether to print a "Case Files: X,
     filed." announcement, matching the existing mechanism-unlock-message
     precedent rather than introducing new dialog machinery.
   - Real, discovered gotcha along the way: `sci.sh` **deliberately swaps**
     `fOPENFAIL`/`fOPENCREATE`'s numeric values for `SCI_0` vs `SCI_1_1`
     builds (`#ifdef SCI_0` block, comment: "have the wrong numbers... keep
     that incorrect behavior here so as not to break old games"). ~~Doesn't
     affect us since we use the symbolic names (`fOPENFAIL`, `fCREATE`),
     which the compiler resolves correctly for our SCI0 target either way~~
     — **CORRECTION, this conclusion was wrong and cost an entire debugging
     session (New Game+ item below) to discover**: using the symbolic name
     does NOT give you the behavior matching its English name. The swap
     means `fOPENFAIL` (value 0 under SCI_0) actually triggers the kernel's
     "open or create" behavior, and `fOPENCREATE` (value 1) actually
     triggers "open or fail, abort if not possible" — confirmed via SCI
     Companion's own bundled kernel docs
     (`~/WebstormProjects/sci_companion/Help/Kernels/FOpen.html`, which
     documents the two constants' *behavior* already reversed from their
     *names* for exactly this reason) and via `sci.sh` itself. Practical
     effect: any code using `fOPENFAIL` to safely open an *existing* file
     for reading (the obviously-correct-looking choice, and what
     `casefiles.sc`'s `LoadCaseFiles()` used from the moment it was
     written) is actually invoking destructive create/reset-style
     behavior, silently wiping the file's content immediately before ever
     trying to read it. **`fOPENCREATE` is the one to use for that case,
     despite how backwards it reads.** If literal `0`/`1`/`2` values ever
     show up hardcoded for file modes anywhere, they're wrong for SCI0 and
     need to be the symbols instead — but "use the symbol" is necessary,
     not sufficient; make sure it's the *correct* one per this correction,
     not just the one whose name matches what you want in English.
   - **Explicitly not built, by design**: any in-game Case Files *viewer* —
     the original has a whole gallery screen (flip-to-reveal file tabs,
     accessible from three different places). This port only has the
     persistence + discovery-tracking layer and a one-line announcement on
     first discovery; there's no way to browse what's been found so far.
     Worth doing eventually, not attempted this round.
   - Ran the structural sanity check on all touched/new files
     (`Main.sc`, `mechanisms.sc`, `rm002.sc`, `casefiles.sc`) — clean.
     **Confirmed compiling and working** after the real fixes described
     just below (script registration + circular-dependency bootstrap) —
     this was a much bumpier road to a working build than the sanity
     checks alone suggested, see those entries for the full story.
5. Actual art: an EGA background pic for the "terminal" room, a player
   portrait view, stat-gauge views (the template's `gauge.sc` has a working
   percentage-meter class already, unused so far). **Partially done** — user
   has created their own room background and title screen art (didn't end
   up using the generated concept image/import pipeline from earlier in
   this doc), and `game.ini` now has a `n000=Cave` pic entry from that work.
   Stat-gauge views and a player portrait are still unbuilt.
6. ~~Port the remaining five zones (HOME/SOCIAL/SELF/BODY/PUBLIC)~~ **HOME
   done, four to go.** After Case Files was confirmed working, user asked
   to move on to porting the rest of the browser game's content, starting
   with HOME.
   - **`tools/` had gone missing entirely** (confirmed never tracked in
     git, per this session's very first `gitStatus` snapshot — it must
     have been swept up in the earlier file-reorg/backup work). Recreated
     it from this conversation's own history rather than asking the user
     to redo anything.
   - **Generalized while recreating it**, since 4 more zones are coming:
     the old WORK-only `tools/gen-work-events.js` is now a ~230-line shared
     library, [tools/lib/zone-events.js](tools/lib/zone-events.js), plus a
     thin per-zone entry script (`tools/gen-work-events.js`,
     `tools/gen-home-events.js`) that just calls `generateZoneEvents(opts)`
     with zone-specific names/paths. Verified the refactor changed
     *nothing* functionally: regenerated WORK zone with it and diffed
     against the currently-compiled-and-working output — only comment
     wording differed, byte-for-byte identical logic.
   - **Found and fixed a real, previously-latent bug while generating
     HOME**: `sciString()` rejected any string with a literal embedded
     newline/tab as "non-ASCII" (a raw `\n`/`\t` control character is
     outside the printable-ASCII range the check allowed) instead of
     converting it to SCI's own two-character `\n`/`\t` escape sequence.
     WORK zone has zero such strings (confirmed by scanning), which is
     why this never surfaced there — HOME has exactly one (a multi-
     paragraph event description using the original's `\n\n\n`). Fixed by
     converting real newlines/tabs to escape sequences *between* the
     illegal-character check and the printable-ASCII check (ordering
     matters: converting first would introduce a literal backslash that'd
     then trip the illegal-character check). Re-verified WORK's output is
     still byte-identical after this fix (it has none of these strings to
     affect).
   - **HOME zone generated**: 32 events (`js/events/home.js`), same
     `MAX_CHOICES=3` cap and 4-chunk split as WORK, new script numbers
     108-112 (`game.sh`: `HOMEEVENTS_SCRIPT`, `HOMEEVENTS1-4_SCRIPT`), new
     `game.ini` entries. Chunk sizes ~10.1-10.9KB, comfortably under the
     ~16KB ceiling — smaller than WORK's chunks despite similar event
     count, presumably just shorter prose on average.
   - **`rm001.sc`'s `runShift` now picks a zone each turn**: uniformly
     random 50/50 between `DoWorkEvent(Random(0 WORK_EVENT_COUNT-1))` and
     `DoHomeEvent(Random(0 HOME_EVENT_COUNT-1))` (new `WORK_EVENT_COUNT`/
     `HOME_EVENT_COUNT` constants in `game.sh`). **Not the original's
     actual selection logic** — the browser game weights zone choice
     toward whichever zone matches the player's current worst stat
     (`weakZoneWeight`); this is uniform-random instead, same "ship the
     core mechanic, tune the weighting later" scope call already made for
     endings/mechanisms. `mechanisms.sc`/`casefiles.sc` needed zero
     changes — both are already zone-agnostic (`ApplyChoiceEffects`/
     `MarkCaseFile` just take deltas/tags/indices, not caring which zone
     an event came from).
   - Also fixed a stale comment in `game.sh` left over from the
     Case-Files array-vs-scalar saga (still described `gCaseFiles[N]` as
     an array; corrected to describe the actual `gCF0..gCF16` scalars +
     `GetCaseFile`/`SetCaseFile` accessor design).
   - Ran the structural sanity check on every touched/new file — clean.
     **Confirmed compiling and working** by the user, after several rounds
     of Compile All/Rebuild Resources (see the new gotcha entry above —
     that's expected for a batch of new interdependent scripts, wasn't a
     repeat of the `casefiles.sc` registration/circular-dependency saga).
   - **SOCIAL zone done too**, right after HOME was confirmed working —
     33 events (`js/events/social.js`, validated clean: no schema issues,
     no non-ASCII, no embedded newlines), same `MAX_CHOICES=3`/4-chunk
     treatment, `tools/gen-social-events.js` (identical shape to
     `gen-home-events.js`), scripts 113-117, chunk sizes ~10.6-11.7KB.
   - **`rm001.sc`'s zone picker restructured from the 50/50 `if/else` to a
     `switch`**, exactly as flagged as the next step: `= zone Random(0
     (- ZONE_COUNT 1))` then `(switch(zone) (case 0 DoWorkEvent(...))
     (case 1 DoHomeEvent(...)) (case 2 DoSocialEvent(...)))` — new
     `ZONE_COUNT`/`SOCIAL_EVENT_COUNT` constants in `game.sh`. One thing
     worth remembering: initially wrote this as `(switch(Random(0 (-
     ZONE_COUNT 1))) ...)` — switching directly on a function-call
     expression instead of a variable. Caught before compiling that there's
     no precedent anywhere in this codebase for `switch` on anything but a
     plain variable/property (every existing one, from `PrintChoices` on
     down, switches on something already assigned to a local first) —
     given how many "should work" assumptions have needed real fixes this
     session, added a `(var zone)` and assigned it first rather than gamble
     on an unverified pattern for something this central. Adding zone 4
     (SELF) is just one more `(case 3 ...)` line plus bumping `ZONE_COUNT`.
   - Ran the structural sanity check on all touched/new files — clean.
     **Confirmed compiling clean** by the user.
   - **SELF, BODY, and PUBLIC all done in the same pass** — by this point
     fully mechanical, exactly as predicted: SELF 33 events (scripts
     118-122), BODY 32 events (123-127), PUBLIC 32 events (128-132), each
     via its own `tools/gen-<zone>-events.js` (identical shape to
     `gen-social-events.js`), plus three more `(case N ...)` lines in
     `rm001.sc`'s zone-picker switch and `ZONE_COUNT` bumped to 6.
     Validated all three source files first (schema, non-ASCII, embedded
     newlines) before generating — SELF has one em dash (event: "Are you
     doing okay, actually?"), PUBLIC has one embedded-newline description;
     both confirmed correctly handled in the generated output (`- from
     someone...` and a literal `\n\n\n` respectively) by the same fixes
     already proven on WORK/HOME. All 12 new chunks ~10.2-11.6KB, same
     comfortable margin under the ~16KB ceiling as every other zone.
   - **All six zones from the original browser game are now ported**:
     WORK (34), HOME (32), SOCIAL (33), SELF (33), BODY (32), PUBLIC (32)
     — 196 events total, all reachable from `rm001.sc`'s turn loop via
     uniform-random zone + event selection. Ran the structural sanity
     check on every new file — clean. **Confirmed compiled and operational
     by the user** — the full six-zone content port is playable end to
     end, in one build.
   - **Zone-weighting done** (item 2's last unaddressed piece), added to
     `rm001.sc` right after the Case Files viewer: three new procedures,
     `PickWorstStat()` (0=repression/1=mask/2=child, comparing
     `gRepression` against `100-gMask`/`100-gChild` as integer "danger"
     values — no floats needed since higher is worse for all three once
     mask/child are inverted; ties favor the earlier stat, matching the
     original's `Array.reduce` order exactly), `ZoneStatBias(zoneIndex)`
     (same zone→stat mapping as the original's `content.js` `zones`
     array — WORK/BODY→repression, SOCIAL/PUBLIC→mask, HOME/SELF→child),
     and `PickZone()` (weighted pick: matching zones get integer weight 5
     vs. 2 for others, same ratio as the original's `weakZoneWeight: 2.5`,
     scaled since SCI0 arithmetic is integer-only; total weight is a
     hardcoded 18 since exactly 2 of the 6 zones always match any given
     worst stat — would need recomputing if that ever changes).
     `runShift`'s zone pick changed from `Random(0 ZONE_COUNT-1)` to
     `PickZone()`, nothing else. **Deliberately not replicated**: the
     original's "don't repeat an event already seen this run" pool —
     that's a separate, meaningfully bigger feature (per-event seen-
     tracking across all 196 events), out of scope for this pass. Ran the
     structural sanity check — clean. **Not yet compiled/playtested.**
   - **What's still not built**: this doc's item 3 (coping mechanisms/
     glitch/endings) already covers every zone equally since
     `ApplyChoiceEffects`/`ApplyGlitch`/the ending system never cared which
     zone an event came from — nothing left there. Still open: item 5's
     remaining art (stat gauges, player portrait), and the "no-repeat"
     event pool noted just above if it turns out to matter.
7. ~~Case Files viewer~~ **Done.** User asked to tackle this right after
   all six zones were confirmed compiled/operational.
   - **First, found the actual file had been renamed**: `casefiles.sc` is
     now `CaseFiles.sc` on disk (capital case) — presumably from creating
     it via SCI Companion's "New empty script" wizard to fix the earlier
     registration saga. Matches this codebase's existing case-insensitive
     `(use ...)` convention (`Door.sc`/`door`, etc.), not a problem, just
     noting it so a future session doesn't go looking for the lowercase
     name and conclude the file is missing. Also found and removed a
     stray duplicate `(script 107)` line at the very top of the file
     (ahead of the real `(script CASEFILES_SCRIPT)` a few lines down) —
     harmless leftover from the wizard, presumably, from before the real
     content got pasted in; cleaned up while already in the file.
   - **Used `DSelector`** (the stock scrollable-list control the
     Save/Restore dialogs already use), not a `PrintChoices`-style
     stacked-button dialog — deliberately, since a fixed-size scrolling
     viewport can't hit the dialog-height-overflow failure mode that's
     bitten this project twice already, regardless of how many of the 17
     entries there are. New procedures in `CaseFiles.sc`:
     `CaseFileTitle(index)` (a switch returning each of the 17 titles,
     duplicating strings already in `mechanisms.sc`/`rm002.sc` — no clean
     way to share a string constant across scripts here, and each is
     short) and `ShowCaseFiles()` (builds a 17-entry, 32-byte-stride
     buffer — sealed entries read "N. ??? (sealed)", discovered ones read
     "N. `<title>`" — and shows it via a `Dialog` + `DText` header +
     `DSelector`, same nsTop-clamp-after-center() defensive pattern as
     `PrintChoices`).
   - **Real bug, from over-generalizing the `SRDialog` precedent**: first
     version added `state(2)` on the selector (matching what the stock
     save-list selector sets) and the user reported the dialog closing
     the instant they clicked *anything*, including just scrolling ("goes
     immediately to the bottom and then closes"). Root cause:
     `DSelector:handleEvent` returns a truthy value (itself) exactly when
     `(& state 2)` is set on a claimed event, and the base `Dialog:doit()`
     loop (which `ShowCaseFiles` uses directly, via `(send hDialog:doit(NULL))`)
     treats *any* truthy `handleEvent` return as "done, close the modal
     loop" — that's correct for `DButton` (state defaults to `3` — clicking
     a button *should* immediately close the dialog with its value) but
     wrong for a browse-only list. `SRDialog` gets away with `state(2)`
     on its own selector because it overrides `doit()` entirely with
     custom logic that interprets that signal differently (which button
     triggered it, double-click vs. an explicit OK) — borrowing the flag
     without the logic that makes it safe was the mistake. **Fix**:
     dropped `state(2)`; added `state(1)` instead (confirmed via `sci.sh`,
     `TRUE = 1`) so the selector is still the dialog's initially-focused
     control (arrow keys/Page Up/Down reach it) without the bit that
     causes the auto-close. Bits 1 and 2 are independent — `DButton`'s
     default `state = 3` sets both because buttons legitimately want both
     behaviors at once; a plain `Dialog:doit()`-driven `DSelector` wants
     only the first.
   - **Reachable via a new "Case Files" menu item** (`` `^f ``, added to
     the existing "Action" menu in `menubar.sc`, `MENU_CASEFILES = $306`
     in `game.sh`) rather than new UI chrome — works from anywhere the
     menu bar is live, which includes the ending room (`rm002.sc` never
     hides it), satisfying the original's "reachable from the end screen"
     without any `rm002`-specific wiring.
   - **Deliberate scope cut**: browse-only, no drill-down into a selected
     entry's full description. That would mean duplicating all 17
     flavor-text strings a *second* time purely for this screen — not
     worth the extra resident heap for what's fundamentally a reference
     screen. Revisit if it turns out to matter.
   - **Compiled and mostly working per the user** — reachable via the
     menu, list displays and shows sealed-vs-discovered correctly. The
     `state(2)`/auto-close bug above is what they found first; after that
     fix, a second real bug surfaced (screenshot): scrolling to the
     bottom of the list showed one garbled row plus several rows of
     repeated "SSSSSSS" garbage past entry 17. **Root cause**: the local
     `buf[544]` array isn't zero-initialized (leftover stack contents,
     not blank) and `DSelector` has no concept of "17 entries, stop" —
     its `advance()` just keeps scrolling as long as the *next* slot's
     first byte is non-zero (`(while(amount and StrAt(cursor x)) ...)`),
     so once scrolled past the last real entry it happily renders
     whatever garbage bytes happen to follow in memory as more rows.
     **Fix**: explicitly zero the entire 544-byte buffer before writing
     the 17 real entries into it. Ran the structural sanity check on both
     fixes — clean. **Confirmed fully working by the user** after this
     second fix — scrolls cleanly, no garbling, closes only on Escape.
     Case Files viewer is done.
8. Player portrait — **plumbing done, art added, draw calls now live.**
   User asked "what do you have in mind for the remaining art?" then
   approved wiring the plumbing (view slot + threshold-based loop-swap
   logic) first, leaving stat-meters as a "nice to have, not now."
   - **Ruled out two existing classes first**: `Gauge` (`gauge.sc`) is
     `of Dialog` — an interactive modal slider (speed/volume use it), not
     a passive live meter, so unsuitable for a stat HUD without real
     rework. `Prop` (`Feature.sc`, `of View`) exists in the stock template
     but has zero instantiation examples anywhere in this codebase — no
     proven pattern to copy, so skipped in favor of the simpler stateless
     `DrawCel(view loop cel x y priority)` kernel call.
   - **Constants added to `game.sh`**: `PORTRAIT_VIEW = 801` (800 is
     already "Item - Test Object"), `PORTRAIT_X/Y` (4, 20 — an
     unvalidated top-left-corner guess, clear of where centered dialogs
     usually sit; move once real art exists and its actual size is
     known), `PORTRAIT_MOOD_NEUTRAL/REPRESSION/MASK/CHILD` (0-3, doubling
     as the art's loop numbers), `PORTRAIT_NEUTRAL_THRESHOLD = 60` (the
     worst stat's "danger" must reach this before showing anything but
     neutral — mild dips shouldn't change expression). `n801=Player
     Portrait` added to `game.ini`'s `[View]` section so the resource
     slot exists in SCI Companion even with no art behind it yet.
   - **`ResCheck` (would let code detect "does this resource exist" and
     skip the draw safely) turned out to be SCI1.1-only** — confirmed via
     the bundled Help docs — so there was no safe way to guard a live
     `Load`/`DrawCel` call against the not-yet-existing view 801 without
     breaking the build in the meantime. Resolved by keeping the actual
     resource-touching calls commented out until the user confirmed the
     view 801 art existed, then uncommenting them (see below) rather than
     ever having a call that could fail at runtime.
   - **Avoided a repeat of the `casefiles.sc` circular-dependency saga**:
     rather than a new `portrait.sc` needing `(use "main")` +
     `(use "mechanisms")` while something in `Main.sc`/`mechanisms.sc`
     would need `(use "portrait")` back — a brand-new circular pair, the
     exact shape that caused that multi-round saga — the new
     `PickWorstStat()` (moved out of `rm001.sc`, now shared) and
     `DrawPortraitMood()` procedures both live directly in the
     already-stable `mechanisms.sc`, called from inside
     `ApplyChoiceEffects`/`ApplyGlitch` (which already call
     `ClampStats()` after every stat change) and once each from
     `rm001.sc`'s `init()` (initial mood before the turn loop starts) and
     `rm002.sc`'s `init()` (final mood at the ending, added along with a
     new `(use "mechanisms")` there). No new circular `(use ...)` pairs
     introduced.
   - **`Main.sc`**: `Load(rsVIEW PORTRAIT_VIEW)` added in both
     `Template:init()` and `Template:newRoom()`, matching the existing
     `Load(rsFONT ...)`/`Load(rsCURSOR ...)` pattern.
   - Ran the structural sanity check on every touched file (`game.sh`,
     `mechanisms.sc`, `rm001.sc`, `rm002.sc`, `Main.sc`) — clean, both in
     the initial plumbing pass and after uncommenting the live calls.
   - **User added the view 801 art**, then reported it "doesn't seem to
     appear anywhere in-game" — expected, since the plumbing pass had
     deliberately left the `Load`/`DrawCel` calls commented out. Fix:
     uncommented the two `Load(rsVIEW PORTRAIT_VIEW)` lines in `Main.sc`
     and the `DrawCel(PORTRAIT_VIEW mood 0 PORTRAIT_X PORTRAIT_Y -1)` call
     inside `DrawPortraitMood()` in `mechanisms.sc`.
   - **Confirmed working by the user on first try** (screenshot): portrait
     renders cleanly in the top-left corner at the guessed `PORTRAIT_X`/
     `PORTRAIT_Y` (4, 20), doesn't overlap the dialog box or menu bar, and
     showed an appropriately grim expression at Repression 55/Mask 60/
     Child 45 — the loop-number-to-mood mapping and position guess both
     turned out correct with no adjustment needed.
   - **Not actually done** — user's words: "it's not done because it
     shouldn't just sit there like that all the time." Right now
     `DrawCel` just paints it permanently in the corner every turn with no
     erase/hide logic — fine as a functional proof but not the intended
     presentation. **Deliberately deferred**: this is presentation polish
     (when/how the portrait should show, hide, or transition), explicitly
     set aside for a later session in favor of the new-game-plus and
     clickable-office-objects work below.
9. ~~New Game+ mode~~ **Done, not yet compiled/playtested.** User pointed
   out this session's design source of truth: rather than inventing New
   Game+ from scratch, the original browser game already has it, fully
   implemented, under the name "Extended Therapy" — found and ported the
   real spec instead of guessing:
   - **Spec, straight from the blueprint** (`js/engine.js` lines 47-58,
     616-635, 690-719; `js/content.js` lines 14-16): survive one standard
     session and it unlocks permanently (`localStorage`-backed in the
     original); once unlocked, every future run offers a choice between
     Standard (`maxTurns: 10`) and Extended Therapy (`hardModeTurns: 20` —
     double length) which also scales *every* stat swing — including
     mechanism modifiers and the glitch wildcard, since the original's
     `handleGlitchChoice` routes through the same `handleChoice` that
     applies the multiplier — by `hardModeMultiplier: 1.25`. Starting stats
     are unaffected. (The original also has a third `arcade` mode — infinite
     turns, escalating multiplier every 10 turns, random starting stats —
     genuinely separate from NG+/hard mode and never mentioned in this
     project's scope; not ported, not asked for.)
   - **Persistence**: new `gNgPlusUnlocked` global (`Main.sc`), loaded once
     at boot (`Template:init()`, right next to `LoadCaseFiles()`) via new
     `LoadNgPlusUnlocked()`/`SaveNgPlusUnlocked()`/`UnlockNgPlus()`
     procedures in `CaseFiles.sc`. **Deliberately a separate file,
     `TRSNGP.DAT`, not a new slot in the existing `gCaseFiles`/
     `TRSCASE.DAT` array** — that machinery already had two real,
     hard-won bug fixes (the `state(2)` auto-close bug, the unzeroed
     `buf[544]` garbage-row bug, both above) and reusing it for one
     unrelated boolean risked reopening either for no real benefit versus
     just writing one more tiny, isolated `FOpen`/`FGets`/`FPuts`/`FClose`
     pair matching the exact same proven shape.
   - **The mode choice itself lives in `rm001.sc`'s `init()`**, not
     `TitleScreen.sc` — deliberately. `menubar.sc`'s "Restart Game" calls
     `(send gGame:restart())`, and `Main.sc`'s `GameIsRestarting()` check
     sends a restart straight to `INITROOMS_SCRIPT` (`rm001`), skipping
     `TitleScreen.sc` entirely. Putting the prompt on the title screen would
     silently never fire on a restart; `rm001.sc`'s `init()` is the one
     place every run actually passes through regardless of entry path. Only
     asked when `gNgPlusUnlocked` (via `PrintChoices`, the existing
     stacked-button dialog, two options, width 290 matching every generated
     event's convention); otherwise defaults straight to Standard, same as
     the original's hidden-until-unlocked button. Sets `gMaxTurns` from the
     new `DEFAULT_MAX_TURNS`(10)/`HARD_MODE_TURNS`(20) constants
     (`game.sh`) — `runShift`'s turn-loop condition already reads
     `gMaxTurns` generically, so doubling turn count needed zero changes
     there. Picking Extended Therapy also prints the original's own flavor
     line verbatim ("Extended session initiated. Your nervous system has
     been here before.") for blueprint fidelity.
   - **The 1.25x stat-swing multiplier**: new `ScaleHardMode(delta)` in
     `mechanisms.sc`, called on all three deltas in both
     `ApplyChoiceEffects` (after the mechanism-modifier switch, before
     adding to state — same order as the original's
     `applyMechanismModifiers` → hard-mode-multiply → add-to-state) and
     `ApplyGlitch`. SCI0 has no floats, so 1.25 = 5/4 via integer
     multiply-then-divide with a rounding nudge (`+2` before dividing by 4)
     rather than truncating. **Written to always divide a non-negative
     numerator** (works on `Abs(delta)`, reapplies the sign after) rather
     than dividing a negative value directly — there's no confirmed
     precedent anywhere in this codebase for which way SCI0's `/` rounds
     negative operands, and since about half of every delta this game
     produces is negative, this was worth designing around rather than
     gambling on, same caution as the if/else-chaining and switch-on-a-
     variable calls made earlier this project (see the gotchas above).
   - **Unlock trigger**: `rm002.sc`'s `printSurvivalEnding()` (survival-only
     — failure endings never call it) now calls `UnlockNgPlus()` first,
     guarded by `(if(not gHardMode) ...)` — matches the original's
     `if (!state.hardMode) unlockNgPlus()` in `checkGameEnd()` exactly:
     an Extended Therapy run surviving doesn't need to re-trigger anything,
     it's already unlocked.
   - Ran the structural sanity check (paired quotes, balanced parens
     outside string literals, no stray non-ASCII in any new string literal)
     across every touched file (`game.sh`, `Main.sc`, `CaseFiles.sc`,
     `mechanisms.sc`, `rm001.sc`, `rm002.sc`) — clean, but **recreated the
     exact `main`↔`casefiles` circular-dependency bootstrap failure from the
     original Case Files saga (see above), first real compile attempt**:
     new symbols landed on *both* sides of that circular `(use ...)` pair at
     once -- `gHardMode`/`gNgPlusUnlocked` (new globals in `Main.sc`, which
     `CaseFiles.sc`'s new procedures read) and `LoadNgPlusUnlocked` (new
     procedure in `CaseFiles.sc`, which `Main.sc` calls). Neither side's
     stale `.sco` had ever resolved the other's brand-new symbols, so both
     failed circularly -- `Unknown procedure 'LoadNgPlusUnlocked'`/
     `Undeclared identifier 'gNgPlusUnlocked'`/`'gHardMode'`, both sides
     saying "did you forget to use X" despite already using it (the
     tell-tale sign, same as last time -- it's a stale-`.sco` bootstrap
     order problem, not a missing `(use ...)`). **Same fix, confirmed
     necessary again**: comment out the `LoadNgPlusUnlocked()` call (line
     175 of `Main.sc`) only, compile `Main.sc` alone (F8, succeeds --
     `gHardMode`/`gNgPlusUnlocked` don't themselves depend on anything new
     in `casefiles.sc`), compile `CaseFiles.sc` alone (F8, now resolves
     against the freshly-updated `main.sco`), restore the line, then
     Compile All (possibly a few rounds, per the usual batch-settling
     behavior). **Not yet confirmed working after this fix** -- told to the
     user, not yet re-attempted as of this doc's last edit.
   - **Real bug, several rounds, root cause never actually identified —
     `TRSNGP.DAT` wrote correctly but never read back correctly, and the
     eventual fix was to stop using it, not to fix it.** After the
     bootstrap fix above got everything compiling clean, the mode-choice
     dialog still never appeared on any subsequent run. Diagnosed step by
     step, each ruling out one theory:
     - First suspected an unflushed write (DOSBox-X's mounted-drive cache
       not syncing to the host before an abrupt window close) — real, but
       a red herring: confirmed via a survive-then-clean-exit retest that
       `TRSNGP.DAT` correctly held `1\n` on disk (verified directly by
       reading the raw file bytes), yet the very next run still defaulted
       to Standard with no dialog.
     - Added a debug `FormatPrint` at the point `rm001.sc` checks
       `gNgPlusUnlocked` — printed `0` even with the file confirmed correct
       on disk. Instrumented `LoadNgPlusUnlocked()` itself with three debug
       prints (`FOpen`'s return, the raw buffer content, the parsed
       result): `FOpen` succeeded (valid handle), but the buffer came back
       empty and parsed as `0` — meaning `FGets` itself wasn't reading the
       file's actual content, despite `FOpen` proving the file existed and
       was found.
     - Suspected DOS file-handle reuse from opening `TRSCASE.DAT`
       immediately before it (same handle number likely reused, possibly
       retaining a stale/EOF-positioned read cursor) — tested by reordering
       the two `Load*()` calls so `LoadNgPlusUnlocked()` ran first, with
       nothing else touching file I/O beforehand. Identical result. Ruled
       out.
     - Suspected the exact bug class that already bit `ShowCaseFiles` once
       this project (an uninitialized local array holding leftover stack
       garbage from a prior call, not clean zeros) — added the same
       explicit zero-the-buffer-first fix that worked there. Identical
       result. Ruled out.
     - Considered whether this was actually a *systemic* bug in the shared
       `Load*()` pattern (never verified to round-trip through a genuinely
       cold boot anywhere in this project before now) rather than something
       new — tested by opening the already-working Case Files viewer on a
       fresh boot, no gameplay first. **Disproved**: real previously-marked
       entries showed correctly as discovered, not reverted to sealed —
       `LoadCaseFiles()`/`FOpen`/`FGets` fundamentally do work correctly for
       reading real persisted values back after a cold boot in general.
       Whatever's actually different about a lone, standalone
       `FOpen`/`FGets` call on its own dedicated file remains
       **unexplained** — every theory that would distinguish it from
       `LoadCaseFiles()`'s own reads was tested and ruled out.
     - **Final fix: stopped trying to make the standalone file work, and
       folded the flag into the already-proven `gCaseFiles` array instead.**
       `CASEFILE_COUNT` bumped from 17 to 18 (`game.sh`); the new slot 17
       (`CASEFILE_NGPLUS`) holds the unlock flag in `gCF17` (`Main.sc`), via
       the exact same `GetCaseFile`/`SetCaseFile`/`LoadCaseFiles`/
       `SaveCaseFiles` machinery already confirmed to round-trip correctly.
       A new `VIEWABLE_CASEFILE_COUNT` (17) keeps `ShowCaseFiles()`'s viewer
       scoped to just the real 17 case files, so slot 17 doesn't show up as
       a bogus 18th entry with no title. `UnlockNgPlus()` (`CaseFiles.sc`)
       now calls `MarkCaseFile(CASEFILE_NGPLUS)` instead of the deleted
       `SaveNgPlusUnlocked()` — a small, harmless improvement over the
       original's exact semantics: `MarkCaseFile` only actually writes to
       disk the *first* time (vs. the original JS's unconditional write on
       every survival), since the in-memory `gNgPlusUnlocked` mirror still
       gets set every call regardless. `gNgPlusUnlocked` itself is now just
       an in-memory convenience mirror of `gCF17`, synced once right after
       `LoadCaseFiles()` in `Template:init()` — every other call site
       (`rm001.sc`'s mode-choice check, `mechanisms.sc`'s `ScaleHardMode`,
       `rm002.sc`'s unlock guard) reads the plain global exactly as before,
       unchanged. `LoadNgPlusUnlocked`/`SaveNgPlusUnlocked`/the standalone
       `TRSNGP.DAT` file are deleted/orphaned entirely.
     - **Compile note**: this change touches both sides of the
       `main`↔`casefiles` pair again, but asymmetrically this time — only
       `CaseFiles.sc`'s new `case 17` branches need the brand-new `gCF17`
       from `Main.sc`; nothing in `Main.sc` needs a new symbol back. Compile
       `Main.sc` alone first (registers `gCF17`), then Compile All — the
       harder two-sided bootstrap dance from before shouldn't be needed
       this time.
     - **Still failed after the array pivot — traced to the real root
       cause and fixed. `LoadCaseFiles()`'s `FGets` read had never actually
       worked, for any slot, this entire project's history — not just the
       new one.** Proved this by dumping every one of the 18 parsed values
       in a single consolidated debug dialog: every single index came back
       `0`, including ones known to be `1` on disk. This meant the earlier
       "Case Files persist correctly across sessions" confirmations
       earlier in this doc were never actually observing a genuine disk
       reload — almost certainly leftover in-memory state surviving
       `Restart Game` (which apparently doesn't reset globals the way
       assumed) within one continuous interpreter session, never
       independently verified end to end before now.
       - Ruled out a tooling artifact first: re-tested in real standalone
         DOSBox-X (the documented workflow) instead of SCI Companion's
         built-in playtest button, in case the IDE's integrated interpreter
         had different/buggy file-I/O emulation. Identical failure in both
         — a real bug in the compiled game, not a testing-tool quirk.
       - Nearly went down a wrong path: found the stock, *unused*
         `fileio.sc` `File` class calling `FGets` with only 2 arguments
         (no handle) and, believing that meant our own 3-argument call was
         wrong, changed `LoadCaseFiles()` to match. **This was a mistake —
         it hung the game on boot** (black screen, stuck loading cursor).
         Reverted it immediately. Checking SCI Companion's own bundled
         kernel docs afterward (`Help/Kernels/FGets.html`) confirmed the
         3-argument form was correct all along —
         `FGets(buffer max [handle])`, with `handle` merely optional, not
         wrong to include. The stock `fileio.sc` class turned out to be
         unverified boilerplate (zero real usage anywhere in this
         project), not a trustworthy reference — a costly reminder that
         "it's in the stock template" isn't the same as "it's proven,"
         unlike e.g. `DisposeLoad.sc`'s `paramTotal` pattern or the
         `and`/`or`-chaining precedent, which *are* real, exercised code.
       - **Actual root cause, found by reading SCI Companion's own bundled
         `Help/Kernels/FOpen.html` and the real `sci.sh` directly**: the
         long-known `fOPENFAIL`/`fOPENCREATE` swap (see the corrected
         gotcha entry earlier in this doc) is not a harmless footnote —
         `LoadCaseFiles()` had used `fOPENFAIL` to open `TRSCASE.DAT` for
         reading since the day it was written, and that flag actually
         triggers destructive create/reset-style kernel behavior under
         SCI0, silently wiping the file immediately before the very next
         line tried to read it back. Writes (`SaveCaseFiles()`'s `fCREATE`)
         were never affected — a genuinely separate constant, not part of
         the swap — which is exactly why every persistence bug this whole
         debugging arc surfaced was one-directional: saves always looked
         correct on disk, loads never worked.
       - **Fix**: `LoadCaseFiles()` now opens with `fOPENCREATE` (the one
         that actually means "open existing, abort if not possible" under
         this swap) instead of `fOPENFAIL`, plus a more defensive
         `(if(not hFile) return)` failure check (matching stock
         `fileio.sc`'s own pattern) instead of `== -1`, since `NULL` is `0`
         in this dialect and a failed open isn't guaranteed to specifically
         return `-1`.
       - **Confirmed fixed by the user** — screenshot shows the "New
         Session" dialog rendering correctly (`Standard Session (10
         turns)` / `Extended Therapy (20 turns, harder swings)`), reachable
         after a real survived run and a restart, exactly as designed.
         Portrait, status line, and background art all render correctly
         alongside it. All temporary debug `FormatPrint`/dump
         instrumentation added during this investigation (`rm001.sc`,
         `Main.sc`, `CaseFiles.sc`) has been removed; structural sanity
         check re-run clean across every touched file.
       - **Confirmed: a full Extended Therapy run played start to finish
         with no crashes.** Item 9 (New Game+ / Extended Therapy) is fully
         done and playtested.
   - **Not built, out of scope**: the original's `arcade` mode (see above);
     rebalancing which content is *available* in Extended Therapy (the
     original doesn't change available content either, only turns/
     multiplier, so this matches); any UI indication of which mode is
     currently active mid-run beyond the one-time flavor line (the original
     shows `" (Extended Therapy)"` appended to a status/title element this
     port's status line doesn't have room for or an equivalent of).
10. **Clickable objects in the office room.** User's own examples: click
    the filing cabinet to open the Case Files viewer (already built and
    working — see item 7 — just needs a second entry point beyond the
    `^f` menu item), click the computer to start a new game (there's no
    restart-flow-from-within-a-room to hook yet either — see item 9's
    "not built" note, item 2's still-missing "run over, restart?" flow,
    and rm002's original "no restart flow yet" comment).
    - **Filing cabinet → Case Files viewer: done, not yet
      compiled/playtested.**
      - **Real architectural finding, changed where this had to live**:
        went looking for a click-to-trigger pattern to copy (`Feature`/
        `Prop` in `Feature.sc` were the leading candidates, per the
        earlier portrait-plumbing notes) and traced how a mouse click
        actually flows through this engine first: `User:handleEvent`
        forwards every event to `gGame:handleEvent` → `gRegions:
        handleEvent` → the current room's own `handleEvent`
        *unconditionally*, regardless of `ProgramControl()`'s
        `canInput(FALSE)`/`canControl(FALSE)` (confirmed by reading
        `User.sc`/`Game.sc` directly) — so a room-level click check
        looked viable even with ego hidden and movement disabled.
        **But**: `PrintChoices`' `Dialog:doit()` (`Controls.sc`) runs its
        *own*, fully separate `Event:new()`/`handleEvent()` loop that only
        ever dispatches to the dialog's own child controls (buttons) —
        confirmed by reading it directly — and never forwards anything
        back out to the room. Since `rm001.sc`'s entire `runShift()` turn
        loop is one unbroken chain of these `PrintChoices` dialogs from
        the moment `init()` runs until the room transitions away, **there
        is no idle moment in `rm001` for a click to ever reach the room's
        `handleEvent`** — user confirmed this directly by testing ("the
        room isn't clickable until all the questions end"), which is what
        settled it rather than more code-reading alone. **Fix**: the
        hotspot lives in **`rm002`** (the ending room) instead — it's
        genuinely idle once the ending prints, reuses the identical
        background art (`picture 1`, same room), and fits the moment
        better anyway (review the outcome, maybe check Case Files) than
        mid-run ever would have.
      - **Implementation**: `CABINET_X1/Y1/X2/Y2` (`game.sh`) — a
        rectangle hand-estimated from a screenshot of the user's actual
        room art (the gray 4-drawer cabinet at the room's right edge),
        explicitly an **unvalidated guess**, same "guess now, adjust once
        confirmed" approach already proven fine for `PORTRAIT_X/Y`.
        `rm002.sc`'s `RoomScript:handleEvent` checks
        `(send pEvent:type)==evMOUSEBUTTON` and the click coordinates
        against that rectangle (same `nsLeft`/`nsTop`/`nsRight`/`nsBottom`
        hit-test idiom already proven in `Controls.sc`'s own button
        `check(pEvent)` method), and calls the already-working
        `ShowCaseFiles()` (`casefiles.sc`, already `(use ...)`'d in
        `rm002.sc`) if it hits. Written as nested single-condition `if`s
        rather than one long `and`-chain — this codebase's only confirmed
        `and`-chain precedent tops out at 4 terms, and this needed 5
        (unclaimed, event type, 2 x-bounds, 2 y-bounds), so it's split
        into two 2-term chains instead of gambling on an unverified
        length, same caution as the hard-mode multiplier and zone-picker
        `switch` decisions earlier in this doc.
      - Ran the structural sanity check on both touched files (`game.sh`,
        `rm002.sc`) — clean. **Confirmed working by the user on the first
        try** — the guessed rectangle needed no adjustment; clicking the
        cabinet in the ending room opens the Case Files viewer correctly.
        Filing cabinet hotspot is done.
    - **Computer → new game: done, confirmed working by the user.** User's
      own call: instead of the "Restart Game" menu item's kernel-level
      `RestartGame()` (a full VM reset), just do a plain
      `(send gRoom:newRoom(INITROOMS_SCRIPT))` back to `rm001` and reset
      per-run state manually, same as the engine already does for every
      other room transition in this project.
      - **Where the reset lives, and why**: rather than resetting stats/
        mechanisms at the click site in `rm002.sc` (which would only cover
        *this* entry path), the reset was added unconditionally to the top
        of `rm001.sc`'s own `init()` — the one place every way of starting
        a run already funnels through (title screen, the menu's
        `RestartGame()`, and now this). Resets `gRepression`/`gMask`/
        `gChild` to new `STARTING_REPRESSION`/`STARTING_MASK`/
        `STARTING_CHILD` constants (`game.sh` — also backfilled into
        `Main.sc`'s own initial globals, so there's exactly one place to
        change starting stats, not two) and all 5 mechanism count/unlocked
        pairs back to 0/`FALSE`. Deliberately does **not** touch
        `gCF0..17`/`gNgPlusUnlocked` — those are the persistent, cross-run
        record and must survive a new run starting, same reasoning as
        everywhere else `MarkCaseFile`/`UnlockNgPlus` come up. For the two
        pre-existing entry paths (fresh boot, kernel restart) this is a
        harmless no-op, since those globals are already at these exact
        values by the time `rm001:init()` runs either way — it's only
        load-bearing for the new click path, since a plain `newRoom()`
        doesn't touch globals at all. `gTurn`/`gHardMode`/`gMaxTurns`
        already get freshly set every time `rm001:init()` runs regardless
        (the turn-loop reset and the NG+ mode-choice logic just above),
        so nothing extra needed there.
      - **Computer hotspot**: `COMPUTER_X1/Y1/X2/Y2` (`game.sh`), another
        unvalidated screenshot-based guess (the desk's monitor+keyboard),
        same rectangle-hit-test idiom as the cabinet, added right below it
        in `rm002.sc`'s `RoomScript:handleEvent`. No confirmation prompt
        (unlike "Restart Game," which interrupts an active run and has
        real progress to lose) — this is only ever reachable from the
        already-ended state, nothing to accidentally lose.
      - Ran the structural sanity check on every touched file (`game.sh`,
        `Main.sc`, `rm001.sc`, `rm002.sc`) — clean. **Confirmed working by
        the user** — clicking the computer correctly starts a fresh run.
        Item 10 (clickable office objects) is fully done: both hotspots
        confirmed working.
11. **Portrait shown inside the event dialog itself, not just the room
    background — done, not yet compiled/playtested.** User's own
    observation: the background-corner portrait (item 8) is barely ever
    actually visible during real play, since a `PrintChoices` dialog
    covers that exact spot on almost every turn. Wrote a whole tutorial
    on control-screen hotspots (see `docs/sci0-control-screen-hotspots.md`)
    right before this came up — unrelated feature, same session.
    - **Real, proven precedent found and reused, not invented**: the stock
      `Print()` kernel wrapper (`Controls.sc`) already supports an
      `#icon` option that does exactly this — embeds a `DIcon` (a real
      dialog child control wrapping a view/loop/cel, sized via
      `CelWide`/`CelHigh`) at the dialog's top-left, then positions the
      message `DText` to its right instead of at the dialog's left edge.
      This project's own inventory `Iitem:showSelf` (`Main.sc`) already
      uses this via `Print(description #title objectName #icon view loop
      cel)`. `PrintChoices` is a hand-built custom dialog, not a call
      through `Print()` itself, so it needed the same `DIcon`+`DText`
      layout built by hand rather than getting it for free — but it's the
      identical, already-proven mechanism, not a new one.
    - **Refactor**: `mechanisms.sc`'s `DrawPortraitMood()` split into a new
      `GetPortraitMood()` (returns the mood integer, 0-3) plus a thin
      `DrawPortraitMood()` that just calls it and draws to the background
      as before — so `PrintChoices` (`printchoices.sc`) can ask for the
      current mood directly without duplicating the worst-stat comparison
      logic a second time. New `(use "mechanisms")` in `printchoices.sc`
      — a one-way addition (`mechanisms.sc` calls nothing in
      `printchoices.sc`), so not circular, and every event chunk that
      calls `PrintChoices` already has `(use "mechanisms")` itself anyway,
      so no new heap-residency risk either.
    - **Layout**: `hIcon` (`DIcon:new()`, `view(PORTRAIT_VIEW)
      loop(GetPortraitMood()) cel(0)`) placed at `(4 4)` inside the
      dialog; `hDText` moved to start at `hIcon:nsRight + 4` instead of
      `4`, and its wrap width reduced by that same amount (`width -
      (hIcon:nsRight + 4)`) so the dialog's total width stays within the
      same budget as before rather than growing past the icon's footprint
      — the icon's actual width comes from `CelWide` at runtime, not a
      guessed constant, so this doesn't depend on knowing the portrait
      art's exact pixel size in advance. Buttons still start at `x=4`
      (full width, unaffected) at whichever of the icon's or text's
      bottom edge ends up lower. `PrintChoices`'s own external signature
      is unchanged — none of the 196 generated per-event calls needed any
      changes.
    - **Real risk, flagged, not yet resolved by testing**: narrowing the
      description's wrap width means more text wraps onto more lines than
      before, which could push already-borderline-tall events back toward
      the vertical-overflow class of bug this project hit twice already
      (the reason the `nsTop` clamp and `SMALL_FONT` buttons exist at
      all). Specifically worth testing against the two previously-
      identified tallest events ("The Typo" and "The Performance Review
      Buzzword," both WORK zone) once compiled, not just a random turn —
      if either overflows again, the next lever is probably shrinking the
      description font too (flagged but untouched back when `SMALL_FONT`
      buttons were the fix, see the tall-dialog entries above).
    - **Follow-up, same conversation**: user pointed out the old
      background-corner `DrawPortraitMood()` calls are now pure dead
      weight during real gameplay — removed the ones in
      `ApplyChoiceEffects`/`ApplyGlitch` (`mechanisms.sc`) and the initial
      one in `rm001.sc`'s `init()`. **Deliberately kept** the one in
      `rm002.sc`'s `init()` — the ending room has no dialog covering that
      spot, so it's the only thing that actually shows the portrait there,
      not a duplicate. The `DrawPortraitMood()` procedure itself is
      unchanged and still live for that one call site.
    - Ran the structural sanity check on every touched file
      (`mechanisms.sc`, `printchoices.sc`, `rm001.sc`) — clean.
    - **Real bug found on first compile/playtest: a choice button's own
      width pushed the whole dialog border out of bounds on the right**
      (screenshot: a SOCIAL-zone "standup meeting divorce" event, not
      either of the two events already flagged as tall-dialog risks —
      this is a wider-reaching issue than just those two). Root cause,
      confirmed by reading the stock `DButton:setSize()` in `Controls.sc`
      directly: it calls `TextSize(@rect text font)` with **no width
      limit at all** — button width has always been whatever the longest
      already-`\n`-embedded line (from the generator's
      `BUTTON_WRAP_LEN = 36` chars/line) happens to measure in
      `SMALL_FONT` pixels, uncapped, then rounded up to the next 16px
      multiple. The description text has respected a width cap
      (`DText:setSize(width)`) this whole project; buttons never did —
      this was presumably always a latent risk, just not exposed until
      this specific event's longest line happened to be wide enough
      in practice. Unrelated to the portrait icon itself (checked the
      math: the description's own worst-case width is actually very
      slightly *smaller* now than before the icon was added, not larger).
      **Fix**: new `SizeButtonToWidth(hButton maxWidth)` in
      `printchoices.sc` — the same computation as stock
      `DButton:setSize()` (the `+2` padding, the round-up-to-16px step),
      but calling `TextSize` with the same `maxWidth` (the `width`
      parameter `PrintChoices` already takes) the description text uses,
      instead of leaving it unconstrained. Applied to both the regular
      choice buttons and the glitch button. Confirmed the `setSize()`-
      then-`moveTo()` call order is safe by reading `Control:moveTo()`
      directly — it shifts all four bounds by a delta, so computing
      size before position (same order the original code already used)
      correctly preserves the computed width/height. Trade-off, flagged
      honestly: forcing a narrower cap means an overly-long button line
      wraps into more (shorter) lines instead of one wide one, trading
      horizontal safety for a bit more vertical growth — the other axis
      this project has already hit twice. Ran the structural sanity check
      again after this fix — clean. **Not yet compiled/playtested.**
    - **Portrait size**: user asked about shrinking the in-dialog portrait
      to help with the same overflow. Not a code fix — SCI0 has no
      runtime sprite-scaling kernel call (that's a later-SCI/VGA-era
      feature), so the rendered size is whatever the `PORTRAIT_VIEW`
      cel's actual art is. The icon's layout math already reads its real
      dimensions via `CelWide`/`CelHigh` at runtime, so a smaller
      re-imported cel would just work with zero code changes if the user
      resizes the source art in SCI Companion's View editor.
    - **Scrollable dialog considered, deliberately not attempted**: user
      floated a scrollbar as an alternative if width/wrapping tuning
      isn't enough. Genuinely possible — this project already has a
      proven scrolling control, `DSelector` (`ShowCaseFiles()`'s viewer)
      — but it's a single-line list-item selector, not a multi-line
      wrapped-button layout, so adopting it for `PrintChoices` would mean
      redesigning the whole choice-rendering/selection model, not adding
      a scrollbar to the existing one. Deferred until it's clear the
      simpler fixes (button width cap, portrait resize) aren't enough.
    - **Follow-up, same conversation**: user wants the portrait visible
      only during actual play, full stop — removed the last remaining
      background-corner call (`rm002.sc`'s `init()`). With that gone,
      `DrawPortraitMood()` (the background-draw wrapper, distinct from
      `GetPortraitMood()`, which `printchoices.sc` still uses and keeps)
      had zero call sites left anywhere in the project, so deleted the
      now-fully-dead procedure itself rather than leaving it as unused
      code. `PORTRAIT_X`/`PORTRAIT_Y` (`game.sh`) are consequently unused
      too, but left defined — a `#define` costs nothing at compile time,
      so there's no cleanup benefit to removing them, and they're ready
      if a background draw is ever wanted again. Updated the now-stale
      `DrawPortraitMood()` references in a couple of `Main.sc` comments
      (near the `Load(rsVIEW PORTRAIT_VIEW)` calls, which are still
      correct and unchanged — `printchoices.sc`'s `DIcon` still needs the
      view resident) to point at `GetPortraitMood()`/`DIcon` instead. Ran
      the structural sanity check on every touched file (`mechanisms.sc`,
      `rm002.sc`, `game.sh`, `Main.sc`) — clean.
    - **Follow-up, same conversation: the button-width fix above was too
      conservative.** User's first compile/playtest of it showed buttons
      wrapping well before they needed to, leaving visible dead space
      between the text and the dialog's own right border (screenshot).
      Root cause: `SizeButtonToWidth` had reused the description's own
      `width` parameter (290) as the button cap too — but that value is
      calibrated for the description+icon pairing specifically (they
      share horizontal space), while buttons start back at the dialog's
      full left edge below both and don't compete with the icon at all,
      so they can safely run wider. **Fix**: new `BUTTON_MAX_WIDTH` (306,
      `game.sh`) — a real, independent cap that only has to stay under
      the 320px screen after centering, decoupled from whatever the
      description/icon combination needs. Both `SizeButtonToWidth` call
      sites in `printchoices.sc` now use it instead of `width`. Ran the
      structural sanity check again — clean.
    - **Follow-up, same conversation: the actual root cause of the wasted
      space was upstream of anything in `printchoices.sc` — the content
      generator, not the engine.** Second playtest with the wider button
      cap still showed short, centered lines with dead space either side
      (screenshot) — user's own sharp diagnosis: "could this be related
      to how the SCI engine chunks/tokenizes text?" Correct instinct,
      wrong layer: `tools/lib/zone-events.js`'s `wrapButtonText()`
      (`BUTTON_WRAP_LEN = 36` characters/line) has always pre-wrapped
      every choice/glitch string with hardcoded `\n` breaks *at content-
      generation time*, baked directly into each generated `.sc` file's
      string literals — completely independent of whatever width the
      button actually renders at. `TextSize()` (and thus
      `SizeButtonToWidth`) respects existing `\n`s as hard breaks and only
      adds *more* wrapping on top if a segment between them is itself too
      wide; it can't undo or reflow around breaks that already exist. So
      widening `BUTTON_MAX_WIDTH` genuinely widened the button's
      bounding box, but every line inside it was still capped at its old
      36-character segment, rendered centered with growing margins on
      both sides as the box got wider than the text needed.
      **Fix**: removed `wrapButtonText()`/`BUTTON_WRAP_LEN` from the
      generator entirely — choice and glitch text now go through plain
      `sciString()` (the ASCII-safety/escaping pass, unchanged) with no
      pre-wrapping at all, emitted as a single unbroken line. Wrapping is
      now handled *only* by `SizeButtonToWidth`'s real, pixel-accurate
      `TextSize()` call at render time — the exact same mechanism the
      description text has always used via `DText:setSize(width)`, so
      buttons and description now share one consistent, width-aware
      wrapping approach instead of two different ones (a fixed
      character-count guess vs. real font metrics). Regenerated all six
      zones (`node tools/gen-<zone>-events.js` for work/home/social/self/
      body/public) — all 30 output files (5 per zone: 1 dispatcher + 4
      chunks) written successfully, chunk sizes actually shrank slightly
      (~10-11.7KB, comfortably under the ~16KB ceiling) since dropping the
      `\n` escapes removes a couple bytes per choice. Spot-checked the
      exact event from the user's screenshot (`socialevents4.sc`,
      "Keep scrolling well past the point...") — confirmed it's now a
      single unbroken line with no embedded `\n`. Ran the full structural
      sanity check (paired quotes, balanced parens outside string
      literals, no *new* non-ASCII — the header-comment arrow/em-dash
      already established as harmless in every prior generation round is
      still present and still fine) across all 30 regenerated files —
      clean. Bonus, not yet confirmed: since text now wraps at its real
      available width instead of an artificially narrow guess, dialogs
      should generally end up *shorter* too (fewer, fuller lines per
      button), which should help rather than hurt the vertical-overflow
      risk this project has fought twice already. **Not yet
      compiled/playtested** — this is the largest-blast-radius change in
      this whole thread (all 196 events regenerated), worth a thorough
      pass: the previously-flagged tallest events ("The Typo," "The
      Performance Review Buzzword") plus a general spot-check across a
      few zones, not just the one event already confirmed by inspection.
    - **Follow-up, same conversation: `BUTTON_MAX_WIDTH` (306) was itself
      still too generous — real bug, confirmed via screenshot ("The
      Unfinished Thing"), dialog escaping both screen edges at once** (a
      too-wide dialog, once centered, pushes nsLeft negative while also
      overflowing nsRight — explains why it affects both sides
      simultaneously, not just the right). Root cause this time, found by
      actually reading `DText:setSize()` for comparison: unlike `DText`
      (a clean `nsRight = nsLeft + measuredWidth`, capped exactly by its
      width parameter, no adjustment), the stock `DButton:setSize()` that
      `SizeButtonToWidth` mirrors adds `+2` padding and then rounds the
      *result* up to the next multiple of 16 — a step the first fix
      didn't account for. Worst case at 306: `306 + 2 = 308`, which rounds
      up to `320`, the full screen width, before even adding the button's
      own 4px left margin. **Fix**: `BUTTON_MAX_WIDTH` lowered to 286 —
      chosen so the worst-case *rounded* result lands exactly on 288
      (`286 + 2 = 288`, already a multiple of 16, so no further rounding
      applies), comfortably clear of the 320px screen with room to spare
      for the dialog's own border chrome on top. Documented the full
      worked-through math directly in `game.sh`'s comment so a future
      width adjustment doesn't have to re-derive it from scratch. Ran the
      structural sanity check again — clean.
    - **Confirmed generally working by the user** after this third round
      of width tuning (uncapped → 306 → 286) — "behaving much more
      nicely." User mentioned unspecified remaining edge cases without
      detail; not yet identified/reproduced, worth asking about
      specifically next session if they resurface rather than assuming
      this is fully closed out.
