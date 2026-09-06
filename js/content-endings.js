// ============================================================
// ENDINGS
// Loaded before content.js, which assembles DEFAULT_CONTENT from this.
// ============================================================


const CONTENT_FAILURE_ENDINGS = {
    repression: [
        {
            title: "Panic Attack",
            desc: "Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet."
        },
        {
            title: "Tectonic Reset",
            desc: "Repression maxed out the scale. Something that was supposed to stay buried came up all at once, in the worst possible meeting."
        },
        {
            title: "The Blowup",
            desc: "Repression blown. You said the quiet part out loud. To the wrong person."
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
            desc: "You reached capacity. The thing that finally broke you was, embarrassingly, very minor."
        },
        {
            title: "Delayed Detonation",
            desc: "It wasn't the big thing. It was the small thing on top of the big thing. Repression is at zero now, mostly because you have none left to hold."
        },
        {
            title: "Public Unraveling",
            desc: "Repression hit 100% in front of everybody. There is no version of Monday that fixes this."
        },
        {
            title: "The Overflow",
            desc: "You were just fine! You've been fine this whole time! You planned on being fine all day... \n\n\n You are not fine."
        },
        {
            title: "Structural Failure",
            desc: "Repression maxed out and took the rest of your composure down with it."
        }
    ],
    mask: [
        {
            title: "Social Exile",
            desc: "Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but free."
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
            desc: "You ran out of mask exactly when someone asked how you were and they got the real answer."
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
            desc: "You stopped performing how \"fine\" you are, and the room noticed immediately."
        },
        {
            title: "The Real You, Unscheduled",
            desc: "Mask hit 0% at the worst possible time in the company meeting. At least it's memorable. You try to tell yourself they were laughing *with* you."
        },
        {
            title: "Social Combustion",
            desc: "You said the true thing instead of the nice thing. The silence afterward was very loud (and awkward)."
        },
        {
            title: "Unfiltered",
            desc: "Mask dropped to zero. You are now saying things out loud that used to just be inside thoughts."
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
            desc: "Inner Child hit 0%. Nothing feels good. Nothing feels bad.\n\nNothing feels at all."
        },
        {
            title: "Muscle Memory Only",
            desc: "Inner Child bottomed out. You are functioning perfectly and feeling absolutely no joy from it."
        },
        {
            title: "The Static",
            desc: "Inner Child hit zero. There's a version of you still doing the tasks. You're not entirely sure where the rest of you went."
        },
        {
            title: "Checked Out",
            desc: "Inner Child hit 0%. You're present in the room in the way furniture is also present."
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
        conditions: [{stat: "repression", op: ">=", value: 70}],
        variants: [
            {title: "The Powder Keg", desc: "You didn't explode. You just got very, very good at ticking."},
            {title: "Holding Pattern", desc: "You didn't self-destruct, yet. Today just isn't over anywhere else, either."},
            {title: "Pressure Cooker", desc: "Something in you is very close to the surface. You made it to the deadline before it did."},
            {title: "The Held Breath", desc: "You survived by holding something in the whole way through. Your shoulders will remember this tomorrow."},
            {title: "Simmering", desc: "Not boiling over. Not cooled down either. Just holding at a temperature that isn't sustainable."},
            {title: "The Clenched Jaw Ending", desc: "You made it through white-knuckled. Nobody else could tell. Your jaw could."},
            {title: "Running Hot", desc: "You crossed the finish line still overheating. The engine didn't seize. It came close."},
            {title: "Barely Contained", desc: "Whatever's in there is still in there. That's the whole victory, such as it is."}
        ]
    },
    {
        conditions: [{stat: "mask", op: ">=", value: 85}, {stat: "child", op: "<=", value: 25}],
        variants: [
            {title: "The Performer", desc: "Nobody has seen the real you in years, including you."},
            {title: "Note-Perfect", desc: "You hit every mark, said every right thing, and couldn't tell anyone what it cost until just now."},
            {title: "The Understudy", desc: "You've played the part of yourself so long the understudy might genuinely be better at it by now."},
            {title: "Flawless Execution", desc: "The performance was seamless. Nobody asks what happens after the curtain, including you."},
            {title: "The Convincing Copy", desc: "You do a very good impression of someone who's fine. Most days, even you almost believe it."},
            {title: "Standing Ovation", desc: "Everyone clapped. You bowed. Somewhere backstage, something's been waiting a long time for its turn."},
            {title: "The Mask That Fits", desc: "It fits so well now you forget you put it on this morning. Or any morning."},
            {title: "All Surface, No Depth Charge", desc: "Smooth all the way down, as far as anyone can tell, including the part of you that used to check."}
        ]
    },
    {
        conditions: [{stat: "child", op: ">=", value: 75}, {stat: "mask", op: "<=", value: 40}],
        variants: [
            {title: "Radically Undone", desc: "You stopped hiding. It cost you more than you expected, but you kept yourself."},
            {title: "The Unvarnished Version", desc: "People saw the real thing, unedited. Some of them left. You're still here, which is the actual point."},
            {title: "Nothing Left to Perform", desc: "You ran out of energy for the act partway through and just stopped. Turns out that was allowed."},
            {title: "The Honest Wreckage", desc: "It isn't pretty. It isn't curated. It's yours, all the way through, for the first time in a while."},
            {title: "Seen and Not Sorry", desc: "You let people see the parts you used to manage. A few flinched. You didn't take it back."},
            {title: "The Costly Truth", desc: "Being yourself had a price today. You paid it and you're still standing in the same shoes."},
            {title: "Unpolished", desc: "Nothing about today was smooth. All of it was real, which turned out to matter more."},
            {title: "The Exposed Wire", desc: "You stopped insulating yourself from being seen. It sparked a little. You're still conducting."}
        ]
    },
    {
        conditions: [{stat: "mask", op: "<=", value: 25}, {stat: "child", op: ">=", value: 25}],
        variants: [
            {title: "Raw Nerve", desc: "You stopped filtering. Everything's a little too loud and a little too close to the surface right now."},
            {title: "Nothing Between You And It", desc: "The mask came off somewhere along the way and you never found a good moment to put it back on."},
            {title: "Exposed Wiring", desc: "Whatever usually buffers you from the world just wasn't there today. You felt everything at full volume."},
            {title: "The Unfiltered Version", desc: "People got the real reaction, in real time, with none of the usual smoothing. It was a lot. For everyone."},
            {title: "Too Close To The Surface", desc: "You spent the day one comment away from visibly reacting to everything. Some days are just like that."},
            {title: "Skinless", desc: "The usual layer between you and the day just wasn't there. You made it through anyway, a little rawer for it."},
            {title: "The Open Wound Approach", desc: "You didn't cover anything up today. It wasn't strategic. It just happened, and here you still are."},
            {title: "Nowhere to Hide It", desc: "There was no version of today where you could smooth this over. So you didn't. That's new, for you."}
        ]
    },
    {
        conditions: [{stat: "mask", op: ">=", value: 41}, {stat: "child", op: "<=", value: 25}],
        variants: [
            {title: "Coasting on Empty", desc: "You looked fine all day. You have no real idea what was actually fueling that."},
            {title: "Running on Fumes and Good Posture", desc: "From the outside, a completely normal day. On the inside, mostly static."},
            {title: "Presentable", desc: "You showed up, said the right things, looked put-together. Nothing underneath was doing the same."},
            {title: "The Functional Hollow", desc: "Everything worked. Nothing felt like anything. Both of those are true at once."},
            {title: "Autopilot, Well-Dressed", desc: "You got through today on muscle memory and social competence. The rest of you clocked out hours ago."},
            {title: "Numb But Nice About It", desc: "You were pleasant to everyone today. You couldn't say which of your own feelings, if any, showed up."},
            {title: "The Smooth Surface", desc: "Nothing cracked today. Nothing underneath the surface was really there to crack, either."},
            {title: "Holding the Shape", desc: "You kept the shape of a normal day. Whatever's supposed to fill that shape took the day off."}
        ]
    },
    {
        conditions: [
            {stat: "repression", op: "<=", value: 30},
            {stat: "mask", op: ">=", value: 40}, {stat: "mask", op: "<=", value: 70},
            {stat: "child", op: ">=", value: 40}, {stat: "child", op: "<=", value: 70}
        ],
        variants: [
            {title: "Fragile Equilibrium", desc: "Nothing is fixed. Nothing is on fire. This might be what okay feels like."},
            {title: "The Uneasy Middle", desc: "Not great, not terrible. You're starting to suspect that's just what most days actually are."},
            {title: "Level, For Now", desc: "Everything's balanced today. You know better than to assume that's permanent. You'll take it anyway."},
            {title: "A Quiet Enough Day", desc: "Nothing demanded a reaction today. That's rarer than it should be, and you noticed it."},
            {title: "Holding Steady", desc: "Not fixed. Not falling apart. Just steady, in a way that felt almost unfamiliar by the end of it."},
            {title: "The In-Between", desc: "Not thriving, not failing. Somewhere in the wide, unglamorous middle most of life actually happens in."},
            {title: "Manageable", desc: "Nothing today asked more of you than you had. That in itself felt like a small, quiet win."},
            {title: "Even Keel", desc: "The boat didn't rock much today. You're starting to remember what that's supposed to feel like."}
        ]
    },
    {
        conditions: [
            {stat: "repression", op: "<=", value: 30},
            {stat: "mask", op: ">=", value: 60},
            {stat: "child", op: ">=", value: 60}
        ],
        variants: [
            {title: "Actually Okay", desc: "Not surviving. Not performing okay. Just, for once, actually okay. You can tell the difference from the inside."},
            {title: "The Real Thing", desc: "This isn't the version of fine you perform for other people. This is the actual, unperformed version."},
            {title: "No Asterisk", desc: "Good, without a footnote explaining why it doesn't count. You keep waiting for the footnote. It doesn't come."},
            {title: "Quietly Thriving", desc: "Nobody's throwing you a parade for this, and it doesn't need one. You're just, genuinely, doing well."},
            {title: "The Unforced Smile", desc: "You didn't have to build this feeling. It was just there today, the way it's supposed to be sometimes."},
            {title: "Solid Ground", desc: "For once, nothing underneath you feels like it's about to give way. You're still getting used to that."},
            {title: "Earned, Not Performed", desc: "This good day wasn't a mask. You checked. Twice. It held up both times."},
            {title: "The Genuine Article", desc: "Same shape as a good day you'd fake for someone else's benefit. The difference is nobody had to be convinced, including you."}
        ]
    },
    {
        conditions: [
            {stat: "repression", op: ">=", value: 31}, {stat: "repression", op: "<=", value: 69},
            {stat: "mask", op: ">=", value: 40}, {stat: "child", op: ">=", value: 40}
        ],
        variants: [
            {title: "The Long Fuse", desc: "You're carrying more than you'd like to admit, and carrying it fine, for now."},
            {title: "Holding It Together, Mostly", desc: "Nothing broke today. A few things bent. You're still counting that as a win."},
            {title: "Managing", desc: "Not thriving. Not drowning. Actively, deliberately managing, which is its own quiet kind of work."},
            {title: "The Working Tension", desc: "Something in you is pulled tight and hasn't snapped. You've gotten used to the tightness."},
            {title: "Under Pressure, Upright", desc: "The weight's real. You're still standing under it. That's not nothing."},
            {title: "Simmer Setting", desc: "Not boiling. Not cold. Just holding at a low, steady heat that takes real effort to maintain."},
            {title: "The Sustainable Strain", desc: "This isn't a crisis. It's just a lot, held at a pace you can actually keep up."},
            {title: "Tightly Wound, Still Functional", desc: "You're wound tighter than you'd like. You're also, somehow, still getting things done."}
        ]
    },
    {
        conditions: [],
        variants: [
            {title: "Functional Enough", desc: "You made it to tomorrow. Good job."},
            {title: "You're Still Here", desc: "That's the whole ending. Some days that's actually the entire accomplishment."},
            {title: "Day Survived", desc: "Nothing about today fits a neater category than this. You got through it. That counts."},
            {title: "The Unremarkable Ending", desc: "No dramatic collapse, no dramatic triumph. Just a day, ending, with you still in it."},
            {title: "Adequate", desc: "Not the best day. Not the worst. A perfectly forgettable, perfectly fine day, and those matter too."},
            {title: "Good Enough, For Now", desc: "This isn't the ending with a moral. It's just the one where you made it to the last turn."},
            {title: "Nothing To Report", desc: "No collapse. No breakthrough. Just a day that happened, the way most of them do."},
            {title: "The Ordinary Ending", desc: "Most days end like this: quietly, without a headline. This was one of those."}
        ]
    }
];
