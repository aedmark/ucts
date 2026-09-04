// ============================================================
// DEFAULT CONTENT — the entire simulation, as data.
// Everything here can be overridden by a saved or imported pack.
// Shared by index.html and editor.html.
// ============================================================
const DEFAULT_CONTENT = {
    config: {
        startingStats: {repression: 20, mask: 100, child: 50},
        // Display names for the three stat bars. Purely cosmetic — used in the
        // stats panel and in the ↑/↓ choice hints. The underlying keys
        // (repression/mask/child) are fixed and unrelated to these labels.
        statLabels: {repression: "Repression Level", mask: "Social Mask", child: "Inner Child"},
        // Shown once, on first load, before the run starts. Optional — a pack
        // missing this (or with blank title/intro) falls back to the game's
        // own title and a generic prompt instead of skipping the screen.
        splash: {
            title: "U.C.T. Simulator",
            intro: "Three stats. Ten turns. Every choice you click is quietly one of five ways people cope with a bad day — you won't know which, or what it costs, until it's already happened.\n\nPress Start when you're ready to find out."
        },
        maxTurns: 10,
        hardModeTurns: 20,
        hardModeMultiplier: 1.25,
        unlockThreshold: 3,
        glitchChance: 0.15,
        weakZoneWeight: 2.5
    },

    // Each zone leans on one of the three core stats. Event selection weights
    // toward whichever zone matches your currently most endangered stat.
    zones: [
        {key: "WORK", statBias: "repression"},
        {key: "HOME", statBias: "child"},
        {key: "SOCIAL", statBias: "mask"},
        {key: "SELF", statBias: "child"}
    ],

    // The five response identities are fixed — used by the engine and the Field Log.
    // Name and numeric mod are yours to retune.
    mechanisms: {
        fawn: {name: "The Approval Loop", mod: {rep: 0, mask: 3, child: -5}},
        flight: {name: "The Exit Strategy", mod: {rep: -5, mask: -3, child: 0}},
        fight: {name: "Hair-Trigger", mod: {rep: -5, mask: -8, child: 0}},
        freeze: {name: "The Void", mod: {rep: 8, mask: 0, child: -5}},
        secure: {name: "Earned Security", mod: {rep: -5, mask: 0, child: 5}}
    },

    glitchLogs: [
        "Jester seized the wheel. Nobody consented to this outcome.",
        "You did something. The Lattice does not know what.",
        "A random event occurred inside a game about control. Fitting.",
        "The Anomaly Injector fired. Efficiency wept quietly.",
        "You rolled the dice on your own nervous system."
    ],

    // The three ways to lose a run — hitting the hardcoded threshold on one stat
    // (repression >= 100, mask <= 0, child <= 0). Unlike survival endings these
    // aren't picked by conditions; each stat has exactly one, shown the instant
    // it crosses its threshold.
    failureEndings: {
        repression: {
            title: "Panic Attack",
            desc: "Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet."
        },
        mask: {
            title: "Social Exile",
            desc: "Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but strangely free."
        },
        child: {
            title: "Total Disassociation",
            desc: "Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory. You feel nothing."
        }
    },

    // Evaluated top to bottom. First ending whose conditions all match wins.
    // An ending with no conditions always matches — keep one at the bottom as a fallback.
    endings: [
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
    ],

    events: [
        // --- WORK ---
        {
            zone: "WORK", title: "The Typo",
            desc: "You sent an email to your boss with a minor typo. Your brain immediately interprets this as a fatal error that will result in death or financial ruin.",
            choices: [
                { text: "Send a frantic three-paragraph apology.", tag: "fawn", effects: { mask: 10, child: -15, rep: -5 }, log: "You debased yourself for a misplaced comma." },
                { text: "Stare at the wall and dissociate for 20 minutes.", tag: "freeze", effects: { mask: 0, child: -5, rep: 20 }, log: "You fled your physical body. The typo remains. Nobody died." },
                { text: "Tell yourself 'it's just a typo' while sweating profusely.", tag: "secure", effects: { mask: -5, child: 10, rep: -10 }, log: "You attempted self-soothing. It was highly unconvincing and only mildly successful." }
            ],
            glitch: { text: "Reply in fluent, unhinged interpretive dance. Over email. Somehow.", log: "HR has several follow-up questions. So does everyone else." }
        },
        {
            zone: "WORK", title: "The Compliment",
            desc: "A coworker tells you that you did a 'really great job' on the presentation. It sounds sincere.",
            choices: [
                { text: "Deflect immediately and credit the team.", tag: "fawn", effects: { mask: 15, child: -10, rep: 5 }, log: "You physically swatted away the affection. Idiot." },
                { text: "Assume they are setting you up for a massive failure later and prepare a defense.", tag: "fight", effects: { mask: -5, child: -15, rep: 15 }, log: "You chose paranoia over pride. Hooray?" },
                { text: "Say 'Thank you' and let the discomfort burn your throat.", tag: "secure", effects: { mask: -10, child: 15, rep: -10 }, log: "You accepted love. It hurt less than you feared." }
            ],
            glitch: { text: "Announce, loudly, that you accept this compliment on behalf of all of humanity.", log: "Someone starts a slow clap. It does not catch on." }
        },
        {
            zone: "WORK", title: "The Closed Door",
            desc: "You walk past a conference room where two managers are talking. They look at you and the door slowly closes.",
            choices: [
                { text: "Begin packing up your desk mentally.", tag: "flight", effects: { mask: 0, child: -20, rep: 15 }, log: "You braced for the worst-case scenario. Which was Nothing." },
                { text: "Work at 300% capacity for the next four hours.", tag: "fawn", effects: { mask: 20, child: -25, rep: 10 }, log: "You overcompensated through labor. Capitalism wins." },
                { text: "Remind yourself you are an adult and this is fine.", tag: "secure", effects: { mask: 5, child: 5, rep: -10 }, log: "You chose logic. Your amygdala chose to ignore it." }
            ],
            glitch: { text: "Slide a note under the door reading only: 'I KNOW.'", log: "You know nothing. The door remains closed. Deeply satisfying anyway." }
        },
        {
            zone: "WORK", title: "The Reply-All",
            desc: "You meant to reply privately with a joke about the meeting length. You did not reply privately.",
            choices: [
                { text: "Immediately close your laptop and compose a new identity.", tag: "flight", effects: { mask: -20, child: -10, rep: 20 }, log: "You attempted to physically escape the timeline. There is no escape." },
                { text: "Send a follow-up message that says 'ignore that. lol.'", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You tried to laugh-track your way out of consequences. Hopefully it worked..." },
                { text: "Own it. Reply-all again with an article about workplace inefficiency.'", tag: "fight", effects: { mask: -15, child: 15, rep: -10 }, log: "You chose chaos over shame. Bold move with your charisma levels." }
            ],
            glitch: { text: "Reply-all a third time, this one entirely in Comic Sans.", log: "Comic Sans has never been used with such menace." }
        },
        {
            zone: "WORK", title: "The Calendar Invite",
            desc: "'Quick Chat' with your manager. No agenda. Fifteen minutes from now.",
            choices: [
                { text: "Spend the fifteen minutes drafting a resignation letter.", tag: "flight", effects: { rep: 25, mask: 0, child: -15 }, log: "You pre-grieved a job you still have." },
                { text: "Ask a coworker if they've heard anything. They haven't.", tag: "fawn", effects: { mask: 5, child: -5, rep: 10 }, log: "You outsourced your anxiety to someone equally unequipped." },
                { text: "Walk in assuming it's fine.", tag: "secure", effects: { mask: 0, child: 10, rep: -15 }, log: "It was never about you. It was about the parking lot construction." }
            ],
            glitch: { text: "Show up in full riot gear, 'just in case.'", log: "It was, in fact, about the parking lot. You overdressed." }
        },
        {
            zone: "WORK", title: "The Slack Read Receipt",
            desc: "Seen 3:12 PM. It is now 6:47 PM. No reply.",
            choices: [
                { text: "Send a second message: 'no worries just following up!'", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You apologized for existing in their inbox (and being impatient)." },
                { text: "Close Slack and refuse to open it until tomorrow.", tag: "flight", effects: { mask: -5, child: 0, rep: 15 }, log: "Out of sight, technically not out of mind. Close enough." },
                { text: "Assume you've been quietly deleted from their mind.", tag: "freeze", effects: { rep: 20, mask: 0, child: -10 }, log: "You Sherlocked your way to a panic attack with zero evidence." }
            ],
            glitch: { text: "Send a single, unprompted photo of a raccoon looking concerned.", log: "The raccoon says what you cannot." }
        },
        {
            zone: "WORK", title: "The Autocorrect Betrayal",
            desc: "You meant to type something professional. Autocorrect had other plans. It has already been sent.",
            choices: [
                { text: "Send six frantic follow-ups explaining what you meant.", tag: "fawn", effects: { mask: 10, child: -15, rep: -5 }, log: "You buried the joke under a small avalanche of context and shame." },
                { text: "Screenshot it, close the laptop, stare at the wall.", tag: "freeze", effects: { mask: 0, child: -10, rep: 20 }, log: "You archived the evidence and fled the scene of a victimless crime." },
                { text: "Let the typo be funny. It's kind of funny.", tag: "secure", effects: { mask: -5, child: 10, rep: -10 }, log: "You allowed yourself to be a person who makes typos. You survived." }
            ],
            glitch: { text: "Lean in. Claim the typo was avant-garde on purpose.", log: "You have accidentally invented a new art movement." }
        },
        {
            zone: "WORK", title: "The Performance Review Buzzword",
            desc: "Your manager says 'let's discuss your growth areas' in a tone that reveals absolutely nothing.",
            choices: [
                { text: "Mentally practice your groveling technique to beg for your job back.", tag: "freeze", effects: { rep: 25, mask: 0, child: -10 }, log: "You spent energy defending a career you still have." },
                { text: "Walk in and ask directly what 'growth areas' means, specifically.", tag: "fight", effects: { mask: -10, child: 10, rep: -15 }, log: "You demanded the noun behind the euphemism." },
                { text: "Prepare a mental defense file of every accomplishment from the last three years.", tag: "fawn", effects: { mask: 10, child: -10, rep: 10 }, log: "You built a case for a trial nobody scheduled." }
            ],
            glitch: { text: "Respond entirely in corporate buzzwords, weaponized back at them.", log: "You have synergized so hard the meeting ended early out of confusion." }
        },
        {
            zone: "WORK", title: "The Meeting That Could Have Been An Email",
            desc: "Forty-five minutes, twelve people, and the entire point could have fit in three sentences. Someone says 'let's take this offline' about the thing that was the entire meeting.",
            choices: [
                { text: "Nod along and add a supportive 'great point' to three different tangents.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You applauded the detour instead of naming it." },
                { text: "Mentally exit the call and return only when your name is said.", tag: "freeze", effects: { mask: 0, child: -10, rep: 15 }, log: "Your body stayed. The rest of you clocked out early. Good for you." },
                { text: "Ask, once, if this could be a two-line message next time.", tag: "secure", effects: { rep: -10, mask: -5, child: 10 }, log: "You said the quiet part. People respect you for it." }
            ],
            glitch: { text: "Stand up and simply read the email aloud instead.", log: "The meeting ends four seconds later. Nobody claps, but they should." }
        },
        {
            zone: "WORK", title: "The Away Message",
            desc: "You set your status to 'in a meeting' twenty minutes ago and never actually joined one. Someone just @-mentioned you directly.",
            choices: [
                { text: "Type 'sorry, just wrapped up, what's up!' like it's true.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You performed availability you didn't have." },
                { text: "Let the message sit unread for another eleven minutes.", tag: "flight", effects: { mask: -5, child: 0, rep: 15 }, log: "You bought time you'll have to pay back later." },
                { text: "Reply honestly: 'Just saw this, give me a minute.'", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You told a true, small, unremarkable thing." }
            ],
            glitch: { text: "Reply with a photo of an actual empty desk, for evidence.", log: "The desk is, notably, yours. This raises more questions than it answers." }
        },
        {
            zone: "WORK", title: "The CC'd Boss",
            desc: "A coworker loops your manager into a thread about a mistake that was mostly, but not entirely, your fault.",
            choices: [
                { text: "Immediately reply-all with a full, apologetic breakdown.", tag: "fawn", effects: { mask: 15, child: -15, rep: 5 }, log: "You built the case against yourself before anyone asked for one." },
                { text: "Reply-all clarifying, calmly, which parts were actually yours.", tag: "fight", effects: { mask: -10, child: 10, rep: -5 }, log: "You drew a line around the blame instead of absorbing all of it." },
                { text: "Leave the thread unanswered and refresh your inbox every four minutes.", tag: "freeze", effects: { rep: 20, mask: 0, child: -15 }, log: "You watched the thread instead of joining it." }
            ],
            glitch: { text: "CC the entire company. Let everyone share in this.", log: "Democracy, but for blame." }
        },
        {
            zone: "WORK", title: "The Unclaimed Credit",
            desc: "In the meeting, your manager describes your idea as something the team 'landed on together.' Nobody looks at you.",
            choices: [
                { text: "Say nothing. Add it to the list you're keeping in your head.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You filed it under evidence, case still open." },
                { text: "Laugh it off and agree it really was a group effort.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You co-signed the erasure to keep the room comfortable." },
                { text: "Mention afterward, privately and plainly, that the idea was yours.", tag: "secure", effects: { rep: -10, mask: -5, child: 15 }, log: "You said the true thing to one person instead of nobody. Feel better?" }
            ],
            glitch: { text: "Stand up and take a long, silent bow.", log: "Three people clap before realizing they don't know why." }
        },
        {
            zone: "WORK", title: "The Typing Indicator",
            desc: "Your manager's typing indicator appears, disappears, appears again. Three times. Still no message.",
            choices: [
                { text: "Stare at the little dots like they're a polygraph.", tag: "freeze", effects: { rep: 20, mask: 0, child: -10 }, log: "You interrogated punctuation that hadn't arrived yet." },
                { text: "Close the tab so you can't watch it happen.", tag: "flight", effects: { mask: 0, child: -5, rep: 15 }, log: "You removed the evidence, not the feeling." },
                { text: "Keep working. Whatever it is will say itself eventually.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You let an unfinished sentence stay unfinished." }
            ],
            glitch: { text: "Start typing back before they've even sent anything.", log: "Your dots meet their dots. A standoff of pure anticipation." }
        },
        {
            zone: "WORK", title: "The Out-of-Office Reply",
            desc: "You email a colleague something urgent. The auto-reply says they've been out since yesterday. Nobody told you.",
            choices: [
                { text: "Apologize to their inbox for bothering them at all.", tag: "fawn", effects: { mask: 10, child: -10, rep: 0 }, log: "You apologized to an away message." },
                { text: "Send a slightly sharp message to whoever should have flagged this.", tag: "fight", effects: { mask: -15, child: 5, rep: -5 }, log: "You aimed the frustration at the actual gap, not yourself." },
                { text: "Sit with the urgent thing, now un-urgent, doing nothing.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "The fire kept burning with nobody assigned to it. Insurance won't cover it." }
            ],
            glitch: { text: "Set your own auto-reply to something ominous and cryptic.", log: "It now reads: 'I am also out. Existentially.'" }
        },
        {
            zone: "WORK", title: "The New Hire Who's Already Better At This",
            desc: "Someone who started three weeks ago just solved, casually, the thing that's been quietly humiliating you for a month.",
            choices: [
                { text: "Smile, say 'nice,' and mentally recalculate your entire worth.", tag: "freeze", effects: { rep: 20, mask: 0, child: -20 }, log: "You ran a full audit off one data point. Maybe they can't do math?" },
                { text: "Ask them to walk you through it, overpraising every step.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You turned learning into a small performance of gratitude." },
                { text: "Ask them to walk you through it. Just that.", tag: "secure", effects: { rep: -10, mask: -5, child: 15 }, log: "You let not-knowing be a normal thing." }
            ],
            glitch: { text: "Challenge them to a desk-chair race down the hallway.", log: "You lose. You also nearly take out a filing cabinet." }
        },
        {
            zone: "WORK", title: "The Deadline Moved Up",
            desc: "'Small change:' the deadline that was next month is now Friday. The email has an exclamation point in it.",
            choices: [
                { text: "Reply 'no problem!' before you've checked if it's a problem.", tag: "fawn", effects: { mask: 15, child: -15, rep: 10 }, log: "You agreed to the math before calculating it. Your team hates you now. Probably." },
                { text: "Push back, in writing, on what's actually possible by Friday.", tag: "fight", effects: { mask: -10, child: 10, rep: -5 }, log: "You said the number out loud. The exclamation point has no power over you." },
                { text: "Open the file. Close the file. Open a different file.", tag: "freeze", effects: { rep: 25, mask: 0, child: -15 }, log: "You orbited the work without landing on it. Nothing gets done." }
            ],
            glitch: { text: "Reply with only a countdown timer emoji, repeated forty times.", log: "Nobody has ever communicated dread this efficiently." }
        },
        {
            zone: "WORK", title: "The Zoom Freeze",
            desc: "Your video froze mid-sentence on an expression you didn't choose. Twelve people saw it for four full seconds.",
            choices: [
                { text: "Open with a self-deprecating joke about your wifi.", tag: "fawn", effects: { mask: 10, child: -10, rep: 0 }, log: "You pre-apologized for a router's decision." },
                { text: "Turn your camera off for the rest of the call.", tag: "flight", effects: { rep: 15, mask: 0, child: -10 }, log: "You removed the risk by removing yourself." },
                { text: "Say 'sorry, it's a freezing in here' and keep going.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "A frozen face is not, it turns out, a permanent record. It's universal." }
            ],
            glitch: { text: "Lean into the frozen frame. Hold the expression on purpose now.", log: "You have become a meme in your own meeting." }
        },
        {
            zone: "WORK", title: "The Two Minutes Late",
            desc: "You join the call two minutes late. Everyone's already talking. Nobody pauses to catch you up.",
            choices: [
                { text: "Whisper 'sorry, sorry' three times while finding your seat.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You apologized for the two minutes and the seven after it." },
                { text: "Sit silently, too embarrassed to ask what you missed.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You chose confusion over one slightly awkward question. Phew!" },
                { text: "Ask, once, what you missed. Move on.", tag: "secure", effects: { rep: -10, mask: -5, child: 10 }, log: "You caught up effortlessly. Three people quietly thanked you because they weren't paying attention." }
            ],
            glitch: { text: "Enter dramatically, out of breath, narrating your own arrival like a nature documentary.", log: "'And here, the latecomer returns to the herd.'" }
        },
        {
            zone: "WORK", title: "The 'Per My Last Email'",
            desc: "Someone replies to your third follow-up with 'per my last email' and a screenshot of an answer that was not, in fact, an answer.",
            choices: [
                { text: "Reply quoting the exact unanswered question, again.", tag: "fight", effects: { mask: -15, child: 10, rep: -5 }, log: "You made the gap impossible to miss a second time. They answer the question." },
                { text: "Apologize for 'missing' the answer that wasn't there.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You took the blame for someone else's incompetence." },
                { text: "Close the thread and decide to just figure it out yourself.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You absorbed the extra work rather than the friction. Nobody learns anything." }
            ],
            glitch: { text: "Reply with the same screenshot, circled in red, seventeen times.", log: "You have made your point. Several points, actually. All the same one." }
        },
        {
            zone: "WORK", title: "The Open Door Policy",
            desc: "Your manager says 'my door is always open' during a meeting. Their door has, notably, never once been open.",
            choices: [
                { text: "File it away as one more thing you won't actually bring up.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You added a line to a list nobody's reading but you." },
                { text: "Nod like the sentence was true and useful.", tag: "fawn", effects: { mask: 10, child: -10, rep: 0 }, log: "You agreed with a door that stays shut." },
                { text: "Test it. Walk over and knock.", tag: "fight", effects: { mask: -5, child: 10, rep: -5 }, log: "You checked the claim against the evidence. You get lunch together and have a good time." }
            ],
            glitch: { text: "Bring a tiny gift as tribute, like visiting a shrine.", log: "The door, astonishingly, opens. You are unprepared for this outcome." }
        },
        {
            zone: "WORK", title: "The LinkedIn Congrats",
            desc: "A peer from two roles ago just posted 'excited to announce' a title you quietly wanted for yourself.",
            choices: [
                { text: "Like the post and write a warm, specific comment.", tag: "fawn", effects: { mask: 15, child: -15, rep: 5 }, log: "You performed happiness at a volume you didn't feel." },
                { text: "Close the app. Reopen it four minutes later.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You kept checking a wound to see if it still hurt." },
                { text: "Feel the envy, don't perform past it, close the app anyway.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You let a small ugly feeling exist without narrating it to anyone or destroying you." }
            ],
            glitch: { text: "Comment using only a single, deeply ambiguous emoji.", log: "Seventeen people have now liked your emoji. Nobody knows what it means. Least of all you." }
        },
        {
            zone: "WORK", title: "The Printer Line",
            desc: "You're fourth in line at the printer, running late, and the person ahead of you is scrolling their phone between pages.",
            choices: [
                { text: "Stand there, saying nothing, doing the math on how late you'll be.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You calculated the cost instead of asking for the copy." },
                { text: "Ask, politely but directly, if you can jump in for one page.", tag: "fight", effects: { mask: -5, child: 10, rep: -5 }, log: "You asked for the small thing you actually needed. You got it without any dirty looks." },
                { text: "Wait it out and tell yourself it's fine, it's fine, it's fine.", tag: "fawn", effects: { mask: 5, child: -10, rep: 10 }, log: "You narrated a calm you weren't actually experiencing, accomplishing nothing." }
            ],
            glitch: { text: "Start a spontaneous printer-line trivia night to pass the time.", log: "Nobody knew this much about the printer. Nobody wanted to." }
        },
        {
            zone: "WORK", title: "The Badge Scan Fail",
            desc: "Your badge doesn't scan. Three times in a row. There's a line building behind you now.",
            choices: [
                { text: "Apologize to everyone behind you individually.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You issued apologies for a malfunctioning badge reader." },
                { text: "Freeze up completely, badge in hand, brain empty except for the intense panic that you've been fired.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "The door and your nervous system both stopped responding." },
                { text: "Step aside, let people pass, try again without an audience.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You removed the audience instead of performing through it." }
            ],
            glitch: { text: "Take a bow for the growing audience behind you.", log: "Someone starts filming. This will outlive your employment here." }
        },
        {
            zone: "WORK", title: "The Wrong Name in the Email",
            desc: "You get an email addressed to someone else's name, clearly copy-pasted from a different, more glowing thread.",
            choices: [
                { text: "Assume you're actually being compared unfavorably to that person.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You built a rivalry with someone who doesn't know you exist." },
                { text: "Reply, lightly, pointing out the name mismatch.", tag: "fight", effects: { mask: -5, child: 10, rep: -5 }, log: "You named the small error instead of absorbing a large story about it." },
                { text: "Ignore it and answer as if it were addressed to you correctly.", tag: "fawn", effects: { mask: 5, child: -5, rep: 5 }, log: "You let the mistake pass to keep things smooth." }
            ],
            glitch: { text: "Reply fully in character as the other person.", log: "You are now, professionally speaking, someone else. It's going well." }
        },
        {
            zone: "WORK", title: "The End-of-Day Ping",
            desc: "A message lands in your inbox at 5:58 PM, two minutes before you were going to log off. It starts with 'quick question.'",
            choices: [
                { text: "Stay online another forty-five minutes to answer it fully.", tag: "fawn", effects: { mask: 10, child: -15, rep: 10 }, log: "You extended the day to protect someone else's evening. How noble." },
                { text: "Reply first thing tomorrow, on purpose.", tag: "secure", effects: { mask: -5, child: 10, rep: -10 }, log: "You let 5:58 PM mean what it says." },
                { text: "Stare at the message, unable to decide, until it's 7 PM anyway.", tag: "freeze", effects: { rep: 20, mask: 0, child: -15 }, log: "Indecision cost you the boundary you meant to keep." }
            ],
            glitch: { text: "Reply instantly with a single word: 'ALWAYS.'", log: "This was not the reassurance they were hoping for." }
        },
        {
            zone: "WORK", title: "The Manager's One-Word Reply",
            desc: "You send a detailed update. The reply is a single word: 'Noted.'",
            choices: [
                { text: "Reread the word eleven times, hunting for a tone that isn't there.", tag: "freeze", effects: { rep: 20, mask: 0, child: -15 }, log: "You built an entire mood off four letters. Now your tummy hurts." },
                { text: "Send a follow-up asking if everything's okay.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You went looking for reassurance and found a boundary." },
                { text: "Take the word at face value and move on with your day.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "Sometimes 'noted' just means noted." }
            ],
            glitch: { text: "Reply with an equally cryptic single word of your own.", log: "A one-word war has begun. Nobody will win it." }
        },
        // --- HOME ---
        {
            zone: "HOME", title: "The Sigh",
            desc: "Your partner sighs audibly in the other room. You have absolutely zero context for why.",
            choices: [
                { text: "Assume it's your fault and quietly clean the kitchen.", tag: "fawn", effects: { mask: 15, child: -20, rep: 10 }, log: "You traded self-worth for perceived safety and clean dishes." },
                { text: "Ask aggressively, 'IS SOMETHING WRONG?!'", tag: "fight", effects: { mask: -25, child: 5, rep: -15 }, log: "You struck first to avoid being struck. Classic." },
                { text: "Put on noise-canceling headphones.", tag: "flight", effects: { mask: -10, child: 0, rep: 25 }, log: "Avoidance achieved. The tension is stored in your jaw." }
            ],
            glitch: { text: "Sigh back, louder, escalating into a full operatic aria.", log: "Neither of you knows what started this. Both of you are committed now." }
        },
        {
            zone: "HOME", title: "The Dishes in the Sink",
            desc: "Your partner says 'hey, whenever you get a chance' about the dishes. Their tone was completely neutral.",
            choices: [
                { text: "Do the dishes at 11 PM, narrating your resentment internally while sighing externally, and heavily.", tag: "fawn", effects: { mask: 10, child: -15, rep: 15 }, log: "You complied loudly. Nobody is impressed." },
                { text: "Say 'I was GOING to' with more heat than the sentence needed.", tag: "fight", effects: { mask: -15, child: 5, rep: -10 }, log: "The dishes were never the real defendant." },
                { text: "Leave the room to 'find something' for four minutes. Flee state.", tag: "flight", effects: { mask: -5, child: -5, rep: 10 }, log: "A tactical retreat from a sink." }
            ],
            glitch: { text: "Build an elaborate dish-based sculpture instead of washing them.", log: "It's actually kind of impressive. It does not count as washing them." }
        },
        {
            zone: "HOME", title: "The Family Group Chat",
            desc: "Your aunt just brought up something from Thanksgiving 2019. Nobody asked.",
            choices: [
                { text: "Mute the chat and pretend your phone is broken.", tag: "flight", effects: { mask: -5, child: 0, rep: 10 }, log: "You unplugged from the family server." },
                { text: "Draft a measured correction, then delete it three times.", tag: "freeze", effects: { rep: 20, mask: 5, child: -10 }, log: "You drafted diplomacy and shipped silence." },
                { text: "Send a single laughing emoji and nothing else.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You de-escalated with punctuation." }
            ],
            glitch: { text: "Reply only in cryptic, unrelated fortune-cookie wisdom.", log: "'The sink remembers what the heart forgets.' Nobody asked. Nobody replies." }
        },
        {
            zone: "HOME", title: "The Silent Car Ride",
            desc: "Twenty minutes home. Nobody has said anything since you left. You are replaying the entire evening.",
            choices: [
                { text: "Turn the radio up to fill the space.", tag: "flight", effects: { mask: 0, child: -5, rep: 10 }, log: "You outsourced the silence to a pop song." },
                { text: "Ask 'you okay?' four separate times.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You checked the temperature of a room that was comfortable, not cold." },
                { text: "Sit in it. Actually just sit in it.", tag: "secure", effects: { mask: 0, child: 10, rep: -15 }, log: "Twenty minutes of quiet did not, in fact, kill you." }
            ],
            glitch: { text: "Start narrating the drive like a hushed golf commentator.", log: "'And she signals... she signals early. Remarkable composure.'" }
        },
        {
            zone: "HOME", title: "The Left-On-Read Text",
            desc: "You sent a long message to a parent, explaining how you actually feel. Marked read. Nothing since.",
            choices: [
                { text: "Open your messages to literally anyone else and get absorbed in something safer.", tag: "flight", effects: { mask: 0, child: -10, rep: 15 }, log: "You changed the channel on your own heart." },
                { text: "Reread your message eleven times, hunting for the sentence that broke it.", tag: "freeze", effects: { rep: 20, mask: 0, child: -15 }, log: "You performed an autopsy on a conversation that isn't dead yet." },
                { text: "Send nothing else. Let the silence belong to them, not you.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You stopped staring at a doorknob that isn't yours to turn." }
            ],
            glitch: { text: "Send one more message: just a single, staring emoji.", log: "The read receipt updates. The silence gets a face now." }
        },
        {
            zone: "HOME", title: "The Different Voice on the Phone",
            desc: "Your parent picks up sounding smaller than you remember. You don't know why yet.",
            choices: [
                { text: "Immediately go bright and cheerful to lift the mood before you've even asked what's wrong.", tag: "fawn", effects: { mask: 15, child: -15, rep: 5 }, log: "You showed up as sunshine before you knew what kind of day it was." },
                { text: "Ask, flatly, exactly what's going on. No preamble.", tag: "fight", effects: { mask: -10, child: 5, rep: -5 }, log: "You skipped the small talk. It cost you nothing you needed." },
                { text: "Say 'oh, okay' and let the conversation drift somewhere safer.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You let a real question evaporate into weather talk." }
            ],
            glitch: { text: "Answer back in an equally strange, unexplained voice of your own.", log: "Neither of you addresses it. Some things stay sacred and weird." }
        },
        {
            zone: "HOME", title: "The Leftover They Didn't Eat",
            desc: "You made extra on purpose, left a note. It's still in the fridge, exactly where you left it.",
            choices: [
                { text: "Eat it yourself and say nothing about the note.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You absorbed the disappointment along with the leftovers." },
                { text: "Leave it in there for three more days, unable to deal with it.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "The food and the feeling both went untouched." },
                { text: "Ask, simply, if they want any leftovers before you eat the rest.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "A question, asked plainly, is not an accusation. Plus, you get dinner." }
            ],
            glitch: { text: "Hold a tiny, formal funeral for the leftovers.", log: "Several words were said. None of them were 'I'm sorry.'" }
        },
        {
            zone: "HOME", title: "The Closed Bedroom Door",
            desc: "It's usually open. Tonight it's closed, and you don't know why.",
            choices: [
                { text: "Stand outside it for a full minute, deciding nothing.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You waited outside a door you could have just knocked on." },
                { text: "Knock and immediately apologize for whatever it is.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You apologized before you knew the charge." },
                { text: "Knock. Ask if everything is okay.", tag: "secure", effects: { rep: -10, mask: -5, child: 10 }, log: "You offered an opening instead of an assumption." }
            ],
            glitch: { text: "Slide a folded paper airplane of concern underneath it.", log: "It does not fly well on carpet. The gesture remains." }
        },
        {
            zone: "HOME", title: "The 'We Need To Talk' Text",
            desc: "Four words. No context. Sent an hour ago, and you've only just seen it.",
            choices: [
                { text: "Spend the hour composing worst-case scenarios instead of replying.", tag: "freeze", effects: { rep: 25, mask: 0, child: -15 }, log: "You lived through several futures that hadn't happened yet." },
                { text: "Reply with three apologies before you know what for.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You pled guilty to a charge that hadn't been read yet." },
                { text: "Reply: 'Okay. I'm here when you're ready.'", tag: "secure", effects: { rep: -15, mask: 0, child: 10 }, log: "You left room for the conversation instead of finishing it alone." }
            ],
            glitch: { text: "Reply with a single dramatic movie-trailer voice line.", log: "'In a world where nobody explains anything...'" }
        },
        {
            zone: "HOME", title: "The Passive-Aggressive Post-it",
            desc: "'Please rinse dishes before leaving in sink :)' — the smiley face is doing a lot of unpaid emotional labor.",
            choices: [
                { text: "Rinse everything in the house preemptively for a week.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You overcorrected to outrun one sticky note." },
                { text: "Leave a post-it back, on a clean dish and slightly too pointed.", tag: "fight", effects: { mask: -10, child: 5, rep: -5 }, log: "You returned the passive-aggression with interest. That'll show 'em." },
                { text: "Just talk to your roommate about it, out loud, later.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You used your words instead of a sticky note war. You both get pizza later and use paper plates." }
            ],
            glitch: { text: "Cover the entire kitchen in post-its of your own.", log: "The kitchen now resembles a ransom note made of politeness." }
        },
        {
            zone: "HOME", title: "The Wall Between Apartments",
            desc: "You can hear your neighbors arguing again, muffled but unmistakable, through a wall that was not built for privacy.",
            choices: [
                { text: "Turn up the TV as loud as you can and hope they can heart it.", tag: "fight", effects: { rep: -5, mask: -5, child: 10 }, log: "You drowned out a sound you couldn't control." },
                { text: "Consider, seriously, banging on the wall. Do nothing instead.", tag: "freeze", effects: { mask: 5, child: -5, rep: -5 }, log: "You almost inserted yourself into a fight that isn't yours." },
                { text: "Put on headphones and let it be someone else's problem.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "Not every wall's noise is yours to carry." }
            ],
            glitch: { text: "Knock back a rhythm, just to see if they knock again.", log: "A wall-based friendship begins, tentatively, in Morse-adjacent taps." }
        },
        {
            zone: "HOME", title: "The Empty Fridge Shelf",
            desc: "The thing you were saving for tomorrow is gone. No note, no explanation.",
            choices: [
                { text: "Ask, directly, who ate it.", tag: "fight", effects: { mask: -5, child: 10, rep: -5 }, log: "You asked the small, direct question instead of stewing." },
                { text: "Say nothing and quietly recalculate your whole week's meals.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "One missing item rearranged your entire life, silently." },
                { text: "Decide it's fine, you didn't really need it anyway.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You talked yourself out of a want that was real, and into being a doormat." }
            ],
            glitch: { text: "Launch a full forensic investigation, complete with a labeled evidence board.", log: "You have connected several pieces of red string to absolutely nothing." }
        },
        {
            zone: "HOME", title: "The Unanswered 'How Was Your Day'",
            desc: "You asked. They said 'fine' and kept scrolling. That was ten minutes ago.",
            choices: [
                { text: "Sit in the same room in total silence, waiting for more.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You waited for a door that wasn't going to open, anyway." },
                { text: "Fill the silence with details about your own day, unprompted.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You performed the conversation for the both of you." },
                { text: "Let 'fine' be enough for now. Try again later.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "Not every silence needs to be filled immediately or be analyzed." }
            ],
            glitch: { text: "Answer your own question instead, at great length, to the room.", log: "The room does not respond either. Fair." }
        },
        {
            zone: "HOME", title: "The Thermostat War",
            desc: "It's been adjusted three times today. Nobody has said a word about it out loud.",
            choices: [
                { text: "Set it where you want it and leave a note explaining why.", tag: "fight", effects: { mask: -5, child: 10, rep: -5 }, log: "You made your preference visible instead of silent. The note lasted 2 hours before it went missing." },
                { text: "Just wear a sweater and say nothing, forever. Probably.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You sacrificed your physical comfort to avoid a two-minute conversation." },
                { text: "Leave it wherever they last set it, every time.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You denied yourself a temperature change just to keep the peace." }
            ],
            glitch: { text: "Install a tiny sign declaring the thermostat a neutral zone.", log: "Switzerland would be proud. Nobody honors the treaty." }
        },
        {
            zone: "HOME", title: "The Borrowed Thing, Not Returned",
            desc: "You lent it three weeks ago. You need it now. Asking for it back feels, somehow, enormously selfish.",
            choices: [
                { text: "Buy a replacement instead of asking for it back.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You paid money to avoid a ten-second conversation." },
                { text: "Ask for it back with four qualifiers and two apologies.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You apologized for wanting your own thing back." },
                { text: "Ask for it back plainly. It's yours.", tag: "secure", effects: { rep: -10, mask: -5, child: 10 }, log: "This did not, in fact, end the friendship. They bought you dinner to thank you, instead." }
            ],
            glitch: { text: "Start a dramatic heist-movie-style plan to retrieve it.", log: "The plan involves zero actual heisting and a lot of standing outside their door." }
        },
        {
            zone: "HOME", title: "The Photo From An Easier Year",
            desc: "It's on the hallway wall, everyone smiling, from a year you remember very differently than the picture suggests.",
            choices: [
                { text: "Stop and stare at it longer than you meant to, every time.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You keep visiting a version of the year that didn't happen." },
                { text: "Consider taking it down. Don't, yet.", tag: "fight", effects: { mask: -5, child: 5, rep: 0 }, log: "You noticed the mismatch and let yourself notice it." },
                { text: "Let the photo be a photo, not a verdict on the year.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "One picture doesn't get to outvote your memory." }
            ],
            glitch: { text: "Photobomb the memory. Mentally. With your current face.", log: "Past-you and present-you now occupy the same photograph, spiritually." }
        },
        {
            zone: "HOME", title: "The Missed Call From Mom",
            desc: "Two missed calls and a voicemail you haven't pressed play on yet.",
            choices: [
                { text: "Let the voicemail sit unheard for the rest of the day.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "An unopened voicemail can hold a lot of imagined weight." },
                { text: "Call back immediately, bracing for whatever it is.", tag: "fawn", effects: { mask: 5, child: -10, rep: 10 }, log: "You armored up before you even knew what for." },
                { text: "Listen to the voicemail first. Then decide.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "Information turned out to be less scary than the dread. Have fun at Disney World!" }
            ],
            glitch: { text: "Call back using a dramatically different, unexplained voice.", log: "You are now, for reasons unclear, doing a full accent." }
        },
        {
            zone: "HOME", title: "The Empty Side Of The Bed",
            desc: "They're traveling this week. The apartment is exactly the same size and feels twice as big.",
            choices: [
                { text: "Leave the TV on all night just to fill the quiet.", tag: "freeze", effects: { rep: 10, mask: 0, child: -10 }, log: "You drowned a feeling in a sitcom rerun." },
                { text: "Text constant updates about your evening, needing the thread to stay busy.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You kept the connection loud so it wouldn't feel absent." },
                { text: "Let the apartment be quiet. It's temporary.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "Quiet turned out to be survivable, if not fun." }
            ],
            glitch: { text: "Build a small, dignified pillow fort in the empty space.", log: "The fort has excellent structural integrity and zero strategic purpose." }
        },
        {
            zone: "HOME", title: "The Return Address You Don't Recognize",
            desc: "A letter arrives from someone you haven't spoken to in years. You've been staring at the envelope, unopened, for ten minutes.",
            choices: [
                { text: "Put it in a drawer. Deal with it 'later.'", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You filed the unknown away instead of facing it." },
                { text: "Open it and immediately plan an apologetic, generous reply.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You started drafting amends before reading the actual letter." },
                { text: "Open it. Read it. Feel whatever you feel.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You let the envelope just be information." }
            ],
            glitch: { text: "Shake the envelope and try to guess the contents like a game show.", log: "You have guessed 'ferret' three times. You are not close." }
        },
        {
            zone: "HOME", title: "The Recipe Card In Her Handwriting",
            desc: "You found it while cleaning out a drawer. The handwriting stops you cold for a second you didn't expect.",
            choices: [
                { text: "Put it back exactly where it was and close the drawer.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You returned the moment to the drawer, unopened." },
                { text: "Make the recipe tonight. Let it mean whatever it means.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You let an ordinary moment hold an old, specific grief." },
                { text: "Get frustrated at how much a card can do to you.", tag: "fight", effects: { mask: -5, child: -5, rep: 5 }, log: "You argued with your own feelings and lost. Now you're sad, hungry, and the drawer is still dirty." }
            ],
            glitch: { text: "Attempt to forge a matching handwriting style for your own notes from now on.", log: "Your grocery lists now look faintly, movingly ancestral." }
        },
        {
            zone: "HOME", title: "The Holiday Seating Chart",
            desc: "You've been placed, again, between the two relatives most likely to start a debate over the mashed potatoes.",
            choices: [
                { text: "Prepare a mental list of neutral topics to redirect toward.", tag: "fawn", effects: { mask: 10, child: -10, rep: 10 }, log: "You showed up armed with small talk as crowd control." },
                { text: "Accept your fate and mentally leave the table early.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You were present in body only, and only barely. Nobody noticed." },
                { text: "Re-assign yourself to the kids table and enjoy yourself.", tag: "secure", effects: { mask: -5, child: 10, rep: -5 }, log: "You provided your own accommodation and now your nephews think you're cool." }
            ],
            glitch: { text: "Redraw the seating chart yourself, exiling the two feuding relatives to the garage.", log: "The garage now has better conversation than the dining room." }
        },
        {
            zone: "HOME", title: "The Sound Of The Garage Door At 2 AM",
            desc: "Someone's home later than expected. You're awake now, doing math you don't want to be doing.",
            choices: [
                { text: "Lie perfectly still, pretending to be asleep, wide awake.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You performed sleep you weren't getting." },
                { text: "Get up and greet them cheerfully, hiding that you were worried.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You covered the worry with a smile at 2 AM." },
                { text: "Ask in the morning if everything's okay.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You waited for daylight and asked a plain question. They just lost track of time." }
            ],
            glitch: { text: "Leap up and greet them in full detective-noir monologue.", log: "'You've got some explaining to do, at 2 AM, in this economy.'" }
        },
        {
            zone: "HOME", title: "The New Paint Color They Didn't Ask About",
            desc: "You come home to a wall that's a different color than it was this morning. Nobody mentioned it was happening.",
            choices: [
                { text: "Say you love it, immediately, before you've decided if you do.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You approved a decision before forming an opinion." },
                { text: "Ask why you weren't part of the conversation.", tag: "fight", effects: { mask: -10, child: 10, rep: -5 }, log: "You named the part that actually bothered you: not the color." },
                { text: "Say nothing and just quietly start disliking the room.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You let a wall become a symbol instead of just a wall." }
            ],
            glitch: { text: "Start referring to the wall as 'the incident' from now on.", log: "The wall is aware of its new title. It does not react." }
        },
        {
            zone: "HOME", title: "The Silence After The Front Door Closes",
            desc: "Everyone's finally gone. The apartment is quiet in a way that feels, for one disorienting second, like something's wrong.",
            choices: [
                { text: "Immediately turn on background noise to fill the silence.", tag: "freeze", effects: { rep: 10, mask: 0, child: -10 }, log: "You filled a quiet room out of habit, not need." },
                { text: "Start texting people to check if everyone got home okay.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You reached outward to avoid sitting in the quiet." },
                { text: "Let the silence be silence for a minute before doing anything.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "An empty room turned out to be just an empty room." }
            ],
            glitch: { text: "Narrate the empty apartment like a nature documentary.", log: "'And here, in its natural habitat, the human finally exhales.'" }
        },
        // --- SOCIAL ---
        {
            zone: "SOCIAL", title: "The Grocery Store Aisle",
            desc: "Someone is standing exactly in front of the specific brand of oat milk you need. They are taking a very long time.",
            choices: [
                { text: "Pretend to look at regular milk until they leave.", tag: "freeze", effects: { mask: 10, child: -10, rep: 15 }, log: "You sacrificed your time and dignity to avoid taking up space." },
                { text: "Say 'Excuse me' using a voice three octaves higher than normal.", tag: "fawn", effects: { mask: 5, child: -5, rep: 5 }, log: "You utilized the Customer Service Voice. It was effective." },
                { text: "Abandon the oat milk. You didn't deserve it anyway.", tag: "flight", effects: { mask: 5, child: -10, rep: 15 }, log: "You punished yourself for a stranger's existence." }
            ],
            glitch: { text: "Ask them, with complete sincerity, for their oat milk opinions.", log: "You now know more about oat milk than several dairy scientists." }
        },
        {
            zone: "SOCIAL", title: "The Unread Notification",
            desc: "A friend replied 'K.' to a vulnerable paragraph you sent them.",
            choices: [
                { text: "Start searching for a completely new friend group.", tag: "fight", effects: { mask: -15, child: -15, rep: 20 }, log: "You burned the bridge in your mind before there was even a threat to identify." },
                { text: "Send 4 memes immediately to lighten the mood.", tag: "fawn", effects: { mask: 10, child: -10, rep: 10 }, log: "You performed as the Jester to avoid abandonment. They didn't react. You internalize it deeply." },
                { text: "Throw your phone into a soft pile of laundry.", tag: "flight", effects: { mask: 0, child: 10, rep: 10 }, log: "You successfully removed the object of your pain. Until you get up again." }
            ],
            glitch: { text: "Reply with a single, deeply dramatic single tear emoji.", log: "They have not responded. The emoji stands alone, weeping into the void." }
        },
        {
            zone: "SOCIAL", title: "The Uneven Text Energy",
            desc: "Your friend's reply is just 'oh nice' where it used to be three exclamation points.",
            choices: [
                { text: "Reread every message you've sent them for the last month.", tag: "freeze", effects: { rep: 20, child: -10, mask: 0 }, log: "You audited a friendship for crimes that don't exist." },
                { text: "Match their energy exactly, one for one.", tag: "fight", effects: { mask: -10, child: 0, rep: 5 }, log: "Mutually assured emotional disengagement." },
                { text: "Text them something low-stakes and let it go.", tag: "secure", effects: { rep: -10, mask: 5, child: 5 }, log: "You extended trust without an audit." }
            ],
            glitch: { text: "Reply with forty exclamation points, unprompted, for no reason at all.", log: "You have single-handedly restored the energy. Possibly too much of it." }
        },
        {
            zone: "SOCIAL", title: "The Group Photo",
            desc: "You've been tagged. The angle is unkind. Twelve people have already liked it.",
            choices: [
                { text: "Untag yourself and message the poster to take it down.", tag: "fight", effects: { mask: -10, child: 5, rep: -5 }, log: "You defended your own image. Feels illegal, but it isn't." },
                { text: "Leave it up and never look at that post again.", tag: "flight", effects: { mask: 0, child: -10, rep: 10 }, log: "You conceded to the internet, this round." },
                { text: "Zoom in and catalog every flaw for later.", tag: "freeze", effects: { rep: 20, child: -20, mask: 0 }, log: "You built a case against your own face. You won... but also... lost?" }
            ],
            glitch: { text: "Comment demanding a formal recount of the likes.", log: "The recount is denied. Democracy fails you again." }
        },
        {
            zone: "SOCIAL", title: "The Small Talk Void",
            desc: "Stuck in an elevator with a coworker for four floors. Neither of you has said anything for eleven seconds.",
            choices: [
                { text: "Comment on the elevator's slowness like it's breaking news.", tag: "fawn", effects: { mask: 10, child: -5, rep: 0 }, log: "You filled the void with the safest possible noise. You and your coworker like you less, now." },
                { text: "Stare at the floor numbers with religious intensity.", tag: "freeze", effects: { rep: 15, mask: -5, child: 0 }, log: "You willed the doors open through sheer discomfort." },
                { text: "Let the silence be silence.", tag: "secure", effects: { rep: -5, mask: 0, child: 5 }, log: "Eleven seconds of quiet did not erase you from this earth." }
            ],
            glitch: { text: "Start humming the elevator music, badly, out loud.", log: "The coworker joins in. This is now, somehow, a duet." }
        },
        {
            zone: "SOCIAL", title: "The Friend Who Remembers Everything",
            desc: "Someone brings up a specific thing you said, off-hand, eight months ago. You have no memory of saying it.",
            choices: [
                { text: "Panic-scan your own memory for context you don't have.", tag: "freeze", effects: { rep: 15, mask: -5, child: 0 }, log: "You audited a version of yourself with no paper trail and came up empty handed." },
                { text: "Agree enthusiastically, like you absolutely remember.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You co-signed a memory that isn't yours." },
                { text: "Say 'I don't actually remember that, tell me more,' and mean it.", tag: "secure", effects: { rep: -10, mask: -5, child: 10 }, log: "You let not-knowing be an ordinary, survivable thing. Because it is." }
            ],
            glitch: { text: "Claim, boldly, that you were actually a time traveler that day.", log: "They seem to accept this explanation more readily than expected." }
        },
        {
            zone: "SOCIAL", title: "The RSVP You Regret",
            desc: "You said yes to something two weeks ago. It's tonight. Every fiber of you wants to cancel.",
            choices: [
                { text: "Draft a vague excuse about not feeling well.", tag: "flight", effects: { mask: -5, child: -5, rep: 10 }, log: "You built an exit out of half a lie. Nobody believes you." },
                { text: "Text 'actually can't make it' with zero elaboration and hit send.", tag: "fight", effects: { mask: -15, child: 10, rep: -10 }, log: "You chose a white lie over a padded truth. You spend the night regretting it." },
                { text: "Go anyway. Perform enthusiasm you do not currently possess.", tag: "fawn", effects: { mask: 15, child: -20, rep: 10 }, log: "You showed up as the version of you that RSVPs on time. You have fun, anyway." }
            ],
            glitch: { text: "Show up in full costume, several genres removed from the event's theme.", log: "You are the only knight at what turns out to be a beach party." }
        },
        {
            zone: "SOCIAL", title: "The Table for One",
            desc: "You get to the restaurant first. You sit alone at a table for four, aware of exactly how alone you look.",
            choices: [
                { text: "Stare at your phone intensely so you look busy, not waiting.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You performed occupation to avoid looking like a 'loser.'" },
                { text: "Apologize to the host for taking up a table for four.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You apologized for the size of a table you didn't choose." },
                { text: "Sit there. Look around. Let it be fine.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "Being visibly alone turned out fine; you got fresh breadsticks and didn't have to share any." }
            ],
            glitch: { text: "Order enough food for four and narrate an imaginary dinner party.", log: "Your imaginary guests are excellent listeners and terrible tippers." }
        },
        {
            zone: "SOCIAL", title: "The Best Man Speech You Weren't Asked to Give",
            desc: "You've known the groom for a decade. Someone he met two years ago is giving the speech instead.",
            choices: [
                { text: "Smile through the whole thing while doing quiet, silent math.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You ran a decade-long audit during a five-minute toast." },
                { text: "Decide to bring it up with him, gently, another day.", tag: "fight", effects: { mask: -5, child: 5, rep: -5 }, log: "You chose to name the hurt instead of just carrying it." },
                { text: "Clap the loudest and mean absolutely none of it.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You applauded harder than you felt to hide what you felt." }
            ],
            glitch: { text: "Stand up anyway and deliver your own speech, uninvited, mid-reception.", log: "The DJ, unsure what else to do, plays dramatic entrance music." }
        },
        {
            zone: "SOCIAL", title: "The Group Chat Without You",
            desc: "You find out, by accident, that there's a group chat that doesn't include you. It's been active for months.",
            choices: [
                { text: "Say nothing and quietly recalibrate every friendship in your life.", tag: "freeze", effects: { rep: 20, mask: 0, child: -20 }, log: "One missing chat became a referendum on everyone you know." },
                { text: "Act completely unbothered, performing it a little too well.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You built a very convincing case for a feeling you don't have." },
                { text: "Notice it stings. Don't make it a bigger story than it is.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "A feeling, felt and not expanded, passes on its own." }
            ],
            glitch: { text: "Start your own group chat. Name it something deeply petty.", log: "It has one member. It is thriving anyway." }
        },
        {
            zone: "SOCIAL", title: "The Birthday They Forgot",
            desc: "It's 8 PM. No text, no call, nothing. You know they're busy. You also know that nobody is THAT busy...",
            choices: [
                { text: "Refresh your phone every few minutes without admitting why.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You kept checking for something you'd already concluded wasn't coming." },
                { text: "Post something upbeat so nobody suspects you noticed.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You broadcast 'fine' to cover a very 'not fine' feeling." },
                { text: "Let yourself be a little sad about it. That's allowed.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You didn't need to perform that you were okay, you arrived there naturally." }
            ],
            glitch: { text: "Throw yourself a tiny, one-person parade around the living room.", log: "The confetti will be found in strange places for weeks." }
        },
        {
            zone: "SOCIAL", title: "The Ex at the Party",
            desc: "You didn't know they'd be here. They just walked in, laughing at something, not looking your way yet.",
            choices: [
                { text: "Find a reason to be near the exit for the rest of the night.", tag: "flight", effects: { mask: 0, child: -10, rep: 15 }, log: "You mapped an escape route instead of a plan." },
                { text: "Go say 'hi' first, overly warm, before they can find you.", tag: "fawn", effects: { mask: 15, child: -15, rep: 5 }, log: "You got there first so you could control the narrative. They still make you feel powerless." },
                { text: "Stay where you are. Say 'hi' if it happens naturally.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You didn't need to manage the whole room to survive; they left before they even saw you." }
            ],
            glitch: { text: "Walk over and loudly introduce yourself as if you'd never met.", log: "This confuses everyone, including, eventually, you." }
        },
        {
            zone: "SOCIAL", title: "The Compliment That Felt Like An Audit",
            desc: "'You look so much better than you used to' is technically a compliment, but it somehow lands like backdoor insult.",
            choices: [
                { text: "Say 'thank you!' brightly and file the sting away for later.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You smiled through the part that actually stung. Now your face hurts." },
                { text: "Say, lightly, 'I looked good before, too!'", tag: "fight", effects: { mask: -5, child: 10, rep: -5 }, log: "You corrected the record instead of just absorbing it. Mad respect." },
                { text: "Replay the sentence for the rest of the night.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "One sentence got more airtime in your head than the whole party. It's all you remember." }
            ],
            glitch: { text: "Demand a formal, itemized list of exactly how much better.", log: "They did not prepare a spreadsheet. You are disappointed in their lack of documentation." }
        },
        {
            zone: "SOCIAL", title: "The Awkward Wave",
            desc: "Someone you sort of know, from somewhere, makes eye contact across the room. Neither of you commits to a greeting.",
            choices: [
                { text: "You suddenly find your phone extremely interesting.", tag: "flight", effects: { mask: 0, child: -5, rep: 10 }, log: "You disappeared into a screen to avoid making a decision." },
                { text: "Overcommit to a huge wave and walk over, unsure why.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You escalated a maybe into a whole interaction. It's awkward." },
                { text: "Give a small, real wave and let it be whatever it is.", tag: "secure", effects: { rep: -5, mask: 0, child: 5 }, log: "A half-known person got a half-committed, perfectly adequate wave." }
            ],
            glitch: { text: "Commit fully: sprint over and hug a near-stranger.", log: "This escalated several social contracts beyond what either of you agreed to." }
        },
        {
            zone: "SOCIAL", title: "The Split-the-Bill Math",
            desc: "You had a salad and water. Someone else had three cocktails. The bill's being split evenly, and everyone's already agreeing.",
            choices: [
                { text: "Agree to split evenly and say nothing about the math.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You paid for cocktails you didn't order to keep the table easy." },
                { text: "Suggest, casually, splitting it by what people actually got.", tag: "fight", effects: { mask: -10, child: 10, rep: -5 }, log: "You named the math out loud before it became a resentment. Three other people suddenly agree." },
                { text: "Pay your share silently and feel weird about it for days.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You let a bill you didn't agree with become a slow-burn grudge." }
            ],
            glitch: { text: "Produce an actual calculator and do the math out loud, in real time.", log: "The table goes silent. You do not stop calculating." }
        },
        {
            zone: "SOCIAL", title: "The Unfollow You Noticed",
            desc: "You don't know when it happened. You just noticed, scrolling, that a number is one smaller than it used to be.",
            choices: [
                { text: "Spend twenty minutes trying to figure out who it was.", tag: "freeze", effects: { rep: 20, mask: 0, child: -15 }, log: "You ran a full investigation into a single missing follower." },
                { text: "Post something extra likable to make up the difference.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You tried to outperform a number you can't actually see." },
                { text: "Close the app. It's one person. It's fine.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "A number went down. The sky, notably, did not fall. Their loss." }
            ],
            glitch: { text: "Post a single cryptic status implying you know things.", log: "You do not, in fact, know anything. The mystery deepens for everyone, including you." }
        },
        {
            zone: "SOCIAL", title: "The Small Talk About The Weather (Again)",
            desc: "Third conversation this week that's stayed entirely on the weather. You're both clearly capable of more.",
            choices: [
                { text: "Keep it light and safe, matching their energy exactly.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You stayed in the shallow end because it felt safer there." },
                { text: "Ask a real question and see what happens.", tag: "fight", effects: { mask: -5, child: 10, rep: -5 }, log: "You risked it all on a real question. You get a real answer (and a new friend)." },
                { text: "Let the conversation end there, like it always does.", tag: "freeze", effects: { rep: 10, mask: 0, child: -10 }, log: "Another conversation stayed exactly as deep as the last one. This is fine." }
            ],
            glitch: { text: "Deliver an unhinged, overly dramatic weather forecast, mid-conversation.", log: "'Sixty percent chance of feelings, with scattered vulnerability by evening.'" }
        },
        {
            zone: "SOCIAL", title: "The Party You Left Early",
            desc: "You said you were tired. You weren't tired. You just needed to be somewhere with fewer people in it.",
            choices: [
                { text: "Send an apologetic follow-up text explaining yourself.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You justified a boundary nobody actually questioned." },
                { text: "Lie awake replaying whether anyone noticed you'd gone.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You audited an exit that was, to everyone else, unremarkable and totally fine." },
                { text: "Let leaving early just be a thing you did. No debrief required.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You left when you needed to and didn't file a report about it. Irish Goodbye FTW." }
            ],
            glitch: { text: "Send the group a full, dramatic exit statement the next morning.", log: "It reads like a press release. Nobody asked for a press release." }
        },
        {
            zone: "SOCIAL", title: "The Toast You Weren't Mentioned In",
            desc: "A long, warm speech naming almost everyone important in the room. Almost.",
            choices: [
                { text: "Smile and clap while quietly re-ranking your own importance.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "A toast became a scoreboard, and you lost." },
                { text: "Compliment the speech extra hard afterward.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You praised loudest the thing that left you out." },
                { text: "Let one omission be one omission, not a verdict.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You weren't named. You were, notably, still there." }
            ],
            glitch: { text: "Stand up and propose your own toast, entirely about yourself.", log: "It runs eleven minutes. The room claps out of confusion, mostly." }
        },
        {
            zone: "SOCIAL", title: "The New Best Friend",
            desc: "Your friend has a new person they mention constantly now. Inside jokes you're not part of. You're happy for them. Mostly.",
            choices: [
                { text: "Ask enthusiastic questions about the new friend, overselling interest.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You interviewed your own replacement with a big smile on." },
                { text: "Quietly pull back from making plans, without saying why.", tag: "flight", effects: { rep: 15, mask: 0, child: -10 }, log: "You left the room before anyone asked you to." },
                { text: "Name the feeling to yourself: a little jealous, and that's okay.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "Jealousy, acknowledged, took up less room than it usually does. You all go out for drinks later." }
            ],
            glitch: { text: "Invent an equally mysterious new friend of your own to mention constantly.", log: "Your new friend, 'Gary,' does not exist. This works better than expected." }
        },
        {
            zone: "SOCIAL", title: "The 'We Should Hang Out Sometime' That Never Happens",
            desc: "The fourth time this month someone's said it. Nobody, including you, has ever proposed an actual date.",
            choices: [
                { text: "Say it back warmly, knowing it means nothing either time.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You kept a nice-sounding ritual alive instead of a friendship." },
                { text: "Actually suggest a specific day and time.", tag: "fight", effects: { mask: -5, child: 10, rep: -5 }, log: "You called the bluff, including your own. It worked." },
                { text: "Let the phrase pass, again, unchallenged.", tag: "freeze", effects: { rep: 10, mask: 0, child: -10 }, log: "A fifth 'sometime' joined the pile of the first four. Nice collection!" }
            ],
            glitch: { text: "Actually pull out a calendar and demand a date, right now.", log: "A date gets picked. Everyone is stunned, including the calendar." }
        },
        {
            zone: "SOCIAL", title: "The Loud Laugh Across The Room",
            desc: "A burst of laughter from a group nearby. You have no evidence it's about you. You're immediately certain it is.",
            choices: [
                { text: "Replay your last ten minutes of behavior for embarrassing material.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You conducted a full review with zero actual evidence." },
                { text: "Change your position in the room, just in case.", tag: "flight", effects: { mask: 0, child: -5, rep: 10 }, log: "You relocated to escape a theory you invented about yourself." },
                { text: "Let the laugh be about literally anything else. It probably is.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "Most laughter in a crowded room has nothing to do with you. Unless you're doing something funny." }
            ],
            glitch: { text: "Walk over and ask, directly, if it's about you.", log: "It was not about you. It is now, slightly, about you." }
        },
        {
            zone: "SOCIAL", title: "The Seat Saved For Someone Else",
            desc: "You go to sit down. 'Oh, that one's taken,' said kindly, but you're now standing in a room full of seated people.",
            choices: [
                { text: "Laugh it off and hover near the wall instead.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You made your own displacement look easy." },
                { text: "Stand there a beat too long, unsure what to do with your body.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "A missing chair became a small, public crisis." },
                { text: "Ask, simply, if there's another seat open.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You solved a chair problem with a chair question." }
            ],
            glitch: { text: "Sit on the floor with great, theatrical dignity instead.", log: "The floor, it turns out, has an excellent view." }
        },
        {
            zone: "SOCIAL", title: "The Group Trip You Weren't Invited To",
            desc: "The photos are already up. It looks like it was a good one. You didn't know it was happening.",
            choices: [
                { text: "Scroll through every photo, cataloging who's in how many.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You turned a vacation you weren't on into forensic evidence." },
                { text: "Like every photo enthusiastically, extra hearts included.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You applauded a trip that stung to see." },
                { text: "Close the app. Ask yourself later, calmly, if it's worth mentioning.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You gave the feeling time before deciding what to do with it." }
            ],
            glitch: { text: "Comment on every photo with a single, ominous 'interesting.'", log: "Nobody knows what you meant. You are not entirely sure either." }
        },
        {
            zone: "SOCIAL", title: "The Voicemail You Haven't Listened To",
            desc: "A friend called instead of texting, which never happens. The voicemail icon has been sitting there for two hours.",
            choices: [
                { text: "Let it sit. Voicemails are for people who don't need you to reply fast.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "An unopened voicemail can hold a lot of imagined weight." },
                { text: "Call back immediately, bracing yourself for bad news that might not be there.", tag: "fight", effects: { mask: 5, child: -10, rep: 5 }, log: "You armored up for a threat you hadn't confirmed yet." },
                { text: "Listen to it first. Then respond to what's actually there.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "The message turned out to be smaller than the dread around it." }
            ],
            glitch: { text: "Reply via voicemail of your own, without listening to theirs first.", log: "Two unheard voicemails now orbit each other, unopened, forever." }
        },
        // --- SELF ---
        {
            zone: "SELF", title: "The Nostalgic Smell",
            desc: "You catch a whiff of a specific cleaning product that smells exactly like your childhood home.",
            choices: [
                { text: "Hold your breath until you pass out.", tag: "flight", effects: { mask: 0, child: -5, rep: 30 }, log: "You physically rejected the memory." },
                { text: "Cry silently in a bathroom stall.", tag: "secure", effects: { mask: -10, child: 20, rep: -20 }, log: "You processed an emotion privately. It's okay." },
                { text: "Make a dark, self-deprecating joke to a coworker.", tag: "fight", effects: { mask: -15, child: -10, rep: 5 }, log: "You weaponized the trauma for comedy." }
            ],
            glitch: { text: "Track down the exact cleaning product, immediately, mid-errand.", log: "You now own four bottles of it. This is, medically, a lot of bottles." }
        },
        {
            zone: "SELF", title: "The Mirror",
            desc: "You catch your reflection in a dark window and don't recognize yourself for a full second.",
            choices: [
                { text: "Immediately look away and think about anything else.", tag: "flight", effects: { mask: 0, child: -10, rep: 15 }, log: "You declined an invitation from your own face." },
                { text: "Stare longer, looking for evidence of who you used to be.", tag: "freeze", effects: { rep: 15, child: -15, mask: 0 }, log: "You interrogated a witness who can't testify." },
                { text: "Say something kind to it, out loud, even though it's weird.", tag: "secure", effects: { rep: -15, child: 15, mask: -5 }, log: "You addressed yourself like a person worth addressing. Because you are." }
            ],
            glitch: { text: "Introduce yourself to the reflection like you've never met.", log: "'Hi, I'm you.' The reflection does not seem convinced either." }
        },
        {
            zone: "SELF", title: "The Old Journal Entry",
            desc: "You find something you wrote at nineteen. It is more honest than anything you've said out loud this year.",
            choices: [
                { text: "Close it immediately and never mention this happened.", tag: "flight", effects: { mask: 0, child: -10, rep: 10 }, log: "You returned the evidence to its tomb." },
                { text: "Feel a specific, targeted contempt for who you used to be.", tag: "fight", effects: { mask: 0, child: -20, rep: 5 }, log: "You picked a fight with a nineteen-year-old and lost." },
                { text: "Let it be true. You used to know something you forgot.", tag: "secure", effects: { rep: -10, child: 20, mask: -5 }, log: "You let an old truth back into the room. It feels warm." }
            ],
            glitch: { text: "Write a formal reply to nineteen-year-old you, dated properly.", log: "You have now started a pen-pal relationship with your own past." }
        },
        {
            zone: "SELF", title: "The 3 AM Wake-Up",
            desc: "No reason. No noise. Just awake, and your brain has already opened seventeen tabs of decade-old conversations.",
            choices: [
                { text: "Start drafting an apology text you'll delete by morning.", tag: "fawn", effects: { mask: 5, child: -15, rep: 10 }, log: "You litigated a conversation the other person forgot happened." },
                { text: "Get up and reorganize something small and physical.", tag: "flight", effects: { mask: 0, child: 0, rep: 10 }, log: "You redirected 3 AM into a drawer." },
                { text: "Name it: this is just cortisol, not prophecy.", tag: "secure", effects: { rep: -15, child: 10, mask: 0 }, log: "You out-argued your own nervous system and won. You sleep in victory." }
            ],
            glitch: { text: "Get up and reorganize the spice rack, aggressively, at 3 AM.", log: "The spices are now alphabetized. You are not more at peace, but the spices are." }
        },
        {
            zone: "SELF", title: "The Empty Sunday",
            desc: "Nothing is scheduled. Nobody needs anything from you. This is, somehow, the hardest part of the week.",
            choices: [
                { text: "Invent an urgent task to feel useful again.", tag: "fawn", effects: { mask: 5, child: -10, rep: 10 }, log: "You manufactured a purpose to avoid the quiet." },
                { text: "Scroll until the day disappears without you in it.", tag: "flight", effects: { mask: 0, child: -15, rep: 5 }, log: "You outsourced eight hours to a feed." },
                { text: "Sit with the unscheduled hour and let it be boring.", tag: "secure", effects: { rep: -10, child: 15, mask: 0 }, log: "You survived free time without earning it first. You didn't explode." }
            ],
            glitch: { text: "Declare it a national holiday, just for yourself, effective immediately.", log: "Attendance is mandatory. You are the only attendee." }
        },
        {
            zone: "SELF", title: "The Unfinished Thing",
            desc: "A project, a hobby, a draft you were once genuinely excited about sits untouched in a folder you avoid opening.",
            choices: [
                { text: "Open the folder, look at it, close it again without touching anything.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You visited the grave without bringing flowers or a shovel. Ingrate." },
                { text: "Start something new and shinier instead.", tag: "flight", effects: { mask: 0, child: -5, rep: 10 }, log: "You outran the old excitement with a fresh one." },
                { text: "Open it. Change one small thing. Close it again.", tag: "secure", effects: { rep: -15, mask: -5, child: 15 }, log: "You proved the thing wasn't actually dead, just resting." }
            ],
            glitch: { text: "Finish it in one unhinged, caffeinated burst, right now.", log: "It is done. It is also, somehow, about ferrets now." }
        },
        {
            zone: "SELF", title: "The Accidental Self-Compliment",
            desc: "You catch yourself thinking something kind about your own work, unprompted, and immediately feel weird about it.",
            choices: [
                { text: "Correct yourself internally: find the flaw, restore the natural order.", tag: "fight", effects: { mask: 0, child: -15, rep: 5 }, log: "You disqualified the thought before it could get comfortable." },
                { text: "Change the subject in your own head immediately.", tag: "flight", effects: { mask: 0, child: -5, rep: 10 }, log: "You fled a compliment like it was a fire alarm." },
                { text: "Let the thought stand. Don't correct it. Just let it be true for a second.", tag: "secure", effects: { rep: -15, mask: -5, child: 20 }, log: "You let something kind about yourself survive contact with your own scrutiny." }
            ],
            glitch: { text: "Say it out loud again, three more times, increasingly loudly.", log: "The dog looks concerned. The compliment stands." }
        },
        {
            zone: "SELF", title: "The Song That Still Does This To You",
            desc: "Three seconds of a song you haven't heard in years, and your chest does something you didn't authorize.",
            choices: [
                { text: "Skip it immediately and pretend you didn't feel that.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You outran a feeling that was already three seconds ahead of you." },
                { text: "Get irritated that a song still has this much power over you.", tag: "fight", effects: { mask: 0, child: -5, rep: 5 }, log: "You picked a fight with your own nervous system. It won." },
                { text: "Let it play. Feel whatever it wants you to feel.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You let three minutes of sound be exactly as small as it actually was." }
            ],
            glitch: { text: "Play it on full volume and have a small, unscheduled concert.", log: "The neighbors have opinions. You have zero regrets." }
        },
        {
            zone: "SELF", title: "The Childhood Photo You Can't Place A Feeling On",
            desc: "You're smiling in it. You don't remember if you were actually happy or just good at looking like it, even then.",
            choices: [
                { text: "Stare at it longer, trying to force a memory that isn't there.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You interrogated a photograph for information it doesn't have." },
                { text: "Decide it must have been a happy day. Move on quickly.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You assigned the photo a feeling so you wouldn't have to sit with the unknown." },
                { text: "Let 'I don't know how I felt' be a complete, acceptable answer.", tag: "secure", effects: { rep: -10, mask: 0, child: 15 }, log: "Not knowing turned out to be allowed. It always is." }
            ],
            glitch: { text: "Interview the photo directly, out loud, like a documentary subject.", log: "The photo declines to comment, as photos generally do." }
        },
        {
            zone: "SELF", title: "The Habit You Picked Up From A Parent",
            desc: "You caught yourself doing the exact thing, the exact way, that used to make you flinch when they did it.",
            choices: [
                { text: "Get angry at yourself for the resemblance.", tag: "fight", effects: { mask: 0, child: -15, rep: 10 }, log: "You punished the habit instead of just noticing it." },
                { text: "Pretend you didn't notice and keep doing it anyway.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You let the pattern run unexamined, again." },
                { text: "Notice it. Name it. Try, gently, to do the next one differently.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You caught a pattern mid-motion, which is most of the work. Keep going." }
            ],
            glitch: { text: "Lean all the way in and do a full, committed impression of them.", log: "It's uncannily accurate. This is deeply unsettling for everyone, including you." }
        },
        {
            zone: "SELF", title: "The Voice In Your Head That Isn't Yours",
            desc: "The criticism arrives in a tone you recognize. It's not how you'd talk to anyone else. It's exactly how someone once talked to you.",
            choices: [
                { text: "Agree with it. It's probably right, like it always was.", tag: "fawn", effects: { mask: 5, child: -15, rep: 5 }, log: "You gave an old voice the final word again." },
                { text: "Argue back at it, harshly, in your own head.", tag: "fight", effects: { mask: 0, child: -5, rep: -5 }, log: "You fought a voice with the same volume it uses." },
                { text: "Notice it's not your voice. You don't have to use it.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You separated the message from the messenger you inherited it from." }
            ],
            glitch: { text: "Talk back to it out loud, in public, with real conviction.", log: "A stranger gives you a wide berth. The voice, notably, has no comeback." }
        },
        {
            zone: "SELF", title: "The Ache With No Origin",
            desc: "Your shoulders have been up by your ears for an hour and you genuinely cannot remember when that started.",
            choices: [
                { text: "Ignore it. It'll probably go away on its own.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You outsourced the problem to future-you. Again. (Jerk)." },
                { text: "Push through it, it's fine, everyone's tired, this is normal.", tag: "fawn", effects: { mask: 5, child: -10, rep: 10 }, log: "You neglected a body that was actively asking for something." },
                { text: "Stop. Relax. Roll your shoulders. Unclench your jaw. Take one real breath.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You gave your body the attention it was asking for. It thanks you by flooding your brain with dopamine." }
            ],
            glitch: { text: "Blame it, loudly and specifically, on Mercury retrograde.", log: "This explains nothing. It helps anyway, somehow." }
        },
        {
            zone: "SELF", title: "The Thing You Said You'd Never Become",
            desc: "You hear the sentence leave your mouth and recognize it a half-second too late. It's not yours. It's theirs.",
            choices: [
                { text: "Spiral into a full self-indictment for the next hour.", tag: "fight", effects: { mask: 0, child: -20, rep: 15 }, log: "You sentenced yourself over one inherited sentence." },
                { text: "Laugh it off in the moment and never think about it again... You swear!", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You buried it under a laugh instead of a look." },
                { text: "Notice it. Decide, calmly, that noticing is the first step, not a failure.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You caught the echo without deciding it was proof of anything permanent." }
            ],
            glitch: { text: "Lean all the way into it, theatrically, for one bit.", log: "You have never committed to a bit this hard. Everyone is a little worried." }
        },
        {
            zone: "SELF", title: "The List of Things You're Supposed To Want",
            desc: "You're scrolling through someone else's milestones; house, promotion, wedding, etc.  You keep checking your own life against it.",
            choices: [
                { text: "Keep scrolling, keep comparing, feel worse with each one.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You audited your life against an idealized list you didn't write. Of course you fell short." },
                { text: "Convince yourself you want all of it too, just to feel aligned.", tag: "fawn", effects: { mask: 5, child: -15, rep: 10 }, log: "You borrowed someone else's wants because yours felt too quiet to trust." },
                { text: "Close the app. Ask yourself, honestly, what you actually want.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You checked the list against your own name instead of theirs." }
            ],
            glitch: { text: "Write your own unhinged, deeply specific counter-list right now.", log: "Item four is just 'a really good sandwich.' You stand by it." }
        },
        {
            zone: "SELF", title: "The Anniversary Your Body Remembers Before You Do",
            desc: "You've been off all day and couldn't say why. Then you check the date.",
            choices: [
                { text: "Push through the day as if you hadn't noticed anything at all.", tag: "freeze", effects: { rep: 20, mask: 0, child: -15 }, log: "Your body kept the appointment even after your mind tried to skip it." },
                { text: "Apologize to everyone around you for being 'off' today.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You apologized for grief that arrived on schedule, uninvited." },
                { text: "Let today be a harder day. You don't owe anyone your usual output.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You gave a hard day permission to be hard." }
            ],
            glitch: { text: "Give the day an oddly specific, ceremonial little ritual.", log: "You have invented a holiday nobody else knows about. It helps anyway." }
        },
        {
            zone: "SELF", title: "The You From Ten Years Ago",
            desc: "You try to picture yourself a decade back and feel a strange mix of tenderness and secondhand embarrassment.",
            choices: [
                { text: "Cringe hard and mentally list everything that version got wrong.", tag: "fight", effects: { mask: 0, child: -15, rep: 10 }, log: "You put a decade-old version of yourself on trial." },
                { text: "Change the subject in your own head before it goes anywhere real.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You closed the door on a version of you who was just trying, too." },
                { text: "Send a little compassion backward. They didn't know what you know now.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You forgave a person for not having information they didn't learn yet." }
            ],
            glitch: { text: "Write them a postcard. Mail it to yourself, unironically.", log: "It arrives in three days. Past-you would be delighted it worked." }
        },
        {
            zone: "SELF", title: "The Apology You Never Got",
            desc: "You've rehearsed the conversation where they finally say it. It hasn't happened. It might not.",
            choices: [
                { text: "Keep rehearsing the conversation, on a loop, indefinitely.", tag: "freeze", effects: { rep: 20, mask: 0, child: -15 }, log: "You kept a courtroom open for a trial nobody else is attending." },
                { text: "Draft the message you'd send them. Don't send it. Yet.", tag: "fight", effects: { mask: 0, child: 5, rep: -5 }, log: "You gave the anger somewhere to go besides in circles." },
                { text: "Consider that closure might have to come from you instead.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You stopped waiting for a door someone else may never open." }
            ],
            glitch: { text: "Draft their apology yourself, in full, and read it aloud dramatically.", log: "It's a great apology. Extremely well-written. Deeply, deeply fake." }
        },
        {
            zone: "SELF", title: "The Question A Stranger Asked That Undid You",
            desc: "'Are you doing okay, actually?' — from someone who barely knows you, at exactly the wrong-yet-right moment.",
            choices: [
                { text: "Say 'I'm fine!' faster than the question finished.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You answered before you let yourself hear the question." },
                { text: "Deflect and change the subject immediately.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You closed a door a stranger had, kindly, tried to open." },
                { text: "Pause. Tell a small piece of the truth.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "A stranger's question got an honest, small answer. Mutual respect intensifies." }
            ],
            glitch: { text: "Chase them down the street to actually answer honestly.", log: "They were already gone. The honesty remains, unclaimed, in the middle of the sidewalk." }
        },
        {
            zone: "SELF", title: "The Time You Almost Told Someone",
            desc: "The words were right there. You had the opening. You changed the subject instead, and you're still thinking about it hours later.",
            choices: [
                { text: "Replay the moment, cataloging exactly why you didn't say it.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You held a post-mortem for a conversation that never happened." },
                { text: "Decide it's better this way for everyone. Probably.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You reframed silence as consideration for other people." },
                { text: "Note that the opening will come again. It's not your only chance.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You released the pressure of a single missed moment." }
            ],
            glitch: { text: "Call them right now and just say it, all of it, fast.", log: "It's out. The world, remarkably, keeps turning." }
        },
        {
            zone: "SELF", title: "The Pride You Can't Say Out Loud",
            desc: "You did something genuinely well today. Saying so, even to yourself, feels like it's asking for trouble.",
            choices: [
                { text: "Immediately find the flaw to balance out the pride.", tag: "fight", effects: { mask: 0, child: -15, rep: 10 }, log: "You disqualified the good thing before it could get comfortable." },
                { text: "Feel the pride, quietly, and never mention it to anyone.", tag: "freeze", effects: { rep: 10, mask: 0, child: -5 }, log: "You let it exist, but only in a locked room." },
                { text: "Say it out loud, once, to yourself. 'I did that well.'", tag: "secure", effects: { rep: -15, mask: 0, child: 20 }, log: "You let something good about yourself survive contact with your own scrutiny, again." }
            ],
            glitch: { text: "Announce it to a stranger on the street, at volume.", log: "A stranger claps for you. This was not what either of you expected." }
        },
        {
            zone: "SELF", title: "The Nightmare You Can't Fully Remember",
            desc: "You woke up at 4 AM with your heart going and no clear memory of why. The feeling stayed. The plot didn't.",
            choices: [
                { text: "Lie there, awake, trying to force the memory back.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You chased a plot your brain had already decided to withhold." },
                { text: "Get up and immediately start the day like nothing happened.", tag: "flight", effects: { mask: 5, child: -15, rep: 10 }, log: "You outran a feeling by scheduling over it." },
                { text: "Let the feeling exist without the plot. You don't need the whole story.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You let your body's alarm matter even without a confirmed cause." }
            ],
            glitch: { text: "Draw it from memory, badly, in crayon, right now.", log: "The drawing is somehow both deeply upsetting and extremely funny." }
        },
        {
            zone: "SELF", title: "The Reflex To Apologize For Existing",
            desc: "Someone bumps into you. You apologize. To them. For being bumped into.",
            choices: [
                { text: "Apologize again, just to be safe.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You doubled down on an apology that was never yours to give." },
                { text: "Notice it happened and feel weird about it for the rest of the day.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You let a two-second reflex become an all-day mood." },
                { text: "Notice the reflex. Don't perform an apology for the apology.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You caught the reflex mid-air and let it just pass through." }
            ],
            glitch: { text: "Apologize to an inanimate object instead. With real emotion.", log: "The doorknob accepts your apology graciously. It has no other choice." }
        },
        {
            zone: "SELF", title: "The Urge To Check If You're Still Likable",
            desc: "You've refreshed the same three apps four times in the last ten minutes, looking for a number that proves something.",
            choices: [
                { text: "Keep refreshing. The number hasn't proven anything yet, but the next one might.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You outsourced your worth to a number that resets every day." },
                { text: "Post something calibrated to perform well, just to be sure.", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You engineered proof instead of just existing for a minute." },
                { text: "Put the phone down. The question doesn't need an answer right now.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "You let the urge exist without feeding it." }
            ],
            glitch: { text: "Post the most unhinged, honest thing you can think of instead.", log: "Engagement is way down but you, somehow, feel better." }
        },
        {
            zone: "SELF", title: "The Streak You Broke",
            desc: "Forty-one days. You missed yesterday. The app has already reset the number to zero, and it feels disproportionately catastrophic.",
            choices: [
                { text: "Berate yourself for the missed day, at length.", tag: "fight", effects: { mask: 0, child: -15, rep: 10 }, log: "You treated one missed day like it undid the other forty-one." },
                { text: "Decide the whole habit is ruined now and quietly stop.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "One broken streak took the whole habit down with it." },
                { text: "Start again today. The forty-one days still happened.", tag: "secure", effects: { rep: -15, mask: 0, child: 15 }, log: "A number resetting didn't erase what you'd actually built." }
            ],
            glitch: { text: "Start an entirely new, deliberately pointless streak instead.", log: "Day one of 'touching a doorframe for luck.' It's going great." }
        },
        {
            zone: "SELF", title: "The Quiet Car Where You Finally Cry",
            desc: "Nowhere dramatic. Just the car, in a parking lot, engine off, and the thing you've been holding all week finally lets go.",
            choices: [
                { text: "Stop it fast. Fix your face. Go inside like nothing happened.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You closed the door on something that had only just cracked open." },
                { text: "Apologize to no one in an empty car for crying at all.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You apologized to an empty passenger seat. It was unmoved." },
                { text: "Let it happen. Stay in the car until it passes on its own.", tag: "secure", effects: { rep: -20, mask: 0, child: 20 }, log: "You let the parking lot hold something you'd been carrying all week." }
            ],
            glitch: { text: "Turn on the radio and sing along badly through it.", log: "You are now simultaneously crying and singing off-key. A rare skill." }
        }
    ]
};

// ============================================================
// CONTENT STORAGE — an active pack overlays the default entirely.
// Shared by index.html and editor.html.
// ============================================================
const CONTENT_KEY = 'uct_custom_content_v1';
let contentCache = null;

function isValidContentPack(obj) {
    if (!obj || typeof obj !== 'object') return false;
    if (!obj.config || typeof obj.config !== 'object') return false;
    if (!Array.isArray(obj.zones) || obj.zones.length === 0) return false;
    if (!obj.mechanisms || typeof obj.mechanisms !== 'object') return false;
    if (!['fawn', 'flight', 'fight', 'freeze', 'secure'].every(k => obj.mechanisms[k])) return false;
    if (!Array.isArray(obj.glitchLogs)) return false;
    if (!Array.isArray(obj.endings) || obj.endings.length === 0) return false;
    if (!Array.isArray(obj.events) || obj.events.length === 0) return false;
    return true;
}

function deepClone(obj) {
    return JSON.parse(JSON.stringify(obj));
}

// Returns DEFAULT_CONTENT by reference when no custom pack exists — never mutate
// the returned object directly. Callers that need to edit it must deepClone() first
// (every current caller already does: editorDraft is always a clone).
function getContent() {
    if (contentCache) return contentCache;
    try {
        const raw = localStorage.getItem(CONTENT_KEY);
        const parsed = raw ? JSON.parse(raw) : null;
        contentCache = (parsed && isValidContentPack(parsed)) ? parsed : DEFAULT_CONTENT;
    } catch (e) {
        contentCache = DEFAULT_CONTENT;
    }
    return contentCache;
}

function saveContent(content) {
    contentCache = content;
    try {
        localStorage.setItem(CONTENT_KEY, JSON.stringify(content));
    } catch (e) { /* storage unavailable, custom content persists for this session only */
    }
}

function resetContent() {
    contentCache = DEFAULT_CONTENT;
    try {
        localStorage.removeItem(CONTENT_KEY);
    } catch (e) { /* storage unavailable */
    }
}
