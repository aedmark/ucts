// ============================================================
// RUNTIME STATE — resets every playthrough. Never part of content.
// index.html only. Depends on content.js.
// ============================================================
let state = {
    repression: 20, mask: 100, child: 50,
    turn: 1, maxTurns: 10, isGameOver: false
};
let mechanismState = {}; // tag -> { unlocked, count }
let lastEventTitle = null;
let seenEventTitles = new Set();

// New Game+: unlocked permanently on your first survival.
const NG_PLUS_KEY = 'uct_extended_unlocked';
function isNgPlusUnlocked() {
    try { return localStorage.getItem(NG_PLUS_KEY) === '1'; } catch (e) { return false; }
}
function unlockNgPlus() {
    try { localStorage.setItem(NG_PLUS_KEY, '1'); } catch (e) { /* storage unavailable, unlock just won't persist */ }
}

// DOM Elements
const elRepressionBar = document.getElementById('bar-repression');
const elRepressionVal = document.getElementById('val-repression');
const elRepressionLabel = document.getElementById('label-repression');
const elMaskBar = document.getElementById('bar-mask');
const elMaskVal = document.getElementById('val-mask');
const elMaskLabel = document.getElementById('label-mask');
const elChildBar = document.getElementById('bar-child');
const elChildVal = document.getElementById('val-child');
const elChildLabel = document.getElementById('label-child');

const DEFAULT_STAT_LABELS = { repression: "Repression Level", mask: "Social Mask", child: "Inner Child" };
function statLabels() {
    return Object.assign({}, DEFAULT_STAT_LABELS, getContent().config.statLabels || {});
}

const DEFAULT_FAILURE_ENDINGS = {
    repression: { title: "Panic Attack", desc: "Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet." },
    mask: { title: "Social Exile", desc: "Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but strangely free." },
    child: { title: "Total Disassociation", desc: "Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory. You feel nothing." }
};
function failureEndings() {
    const custom = getContent().failureEndings || {};
    return {
        repression: Object.assign({}, DEFAULT_FAILURE_ENDINGS.repression, custom.repression),
        mask: Object.assign({}, DEFAULT_FAILURE_ENDINGS.mask, custom.mask),
        child: Object.assign({}, DEFAULT_FAILURE_ENDINGS.child, custom.child)
    };
}

const elEventDisplay = document.getElementById('event-display');
const elChoicesContainer = document.getElementById('choices-container');
const elTurnCounter = document.getElementById('turn-counter');
const elActionLog = document.getElementById('action-log');

const elMechanismsPanel = document.getElementById('mechanisms-panel');
const elMechanismsList = document.getElementById('mechanisms-list');

const elEndScreen = document.getElementById('end-screen');
const elEndTitle = document.getElementById('end-title');
const elEndDesc = document.getElementById('end-desc');
const elEndNgPlusBtn = document.getElementById('end-ngplus-btn');

const elGameTitle = document.getElementById('game-title');
const elObjectiveText = document.getElementById('objective-text');
const elNgPlusBtn = document.getElementById('ng-plus-btn');

// ============================================================
// ENGINE
// ============================================================
function logAction(msg) {
    const entry = document.createElement('div');
    entry.textContent = `> Turn ${state.turn}: ${msg}`;
    elActionLog.appendChild(entry);
    elActionLog.scrollTop = elActionLog.scrollHeight;
}

function resetMechanismState() {
    mechanismState = {};
    Object.keys(getContent().mechanisms).forEach(tag => {
        mechanismState[tag] = { unlocked: false, count: 0 };
    });
}

function applyMechanismModifiers(tag, effects) {
    const content = getContent();
    const mech = content.mechanisms[tag];
    const s = mechanismState[tag];
    const e = { rep: effects.rep || 0, mask: effects.mask || 0, child: effects.child || 0 };
    if (!mech || !s || !s.unlocked) return e;
    e.rep += mech.mod.rep || 0;
    e.mask += mech.mod.mask || 0;
    e.child += mech.mod.child || 0;
    return e;
}

function trackMechanism(tag) {
    const content = getContent();
    const mech = content.mechanisms[tag];
    const s = mechanismState[tag];
    if (!mech || !s || s.unlocked) return;
    s.count++;
    if (s.count >= content.config.unlockThreshold) {
        s.unlocked = true;
        logAction(`COPING MECHANISM ACQUIRED: "${mech.name}." This will not be undone.`);
        renderMechanisms(true);
    }
}

function renderMechanisms(hasFresh) {
    const content = getContent();
    const unlockedTags = Object.entries(mechanismState).filter(([, s]) => s.unlocked).map(([tag]) => tag);
    if (unlockedTags.length === 0) {
        elMechanismsPanel.classList.add('hidden');
        return;
    }
    elMechanismsPanel.classList.remove('hidden');
    elMechanismsList.innerHTML = '';
    unlockedTags.forEach((tag, i) => {
        const badge = document.createElement('span');
        const isNewest = hasFresh && i === unlockedTags.length - 1;
        badge.className = "mechanism-badge" + (isNewest ? " fresh" : "");
        badge.textContent = content.mechanisms[tag] ? content.mechanisms[tag].name : tag;
        elMechanismsList.appendChild(badge);
    });
}

