const CONTENT_EVENTS_SOCIAL = [
    {
        zone: "SOCIAL", title: "The Grocery Store Aisle",
        desc: "Someone is standing exactly in front of the specific brand of oat milk you need. They are taking a very long time.",
        choices: [
            {
                text: "Pretend to look at regular milk until they leave.",
                tag: "freeze",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 15}
                },
                log: "You sacrificed your time and dignity to avoid taking up space."
            },
            {
                text: "Say 'Excuse me' using a voice three octaves higher than normal.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 5}
                },
                log: "You utilized the Customer Service Voice. It was effective."
            },
            {
                text: "Abandon the oat milk. You didn't deserve it anyway.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 15}
                },
                log: "You punished yourself for a stranger's existence."
            }
        ],
        glitch: {
            text: "Ask them, with complete sincerity, for their thoughts on oat milk.",
            log: "You now know more about oat milk than many food scientists. And have a new tennis partner."
        }
    },
    {
        zone: "SOCIAL", title: "The Unread Notification",
        desc: "A friend replied 'K.' to a vulnerable paragraph you sent them.",
        choices: [
            {
                text: "Start searching for a completely new friend group.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 20}
                },
                log: "You burned the bridge in your mind before there was even a threat to identify."
            },
            {
                text: "Send 4 memes immediately to lighten the mood.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You performed as the Jester to avoid abandonment. They didn't react. You internalize it deeply."
            },
            {
                text: "Throw your phone into a soft pile of laundry.",
                tag: "flight",
                effects: {mask: {op: "add", value: 0}, child: {op: "add", value: 10}, rep: {op: "add", value: 10}},
                log: "You successfully removed the object of your pain. Until you get up again."
            }
        ],
        glitch: {
            text: "Reply with a single, deeply dramatic tear emoji.",
            log: "They have not responded. The emoji stands alone, weeping into the void."
        }
    },
    {
        zone: "SOCIAL", title: "The Uneven Text Energy",
        desc: "Your friend's reply is just 'oh nice' where it used to be three exclamation points.",
        choices: [
            {
                text: "Reread every message you've sent them for the last month.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    child: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0}
                },
                log: "You audited a friendship for crimes that don't exist."
            },
            {
                text: "Match their energy exactly, one for one.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 0},
                    rep: {op: "add", value: 5}
                },
                log: "Mutually assured emotional disengagement."
            },
            {
                text: "Text them something low-stakes and let it go.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 5},
                    child: {op: "add", value: 5}
                },
                log: "You extended trust without an audit."
            }
        ],
        glitch: {
            text: "Reply with forty exclamation points, unprompted, for no reason at all.",
            log: "You have single-handedly restored the energy. Possibly too much of it."
        }
    },
    {
        zone: "SOCIAL", title: "The Group Photo",
        desc: "You've been tagged. The angle is unkind. Twelve people have already liked it.",
        choices: [
            {
                text: "Untag yourself and message the poster to take it down.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You defended your own image. Feels illegal, but it isn't."
            },
            {
                text: "Leave it up and never look at that post again.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You conceded to the internet, this round."
            },
            {
                text: "Zoom in and catalog every flaw for later.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    child: {op: "subtract", value: 20},
                    mask: {op: "add", value: 0}
                },
                log: "You built a case against your own face. You won... but also... lost?"
            }
        ],
        glitch: {
            text: "Comment demanding a formal recount of the likes.",
            log: "The recount is denied. Someone photoshops a powdered wig on your head in said photo. Democracy has failed you again."
        }
    },
    {
        zone: "SOCIAL", title: "The Small Talk Void",
        desc: "Stuck in an elevator with a coworker for four floors. Neither of you has said anything for eleven seconds.",
        choices: [
            {
                text: "Comment on the elevator's slowness like it's breaking news.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 0}
                },
                log: "You filled the void with the safest possible noise. Coward."
            },
            {
                text: "Stare at the floor numbers with religious intensity.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 0}
                },
                log: "You willed the doors open through sheer discomfort."
            },
            {
                text: "Let the silence be silence.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 5},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 5}
                },
                log: "Eleven seconds of quiet did not erase you from this earth."
            },
            {
                text: "Turn and ask, flatly, why neither of you will just say something real.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 12},
                    child: {op: "add", value: 3},
                    rep: {op: "subtract", value: 8}
                },
                log: "You called out the silence instead of just enduring it. The doors open two floors later. Not soon enough for either of you."
            }
        ],
        glitch: {
            text: "Start humming the elevator music, badly, out loud.",
            log: "The coworker joins in. This is now, somehow, a duet."
        }
    },
    {
        zone: "SOCIAL", title: "The Friend Who Remembers Everything",
        desc: "Someone brings up a specific thing you said, off-hand, eight months ago. You have no memory of saying it.",
        choices: [
            {
                text: "Panic-scan your own memory for context you don't have.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 0}
                },
                log: "You audited a version of yourself with no paper trail and came up empty handed."
            },
            {
                text: "Agree enthusiastically, like you absolutely remember.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You co-signed a memory that isn't yours."
            },
            {
                text: "Say 'I don't actually remember that, tell me more,' and mean it.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10}
                },
                log: "You let not-knowing be an ordinary, survivable thing. Because it is."
            },
            {
                text: "Change the subject fast, before anyone asks a follow-up question.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 8},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 10}
                },
                log: "You steered the conversation somewhere you actually remembered."
            }
        ],
        glitch: {
            text: "Claim, boldly, that you were actually a time traveler that day.",
            log: "They seem to accept this explanation more readily than expected. Someone asks if you've ever met Elvis."
        }
    },
    {
        zone: "SOCIAL", title: "The RSVP You Regret",
        desc: "You said yes to something two weeks ago. It's tonight. Every fiber of you wants to cancel.",
        choices: [
            {
                text: "Draft a vague excuse about not feeling well.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 10}
                },
                log: "You built an exit out of half a lie. Nobody believes you."
            },
            {
                text: "Text 'actually can't make it' with zero elaboration and hit send.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 10}
                },
                log: "You chose a white lie over a padded truth. You spend the night regretting it."
            },
            {
                text: "Go anyway. Perform enthusiasm you do not currently possess.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 15},
                    child: {op: "subtract", value: 20},
                    rep: {op: "add", value: 10}
                },
                log: "You showed up as the version of you that RSVPs on time. You have fun, anyway."
            }
        ],
        glitch: {
            text: "Show up in full costume, several genres removed from the event's theme.",
            log: "You are the only knight at what turns out to be a beach party."
        }
    },
    {
        zone: "SOCIAL", title: "The Table for One",
        desc: "You get to the restaurant first. You sit alone at a table for four, aware of exactly how alone you look.",
        choices: [
            {
                text: "Stare at your phone intensely so you look busy, not waiting.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You performed occupation to avoid looking like a 'loser.'"
            },
            {
                text: "Apologize to the host for taking up a table for four.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You apologized for the size of a table you didn't choose."
            },
            {
                text: "Sit there. Look around. Let it be fine.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "Being visibly alone turned out fine; you got fresh breadsticks and didn't have to share any."
            },
            {
                text: "Flag down the host and ask, pointedly, if a smaller table's actually available.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 12},
                    child: {op: "add", value: 3},
                    rep: {op: "subtract", value: 8}
                },
                log: "You made the awkwardness someone else's problem to solve."
            }
        ],
        glitch: {
            text: "Order enough food for four and narrate an imaginary dinner party.",
            log: "Your imaginary guests are excellent listeners and terrible tippers."
        }
    },
    {
        zone: "SOCIAL", title: "The Best Man Speech You Weren't Asked to Give",
        desc: "You've known the groom for a decade. Someone he met two years ago is giving the speech instead.",
        choices: [
            {
                text: "Smile through the whole thing while doing quiet, silent math.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You ran a decade-long audit during a five-minute toast."
            },
            {
                text: "Decide to bring it up with him, gently, another day.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You chose to name the hurt instead of just carrying it."
            },
            {
                text: "Clap the loudest and mean absolutely none of it.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You applauded harder than you felt to hide what you felt."
            }
        ],
        glitch: {
            text: "Stand up anyway and deliver your own speech, uninvited, mid-reception.",
            log: "The DJ, unsure what else to do, plays dramatic entrance music."
        }
    },
    {
        zone: "SOCIAL", title: "The Group Chat Without You",
        desc: "You find out, by accident, that there's a group chat that doesn't include you. It's been active for months.",
        choices: [
            {
                text: "Say nothing and quietly recalibrate every friendship in your life.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 20}
                },
                log: "One missing chat became a referendum on everyone you know."
            },
            {
                text: "Act completely unbothered, performing it a little too well.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You built a very convincing case for a feeling you don't have."
            },
            {
                text: "Notice it stings. Don't make it a bigger story than it is.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "A feeling, felt and not expanded, passes on its own."
            },
            {
                text: "Mute every group chat you're actually in, preemptively.",
                tag: "flight",
                effects: {
                    rep: {op: "subtract", value: 5},
                    mask: {op: "subtract", value: 5},
                    child: {op: "subtract", value: 10}
                },
                log: "You left before anyone could leave you out again."
            }
        ],
        glitch: {
            text: "Start your own group chat. Name it something deeply petty.",
            log: "It has one member. It is thriving."
        }
    },
    {
        zone: "SOCIAL", title: "The Birthday They Forgot",
        desc: "It's 8 PM. No text, no call, nothing. You know they're busy. You also know that nobody is THAT busy...",
        choices: [
            {
                text: "Refresh your phone every few minutes without admitting why.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You kept checking for something you'd already concluded wasn't coming."
            },
            {
                text: "Post something upbeat so nobody suspects you noticed.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You broadcast 'fine' to cover a very 'not fine' feeling."
            },
            {
                text: "Let yourself be a little sad about it. That's allowed.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You didn't need to perform that you were okay, you arrived there naturally."
            },
            {
                text: "Text them directly: 'did you forget what today is?'",
                tag: "fight",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 0}
                },
                log: "You forced the acknowledgment you were hoping would arrive on its own."
            }
        ],
        glitch: {
            text: "Throw yourself a tiny, one-person parade around the living room.",
            log: "The confetti will be found in strange places for weeks."
        }
    },
    {
        zone: "SOCIAL", title: "The Ex at the Party",
        desc: "You didn't know they'd be here. They just walked in, laughing at something, not looking your way yet.",
        choices: [
            {
                text: "Find a reason to be near the exit for the rest of the night.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 15}
                },
                log: "You mapped an escape route instead of a plan."
            },
            {
                text: "Go say 'hi' first, overly warm, before they can find you.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 15},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You got there first so you could control the narrative. They still make you feel powerless."
            },
            {
                text: "Stay where you are. Say 'hi' if it happens naturally.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You didn't need to manage the whole room to survive; they left before they even saw you."
            }
        ],
        glitch: {
            text: "Walk over and loudly introduce yourself as if you'd never met.",
            log: "This confuses everyone, including, eventually, you."
        }
    },
    {
        zone: "SOCIAL", title: "The Compliment That Felt Like An Audit",
        desc: "'You look so much better than you used to' is technically a compliment, but it somehow lands like backdoor insult.",
        choices: [
            {
                text: "Say 'thank you!' brightly and file the sting away for later.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You smiled through the part that actually stung. Now your face hurts."
            },
            {
                text: "Say, lightly, 'I looked good before, too!'",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You corrected the record instead of just absorbing it. Mad respect."
            },
            {
                text: "Replay the sentence for the rest of the night.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "One sentence got more airtime in your head than the whole party. It's all you remember."
            }
        ],
        glitch: {
            text: "Demand a formal, itemized list of exactly how much better.",
            log: "They did not prepare a spreadsheet. You are disappointed in their inability to verify their claims."
        }
    },
    {
        zone: "SOCIAL", title: "The Awkward Wave",
        desc: "Someone you sort of know, from somewhere, makes eye contact across the room. Neither of you commits to a greeting.",
        choices: [
            {
                text: "You suddenly find your phone extremely interesting.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 10}
                },
                log: "You disappeared into a screen to avoid making a decision."
            },
            {
                text: "Overcommit to a huge wave and walk over, unsure why.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You escalated a maybe into a whole interaction. It's awkward."
            },
            {
                text: "Give a small, real wave and let it be whatever it is.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 5},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 5}
                },
                log: "A half-known person got a half-committed, perfectly adequate wave."
            }
        ],
        glitch: {
            text: "Commit fully: sprint over and hug a near-stranger.",
            log: "This escalated several social contracts beyond what either of you agreed to. It feels right."
        }
    },
    {
        zone: "SOCIAL", title: "The Split-the-Bill Math",
        desc: "You had a salad and water. Someone else had three cocktails. The bill's being split evenly, and everyone's already agreeing.",
        choices: [
            {
                text: "Agree to split evenly and say nothing about the math.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You paid for cocktails you didn't order to keep the table easy."
            },
            {
                text: "Suggest, casually, splitting it by what people actually got.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You named the math out loud before it became a resentment. Three other people suddenly agree."
            },
            {
                text: "Pay your share silently and feel weird about it for days.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You let a bill you didn't agree with become a slow-burn grudge."
            }
        ],
        glitch: {
            text: "Produce an actual calculator and do the math out loud, in real time.",
            log: "The table goes silent. You do not stop calculating."
        }
    },
    {
        zone: "SOCIAL", title: "The Unfollow You Noticed",
        desc: "You don't know when it happened. You just noticed, scrolling, that a number is one smaller than it used to be.",
        choices: [
            {
                text: "Spend twenty minutes trying to figure out who it was.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You ran a full investigation into a single missing follower."
            },
            {
                text: "Post something extra likable to make up the difference.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You tried to outperform a number you can't actually see."
            },
            {
                text: "Close the app. It's one person. It's fine.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "A number went down. The sky, notably, did not fall. Their loss."
            },
            {
                text: "Close the app entirely and delete it off your homescreen for the rest of the day.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 8},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 10}
                },
                log: "You removed the scoreboard instead of the score."
            }
        ],
        glitch: {
            text: "Post a single cryptic status implying you know things.",
            log: "You know anything. The mystery deepens for everyone, including you."
        }
    },
    {
        zone: "SOCIAL", title: "The Small Talk About The Weather (Again)",
        desc: "Third conversation this week that's stayed entirely on the weather. You're both clearly capable of more.",
        choices: [
            {
                text: "Keep it light and safe, matching their energy exactly.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You stayed in the shallow end because it felt safer there."
            },
            {
                text: "Ask a real question and see what happens.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You risked it all on a real question. You get a real answer (and a new friend)."
            },
            {
                text: "Let the conversation end there, like it always does.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "Another conversation stayed exactly as deep as the last one. This is fine."
            }
        ],
        glitch: {
            text: "Deliver an unhinged, overly dramatic weather forecast, mid-conversation.",
            log: "'Sixty percent chance of feelings, with scattered vulnerability by evening.'"
        }
    },
    {
        zone: "SOCIAL", title: "The Party You Left Early",
        desc: "You said you were tired. You weren't tired. You just needed to be somewhere with fewer people in it.",
        choices: [
            {
                text: "Send an apologetic follow-up text explaining yourself.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You justified a boundary nobody actually questioned."
            },
            {
                text: "Lie awake replaying whether anyone noticed you'd gone.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You audited an exit that was, to everyone else, unremarkable and totally fine."
            },
            {
                text: "Let leaving early just be a thing you did. No debrief required.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You left when you needed to and didn't file a report about it. Irish Goodbye FTW."
            },
            {
                text: "Tell the group chat directly: 'I left because it was too much. That's allowed.'",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 8}
                },
                log: "You defended a boundary nobody was actually attacking."
            }
        ],
        glitch: {
            text: "Send the group a full, dramatic exit statement the next morning.",
            log: "It reads like a press release nobody asked for."
        }
    },
    {
        zone: "SOCIAL", title: "The Toast You Weren't Mentioned In",
        desc: "A long, warm speech naming almost everyone important in the room. Almost.",
        choices: [
            {
                text: "Smile and clap while quietly re-ranking your own importance.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "A toast became a scoreboard, and you lost."
            },
            {
                text: "Compliment the speech extra hard afterward.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You praised loudest the thing that left you out."
            },
            {
                text: "Let one omission be one omission, not a verdict.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You weren't named. You were, notably, still there."
            },
            {
                text: "Mention to them afterward that you noticed.",
                tag: "fight",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 0}
                },
                log: "You turned a passing feeling into a conversation neither of you wanted to have at a party."
            }
        ],
        glitch: {
            text: "Stand up and propose your own toast, entirely about yourself.",
            log: "It runs nine minutes. The room claps out of confusion, mostly."
        }
    },
    {
        zone: "SOCIAL", title: "The New Best Friend",
        desc: "Your friend has a new person they mention constantly now. Inside jokes you're not part of. You're happy for them. Mostly.",
        choices: [
            {
                text: "Ask enthusiastic questions about the new friend, overselling interest.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You interviewed your own replacement with a big smile on."
            },
            {
                text: "Quietly pull back from making plans, without saying why.",
                tag: "flight",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You left the room before anyone asked you to."
            },
            {
                text: "Name the feeling to yourself: a little jealous, and that's okay.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "Jealousy, acknowledged, took up less room than it usually does. You all go out for drinks later."
            }
        ],
        glitch: {
            text: "Invent an equally mysterious new friend of your own to mention constantly.",
            log: "Your new friend, 'Gary,' may not exist, but this plan works better than expected."
        }
    },
    {
        zone: "SOCIAL", title: "The 'We Should Hang Out Sometime' That Never Happens",
        desc: "The fourth time this month someone's said it. Nobody, including you, has ever proposed an actual date.",
        choices: [
            {
                text: "Say it back warmly, knowing it means nothing either time.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You kept a nice-sounding ritual alive instead of a friendship."
            },
            {
                text: "Actually suggest a specific day and time.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 10},
                    rep: {op: "subtract", value: 5}
                },
                log: "You called the bluff, including your own. It worked."
            },
            {
                text: "Let the phrase pass, again, unchallenged.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "A fifth 'sometime' joined the pile of the first four. Nice collection!"
            }
        ],
        glitch: {
            text: "Actually pull out a calendar and demand a date, right now.",
            log: "A date gets picked. Everyone is stunned, including the calendar."
        }
    },
    {
        zone: "SOCIAL", title: "The Loud Laugh Across The Room",
        desc: "A burst of laughter from a group nearby. You have no evidence it's about you. You're immediately certain it is.",
        choices: [
            {
                text: "Replay your last ten minutes of behavior for embarrassing material.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You conducted a full review with zero actual evidence."
            },
            {
                text: "Change your position in the room, just in case.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 10}
                },
                log: "You relocated to escape a theory you invented about yourself."
            },
            {
                text: "Let the laugh be about literally anything else. It probably is.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "Most laughter in a crowded room has nothing to do with you. Unless you're doing something funny."
            }
        ],
        glitch: {
            text: "Walk over and ask, directly, if it's about you.",
            log: "It was not about you...Well, it is *now*... but it wasn't, then Thank God. That could have been embarrassing."
        }
    },
    {
        zone: "SOCIAL", title: "The Seat Saved For Someone Else",
        desc: "You go to sit down. 'Oh, that one's taken,' said kindly, but you're now standing in a room full of seated people.",
        choices: [
            {
                text: "Laugh it off and hover near the wall instead.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You made your own displacement look easy."
            },
            {
                text: "Stand there a beat too long, unsure what to do with your body.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "A missing chair became a small, public crisis."
            },
            {
                text: "Ask, simply, if there's another seat open.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You solved a chair problem with a chair question."
            },
            {
                text: "Slip out to the hallway for a few minutes until you can casually reappear.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 8},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 10}
                },
                log: "You gave the room time to forget it saw you standing there."
            }
        ],
        glitch: {
            text: "Sit on the floor with great, theatrical dignity instead.",
            log: "The floor, it turns out, has an excellent view."
        }
    },
    {
        zone: "SOCIAL", title: "The Group Trip You Weren't Invited To",
        desc: "The photos are already up. It looks like it was a good one. You didn't know it was happening.",
        choices: [
            {
                text: "Scroll through every photo, cataloging who's in how many.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You turned a vacation you weren't on into forensic evidence."
            },
            {
                text: "Like every photo enthusiastically, extra hearts included.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You applauded a trip that stung to see."
            },
            {
                text: "Close the app. Ask yourself later, calmly, if it's worth mentioning.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You gave the feeling time before deciding what to do with it."
            },
            {
                text: "Message the group directly: 'Hey, was this planned somewhere else? Just noticed.'",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 12},
                    child: {op: "add", value: 3},
                    rep: {op: "subtract", value: 8}
                },
                log: "You asked the question out loud instead of just doing math about it in silence."
            }
        ],
        glitch: {
            text: "Comment on every photo with a single, ominous 'interesting.'",
            log: "Nobody knows what you meant. You are not entirely sure either. Regardless, it IS interesting."
        }
    },
    {
        zone: "SOCIAL", title: "The Voicemail You Haven't Listened To",
        desc: "A friend called instead of texting, which never happens. The voicemail icon has been sitting there for two hours.",
        choices: [
            {
                text: "Let it sit. Voicemails are for people who don't need you to reply fast.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "An unopened voicemail can hold a lot of imagined weight."
            },
            {
                text: "Call back immediately, bracing yourself for bad news that might not be there.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You armored up for a threat you hadn't confirmed yet."
            },
            {
                text: "Listen to it first. Then respond to what's actually there.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "The message turned out to be smaller than the dread around it."
            }
        ],
        glitch: {
            text: "Reply via a voicemail of your own, without listening to theirs first.",
            log: "Two unheard voicemails now orbit each other, unopened, forever."
        }
    },
    {
        zone: "SOCIAL", title: "The Dating App Match Who Stopped Responding",
        desc: "Three good days of conversation. Then nothing, mid-sentence, two days ago.",
        choices: [
            {
                text: "Reread the whole conversation looking for the exact moment it went wrong.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "You found nothing. There was nothing to find."
            },
            {
                text: "Send one more message, lighter this time, just in case they missed the first.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 10}},
                log: "You performed casual. It did not feel casual."
            },
            {
                text: "Unmatch and let it be exactly as unremarkable as it actually is.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "Three good days doesn't owe you an explanation for a fourth."
            },
            {
                text: "Delete the app. All of them, actually.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You solved the problem by removing the entire category it lived in."
            }
        ],
        glitch: {
            text: "Rewrite your entire profile at midnight out of spite.",
            log: "You now describe yourself as 'allegedly fun.' It's an improvement, actually."
        }
    },
    {
        zone: "SOCIAL", title: "The Second Date That Never Got Scheduled",
        desc: "The first date was good. You both said 'we should do this again.' Neither of you has said anything since.",
        choices: [
            {
                text: "Decide you're clearly not that interested and move on without saying so.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "Cleaner this way, probably. You'll never actually know."
            },
            {
                text: "Wait for them to text first so you don't seem too eager.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 8}},
                log: "You outlasted your own interest waiting for permission to show it."
            },
            {
                text: "Just text and ask if they want to grab coffee this week.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 8}},
                log: "You said the plain thing instead of waiting for the safer one."
            }
        ],
        glitch: {
            text: "Have a friend text them from your phone 'as a joke.'",
            log: "This has never worked in the history of dating. It does not work now."
        }
    },
    {
        zone: "SOCIAL", title: "The Wedding Invite You Can't Afford to Attend",
        desc: "A close friend's wedding, three states away, right when your budget has zero room for flights and a hotel.",
        choices: [
            {
                text: "RSVP yes and figure out the money somehow, some way you haven't found yet.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 12}},
                log: "You said yes to the invitation and no, quietly, to your own budget."
            },
            {
                text: "Don't respond to the invite for as long as you possibly can.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "The deadline is still coming. It doesn't care that you didn't look at it."
            },
            {
                text: "Call your friend and just tell them the truth about the money.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "The honest version of 'I can't' turned out to cost you less than the performance of 'I'll try.'"
            }
        ],
        glitch: {
            text: "Look into whether you could realistically drive three states in one weekend.",
            log: "You could not. You looked anyway."
        }
    },
    {
        zone: "SOCIAL", title: "The Friend Who Cancelled Last Minute (Again)",
        desc: "Twenty minutes before you were supposed to meet, the same friend cancels for the third time this month.",
        choices: [
            {
                text: "Reply 'no worries at all!' and mean less than half of it.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 10}},
                log: "You absorbed the disappointment so smoothly nobody, including you, noticed it happened."
            },
            {
                text: "Send a pointed text about the pattern, right then, while you're still annoyed.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "Accurate. Badly timed. Both true at once."
            },
            {
                text: "Say it's fine tonight, and bring up the pattern later when you're not standing in your coat.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 8}},
                log: "You separated the moment from the pattern instead of dumping both at once."
            }
        ],
        glitch: {
            text: "Show up to the venue alone anyway, on principle.",
            log: "You had a fine time by yourself. This was not the point you were trying to prove."
        }
    },
    {
        zone: "SOCIAL", title: "The Unsolicited Advice",
        desc: "You mention, in passing, that you're a little stressed. What you get back is a twelve-point plan for fixing your entire life.",
        choices: [
            {
                text: "Nod, say 'that's a good point,' and absorb advice you didn't ask for.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "You outsourced twelve minutes of your afternoon to someone else's certainty."
            },
            {
                text: "Cut them off and say you weren't actually asking for a solution.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 12}, child: {op: "add", value: 0}},
                log: "True, and it landed like a slap instead of a boundary. And made your stress feel worse."
            },
            {
                text: "Say gently that you just wanted to vent, not fix it right now. But thank you.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 8}},
                log: "You named exactly what you needed instead of quietly enduring the wrong thing."
            }
        ],
        glitch: {
            text: "Start giving them unsolicited advice back, immediately, at the same volume.",
            log: "Neither of you asked for this exchange. It is happening regardless."
        }
    },
    {
        zone: "SOCIAL", title: "The Comparison Scroll",
        desc: "You open the app for one notification and forty minutes later you're deep in someone else's vacation photos, doing math on their life against yours.",
        choices: [
            {
                text: "Keep scrolling well past the point it started feeling bad.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You know more about a stranger's trip to Portugal than you do about how you're actually doing right now."
            },
            {
                text: "Like everything, generously, while feeling worse with every tap.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 10}},
                log: "You performed happiness for people while quietly auditing your own life against theirs."
            },
            {
                text: "Close the app and name, out loud, that it's a highlight reel, not a life.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You put the comparison down instead of finishing the whole plate of it."
            },
            {
                text: "Put the phone in another room and go do something with your hands instead.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "subtract", value: 5}},
                log: "You physically separated yourself from the plate you kept refilling."
            }
        ],
        glitch: {
            text: "Post something deliberately, aggressively mundane in response.",
            log: "Twelve people liked a photo of your ceiling. Justice, kinda."
        }
    },
    {
        zone: "SOCIAL", title: "The Wrong Group Chat Text",
        desc: "You mean to vent about your coworker to one friend. You send it to the group chat that includes that coworker.",
        choices: [
            {
                text: "Stare at the message, unsent-but-sent, for a full minute before doing anything.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "Time did not undo it. It rarely does."
            },
            {
                text: "Immediately send eight apology messages in a row.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 10}},
                log: "Eight messages later, everyone has now read about the mistake three extra times."
            },
            {
                text: "Send one clear apology, own it, and stop typing.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 5}, child: {op: "add", value: 8}},
                log: "One honest sentence did more than eight nervous ones would have. The coworker actually admits they deserved it."
            },
            {
                text: "Call the coworker directly and say the thing to their face, badly, right now.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "subtract", value: 18}, child: {op: "add", value: 5}},
                log: "You turned a screenshot into an actual conversation. Braver, and much worse, in real time."
            }
        ],
        glitch: {
            text: "Try to convince everyone your account got hacked.",
            log: "Nobody believes this. You did not really expect them to."
        }
    },
    {
        zone: "SOCIAL", title: "The New Partner Absorbing the Friend Group",
        desc: "Your friend has a new partner, and lately every group hangout quietly becomes about the two of them.",
        choices: [
            {
                text: "Say how much you love the new couple, louder than you feel it.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "You clapped for something that, honestly, you have mixed feelings about."
            },
            {
                text: "Start finding reasons to skip the group hangouts for a while.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 8}, child: {op: "add", value: 0}},
                log: "Easier than saying anything. Also, quietly, lonelier."
            },
            {
                text: "Tell your friend directly that you miss the just-us version of hanging out.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 8}},
                log: "You said how you felt instead of just fading out of the group chat."
            }
        ],
        glitch: {
            text: "'Accidentally' plan something that conflicts with their next couple's outing.",
            log: "Petty, effective, and everyone can tell exactly what you did."
        }
    },
];
