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
4. Native SCI0 save/restore for in-progress runs, and a QFG-style external
   file (via the template's existing `fileio.sc` `File` class, which wraps
   `FOpen`/`FGets`/`FPuts`/`FClose`) for Case-Files-style cross-run unlock
   persistence.
5. Actual art: an EGA background pic for the "terminal" room, a player
   portrait view, stat-gauge views (the template's `gauge.sc` has a working
   percentage-meter class already, unused so far).
