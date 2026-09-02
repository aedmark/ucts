# Changelog

All notable changes to the Unresolved Childhood Trauma Simulator are documented here.

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
