const CONTENT_EVENTS_HOME = [
    {
        zone: "HOME", title: "The Sigh",
        desc: "Your partner sighs audibly in the other room. You have absolutely zero context for why.",
        choices: [
            {
                text: "Assume it's your fault and quietly clean the kitchen.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 15},
                    child: {op: "subtract", value: 20},
                    rep: {op: "add", value: 10}
                },
                log: "You traded self-worth for perceived safety and clean dishes."
            },
            {
                text: "Ask aggressively, 'IS SOMETHING WRONG?!'",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 25},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 15}
                },
                log: "You struck first to avoid being struck. Classic."
            },
            {
                text: "Put on noise-canceling headphones.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 0},
                    rep: {op: "add", value: 25}
                },
                log: "Avoidance achieved. The tension is stored in your jaw."
            }
        ],
        glitch: {
            text: "Sigh back, louder, escalating into a full operatic aria.",
            log: "Neither of you knows what started this. Both of you are committed now."
        }
    },
    {
        zone: "HOME", title: "The Dishes in the Sink",
        desc: "Your partner says 'hey, whenever you get a chance' about the dishes. Their tone was completely neutral.",
        choices: [
            {
                text: "Do the dishes at 11 PM, narrating your resentment internally while sighing externally, and heavily.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 15}
                },
                log: "You complied loudly. Nobody is impressed."
            },
            {
                text: "Say 'I was GOING to' with more heat than the sentence needed.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 10}
                },
                log: "The dishes were never the real defendant."
            },
            {
                text: "Leave the room to 'find something' for four minutes. Flee state.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 10}
                },
                log: "A tactical retreat from a sink."
            }
        ],
        glitch: {
            text: "Build an elaborate dish-based sculpture instead of washing them.",
            log: "It's actually kind of impressive. It does not count as washing them, though."
        }
    },
    {
        zone: "HOME", title: "The Family Group Chat",
        desc: "Your aunt just brought up something from Thanksgiving 2019. Nobody asked.",
        choices: [
            {
                text: "Mute the chat and pretend your phone is broken.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 0},
                    rep: {op: "add", value: 10}
                },
                log: "You unplugged from the family server."
            },
            {
                text: "Draft a measured correction, then delete it three times.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10}
                },
                log: "You drafted diplomacy and shipped silence."
            },
            {
                text: "Send a single laughing emoji and nothing else.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You de-escalated with punctuation."
            }
        ],
        glitch: {
            text: "Reply only in cryptic, unrelated fortune-cookie wisdom.",
            log: "'The sink remembers what the heart forgets.' Nobody asked. Nobody replies."
        }
    },
    {
        zone: "HOME", title: "The Silent Car Ride",
        desc: "Twenty minutes home. Nobody has said anything since you left. You are replaying the entire evening.",
        choices: [
            {
                text: "Turn the radio up to fill the space.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 10}
                },
                log: "You outsourced the silence to a pop song."
            },
            {
                text: "Ask 'you okay?' four separate times.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You checked the temperature of a room that was comfortable, not cold."
            },
            {
                text: "Sit in it. Actually just sit in it.",
                tag: "secure",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 15}
                },
                log: "Twenty minutes of quiet did not, in fact, kill you."
            }
        ],
        glitch: {
            text: "Start narrating the drive like a hushed golf commentator.",
            log: "'And she signals... she signals early. Remarkable composure. Will she return her hands to ten and two? Stay tuned to find out...'"
        }
    },
    {
        zone: "HOME", title: "The Left-On-Read Text",
        desc: "You sent a long message to a parent, explaining how you actually feel. Marked read. Nothing since.",
        choices: [
            {
                text: "Open your messages to literally anyone else and get absorbed in something safer.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 15}
                },
                log: "You changed the channel on your own heart."
            },
            {
                text: "Reread your message eleven times, hunting for the sentence that broke it.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You performed an autopsy on a conversation that isn't dead yet."
            },
            {
                text: "Send nothing else. Let the silence belong to them, not you.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You stopped staring at a doorknob that isn't yours to turn."
            }
        ],
        glitch: {
            text: "Send one more message: just a single, staring emoji.",
            log: "The read receipt updates. The silence gets a face now."
        }
    },
    {
        zone: "HOME", title: "The Different Voice on the Phone",
        desc: "Your parent picks up sounding smaller than you remember. You don't know why yet.",
        choices: [
            {
                text: "Immediately go bright and cheerful to lift the mood before you've even asked what's wrong.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 15},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You showed up as sunshine before you knew what kind of day it was."
            },
            {
                text: "Ask, flatly, exactly what's going on. No preamble.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You skipped the small talk. It cost you nothing you needed."
            },
            {
                text: "Say 'oh, okay' and let the conversation drift somewhere safer.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You let a real question evaporate into weather talk."
            }
        ],
        glitch: {
            text: "Answer back in an equally strange, unexplained voice of your own.",
            log: "Neither of you addresses it. Some things stay sacred and weird."
        }
    },
    {
        zone: "HOME", title: "The Leftover They Didn't Eat",
        desc: "You made extra on purpose, left a note. It's still in the fridge, exactly where you left it.",
        choices: [
            {
                text: "Eat it yourself and say nothing about the note.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You absorbed the disappointment along with the leftovers."
            },
            {
                text: "Leave it in there for three more days, unable to deal with it.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "The food and the feeling both went untouched."
            },
            {
                text: "Ask, simply, if they want any leftovers before you eat the rest.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "A question, asked plainly, is not an accusation. Plus, you get dinner."
            }
        ],
        glitch: {
            text: "Hold a tiny, formal funeral for the leftovers.",
            log: "Several words were said. None of them were 'I'm sorry.'"
        }
    },
    {
        zone: "HOME", title: "The Closed Bedroom Door",
        desc: "It's usually open. Tonight it's closed, and you don't know why.",
        choices: [
            {
                text: "Stand outside it for a full minute, deciding nothing.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You waited outside a door you could have just knocked on."
            },
            {
                text: "Knock and immediately apologize for whatever it is.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You apologized before you knew the charge."
            },
            {
                text: "Knock. Ask if everything is okay.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10}
                },
                log: "You offered an opening instead of an assumption."
            }
        ],
        glitch: {
            text: "Slide a folded paper airplane of concern underneath it.",
            log: "It does not fly well on carpet. The gesture remains."
        }
    },
    {
        zone: "HOME", title: "The 'We Need To Talk' Text",
        desc: "Four words. No context. Sent an hour ago, and you've only just seen it.",
        choices: [
            {
                text: "Spend the hour composing worst-case scenarios instead of replying.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 25},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You lived through several futures that hadn't happened yet."
            },
            {
                text: "Reply with three apologies before you know what for.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You pled guilty to a charge that hadn't been read yet."
            },
            {
                text: "Reply: 'Okay. I'm here when you're ready.'",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You left room for the conversation instead of finishing it alone."
            }
        ],
        glitch: {
            text: "Reply with a single dramatic movie-trailer voice line.",
            log: "'In a world where nobody explains anything...'"
        }
    },
    {
        zone: "HOME", title: "The Passive-Aggressive Post-it",
        desc: "'Please rinse dishes before leaving in sink :)'\n\n\n The smiley face is doing a lot of unpaid emotional labor.",
        choices: [
            {
                text: "Rinse everything in the house preemptively for a week.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You overcorrected to outrun one sticky note."
            },
            {
                text: "Leave a post-it back, on a clean dish and slightly too pointed.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You returned the passive-aggression with interest. That'll show 'em."
            },
            {
                text: "Just talk to your roommate about it, out loud, later.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You used your words instead of a sticky note war. You both get pizza later and use paper plates."
            }
        ],
        glitch: {
            text: "Cover the entire kitchen in post-its of your own.",
            log: "The kitchen now resembles a ransom note made of passively aggressive politeness."
        }
    },
    {
        zone: "HOME", title: "The Wall Between Apartments",
        desc: "You can hear your neighbors arguing again, muffled but unmistakable, through a wall that was not built for privacy.",
        choices: [
            {
                text: "Turn up the TV as loud as you can and hope they can heart it.",
                tag: "fight",
                effects: {
                    rep: {op: "subtract", value: 5},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10}
                },
                log: "You drowned out a sound you couldn't control."
            },
            {
                text: "Consider, seriously, banging on the wall. Do nothing instead.",
                tag: "freeze",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You almost inserted yourself into a fight that isn't yours."
            },
            {
                text: "Put on headphones and let it be someone else's problem.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "Not every wall's noise is yours to carry."
            }
        ],
        glitch: {
            text: "Knock back in a rhythm, just to see if they knock again.",
            log: "A wall-based friendship begins, tentatively, in Morse-adjacent taps."
        }
    },
    {
        zone: "HOME", title: "The Empty Fridge Shelf",
        desc: "The thing you were saving for tomorrow is gone. No note, no explanation.",
        choices: [
            {
                text: "Ask, directly, who ate it.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You asked the small, direct question instead of stewing."
            },
            {
                text: "Say nothing and quietly recalculate your whole week's meals.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "One missing item rearranged your entire life, silently."
            },
            {
                text: "Decide it's fine, you didn't really need it anyway.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You talked yourself out of a want that was real, and into being a doormat."
            }
        ],
        glitch: {
            text: "Launch a full forensic investigation, complete with a labeled evidence board.",
            log: "You have connected several pieces of red string to absolutely nothing helpful."
        }
    },
    {
        zone: "HOME", title: "The Unanswered 'How Was Your Day'",
        desc: "You asked. They said 'fine' and kept scrolling. That was ten minutes ago.",
        choices: [
            {
                text: "Sit in the same room in total silence, waiting for more.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You waited for a door that wasn't going to open, anyway."
            },
            {
                text: "Fill the silence with details about your own day, unprompted.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You performed the conversation for the both of you."
            },
            {
                text: "Let 'fine' be enough for now. Try again later.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "Not every silence needs to be filled immediately or be analyzed."
            }
        ],
        glitch: {
            text: "Answer your own question instead, at great length, to the room.",
            log: "The room does not respond either. Hurtful, but fair."
        }
    },
    {
        zone: "HOME", title: "The Thermostat War",
        desc: "It's been adjusted three times today. Nobody has said a word about it out loud.",
        choices: [
            {
                text: "Set it where you want it and leave a note explaining why.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You made your preference visible instead of silent. The note lasted 2 hours before it went missing."
            },
            {
                text: "Just wear a sweater and say nothing, forever. Probably.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You sacrificed your physical comfort to avoid a two-minute conversation."
            },
            {
                text: "Leave it wherever they last set it, every time.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You denied yourself a temperature change just to keep the peace."
            }
        ],
        glitch: {
            text: "Install a tiny sign declaring the thermostat a neutral zone.",
            log: "Switzerland would be proud. (Nobody honors the treaty)."
        }
    },
    {
        zone: "HOME", title: "The Borrowed Thing, Not Returned",
        desc: "You lent it three weeks ago. You need it now. Asking for it back feels, somehow, enormously selfish.",
        choices: [
            {
                text: "Buy a replacement instead of asking for it back.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You paid money to avoid a ten-second conversation."
            },
            {
                text: "Ask for it back with four qualifiers and two apologies.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You apologized for wanting your own thing back."
            },
            {
                text: "Ask for it back plainly. It's yours.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10}
                },
                log: "This did not, in fact, end the friendship. They bought you dinner in appreciation."
            }
        ],
        glitch: {
            text: "Start a dramatic heist-movie-style plan to retrieve it.",
            log: "The plan involves zero actual heisting and a lot of standing outside their door whining."
        }
    },
    {
        zone: "HOME", title: "The Photo From An Easier Year",
        desc: "It's on the hallway wall, everyone smiling, from a year you remember very differently than the picture suggests.",
        choices: [
            {
                text: "Stop and stare at it longer than you meant to, every time.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You keep visiting a version of the year that didn't happen."
            },
            {
                text: "Consider taking it down. Don't, yet.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 5},
                    rep: {op: "add", value: 0}
                },
                log: "You noticed the mismatch and let yourself notice it."
            },
            {
                text: "Let the photo be a photo, not a verdict on the year.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "One picture doesn't get to outvote your memory."
            }
        ],
        glitch: {
            text: "Photobomb the memory. Mentally. With your current face.",
            log: "Past-you and present-you now occupy the same photograph, spiritually. So there."
        }
    },
    {
        zone: "HOME", title: "The Missed Call From Mom",
        desc: "Two missed calls and a voicemail you haven't pressed play on yet.",
        choices: [
            {
                text: "Let the voicemail sit unheard for the rest of the day.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "An unopened voicemail can hold a lot of imagined weight."
            },
            {
                text: "Call back immediately, bracing for whatever it is.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You armored up before you even knew what for."
            },
            {
                text: "Listen to the voicemail first. Then decide.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "Information turned out to be less scary than the dread. Have fun at Disney World!"
            }
        ],
        glitch: {
            text: "Call back using a dramatically different, unexplained voice.",
            log: "You are now, for reasons unclear, doing a full Irish accent."
        }
    },
    {
        zone: "HOME", title: "The Empty Side Of The Bed",
        desc: "They're traveling this week. The apartment is exactly the same size and feels twice as big.",
        choices: [
            {
                text: "Leave the TV on all night just to fill the quiet.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You drowned a feeling in a sitcom rerun."
            },
            {
                text: "Text constant updates about your evening, needing the thread to stay busy.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You kept the connection loud so it wouldn't feel absent."
            },
            {
                text: "Let the apartment be quiet. It's temporary.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "Quiet turned out to be survivable, if not fun."
            }
        ],
        glitch: {
            text: "Build a small, dignified pillow fort in the empty space and watch Netflix in it.",
            log: "The fort has excellent structural integrity and zero strategic purpose. "
        }
    },
    {
        zone: "HOME", title: "The Return Address You Don't Recognize",
        desc: "A letter arrives from someone you haven't spoken to in years. You've been staring at the envelope, unopened, for ten minutes.",
        choices: [
            {
                text: "Put it in a drawer. Deal with it 'later.'",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You filed the unknown away instead of facing it."
            },
            {
                text: "Open it and immediately plan an apologetic, generous reply.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You started drafting amends before reading the actual letter."
            },
            {
                text: "Open it. Read it. Feel whatever you feel.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You let the envelope just be information."
            }
        ],
        glitch: {
            text: "Shake the envelope and try to guess the contents like a game show.",
            log: "You have guessed 'ferret' three times. You weren't even close."
        }
    },
    {
        zone: "HOME", title: "The Recipe Card In Her Handwriting",
        desc: "You found it while cleaning out a drawer. The handwriting stops you cold for a second you didn't expect.",
        choices: [
            {
                text: "Put it back exactly where it was and close the drawer.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You returned the moment to the drawer, unopened."
            },
            {
                text: "Make the recipe tonight. Let it mean whatever it means.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You let an ordinary moment hold an old, specific grief."
            },
            {
                text: "Get frustrated at how much a card can do to you.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 5}
                },
                log: "You argued with your own feelings and lost. Now you're sad, hungry, and the drawer is still dirty."
            }
        ],
        glitch: {
            text: "Attempt to forge a matching handwriting style for your own notes from now on.",
            log: "Your grocery lists now look faintly, movingly ancestral."
        }
    },
    {
        zone: "HOME", title: "The Holiday Seating Chart",
        desc: "You've been placed, again, between the two relatives most likely to start a debate over the mashed potatoes.",
        choices: [
            {
                text: "Prepare a mental list of neutral topics to redirect toward.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You showed up armed with small talk as crowd control."
            },
            {
                text: "Accept your fate and mentally leave the table early.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You were present in body only, and only barely. Nobody noticed."
            },
            {
                text: "Re-assign yourself to the kids table and enjoy yourself.",
                tag: "secure",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You provided your own accommodation and now your nephews think you're cool."
            }
        ],
        glitch: {
            text: "Redraw the seating chart yourself, exiling the two feuding relatives to the garage.",
            log: "The garage now has better conversation than the dining room."
        }
    },
    {
        zone: "HOME", title: "The Sound Of The Garage Door At 2 AM",
        desc: "Someone's home later than expected. You're awake now, doing math you don't want to be doing.",
        choices: [
            {
                text: "Lie perfectly still, pretending to be asleep, wide awake.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You performed sleep you weren't getting."
            },
            {
                text: "Get up and greet them cheerfully, hiding that you were worried.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You covered the worry with a smile at 2 AM."
            },
            {
                text: "Ask in the morning if everything's okay.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You waited for daylight and asked a plain question. They just lost track of time."
            }
        ],
        glitch: {
            text: "Leap up and greet them in full detective-noir monologue.",
            log: "'You've got some explaining to do! At 2 AM! ...In this economy!'"
        }
    },
    {
        zone: "HOME", title: "The New Paint Color They Didn't Ask About",
        desc: "You come home to a wall that's a different color than it was this morning. Nobody mentioned it was happening.",
        choices: [
            {
                text: "Say you love it, immediately, before you've decided if you do.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You approved a decision before forming an opinion."
            },
            {
                text: "Ask why you weren't part of the conversation.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You named the part that actually bothered you: not the color."
            },
            {
                text: "Say nothing and just quietly start disliking the room.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You let a wall become a symbol instead of just a wall."
            }
        ],
        glitch: {
            text: "Start referring to the wall as 'the incident' from now on.",
            log: "The wall is aware of its new title. It does not react."
        }
    },
    {
        zone: "HOME", title: "The Silence After The Front Door Closes",
        desc: "Everyone's finally gone. The apartment is quiet in a way that feels, for one disorienting second, like something's wrong.",
        choices: [
            {
                text: "Immediately turn on background noise to fill the silence.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You filled a quiet room out of habit, not need."
            },
            {
                text: "Start texting people to check if everyone got home okay.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You reached outward to avoid sitting in the quiet."
            },
            {
                text: "Let the silence be silence for a minute before doing anything.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "An empty room turned out to be just an empty room."
            }
        ],
        glitch: {
            text: "Narrate the empty apartment like a nature documentary.",
            log: "'And here, in its natural habitat, the human finally exhales.'"
        }
    },
    {
        zone: "HOME", title: "The Rent Increase",
        desc: "An envelope from the landlord. The number at the bottom is $200 more than last year, effective next month.",
        choices: [
            {
                text: "Set the letter on the counter and don't look at the actual number again for a week.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "The number didn't go away. It just waited."
            },
            {
                text: "Draft a furious response citing every unfixed thing in the apartment.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 12}, child: {op: "add", value: 0}},
                log: "All true. None of it will lower the rent. Or your blood pressure."
            },
            {
                text: "Sit down and actually rework the budget around the new number.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You made the number smaller by making it real instead of avoided."
            }
        ],
        glitch: {
            text: "Start browsing apartments you can't afford, in a city you don't live in.",
            log: "You've mentally moved to a lake house. Your higher rent is still due next month."
        }
    },
    {
        zone: "HOME", title: "The Utility Bill That Doubled",
        desc: "The electric bill arrives at nearly twice last month's number, with no explanation you can find.",
        choices: [
            {
                text: "Set up autopay so you never have to actually look at the number again.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "Out of sight. Still, technically, out of your account."
            },
            {
                text: "Reread every line item like it's a puzzle with a solution.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "There is no puzzle. There is just a bigger number."
            },
            {
                text: "Call and actually ask what changed, instead of guessing.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "Turns out asking a real question gets you a real answer."
            }
        ],
        glitch: {
            text: "Unplug everything in the apartment out of spite.",
            log: "You now sit in the dark, on principle, having solved nothing."
        }
    },
    {
        zone: "HOME", title: "The Overdraft Notification",
        desc: "Your phone buzzes. Balance below zero. You do the math and know exactly which charge did it.",
        choices: [
            {
                text: "Text a family member asking, in a roundabout way, if they're doing okay 'financially, generally.'",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 10}},
                log: "You asked about someone else's money to avoid saying anything about yours."
            },
            {
                text: "Don't check the account again until the fee posts.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "The fee posted anyway. It always does."
            },
            {
                text: "Move what you can, call the bank, and actually look at the number.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You looked right at it, which turns out to be the hard part."
            }
        ],
        glitch: {
            text: "Buy something small and unnecessary out of pure defiance.",
            log: "The balance is more negative. You feel powerful. Briefly."
        }
    },
    {
        zone: "HOME", title: "The Forgotten Subscription Charge",
        desc: "A charge you don't recognize turns out to be a subscription you forgot to cancel eight months ago.",
        choices: [
            {
                text: "Decide it's not worth the hassle of calling to complain.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 8}},
                log: "You let it go, mostly because asking felt like more of a cost than the actual money lost."
            },
            {
                text: "Call and argue for a full refund on principle.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "You got half the money back and a mild reputation with customer service."
            },
            {
                text: "Cancel it, note the loss, and set a reminder to check for others.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You closed the leak instead of just being mad about the water."
            }
        ],
        glitch: {
            text: "Audit every single subscription you have in the middle of the night.",
            log: "You found six more. You are now afraid of your own bank statement. And your goldfish-like memory."
        }
    },
    {
        zone: "HOME", title: "The Broken Appliance No One Can Afford to Fix",
        desc: "The washing machine makes a sound it shouldn't and then stops entirely. The repair quote costs almost as much as a new one.",
        choices: [
            {
                text: "Leave the wet laundry in it for two days while you decide what to do.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "The laundry did not decide anything on your behalf."
            },
            {
                text: "Tell whoever asks that it's 'basically fine, just being weird.'",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "It is not fine. You've just decided saying so is easier."
            },
            {
                text: "Get a second quote and actually compare the real numbers.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You treated it like a decision instead of a crisis."
            }
        ],
        glitch: {
            text: "Watch seventeen YouTube tutorials on fixing it yourself.",
            log: "You now understand washing machine repair on a conceptual level. The machine remains tangibly broken."
        }
    },
    {
        zone: "HOME", title: "The Family Loan Request",
        desc: "A family member asks to borrow money you don't really have room to lend, in a tone that makes it hard to say no.",
        choices: [
            {
                text: "Say yes immediately and figure out the math later.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 15}},
                log: "You said yes with your mouth before your budget got a vote."
            },
            {
                text: "Say no bluntly, then feel terrible about how it came out.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "The no was correct. The delivery could use some work."
            },
            {
                text: "Say what you can actually afford, clearly, without over-explaining.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You gave an honest number instead of an apologetic one."
            }
        ],
        glitch: {
            text: "Offer to lend double what they asked for, out of guilt.",
            log: "Your bank account did not consent to this decision. You may never financially recover from this."
        }
    },
    {
        zone: "HOME", title: "The Split Bill Argument",
        desc: "Whoever you live with wants to talk about the shared expenses again. The conversation you've both been avoiding for a month.",
        choices: [
            {
                text: "Say you're too tired to get into it tonight, again.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 8}, child: {op: "add", value: 0}},
                log: "You bought one more night of not talking about it. The bill didn't wait."
            },
            {
                text: "Bring up every uneven expense from the last six months at once.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 12}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "You were right about most of it. It still didn't land well."
            },
            {
                text: "Actually sit down, split it fairly, and let the conversation be boring.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 5}, child: {op: "add", value: 8}},
                log: "Nothing dramatic happened. That was sort of the point."
            }
        ],
        glitch: {
            text: "Propose settling it with a coin flip.",
            log: "Nobody agreed to this. You flipped it anyway. Somehow it worked flawlessly, anyway."
        }
    },
    {
        zone: "HOME", title: "The Pet Skipping Meals",
        desc: "Your dog, or cat, walks right past a full food bowl for the second day in a row, and the vet visit you can't quite afford yet keeps not happening.",
        choices: [
            {
                text: "Tell yourself it's probably nothing and keep watching from across the room.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "Watching didn't make the bowl any emptier or any fuller."
            },
            {
                text: "Post about it online and let strangers' reassurance stand in for an actual appointment.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 8}},
                log: "Forty people said it's probably fine. None of them are a vet."
            },
            {
                text: "Call and ask what a visit actually costs, then book it anyway.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You let the real number decide instead of your fear of it."
            }
        ],
        glitch: {
            text: "Offer the pet seventeen different foods in immediate succession.",
            log: "Nothing worked. You now have six open cans and one unimpressed animal."
        }
    },
];
