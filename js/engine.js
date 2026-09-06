let state = {
    repression: 20, mask: 100, child: 50,
    turn: 1, maxTurns: 10, isGameOver: false
};
let mechanismState = {};
let lastEventTitle = null;
let seenEventTitles = new Set();
let timedEnabled = false;
let timerInterval = null;
let playerName = '';

function hashSeed(str) {
    let h = 1779033703 ^ str.length;
    for (let i = 0; i < str.length; i++) {
        h = Math.imul(h ^ str.charCodeAt(i), 3432918353);
        h = (h << 13) | (h >>> 19);
    }
    return () => {
        h = Math.imul(h ^ (h >>> 16), 2246822507);
        h = Math.imul(h ^ (h >>> 13), 3266489909);
        return (h ^= h >>> 16) >>> 0;
    };
}

function mulberry32(a) {
    return function () {
        a |= 0;
        a = (a + 0x6D2B79F5) | 0;
        let t = Math.imul(a ^ (a >>> 15), 1 | a);
        t = (t + Math.imul(t ^ (t >>> 7), 61 | t)) ^ t;
        return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
    };
}

function makeRng(seedString) {
    return mulberry32(hashSeed(String(seedString))());
}

const SEED_WORDS = ["ash", "bramble", "cinder", "drift", "ember", "fawn", "glass", "hollow", "ivy", "knot",
    "lull", "moth", "nettle", "opal", "pale", "quiet", "rust", "salt", "thorn", "umber", "veil", "wick", "yarrow", "zephyr"];

function generateRandomSeed() {
    const pick = () => SEED_WORDS[Math.floor(Math.random() * SEED_WORDS.length)];
    return `${pick()}-${pick()}-${Math.floor(Math.random() * 900 + 100)}`;
}

const NG_PLUS_KEY = 'uct_extended_unlocked';

function isNgPlusUnlocked() {
    try {
        return localStorage.getItem(NG_PLUS_KEY) === '1';
    } catch (e) {
        return false;
    }
}

function unlockNgPlus() {
    try {
        localStorage.setItem(NG_PLUS_KEY, '1');
    } catch (e) {
    }
}

const ARCADE_HIGHSCORE_KEY = 'uct_arcade_highscore';

function getArcadeHighScore() {
    try {
        return parseInt(localStorage.getItem(ARCADE_HIGHSCORE_KEY), 10) || 0;
    } catch (e) {
        return 0;
    }
}

function setArcadeHighScore(score) {
    try {
        localStorage.setItem(ARCADE_HIGHSCORE_KEY, String(score));
    } catch (e) {
    }
}

const elRepressionBar = document.getElementById('bar-repression');
const elRepressionVal = document.getElementById('val-repression');
const elRepressionLabel = document.getElementById('label-repression');
const elMaskBar = document.getElementById('bar-mask');
const elMaskVal = document.getElementById('val-mask');
const elMaskLabel = document.getElementById('label-mask');
const elChildBar = document.getElementById('bar-child');
const elChildVal = document.getElementById('val-child');
const elChildLabel = document.getElementById('label-child');
const elEndRepressionLabel = document.getElementById('end-label-repression');
const elEndMaskLabel = document.getElementById('end-label-mask');
const elEndChildLabel = document.getElementById('end-label-child');

const DEFAULT_STAT_LABELS = {repression: "Repression Level", mask: "Social Mask", child: "Inner Child"};

function statLabels() {
    return Object.assign({}, DEFAULT_STAT_LABELS, getContent().config.statLabels || {});
}

const DEFAULT_FAILURE_ENDINGS = DEFAULT_CONTENT.failureEndings;

function normalizeFailureEndingPool(value, fallback) {
    if (Array.isArray(value) && value.length) return value;
    if (value && typeof value === 'object' && value.title) return [value];
    return fallback;
}

function failureEndings() {
    const custom = getContent().failureEndings || {};
    return {
        repression: normalizeFailureEndingPool(custom.repression, DEFAULT_FAILURE_ENDINGS.repression),
        mask: normalizeFailureEndingPool(custom.mask, DEFAULT_FAILURE_ENDINGS.mask),
        child: normalizeFailureEndingPool(custom.child, DEFAULT_FAILURE_ENDINGS.child)
    };
}

