// ============================================================
// MODE SWITCHING + BOOT — index.html only.
// Depends on content.js, engine.js, field-log.js.
// ============================================================
const elTabSim = document.getElementById('tab-sim');
const elTabField = document.getElementById('tab-field');
const elSimView = document.getElementById('sim-view');
const elFieldView = document.getElementById('field-view');
const elHelpScreen = document.getElementById('help-screen');
const elSplashScreen = document.getElementById('splash-screen');
const elSplashTitle = document.getElementById('splash-title');
const elSplashIntro = document.getElementById('splash-intro');
const elSplashTimedToggle = document.getElementById('splash-timed-toggle');
const elSplashSeedInput = document.getElementById('splash-seed-input');

const TIMED_PREF_KEY = 'uct_timed_pref';

function getTimedPref() {
    try {
        return localStorage.getItem(TIMED_PREF_KEY) === '1';
    } catch (e) {
        return false;
    }
}

function setTimedPref(v) {
    try {
        localStorage.setItem(TIMED_PREF_KEY, v ? '1' : '0');
    } catch (e) { /* storage unavailable, preference just won't persist */
    }
}

function todaySeed() {
    const d = new Date();
    const pad = n => String(n).padStart(2, '0');
    return `daily-${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())}`;
}

function randomizeSeed() {
    elSplashSeedInput.value = generateRandomSeed();
}

function useDailySeed() {
    elSplashSeedInput.value = todaySeed();
}

function toggleHelp(show) {
    elHelpScreen.classList.toggle('hidden', !show);
}

function showSplash() {
    const content = getContent();
    const splash = (content.config && content.config.splash) || {};
    elSplashTitle.textContent = splash.title || elGameTitle.textContent || "U.C.T. Simulator";

    elSplashIntro.innerHTML = '';
    const intro = splash.intro || "Press Start when you're ready.";
    intro.split('\n\n').forEach(para => {
        if (!para.trim()) return;
        const p = document.createElement('p');
        p.textContent = para;
        elSplashIntro.appendChild(p);
    });

    elSplashTimedToggle.checked = getTimedPref();
    elSplashSeedInput.value = '';
    elSplashScreen.classList.remove('hidden');
}

function dismissSplash() {
    elSplashScreen.classList.add('hidden');
    timedEnabled = elSplashTimedToggle.checked;
    setTimedPref(timedEnabled);
    startGame(false, elSplashSeedInput.value);
}

function setMode(mode) {
    const views = {sim: elSimView, field: elFieldView};
    const tabs = {sim: elTabSim, field: elTabField};

    Object.keys(views).forEach(key => {
        views[key].classList.toggle('hidden', key !== mode);
        tabs[key].classList.toggle('active', key === mode);
    });

    if (mode === 'field') {
        renderFieldEntries();
        renderPattern();
    }
}

// Boot
window.onload = () => {
    renderFieldPickers();
    showSplash();
};
