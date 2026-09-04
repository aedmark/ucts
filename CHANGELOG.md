# Changelog

All notable changes to the Unresolved Childhood Trauma Simulator are documented here.

## [4.10.0] - 2026-09-04

### Added
- **Splash / title screen.** Shown once, before the first turn, covering the app until the player clicks Start — `config.splash: { title, intro }` in the content pack, editable from the editor's Config tab. Falls back to the game's own title and a generic prompt if a pack doesn't define one (or predates the field), so nothing regresses for existing packs. Only shows on the very first load — restarting a run, hitting a failure ending, or unlocking Extended Therapy doesn't bring it back.
- **Add/Subtract/Set effect inputs**, as an alternative to typing a signed number for a choice's stat effect. Each of a choice's three stat effects can now be a plain number (unchanged — every existing event still uses this) or `{ op: "add"|"subtract"|"set", value }`: Add/Subtract apply a magnitude in that direction, Set pins the stat to an exact 0–100 value regardless of what it currently is. All three resolve to an ordinary relative delta before mechanism mods or Extended Therapy's multiplier ever see it, so nothing downstream changed. The editor has a small `±`/`+`/`−`/`=` selector next to each stat input; switching modes converts just that one stat in place. This is additive only — no existing content was touched or needs to be.

## [4.9.0] - 2026-09-04

### Added
- **Every event now has its own wildcard.** The random fourth choice used to share one generic button ("??? Do something you can't predict.") and a pack-wide pool of five generic log lines across all 100 events — flat, and easy to miss the point of picking it. Each event now optionally defines its own `glitch: { text, log }`, written to react to that specific scenario rather than reading like a system message. All 100 shipped events got one. The editor has a new dashed-border "Wildcard" field on every event card for it.
- The `glitch` field is optional and per-event — a pack saved before this existed, or a newly added event nobody's written one for yet, falls back to the generic button text and the pack's `glitchLogs` pool exactly as before. The mechanic itself is unchanged: still a 15%-default chance, still fully randomized effects, still untagged (doesn't count toward coping mechanism unlocks).

## [4.8.0] - 2026-09-04

### Changed
- **Choice buttons no longer show a stat-direction hint.** Every choice used to carry a small "↑ Repression | ↓ Mask"-style line under the button text — a hidden magnitude, but a telegraphed direction. That's gone: picking a choice is now a genuine surprise, with only the button's own text (and, after the fact, the log line and the bars themselves) to go on. Also dropped the glitch choice's now-redundant "OUTCOME UNKNOWN" label, since that's true of every choice now, not just that one — it's still the only one whose effects are randomized fresh each time rather than authored.
- `README.md` and `AUTHORING.md` updated to match — both used to describe the ↑/↓ hint as part of how the game works, which is no longer true.

## [4.7.0] - 2026-09-03

### Added
- **Configurable stat bar names** (`config.statLabels`). The stats panel headings and the ↑/↓ choice hints ("↑ Repression", "↓ Mask") were literal strings in `index.html` and `engine.js`; they now read from the pack, with a new "Bar Display Names" row in the editor's Config section. Renaming a bar is cosmetic only — the underlying `repression`/`mask`/`child` keys, and everything wired to them (zone bias, ending conditions, mechanism mods), are unchanged. Optional field; a pack saved before this existed falls back to the original label text.
- **Configurable failure endings** (`failureEndings`). "Panic Attack", "Social Exile", and "Total Disassociation" — the title and description shown when a stat crosses its loss threshold — were hardcoded in `engine.js`, unlike survival endings, which have always been pack data. A heavily reskinned pack could reach the end of a run and still show unrelated default flavor text on a loss, which is also statistically the *most common* outcome for many packs. Each of the three now has an editable title/desc in a new "Failure Endings" section in the editor, between Coping Mechanisms and Survival Endings. Optional field, same backward-compatible fallback as above.

### Fixed
- **Editor event cards were unusable on narrow screens.** The title input shared one row with the zone dropdown and the Remove Event button, both fixed-width, so on mobile the title field was squeezed down to a sliver. The title now sits full-width on its own row above the zone/remove row.

### Changed
- `AUTHORING.md` documents both new fields (a "Failure Endings" section alongside "Survival Endings", a new Config table row for the bar names) and notes two things this pass surfaced while reviewing a user-submitted content pack: failure endings are worth writing deliberately since they often outnumber every survival ending combined, and a broad, low-specificity condition placed early in the Survival Endings list can silently starve narrower ones placed after it.

## [4.6.0] - 2026-09-02

### Changed
- **Contrast pass across both pages.** Several of the "quiet" text tiers introduced during the Tailwind removal — zone tags, captions, hints, timestamps, form labels, fine print, the Field Log's delete control, and both pages' danger-button text — measured below WCAG AA's 4.5:1 minimum against every dark background actually in use here (as low as 1.7:1 for the delete × and the danger buttons' resting red, worse than the CRT overlay makes any of this look in a screenshot). `#6b7280`, `#4b5563`, `#374151`-as-text, and `.editor-label`'s `#737373` are consolidated into `#9ca3af`, which measured 6.9–8.3:1 across `#000`/`#0a0a0a`/`#111`/`#0d0d0d`/`#161616`/`#1a1a1a`. Danger-button text (`Clear Log`, `Reset to Default`) moves from `#b91c1c` (2.7–3.3:1, failing even the large-text bar) to `#f87171` (6.3–7.6:1), which was already this app's hover-state red, so the palette didn't grow. Borders, backgrounds, and every already-legible tier (`#9ca3af`, `#d1d5db`, `#e5e7eb`, the stat colors) are untouched — this is a fix for the specific tiers that were actually failing, not a general brightening.