function pickFailureEnding(pool) {
    return pool[Math.floor(state.rng() * pool.length)];
}

const elEventDisplay = document.getElementById('event-display');
const elChoicesContainer = document.getElementById('choices-container');
const elTurnCounter = document.getElementById('turn-counter');
const elActionLog = document.getElementById('action-log');

const elMechanismsPanel = document.getElementById('mechanisms-panel');
const elMechanismsList = document.getElementById('mechanisms-list');
const elMechanismsDesc = document.getElementById('mechanisms-desc');

const elEndScreen = document.getElementById('end-screen');
const elEndCardTitle = document.getElementById('end-card-title');
const elEndCardPlayedBy = document.getElementById('end-card-played-by');
const elEndTitle = document.getElementById('end-title');
const elEndDesc = document.getElementById('end-desc');
const elEndNgPlusBtn = document.getElementById('end-ngplus-btn');
const elEndReplayBtn = document.getElementById('end-replay-btn');
const elEndSeedTag = document.getElementById('end-seed-tag');
const elEndRepressionBar = document.getElementById('end-bar-repression');
const elEndRepressionVal = document.getElementById('end-val-repression');
const elEndMaskBar = document.getElementById('end-bar-mask');
const elEndMaskVal = document.getElementById('end-val-mask');
const elEndChildBar = document.getElementById('end-bar-child');
const elEndChildVal = document.getElementById('end-val-child');
const elEndShareStatus = document.getElementById('end-share-status');
const elEndShareText = document.getElementById('end-share-text');
const elSharePanel = document.getElementById('share-panel');
const elShareCanvasContainer = document.getElementById('share-canvas-container');
let shareStatusTimeout = null;

function copySeed() {
    if (!state.seed || !navigator.clipboard) return;
    navigator.clipboard.writeText(state.seed).catch(() => {
    });
}

function showShareStatus(msg) {
    elEndShareStatus.textContent = msg;
    clearTimeout(shareStatusTimeout);
    shareStatusTimeout = setTimeout(() => {
        elEndShareStatus.textContent = '';
    }, 4000);
}

function buildShareText() {
    const content = getContent();
    const labels = statLabels();
    const barify = val => {
        const filled = Math.max(0, Math.min(10, Math.round(val / 10)));
        return '█'.repeat(filled) + '░'.repeat(10 - filled);
    };
    const modeTag = state.arcade ? " (Arcade)" : state.hardMode ? " (Extended Therapy)" : "";
    const titleLine = playerName
        ? `${playerName}'s ${elGameTitle.textContent} — ${elEndTitle.textContent}`
        : `${elGameTitle.textContent} — ${elEndTitle.textContent}`;
    const footerLine = state.arcade
        ? `Turns Survived: ${state.arcadeScore}${state.arcadeIsNewBest ? " — New Best!" : ` · Best: ${getArcadeHighScore()}`}${modeTag}`
        : `Seed: ${state.seed} · Turn ${Math.min(state.turn, state.maxTurns)}/${state.maxTurns}${modeTag}`;
    const lines = [
        titleLine,
        footerLine,
        `${labels.repression}  ${barify(state.repression)}  ${state.repression}%`,
        `${labels.mask}  ${barify(state.mask)}  ${state.mask}%`,
        `${labels.child}  ${barify(state.child)}  ${state.child}%`
    ];
    const unlockedNames = Object.entries(mechanismState)
        .filter(([, s]) => s.unlocked)
        .map(([tag]) => content.mechanisms[tag] ? content.mechanisms[tag].name : tag);
    if (unlockedNames.length) {
        lines.push(`Coping Mechanisms: ${unlockedNames.join(', ')}`);
    }
    lines.push('aedmark.itch.io/ucts');
    return lines.join('\n');
}

async function copyResultText() {
    const text = buildShareText();
    elEndShareText.classList.add('hidden');
    if (navigator.clipboard && navigator.clipboard.writeText) {
        try {
            await navigator.clipboard.writeText(text);
            showShareStatus('Copied to clipboard.');
            return;
        } catch (e) {
        }
    }
    elEndShareText.value = text;
    elEndShareText.classList.remove('hidden');
    elEndShareText.select();
    showShareStatus('Select and copy the text below.');
}

