const CONTENT_EVENTS_PUBLIC = [
    {
        zone: "PUBLIC", title: "The Checkout Line Small Talk",
        desc: "The cashier asks how your day is going, right as you're barely holding it together behind a very calm face.",
        choices: [
            {
                text: "Say 'great, thanks!' with way more enthusiasm than you have.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "You performed 'great' for someone who will forget this conversation in four seconds."
            },
            {
                text: "Give a flat, one-word answer and stare at the card reader.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You disappeared into the transaction and let the small talk die there."
            },
            {
                text: "Say 'honestly, a lot today' and let it be a real, if brief, answer.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You told a stranger a true thing instead of a convenient one."
            }
        ],
        glitch: {
            text: "Ask the cashier how THEIR day is going, in aggressive detail.",
            log: "They were not prepared for this. Neither, really, were you."
        }
    },
    {
        zone: "PUBLIC", title: "The Customer Service Call",
        desc: "Forty minutes on hold, then a real person finally picks up, and you have to sound pleasant immediately.",
        choices: [
            {
                text: "Apologize for 'bothering them' before you've even explained the problem.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "You apologized for needing help before anyone accused you of it."
            },
            {
                text: "Let all forty minutes of frustration out on the person who just picked up.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 12}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "Correct target: the hold music. Actual target: an underpaid stranger."
            },
            {
                text: "Take a breath, state the problem plainly, and stay civil.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 5}},
                log: "You separated the wait from the person now trying to fix it."
            }
        ],
        glitch: {
            text: "Ask to speak to the hold music's manager.",
            log: "There is no such position. You asked anyway."
        }
    },
    {
        zone: "PUBLIC", title: "The Compliment From A Stranger You'll Never See Again",
        desc: "Someone in line says, out of nowhere, that they like your energy today. You will never see them again.",
        choices: [
            {
                text: "Say a fast 'thanks' and physically speed up to end the interaction.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You outran a kind word like it was a threat."
            },
            {
                text: "Freeze up and say nothing until they look away, confused.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "A nice moment got the silent treatment. It wasn't personal. Probably."
            },
            {
                text: "Say thank you and actually let it land for a second.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "A stranger's small kindness got all the way in, for once."
            }
        ],
        glitch: {
            text: "Ask them to write the compliment down so you can keep it.",
            log: "You are now holding a receipt that says 'good energy, 2:47 PM.' You will keep this forever."
        }
    },
    {
        zone: "PUBLIC", title: "The Public Cry On The Bus",
        desc: "It hits you three stops from home and there is nowhere to put it. Everyone very pointedly looks at their phones.",
        choices: [
            {
                text: "Smile at the person next to you like everything's fine, tears and all.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 10}},
                log: "You performed 'fine' with visible evidence directly contradicting it."
            },
            {
                text: "Stare straight ahead and let it happen without acknowledging it at all.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You rode it out like weather. Nobody said anything. That's the deal on public transit."
            },
            {
                text: "Let it happen, wipe your face when it passes, and don't apologize for it.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 3}, child: {op: "add", value: 10}},
                log: "You cried on a bus and didn't make it mean anything about you as a person."
            }
        ],
        glitch: {
            text: "Loudly announce to the whole bus that you're fine, actually.",
            log: "Nobody asked. Several people now know more about your day than they wanted to."
        }
    },
    {
        zone: "PUBLIC", title: "The Interview With A Stranger Who Holds All The Cards",
        desc: "The interviewer asks you to describe your biggest weakness, and you have exactly one chance to answer this well.",
        choices: [
            {
                text: "Give the fake-weakness answer everyone knows is fake ('I work too hard').",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "You performed vulnerability shaped like a humblebrag. They've heard it before. So have you."
            },
            {
                text: "Blank out and give a rambling non-answer that goes nowhere.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You watched yourself not answer the question and could not intervene."
            },
            {
                text: "Give an actual, specific weakness and what you're doing about it.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You told a stranger something true, on purpose, in a room built for performance."
            }
        ],
        glitch: {
            text: "Answer with a completely unrelated childhood memory instead.",
            log: "The interviewer is now taking very different notes than they were a minute ago."
        }
    },
    {
        zone: "PUBLIC", title: "The Wrong Order",
        desc: "The order that arrives is not, in any respect, the order you placed. Correcting it means being A Problem in front of everyone in line.",
        choices: [
            {
                text: "Eat it anyway and never mention it to anyone.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 10}},
                log: "You absorbed someone else's mistake so nobody's day would get slightly harder."
            },
            {
                text: "Send it back loudly enough that the whole counter hears the complaint.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 12}, child: {op: "add", value: 0}},
                log: "The order got fixed. So did everyone's opinion of you, briefly."
            },
            {
                text: "Quietly flag the mistake and ask for it to be corrected.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 5}},
                log: "You asked for the right thing without turning it into a scene."
            }
        ],
        glitch: {
            text: "Decide this is a sign and order something you've never tried.",
            log: "It's fine. Not what you wanted. An acceptable plot twist regardless."
        }
    },
    {
        zone: "PUBLIC", title: "The Icebreaker Question You Have To Answer In Front Of The Room",
        desc: "The facilitator goes around the circle. You have about ten seconds to decide what a room full of near-strangers gets to know about you.",
        choices: [
            {
                text: "Give the safest, most forgettable answer possible.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 5}},
                log: "You disappeared into the icebreaker without anyone noticing you were even in it."
            },
            {
                text: "Go completely blank when it's your turn and stall for time.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "Eight seconds of silence in front of strangers. You counted every one."
            },
            {
                text: "Say something small but actually true instead of the safe version.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You gave the room slightly more of the real thing than it was expecting."
            }
        ],
        glitch: {
            text: "Answer with something completely unhinged just to see what happens.",
            log: "The circle is now unsettled. You have, at least, made an impression."
        }
    },
    {
        zone: "PUBLIC", title: "The Person Who Held The Door And Now You're Walking The Same Direction",
        desc: "They held the door. You said thanks. Now you're both walking the exact same direction down an empty hallway, well past the point where silence is normal.",
        choices: [
            {
                text: "Suddenly develop an urgent need to check your phone until they're gone.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You performed an emergency to escape a hallway. The emergency was silence."
            },
            {
                text: "Keep walking in dead silence and hope the hallway ends soon.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "Neither of you said anything else. The hallway did, eventually, end."
            },
            {
                text: "Just laugh and say 'well, this is a long hallway.'",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 5}, child: {op: "add", value: 5}},
                log: "You named the awkward thing out loud and it immediately stopped being awkward."
            }
        ],
        glitch: {
            text: "Strike up a completely unnecessary full conversation for the rest of the hallway.",
            log: "You now know a stranger's opinion on parking garages. Neither of you asked for this."
        }
    }
];