function updateUI() {
    state.repression = Math.max(0, Math.min(100, state.repression));
    state.mask = Math.max(0, Math.min(100, state.mask));
    state.child = Math.max(0, Math.min(100, state.child));

    elRepressionBar.style.width = `${state.repression}%`;
    elRepressionVal.textContent = `${state.repression}%`;
    elRepressionBar.className = state.repression > 80 ? "stat-bar-fill red-hot" : "stat-bar-fill red-dim";

    elMaskBar.style.width = `${state.mask}%`;
    elMaskVal.textContent = `${state.mask}%`;
    elMaskBar.className = state.mask < 30 ? "stat-bar-fill blue-hot" : "stat-bar-fill blue-dim";

    elChildBar.style.width = `${state.child}%`;
    elChildVal.textContent = `${state.child}%`;
    elChildBar.className = state.child < 30 ? "stat-bar-fill pink-hot" : "stat-bar-fill pink-dim";

    elTurnCounter.textContent = `Turn: ${Math.min(state.turn, state.maxTurns)}/${state.maxTurns}`;
}

function evalCondition(cond, stats) {
    const v = stats[cond.stat];
    if (typeof v !== 'number') return false;
    switch (cond.op) {
        case '>=': return v >= cond.value;
        case '<=': return v <= cond.value;
        case '>':  return v > cond.value;
        case '<':  return v < cond.value;
        case '==': return v === cond.value;
        default: return false;
    }
}

function getSurvivalEnding() {
    const content = getContent();
    const stats = { repression: state.repression, mask: state.mask, child: state.child };
    const match = content.endings.find(e => (e.conditions || []).every(c => evalCondition(c, stats)))
        || content.endings[content.endings.length - 1];

    let desc = match.desc;
    const unlockedNames = Object.entries(mechanismState)
        .filter(([, s]) => s.unlocked)
        .map(([tag]) => content.mechanisms[tag] ? content.mechanisms[tag].name : tag);
    if (unlockedNames.length) {
        desc += ` Coping mechanisms acquired: ${unlockedNames.join(', ')}.`;
    }
    return { title: match.title, desc };
}

function checkGameEnd() {
    const fe = failureEndings();
    if (state.repression >= 100) {
        endGame(fe.repression.title, fe.repression.desc);
        return true;
    }
    if (state.mask <= 0) {
        endGame(fe.mask.title, fe.mask.desc);
        return true;
    }
    if (state.child <= 0) {
        endGame(fe.child.title, fe.child.desc);
        return true;
    }
    if (state.turn > state.maxTurns) {
        if (!state.hardMode) unlockNgPlus();
        const ending = getSurvivalEnding();
        endGame(ending.title, ending.desc, true);
        return true;
    }
    return false;
}

function endGame(title, desc, win = false) {
    state.isGameOver = true;
    elEndScreen.classList.remove('hidden');
    elEndTitle.textContent = title;
    elEndTitle.className = "overlay-heading end " + (win ? "win" : "loss");
    elEndDesc.textContent = desc;
    elEndNgPlusBtn.classList.toggle('hidden', state.hardMode || !isNgPlusUnlocked());
}

// A stat's raw effect can be a plain number (legacy — an implicit relative
// delta, the only format that's ever existed) or, as of the Add/Subtract/Set
// expansion, { op: "add"|"subtract"|"set", value }. Either way this resolves
// to a plain relative delta before anything else (mechanism mods, hard-mode
// scaling) touches it, so nothing downstream needs to know the difference.
// No existing content uses the object form — this only exists so future
// choices can opt in.
function resolveStatEffect(raw, currentValue) {
    if (raw == null) return 0;
    if (typeof raw === 'number') return raw;
    if (typeof raw === 'object') {
        const v = raw.value || 0;
        if (raw.op === 'add') return v;
        if (raw.op === 'subtract') return -v;
        if (raw.op === 'set') return v - currentValue;
    }
    return 0;
}
function resolveEffects(rawEffects) {
    rawEffects = rawEffects || {};
    return {
        rep: resolveStatEffect(rawEffects.rep, state.repression),
        mask: resolveStatEffect(rawEffects.mask, state.mask),
        child: resolveStatEffect(rawEffects.child, state.child)
    };
}

function handleChoice(rawEffects, logMsg, tag) {
    if (state.isGameOver) return;
    const content = getContent();

    const resolvedEffects = resolveEffects(rawEffects);
    let effects = tag ? applyMechanismModifiers(tag, resolvedEffects) : resolvedEffects;
    if (state.hardMode) {
        const mult = content.config.hardModeMultiplier;
        effects = {
            rep: Math.round((effects.rep || 0) * mult),
            mask: Math.round((effects.mask || 0) * mult),
            child: Math.round((effects.child || 0) * mult)
        };
    }

    state.repression += effects.rep || 0;
    state.mask += effects.mask || 0;
    state.child += effects.child || 0;

    logAction(logMsg || "...");
    if (tag) trackMechanism(tag);

    state.turn++;
    updateUI();

    if (!checkGameEnd()) {
        loadRandomEvent();
    }
}