const CANVAS_COLORS = {
    case: '#e6dabd',
    caseDeep: '#d3c39c',
    ink: '#2c2013',
    inkSoft: '#6b5a3f',
    keyRed: '#e3543f',
    screen: '#1a0e08',
    screenEdge: '#0a0503',
    screenBody: '#c9b990',
    ledRed: '#ff5240',
    ledAmber: '#ffb100',
    ledTeal: '#2fe0c4'
};

function roundedRectPath(ctx, x, y, w, h, r) {
    ctx.beginPath();
    ctx.moveTo(x + r, y);
    ctx.arcTo(x + w, y, x + w, y + h, r);
    ctx.arcTo(x + w, y + h, x, y + h, r);
    ctx.arcTo(x, y + h, x, y, r);
    ctx.arcTo(x, y, x + w, y, r);
    ctx.closePath();
}

function layoutWrappedLines(ctx, text, maxWidth) {
    const words = text.split(' ');
    const lines = [];
    let line = '';
    words.forEach(word => {
        const test = line ? `${line} ${word}` : word;
        if (line && ctx.measureText(test).width > maxWidth) {
            lines.push(line);
            line = word;
        } else {
            line = test;
        }
    });
    if (line) lines.push(line);
    return lines;
}

function drawWrappedLines(ctx, lines, x, y, lineHeight) {
    lines.forEach(line => {
        ctx.fillText(line, x, y);
        y += lineHeight;
    });
    return y;
}

function drawStatBar(ctx, x, y, w, h, pct, color) {
    ctx.fillStyle = CANVAS_COLORS.screenEdge;
    ctx.fillRect(x, y, w, h);
    ctx.fillStyle = color;
    ctx.fillRect(x, y, (w * Math.max(0, Math.min(100, pct))) / 100, h);
    ctx.strokeStyle = '#000';
    ctx.lineWidth = 2;
    ctx.strokeRect(x, y, w, h);
}

