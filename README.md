# Unresolved Childhood Trauma Simulator

A turn-based browser toy about the five ways people actually cope with a bad moment — dressed up as a CRT-terminal therapy simulator. Every event is a small, absurdly specific bad moment (a typo in an email to your boss, a sigh from the next room, your own reflection not looking like you for a second), and every choice you make is quietly one of five trauma responses, whether you meant it to be or not.

Nothing about this leaves your browser. No server, no account, no analytics, no build step.

## Running It

Open `index.html`. That's the whole install. It works the same as a local `file://` file or hosted as static files anywhere (Neocities, GitHub Pages, wherever) — there's no server-side anything to set up.

A title screen shows once before the first turn — its text is pack data too, so a custom content pack can replace it with its own name and a proper introduction instead of the default.

## The Loop

You start with three stats and a turn limit. Each turn, an event fires — a short scenario — and you pick one of two to five responses. Each response nudges the three stats up or down by an amount you never see — no hint, no preview, just the button text — and writes a line to the action log once you've committed to it. Do this until you either bottom out a stat or survive to the turn limit.

## The Three Stats

| Stat | Starts at | Loses the run at |
|---|---|---|
| **Repression** | 20% | 100% (*Panic Attack*) |
| **Social Mask** | 100% | 0% (*Social Exile*) |
| **Inner Child** | 50% | 0% (*Total Disassociation*) |

The names above — the bar labels and the three failure endings — are the built-in defaults; both are content-pack data, so a custom pack can rename the bars and rewrite what a loss says without touching what's actually being tracked underneath.

Survive every turn without any of the three failing, and you get a **Survival Ending** instead — one of five, chosen by evaluating your final stat shape against a list of conditions, top to bottom, first match wins. There's always a no-conditions catch-all at the bottom, so a run always ends in *something*.

## The Five Responses

Every choice you click is secretly tagged as one of five trauma responses — `fawn`, `flight`, `fight`, `freeze`, `secure`. You never see the tag while picking, and you never see what it does to your stats either — you just see the button text, and find out the rest by watching the bars move after you've already chosen.

Lean on the same response three times in one run and it permanently unlocks as a **Coping Mechanism**. From then on, every choice of that type also carries a passive modifier stacked on top of its own written-in effect, for the rest of the run. Four of the five compound something you probably don't want more of — `freeze`'s default modifier, for instance, is more Repression buildup and more cost to your Inner Child, every single time you freeze again. The fifth, `secure`, compounds something you do want: less Repression, more Inner Child, every time.

That asymmetry is the actual mechanic underneath the mechanic. Repeating the easy, avoidant option has a real and growing cost. The plain, undramatic option is the one worth choosing on purpose, not the one that happens to feel safest in the moment.

## Zones

Every event belongs to a zone — `WORK`, `HOME`, `SOCIAL`, `SELF` by default — and every zone leans toward one of the three stats. Event selection isn't uniformly random: it leans toward whichever zone matches your current worst stat, so if your Repression is climbing, `WORK`-zone events start showing up more. The game is quietly steering toward whatever your nervous system is currently most worried about.

## The Glitch

Each turn carries a small chance (15% by default) of a fourth choice — a wildcard, written per event so it actually reacts to what's happening rather than reading like a system message. Every choice's effects are already hidden from you; the glitch's are hidden from the game too: fully randomized on the spot, untagged, and never repeated the same way twice. It's the one moment per run where nobody, including whoever wrote the content pack, knows what's about to happen.

## Extended Therapy

Survive a standard run once and a harder New Game+ mode unlocks permanently: double the turns, and every stat swing — including mechanism modifiers — scaled up. Your nervous system has been here before; it doesn't get an easier version of the same problem.

## Run Seeds

Every run has a seed — a short string that drives every random draw made during it (which events show up, the wildcard's roll, a timed-choice timeout) through a seeded generator instead of raw randomness. Leave the splash screen's seed field blank for a fresh random one each time, or set it yourself: type a shared seed to compare runs with someone else, or hit the 📅 button for that day's challenge seed, the same for everyone who plays it that day. The end screen shows whatever seed a run used and offers a **Replay Seed** button to run it back identically, next to the normal (freshly seeded) restart.

## Timed Events

An opt-in toggle on the splash screen. On, each turn has a chance of putting a countdown under the event text — the same hidden-effects choices as always, just under a clock. Let it run out and the game picks one of that event's choices for you at random, logged as a `[FROZE]` line and always counted toward the `freeze` coping mechanism, whatever that choice actually did to your stats. Off by default; your last choice is remembered for next time.

## The Field Log

A second mode, separate from the simulation: a real, persistent journal that uses the same five-word vocabulary. Log an actual moment from your actual life, tag it with whichever response fits closest, and build a local history — a rolling 7-day pattern reading, a plain-language read on your most common response, text export, and a confirm-gated way to delete a single entry or clear everything.

This one matters enough to say plainly, in the app and here: **this is a self-tracking tool, not therapy.** If you're in crisis, contact a crisis line or a licensed professional — not a browser tab.

## Writing Your Own Content

Every event, ending (survival *and* failure), zone, stat bar name, and numeric knob in the game lives in one exportable/importable JSON structure called a content pack. [`editor.html`](editor.html) is a full visual editor for it — no code required — and [`AUTHORING.md`](AUTHORING.md) is the complete guide: what each field does, a start-to-finish walkthrough of writing a new event, the raw JSON schema if you'd rather hand-edit, and the design notes that keep new content feeling like it belongs next to everything already there.

## Version History

See [`CHANGELOG.md`](CHANGELOG.md) for what changed and when, release by release.

## License

MIT. See [`LICENSE`](LICENSE).
