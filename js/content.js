// ============================================================
// DEFAULT CONTENT — the entire simulation, as data.
// Everything here can be overridden by a saved or imported pack.
// Shared by index.html and editor.html.
//
// The bulk of it — mechanisms, endings, events — lives in
// content-mechanisms.js / content-endings.js / content-events.js, loaded
// before this file (index.html and editor.html both list them first).
// This file holds the small config/zones fields, assembles DEFAULT_CONTENT
// from all four, and owns pack storage (getContent/saveContent/resetContent).
// ============================================================
const DEFAULT_CONTENT = {
    config: {
        startingStats: {repression: 40, mask: 80, child: 30},
        // Arcade rolls each stat independently within its own range instead of
        // using startingStats — ranges are picked so every stat gets a comparable
        // buffer to its danger threshold (repression's is inverted: 100 minus
        // the roll), rather than child starting with roughly half the runway
        // repression and mask get under the fixed defaults above.
        arcadeStartingStats: {
            repression: {min: 20, max: 50},
            mask: {min: 50, max: 80},
            child: {min: 50, max: 80}
        },
        statLabels: {repression: "Repression Level", mask: "Social Mask", child: "Inner Child"},
        splash: {
            title: "Unresolved Childhood Trauma Simulator",
            intro: "You are about to have a very bad day. Every choice you make is quietly one of five ways people cope with stress and anxiety.\n\nYou won't know which one is which, or what it will cost you, until it's already happened."
        },
        maxTurns: 10,
        hardModeTurns: 20,
        hardModeMultiplier: 1.25,
        unlockThreshold: 3,
        glitchChance: 0.15,
        weakZoneWeight: 2.5,
        timedEventChance: 0.2,
        timedDuration: 8000
    },

    zones: [
        {key: "WORK", statBias: "repression"},
        {key: "HOME", statBias: "child"},
        {key: "SOCIAL", statBias: "mask"},
        {key: "SELF", statBias: "child"}
    ],

    mechanisms: CONTENT_MECHANISMS,

    glitchLogs: CONTENT_GLITCH_LOGS,

    failureEndings: CONTENT_FAILURE_ENDINGS,

    endings: CONTENT_ENDINGS,

    events: CONTENT_EVENTS
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