## [4.5.0] - 2026-09-02

### Fixed
- **Events could repeat within a single playthrough.** `pickWeightedEvent()` only ever excluded the *immediately previous* event from the draw pool, so nothing stopped the same event from resurfacing later in the same 10-turn run — just not back-to-back. The engine now tracks every event title already shown this playthrough and draws only from what's left, falling back to "anything but the last one shown" only once the whole pool is exhausted (relevant on a longer Extended Therapy run, where the fixed pack's events can run out before the turns do). Verified with 500 simulated 10-turn runs (zero repeats) and a 40-turn stress run past the pool size (zero back-to-back repeats once it starts reusing).

## [4.4.0] - 2026-09-02

### Changed
- **Tailwind is gone.** The CDN play-script (`<script src="https://cdn.tailwindcss.com">`) is removed from both `index.html` and `editor.html`, and every utility-class string — in the static markup and in the class strings `engine.js`, `field-log.js`, and `editor.js` build at runtime — is replaced with hand-authored, semantically named CSS in `css/style.css` and `css/editor.css`. The project's own pitch is "nothing leaves your browser, no build step"; a third-party CDN script silently pulling a full utility framework at runtime on every load never fit that, and doesn't now. Visual output is unchanged — the new CSS was written against Tailwind's actual palette values and breakpoints to match pixel-for-pixel, verified with a headless-browser pass across mobile and desktop viewports on both pages.
- `main.js`'s tab switching, `field-log.js`'s tag/zone picker state, and `engine.js`'s stat-bar/end-screen/glitch-button styling now toggle single semantic classes (`.active`, `.red-hot`/`.red-dim`, `.overlay-heading.win`/`.loss`, `.choice-btn.glitch`) instead of juggling three-to-five Tailwind classes per state change.
- Caught one real inconsistency along the way: the Inner Child bar's resting color was one shade off between the static markup (`pink-600`) and the engine's own dynamic update (`pink-700`). Standardized on `pink-700`, matching what the engine was already doing during play.

## [4.3.0] - 2026-09-02

