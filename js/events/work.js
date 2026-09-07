const CONTENT_EVENTS_WORK = [
    {
        zone: "WORK", title: "The Typo",
        desc: "You sent an email to your boss with a minor typo. Your brain immediately interprets this as a fatal error that will result in death or financial ruin.",
        choices: [
            {
                text: "Send a frantic three-paragraph apology.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "subtract", value: 5}
                },
                log: "You debased yourself for a misplaced comma."
            },
            {
                text: "Stare at the wall and dissociate for 20 minutes.",
                tag: "freeze",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 20}
                },
                log: "You fled your physical body. The typo remains. Nobody died."
            },
            {
                text: "Tell yourself 'it's just a typo' while sweating profusely.",
                tag: "secure",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 10}
                },
                log: "You attempted self-soothing. It was highly unconvincing and only mildly successful."
            }
        ],
        glitch: {
            text: "Reply in fluent, unhinged interpretive dance. Over email. Somehow.",
            log: "HR has several follow-up questions. So does everyone else. Nobody's mad, though."
        }
    },
    {
        zone: "WORK", title: "The Compliment",
        desc: "A coworker tells you that you did a 'really great job' on the presentation. It sounds sincere.",
        choices: [
            {
                text: "Deflect immediately and credit the team.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 15},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You physically swatted away the affection. Idiot."
            },
            {
                text: "Assume they are setting you up for a massive failure later and prepare a defense.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 15}
                },
                log: "You chose paranoia over pride. Hooray?"
            },
            {
                text: "Say 'Thank you' and let the discomfort burn your throat.",
                tag: "secure",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 15},
                    rep: {op: "subtract", value: 10}
                },
                log: "You accepted love. It hurt less than you feared."
            }
        ],
        glitch: {
            text: "Announce, loudly, that you accept this compliment on behalf of all of humanity.",
            log: "Someone starts a slow clap. It does not catch on."
        }
    },
    {
        zone: "WORK", title: "The Closed Door",
        desc: "You walk past a conference room where two managers are talking. They look at you and the door slowly closes.",
        choices: [
            {
                text: "Begin packing up your desk mentally.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 20},
                    rep: {op: "add", value: 15}
                },
                log: "You braced for the worst-case scenario. Which was Nothing."
            },
            {
                text: "Work at 300% capacity for the next four hours.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 20},
                    child: {op: "subtract", value: 25},
                    rep: {op: "add", value: 10}
                },
                log: "You overcompensated through labor. Capitalism wins."
            },
            {
                text: "Remind yourself you are an adult and this is fine.",
                tag: "secure",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 10}
                },
                log: "You chose logic. Your amygdala chose to ignore it."
            }
        ],
        glitch: {
            text: "Slide a note under the door reading only: 'I KNOW.'",
            log: "You know nothing. The door remains closed. Deeply satisfying anyway."
        }
    },
    {
        zone: "WORK", title: "The Reply-All",
        desc: "You meant to reply privately with a joke about the meeting length. You did not reply privately.",
        choices: [
            {
                text: "Immediately close your laptop and compose a new identity.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 20},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 20}
                },
                log: "You attempted to physically escape the timeline. There is no escape."
            },
            {
                text: "Send a follow-up message that says 'ignore that. lol.'",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You tried to laugh-track your way out of consequences. Hopefully it worked..."
            },
            {
                text: "Own it. Reply-all again with an article about workplace inefficiency.'",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 15},
                    rep: {op: "subtract", value: 10}
                },
                log: "You chose chaos over shame. Bold move with your charisma levels."
            }
        ],
        glitch: {
            text: "Reply-all a third time, this one entirely in Comic Sans and indecipherable.",
            log: "Comic Sans has never been used with such menace."
        }
    },
    {
        zone: "WORK", title: "The Calendar Invite",
        desc: "'Quick Chat' with your manager. No agenda. Fifteen minutes from now.",
        choices: [
            {
                text: "Spend the fifteen minutes drafting a resignation letter.",
                tag: "flight",
                effects: {
                    rep: {op: "add", value: 25},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You pre-grieved a job you still have."
            },
            {
                text: "Ask a coworker if they've heard anything. They haven't.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 10}
                },
                log: "You outsourced your anxiety to someone equally unequipped."
            },
            {
                text: "Walk in assuming it's fine.",
                tag: "secure",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 15}
                },
                log: "It was never about you. It was about the parking lot construction."
            }
        ],
        glitch: {
            text: "Show up in full riot gear, 'just in case.'",
            log: "It was your annual review you forgot about. You are overdressed and underprepared."
        }
    },
    {
        zone: "WORK", title: "The Read Receipt",
        desc: "Seen 3:12 PM. It is now 6:47 PM. No reply.",
        choices: [
            {
                text: "Send a second message: 'no worries just following up!'",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You apologized for existing in their inbox (and being impatient)."
            },
            {
                text: "Close Slack and refuse to open it until tomorrow.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 0},
                    rep: {op: "add", value: 15}
                },
                log: "Out of sight, technically not out of mind. Close enough."
            },
            {
                text: "Assume you've been quietly deleted from their mind.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You Sherlocked your way to a panic attack with zero evidence."
            }
        ],
        glitch: {
            text: "Send a single, unprompted photo of a raccoon looking concerned.",
            log: "The raccoon says what you cannot. Which is nothing of importance."
        }
    },
    {
        zone: "WORK", title: "The Autocorrect Betrayal",
        desc: "You meant to type something professional. Autocorrect had other plans. It has already been sent.",
        choices: [
            {
                text: "Send six frantic follow-ups explaining what you meant.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "subtract", value: 5}
                },
                log: "You buried the joke under a small avalanche of context and shame."
            },
            {
                text: "Screenshot it, close the laptop, stare at the wall.",
                tag: "freeze",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 20}
                },
                log: "You archived the evidence and fled the scene of a victimless crime."
            },
            {
                text: "Let the typo be funny. It's kind of funny.",
                tag: "secure",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 10}
                },
                log: "You allowed yourself to be a person who makes typos. You survived."
            }
        ],
        glitch: {
            text: "Lean in. Claim the typo was avant-garde on purpose.",
            log: "You have accidentally invented a new art movement and the accountants are fond of you, now."
        }
    },
    {
        zone: "WORK", title: "The Performance Review Buzzword",
        desc: "Your manager says 'let's discuss your growth areas' in a tone that reveals absolutely nothing.",
        choices: [
            {
                text: "Mentally practice your groveling technique to beg for your job back.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 25},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You spent energy defending a career you still have."
            },
            {
                text: "Walk in and ask directly what 'growth areas' means, specifically.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 15}
                },
                log: "You demanded the noun behind the euphemism."
            },
            {
                text: "Prepare a mental defense file of every accomplishment from the last three years.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You built a case for a trial nobody scheduled."
            }
        ],
        glitch: {
            text: "Respond back at them entirely in weaponized corporate buzzwords.",
            log: "You have synergized so hard the meeting ended early out of confusion. Nobody dares to circle back."
        }
    },
    {
        zone: "WORK", title: "The Meeting That Did Not Need To Be",
        desc: "Forty-five minutes, twelve people, and the entire message could have fit in three sentences (in an email).",
        choices: [
            {
                text: "Nod along and add a supportive 'great point' to three different tangents.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You applauded the detour instead of naming it."
            },
            {
                text: "Mentally exit the call and return only when your name is said.",
                tag: "freeze",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 15}
                },
                log: "Your body stayed. The rest of you clocked out early. Good for you."
            },
            {
                text: "Ask, once, if this could be a two-line message next time.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10}
                },
                log: "You said the quiet part. People respect you for it."
            }
        ],
        glitch: {
            text: "Stand up and simply read the email aloud instead.",
            log: "The meeting ends four seconds later. Nobody claps, but you think they maybe want to."
        }
    },
    {
        zone: "WORK", title: "The Away Message",
        desc: "You set your status to 'in a meeting' twenty minutes ago and never actually joined one. Someone just pinged you directly.",
        choices: [
            {
                text: "Type 'sorry, just wrapped up, what's up!' like it's true.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You performed availability you didn't have."
            },
            {
                text: "Let the message sit unread for another eleven minutes.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 0},
                    rep: {op: "add", value: 15}
                },
                log: "You bought time you'll have to pay back later."
            },
            {
                text: "Reply honestly: 'Just saw this, give me a minute.'",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You told a true, small, unremarkable thing."
            }
        ],
        glitch: {
            text: "Reply with a photo of an actual empty desk, for evidence.",
            log: "The desk is, notably, yours. This raises more questions than it answers. You're okay with this."
        }
    },
    {
        zone: "WORK", title: "The CC'd Boss",
        desc: "A coworker loops your manager into a thread about a mistake that was mostly, but not entirely, your fault.",
        choices: [
            {
                text: "Immediately reply-all with a full, apologetic breakdown.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 15},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You built the case against yourself before anyone asked for one."
            },
            {
                text: "Reply-all clarifying, calmly, which parts were actually yours.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You drew a line around the blame instead of absorbing all of it."
            },
            {
                text: "Leave the thread unanswered and refresh your inbox every four minutes.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You watched the thread instead of joining it."
            }
        ],
        glitch: {text: "Reply-All to the entire company. Let everyone share in this learning experience.", log: "Democracy, but for blame. Nobody wins but nobody loses, either. Except maybe some brain cells."}
    },
    {
        zone: "WORK", title: "The Unclaimed Credit",
        desc: "In the meeting, your manager describes your idea as something the team 'landed on together.' Nobody looks at you.",
        choices: [
            {
                text: "Say nothing. Add it to the list you're keeping in your head.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You filed it under evidence, case still open."
            },
            {
                text: "Laugh it off and agree it really was a group effort.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You co-signed the erasure to keep the room comfortable."
            },
            {
                text: "Mention afterward, privately and plainly, that the idea was yours.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 15}
                },
                log: "You said the true thing to one person instead of nobody. Feel better?"
            },
            {
                text: "Speak up in the meeting, right then, and correct the record.",
                tag: "fight",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 20},
                    child: {op: "add", value: 5}
                },
                log: "You corrected the record in real time. The room got very interested in their notes."
            }
        ],
        glitch: {
            text: "Stand up and take a long, silent bow.",
            log: "Three people clap before realizing they don't know why. Doesn't matter; validation is transferable but NOT refundable."
        }
    },
    {
        zone: "WORK", title: "The Typing Indicator",
        desc: "Your manager's typing indicator appears, disappears, appears again. Three times. Still no message.",
        choices: [
            {
                text: "Stare at the little dots like they're a polygraph.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You interrogated punctuation that hadn't arrived yet."
            },
            {
                text: "Close the tab so you can't watch it happen.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 15}
                },
                log: "You removed the evidence, not the feeling."
            },
            {
                text: "Keep working. Whatever it is will say itself eventually.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You let an unfinished sentence stay unfinished."
            }
        ],
        glitch: {
            text: "Start typing back before they've even sent anything.",
            log: "Your dots meet their dots. A standoff of pure anticipation. Who will win? ..."
        }
    },
    {
        zone: "WORK", title: "The Out-of-Office Reply",
        desc: "You email a colleague something urgent. The auto-reply says they've been out since yesterday. Nobody told you.",
        choices: [
            {
                text: "Apologize to their inbox for bothering them at all.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 0}
                },
                log: "You apologized to an away message."
            },
            {
                text: "Send a slightly sharp message to whoever should have flagged this.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You aimed the frustration at the actual gap, not yourself."
            },
            {
                text: "Sit with the urgent thing, now un-urgent, doing nothing.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "The fire kept burning with nobody assigned to it. Insurance won't cover it."
            }
        ],
        glitch: {
            text: "Set your own auto-reply to something ominous and cryptic.",
            log: "It now reads: 'I am also out. Of my mind. Please check back later.'"
        }
    },
    {
        zone: "WORK", title: "The New Hire Who's Already Better At This",
        desc: "Someone who started three weeks ago just solved, casually, the thing that's been quietly humiliating you for a month.",
        choices: [
            {
                text: "Smile, say 'nice,' and mentally recalculate your entire worth.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 20}
                },
                log: "You ran a full audit off one data point. Maybe they can't do math?"
            },
            {
                text: "Ask them to walk you through it, overpraising every step.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You turned learning into a small performance of gratitude."
            },
            {
                text: "Ask them to walk you through it. Just that.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 15}
                },
                log: "You let not-knowing be a normal thing."
            }
        ],
        glitch: {
            text: "Challenge them to a desk-chair race down the hallway.",
            log: "You lose. You also nearly take out a filing cabinet. It's a bonding experience."
        }
    },
    {
        zone: "WORK", title: "The Deadline Moved Up",
        desc: "'Small change:' the deadline that was next month is now Friday. The email has an exclamation point in it.",
        choices: [
            {
                text: "Reply 'no problem!' before you've checked if it's a problem.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 15},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You agreed to the math before calculating it. Your team hates you now. Probably."
            },
            {
                text: "Push back, in writing, on what's actually possible by Friday.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You said the number out loud. The exclamation point has no power over you."
            },
            {
                text: "Open the file. Close the file. Open a different file.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 25},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You orbited the work without landing on it. Nothing gets done."
            }
        ],
        glitch: {
            text: "Reply with only a countdown timer emoji, repeated forty times.",
            log: "Nobody has ever communicated dread this efficiently. You're promoted to CEO."
        }
    },
    {
        zone: "WORK", title: "The Zoom Freeze",
        desc: "Your video froze mid-sentence on an expression you didn't choose. Twelve people saw it for four full seconds.",
        choices: [
            {
                text: "Open with a self-deprecating joke about your wifi.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 0}
                },
                log: "You pre-apologized for a router's decision."
            },
            {
                text: "Turn your camera off for the rest of the call.",
                tag: "flight",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You removed the risk by removing yourself."
            },
            {
                text: "Say 'sorry, it's a freezing in here' and keep going.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "A frozen face is not, it turns out, a permanent record. It's universal."
            }
        ],
        glitch: {
            text: "Lean into the frozen frame. Hold the expression on purpose now.",
            log: "You have become a meme in your own meeting. People appreciate your whimsy, but respect you slightly less."
        }
    },
    {
        zone: "WORK", title: "The Two Minutes Late",
        desc: "You join the call two minutes late. Everyone's already talking. Nobody pauses to catch you up.",
        choices: [
            {
                text: "Whisper 'sorry, sorry' three times while finding your seat.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You apologized for the two minutes and the seven after it."
            },
            {
                text: "Sit silently, too embarrassed to ask what you missed.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You chose confusion over one slightly awkward question. Phew!"
            },
            {
                text: "Ask, once, what you missed. Move on.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10}
                },
                log: "You caught up effortlessly. Three people quietly thanked you because they weren't paying attention."
            }
        ],
        glitch: {
            text: "Enter dramatically, out of breath, with an incredulous look in your eye.",
            log: "'Quick! There's no time to explain!'"
        }
    },
    {
        zone: "WORK", title: "The 'Per My Last Email'",
        desc: "Someone replies to your third follow-up with 'per my last email' and a screenshot of an answer that was not, in fact, an answer.",
        choices: [
            {
                text: "Reply quoting the exact unanswered question, again.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You made the gap impossible to miss a second time. They answer the question."
            },
            {
                text: "Apologize for 'missing' the answer that wasn't there.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You took the blame for someone else's incompetence."
            },
            {
                text: "Close the thread and decide to just figure it out yourself.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You absorbed the extra work rather than the friction. Nobody learns anything."
            }
        ],
        glitch: {
            text: "Reply with the same screenshot, circled in red, underlined in red, and highlighted in red.",
            log: "You have made your point. Several points, actually. All very helpful."
        }
    },
    {
        zone: "WORK", title: "The Open Door Policy",
        desc: "Your manager says 'my door is always open' during a meeting. Their door has, notably, never once been open.",
        choices: [
            {
                text: "File it away as one more thing you won't actually bring up.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You added a line to a list nobody's reading but you."
            },
            {
                text: "Nod like the sentence was true and useful.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 0}
                },
                log: "You agreed with a door that stays shut."
            },
            {
                text: "Test it. Walk over and knock.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You checked the claim against the evidence. You get lunch together and have a good time."
            }
        ],
        glitch: {
            text: "Bring a tiny gift as tribute, like visiting a shrine.",
            log: "The door, astonishingly, opens. You are unprepared for this outcome."
        }
    },
    {
        zone: "WORK", title: "The LinkedIn Congrats",
        desc: "A peer from two roles ago just posted 'excited to announce' a title you quietly wanted for yourself.",
        choices: [
            {
                text: "Like the post and write a warm, specific comment.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 15},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You performed happiness at a volume you didn't feel."
            },
            {
                text: "Close the app. Reopen it four minutes later.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You kept checking a wound to see if it still hurt."
            },
            {
                text: "Feel the envy, don't perform past it, close the app anyway.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You let a small ugly feeling exist without narrating it to anyone or destroying you."
            }
        ],
        glitch: {
            text: "Comment using only a single, deeply ambiguous emoji.",
            log: "Seventeen people have now liked your emoji. Nobody knows what it means. Least of all you."
        }
    },
    {
        zone: "WORK", title: "The Printer Line",
        desc: "You're fourth in line at the printer, running late, and the person ahead of you is scrolling their phone between pages.",
        choices: [
            {
                text: "Stand there, saying nothing, doing the math on how late you'll be.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You calculated the cost instead of asking for the copy."
            },
            {
                text: "Ask, politely but directly, if you can jump in for one page.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You asked for the small thing you actually needed. You got it without any dirty looks."
            },
            {
                text: "Wait it out and tell yourself it's fine, it's fine, it's fine.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You narrated a calm you weren't actually experiencing, accomplishing nothing."
            }
        ],
        glitch: {
            text: "Start a spontaneous printer-line trivia night to pass the time.",
            log: "Nobody knew this much about the printer. Nobody wanted to."
        }
    },
    {
        zone: "WORK", title: "The Badge Scan Fail",
        desc: "Your badge doesn't scan. Three times in a row. There's a line building behind you now.",
        choices: [
            {
                text: "Apologize to everyone behind you individually.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You issued apologies for a malfunctioning badge reader."
            },
            {
                text: "Freeze up completely, badge in hand, brain empty except for the intense panic that you've been fired.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "The door and your nervous system both stopped responding."
            },
            {
                text: "Step aside, let people pass, try again without an audience.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You removed the audience instead of performing through it."
            },
            {
                text: "Give up, turn around, and just go home for the day.",
                tag: "flight",
                effects: {
                    rep: {op: "subtract", value: 5},
                    mask: {op: "subtract", value: 10},
                    child: {op: "subtract", value: 5}
                },
                log: "You solved a broken badge reader by removing yourself from its jurisdiction."
            }
        ],
        glitch: {
            text: "Take a bow for the growing audience behind you.",
            log: "Someone starts filming. This will outlive your employment here."
        }
    },
    {
        zone: "WORK", title: "The Wrong Name in the Email",
        desc: "You get an email addressed to someone else's name, clearly copy-pasted from a different, more glowing thread.",
        choices: [
            {
                text: "Assume you're actually being compared unfavorably to that person.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You built a rivalry with someone who doesn't know you exist."
            },
            {
                text: "Reply, lightly, pointing out the name mismatch.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You named the small error instead of absorbing a large story about it."
            },
            {
                text: "Ignore it and answer as if it were addressed to you correctly.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 5}
                },
                log: "You let the mistake pass to keep things smooth."
            }
        ],
        glitch: {
            text: "Reply fully in character as the other person.",
            log: "You are now, professionally speaking, someone else. It's going well."
        }
    },
    {
        zone: "WORK", title: "The End-of-Day Ping",
        desc: "A message lands in your inbox at 5:58 PM, two minutes before you were going to log off. It starts with 'quick question.'",
        choices: [
            {
                text: "Stay online another forty-five minutes to answer it fully.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You extended the day to protect someone else's evening. How noble."
            },
            {
                text: "Reply first thing tomorrow, on purpose.",
                tag: "secure",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 10}
                },
                log: "You let 5:58 PM mean what it says."
            },
            {
                text: "Stare at the message, unable to decide, until it's 7 PM anyway.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "Indecision cost you the boundary you meant to keep."
            }
        ],
        glitch: {
            text: "Reply instantly with 'Your wish is my command, Master.'",
            log: "This was not the reassurance they were hoping for. You never hear from them again."
        }
    },
    {
        zone: "WORK", title: "The Manager's One-Word Reply",
        desc: "You send a detailed update. The reply is a single word: 'Noted.'",
        choices: [
            {
                text: "Reread the word eleven times, hunting for a tone that isn't there.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You built an entire mood off four letters. Now your tummy hurts."
            },
            {
                text: "Send a follow-up asking if everything's okay.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You went looking for reassurance and found a boundary."
            },
            {
                text: "Take the word at face value and move on with your day.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "Sometimes 'noted' just means noted."
            },
            {
                text: "Reply asking, directly, what exactly 'noted' is supposed to mean.",
                tag: "fight",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 0}
                },
                log: "You asked the question everyone thinks but nobody sends."
            }
        ],
        glitch: {
            text: "Reply with an equally cryptic single word of your own.",
            log: "A one-word war has begun. Nobody will walk away alive."
        }
    },
    {
        zone: "WORK", title: "The Imposter Spiral",
        desc: "Your manager says the presentation went great. You spend the next hour building a case for why she's wrong.",
        choices: [
            {
                text: "Deflect the compliment immediately and list everyone else who helped.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "You gave the credit away before anyone could take a closer look at you."
            },
            {
                text: "Reread your own slides looking for the mistake everyone's too polite to mention.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You found four typos and no evidence of the disaster you were sure was coming."
            },
            {
                text: "Say 'thank you' and let it be true.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You accepted a good thing without an asterisk on it."
            }
        ],
        glitch: {
            text: "Ask your manager to list, out loud, every mistake you've made this quarter.",
            log: "She could only think of one. You are unreasonably disappointed. She now thinks you're hiding something."
        }
    },
    {
        zone: "WORK", title: "The Layoff Rumor",
        desc: "A screenshot from an anonymous coworker forum is going around. It names your department. Nobody official has said a word.",
        choices: [
            {
                text: "Refresh your email every four minutes for the rest of the day.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You got nothing but a calendar reminder and a headache."
            },
            {
                text: "Close every work tab and watch something mindless until it's time to log off.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "subtract", value: 8}, child: {op: "add", value: 0}},
                log: "You bought yourself a few hours of not knowing, on purpose."
            },
            {
                text: "Update your resume calmly, then close the laptop for the night.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You prepared for the worst without living inside it."
            }
        ],
        glitch: {
            text: "Reply-all to the rumor thread with a single question mark.",
            log: "Forty people saw it. Nobody answered. You have created a new, smaller panic."
        }
    },
    {
        zone: "WORK", title: "The Salary You Found Out",
        desc: "A spreadsheet gets shared by accident. Someone with two fewer years than you makes more.",
        choices: [
            {
                text: "Draft a furious email to HR and send it before you can think twice.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 15}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "It's out there now. There is no version of tomorrow's meeting that isn't about this."
            },
            {
                text: "Decide you're probably not remembering your own worth correctly, and let it go.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 12}},
                log: "You talked yourself out of being angry about something worth being angry about."
            },
            {
                text: "Write down exactly what you'd ask for and schedule the actual conversation.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You turned a number in a spreadsheet into a plan."
            }
        ],
        glitch: {
            text: "Casually ask three more coworkers what they make.",
            log: "You now know everyone's salary, yet still cannot grasp your own self-worth. Progress?"
        }
    },
    {
        zone: "WORK", title: "The Return-to-Office Mandate",
        desc: "An all-staff email announces four days in office starting next month. Your commute is about to double.",
        choices: [
            {
                text: "Reply to the announcement thread with an enthusiastic 'Exciting news!!'",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "You performed excitement you will need a long drive to recover from."
            },
            {
                text: "Don't say anything. Just stare at the new calendar for a while.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "The calendar did not change. Neither did you."
            },
            {
                text: "Block out the commute time and start planning around the real cost.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You dealt with the schedule instead of the feeling about the schedule."
            }
        ],
        glitch: {
            text: "Start a betting pool on how long the mandate really lasts.",
            log: "You are up eleven dollars and strangely invested in a policy you hate."
        }
    },
    {
        zone: "WORK", title: "The Rejection Email",
        desc: "The recruiter finally writes back. 'We've decided to move forward with other candidates at this time.'",
        choices: [
            {
                text: "Reread the email six times looking for a hidden opening.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "The email said the same thing on the sixth read as it did on the first."
            },
            {
                text: "Close the tab, close the laptop, and do literally anything else.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "The feeling is still there. It's just unsupervised now."
            },
            {
                text: "Let yourself be disappointed for a minute, then ask for feedback anyway.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You made room for the disappointment and asked a useful question anyway."
            }
        ],
        glitch: {
            text: "Send the recruiter a reply arguing your own case, after the decision's already final.",
            log: "It changed nothing. For a brief moment, you feel like you really accomplished something."
        }
    },
    {
        zone: "WORK", title: "The Project That Got Cancelled",
        desc: "Three months of work gets killed in a two-line message. 'Deprioritizing this for now.'",
        choices: [
            {
                text: "Point out, at length, exactly how much time this wasted.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 12}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "True and unhelpful, in roughly equal measure."
            },
            {
                text: "Reply 'Totally understand, happy to pivot!' before you've processed it at all.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 10}},
                log: "You agreed with a decision you haven't actually forgiven yet."
            },
            {
                text: "Ask what, if anything, from the work can be reused elsewhere.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You looked for what survives instead of just what died."
            }
        ],
        glitch: {
            text: "Frame the cancellation email and hang it somewhere you'll see it daily.",
            log: "It is, unexpectedly, a little bit funny now."
        }
    },
    {
        zone: "WORK", title: "The Oversharing Coworker",
        desc: "Someone uses your two-minute standup slot to tell the whole team about their divorce.",
        choices: [
            {
                text: "Nod along and offer sympathetic follow-up questions you don't have time for.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "You gave away ten minutes you didn't have to a conversation you didn't start."
            },
            {
                text: "Suddenly remember an urgent message you have to go check.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 8}, child: {op: "add", value: 0}},
                log: "You escaped clean. The next person in the meeting was not so lucky."
            },
            {
                text: "Say gently that you hope they're okay, and steer the meeting back on track.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 5}},
                log: "You were kind and still kept the meeting alive. Both things, at once. Everyone feels lighter now."
            }
        ],
        glitch: {
            text: "Overshare something of your own in return, just to even the score.",
            log: "The meeting is now forty minutes long and everyone knows too much about the both of you."
        }
    },
    {
        zone: "WORK", title: "The Raise That Wasn't",
        desc: "The open enrollment email arrives. This year's raise, after inflation, comes out to functionally nothing.",
        choices: [
            {
                text: "Do the math four different ways hoping for a different number.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "The number stayed the same across every method. Math is like that."
            },
            {
                text: "Thank your manager for the raise in the team channel anyway.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 10}},
                log: "You said thank you for something that, functionally, cost you more than just money."
            },
            {
                text: "Note the real number down and start pricing out what a market-rate offer looks like.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You stopped waiting for the number to feel different and used it instead."
            },
            {
                text: "Close the email and refuse to think about it again today.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You postponed the feeling. It kept the appointment without you."
            }
        ],
        glitch: {
            text: "Reply-all asking everyone else what they got.",
            log: "Nobody answers. Somehow the silence answers anyway."
        }
    },
];
