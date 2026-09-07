const CONTENT_EVENTS_SELF = [
    {
        zone: "SELF", title: "The Nostalgic Smell",
        desc: "You catch a whiff of a specific cleaning product that smells exactly like your childhood home.",
        choices: [
            {
                text: "Hold your breath until you pass out.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 30}
                },
                log: "You physically rejected the memory."
            },
            {
                text: "Cry silently in a bathroom stall.",
                tag: "secure",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 20},
                    rep: {op: "subtract", value: 20}
                },
                log: "You processed an emotion privately. It's okay."
            },
            {
                text: "Make a dark, self-deprecating joke to a coworker.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You weaponized the trauma for comedy."
            }
        ],
        glitch: {
            text: "Track down the exact cleaning product, immediately, mid-errand.",
            log: "You now own four bottles of it. This is a lot of bottles."
        }
    },
    {
        zone: "SELF", title: "The Mirror",
        desc: "You catch your reflection in a dark window and don't recognize yourself for a full second.",
        choices: [
            {
                text: "Immediately look away and think about anything else.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 15}
                },
                log: "You declined an invitation from your own face."
            },
            {
                text: "Stare longer, looking for evidence of who you used to be.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    child: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0}
                },
                log: "You interrogated a witness who can't testify."
            },
            {
                text: "Say something kind to it, out loud, even though it's weird.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    child: {op: "add", value: 15},
                    mask: {op: "subtract", value: 5}
                },
                log: "You addressed yourself like a person worth addressing. Because you are."
            }
        ],
        glitch: {
            text: "Introduce yourself to the reflection like you've never met.",
            log: "'Hi, I'm you.' The reflection does not seem convinced either."
        }
    },
    {
        zone: "SELF", title: "The Old Journal Entry",
        desc: "You find something you wrote at nineteen. It is more honest than anything you've said out loud this year.",
        choices: [
            {
                text: "Close it immediately and never mention this happened.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You returned the evidence to its tomb."
            },
            {
                text: "Feel a specific, targeted contempt for who you used to be.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 20},
                    rep: {op: "add", value: 5}
                },
                log: "You picked a fight with a nineteen-year-old and lost."
            },
            {
                text: "Let it be true. You used to know something you forgot.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    child: {op: "add", value: 20},
                    mask: {op: "subtract", value: 5}
                },
                log: "You let an old truth back into the room. It feels warm."
            }
        ],
        glitch: {
            text: "Write a formal reply to nineteen-year-old you, dated properly.",
            log: "You have now started a pen-pal relationship with your past self. Neat!"
        }
    },
    {
        zone: "SELF", title: "The 3 AM Wake-Up",
        desc: "No reason. No noise. Just awake, and your brain has already opened seventeen tabs of decade-old conversations.",
        choices: [
            {
                text: "Start drafting an apology text you'll delete by morning.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You litigated a conversation the other person forgot happened."
            },
            {
                text: "Get up and reorganize something small and physical.",
                tag: "flight",
                effects: {mask: {op: "add", value: 0}, child: {op: "add", value: 0}, rep: {op: "add", value: 10}},
                log: "You redirected 3 AM into a drawer."
            },
            {
                text: "Name it: this is just cortisol, not prophecy.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    child: {op: "add", value: 10},
                    mask: {op: "add", value: 0}
                },
                log: "You out-argued your own nervous system and won. You sleep in victory."
            }
        ],
        glitch: {
            text: "Get up and reorganize the spice rack, aggressively, at 3 AM.",
            log: "The spices are now alphabetized. You are not more at peace, but the spices are."
        }
    },
    {
        zone: "SELF", title: "The Empty Sunday",
        desc: "Nothing is scheduled. Nobody needs anything from you. This is, somehow, the hardest part of the week.",
        choices: [
            {
                text: "Invent an urgent task to feel useful again.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You manufactured a purpose to avoid the quiet."
            },
            {
                text: "Scroll until the day disappears without you in it.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You outsourced eight hours to a feed."
            },
            {
                text: "Sit with the unscheduled hour and let it be boring.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    child: {op: "add", value: 15},
                    mask: {op: "add", value: 0}
                },
                log: "You survived free time without earning it first. You didn't explode."
            }
        ],
        glitch: {
            text: "Declare it a national holiday, just for yourself, effective immediately.",
            log: "Attendance is mandatory. You are the only attendee."
        }
    },
    {
        zone: "SELF", title: "The Unfinished Thing",
        desc: "A project, a hobby, a draft you were once genuinely excited about sits untouched in a folder you avoid opening.",
        choices: [
            {
                text: "Open the folder, look at it, close it again without touching anything.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You visited the grave without bringing flowers or a shovel. Ingrate."
            },
            {
                text: "Start something new and shinier instead.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 10}
                },
                log: "You outran the old excitement with a fresh one."
            },
            {
                text: "Open it. Change one small thing. Close it again.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 15}
                },
                log: "You proved the thing wasn't actually dead, just resting."
            }
        ],
        glitch: {
            text: "Finish it in one unhinged, caffeinated burst, right now.",
            log: "It is done. It is also, somehow, about cats now."
        }
    },
    {
        zone: "SELF", title: "The Accidental Self-Compliment",
        desc: "You catch yourself thinking something kind about your own work, unprompted, and immediately feel weird about it.",
        choices: [
            {
                text: "Correct yourself internally: find the flaw, restore the natural order.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You disqualified the thought before it could get comfortable."
            },
            {
                text: "Change the subject in your own head immediately.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 10}
                },
                log: "You fled a compliment like it was a fire alarm."
            },
            {
                text: "Let the thought stand. Don't correct it. Just let it be true for a second.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "subtract", value: 5},
                    child: {op: "add", value: 20}
                },
                log: "You let something kind about yourself survive contact with your own scrutiny."
            }
        ],
        glitch: {
            text: "Say it out loud again, three more times, increasingly loudly.",
            log: "The dog looks concerned. The compliment stands."
        }
    },
    {
        zone: "SELF", title: "The Song That Still Does This To You",
        desc: "Three seconds of a song you haven't heard in years, and your chest does something you didn't authorize.",
        choices: [
            {
                text: "Skip it immediately and pretend you didn't feel that.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You outran a feeling that was already three seconds ahead of you."
            },
            {
                text: "Get irritated that a song still has this much power over you.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "add", value: 5}
                },
                log: "You picked a fight with your own nervous system. It won."
            },
            {
                text: "Let it play. Feel whatever it wants you to feel.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You let three minutes of sound be exactly as small as it actually was."
            }
        ],
        glitch: {
            text: "Play it on full volume and have a small, unscheduled concert.",
            log: "The neighbors have opinions. You have zero regrets."
        }
    },
    {
        zone: "SELF", title: "The Childhood Photo You Can't Place A Feeling On",
        desc: "You're smiling in it. You don't remember if you were actually happy or just good at looking like it, even then.",
        choices: [
            {
                text: "Stare at it longer, trying to force a memory that isn't there.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You interrogated a photograph for information it doesn't have."
            },
            {
                text: "Decide it must have been a happy day. Move on quickly.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You assigned the photo a feeling so you wouldn't have to sit with the unknown."
            },
            {
                text: "Let 'I don't know how I felt' be a complete, acceptable answer.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "Not knowing turned out to be allowed. It always is."
            },
            {
                text: "Get irritated at the photo for not just telling you the truth.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 0},
                    rep: {op: "subtract", value: 5}
                },
                log: "You picked a fight with a piece of paper. It did not blink first."
            }
        ],
        glitch: {
            text: "Interview the photo directly, out loud, like a documentary subject.",
            log: "The photo declines to comment... Which is ironic, since they're worth a thousand words each, minimum."
        }
    },
    {
        zone: "SELF", title: "The Habit You Picked Up From A Parent",
        desc: "You caught yourself doing the exact thing, the exact way, that used to make you flinch when they did it.",
        choices: [
            {
                text: "Get angry at yourself for the resemblance.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You punished the habit instead of just noticing it."
            },
            {
                text: "Pretend you didn't notice and keep doing it anyway.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You let the pattern run unexamined, again."
            },
            {
                text: "Notice it. Name it. Try, gently, to do the next one differently.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You caught a pattern mid-motion, which is most of the work. Keep going."
            }
        ],
        glitch: {
            text: "Lean all the way in and do a full, committed impression of them.",
            log: "It's uncannily accurate. Everyone is deeply unsettled."
        }
    },
    {
        zone: "SELF", title: "The Voice In Your Head That Isn't Yours",
        desc: "The criticism arrives in a tone you recognize. It's not how you'd talk to anyone else. It's exactly how someone once talked to you.",
        choices: [
            {
                text: "Agree with it. It's probably right, like it always was.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You gave an old voice the final word again."
            },
            {
                text: "Argue back at it, harshly, in your own head.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You fought a voice with the same volume it uses."
            },
            {
                text: "Notice it's not your voice. You don't have to use it.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You separated the message from the messenger you inherited it from."
            }
        ],
        glitch: {
            text: "Talk back to it out loud, in public, with real conviction.",
            log: "A stranger gives you a wide berth. The voice, notably, has no comeback."
        }
    },
    {
        zone: "SELF", title: "The Ache With No Origin",
        desc: "Your shoulders have been up by your ears for an hour and you genuinely cannot remember when that started.",
        choices: [
            {
                text: "Ignore it. It'll probably go away on its own.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You outsourced the problem to future-you. Again. (Jerk)."
            },
            {
                text: "Push through it, it's fine, everyone's tired, this is normal.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 10}
                },
                log: "You neglected a body that was actively asking for something."
            },
            {
                text: "Stop. Relax. Roll your shoulders. Unclench your jaw. Take one real breath.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You gave your body the attention it was asking for. It thanks you by flooding your brain with dopamine."
            },
            {
                text: "Get up and go for a walk to shake it off instead of figuring out what it is.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 10}
                },
                log: "You moved the tension around the block instead of asking where it came from."
            }
        ],
        glitch: {
            text: "Blame it, loudly and specifically, on Mercury being in retrograde.",
            log: "This explains nothing. It helps somehow, anyway."
        }
    },
    {
        zone: "SELF", title: "The Thing You Said You'd Never Become",
        desc: "You hear the sentence leave your mouth and recognize it a half-second too late. It's not yours. It's theirs.",
        choices: [
            {
                text: "Spiral into a full self-indictment for the next hour.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 20},
                    rep: {op: "add", value: 15}
                },
                log: "You sentenced yourself over one inherited sentence."
            },
            {
                text: "Laugh it off in the moment and never think about it again... You swear!",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You buried it under a laugh instead of a look."
            },
            {
                text: "Notice it. Decide, calmly, that noticing is the first step, not a failure.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You caught the echo without deciding it was proof of anything permanent."
            }
        ],
        glitch: {
            text: "Lean all the way into it.",
            log: "You have never committed to a bit this hard. Everyone is a little worried about you."
        }
    },
    {
        zone: "SELF", title: "The List of Things You're Supposed To Want",
        desc: "You're scrolling through someone else's milestones; house, promotion, wedding, etc.  You keep checking your own life against it.",
        choices: [
            {
                text: "Keep scrolling, keep comparing, feel worse with each one.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You audited your life against an idealized list you didn't write. Of course you fell short."
            },
            {
                text: "Convince yourself you want all of it too, just to feel aligned.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You borrowed someone else's wants because yours felt too quiet to trust."
            },
            {
                text: "Close the app. Ask yourself, honestly, what you actually want.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You checked the list against your own name instead of theirs."
            },
            {
                text: "Fire off a defensive comment about how milestones aren't a real measure of anything.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 15},
                    child: {op: "add", value: 3},
                    rep: {op: "subtract", value: 8}
                },
                log: "You argued with a stranger's wedding photos and, somehow, still lost."
            }
        ],
        glitch: {
            text: "Write your own unhinged, deeply specific counter-list right now.",
            log: "Item four is just 'made a really good sandwich.' You stand by it."
        }
    },
    {
        zone: "SELF", title: "The Anniversary Your Body Remembers Before You Do",
        desc: "You've been off all day and couldn't say why. Then you check the date.",
        choices: [
            {
                text: "Push through the day as if you hadn't noticed anything at all.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "Your body kept the appointment even after your mind tried to skip it."
            },
            {
                text: "Apologize to everyone around you for being 'off' today.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You apologized for grief that arrived on schedule, uninvited."
            },
            {
                text: "Let today be a harder day. You don't owe anyone your usual output.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You gave a hard day permission to be hard."
            },
            {
                text: "Cancel the rest of the day's plans and just leave without explanation.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 8},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 10}
                },
                log: "You gave the day less of you instead of naming what the day actually was."
            }
        ],
        glitch: {
            text: "Give the day an oddly specific, ceremonial little ritual.",
            log: "You have invented a holiday nobody else knows about. It helps."
        }
    },
    {
        zone: "SELF", title: "The You From Ten Years Ago",
        desc: "You try to picture yourself a decade back and feel a strange mix of tenderness and secondhand embarrassment.",
        choices: [
            {
                text: "Cringe hard and mentally list everything that version got wrong.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You put a decade-old version of yourself on trial."
            },
            {
                text: "Change the subject in your own head before it goes anywhere real.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You closed the door on a version of you who was just trying, too."
            },
            {
                text: "Send a little compassion backward. They didn't know what you know now.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You forgave a person for not having information they didn't learn yet."
            }
        ],
        glitch: {
            text: "Write them a postcard. Mail it to yourself, unironically.",
            log: "It arrives in three days. Past-you would be delighted it worked."
        }
    },
    {
        zone: "SELF", title: "The Apology You Never Got",
        desc: "You've rehearsed the conversation where they finally say it. It hasn't happened. It might not.",
        choices: [
            {
                text: "Keep rehearsing the conversation, on a loop, indefinitely.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You kept a courtroom open for a trial nobody else is attending."
            },
            {
                text: "Draft the message you'd send them. Don't send it. Yet.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You gave the anger somewhere to go besides in circles."
            },
            {
                text: "Consider that closure might have to come from you instead.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You stopped waiting for a door someone else may never open."
            }
        ],
        glitch: {
            text: "Draft their apology yourself, in full, and read it aloud dramatically.",
            log: "It's a great apology. Extremely well-written and deeply, deeply fake."
        }
    },
    {
        zone: "SELF", title: "The Question A Stranger Asked That Undid You",
        desc: "'Are you doing okay, actually?' — from someone who barely knows you, at exactly the wrong-yet-right moment.",
        choices: [
            {
                text: "Say 'I'm fine!' faster than the question finished.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You answered before you let yourself hear the question."
            },
            {
                text: "Deflect and change the subject immediately.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You closed a door a stranger had, kindly, tried to open."
            },
            {
                text: "Pause. Tell a small piece of the truth.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "A stranger's question got an honest, small answer. Mutual respect intensifies."
            },
            {
                text: "Snap back: 'Why are you asking me that?' before you've thought about it.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 12},
                    child: {op: "add", value: 3},
                    rep: {op: "subtract", value: 5}
                },
                log: "You treated a kind question like an ambush."
            }
        ],
        glitch: {
            text: "Chase them down the street to actually answer honestly.",
            log: "They were already gone. The honesty remains, unclaimed, in the middle of the sidewalk."
        }
    },
    {
        zone: "SELF", title: "The Time You Almost Told Someone",
        desc: "The words were right there. You had the opening. You changed the subject instead, and you're still thinking about it hours later.",
        choices: [
            {
                text: "Replay the moment, cataloging exactly why you didn't say it.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You held a post-mortem for a conversation that never happened."
            },
            {
                text: "Decide it's better this way for everyone. Probably.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You reframed silence as consideration for other people."
            },
            {
                text: "Note that the opening will come again. It's not your only chance.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 10}
                },
                log: "You released the pressure of a single missed moment."
            },
            {
                text: "Leave the conversation early, before anything else can slip out.",
                tag: "flight",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 5},
                    child: {op: "subtract", value: 5}
                },
                log: "You removed yourself from your own opening before it could close on its own."
            }
        ],
        glitch: {
            text: "Call them right now and just say it, all of it, fast.",
            log: "It's out. The world, remarkably, keeps turning."
        }
    },
    {
        zone: "SELF", title: "The Pride You Can't Say Out Loud",
        desc: "You did something genuinely well today. Saying so, even to yourself, feels like it's asking for trouble.",
        choices: [
            {
                text: "Immediately find the flaw to balance out the pride.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You disqualified the good thing before it could get comfortable."
            },
            {
                text: "Feel the pride, quietly, and never mention it to anyone.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 10},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 5}
                },
                log: "You let it exist, but only in a locked room."
            },
            {
                text: "Say it out loud, once, to yourself. 'I did that well.'",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 20}
                },
                log: "You let something good about yourself survive contact with your own scrutiny, again."
            }
        ],
        glitch: {
            text: "Announce it to a stranger on the street, at volume.",
            log: "A stranger claps for you. You're both very proud of yourself.."
        }
    },
    {
        zone: "SELF", title: "The Nightmare You Can't Fully Remember",
        desc: "You woke up at 4 AM with your heart going and no clear memory of why. The feeling stayed. The plot didn't.",
        choices: [
            {
                text: "Lie there, awake, trying to force the memory back.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You chased a plot your brain had already decided to withhold."
            },
            {
                text: "Get up and immediately start the day like nothing happened.",
                tag: "flight",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You outran a feeling by scheduling over it."
            },
            {
                text: "Let the feeling exist without the plot. You don't need the whole story.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You let your body's alarm matter even without a confirmed cause."
            }
        ],
        glitch: {
            text: "Draw it from memory, badly, in crayon, right now.",
            log: "The drawing is somehow both deeply upsetting and extremely funny."
        }
    },
    {
        zone: "SELF", title: "The Reflex To Apologize For Existing",
        desc: "Someone bumps into you. You apologize. To them. For being bumped into.",
        choices: [
            {
                text: "Apologize again, just to be safe.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You doubled down on an apology that was never yours to give."
            },
            {
                text: "Notice it happened and feel weird about it for the rest of the day.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You let a two-second reflex become an all-day mood."
            },
            {
                text: "Notice the reflex. Don't perform an apology for the apology.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You caught the reflex mid-air and let it just pass through."
            },
            {
                text: "Catch yourself mid-apology and snap, out loud, 'why am I sorry?'",
                tag: "fight",
                effects: {
                    rep: {op: "subtract", value: 10},
                    mask: {op: "subtract", value: 10},
                    child: {op: "add", value: 5}
                },
                log: "You interrupted a lifelong reflex with your own irritation. Progress is loud sometimes."
            }
        ],
        glitch: {
            text: "Apologize to an inanimate object instead. With real emotion.",
            log: "The doorknob accepts your apology graciously. It has no other choice."
        }
    },
    {
        zone: "SELF", title: "The Urge To Check If You're Still Likable",
        desc: "You've refreshed the same three apps four times in the last ten minutes, looking for a number that proves something.",
        choices: [
            {
                text: "Keep refreshing. The number hasn't proven anything yet, but the next one might.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "You outsourced your worth to a number that resets every day."
            },
            {
                text: "Post something calibrated to perform well, just to be sure.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 10},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 5}
                },
                log: "You engineered proof instead of just existing for a minute."
            },
            {
                text: "Put the phone down. The question doesn't need an answer right now.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "You let the urge exist without feeding it."
            },
            {
                text: "Throw the phone across the couch and go do something that doesn't have a number attached.",
                tag: "flight",
                effects: {
                    mask: {op: "subtract", value: 5},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 10}
                },
                log: "You removed the scoreboard from the room, at least for now."
            }
        ],
        glitch: {
            text: "Post the most unhinged, honest thing you can think of instead.",
            log: "Engagement still goes way down... but you, somehow, feel better."
        }
    },
    {
        zone: "SELF", title: "The Streak You Broke",
        desc: "Forty-one days. You missed yesterday. The app has already reset the number to zero, and it feels disproportionately catastrophic.",
        choices: [
            {
                text: "Berate yourself for the missed day, at length.",
                tag: "fight",
                effects: {
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15},
                    rep: {op: "add", value: 10}
                },
                log: "You treated one missed day like it undid the other forty-one."
            },
            {
                text: "Decide the whole habit is ruined now and quietly stop.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 15}
                },
                log: "One broken streak took the whole habit down with it."
            },
            {
                text: "Start again today. The forty-one days still happened.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 15}
                },
                log: "A number resetting didn't erase what you'd actually built."
            }
        ],
        glitch: {
            text: "Start an entirely new, deliberately pointless streak instead.",
            log: "Day one of 'touching a doorframe for luck.' It's going great."
        }
    },
    {
        zone: "SELF", title: "The Quiet Car Where You Finally Cry",
        desc: "Nowhere dramatic. Just the car, in a parking lot, engine off, and the thing you've been holding all week finally lets go.",
        choices: [
            {
                text: "Stop it fast. Fix your face. Go inside like nothing happened.",
                tag: "freeze",
                effects: {
                    rep: {op: "add", value: 15},
                    mask: {op: "add", value: 0},
                    child: {op: "subtract", value: 10}
                },
                log: "You closed the door on something that had only just cracked open."
            },
            {
                text: "Apologize to no one in an empty car for crying at all.",
                tag: "fawn",
                effects: {
                    mask: {op: "add", value: 5},
                    child: {op: "subtract", value: 10},
                    rep: {op: "add", value: 5}
                },
                log: "You apologized to an empty passenger seat. It was unmoved."
            },
            {
                text: "Let it happen. Stay in the car until it passes on its own.",
                tag: "secure",
                effects: {
                    rep: {op: "subtract", value: 20},
                    mask: {op: "add", value: 0},
                    child: {op: "add", value: 20}
                },
                log: "You let the parking lot hold something you'd been carrying all week."
            },
            {
                text: "Hit the steering wheel, furious at yourself for needing this at all.",
                tag: "fight",
                effects: {
                    mask: {op: "subtract", value: 10},
                    child: {op: "subtract", value: 5},
                    rep: {op: "subtract", value: 5}
                },
                log: "You turned the release into another thing to be angry about."
            }
        ],
        glitch: {
            text: "Turn on the radio and sing along badly through it.",
            log: "You are now simultaneously crying and singing off-key. A rare skill."
        }
    },
    {
        zone: "SELF", title: "The Banking App",
        desc: "You open the app, see the number, close it immediately, and open it again ninety seconds later like it might have changed.",
        choices: [
            {
                text: "Check it six more times without doing anything differently.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "The number stayed the same. Checking isn't the same as changing it."
            },
            {
                text: "Tell yourself other people are worse with money, so it's fine.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 8}},
                log: "Comparing down didn't actually move the number either."
            },
            {
                text: "Actually write the number down somewhere and make one small plan around it.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You stopped checking and started, in a small way, handling it."
            },
            {
                text: "Get sharply angry at whatever got you here in the first place.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "subtract", value: 5}},
                log: "The number didn't move. You definitely did, internally, at volume."
            }
        ],
        glitch: {
            text: "Open three different budgeting apps in the same five minutes.",
            log: "You now have three different opinions on how broke you are."
        }
    },
    {
        zone: "SELF", title: "The Age You Thought You'd Have This Figured Out By",
        desc: "You do the math on how old you are versus how old you thought 'having it together' would happen by. The math is not flattering.",
        choices: [
            {
                text: "Get irrationally angry at your younger self for the timeline they set.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "Younger you didn't know anything. It's a strange thing to resent them for."
            },
            {
                text: "Spiral quietly for the rest of the evening without telling anyone.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 12}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "The spiral had an audience of exactly one and no exit."
            },
            {
                text: "Notice the timeline was arbitrary, made up by a much younger, much less informed you.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You fired a deadline nobody real ever actually set."
            }
        ],
        glitch: {
            text: "Google 'average age people figure their life out' at midnight.",
            log: "The internet does not agree with itself. You are somehow less comforted."
        }
    },
    {
        zone: "SELF", title: "The Decision You've Been Avoiding for Months",
        desc: "It's still sitting there, unmade, quietly getting heavier every week you don't touch it.",
        choices: [
            {
                text: "Add it to tomorrow's list again, same as every day this month.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "Tomorrow's list is getting long. This is still at the top of it."
            },
            {
                text: "Ask five different people what they'd do, hoping one of them decides for you.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 10}},
                log: "Five people, five opinions, and the decision is still, somehow, yours."
            },
            {
                text: "Give yourself an actual deadline and one criterion to decide by.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You made the decision smaller instead of making it disappear."
            },
            {
                text: "Plan something else entirely, something big and distracting, instead.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You built an impressive amount of momentum in every direction except the correct one."
            }
        ],
        glitch: {
            text: "Flip a coin and pretend the outcome doesn't matter to you.",
            log: "It landed. Your reaction to it told you exactly what you actually wanted."
        }
    },
    {
        zone: "SELF", title: "The Specific Person You Compare Yourself To",
        desc: "There's one person (you know exactly who) whose life you measure your own against, always unfavorably, without ever really deciding to.",
        choices: [
            {
                text: "Check what they're up to again, even though it never actually helps.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "Same result as every other time. You checked anyway."
            },
            {
                text: "Congratulate their latest thing extra warmly to prove to yourself you're not bitter.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "The warmth was mostly for your own benefit, not theirs."
            },
            {
                text: "Notice you're comparing your whole self to their highlight reel, and put the scale down.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You stopped measuring two different things with the same ruler."
            },
            {
                text: "Mute their profile entirely instead of figuring out why it stings.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "subtract", value: 5}},
                log: "You removed the mirror instead of asking what it kept showing you."
            }
        ],
        glitch: {
            text: "Look up someone from high school you haven't thought about in a decade instead.",
            log: "New person to compare yourself to. Same old habit, different target."
        }
    },
    {
        zone: "SELF", title: "The Question You Can't Answer",
        desc: "Someone asks, casually, 'so what do you actually want?' and your mind goes completely, embarrassingly blank.",
        choices: [
            {
                text: "Give the answer you think they want to hear instead of sitting in the blank.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 10}},
                log: "You answered the question they asked instead of the one that stumped you."
            },
            {
                text: "Laugh it off and change the subject before the silence gets uncomfortable.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "The subject changed. The blank is still there, waiting for next time."
            },
            {
                text: "Just say, honestly, 'I don't actually know yet.'",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "Not knowing, said out loud, turned out to be survivable."
            }
        ],
        glitch: {
            text: "Answer with the most impulsive thing that comes to mind, unfiltered.",
            log: "You surprised yourself with the answer. You're still not sure it's 100% true."
        }
    },
    {
        zone: "SELF", title: "The Photo Where You Look Happy",
        desc: "You find an old photo. You're laughing, genuinely, in a way you don't fully remember how to do anymore. You can't place the feeling.",
        choices: [
            {
                text: "Stare at it a while, then close the folder without really processing anything.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "You looked at the feeling instead of into it. Close, but not quite."
            },
            {
                text: "Post it with a caption implying everything's still that happy now.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "The caption performed something the photo didn't actually contain anymore."
            },
            {
                text: "Let yourself actually miss that feeling instead of performing that you still have it.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 12}},
                log: "Missing something honestly takes up less room than pretending you don't."
            },
            {
                text: "Get annoyed at the photo for making the present look worse by comparison.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "You resented a memory for being better than the moment you're in."
            }
        ],
        glitch: {
            text: "Try to recreate the exact photo, right now, alone in your room.",
            log: "It does not look the same. It was never really about the photo."
        }
    },
    {
        zone: "SELF", title: "The Urge to Label 'Isolation' as 'Introversion'",
        desc: "You've cancelled the last four plans in a row and told yourself, each time, that you're just 'an introvert who needs space.'",
        choices: [
            {
                text: "Cancel the fifth one too, and repeat the same explanation to yourself.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 8}, child: {op: "add", value: 0}},
                log: "The explanation is getting worn out. You use it anyway because you know the only person holding you accountable is you."
            },
            {
                text: "Don't examine it. Just stay in and let the pattern keep going.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "Staying in didn't recharge anything this time. It just kept happening."
            },
            {
                text: "Ask yourself honestly whether this is self-care or avoidance, and answer honestly.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You looked at the label before deciding whether to trust it. The answer was, as usual, the quantumly optimal 'Yes.'"
            }
        ],
        glitch: {
            text: "Accept a plan you don't want to go to, purely to prove a point to yourself.",
            log: "You went. It was fine. It proved absolutely nothing either way."
        }
    },
    {
        zone: "SELF", title: "The Thing You're Waiting to Feel Ready For",
        desc: "There's something you've wanted to start for a long time. You're still waiting to feel ready, and the feeling hasn't come.",
        choices: [
            {
                text: "Keep waiting for the readiness to arrive on its own schedule.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "It didn't arrive. It rarely does on its own."
            },
            {
                text: "Tell people you're 'about to start soon' again, to keep the pressure off.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "You bought yourself another round of not starting, at the cost of a small lie."
            },
            {
                text: "Do one small piece of it today, ready or not.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "Turns out readiness was never actually the requirement."
            },
            {
                text: "Change the subject whenever it comes up and quietly shelve the idea a while longer.",
                tag: "flight",
                effects: {rep: {op: "add", value: 15}, mask: {op: "subtract", value: 8}, child: {op: "subtract", value: 8}},
                log: "You didn't decide against it. You just stopped bringing it up."
            }
        ],
        glitch: {
            text: "Buy all the equipment for it and start absolutely nothing else.",
            log: "You now own everything you need and have used none of it. A familiar shape."
        }
    }
];