### Changed
- **Mobile pass on `index.html`.** Every `md:`-gated text size was invisible on a phone \u2014 essentially all phones fall under Tailwind's 768px `md:` breakpoint, so text was permanently stuck at its smallest defined size regardless of screen. Base sizes are bumped across the board (stat labels, event text, choice buttons, Field Log) instead of relying on a breakpoint that never fired on the device most people are actually using. Fixed the same problem in the JS-templated event/choice markup in `engine.js`, which had its own hardcoded sizes the static HTML changes didn't reach.
- **Padding tightened on small screens.** Fixed `p-6`/`p-8` blocks (stats panel, gameplay area, help/end overlays) now scale down below the `sm:` breakpoint instead of eating the same fixed margin on a 375px screen as a desktop window.
- Header now wraps instead of squeezing; the "Objective: Survive N Turns" label hides below `sm:` since the turn counter already covers it and screen space is scarcer there.
- End screen's two buttons stack vertically on narrow screens instead of competing for width.
- `#field-note`'s textarea is `text-base` now instead of `text-sm` \u2014 iOS Safari auto-zooms the viewport on focusing any input under 16px, which was firing every time someone opened the Field Log to write an entry.
- `.mechanism-badge` bumped from a fixed 10px to 11px with slightly more padding.

## [4.2.0] - 2026-09-02

### Added
- **8 new events, 2 per zone** (28 total, up from 20): The Autocorrect Betrayal and The Performance Review Buzzword (Work); The Left-On-Read Text and The Different Voice on the Phone (Home); The Friend Who Remembers Everything and The RSVP You Regret (Social); The Unfinished Thing and The Accidental Self-Compliment (Self). Written through the QUEST mod chip's Room/Inventory framing \u2014 each scenario as a small, specific obstacle rather than a mood \u2014 while staying inside the existing schema and voice. Validated against `isValidContentPack`; no duplicate titles.

## [4.1.0] - 2026-09-02

### Added
- **A help panel (the "?" next to the title).** Explains the three stats, the turn loop, that a fourth unhinted option shows up sometimes, and what Extended Therapy is — without naming the five response tags or how Coping Mechanisms work mechanically. That stays something you find out by playing, on purpose.
- **Per-entry Field Log deletion.** Each logged entry now has a delete control next to its timestamp. The `id` generated for every entry since the Field Log shipped in 2.0.0 was previously write-only — nothing ever read it back. It does now.

### Changed
- **Field Log pattern reading no longer breaks ties silently.** `renderPattern()` used to sort tag counts and take the first result, which meant an exact tie was always resolved by object key order (`fawn` before `flight` before `fight`...), regardless of what you'd actually logged. Ties are now broken by recency — whichever response showed up most recently in the window wins — and if it's still a genuine tie, the reading says so by name instead of picking one silently.

### Fixed
- **Turn counter could briefly read past `maxTurns`** (e.g. `Turn: 11/10`) in the instant between the final choice resolving and the end screen covering it. Currently invisible in practice (`#end-screen` is opaque and fully covers the play area), but the counter now clamps to `maxTurns` at the display site itself, so it's correct independent of whatever the end-screen's CSS happens to be doing.

## [4.0.0] - 2026-09-01

### Changed
- **Split into discrete files.** The single 1760-line `index.html` is now `index.html` + `editor.html` + `css/style.css` + `css/editor.css` + `js/content.js` + `js/engine.js` + `js/field-log.js` + `js/main.js` + `js/editor.js`. `content.js` (the `DEFAULT_CONTENT` data and the storage layer) is the only code shared by both pages; the simulation engine and Field Log are game-only, and the editor logic is editor-only. Deploying or sharing this project now means copying the whole folder, not a single file.
- **The Editor is now its own page (`editor.html`), not a tab.** It no longer shares a JS runtime with the game — saving a pack writes to `localStorage` and shows a "▶ Play This Pack" link rather than restarting an in-page simulation. `index.html` picks up whatever pack is current on its own next load, automatically. It's also no longer linked from the game's nav — it's a dev-only tool now, reached by opening `editor.html` directly.
- Script loading uses plain `<script src>` tags in a fixed order, not ES modules — `type="module"` is blocked by CORS under `file://`, and the app needs to behave identically whether opened locally or hosted as static files (e.g. on Neocities).