function handleGlitchChoice(evt) {
    if (state.isGameOver) return;
    const content = getContent();
    const effects = {
        rep: Math.floor(Math.random() * 51) - 25,
        mask: Math.floor(Math.random() * 51) - 25,
        child: Math.floor(Math.random() * 51) - 25
    };
    // Each event can write its own wildcard log line; packs saved before
    // that existed (or an event nobody's gotten to yet) fall back to the
    // pack-wide pool.
    let log;
    if (evt && evt.glitch && evt.glitch.log) {
        log = evt.glitch.log;
    } else {
        const logs = content.glitchLogs.length ? content.glitchLogs : ["Something happened."];
        log = logs[Math.floor(Math.random() * logs.length)];
    }
    handleChoice(effects, log, null);
}

function pickWeightedEvent() {
    const content = getContent();
    const danger = {
        repression: state.repression / 100,
        mask: (100 - state.mask) / 100,
        child: (100 - state.child) / 100
    };
    const worstStat = Object.keys(danger).reduce((a, b) => danger[b] > danger[a] ? b : a);

    let pool = content.events.filter(e => !seenEventTitles.has(e.title));
    if (pool.length === 0) pool = content.events.filter(e => e.title !== lastEventTitle);
    if (pool.length === 0) pool = content.events;

    const weights = pool.map(e => {
        const zone = content.zones.find(z => z.key === e.zone);
        return (zone && zone.statBias === worstStat) ? content.config.weakZoneWeight : 1;
    });
    const total = weights.reduce((a, b) => a + b, 0);

    let r = Math.random() * total;
    for (let i = 0; i < pool.length; i++) {
        r -= weights[i];
        if (r <= 0) return pool[i];
    }
    return pool[pool.length - 1];
}

function loadRandomEvent() {
    const content = getContent();
    const evt = pickWeightedEvent();
    lastEventTitle = evt.title;
    seenEventTitles.add(evt.title);

    elEventDisplay.innerHTML = `
        <div class="event-zone-tag" id="event-zone-tag"></div>
        <h2 class="event-title" id="event-title-el"></h2>
        <p class="event-desc live" id="event-desc-el"></p>
    `;
    document.getElementById('event-zone-tag').textContent = `[ ${evt.zone} ]`;
    document.getElementById('event-title-el').textContent = evt.title;
    document.getElementById('event-desc-el').textContent = `"${evt.desc}"`;

    elChoicesContainer.innerHTML = '';

    // No stat-direction hint on the button — the whole point now is that you
    // don't know what a choice does until you've picked it and watched the
    // bars move.
    (evt.choices || []).forEach(choice => {
        const fx = choice.effects || {};
        const btn = document.createElement('button');
        btn.className = "choice-btn";

        const textSpan = document.createElement('span');
        textSpan.textContent = choice.text || "...";
        btn.appendChild(textSpan);

        btn.onclick = () => handleChoice(fx, choice.log, choice.tag || null);
        elChoicesContainer.appendChild(btn);
    });

    if (Math.random() < content.config.glitchChance) {
        const glitchBtn = document.createElement('button');
        glitchBtn.className = "choice-btn glitch";
        const glitchTextSpan = document.createElement('span');
        // Built with textContent, not innerHTML — evt.glitch.text can come
        // from an imported pack, same as choice text elsewhere.
        glitchTextSpan.textContent = (evt.glitch && evt.glitch.text) || "??? Do something you can't predict.";
        glitchBtn.appendChild(glitchTextSpan);
        glitchBtn.onclick = () => handleGlitchChoice(evt);
        elChoicesContainer.appendChild(glitchBtn);
    }
}

function startGame(hard = false) {
    const content = getContent();
    const cfg = content.config;
    state = {
        repression: cfg.startingStats.repression,
        mask: cfg.startingStats.mask,
        child: cfg.startingStats.child,
        turn: 1,
        maxTurns: hard ? cfg.hardModeTurns : cfg.maxTurns,
        isGameOver: false,
        hardMode: hard
    };
    resetMechanismState();
    lastEventTitle = null;
    seenEventTitles = new Set();
    elEndScreen.classList.add('hidden');

    const labels = statLabels();
    elRepressionLabel.textContent = labels.repression;
    elMaskLabel.textContent = labels.mask;
    elChildLabel.textContent = labels.child;

    elGameTitle.textContent = hard ? "U.C.T.S :: EXTENDED THERAPY" : "U.C.T.S";
    elObjectiveText.textContent = `Objective: Survive ${state.maxTurns} Turns`;
    elNgPlusBtn.classList.toggle('hidden', hard || !isNgPlusUnlocked());

    elActionLog.innerHTML = hard
        ? '<div>> Extended session initiated. Your nervous system has been here before.</div>'
        : '<div>> Therapy session restarted. Commencing psychological baseline.</div>';
    renderMechanisms(false);
    updateUI();
    loadRandomEvent();
}
