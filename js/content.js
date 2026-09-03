// ============================================================
// DEFAULT CONTENT — the entire simulation, as data.
// Everything here can be overridden by a saved or imported pack.
// Shared by index.html and editor.html.
// ============================================================
const DEFAULT_CONTENT = {
    config: {
        startingStats: { repression: 20, mask: 100, child: 50 },
        // Display names for the three stat bars. Purely cosmetic — used in the
        // stats panel and in the ↑/↓ choice hints. The underlying keys
        // (repression/mask/child) are fixed and unrelated to these labels.
        statLabels: { repression: "Repression Level", mask: "Social Mask", child: "Inner Child" },
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
        { key: "WORK", statBias: "repression" },
        { key: "HOME", statBias: "child" },
        { key: "SOCIAL", statBias: "mask" },
        { key: "SELF", statBias: "child" }
    ],

    // The five response identities are fixed — used by the engine and the Field Log.
    // Name and numeric mod are yours to retune.
    mechanisms: {
        fawn:   { name: "The Approval Loop", mod: { rep: 0,  mask: 3,  child: -5 } },
        flight: { name: "The Exit Strategy", mod: { rep: -5, mask: -3, child: 0  } },
        fight:  { name: "Hair-Trigger",      mod: { rep: -5, mask: -8, child: 0  } },
        freeze: { name: "The Void",          mod: { rep: 8,  mask: 0,  child: -5 } },
        secure: { name: "Earned Security",   mod: { rep: -5, mask: 0,  child: 5  } }
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
        repression: { title: "Panic Attack", desc: "Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet." },
        mask: { title: "Social Exile", desc: "Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but strangely free." },
        child: { title: "Total Disassociation", desc: "Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory. You feel nothing." }
    },

    // Evaluated top to bottom. First ending whose conditions all match wins.
    // An ending with no conditions always matches — keep one at the bottom as a fallback.
    endings: [
        {
            title: "The Powder Keg",
            desc: "You didn't explode. You just got very, very good at ticking.",
            conditions: [{ stat: "repression", op: ">=", value: 70 }]
        },
        {
            title: "The Performer",
            desc: "Nobody has seen the real you in years, including you.",
            conditions: [{ stat: "mask", op: ">=", value: 85 }, { stat: "child", op: "<=", value: 25 }]
        },
        {
            title: "Radically Undone",
            desc: "You stopped hiding. It cost you more than you expected, but you kept yourself.",
            conditions: [{ stat: "child", op: ">=", value: 75 }, { stat: "mask", op: "<=", value: 40 }]
        },
        {
            title: "Fragile Equilibrium",
            desc: "Nothing is fixed. Nothing is on fire. This might be what okay feels like.",
            conditions: [
                { stat: "repression", op: "<=", value: 30 },
                { stat: "mask", op: ">=", value: 40 }, { stat: "mask", op: "<=", value: 70 },
                { stat: "child", op: ">=", value: 40 }, { stat: "child", op: "<=", value: 70 }
            ]
        },
        {
            title: "Functional Enough",
            desc: "You made it to tomorrow. That's the whole job.",
            conditions: []
        }
    ],

    events: [
        // --- WORK ---
        {
            zone: "WORK", title: "The Typo",
            desc: "You sent an email to your boss with a minor typo. Your brain immediately interprets this as a fatal error that will result in destitution.",
            choices: [
                { text: "Send a frantic 3-paragraph apology.", tag: "fawn", effects: { mask: 10, child: -15, rep: -5 }, log: "You debased yourself for a misplaced comma." },
                { text: "Stare at the wall and dissociate for 20 minutes.", tag: "freeze", effects: { mask: 0, child: -5, rep: 20 }, log: "You fled your physical body. The typo remains." },
                { text: "Tell yourself 'it's just a typo' while sweating profusely.", tag: "secure", effects: { mask: -5, child: 10, rep: 10 }, log: "You attempted self-soothing. It was highly unconvincing." }
            ]
        },
        {
            zone: "WORK", title: "The Compliment",
            desc: "A coworker tells you that you did a 'really great job' on the presentation. It sounds sincere.",
            choices: [
                { text: "Deflect immediately and credit the team.", tag: "fawn", effects: { mask: 15, child: -10, rep: 5 }, log: "You physically swatted away the affection." },
                { text: "Assume they are setting you up for a massive failure later.", tag: "freeze", effects: { mask: -5, child: -15, rep: 15 }, log: "You chose paranoia over pride." },
                { text: "Say 'Thank you' and let the discomfort burn your throat.", tag: "secure", effects: { mask: -10, child: 15, rep: -10 }, log: "You accepted love. It hurt incredibly bad." }
            ]
        },
        {
            zone: "WORK", title: "The Closed Door",
            desc: "You walk past a conference room where two managers are talking. They look at you and the door slowly closes.",
            choices: [
                { text: "Begin packing up your desk mentally.", tag: "freeze", effects: { mask: 0, child: -20, rep: 15 }, log: "You braced for the worst-case scenario." },
                { text: "Work at 300% capacity for the next four hours.", tag: "fawn", effects: { mask: 20, child: -25, rep: 10 }, log: "You overcompensated through labor. Capitalism wins." },
                { text: "Remind yourself you are an adult and this is fine.", tag: "secure", effects: { mask: 5, child: 5, rep: 10 }, log: "You tried logic. Your amygdala is ignoring it." }
            ]
        },
        {
            zone: "WORK", title: "The Reply-All",
            desc: "You meant to reply privately with a joke about the meeting length. You did not reply privately.",
            choices: [
                { text: "Immediately close your laptop and consider a new identity.", tag: "flight", effects: { mask: -20, child: -10, rep: 20 }, log: "You attempted to physically escape the timeline." },
                { text: "Send a follow-up message that says 'ignore that lol'.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You tried to laugh-track your way out of consequences." },
                { text: "Own it. Reply-all again: 'No notes retracted.'", tag: "fight", effects: { mask: -15, child: 15, rep: -10 }, log: "You chose chaos over shame. Bold." }
            ]
        },
        {
            zone: "WORK", title: "The Calendar Invite",
            desc: "'Quick Chat' with your manager. No agenda. Fifteen minutes from now.",
            choices: [
                { text: "Spend the fifteen minutes drafting a resignation letter you won't send.", tag: "freeze", effects: { rep: 25, mask: 0, child: -15 }, log: "You pre-grieved a job you still have." },
                { text: "Ask a coworker if they've heard anything. They haven't.", tag: "fawn", effects: { mask: 5, child: -5, rep: 10 }, log: "You outsourced your anxiety to someone equally unequipped." },
                { text: "Walk in assuming it's fine. It's about the parking lot.", tag: "secure", effects: { mask: 0, child: 10, rep: -15 }, log: "It was never about you. Rude, but freeing." }
            ]
        },
        {
            zone: "WORK", title: "The Slack Read Receipt",
            desc: "Seen 3:12 PM. It is now 6:47 PM. No reply.",
            choices: [
                { text: "Send a second message: 'no worries just following up!'", tag: "fawn", effects: { mask: 10, child: -15, rep: 5 }, log: "You apologized for existing in their inbox." },
                { text: "Close Slack and refuse to open it until tomorrow.", tag: "flight", effects: { mask: -5, child: 0, rep: 15 }, log: "Out of sight, technically not out of mind." },
                { text: "Assume you've been quietly reassigned in their mind.", tag: "freeze", effects: { rep: 20, mask: 0, child: -10 }, log: "You promoted a theory to a fact with zero evidence." }
            ]
        },

        // --- HOME ---
        {
            zone: "HOME", title: "The Sigh",
            desc: "Your partner sighs audibly in the other room. You have absolutely zero context for why.",
            choices: [
                { text: "Assume it's your fault and quietly clean the kitchen.", tag: "fawn", effects: { mask: 15, child: -20, rep: 10 }, log: "You traded self-worth for perceived safety." },
                { text: "Ask aggressively, 'IS SOMETHING WRONG?!'", tag: "fight", effects: { mask: -25, child: 5, rep: -15 }, log: "You struck first to avoid being struck. Classic." },
                { text: "Put on noise-canceling headphones.", tag: "flight", effects: { mask: -10, child: 0, rep: 25 }, log: "Avoidance achieved. The tension is stored in your jaw." }
            ]
        },
        {
            zone: "HOME", title: "The Dishes in the Sink",
            desc: "Your partner says 'hey, whenever you get a chance' about the dishes. Their tone was completely neutral.",
            choices: [
                { text: "Do the dishes at 11 PM, narrating your resentment internally.", tag: "fawn", effects: { mask: 10, child: -15, rep: 15 }, log: "You complied loudly, in your head only." },
                { text: "Say 'I was GOING to' with more heat than the sentence needed.", tag: "fight", effects: { mask: -15, child: 5, rep: -10 }, log: "The dishes were never the real defendant." },
                { text: "Leave the room to 'find something' for four minutes.", tag: "flight", effects: { mask: -5, child: -5, rep: 10 }, log: "A tactical retreat from a sink." }
            ]
        },
        {
            zone: "HOME", title: "The Family Group Chat",
            desc: "Your aunt just brought up something from Thanksgiving 2019. Nobody asked.",
            choices: [
                { text: "Mute the chat and pretend your phone is broken.", tag: "flight", effects: { mask: -5, child: 0, rep: 10 }, log: "You unplugged from the family server." },
                { text: "Draft a measured correction, then delete it three times.", tag: "freeze", effects: { rep: 20, mask: 5, child: -10 }, log: "You drafted diplomacy and shipped silence." },
                { text: "Send a single laughing emoji and nothing else.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You de-escalated with punctuation." }
            ]
        },
        {
            zone: "HOME", title: "The Silent Car Ride",
            desc: "Twenty minutes home. Nobody has said anything since you left. You are replaying the entire evening.",
            choices: [
                { text: "Turn the radio up to fill the space.", tag: "flight", effects: { mask: 0, child: -5, rep: 10 }, log: "You outsourced the silence to a pop song." },
                { text: "Ask 'you okay?' four separate times.", tag: "fawn", effects: { mask: 5, child: -10, rep: 5 }, log: "You checked the temperature of a room that was fine." },
                { text: "Sit in it. Actually just sit in it.", tag: "secure", effects: { mask: 0, child: 10, rep: -15 }, log: "Twenty minutes of quiet did not, in fact, kill you." }
            ]
        },

        // --- SOCIAL ---
        {
            zone: "SOCIAL", title: "The Grocery Store Aisle",
            desc: "Someone is standing exactly in front of the specific brand of oat milk you need. They are taking a very long time.",
            choices: [
                { text: "Pretend to look at regular milk until they leave.", tag: "flight", effects: { mask: 10, child: -10, rep: 15 }, log: "You sacrificed your time to avoid taking up space." },
                { text: "Say 'Excuse me' using a voice three octaves higher than normal.", tag: "fawn", effects: { mask: 5, child: -5, rep: 5 }, log: "You utilized the Customer Service Fawning Voice." },
                { text: "Abandon the oat milk. You didn't deserve it anyway.", tag: "freeze", effects: { mask: 0, child: -25, rep: 10 }, log: "You punished yourself for a stranger's existence." }
            ]
        },
        {
            zone: "SOCIAL", title: "The Unread Notification",
            desc: "A friend replied 'K.' to a vulnerable paragraph you sent them.",
            choices: [
                { text: "Mentally draft a completely new friend group.", tag: "fight", effects: { mask: -15, child: -15, rep: 20 }, log: "You burned the bridge in your mind." },
                { text: "Send 4 memes immediately to lighten the mood.", tag: "fawn", effects: { mask: 15, child: -20, rep: 5 }, log: "You performed as the Jester to avoid abandonment." },
                { text: "Throw your phone into a soft pile of laundry.", tag: "flight", effects: { mask: 0, child: 10, rep: 10 }, log: "You successfully removed the object of your pain." }
            ]
        },
        {
            zone: "SOCIAL", title: "The Uneven Text Energy",
            desc: "Your friend's reply is just 'oh nice' where it used to be three exclamation points.",
            choices: [
                { text: "Reread every message you've sent them for the last month.", tag: "freeze", effects: { rep: 20, child: -10, mask: 0 }, log: "You audited a friendship for crimes that don't exist." },
                { text: "Match their energy exactly, one for one.", tag: "fight", effects: { mask: -10, child: 0, rep: 5 }, log: "Mutually assured emotional disengagement." },
                { text: "Text them something low-stakes and let it go.", tag: "secure", effects: { rep: -10, mask: 5, child: 5 }, log: "You extended trust without an audit." }
            ]
        },
        {
            zone: "SOCIAL", title: "The Group Photo",
            desc: "You've been tagged. The angle is unkind. Twelve people have already liked it.",
            choices: [
                { text: "Untag yourself and message the poster to take it down.", tag: "fight", effects: { mask: -10, child: 5, rep: -5 }, log: "You defended your own image. Feels illegal, isn't." },
                { text: "Leave it up and never look at that post again.", tag: "flight", effects: { mask: 0, child: -10, rep: 10 }, log: "You conceded the internet round." },
                { text: "Zoom in and catalog every flaw for later.", tag: "freeze", effects: { rep: 20, child: -20, mask: 0 }, log: "You built a case file against your own face." }
            ]
        },
        {
            zone: "SOCIAL", title: "The Small Talk Void",
            desc: "Stuck in an elevator with a coworker for four floors. Neither of you has said anything for eleven seconds.",
            choices: [
                { text: "Comment on the elevator's slowness like it's breaking news.", tag: "fawn", effects: { mask: 10, child: -5, rep: 0 }, log: "You filled the void with the safest possible noise." },
                { text: "Stare at the floor numbers with religious intensity.", tag: "freeze", effects: { rep: 15, mask: -5, child: 0 }, log: "You willed the doors open through sheer discomfort." },
                { text: "Let the silence be silence.", tag: "secure", effects: { rep: -5, mask: 0, child: 5 }, log: "Eleven seconds of quiet did not, in fact, kill you." }
            ]
        },

        // --- SELF ---
        {
            zone: "SELF", title: "The Nostalgic Smell",
            desc: "You catch a whiff of a specific cleaning product that smells exactly like your childhood home.",
            choices: [
                { text: "Hold your breath until you pass out.", tag: "flight", effects: { mask: 0, child: -5, rep: 30 }, log: "You physically rejected the memory." },
                { text: "Cry silently in a bathroom stall.", tag: "secure", effects: { mask: -10, child: 20, rep: -20 }, log: "You processed an emotion. Minus points to social mask." },
                { text: "Make a dark, self-deprecating joke to a coworker.", tag: "fight", effects: { mask: -15, child: -10, rep: 5 }, log: "You weaponized the trauma for comedy." }
            ]
        },
        {
            zone: "SELF", title: "The Mirror",
            desc: "You catch your reflection in a dark window and don't recognize yourself for a full second.",
            choices: [
                { text: "Immediately look away and think about anything else.", tag: "flight", effects: { mask: 0, child: -10, rep: 15 }, log: "You declined an invitation from your own face." },
                { text: "Stare longer, looking for evidence of who you used to be.", tag: "freeze", effects: { rep: 15, child: -15, mask: 0 }, log: "You interrogated a witness who can't testify." },
                { text: "Say something kind to it, out loud, even though it's weird.", tag: "secure", effects: { rep: -15, child: 15, mask: -5 }, log: "You addressed yourself like a person worth addressing." }
            ]
        },
        {
            zone: "SELF", title: "The Old Journal Entry",
            desc: "You find something you wrote at nineteen. It is more honest than anything you've said out loud this year.",
            choices: [
                { text: "Close it immediately and never mention this happened.", tag: "flight", effects: { mask: 0, child: -10, rep: 10 }, log: "You returned the evidence to its tomb." },
                { text: "Feel a specific, targeted contempt for who you used to be.", tag: "fight", effects: { mask: 0, child: -20, rep: 5 }, log: "You picked a fight with a nineteen-year-old and lost." },
                { text: "Let it be true. You used to know something you forgot.", tag: "secure", effects: { rep: -10, child: 20, mask: -5 }, log: "You let an old truth back into the room." }
            ]
        },
        {
            zone: "SELF", title: "The 3 AM Wake-Up",
            desc: "No reason. No noise. Just awake, and your brain has already opened seventeen tabs of decade-old conversations.",
            choices: [
                { text: "Start drafting an apology text you'll delete by morning.", tag: "fawn", effects: { mask: 5, child: -15, rep: 10 }, log: "You litigated a conversation the other person forgot happened." },
                { text: "Get up and reorganize something small and physical.", tag: "flight", effects: { mask: 0, child: 0, rep: 10 }, log: "You redirected 3 AM into a drawer." },
                { text: "Name it: this is just cortisol, not prophecy.", tag: "secure", effects: { rep: -15, child: 10, mask: 0 }, log: "You out-argued your own nervous system, briefly." }
            ]
        },
        {
            zone: "SELF", title: "The Empty Sunday",
            desc: "Nothing is scheduled. Nobody needs anything from you. This is, somehow, the hardest part of the week.",
            choices: [
                { text: "Invent an urgent task to feel useful again.", tag: "fawn", effects: { mask: 5, child: -10, rep: 10 }, log: "You manufactured a purpose to avoid the quiet." },
                { text: "Scroll until the day disappears without you in it.", tag: "flight", effects: { mask: 0, child: -15, rep: 5 }, log: "You outsourced eight hours to a feed." },
                { text: "Sit with the unscheduled hour and let it be boring.", tag: "secure", effects: { rep: -10, child: 15, mask: 0 }, log: "You survived free time without earning it first." }
            ]
        },

        // --- WORK (expansion) ---
        {
            zone: "WORK", title: "The Autocorrect Betrayal",
            desc: "You meant to type something professional. Autocorrect had other plans. It has already been sent.",
            choices: [
                { text: "Send six frantic follow-ups explaining what you meant.", tag: "fawn", effects: { mask: 10, child: -15, rep: -5 }, log: "You buried the joke under a small avalanche of context." },
                { text: "Screenshot it, close the laptop, stare at the wall.", tag: "freeze", effects: { mask: 0, child: -10, rep: 20 }, log: "You archived the evidence and fled the scene." },
                { text: "Let the typo be funny. It's kind of funny.", tag: "secure", effects: { mask: -5, child: 10, rep: -10 }, log: "You allowed yourself to be a person who makes typos." }
            ]
        },
        {
            zone: "WORK", title: "The Performance Review Buzzword",
            desc: "Your manager says 'let's discuss your growth areas' in a tone that reveals absolutely nothing.",
            choices: [
                { text: "Mentally draft your resignation before the meeting even starts.", tag: "freeze", effects: { rep: 25, mask: 0, child: -10 }, log: "You pre-grieved a career you still have." },
                { text: "Walk in and ask directly what 'growth areas' means, specifically.", tag: "fight", effects: { mask: -10, child: 10, rep: -15 }, log: "You demanded the noun behind the euphemism." },
                { text: "Prepare a mental defense file of every accomplishment from the last three years.", tag: "fawn", effects: { mask: 10, child: -10, rep: 10 }, log: "You built a case for a trial nobody scheduled." }
            ]
        },

        // --- HOME (expansion) ---
        {
            zone: "HOME", title: "The Left-On-Read Text",
            desc: "You sent a long message to a parent, explaining how you actually feel. Marked read. Nothing since.",
            choices: [
                { text: "Open your messages to literally anyone else and get absorbed in something safer.", tag: "flight", effects: { mask: 0, child: -10, rep: 15 }, log: "You changed the channel on your own heart." },
                { text: "Reread your message eleven times, hunting for the sentence that broke it.", tag: "freeze", effects: { rep: 20, mask: 0, child: -15 }, log: "You performed an autopsy on a conversation that isn't dead yet." },
                { text: "Send nothing else. Let the silence belong to them, not you.", tag: "secure", effects: { rep: -10, mask: 0, child: 10 }, log: "You stopped auditing a door that isn't yours to open." }
            ]
        },
        {
            zone: "HOME", title: "The Different Voice on the Phone",
            desc: "Your parent picks up sounding smaller than you remember. You don't know why yet.",
            choices: [
                { text: "Immediately go bright and cheerful to lift the mood before you've even asked what's wrong.", tag: "fawn", effects: { mask: 15, child: -15, rep: 5 }, log: "You showed up as sunshine before you knew what kind of day it was." },
                { text: "Ask, flatly, exactly what's going on. No preamble.", tag: "fight", effects: { mask: -10, child: 5, rep: -5 }, log: "You skipped the small talk. It cost you nothing you needed." },
                { text: "Say 'oh, okay' and let the conversation drift somewhere safer.", tag: "freeze", effects: { rep: 15, mask: 0, child: -10 }, log: "You let a real question evaporate into weather talk." }
            ]
        },

        // --- SOCIAL (expansion) ---
        {
            zone: "SOCIAL", title: "The Friend Who Remembers Everything",
            desc: "Someone brings up a specific thing you said, off-hand, eight months ago. You have no memory of saying it.",
            choices: [
                { text: "Panic-scan your own memory for context you don't have.", tag: "freeze", effects: { rep: 15, mask: -5, child: 0 }, log: "You audited a version of yourself with no paper trail." },
                { text: "Agree enthusiastically, like you absolutely remember.", tag: "fawn", effects: { mask: 10, child: -10, rep: 5 }, log: "You co-signed a memory that isn't yours." },
                { text: "Say 'I don't actually remember that, tell me more,' and mean it.", tag: "secure", effects: { rep: -10, mask: -5, child: 10 }, log: "You let not-knowing be an ordinary, survivable thing." }
            ]
        },
        {
            zone: "SOCIAL", title: "The RSVP You Regret",
            desc: "You said yes to something two weeks ago. It's tonight. Every fiber of you wants to cancel.",
            choices: [
                { text: "Draft a vague excuse about not feeling well.", tag: "flight", effects: { mask: -5, child: -5, rep: 10 }, log: "You built an exit out of half a lie." },
                { text: "Text 'actually can't make it' with zero elaboration and hit send.", tag: "fight", effects: { mask: -15, child: 10, rep: -10 }, log: "You chose a bare truth over a padded one." },
                { text: "Go anyway. Perform enthusiasm you do not currently possess.", tag: "fawn", effects: { mask: 15, child: -20, rep: 10 }, log: "You showed up as the version of you that RSVPs on time." }
            ]
        },

        // --- SELF (expansion) ---
        {
            zone: "SELF", title: "The Unfinished Thing",
            desc: "A project, a hobby, a draft you were once genuinely excited about sits untouched in a folder you avoid opening.",
            choices: [
                { text: "Open the folder, look at it, close it again without touching anything.", tag: "freeze", effects: { rep: 15, mask: 0, child: -15 }, log: "You visited the grave without bringing flowers or a shovel." },
                { text: "Start something new and shinier instead.", tag: "flight", effects: { mask: 0, child: -5, rep: 10 }, log: "You outran the old excitement with a fresh one." },
                { text: "Open it. Change one small thing. Close it again.", tag: "secure", effects: { rep: -15, mask: -5, child: 15 }, log: "You proved the thing wasn't actually dead, just resting." }
            ]
        },
        {
            zone: "SELF", title: "The Accidental Self-Compliment",
            desc: "You catch yourself thinking something kind about your own work, unprompted, and immediately feel weird about it.",
            choices: [
                { text: "Correct yourself internally: find the flaw, restore the natural order.", tag: "fight", effects: { mask: 0, child: -15, rep: 5 }, log: "You disqualified the thought before it could get comfortable." },
                { text: "Change the subject in your own head immediately.", tag: "flight", effects: { mask: 0, child: -5, rep: 10 }, log: "You fled a compliment like it was a fire alarm." },
                { text: "Let the thought stand. Don't correct it. Just let it be true for a second.", tag: "secure", effects: { rep: -15, mask: -5, child: 20 }, log: "You let something kind about yourself survive contact with your own scrutiny." }
            ]
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
    try { localStorage.setItem(CONTENT_KEY, JSON.stringify(content)); }
    catch (e) { /* storage unavailable, custom content persists for this session only */ }
}
function resetContent() {
    contentCache = DEFAULT_CONTENT;
    try { localStorage.removeItem(CONTENT_KEY); } catch (e) { /* storage unavailable */ }
}
