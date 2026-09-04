# Writing Your Own Content

New here? [README.md](README.md) explains what the game actually does — the stats, the zones, how coping mechanisms unlock, how endings resolve — from a player's side. This doc assumes you already know that and want to change it.

The simulator's events, response types, zones, and endings exist in a single data structure called a **content pack**, and [editor.html](editor.html) is the tool for writing one without touching JavaScript. This doc explains what each part of a pack does and walks through writing new content with it.

If you'd rather hand-edit the underlying JSON in a text editor, skip to [Editing the raw JSON instead](#editing-the-raw-json-instead) — the schema is the same either way.

## How a pack gets from the editor into the game

`editor.html` and `index.html` are separate pages that don't share memory — they only share `localStorage`. When you click **Save All Changes** in the editor, it writes your whole pack to `localStorage` under one key and shows a **▶ Play This Pack** link. `index.html` doesn't know or care that you were just editing — it just reads whatever pack is currently saved, every time it loads. So the loop is: edit → Save → open (or reload) the simulator → you're playing your version.

Nothing ever leaves your browser. There's no server, no account, no sync.

A saved pack **fully replaces** the built-in default — it's not a diff. That's why the editor always shows every event, not just the ones you've touched: whatever's in the editor when you hit Save is the entire game from then on.

## Opening the editor

Open `editor.html` directly, or follow the "Content Editor" link in the simulator's footer. Whatever you had saved last time is still there; if you've never saved anything, it starts from the built-in default content so you're editing existing material rather than a blank page.

## The six sections

### Config

Global numbers that shape the whole run, not any one event:

| Field | What it does |
|---|---|
| Starting Repression / Mask / Inner Child | Your three stats at turn 1. Same defaults every restart. |
| Base Max Turns | How many turns until a standard run ends in a survival ending. |
| Extended Therapy Turns | Turn count for the harder mode unlocked after your first survival. |
| Extended Therapy Multiplier | Every stat swing (including mechanism mods) is multiplied by this in Extended Therapy. `1.25` means 25% harder in both directions. |
| Mechanism Unlock Threshold | How many times you have to pick the *same* response type before it "locks in" as a coping mechanism. Default `3`. |
| Glitch Chance (0-1) | Odds, each turn, of a bonus fourth choice with fully random effects. `0.15` = 15%. |
| Weak Zone Weight | How much more likely an event is to be picked when its zone matches your worst stat. `2.5` means 2.5x the normal odds. `1` would turn this off entirely (pure random selection). |
| Repression / Mask / Inner Child Bar Name | What the stats panel calls each stat. Purely cosmetic — the underlying `repression`/`mask`/`child` keys used everywhere else (zone bias, ending conditions, mechanism mods) don't change, so renaming a bar doesn't rewire what it tracks. |
| Splash Title / Splash Intro | Shown once, before the first turn, covering the whole app until the player clicks Start. Leave either blank and it falls back to the game's own title and a generic prompt rather than showing nothing. A blank line in the intro starts a new paragraph. Only shown on first load — restarting a run or unlocking Extended Therapy doesn't bring it back. |

### Zones

A zone is just a category — `WORK`, `HOME`, `SOCIAL`, `SELF` by default — with one property that matters mechanically: **Stat Bias**. It says which of the three stats that zone is "about." The game uses this to lean the random event selection toward whatever's currently your most dangerous stat — if your Repression is climbing, `WORK`-zone events (biased toward repression) start showing up more often, because that's a reasonable proxy for "the thing your nervous system is currently worried about."

You can add a zone anytime; you can't remove one that's still used by an event (reassign or delete those events first).

### Coping Mechanisms

The five response identities — `fawn`, `flight`, `fight`, `freeze`, `secure` — are the one part of the system you can't rename or add to. Every choice in every event is tagged with one of these five, and the Field Log's own tagging depends on that exact set existing. What *is* yours to change: each one's **display name** (what shows up in the "Coping Mechanisms Acquired" badge and in ending text) and its **mod** — the passive effect that kicks in on every future choice of that type, once unlocked.

