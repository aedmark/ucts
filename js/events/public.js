const CONTENT_EVENTS_PUBLIC = [
    {
        zone: "PUBLIC", title: "The Checkout Line",
        desc: "You're barely holding it together behind a very calm face. The cashier asks how your day is going. ",
        choices: [
            {
                text: "Say 'great, thanks!' with way more enthusiasm than you actually have.",
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
                text: "Be honest and let it be a real, if brief, answer.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You told a stranger a true thing instead of a convenient one. They gave you a sticker. "
            },
            {
                text: "Snap back, sharper than you meant: 'Do you actually want to know?'",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 3}},
                log: "You handed a stranger's small talk back with interest it didn't ask for."
            }
        ],
        glitch: {
            text: "Ask the cashier how THEIR day is going, in aggressive detail.",
            log: "They were not prepared for this. Neither were you."
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
            log: "There is no such position. You asked anyway. You were put on hold again.\n\n\n Close enough."
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
                text: "Say thank you and let it land.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "A stranger's small kindness got all the way in, for once."
            }
        ],
        glitch: {
            text: "Ask them to write the compliment down so you can frame it.",
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
            },
            {
                text: "Get off two stops early just to escape the eyes on the bus.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 8}, child: {op: "subtract", value: 5}},
                log: "You walked the rest of the way so nobody had to watch you do it."
            }
        ],
        glitch: {
            text: "Loudly announce to the whole bus that you're not crying it's just allergies.",
            log: "Nobody asked. Several people now have to carry a bit of something from you they didn't want."
        }
    },
    {
        zone: "PUBLIC", title: "The Interview With A Stranger Who Holds All The Cards",
        desc: "The interviewer asks you to describe your biggest weakness, and you have exactly one chance to answer this well.",
        choices: [
            {
                text: "Give the fake answer everyone knows is fake because it's clearly a humblebrag in jackass clothing.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "You performed vulnerability shaped like win. It wins you nothing but an eyeroll."
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
            },
            {
                text: "'Honestly, I think that question rewards fake humility.'",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 5}},
                log: "You argued with the format instead of playing along with it."
            }
        ],
        glitch: {
            text: "Answer with a completely unrelated childhood memory instead.",
            log: "The interviewer is now taking very different notes than they were a minute ago."
        }
    },
    {
        zone: "PUBLIC", title: "The Wrong Order",
        desc: "The order that arrives is not, in any respect, the order you placed. Correcting it means being 'A Problem' in front of everyone in line.",
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
        zone: "PUBLIC", title: "The Icebreaker",
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
                log: "Eight seconds of silence in front of strangers. You counted every one. So did they."
            },
            {
                text: "Say something small but actually true instead of the safe version.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You gave the room slightly more of the real thing than it was expecting."
            },
            {
                text: "Ask to come back to it later and hope the moment gets forgotten.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "subtract", value: 5}},
                log: "You bought time by passing the turn to someone else's ten seconds."
            }
        ],
        glitch: {
            text: "Answer with something completely unhinged just to see what happens.",
            log: "The circle is now unsettled. You have made an impression. Possibly a good one; it's too early to tell."
        }
    },
    {
        zone: "PUBLIC", title: "The Person Who Held The Door",
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
            log: "You now know a stranger's opinion on parking garages. Neither of you asked for this, but someone needed it all the same."
        }
    },
    {
        zone: "PUBLIC", title: "The DMV Line",
        desc: "You've been standing in the same six feet of floor for forty minutes. The number on your ticket has not moved. Neither has anyone's mood.",
        choices: [
            {
                text: "Apologize to the person behind you for existing in their line of sight.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "You said sorry for taking up floor space that was yours to take up."
            },
            {
                text: "Loudly ask if anyone else thinks this is insane.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 12}, child: {op: "add", value: 0}},
                log: "Several strangers agreed with you. Nobody moved any faster because of it."
            },
            {
                text: "Accept the wait for what it is and let your mind go somewhere else.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You gave the wait exactly the amount of your life it was going to take anyway."
            }
        ],
        glitch: {
            text: "Strike up a conversation with your number ticket like it's a person.",
            log: "B-247 did not respond. You feel closer to it anyway."
        }
    },
    {
        zone: "PUBLIC", title: "The Jury Duty Summons",
        desc: "You're in a room full of strangers being told that your normal lives are on hold for an unknown number of days.",
        choices: [
            {
                text: "Raise your hand immediately to volunteer for anything that gets you excused.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You ran from an obligation using the exact process built to enforce it."
            },
            {
                text: "Sit very still and hope your name simply never gets called.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You made yourself as unnoticeable as a person in a numbered chair can be."
            },
            {
                text: "Answer the questions honestly and let the process do what it does.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You told the truth to a room of strangers deciding your next two weeks. It felt strange, but fine."
            }
        ],
        glitch: {
            text: "Tell the bailiff you've actually always wanted to see how this works.",
            log: "This is, apparently, an unusual thing to say out loud. You are now Juror Number One."
        }
    },
    {
        zone: "PUBLIC", title: "The Rideshare Small Talk",
        desc: "The driver wants to talk. You have eighteen minutes left and a headache forming right behind your left eye.",
        choices: [
            {
                text: "Answer every question with enthusiasm you do not currently possess.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "You performed 'Friendly Passenger' for eighteen minutes straight through a headache."
            },
            {
                text: "Put in headphones without actually playing anything.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 3}},
                log: "You faked a soundtrack to buy yourself a real silence."
            },
            {
                text: "Say, kindly, that you're wiped and would rather ride quiet.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 10}},
                log: "You asked for what you needed from a stranger you'll know for eighteen more minutes."
            }
        ],
        glitch: {
            text: "Ask the driver a series of increasingly personal questions right back.",
            log: "You now know more about their custody arrangement than the last three passengers combined."
        }
    },
    {
        zone: "PUBLIC", title: "The Street Canvasser With A Clipboard",
        desc: "They've made eye contact from twenty feet away and are already walking toward you with a clipboard and a warm, practiced smile.",
        choices: [
            {
                text: "Sign up for something you don't care about just to end the conversation faster.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "You bought your own escape with a monthly donation you will forget about until it's not forgettable."
            },
            {
                text: "Pretend to be mid-phone-call and speed-walk past.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You held a phone to your ear and spoke to no one to avoid speaking to someone."
            },
            {
                text: "Make eye contact, say 'not today, thanks,' and keep walking.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You said no in four words and nothing bad happened."
            }
        ],
        glitch: {
            text: "Ask to see the clipboard and start signing up other pedestrians yourself.",
            log: "You have somehow become their best volunteer of the day. This was not the plan."
        }
    },
    {
        zone: "PUBLIC", title: "The Tip Jar",
        desc: "You're paying with a card. The screen turns around. It's asking for a tip, and it's asking in front of the person you'd be tipping.",
        choices: [
            {
                text: "Tip more than you can afford so they don't think badly of you.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "You paid for a stranger's good opinion of you with money you didn't have to spare."
            },
            {
                text: "Hit 'no tip' fast and avoid eye contact for the rest of the transaction.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You made a budget decision and then flinched from it like it was a crime."
            },
            {
                text: "Tip what you can actually afford and let that be enough.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You made a number decision and didn't turn it into a referendum on your character."
            },
            {
                text: "Exclaim to the cashier that these screens are a scam, then hit 'no tip' hard.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 3}},
                log: "You picked a fight with a piece of hardware in front of the one person it couldn't help."
            }
        ],
        glitch: {
            text: "Ask the screen, out loud, if it has any tips for you.",
            log: "The screen did not answer. The cashier, after a pause, actually did."
        }
    },
    {
        zone: "PUBLIC", title: "The Crying Baby On The Plane",
        desc: "Row fourteen has been going for twenty minutes. You can feel the whole cabin's patience thinning at the same rate as yours.",
        choices: [
            {
                text: "Shoot the parents a series of sympathetic smiles you don't actually feel.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 5}},
                log: "You performed patience at a stranger so they wouldn't feel what the whole cabin was actually feeling."
            },
            {
                text: "Sigh loudly enough that row fourteen is guaranteed to hear it.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "You made your irritation a cabin announcement. The baby was unmoved. The parents were not."
            },
            {
                text: "Put in earplugs and let it be someone else's hard day, not a referendum on yours.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You gave a stranger's rough flight the space to just be theirs."
            }
        ],
        glitch: {
            text: "Offer to hold the baby. You have never held a baby.",
            log: "The baby, astonishingly, stopped crying. You are now afraid to move for the rest of the flight."
        }
    },
    {
        zone: "PUBLIC", title: "The Person Who Fell In Front Of You",
        desc: "They went down hard on the sidewalk two steps ahead of you, and now there's a very short window to decide what kind of stranger you are.",
        choices: [
            {
                text: "Rush over and apologize on their behalf before they've even said anything.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You absorbed a stranger's embarrassment for them before they'd even located it themselves."
            },
            {
                text: "Freeze for a second too long, unsure if helping is your job here.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "By the time you moved, someone else already had. You still feel the half-second of not moving."
            },
            {
                text: "Ask if they're okay and help them up without making it a scene.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 8}},
                log: "You did the plain, obvious thing. It was enough."
            },
            {
                text: "Keep walking like you didn't quite see it happen.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 10}, child: {op: "subtract", value: 5}},
                log: "Someone else stopped. You kept your pace and a small, specific discomfort meant only for you."
            }
        ],
        glitch: {
            text: "Applaud, as if it were an intentional stunt.",
            log: "They did not take a bow. They did, eventually, laugh."
        }
    },
    {
        zone: "PUBLIC", title: "The Wrong Number You Answered",
        desc: "A stranger is on the other end of the line, mid-sentence, about something urgent that has nothing to do with you.",
        choices: [
            {
                text: "Stay on the line and try to help them anyway, even though you have no idea who they meant to call.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You took on a stranger's emergency because hanging up felt ruder than staying confused."
            },
            {
                text: "Hang up immediately without saying a word.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 3}, child: {op: "add", value: 0}},
                log: "You removed yourself from a problem that was never yours in the first place."
            },
            {
                text: "Politely tell them they've got the wrong number and wish them luck.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You closed the door gently instead of slamming it or leaving it open."
            }
        ],
        glitch: {
            text: "Ask them to just tell you the whole story anyway. You're invested now.",
            log: "You now know entirely too much about someone named Gary's custody hearing."
        }
    },
    {
        zone: "PUBLIC", title: "The Parking Lot Standoff",
        desc: "You both saw the spot at the same time. Neither of you has backed down, and there is now a small line of cars forming behind you both.",
        choices: [
            {
                text: "Wave them through with a big smile even though you got there first.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 8}},
                log: "You gave up a spot you were entitled to so a stranger wouldn't be briefly annoyed at you."
            },
            {
                text: "Rev the engine and hold your ground until they give up.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "You won a parking spot and lost thirty seconds of goodwill from everyone now stuck behind you."
            },
            {
                text: "Point them to the spot you can see open two rows down instead.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 5}},
                log: "You solved the actual problem instead of winning the argument about it."
            }
        ],
        glitch: {
            text: "Get out of the car and offer to play rock paper scissors.",
            log: "You always play rock first. This time, you didn't. And you lost."
        }
    },
    {
        zone: "PUBLIC", title: "The Fitting Room Mirror",
        desc: "Three angles of unflattering light and a mirror that doesn't care about your feelings. Someone outside asks how it's going in there.",
        choices: [
            {
                text: "Call out 'great, thanks!' before you've even looked down.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 8}},
                log: "You reassured a stranger through a door about a feeling you hadn't actually had yet."
            },
            {
                text: "Stand very still and stare until the moment passes on its own.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You had a small, private standoff with a mirror and the mirror won by default."
            },
            {
                text: "Say 'still deciding' and actually take a second to decide.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You let the honest answer be the answer, even through a curtain."
            },
            {
                text: "Change back into your regular clothes fast and skip buying anything at all.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 5}, child: {op: "subtract", value: 5}},
                log: "You left the store instead of the mirror. Same problem, different venue."
            }
        ],
        glitch: {
            text: "Model the outfit for the entire waiting area whether they asked or not.",
            log: "You received two genuine compliments and one very confused nod. Worth it."
        }
    },
    {
        zone: "PUBLIC", title: "The Restroom Line",
        desc: "There's one working stall, a line of eight, and someone near the front who is very audibly running out of patience with the wait.",
        choices: [
            {
                text: "Let three people cut ahead of you so no one thinks you mind.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 8}},
                log: "You gave away your spot in line three separate times to avoid being thought of as difficult."
            },
            {
                text: "Loudly point out that this line has not moved once in ten minutes.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "subtract", value: 8}, child: {op: "add", value: 0}},
                log: "You said the true thing everyone was thinking. It did not fix the plumbing."
            },
            {
                text: "Hold your spot, wait it out, and make small talk with the person next to you.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 5}},
                log: "You turned a bad wait into a slightly less bad one by just being a person about it."
            }
        ],
        glitch: {
            text: "Start a betting pool on how long the line will actually take.",
            log: "You won four dollars. The line still has not moved. Worth it."
        }
    },
    {
        zone: "PUBLIC", title: "The Face You Can't Place",
        desc: "Someone waves at you like they know you well. You have absolutely no idea who this is, and they are now three feet away and closing.",
        choices: [
            {
                text: "Fake total recognition and hope context clues fill in the blanks.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 10}, child: {op: "subtract", value: 8}},
                log: "You performed an entire friendship's worth of warmth for someone whose name you can't remember."
            },
            {
                text: "Suddenly find your phone extremely urgent and duck the interaction.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You outran a conversation you were fully capable of having."
            },
            {
                text: "Admit you're blanking and ask them to remind you how you know each other.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You told the truth instead of performing memory you didn't have. They didn't mind at all."
            }
        ],
        glitch: {
            text: "Introduce yourself as if this were the first time you've ever met.",
            log: "It was, in fact, the first time. You had actually never met this person."
        }
    },
    {
        zone: "PUBLIC", title: "The Flyer Someone Hands You",
        desc: "A stranger presses a flyer into your hand on the sidewalk and watches, expectantly, to see what you'll do with it.",
        choices: [
            {
                text: "Take it, thank them warmly, and hold onto it for three more blocks before tossing it.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You carried a flyer you didn't want for three blocks so a stranger wouldn't watch you not want it."
            },
            {
                text: "Speed up before they can even fully extend their arm.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 3}, child: {op: "add", value: 0}},
                log: "You dodged a piece of paper like it was an arrow."
            },
            {
                text: "Say 'no thanks' and keep walking at your normal pace.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You declined a piece of paper without treating it like a moral event."
            }
        ],
        glitch: {
            text: "Ask if you can hand out flyers too. You don't know what they're for.",
            log: "You now work here, apparently, for the next twenty minutes and zero dollars."
        }
    },
    {
        zone: "PUBLIC", title: "The Argument You Overheard",
        desc: "Two strangers are having a very loud, very personal fight three feet from where you're standing, and there's nowhere to look that isn't at them.",
        choices: [
            {
                text: "Step in and try to smooth things over between two people you've never met.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You took responsibility for a stranger's conflict resolution. They didn't accept your help."
            },
            {
                text: "Stand frozen, unsure whether moving will make it worse.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You became furniture until the argument found somewhere else to be."
            },
            {
                text: "Quietly step away and let it be their business, not yours.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You gave two strangers the privacy of a fight that was never going to include you."
            },
            {
                text: "Tell them both, loudly, to take it somewhere else.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 3}},
                log: "You inserted yourself into a fight that was never yours to resolve."
            }
        ],
        glitch: {
            text: "Start narrating the argument quietly to yourself like a nature documentary.",
            log: "One of them heard you. You have made an enemy and, somehow, also a fan."
        }
    },
    {
        zone: "PUBLIC", title: "The Stranger Asking For Directions",
        desc: "Someone stops you on the street, clearly lost, and you're maybe seventy percent sure you know the way they need to go.",
        choices: [
            {
                text: "Give confident, detailed directions despite the seventy percent confidence.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 5}},
                log: "You handed a stranger a confident answer instead of an honest maybe."
            },
            {
                text: "Say you're not sure and hurry off before they can ask a follow-up.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You left a lost person slightly more lost, at record speed."
            },
            {
                text: "Say what you actually know and what you don't, and point them toward someone who might know more.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You gave a stranger the size of your knowledge instead of an inflated one."
            }
        ],
        glitch: {
            text: "Walk them there yourself, twelve full blocks out of your way.",
            log: "You are now further from your destination than they were from theirs. You made a friend, sort of."
        }
    },
    {
        zone: "PUBLIC", title: "The Person Asking For Change",
        desc: "Someone asks if you have any spare change, right as you're fumbling for your keys and trying not to make eye contact.",
        choices: [
            {
                text: "Hand over more than you meant to and apologize for not having more than that.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You apologized to a stranger for the size of your own generosity."
            },
            {
                text: "Say 'sorry, not today' and keep walking without slowing down.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 3}, child: {op: "add", value: 0}},
                log: "You gave a fast, plain no and let it be exactly that, no more."
            },
            {
                text: "Say no if you mean no, or give what you can if you mean yes, and mean it either way.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "Whatever you did, you did it without performing guilt about it afterward."
            }
        ],
        glitch: {
            text: "Ask if they want to just come get lunch with you instead.",
            log: "You had lunch with a total stranger and heard a genuinely wild story. You paid. Worth it."
        }
    },
    {
        zone: "PUBLIC", title: "The Silent Elevator",
        desc: "It's a short ride, but the silence with the one other passenger has gone on for one floor too long to still feel normal.",
        choices: [
            {
                text: "Say something bright and pointless just to fill the air.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 5}},
                log: "You performed small talk for two floors so the silence wouldn't have to belong to anyone."
            },
            {
                text: "Study the floor numbers with the intensity of someone defusing a bomb.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 3}},
                log: "You survived an elevator ride by pretending very hard to be a wall fixture."
            },
            {
                text: "Let the silence just be a normal, unremarkable silence.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "Two people stood quietly near each other for a few seconds. Nothing happened. Nothing needed to."
            },
            {
                text: "Get off one floor early, for no reason you'd admit to.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "subtract", value: 5}},
                log: "You added four flights of stairs to your day to skip eleven more seconds of silence."
            }
        ],
        glitch: {
            text: "Push every button on the panel, just to see what happens.",
            log: "You are now stopping on every floor. The other passenger has begun to visibly reconsider their life choices."
        }
    },
    {
        zone: "PUBLIC", title: "The Thing You Dropped In Public",
        desc: "It hit the floor loudly and rolled somewhere. Multiple strangers definitely saw. You have to decide how big a deal this is.",
        choices: [
            {
                text: "Laugh it off way harder than it deserves so no one thinks you're bothered.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 5}},
                log: "You performed 'unbothered' at a volume the moment didn't actually require."
            },
            {
                text: "Grab it fast and pretend it never happened at all.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 5}, child: {op: "add", value: 0}},
                log: "You erased a thirty-cent incident with the urgency of a crime scene."
            },
            {
                text: "Pick it up, shrug, and move on without narrating it.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "A thing fell. You picked it up."
            }
        ],
        glitch: {
            text: "Take a bow, as if the drop were a rehearsed part of the show.",
            log: "Someone actually clapped. You are now a performance artist."
        }
    },
    {
        zone: "PUBLIC", title: "The Fire Alarm Mid-Errand",
        desc: "It goes off while you're two items into your shopping list, and everyone has to decide, together, how seriously to take it.",
        choices: [
            {
                text: "Apologize to the employee at the door like the alarm is somehow your fault.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You apologized for a fire alarm you did not build, own, or trigger."
            },
            {
                text: "Sprint outside faster than the actual emergency protocol requires.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 3}, child: {op: "add", value: 3}},
                log: "You treated a possible drill like a certain inferno. Better safe, you tell yourself."
            },
            {
                text: "Walk out calmly with everyone else and wait for the all-clear.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You treated an alarm like an alarm: worth responding to, not worth panicking over."
            }
        ],
        glitch: {
            text: "Ask an employee, with real hope, if you can still buy your two items outside.",
            log: "They said no. You asked again. They said no again, more slowly."
        }
    },
    {
        zone: "PUBLIC", title: "The Line-Cutter",
        desc: "Someone just walked straight past eleven people, including you, and set their items down at the front like it's nothing.",
        choices: [
            {
                text: "Say nothing and let it happen so you don't have to be The Person Who Says Something.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 10}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 8}},
                log: "You watched a small injustice happen live and filed it under things you'll think about later when you should be asleep."
            },
            {
                text: "Call it out loudly enough that the whole line hears you.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 10}, child: {op: "add", value: 0}},
                log: "The line briefly became a jury. The cutter lost. So did the general mood of the store."
            },
            {
                text: "Say, calmly, that the line ends back there.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 3}, child: {op: "add", value: 8}},
                log: "You named the small unfairness out loud, without turning it into a war."
            }
        ],
        glitch: {
            text: "Applaud their confidence and ask for tips on doing it yourself.",
            log: "They actually gave you a few. You will never use them. You appreciate them anyway."
        }
    },
    {
        zone: "PUBLIC", title: "The Waiting Room",
        desc: "The TV is playing something no one chose, at a volume no one agreed to, and everyone is very carefully not looking at each other.",
        choices: [
            {
                text: "Smile blandly at anyone who glances your way, just in case.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 5}},
                log: "You maintained a friendly face for a room full of people who were not looking at you closely enough to notice."
            },
            {
                text: "Stare at your phone so hard you could probably describe none of what's on it later.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 5}},
                log: "You scrolled through a phone you weren't actually reading for the better part of an hour."
            },
            {
                text: "Just sit there, bored, and let the boredom be boring.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You let a waiting room be exactly as uneventful as it actually was."
            },
            {
                text: "Ask, louder than the room expected, if anyone would mind turning the TV down.",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 10}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "Someone turned it down. Everyone also now knows exactly who asked."
            }
        ],
        glitch: {
            text: "Ask the room, out loud, if anyone else wants to talk about what's on the TV.",
            log: "One person, astonishingly, did. You now have opinions about a show you weren't watching an hour ago."
        }
    },
    {
        zone: "PUBLIC", title: "The Street Performer's Eye Contact",
        desc: "They've locked eyes with you specifically, mid-act, in front of a small crowd, and are clearly hoping you'll play along.",
        choices: [
            {
                text: "Go along with whatever bit they're doing, way past your comfort line.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 8}, child: {op: "subtract", value: 5}},
                log: "You gave a stranger's act more of your dignity than the bit actually required."
            },
            {
                text: "Break eye contact and walk fast, out of the crowd entirely.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 3}, child: {op: "add", value: 0}},
                log: "You exited a public performance as if you were guilty of a crime you didn't actually commit."
            },
            {
                text: "Play along a little, on your own terms, and enjoy it.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 8}},
                log: "You let yourself be a little bit silly in public, on purpose, for no reason but that it was fun."
            }
        ],
        glitch: {
            text: "Try to upstage the performer entirely.",
            log: "The crowd's attention has shifted to you completely. The performer looks personally betrayed."
        }
    },
    {
        zone: "PUBLIC", title: "The 'Do You Work Here?' Moment",
        desc: "You're wearing a shirt that happens to be the same color as the store's uniform, and a stranger is now approaching you with a question and a basket.",
        choices: [
            {
                text: "Try your best to actually help them find what they need.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You performed employment at a store that has never once paid you."
            },
            {
                text: "Say nothing and duck around the next aisle before they finish the sentence.",
                tag: "flight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 3}, child: {op: "add", value: 0}},
                log: "You evaded a mild case of mistaken identity like it was a subpoena."
            },
            {
                text: "Say 'sorry, I don't work here, but I think it's in aisle six.'",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You corrected the mistake and still helped, because both things were easy to do at once."
            }
        ],
        glitch: {
            text: "Lean all the way in and give them a full store tour anyway.",
            log: "You just gave a stranger better customer service than most actual employees. No one paid you. You feel weirdly great about it."
        }
    },
    {
        zone: "PUBLIC", title: "The Sneeze In The Quiet Room",
        desc: "It happens right in the middle of a silence you did not create and loud enough that it may as well have been an announcement.",
        choices: [
            {
                text: "Apologize three separate times to three separate directions.",
                tag: "fawn",
                effects: {rep: {op: "add", value: 5}, mask: {op: "add", value: 5}, child: {op: "subtract", value: 5}},
                log: "You apologized for a biological reflex like it was a breach of contract."
            },
            {
                text: "Freeze completely and hope everyone just forgets it happened.",
                tag: "freeze",
                effects: {rep: {op: "add", value: 8}, mask: {op: "add", value: 0}, child: {op: "subtract", value: 3}},
                log: "You went very still, as if stillness now could undo a sneeze from three seconds ago."
            },
            {
                text: "Say a quiet 'excuse me' and let the room move on, because it will.",
                tag: "secure",
                effects: {rep: {op: "subtract", value: 8}, mask: {op: "add", value: 0}, child: {op: "add", value: 5}},
                log: "You let a small, human noise be exactly that small."
            },
            {
                text: "Say, loudly and a little defensively, 'it's just a sneeze, everyone relax.'",
                tag: "fight",
                effects: {rep: {op: "subtract", value: 5}, mask: {op: "subtract", value: 15}, child: {op: "add", value: 0}},
                log: "Nobody had actually reacted yet. Now they have something to react to."
            }
        ],
        glitch: {
            text: "Take a bow for the sneeze, like it was the whole point of coming today.",
            log: "Someone actually said 'bless you' with real enthusiasm. A win, of sorts, for the sneeze."
        }
    }
];
