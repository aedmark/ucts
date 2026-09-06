// ============================================================
// EVENTS — BODY zone.
// Loaded before content-events.js, which assembles CONTENT_EVENTS
// from this file and the other four zones.
// ============================================================
const CONTENT_EVENTS_BODY = [
    {
        zone: "BODY", title: "The Wake-Up Call",
        desc: "You're awake at an ungodly hour for no reason, running a full inventory of every mistake you've made since birth.",
        choices: [
            {
                text: "Get up and scroll your phone until the sky turns on again",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You traded sleep for a different, worse kind of tired."
            },
            {
                text: "Lie perfectly still and pretend this isn't happening.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You waited it out. It did not go anywhere."
            },
            {
                text: "Get up, write down the one thing actually bothering you, and go back to bed.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You gave the thought somewhere else to live besides your brain."
            }
        ],
        glitch: {
            text: "Reorganize your entire closet.",
            log: "Your shirts are now sorted by emotional association. Impressive. And unhelpful."
        }
    },
    {
        zone: "BODY", title: "The Locked Jaw",
        desc: "You catch yourself clenching your jaw so hard your teeth ache. You have no memory of starting.",
        choices: [
            {
                text: "Force a smile until the muscles relax on their own.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 5}},
                log: "You performed relaxation until it was almost real. You even broke a sweat!"
            },
            {
                text: "Snap at the next person who asks you a simple question.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 15}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "Your jaw unclenched. Someone else's day is now ruined. Good job."
            },
            {
                text: "Actually stretch it out and breathe for ten seconds.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You noticed the tension and let it go. On purpose (for once)."
            }
        ],
        glitch: {
            text: "Buy a $40 mouthguard you will wear exactly once.",
            log: "It's in a drawer now. You develop TNJ."
        }
    },
    {
        zone: "BODY", title: "The Phantom Vibration",
        desc: "You feel your phone buzz in your pocket. It's not there. You're not wearing anything with pockets.",
        choices: [
            {
                text: "Check anyway and feign shock when you can't find your phone.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 0}, mask: {op: "subtract", value: 5}, child: {op: "subtract", value: 5}},
                log: "You reflexively obeyed the call of a muscle spasm."
            },
            {
                text: "Laugh it off and immediately forget it happened.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 5}, child: {op: "add", value: 0}},
                log: "You buried the weird little moment under a joke."
            },
            {
                text: "Notice it, name it, and let it pass.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You clocked your nervous system doing a bit and didn't argue with it."
            }
        ],
        glitch: {
            text: "Genuinely consider whether you're being paged by the universe.",
            log: "You are not. The universe isn't on speaking terms with you, currently."
        }
    },
    {
        zone: "BODY", title: "The Skipped Lunch",
        desc: "It's been hours since your lunch break. You still have not eaten. You only just now noticed the shaking.",
        choices: [
            {
                text: "Push through, you'll eat when this is 'actually done.'",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You deferred a basic need to a deadline that keeps moving."
            },
            {
                text: "Eat standing up over the sink in under ninety seconds.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "Technically food. Technically eaten. Moving on. (Your colon hates you)."
            },
            {
                text: "Sit down and finally enjoy your meal.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 15}},
                log: "Wild concept: feeding yourself food when your body needs it because you matter."
            }
        ],
        glitch: {
            text: "Eat a mystery item from the back of the freezer instead.",
            log: "Unidentifiable but savory, slightly freezer-burned, and somehow the best part of your day."
        }
    },
    {
        zone: "BODY", title: "The Tension Headache",
        desc: "A headache has been building behind your left eye since you woke up.",
        choices: [
            {
                text: "Apologize to it and keep working through the pain.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 10}},
                log: "You negotiated with a headache. The headache won."
            },
            {
                text: "Snap the laptop shut and lie in a dark room out of spite.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 15}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 5}},
                log: "You declared war on productivity and productivity lost."
            },
            {
                text: "Drink water, step outside, take a break.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 15}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You treated the cause instead of white-knuckling the symptom."
            }
        ],
        glitch: {
            text: "Diagnose yourself with seven unrelated conditions via search engine.",
            log: "According to the internet, it's either dehydration or something terminal. No in-between."
        }
    },
    {
        zone: "BODY", title: "The Shoulders",
        desc: "Someone points out that you look really tense. You notice your shoulders are up to your ears.",
        choices: [
            {
                text: "Laugh it off, drop them for exactly four seconds.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 5}},
                log: "You roleplay as a relaxed person. Your shoulders forgot their lines immediately."
            },
            {
                text: "Get defensive about your own posture.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "Now everyone thinks you're hiding something... Or you think that they think you're hiding something. "
            },
            {
                text: "Actually roll them out and admit you're tensed up for no reason.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 10}},
                log: "You told the truth about your own body. Small, but real. Everyone else rolls their shoulders, too."
            }
        ],
        glitch: {
            text: "Book a massage you will cancel twice and never reschedule.",
            log: "It's the thought that counts. Your shoulders disagree."
        }
    },
    {
        zone: "BODY", title: "The Stomach Drop",
        desc: "An email notification appears. Your stomach drops before your eyes even finish reading the subject line.",
        choices: [
            {
                text: "Open it immediately, brace for impact.",
                tag: "flight",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You ran toward the thing that scared you. Godspeed."
            },
            {
                text: "Let it sit unread while your stomach keeps dropping anyway.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You delayed the information, not the feeling. Then you dry heave into the wastebasket."
            },
            {
                text: "Take one breath, then open it at your own pace.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You let your body catch up before you made a decision."
            }
        ],
        glitch: {
            text: "Have a coworker read it out loud to you instead.",
            log: "Outsourcing Dread: an underrated coping strategy."
        }
    },
    {
        zone: "BODY", title: "The Tight Chest",
        desc: "Your chest has felt tight since the second cup of coffee. It's been four hours.",
        choices: [
            {
                text: "Have a third cup, see what happens.",
                tag: "fight",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You met a warning sign with more of the thing that caused it."
            },
            {
                text: "Ignore it, it'll pass, it always passes.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You filed it under 'later' along with everything else."
            },
            {
                text: "Switch to water and sit somewhere quiet for a minute.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 15}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You gave your nervous system one thing to not fight."
            }
        ],
        glitch: {
            text: "Google your symptoms while your chest gets tighter reading the results.",
            log: "Peak efficiency: causing the exact symptom you were worried about."
        }
    },
    {
        zone: "BODY", title: "The Bitten Nails",
        desc: "You look down mid-meeting and realize you've bitten a nail down past comfortable. You don't remember starting.",
        choices: [
            {
                text: "Hide your hands and keep going like nothing happened.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You concealed the evidence. The habit remains unfiled."
            },
            {
                text: "Sit on your hands for the rest of the meeting.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You restrained the symptom instead of asking about the cause."
            },
            {
                text: "Notice it, put your hands flat on the table, and let it be a fact.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You caught yourself mid-habit without turning it into a crisis."
            }
        ],
        glitch: {
            text: "Buy the extremely bitter anti-biting nail polish.",
            log: "You now know exactly how bitter it is. Repeatedly."
        }
    },
    {
        zone: "BODY", title: "The Restless Leg",
        desc: "Your leg has been bouncing under the desk for forty-five minutes. You only notice when someone asks if you're okay.",
        choices: [
            {
                text: "Say you're fine, laugh, keep bouncing.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You answered the question the body was already answering."
            },
            {
                text: "Get up and pace the hallway instead.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You gave the energy somewhere bigger to go."
            },
            {
                text: "Name it: 'I think I'm anxious about something.'",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 10}},
                log: "You let the leg tell on you, and believed it."
            }
        ],
        glitch: {
            text: "Turn the bouncing into a full drum solo on the desk.",
            log: "Everyone in the room now knows exactly how you feel. Loudly."
        }
    },
    {
        zone: "BODY", title: "The Cold That Won't Quit",
        desc: "You've had the same low-grade cold for three weeks. It appeared right after things got hard and hasn't left since.",
        choices: [
            {
                text: "Power through, colds are for people with time off.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You outsourced your recovery to a future that keeps not arriving."
            },
            {
                text: "Blame the office AC loudly to anyone who'll listen.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "You found a villain. It wasn't the AC."
            },
            {
                text: "Actually take the day, and actually rest on it.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 15}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 15}},
                log: "You let your immune system have the meeting instead of you."
            }
        ],
        glitch: {
            text: "Self-medicate with four different remedies simultaneously.",
            log: "You are now buzzing gently and no less congested. A wash."
        }
    },
    {
        zone: "BODY", title: "The Alarm You Don't Remember Silencing",
        desc: "You wake up two hours late. Your alarm went off. You have no memory of turning it off.",
        choices: [
            {
                text: "Rush out the door pretending this is fine and normal.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You performed 'on schedule' at a very unconvincing level."
            },
            {
                text: "Lie there a while longer, the day's already ruined anyway.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "subtract", value: 10}},
                log: "You let one bad start decide the whole day's verdict."
            },
            {
                text: "Get up slowly, text that you're late, actually wake up first.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 10}},
                log: "You let your body finish waking up before you asked it to perform."
            }
        ],
        glitch: {
            text: "Convince yourself you're still dreaming for a solid ninety seconds.",
            log: "You were not dreaming. You were extremely, demonstrably late."
        }
    },
    {
        zone: "BODY", title: "The Cracking Joints",
        desc: "You've cracked your knuckles, neck, and back four times each in the last hour. It's becoming a whole thing.",
        choices: [
            {
                text: "Keep doing it, quieter, so no one notices.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You hid the fidget instead of asking what it was fidgeting about."
            },
            {
                text: "Crack something loudly on purpose, right at someone.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "You turned a nervous habit into a small act of war."
            },
            {
                text: "Get up and actually move for a minute instead.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You gave the restlessness an actual job to do."
            }
        ],
        glitch: {
            text: "Attempt to crack a joint that does not, physiologically, crack.",
            log: "It did not crack. You will try again in eleven minutes."
        }
    },
    {
        zone: "BODY", title: "The Overwhelm in the Cereal Aisle",
        desc: "You are standing in front of forty kinds of the same cereal and you cannot make your body pick one.",
        choices: [
            {
                text: "Grab the one closest to your hand and leave fast.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You escaped the aisle. The cereal was incidental."
            },
            {
                text: "Stand there until someone else's cart forces you to move.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "Your body vetoed the decision and nobody overruled it."
            },
            {
                text: "Breathe, pick the familiar one on purpose, keep walking.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You made a small decision on purpose. It counts."
            }
        ],
        glitch: {
            text: "Buy one of every single kind out of sheer decision fatigue.",
            log: "You now own more cereal than a household requires. Problem technically solved."
        }
    },
    {
        zone: "BODY", title: "The Bruise You Don't Remember",
        desc: "You notice a bruise on your arm. You have absolutely no memory of how it got there.",
        choices: [
            {
                text: "Cover it and move on, it's fine, you're fine.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 0}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 10}},
                log: "You concealed the evidence and skipped the question it was asking."
            },
            {
                text: "Poke it a few times, oddly detached from the pain.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 15}},
                log: "You observed your own body like it belonged to someone else."
            },
            {
                text: "Actually stop and wonder what's been going on with you lately.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "add", value: 15}},
                log: "You treated a small mystery as information instead of noise."
            }
        ],
        glitch: {
            text: "Construct an elaborate, confident lie about how you got it.",
            log: "Nobody asked. You told them anyway. The story was very good."
        }
    },
    {
        zone: "BODY", title: "The Racing Heart in the Elevator",
        desc: "The elevator doors close and your heart rate spikes for no reason you can name.",
        choices: [
            {
                text: "Smile at the stranger next to you like everything's normal.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 5}},
                log: "You performed calm at a stranger who will never know otherwise."
            },
            {
                text: "Get out at the wrong floor just to escape the box.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You solved the feeling by relocating the problem, briefly."
            },
            {
                text: "Count your breaths until the doors open again.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 15}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You rode it out instead of running from it."
            }
        ],
        glitch: {
            text: "Strike up a loud, urgent conversation about the weather.",
            log: "Nobody wanted this conversation. It happened anyway. Heart rate: unchanged."
        }
    },
    {
        zone: "BODY", title: "The Itch That Isn't There",
        desc: "Something itches. You've checked twice. There is nothing there. It still itches.",
        choices: [
            {
                text: "Scratch it anyway, repeatedly, in front of everyone.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "You enabled a feeling that had no physical location."
            },
            {
                text: "Ignore it and hope it forgets about you first.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You waited out a sensation that was never going to negotiate."
            },
            {
                text: "Notice it's stress, not skin, and address the actual thing.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You correctly diagnosed a body signal for once."
            }
        ],
        glitch: {
            text: "Buy an entire tube of anti-itch cream for a psychosomatic itch.",
            log: "The itch was never dermatological. The cream is very soothing regardless."
        }
    },
    {
        zone: "BODY", title: "The Forgotten Water Bottle",
        desc: "You realize the water bottle on your desk has been full since yesterday morning. You feel vaguely awful and can't say why.",
        choices: [
            {
                text: "Drink coffee instead, that's basically water.",
                tag: "flight",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You addressed thirst with a substance that specializes in the opposite."
            },
            {
                text: "Feel guilty about it and do nothing differently.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You apologized to your own body and changed nothing."
            },
            {
                text: "Drink the whole thing right now, slowly.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You met an actual need instead of a performed one."
            }
        ],
        glitch: {
            text: "Set six hourly water alarms you will immediately start ignoring.",
            log: "Your phone now nags you about hydration. You remain thirsty."
        }
    },
    {
        zone: "BODY", title: "The Crash",
        desc: "Your energy leaves your body like water out of a bathtub, all at once.",
        choices: [
            {
                text: "Chug an energy drink and pretend the crash isn't happening.",
                tag: "flight",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You borrowed energy from tonight-you, who was not consulted and does not appreciate it."
            },
            {
                text: "Push through on pure spite and bad posture.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You ran the tank to empty and called it discipline."
            },
            {
                text: "Take five real minutes doing nothing.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You let the crash happen instead of arguing with it."
            }
        ],
        glitch: {
            text: "Do twenty jumping jacks in a supply closet to 'reset your energy.'",
            log: "You are now tired in a completely new and different way. Innovative."
        }
    },
    {
        zone: "BODY", title: "The Clenched Fists",
        desc: "You notice your fists have been closed, nails in your palms, for who knows how long.",
        choices: [
            {
                text: "Unclench them fast and act like it never happened.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You hid the evidence and skipped the follow-up question."
            },
            {
                text: "Squeeze harder, actually, see what that does.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "You gave the anger a place to go. It chose your own hands. You have have broken some skin."
            },
            {
                text: "Open them slowly and shake them out on purpose.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You released something before it decided to dig in further."
            }
        ],
        glitch: {
            text: "Punch a couch cushion with genuine, focused intent.",
            log: "The cushion did not deserve this. The cushion will recover."
        }
    },
    {
        zone: "BODY", title: "The Skipped Workout",
        desc: "You had a plan to move your body today. Your body voted no.",
        choices: [
            {
                text: "Guilt yourself about it for the rest of the evening.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You turned a rest day into a tribunal."
            },
            {
                text: "Declare fitness dead to you and order takeout in protest.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 5}},
                log: "You staged a small rebellion against your own to-do list."
            },
            {
                text: "Skip it without ceremony, no apology required.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You let 'not today' be a complete sentence."
            }
        ],
        glitch: {
            text: "Do one dramatic stretch and count it as a full workout.",
            log: "Technically movement occurred. The bar has been set, and it is on the floor."
        }
    },
    {
        zone: "BODY", title: "The Sudden Nosebleed",
        desc: "Your nose starts bleeding out of nowhere. Around people.",
        choices: [
            {
                text: "Play it off smoothly like this happens all the time.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 0}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 5}},
                log: "You narrated your own bleeding nose as a completely normal event."
            },
            {
                text: "Panic slightly and leave the room without explanation.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "You exited stage left, mid-sentence, mid-nosebleed."
            },
            {
                text: "Tilt your head forward, breathe, and just deal with it.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You handled a small crisis like an adult, briefly, on purpose."
            }
        ],
        glitch: {
            text: "Announce to the whole room that you are, in fact, fine.",
            log: "Nobody was worried until you said something. Now everyone's worried."
        }
    },
    {
        zone: "BODY", title: "The Full-Body Flinch",
        desc: "Someone sets a mug down a little too hard and your entire body flinches like it's under attack.",
        choices: [
            {
                text: "Laugh it off immediately, extra loud, extra fast.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 10}},
                log: "You covered a real reaction with a performed one, instantly."
            },
            {
                text: "Make an equally loud noise which is actually much louder and actually disruptive.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "You made your nervous system's problem everyone else's problem."
            },
            {
                text: "Let it be visible, no cover story required.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 15}},
                log: "You let your body's honest reaction stand without editing it."
            }
        ],
        glitch: {
            text: "Learn to crochet and make everyone cozies for their mugs.",
            log: "You are the Andy Warhol of passive aggression."
        }
    },
    {
        zone: "BODY", title: "The Unbrushed Teeth",
        desc: "It's almost bedtime and you realize you haven't done a single part of your evening routine. Or your morning one.",
        choices: [
            {
                text: "Do the bare minimum and call it a wash.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You lowered the bar until you could technically step over it."
            },
            {
                text: "Spiral about what this says about you as a person.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 15}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 10}},
                log: "You turned one skipped routine into a referendum on your whole character."
            },
            {
                text: "Just do it now, no narrative required.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You took care of yourself late instead of not at all."
            }
        ],
        glitch: {
            text: "Deep-clean the entire bathroom instead of just brushing your teeth.",
            log: "The sink has never been shinier. Your teeth remain exactly as they were."
        }
    }
];