Here's the mechanic in full: every time you pick a choice tagged, say, `fawn`, a per-playthrough counter for `fawn` goes up. Hit the Mechanism Unlock Threshold (default 3) and `fawn` becomes permanently unlocked for the rest of that run. From then on, every `fawn`-tagged choice you pick also applies `fawn`'s mod on top of its own written-in effects. The defaults are built so that leaning on an avoidant/defensive response makes it *quietly worse over time* (e.g. `freeze`'s default mod is `{rep: 8, mask: 0, child: -5}` — more repression buildup, more cost to your inner child, every single time), while leaning on `secure` makes it *quietly better* (`{rep: -5, mask: 0, child: 5}` — extra relief, extra healing). That asymmetry is what makes repeatedly picking the "easy" option a real cost, and what makes the plain, undramatic option worth choosing on purpose.

If you retune the mods, that asymmetry is the thing to preserve or deliberately break — a mechanism whose mod helps rather than compounds a cost stops meaning anything as a mechanic.

One caveat: the Field Log's own tag picker (in `index.html`) shows the raw identity — `fawn`, `flight`, etc. — not your renamed display name. Renaming only changes what shows up inside the simulation itself (badges, ending text).

### Failure Endings

These are the three ways to *lose* — repression hitting 100%, mask hitting 0%, or inner child hitting 0%. Unlike survival endings, there's no condition list: each stat has exactly one, and it fires the instant that stat crosses its threshold, whichever turn that happens to be. Each has a **title** and a **desc**, same as a survival ending.

These are easy to forget about because they're not "endings" in the same list as the rest, but statistically they're often the *most common* outcome, especially in a pack that pushes stats hard — so if your event effects skew heavy in one direction, more players will see one of these three than any survival ending you wrote. Give them the same voice as the rest of your pack; leaving them at the defaults means a reskinned pack still ends with unrelated flavor text on most losses.

### Survival Endings

These decide what a player sees if they make it to the end of a run without losing. They're evaluated **top to bottom**, and the first ending whose conditions are *all* true wins — so put your most specific endings first and your catch-all last. An ending with **no conditions at all** always matches, which is what makes it a fallback. Keep exactly one of those at the very bottom.

A condition is `stat` (repression / mask / child) + `op` (`>=`, `<=`, `>`, `<`, `==`) + `value`. An ending can have several conditions — they're all ANDed together.

Don't restate "you unlocked these coping mechanisms" in your `desc` text — the game already appends that list automatically to whatever description you write, if the player unlocked any.

A broad, easily-satisfied condition placed early (e.g. a single `repression >= 70`) will out-compete narrower, more specific endings placed after it, since evaluation stops at the first match — if your richer endings never seem to show up, check whether something earlier in the list is catching runs that were meant to reach them.

### Events

This is the actual content — the scenarios players click through. Each event has:
- **Zone** — which category it belongs to (must be one of your defined zones).
- **Title** and **Description** — the trigger and its setup text.
- **2 to 5 choices** — see below.
- **Wildcard** (optional) — the event's own version of the 15%-chance fourth choice. See below.

Each choice has:
- **Text** — the button label the player sees and clicks.
- **Response** — which of the five mechanism tags this choice represents.
- **Rep / Mask / Child** — the raw stat effect (can be positive, negative, or zero on each). These are *entirely hidden* — the player sees no arrow, no number, no direction of any kind before picking, only the button text and whatever the log line says afterward. The exact value, and how honestly the button text foreshadows it, is entirely your call as the author.

  Each stat's effect is one of three named operations, picked via the small mode selector (`+` / `−` / `=`) next to each number: **Add** a magnitude, **Subtract** a magnitude, or **Set** the stat to an exact value (0–100) regardless of what it currently is. All three resolve to the same kind of relative nudge under the hood — mechanism mods and Extended Therapy's multiplier stack on top exactly the same way regardless of which one wrote the base effect. This is the only form the editor writes or displays; every built-in event uses it.

  A plain signed number (e.g. `-15`) is still accepted wherever a pack gets loaded — hand-edited JSON, or a pack exported before this existed — so nothing breaks on import. The editor just normalizes it into Add/Subtract the instant that choice's card renders, so you'll never see a stray fourth "legacy" mode sitting next to the other three.
- **Log line** — what appears in the action log footer when this choice is picked, e.g. *"You debased yourself for a misplaced comma."*

### The Wildcard

Every event carries its own version of the random fourth choice (the glitch), rather than sharing one generic "??? Do something you can't predict" button across the whole pack. It's two fields — **Button Text** and **Log Line** — and both are optional per event: leave either blank and it falls back to the generic button text or a random line from the pack-wide `glitchLogs` pool, so a pack written before this existed (or a new event you haven't gotten to yet) still works.

