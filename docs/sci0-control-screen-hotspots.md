# Arbitrary-shaped hotspots in SCI0 (the control screen, not "Polygons")

How to make a clickable (or walk-into-able) region that isn't just a
hand-typed rectangle in source — for when the cabinet/computer's
`CABINET_X1/Y1/X2/Y2`-style constants in `game.sh` aren't precise enough
(irregular silhouettes, overlapping objects, art that's likely to move).

Written after building the filing-cabinet and computer hotspots in
`rm002.sc` the simple way (hardcoded rectangles). This is the "proper"
SCI0 alternative, verified against SCI Companion's own bundled kernel docs
and this project's actual stock template code — not general SCI folklore.

## Don't look for "Polygons" — that's SCI1.1 only

SCI Companion's Pic Editor has a real Polygon tool, and it's tempting to
reach for it. **Don't** — confirmed directly from SCI Companion's own docs
(`Help/_sources/polygons.txt`, `Help/_sources/pics.txt`): the Polygon
tool, the `.shp` polygon files, `CreateNewPolygon`, `AddPolygonsToRoom`,
and `onMeCheck`/`omcPOLYGON` are all explicitly **SCI1.1 only**. This
project targets SCI0. That machinery doesn't exist for us.

The SCI0-native equivalent is older and different: the **control screen**.

## The three screens (quick recap)

Every SCI0 pic resource is actually three separate vector drawings, all
drawn with the same pen/fill/line tools, switchable in the Pic Editor with
the "V"/"P"/"C" toolbar buttons or the F2/F3/F4 keys:

- **Visual** — what the player actually sees.
- **Priority** — depth layering (what's in front of/behind ego).
- **Control** — invisible to the player; a hidden "meaning" layer used to
  trigger gameplay behavior. This is what we want.

None of this needs a separate resource or export step — control-screen
data is baked into the same pic resource as the visual art. Draw it,
compile, done.

## Step 1: draw the hotspot's shape on the control screen

1. Open the room's pic in the Pic Editor.
2. Switch to the **Control** screen (the "C" button, or press **F4**).
3. Pick a control color. SCI0/SCI1 convention reserves **`ctlWHITE`**
   (value 15, i.e. `$8000` as a bitmask — see below) for "ego can't walk
   here." **Don't use white for a click-only trigger** — pick any other
   color, e.g. `ctlRED`.
4. Trace the object's outline with the **Line** tool (or paint it directly
   with **Pen** for something more organic/curved — unlike the visual
   screen, the shape can be anything, not just a rectangle).
5. **Fill** the traced outline solid with that color.
   - Caveat, straight from SCI Companion's own docs: the Fill tool only
     fills *previously blank* areas. If a fill does nothing, the outline
     probably isn't fully closed, or you're re-filling something already
     painted.
6. To check alignment against the real art while drawing: the Pic Editor's
   "Pic Helper" pane (right side) has Zoom/Final views that can each be
   set to follow Visual, Priority, or Control independently of whatever
   the main view is showing — so you can draw on Control in the main view
   while a helper pane still shows Visual for reference. There's also the
   "fake ego" tool if you need to size things relative to the character.

## Step 2: verify it lines up, in-game, before writing code

This project already has a debug hotkey for exactly this — no new code
needed. `Main.sc`'s `Template:handleEvent` (the stock ALT-key debug block)
already has:

```
(case $2e00 // alt-c
   Show(4) // Show control
)
```

Boot the game (DOSBox-X or SCI Companion's playtest), get to the room, and
press **Alt+C** to overlay the live control screen on top of the visual
art. (Alt+V shows visual, Alt+P shows priority — same family.) Confirm
your painted shape actually covers the object before wiring up code
against it.

## Step 3: check it in code

`OnControl` is the kernel call. Two ways to use it, both real and already
present in this project's own compiled template:

**A. Raw kernel call** (matches SCI Companion's own documented example,
`Help/Kernels/OnControl.html`):

```
(if(== (OnControl ocSPECIAL (send pEvent:x) (send pEvent:y)) ctlRED)
    ; clicked inside the hotspot
)
```

**B. The stock template's own built-in helper** — `Feature.sc` (already
part of this project, already compiled, currently unused but fully wired)
defines `View:onControl(fUSE_POINT)`, inherited by `Prop`/`Act`:

```
(send someProp:onControl(TRUE))   ; checks the Prop's own (x, y) point
(send someProp:onControl())       ; checks its full bounding box instead
```

Reach for B if the hotspot is (or should become) a real `Prop`/`Act`
instance — something that might also animate, cycle, or need other View
machinery — rather than a plain background painted directly into the
room's own pic. For a purely static "click this part of the background"
trigger, A is simpler and is exactly what the current cabinet/computer
hotspots would become if ported to this approach.

### Important: `OnControl` returns a bitmask, not a plain color index

Confirmed via `sci.sh`: each of the 16 EGA control colors is a distinct
bit —

```
(define ctlBLACK   $0001)
(define ctlNAVY    $0002)
...
(define ctlRED     $1000)
...
(define ctlWHITE   $8000)
```

For a **point** query (`OnControl ocSPECIAL x y`), compare the result with
`==` against one `ctlXXX` constant, as above. For a **rectangle** query
(`OnControl ocSPECIAL left top width height`), more than one color can be
present at once, so use bitwise `&` instead of `==`:

```
(if(& (OnControl ocSPECIAL 50 20 100 40) ctlBLUE)
    ; at least one pixel in that rect is ctlBLUE
)
```

### Naming trap: there are two unrelated `ctlXXX` families

`sci.sh` also defines `ctlBUTTON`/`ctlTEXT`/`ctlEDIT`/`ctlICON`/
`ctlSELECTOR` (values 1, 2, 3, 4, 6) — these are **dialog control types**
for `DButton`/`DText`/etc. in `Controls.sc`, completely unrelated to the
pic control-screen colors above (`ctlBLACK`..`ctlWHITE`, `$0001`-`$8000`).
Same prefix, two different meanings. Don't let autocomplete fool you.

## Worked example: the cabinet, this way instead

For comparison, here's what today's rectangle-based cabinet check
(`rm002.sc`) would look like ported to a control-screen hotspot — not
applied to the actual code, just illustrative:

```
(if(not (send pEvent:claimed))
    (if(== (send pEvent:type) evMOUSEBUTTON)
        (if(== (OnControl ocSPECIAL (send pEvent:x) (send pEvent:y)) ctlRED)
            (send pEvent:claimed(TRUE))
            ShowCaseFiles()
        )
    )
)
```

No `CABINET_X1/Y1/X2/Y2` constants at all — the painted shape *is* the
hit-test data.

## When this is actually worth the extra step

The rectangle-in-`game.sh` approach used for the cabinet and computer has
real advantages: zero VM/GUI round-trips, fast to iterate purely on the
Linux side, and it worked correctly on the first try for both objects.
Reach for the control screen instead when:

- The object's silhouette is genuinely irregular (a chair, a curved lamp,
  anything a rectangle would over- or under-cover at the corners).
- Two hotspots are close together or overlapping and a rectangle for one
  would false-positive into the other.
- You want to draw the hit region visually, right on top of the real art,
  instead of estimating pixel numbers from a screenshot.

**It doesn't solve everything the rectangle approach has to live with** —
if the visual art moves later, the control-screen shape goes stale exactly
the same way a hardcoded rectangle would; you're just repainting a shape
in the Pic Editor instead of editing four numbers in `game.sh`. Same
underlying tradeoff, different place to fix it.

## Bonus: this isn't just for clicks

`OnControl` (and `View:onControl`) works anywhere you have an (x, y) —
including a room's own `doit()` checking ego's or a Prop's *own* position
each cycle, not just a mouse event's coordinates. That's the SCI0 way to
do "trigger something when the player walks into a marked area," as
distinct from `CanBeHere()`/`Avoid.sc` (which this project's stock
template already uses for movement *legality*, not custom triggers).
Not needed anywhere in this project yet — flagging it since a future room
with actual ego movement (rather than rm001/rm002's hidden,
`ProgramControl()`-locked ego) would likely want it.