async function renderResultCanvas() {
    await document.fonts.ready;
    const content = getContent();
    const labels = statLabels();
    const win = elEndTitle.classList.contains('win');
    const W = 1080, H = 1350;

    const canvas = document.createElement('canvas');
    canvas.width = W;
    canvas.height = H;
    const ctx = canvas.getContext('2d');

    roundedRectPath(ctx, 0, 0, W, H, 48);
    const caseGrad = ctx.createLinearGradient(0, 0, 0, H);
    caseGrad.addColorStop(0, CANVAS_COLORS.case);
    caseGrad.addColorStop(1, CANVAS_COLORS.caseDeep);
    ctx.fillStyle = caseGrad;
    ctx.fill();
    ctx.save();
    roundedRectPath(ctx, 0, 0, W, H, 48);
    ctx.clip();
    ctx.translate(-20, 50);
    ctx.rotate(-Math.PI / 4);
    const stripeColors = ['#e3543f', '#eda123', '#f0d048', '#2f9e8f', '#4a72c9'];
    const stripeH = 22;
    stripeColors.forEach((c, i) => {
        ctx.fillStyle = c;
        ctx.fillRect(-300, i * stripeH, 1200, stripeH + 1);
    });
    ctx.restore();

    ctx.lineWidth = 22;
    ctx.strokeStyle = CANVAS_COLORS.ink;
    roundedRectPath(ctx, 0, 0, W, H, 48);
    ctx.stroke();

    ctx.textAlign = 'center';
    ctx.fillStyle = CANVAS_COLORS.keyRed;
    ctx.font = '52px "Press Start 2P", monospace';
    ctx.fillText(elGameTitle.textContent, W / 2, 150);

    let screenTop = 195;
    if (playerName) {
        ctx.fillStyle = CANVAS_COLORS.inkSoft;
        ctx.font = '26px "Space Mono", monospace';
        ctx.fillText(`Played by ${playerName}`, W / 2, 195);
        screenTop = 230;
    }

    const screenX = 66, footerBlockH = 150;
    const screenW = W - screenX * 2;
    const screenBottom = H - 40 - footerBlockH;
    const screenH = screenBottom - screenTop;
    roundedRectPath(ctx, screenX, screenTop, screenW, screenH, 22);
    ctx.fillStyle = CANVAS_COLORS.screen;
    ctx.fill();
    ctx.lineWidth = 5;
    ctx.strokeStyle = CANVAS_COLORS.ink;
    ctx.stroke();

    const innerPad = 50;
    const contentX = screenX + innerPad;
    const contentW = screenW - innerPad * 2;

    const endingFont = '34px "Press Start 2P", monospace';
    const descFont = '24px "Space Mono", monospace';
    const mechHeaderFont = '24px "Space Mono", monospace';
    const mechBodyFont = 'italic 24px "Space Mono", monospace';
    const endingLineHeight = 44, descLineHeight = 32, statRowHeight = 22 + 46 + 58, mechLineHeight = 30;

    ctx.font = endingFont;
    const endingLines = layoutWrappedLines(ctx, elEndTitle.textContent.toUpperCase(), contentW);

    ctx.font = descFont;
    const descLines = elEndDesc.textContent ? layoutWrappedLines(ctx, elEndDesc.textContent, contentW) : [];

    const unlockedNames = Object.entries(mechanismState)
        .filter(([, s]) => s.unlocked)
        .map(([tag]) => content.mechanisms[tag] ? content.mechanisms[tag].name : tag);
    ctx.font = mechBodyFont;
    const mechLines = unlockedNames.length ? layoutWrappedLines(ctx, unlockedNames.join(', '), contentW) : [];

    const blockHeight = endingLines.length * endingLineHeight + 26
        + (descLines.length ? descLines.length * descLineHeight + 26 : 0)
        + 50 + statRowHeight * 3
        + (mechLines.length ? 30 + mechLines.length * mechLineHeight + 16 : 0);
    const blockTop = screenTop + 40, blockBottom = screenBottom - 40;
    let y = blockTop + Math.max(0, (blockBottom - blockTop - blockHeight) / 2);

    ctx.fillStyle = win ? CANVAS_COLORS.ledTeal : CANVAS_COLORS.ledRed;
    ctx.font = endingFont;
    y = drawWrappedLines(ctx, endingLines, W / 2, y, endingLineHeight) + 26;

    if (descLines.length) {
        ctx.fillStyle = CANVAS_COLORS.screenBody;
        ctx.font = descFont;
        y = drawWrappedLines(ctx, descLines, W / 2, y, descLineHeight) + 26;
    }

    ctx.strokeStyle = CANVAS_COLORS.screenEdge;
    ctx.lineWidth = 4;
    ctx.beginPath();
    ctx.moveTo(contentX, y);
    ctx.lineTo(contentX + contentW, y);
    ctx.stroke();
    y += 50;

    const statRows = [
        {label: labels.repression, val: state.repression, color: CANVAS_COLORS.ledRed},
        {label: labels.mask, val: state.mask, color: CANVAS_COLORS.ledAmber},
        {label: labels.child, val: state.child, color: CANVAS_COLORS.ledTeal}
    ];
    statRows.forEach(row => {
        ctx.font = '30px "Space Mono", monospace';
        ctx.fillStyle = row.color;
        ctx.textAlign = 'left';
        ctx.fillText(row.label.toUpperCase(), contentX, y);
        ctx.textAlign = 'right';
        ctx.fillText(`${row.val}%`, contentX + contentW, y);
        y += 22;
        drawStatBar(ctx, contentX, y, contentW, 46, row.val, row.color);
        y += 46 + 58;
    });

    if (mechLines.length) {
        ctx.textAlign = 'left';
        ctx.fillStyle = CANVAS_COLORS.ledTeal;
        ctx.font = mechHeaderFont;
        ctx.fillText('COPING MECHANISMS ACQUIRED:', contentX, y);
        y += 30;
        ctx.font = mechBodyFont;
        drawWrappedLines(ctx, mechLines, contentX, y, mechLineHeight);
    }

    const modeTag = state.arcade ? ' (Arcade)' : state.hardMode ? ' (Extended Therapy)' : '';
    const footerLine = state.arcade
        ? `Turns Survived: ${state.arcadeScore}${state.arcadeIsNewBest ? ' — New Best!' : `  ·  Best: ${getArcadeHighScore()}`}${modeTag}`
        : `Seed: ${state.seed}  ·  Turn ${Math.min(state.turn, state.maxTurns)}/${state.maxTurns}${modeTag}`;
    ctx.textAlign = 'center';
    ctx.fillStyle = CANVAS_COLORS.inkSoft;
    ctx.font = '24px "Space Mono", monospace';
    ctx.fillText(footerLine, W / 2, H - 95);

    ctx.fillStyle = CANVAS_COLORS.ink;
    ctx.font = '22px "Space Mono", monospace';
    ctx.fillText('aedmark.itch.io/ucts', W / 2, H - 55);

    return canvas;
}