The wildcard should read differently from the event's other three choices — those are the grounded, specific-but-plausible options; the wildcard is where the simulation stops pretending to be realistic and gets deliberately unhinged. Its effects are still fully random every time regardless of what the button says (the text doesn't telegraph the outcome any more than the other choices do), so write it for the joke, not as a fourth balanced option.

## Walkthrough: writing one event start to finish

1. Scroll to **Events**, click **+ Add Event**. A blank card appears with two starter choices.
2. Set **Zone** to whichever category fits (or add a new zone first, if you want a new category — e.g. a `SCHOOL` zone biased toward `mask`).
3. Write the **Title** (short, a few words — it's shown as a section header) and **Description** (the actual scenario, in second person, present or recent-past tense to match the existing voice).
4. For each choice: write the button text, pick a response tag that honestly matches what the choice represents (a boundary-setting choice is probably `secure` or `fight`, not `fawn`), set the three effect numbers, and write a log line that reads naturally after "`> Turn N:`".
5. Use **+ Add Choice** / **Remove** to land between 2 and 5 choices. Three is the sweet spot used throughout the default content — enough for a real branch, not so many the player is reading a menu.
6. Click **Save All Changes**, then **▶ Play This Pack** to see it in the actual game. Your new event enters the same random rotation as everything else immediately.

For calibration, existing choice effects mostly land in the ±5 to ±20 range per stat, with a few intentional outliers up to ±30 for higher-stakes events. Numbers that large everywhere flatten the game into noise; keep most choices modest and save the big swings for the events that should feel like a big swing.

## Save / Export / Import / Reset

- **Save All Changes** — writes your edits to `localStorage`. Nothing is live until you do this; you can navigate away and your unsaved edits are gone (there's no autosave by design, so a half-finished edit can't accidentally overwrite a working pack).
- **Export Pack** — downloads your current draft (saved or not) as a `.json` file. Use this to back up your work outside the browser, or to hand a pack to someone else.
- **Import Pack** — loads a `.json` file into the editor as a draft for review. It does **not** save automatically — check it over, then hit Save All Changes yourself. A file that's missing required fields is rejected with an error rather than silently breaking anything.
- **Discard Draft** — throws away unsaved changes and reloads whatever's currently saved.
- **Reset to Default** — deletes your custom pack entirely and reverts to the built-in content. Asks for confirmation first; there's no undo after that.

## Editing the raw JSON instead

Export a pack to see the exact shape. The top level is:

```json
{
  "config": { "startingStats": { "repression": 20, "mask": 100, "child": 50 }, "statLabels": { "repression": "Repression Level", "mask": "Social Mask", "child": "Inner Child" }, "splash": { "title": "U.C.T. Simulator", "intro": "Shown once before the run starts.\n\nBlank lines start new paragraphs." }, "maxTurns": 10, "hardModeTurns": 20, "hardModeMultiplier": 1.25, "unlockThreshold": 3, "glitchChance": 0.15, "weakZoneWeight": 2.5 },
  "zones": [ { "key": "WORK", "statBias": "repression" } ],
  "mechanisms": {
    "fawn":   { "name": "The Approval Loop", "mod": { "rep": 0,  "mask": 3,  "child": -5 } },
    "flight": { "name": "...", "mod": { "rep": 0, "mask": 0, "child": 0 } },
    "fight":  { "name": "...", "mod": { "rep": 0, "mask": 0, "child": 0 } },
    "freeze": { "name": "...", "mod": { "rep": 0, "mask": 0, "child": 0 } },
    "secure": { "name": "...", "mod": { "rep": 0, "mask": 0, "child": 0 } }
  },
  "glitchLogs": [ "A fallback line for the wildcard choice, used when an event doesn't define its own." ],
  "failureEndings": {
    "repression": { "title": "Panic Attack", "desc": "Shown when repression hits 100." },
    "mask": { "title": "Social Exile", "desc": "Shown when mask hits 0." },
    "child": { "title": "Total Disassociation", "desc": "Shown when child hits 0." }
  },
  "endings": [
    { "title": "...", "desc": "...", "conditions": [ { "stat": "repression", "op": ">=", "value": 70 } ] }
  ],
  "events": [
    {
      "zone": "WORK",
      "title": "The Typo",
      "desc": "...",
      "choices": [
        { "text": "...", "tag": "fawn", "effects": { "rep": { "op": "subtract", "value": 5 }, "mask": { "op": "add", "value": 10 }, "child": { "op": "subtract", "value": 15 } }, "log": "..." },
        { "text": "A plain number still works too — same result as { \"op\": \"subtract\", \"value\": 20 } — for hand-edited JSON or a pack exported before this existed.", "tag": "secure", "effects": { "rep": -20, "mask": { "op": "set", "value": 50 }, "child": 5 }, "log": "..." }
      ],
      "glitch": { "text": "The wildcard's button text.", "log": "What the action log says when it fires." }
    }
  ]
}
```

Requirements the importer actually checks: `config` is an object; `zones` is a non-empty array; `mechanisms` has all five keys (`fawn`/`flight`/`fight`/`freeze`/`secure`) present; `glitchLogs` is an array; `endings` is a non-empty array; `events` is a non-empty array. It doesn't deep-validate every field inside each event or choice, so a malformed individual event won't necessarily be caught at import — it'll just render oddly (missing text shows as `...`, missing effects default to `0`). When in doubt, edit through the UI, which can't produce a malformed shape in the first place.

`failureEndings`, `config.statLabels`, `config.splash`, and each event's `glitch` are all optional — a pack from before these existed imports fine and just falls back to the built-in defaults (or the pack-wide `glitchLogs` pool, for `glitch`) for whichever it's missing. A plain number is likewise still accepted for any choice's per-stat effect — the editor writes and displays the `{ "op", "value" }` form exclusively now, but importing an older pack that still uses plain numbers works fine; that choice's numbers just get normalized into Add/Subtract the moment its card is opened in the editor.

## Design notes

- **Second person, present or just-past tense.** Every existing event is written as something that just happened *to the player* — not a hypothetical, not addressed to a character.
- **The comedy is in the specificity, not the punchline.** "Someone is standing exactly in front of the specific brand of oat milk you need" does more work than a generic "you're waiting in line and it's annoying."
- **A choice's tag should be honest, not convenient.** If a choice reads as clearly avoidant, tag it `flight` or `freeze` even if you'd rather it read as `secure` — the mechanism system depends on tags meaning what they say, and mistagging quietly breaks the pattern-tracking that makes coping mechanisms feel earned.
- **Every event doesn't need a `secure` option**, but most should have one. It's the only response type whose unlock is a reward rather than a cost, and it's the throughline that makes the whole system feel like it's tracking something real rather than just spending points.