### Fixed
- `getContent()` now documents (in `content.js`) that it returns `DEFAULT_CONTENT` by reference when no custom pack exists — callers must `deepClone()` before mutating it.
- The gameplay area could clip choices instead of showing them — `overflow-hidden` on the section holding the event text and choice buttons meant anything past the visible height was simply cut off, not scrollable. More choices per event (the editor now allows up to 5, plus the glitch choice) made this worse. Changed to `overflow-y-auto` so it scrolls instead.

## [3.0.0] - 2026-08-31

### Added
- **Content Engine** — the entire simulation (events, zones, coping mechanisms, glitch lines, survival endings, and every numeric knob) is now data in a single `DEFAULT_CONTENT` object, not hardcoded logic. A custom pack, once saved, fully overlays the defaults.
- **Editor tab** — a third mode alongside Simulation and Field Log. Write and edit events (zone, title, description, 2–5 choices each with response type, stat effects, and log line) through forms, no code required. Also edits zones (and their stat weighting), coping mechanism names/modifiers, survival endings (as reorderable condition rules: stat/operator/value), and every tunable number (starting stats, turn counts, glitch chance, unlock threshold, etc.).
- **Export / Import content packs** — download the active pack as JSON, or load one back in; a malformed or incomplete file is rejected with a clear message rather than corrupting the running game. Import loads into a review draft — nothing applies until you explicitly save.
- **Reset to Default** and **Discard Draft** controls, so experimenting in the editor is never a one-way door.

### Changed
- The five response identities (`fawn`/`flight`/`fight`/`freeze`/`secure`) remain fixed engine primitives — their display name and numeric effect are editable, their key identity is not, since both the simulation and the Field Log depend on it.
- Event rendering now assigns all user-authored text (titles, descriptions, choice text, field log entries) via `textContent` rather than HTML interpolation, since that text can now come from an imported file rather than only from code I wrote and reviewed.

### Fixed
- `getContent()` validates whatever it loads from storage and falls back to the default pack if it's missing required fields, so a corrupted or hand-edited save can't crash the simulation on boot.
- `pickWeightedEvent` no longer assumes the event pool has more than one entry — a content pack trimmed down to a single event no longer breaks turn progression.

## [2.0.0] - 2026-08-31

### Added
- **20 events across 4 zones** (Work / Home / Social / Self), replacing the original flat pool of 7.
- **Trauma-response tagging** — every choice is tagged `fawn`, `flight`, `fight`, `freeze`, or `secure`.
- **Coping Mechanisms** — leaning on the same response 3 times permanently unlocks a passive modifier on future choices of that type (bad responses compound worse, `secure` compounds better).
- **Card Shuffle glitch choice** — 15% chance per turn of a fourth, unlabeled option with fully randomized stat swings.
- **Adaptive event weighting** — event selection leans toward the zone tied to whichever stat is currently most endangered; no more back-to-back repeats.
- **Extended Therapy (New Game+)** — a 20-turn hard mode, unlocked permanently after a first survival, with all stat swings scaled 1.25x.
- **Five survival endings** (Powder Keg, Performer, Radically Undone, Fragile Equilibrium, Functional Enough) computed from final stat shape, replacing the single generic "You Survived."
- **Field Log** — a second, real-world mode alongside the fictional simulation. Log an actual moment, tag it with the same response vocabulary, and build a persistent local history. Includes a 7-day pattern view, a plain-language dominant-response reading, text export, and a confirm-gated clear. Entries never leave the browser — no backend, no network calls.

### Fixed
- Extended Therapy's unlock flag and the Field Log's storage both now degrade gracefully when `localStorage` is unavailable (e.g. sandboxed/embedded contexts), instead of throwing and silently breaking the win screen or the log.

## [1.0.0] - 2026-08-16
- Initial release: single-file browser game. Three stats (Repression, Social Mask, Inner Child), 7 events, 10-turn survival loop, CRT terminal aesthetic.
