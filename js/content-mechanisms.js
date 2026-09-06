// ============================================================
// COPING MECHANISMS — the five response identities (fawn/flight/fight/
// freeze/secure): display name, the stat mods applied once unlocked, and
// the description shown when a player hovers or taps an unlocked badge.
// Loaded before content.js, which assembles DEFAULT_CONTENT from this.
// ============================================================
const CONTENT_MECHANISMS = {
    fawn: {
        name: "The Approval Loop",
        desc: "You've learned that being needed is safer than being seen. It pads your Social Mask a little every time, and quietly costs your Inner Child.",
        mod: {rep: 0, mask: 3, child: -5}
    },
    flight: {
        name: "The Exit Strategy",
        desc: "Removing yourself from the room becomes reflex. It bleeds off Repression fast, but your Social Mask takes the hit every time you leave.",
        mod: {rep: -5, mask: -3, child: 0}
    },
    fight: {
        name: "Hair-Trigger",
        desc: "You stopped swallowing it. Repression drops hard and immediate, but the damage to your Social Mask is worse.",
        mod: {rep: -5, mask: -8, child: 0}
    },
    freeze: {
        name: "The Void",
        desc: "You go somewhere else while your body stays in the room. Repression quietly climbs while you're gone, and your Inner Child pays the toll.",
        mod: {rep: 8, mask: 0, child: -5}
    },
    secure: {
        name: "Earned Security",
        desc: "An actual regulated response instead of a coping one. It's the only mechanism that heals instead of trading: Repression drops, Inner Child grows.",
        mod: {rep: -5, mask: 0, child: 5}
    }
};