async function toggleSharePanel() {
    const opening = elSharePanel.classList.contains('hidden');
    if (!opening) {
        elSharePanel.classList.add('hidden');
        return;
    }
    elSharePanel.classList.remove('hidden');
    elShareCanvasContainer.innerHTML = '<p class="share-note">Rendering result card…</p>';
    const canvas = await renderResultCanvas();
    elShareCanvasContainer.innerHTML = '';
    elShareCanvasContainer.appendChild(canvas);
}

function downloadCanvasBlob(blob, filename) {
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;
    document.body.appendChild(a);
    a.click();
    a.remove();
    setTimeout(() => URL.revokeObjectURL(url), 5000);
}

async function shareResultImage() {
    const canvas = await renderResultCanvas();
    const blob = await new Promise(resolve => canvas.toBlob(resolve, 'image/png'));
    if (!blob) return;
    const filename = `ucts-result-${state.seed}.png`;
    const file = new File([blob], filename, {type: 'image/png'});

    if (navigator.canShare && navigator.canShare({files: [file]})) {
        try {
            await navigator.share({files: [file], text: buildShareText()});
            return;
        } catch (e) {
        }
    }
    downloadCanvasBlob(blob, filename);
    showShareStatus('Image downloaded.');
}

const elGameTitle = document.getElementById('game-title');
const elObjectiveText = document.getElementById('objective-text');
const elNgPlusBtn = document.getElementById('ng-plus-btn');

function logAction(msg) {
    const entry = document.createElement('div');
    entry.textContent = `> Turn ${state.turn}: ${msg}`;
    elActionLog.appendChild(entry);
    elActionLog.scrollTop = elActionLog.scrollHeight;
}

function resetMechanismState() {
    mechanismState = {};
    Object.keys(getContent().mechanisms).forEach(tag => {
        mechanismState[tag] = {unlocked: false, count: 0};
    });
}

