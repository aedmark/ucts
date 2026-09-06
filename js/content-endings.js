// ============================================================
// ENDINGS — the three ways to lose (failureEndings, one pool of variants
// per stat, picked at random each time) and the ways to survive (endings,
// evaluated top to bottom — first match wins).
// Loaded before content.js, which assembles DEFAULT_CONTENT from this.
// ============================================================

// Each stat has a pool of variants instead of one fixed ending, picked at
// random when that stat crosses its threshold — so repeated runs (arcade
// especially, which can end this way many times in a sitting) don't show
// the identical line every single time. A legacy pack with a single
// {title, desc} object per stat instead of an array still works — see
// failureEndings() in engine.js.
const CONTENT_FAILURE_ENDINGS = {
    repression: [
        {
            title: "Panic Attack",
            desc: "Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet."
        },
        {
            title: "Tectonic Reset",
            desc: "Repression hit 100%. Something that was supposed to stay buried came up all at once, in the worst possible meeting."
        },
        {
            title: "The Blowup",
            desc: "Repression maxed out. You said the quiet part loud, at volume, to the wrong person."
        },
        {
            title: "System Overpressure",
            desc: "You held it in until you couldn't. Now everyone knows exactly how you feel, whether they asked or not."
        },
        {
            title: "Full Meltdown",
            desc: "Repression hit 100%. You cried in the car for forty minutes before you could turn the key."
        },
        {
            title: "The Snap",
            desc: "You reached capacity. The thing that finally broke you was, embarrassingly, a printer."
        },
        {
            title: "Delayed Detonation",
            desc: "It wasn't the big thing. It was the small thing on top of the big thing. Repression is at zero now, mostly because you have none left to hold."
        },
        {
            title: "Public Unraveling",
            desc: "Repression hit 100% in front of coworkers. There is no version of Monday that fixes this."
        },
        {
            title: "The Overflow",
            desc: "You'd been fine. You'd been fine. You'd been fine. You are not fine."
        },
        {
            title: "Structural Failure",
            desc: "Repression maxed out and took the rest of your composure down with it."
        }
    ],
    mask: [
        {
            title: "Social Exile",
            desc: "Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but strangely free."
        },
        {
            title: "The Unmasking",
            desc: "Social Mask hit 0%. You said what you actually thought, out loud, in a group chat with your entire extended family."
        },
        {
            title: "Honesty, Uninvited",
            desc: "Mask hit zero. Turns out the truth doesn't need permission to leave your mouth."
        },
        {
            title: "No Filter Left",
            desc: "You ran out of mask exactly when someone asked \"no really, how are you?\" They got the real answer."
        },
        {
            title: "The Reveal",
            desc: "Social Mask bottomed out. Everyone can now see exactly what you've been holding back, and they have thoughts."
        },
        {
            title: "Radical Candor (Involuntary)",
            desc: "Mask hit 0%. You told your boss what you actually think of the quarterly review. It felt incredible for six seconds."
        },
        {
            title: "Exiled",
            desc: "You stopped performing \"fine,\" and the room noticed immediately."
        },
        {
            title: "The Real You, Unscheduled",
            desc: "Mask hit 0% at the worst possible meeting. At least it's memorable."
        },
        {
            title: "Social Combustion",
            desc: "You said the true thing instead of the nice thing. The silence afterward was very loud."
        },
        {
            title: "Unfiltered",
            desc: "Mask dropped to zero. You are now saying things out loud that used to just be thoughts."
        }
    ],
    child: [
        {
            title: "Total Disassociation",
            desc: "Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory.\n\n You feel nothing."
        },
        {
            title: "Autopilot Engaged",
            desc: "Inner Child hit 0%. You drove home and don't remember any of it."
        },
        {
            title: "The Hollow",
            desc: "Inner Child bottomed out. You're going through the motions, and the motions are all that's left."
        },
        {
            title: "Nobody Home",
            desc: "Inner Child hit zero. Someone asked how you were and you answered before you'd actually heard the question."
        },
        {
            title: "Flatline",
            desc: "Inner Child hit 0%. Nothing feels good. Nothing feels bad. Nothing, mostly, feels."
        },
        {
            title: "Muscle Memory Only",
            desc: "Inner Child bottomed out. You are functioning perfectly and feeling absolutely nothing about it."
        },
        {
            title: "The Static",
            desc: "Inner Child hit zero. There's a version of you still doing the tasks. You're not entirely sure where you went."
        },
        {
            title: "Checked Out",
            desc: "Inner Child hit 0%. You're present in the way furniture is present."
        },
        {
            title: "The Long Blink",
            desc: "Inner Child bottomed out somewhere between one task and the next. You didn't notice it happen. That's the part that should worry you."
        },
        {
            title: "Running on Empty",
            desc: "Inner Child hit zero. You are technically fine. Technically is doing a lot of work in that sentence."
        }
    ]
};

const CONTENT_ENDINGS = [
    {
        title: "The Powder Keg",
        desc: "You didn't explode. You just got very, very good at ticking.",
        conditions: [{stat: "repression", op: ">=", value: 70}]
    },
    {
        title: "The Performer",
        desc: "Nobody has seen the real you in years, including you.",
        conditions: [{stat: "mask", op: ">=", value: 85}, {stat: "child", op: "<=", value: 25}]
    },
    {
        title: "Radically Undone",
        desc: "You stopped hiding. It cost you more than you expected, but you kept yourself.",
        conditions: [{stat: "child", op: ">=", value: 75}, {stat: "mask", op: "<=", value: 40}]
    },
    {
        title: "Fragile Equilibrium",
        desc: "Nothing is fixed. Nothing is on fire. This might be what okay feels like.",
        conditions: [
            {stat: "repression", op: "<=", value: 30},
            {stat: "mask", op: ">=", value: 40}, {stat: "mask", op: "<=", value: 70},
            {stat: "child", op: ">=", value: 40}, {stat: "child", op: "<=", value: 70}
        ]
    },
    {
        title: "Functional Enough",
        desc: "You made it to tomorrow. Good job.",
        conditions: []
    }
];
