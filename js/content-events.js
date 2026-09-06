// ============================================================
// EVENTS — assembled from the five zone files in js/events/ (loaded
// before this one), plus the wildcard's pack-wide fallback log lines
// (glitchLogs), used when an event doesn't define its own glitch.log.
// Loaded before content.js, which assembles DEFAULT_CONTENT from this.
// ============================================================
const CONTENT_GLITCH_LOGS = [
    "Jester seized the wheel. Nobody consented to this outcome.",
    "You did something. The Lattice does not know what.",
    "A random event occurred inside a game about control. Fitting.",
    "The Anomaly Injector fired. Efficiency wept quietly.",
    "You rolled the dice on your own nervous system."
];

const CONTENT_EVENTS = [
    ...CONTENT_EVENTS_WORK,
    ...CONTENT_EVENTS_HOME,
    ...CONTENT_EVENTS_SOCIAL,
    ...CONTENT_EVENTS_SELF,
    ...CONTENT_EVENTS_BODY
];