function applyMechanismModifiers(tag, effects) {
    const content = getContent();
    const mech = content.mechanisms[tag];
    const s = mechanismState[tag];
    const e = {rep: effects.rep || 0, mask: effects.mask || 0, child: effects.child || 0};
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

function showMechanismDesc(tag, badge) {
    const content = getContent();
    const mech = content.mechanisms[tag];
    const alreadyOpen = badge.classList.contains('active');
    elMechanismsList.querySelectorAll('.mechanism-badge').forEach(b => b.classList.remove('active'));
    if (alreadyOpen || !mech || !mech.desc) {
        elMechanismsDesc.classList.add('hidden');
        elMechanismsDesc.textContent = '';
        return;
    }
    badge.classList.add('active');
    elMechanismsDesc.textContent = `${mech.name}: ${mech.desc}`;
    elMechanismsDesc.classList.remove('hidden');
}

function renderMechanisms(hasFresh) {
    const content = getContent();
    const unlockedTags = Object.entries(mechanismState).filter(([, s]) => s.unlocked).map(([tag]) => tag);
    elMechanismsDesc.classList.add('hidden');
    elMechanismsDesc.textContent = '';
    if (unlockedTags.length === 0) {
        elMechanismsPanel.classList.add('hidden');
        return;
    }
    elMechanismsPanel.classList.remove('hidden');
    elMechanismsList.innerHTML = '';
    unlockedTags.forEach((tag, i) => {
        const mech = content.mechanisms[tag];
        const badge = document.createElement('button');
        badge.type = 'button';
        const isNewest = hasFresh && i === unlockedTags.length - 1;
        badge.className = "mechanism-badge" + (isNewest ? " fresh" : "");
        badge.textContent = mech ? mech.name : tag;
        if (mech && mech.desc) badge.title = mech.desc;
        badge.onclick = () => showMechanismDesc(tag, badge);
        elMechanismsList.appendChild(badge);
    });
}

function setStatBarVisual(barEl, valEl, value, colorName, isHot) {
    barEl.style.width = `${value}%`;
    if (valEl) valEl.textContent = `${value}%`;
    barEl.className = `stat-bar-fill ${colorName}-${isHot ? 'hot' : 'dim'}`;
}

function updateUI() {
    state.repression = Math.max(0, Math.min(100, state.repression));
    state.mask = Math.max(0, Math.min(100, state.mask));
    state.child = Math.max(0, Math.min(100, state.child));

    setStatBarVisual(elRepressionBar, elRepressionVal, state.repression, 'red', state.repression > 80);
    setStatBarVisual(elMaskBar, elMaskVal, state.mask, 'blue', state.mask < 30);
    setStatBarVisual(elChildBar, elChildVal, state.child, 'pink', state.child < 30);

    elTurnCounter.textContent = state.arcade
        ? `Turn: ${state.turn} · Best: ${getArcadeHighScore()}`
        : `Turn: ${Math.min(state.turn, state.maxTurns)}/${state.maxTurns}`;
}

function evalCondition(cond, stats) {
    const v = stats[cond.stat];
    if (typeof v !== 'number') return false;
    switch (cond.op) {
        case '>=':
            return v >= cond.value;
        case '<=':
            return v <= cond.value;
        case '>':
            return v > cond.value;
        case '<':
            return v < cond.value;
        case '==':
            return v === cond.value;
        default:
            return false;
    }
}

function normalizeEndingVariants(ending) {
    if (Array.isArray(ending.variants) && ending.variants.length) return ending.variants;
    if (ending.title) return [{title: ending.title, desc: ending.desc}];
    return [{title: "Functional Enough", desc: "You made it to tomorrow."}];
}

function getSurvivalEnding() {
    const content = getContent();
    const stats = {repression: state.repression, mask: state.mask, child: state.child};
    const match = content.endings.find(e => (e.conditions || []).every(c => evalCondition(c, stats)))
        || content.endings[content.endings.length - 1];

    const variants = normalizeEndingVariants(match);
    const picked = variants[Math.floor(state.rng() * variants.length)];

    let desc = picked.desc;
    const unlockedNames = Object.entries(mechanismState)
        .filter(([, s]) => s.unlocked)
        .map(([tag]) => content.mechanisms[tag] ? content.mechanisms[tag].name : tag);
    if (unlockedNames.length) {
        desc += ` Coping mechanisms acquired: ${unlockedNames.join(', ')}.`;
    }
    return {title: picked.title, desc};
}

function checkGameEnd() {
    const fe = failureEndings();
    if (state.repression >= 100) {
        const e = pickFailureEnding(fe.repression);
        endGame(e.title, e.desc);
        return true;
    }
    if (state.mask <= 0) {
        const e = pickFailureEnding(fe.mask);
        endGame(e.title, e.desc);
        return true;
    }
    if (state.child <= 0) {
        const e = pickFailureEnding(fe.child);
        endGame(e.title, e.desc);
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
    clearTimedChoice();
    state.isGameOver = true;
    elEndScreen.classList.remove('hidden');
    elEndTitle.textContent = title;
    elEndTitle.className = "overlay-heading end " + (win ? "win" : "loss");
    elEndDesc.textContent = desc;

    elEndCardTitle.textContent = elGameTitle.textContent;
    elEndCardPlayedBy.textContent = playerName ? `Played by ${playerName}` : '';
    elEndCardPlayedBy.classList.toggle('hidden', !playerName);

    setStatBarVisual(elEndRepressionBar, elEndRepressionVal, state.repression, 'red', state.repression > 80);
    setStatBarVisual(elEndMaskBar, elEndMaskVal, state.mask, 'blue', state.mask < 30);
    setStatBarVisual(elEndChildBar, elEndChildVal, state.child, 'pink', state.child < 30);

    if (state.arcade) {
        state.arcadeScore = state.turn - 1;
        state.arcadeIsNewBest = state.arcadeScore > getArcadeHighScore();
        if (state.arcadeIsNewBest) setArcadeHighScore(state.arcadeScore);
        elEndSeedTag.textContent = state.arcadeIsNewBest
            ? `New High Score: ${state.arcadeScore} turns!`
            : `Turns Survived: ${state.arcadeScore} · Best: ${getArcadeHighScore()}`;
    } else {
        elEndSeedTag.textContent = `Seed: ${state.seed}`;
    }
    elEndReplayBtn.classList.toggle('hidden', state.arcade);
    elEndNgPlusBtn.classList.toggle('hidden', state.hardMode || state.arcade || !isNgPlusUnlocked());
    elEndShareStatus.textContent = '';
    elEndShareText.classList.add('hidden');
    elEndShareText.value = '';
    elSharePanel.classList.add('hidden');
    elShareCanvasContainer.innerHTML = '';
}

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
    if (state.arcade) {
        const step = content.config.hardModeMultiplier;
        const tier = Math.floor((state.turn - 1) / 10);
        const mult = 1 + tier * (step - 1);
        effects = {
            rep: Math.round((effects.rep || 0) * mult),
            mask: Math.round((effects.mask || 0) * mult),
            child: Math.round((effects.child || 0) * mult)
        };
    } else if (state.hardMode) {
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
        rep: Math.floor(state.rng() * 51) - 25,
        mask: Math.floor(state.rng() * 51) - 25,
        child: Math.floor(state.rng() * 51) - 25
    };
    let log;
    if (evt && evt.glitch && evt.glitch.log) {
        log = evt.glitch.log;
    } else {
        const logs = content.glitchLogs.length ? content.glitchLogs : ["Something happened."];
        log = logs[Math.floor(state.rng() * logs.length)];
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

    let r = state.rng() * total;
    for (let i = 0; i < pool.length; i++) {
        r -= weights[i];
        if (r <= 0) return pool[i];
    }
    return pool[pool.length - 1];
}

function timedConfigFor(evt, content) {
    if (evt.timed === false) return null;
    const chance = typeof content.config.timedEventChance === 'number' ? content.config.timedEventChance : 0.2;
    const baseDuration = typeof content.config.timedDuration === 'number' ? content.config.timedDuration : 8000;
    const duration = (evt.timed && typeof evt.timed.duration === 'number') ? evt.timed.duration : baseDuration;
    return {chance, duration};
}

function clearTimedChoice() {
    if (timerInterval) {
        clearInterval(timerInterval);
        timerInterval = null;
    }
}

function resolveTimedOut(evt) {
    if (state.isGameOver) return;
    const choices = evt.choices || [];
    if (!choices.length) return;
    const pick = choices[Math.floor(state.rng() * choices.length)];
    handleChoice(pick.effects || {}, `[FROZE] ${pick.log || pick.text || "..."}`, 'freeze');
}

function startTimedChoice(evt, duration) {
    const bar = document.createElement('div');
    bar.className = "timer-bar-container";
    bar.innerHTML = '<div id="event-timer-fill" class="timer-bar-fill"></div>';
    elEventDisplay.appendChild(bar);
    const fill = document.getElementById('event-timer-fill');
    const deadline = performance.now() + duration;

    const tick = () => {
        const remaining = deadline - performance.now();
        fill.style.width = `${Math.max(0, Math.min(100, (remaining / duration) * 100))}%`;
        fill.classList.toggle('timer-critical', remaining < duration * 0.25);
        if (remaining <= 0) {
            clearTimedChoice();
            resolveTimedOut(evt);
        }
    };
    tick();
    timerInterval = setInterval(tick, 100);
}

function loadRandomEvent() {
    clearTimedChoice();
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

    (evt.choices || []).forEach(choice => {
        const fx = choice.effects || {};
        const btn = document.createElement('button');
        btn.className = "choice-btn";

        const textSpan = document.createElement('span');
        textSpan.textContent = choice.text || "...";
        btn.appendChild(textSpan);

        btn.onclick = () => {
            clearTimedChoice();
            handleChoice(fx, choice.log, choice.tag || null);
        };
        elChoicesContainer.appendChild(btn);
    });

    if (state.rng() < content.config.glitchChance) {
        const glitchBtn = document.createElement('button');
        glitchBtn.className = "choice-btn glitch";
        const glitchTextSpan = document.createElement('span');
        glitchTextSpan.textContent = (evt.glitch && evt.glitch.text) || "??? Do something you can't predict.";
        glitchBtn.appendChild(glitchTextSpan);
        glitchBtn.onclick = () => {
            clearTimedChoice();
            handleGlitchChoice(evt);
        };
        elChoicesContainer.appendChild(glitchBtn);
    }

    const timedCfg = timedEnabled ? timedConfigFor(evt, content) : null;
    if (timedCfg && (evt.choices || []).length && state.rng() < timedCfg.chance) {
        startTimedChoice(evt, timedCfg.duration);
    }
}

const DEFAULT_ARCADE_STAT_RANGES = DEFAULT_CONTENT.config.arcadeStartingStats;

function rollArcadeStartingStats(rng, cfg) {
    const ranges = Object.assign({}, DEFAULT_ARCADE_STAT_RANGES, cfg.arcadeStartingStats);
    const roll = key => {
        const r = Object.assign({}, DEFAULT_ARCADE_STAT_RANGES[key], ranges[key]);
        return Math.round(r.min + rng() * (r.max - r.min));
    };
    return {repression: roll('repression'), mask: roll('mask'), child: roll('child')};
}

function startGame(hard = false, seedOverride = null, arcade = false) {
    clearTimedChoice();
    if (arcade) hard = false;
    const content = getContent();
    const cfg = content.config;
    const seed = (!arcade && seedOverride && String(seedOverride).trim())
        ? String(seedOverride).trim() : generateRandomSeed();
    const rng = makeRng(seed);
    const startingStats = arcade ? rollArcadeStartingStats(rng, cfg) : cfg.startingStats;
    state = {
        repression: startingStats.repression,
        mask: startingStats.mask,
        child: startingStats.child,
        turn: 1,
        maxTurns: hard ? cfg.hardModeTurns : (arcade ? Infinity : cfg.maxTurns),
        isGameOver: false,
        hardMode: hard,
        arcade: arcade,
        seed: seed,
        rng: rng
    };
    resetMechanismState();
    lastEventTitle = null;
    seenEventTitles = new Set();
    elEndScreen.classList.add('hidden');

    const labels = statLabels();
    elRepressionLabel.textContent = labels.repression;
    elMaskLabel.textContent = labels.mask;
    elChildLabel.textContent = labels.child;
    elEndRepressionLabel.textContent = labels.repression;
    elEndMaskLabel.textContent = labels.mask;
    elEndChildLabel.textContent = labels.child;

    elGameTitle.textContent = arcade ? "U.C.T.S :: ARCADE" : hard ? "U.C.T.S :: EXTENDED THERAPY" : "U.C.T.S";
    elObjectiveText.textContent = arcade
        ? `Objective: Survive as long as you can. Best: ${getArcadeHighScore()} turns.`
        : `Objective: Survive ${state.maxTurns} Turns`;
    elNgPlusBtn.classList.toggle('hidden', hard || arcade || !isNgPlusUnlocked());

    elActionLog.innerHTML = arcade
        ? '<div>> Arcade mode engaged. No ceiling. Escalation every ten turns.</div>'
        : hard
            ? '<div>> Extended session initiated. Your nervous system has been here before.</div>'
            : '<div>> Therapy session restarted. Commencing psychological baseline.</div>';
    renderMechanisms(false);
    updateUI();
    loadRandomEvent();
}
