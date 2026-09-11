**You're having a bad day. Nothing dramatic; a typo in an email to your boss, a sigh from the next cubicle, and your reflection in the CRT monitor not looking quite like you for a second. You'll get ten of these before the shift ends. How you handle each one is the game.**

The *Trauma Response Simulator* started life as a browser game pretending to be an eighties computer terminal. Now, it isn't pretending anymore: this is an authentic, genuine **Sierra On-Line SCI0 adventure engine** release, compiled to run in real 16-bit MS-DOS.

No web frameworks. No modern browser tricks. Just 320x200 16-color EGA graphics, classic Sierra serif typography, drop-shadow dialog windows, and the iconic top status bar quietly tracking your breakdown:

`Repression: 40   Mask: 60   Child: 60`

---

### The Setup

Every turn, a small, absurdly specific bad moment fires from your day. Every button you click is secretly tagged as one of five trauma responses: **fawn**, **flight**, **fight**, **freeze**, or **secure**.

You never see the tag. You just see the button text, an expressive character portrait embedded in the dialog window watching you make bad decisions, and three stat bars moving quietly behind the scenes.

Lean on the same response three times in a single shift, and it locks in as a permanent **Coping Mechanism** — a passive modifier stacked onto every future choice of that type for the rest of your run. 

Four of the five compound something you probably don't want more of (freeze, for instance, adds even more Repression buildup and costs more of your Inner Child every time you freeze again). The fifth — *secure* — compounds something you actually do want: less Repression, more Inner Child.

Figuring out which one is which, and whether you can actually bring yourself to pick the healthy option when your nervous system is on fire, is the whole game.

---

### What's in the Box (DOS Edition)

- **All 196 Hand-Written Scenarios across Six Zones:** Full coverage of Work, Home, Social, Self, Body, and Public. Event selection isn't uniformly random: the interpreter quietly steers toward whichever zone matches your worst current stat. If your Repression is spiking, Work events show up more. The engine knows what you're worried about.
- **Dynamic In-Dialog Mood Portrait:** A period-accurate Sierra character portrait embedded right inside the event dialog. Watch your face visibly shift and deteriorate across four emotional states in real-time as your stats crack under pressure.
- **The Glitch (15% Wildcard):** A 15% chance per turn of a fourth, unscripted choice written specifically for that scenario. While normal choices have hidden effects, the glitch is hidden from the game itself: fully randomized stat swings on the spot. Sometimes you really aren't in control.
- **Twelve Distinct Endings:** 
  - **9 Survival Endings:** Hold it together through turn 10 and the engine evaluates your final psychological posture (*The Powder Keg*, *The Performer*, *Radically Undone*, *Raw Nerve*, *Coasting on Empty*, *Fragile Equilibrium*, *Actually Okay*, *The Long Fuse*, or *Functional Enough*).
  - **3 Failure Breakdowns:** Let Repression hit 100% (*Panic Attack*), Social Mask drop to 0% (*Social Exile*), or Inner Child bottom out at 0% (*Total Disassociation*).
- **Persistent DOS Case Files (`TRSCASE.DAT`):** A genuine disk-backed archive file saved directly to your DOS directory. Every ending and coping mechanism you uncover flips from sealed to permanently readable across playthroughs. Browse your unlocked archive anytime via an authentic Sierra scrolling list (`DSelector`) from the menu bar (`Ctrl+F`).
- **Interactive Office Scenery:** When your shift ends, you aren't dumped back to a modern title screen. You're left standing in your EGA cubicle. Click the 4-drawer metal filing cabinet to open your Case Files archive, or click the CRT terminal on your desk to boot straight into another shift.
- **Extended Therapy (New Game+):** Survive a standard 10-turn shift once, and Extended Therapy unlocks permanently: a 20-turn gauntlet with all stat swings scaled up by 1.25x. Your nervous system has been here before; it doesn't get an easier shift.
- **Period Audio Driver Support:** Plays through PC Speaker, Tandy 3-voice, AdLib / Sound Blaster, or Roland MT-32 / General MIDI via Sierra's original sound drivers.

---

### System Requirements & Running the Game

Because this is a real SCI0 game built with `SCIV.EXE`, you can run it almost anywhere DOS lives:

#### On Modern Machines (Windows / macOS / Linux):
Run via **DOSBox**, **DOSBox-Staging**, or **DOSBox-X**:
```text
mount c /path/to/TRS_SCI
c:
sciv.exe
```
*(Or simply drag and drop `SCIV.EXE` onto your DOSBox executable.)*

#### On Real Vintage Hardware:
- **CPU:** IBM PC/XT, 80286, 80386, 80486, or Pentium
- **RAM:** 640 KB conventional memory
- **Graphics:** EGA or VGA (320x200, 16 colors)
- **OS:** MS-DOS 3.3 or higher, PC-DOS, or FreeDOS
- **Storage:** Fits comfortably on a single 720KB or 1.44MB 3.5" floppy disk

#### Controls:
- **Mouse:** Click any choice button, click menu items, or click the office filing cabinet and computer terminal.
- **Keyboard:** Use `Tab` or `Arrow Keys` to cycle buttons, `Enter` or `Space` to select, and standard Sierra hotkeys (`Ctrl+F` for Case Files, `Ctrl+Q` to quit, `Ctrl+P` to pause).

---

### Content Note

This is dark comedy about coping mechanisms and modern anxiety, not a clinical instrument. It references panic, dissociation, corporate alienation, and substance use in passing, all played for the specific-not-generic kind of laugh. 

If you are experiencing real distress or a crisis in your real life, please contact a local crisis hotline or a licensed professional instead of an MS-DOS prompt.
