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
const elSplashNameInput = document.getElementById('splash-name-input');

const PLAYER_NAME_KEY = 'uct_player_name';

function getStoredPlayerName() {
    try {
        return localStorage.getItem(PLAYER_NAME_KEY) || '';
    } catch (e) {
        return '';
    }
}

function setStoredPlayerName(name) {
    try {
        localStorage.setItem(PLAYER_NAME_KEY, name);
    } catch (e) {
    }
}

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
    } catch (e) {
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

const GAME_DATA_KEYS = [CONTENT_KEY, CODEX_KEY, NG_PLUS_KEY, ARCADE_HIGHSCORE_KEY, PLAYER_NAME_KEY, TIMED_PREF_KEY];

function resetAllGameData() {
    const confirmed = window.confirm(
        "Reset all simulation data?\n\n" +
        "This permanently clears your custom content pack, Case Files archive, Extended Therapy unlock, " +
        "Arcade high score, player name, and Timed Events preference. This cannot be undone."
    );
    if (!confirmed) return;

    GAME_DATA_KEYS.forEach(key => {
        try {
            localStorage.removeItem(key);
        } catch (e) {
        }
    });

    const alsoFieldLog = window.confirm(
        "Also permanently delete your Field Log?\n\n" +
        "That's your separate, real journal — actual entries about actual moments in your actual life, not part " +
        "of the simulation above. This is a second, distinct action from the reset you just confirmed, and it " +
        "cannot be undone either. Choose Cancel to keep your Field Log exactly as it is."
    );
    if (alsoFieldLog) {
        try {
            localStorage.removeItem(FIELD_LOG_KEY);
        } catch (e) {
        }
    }

    location.reload();
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
    elSplashNameInput.value = getStoredPlayerName();
    elSplashScreen.classList.remove('hidden');
}

function dismissSplash(arcade = false) {
    elSplashScreen.classList.add('hidden');
    timedEnabled = elSplashTimedToggle.checked;
    setTimedPref(timedEnabled);
    playerName = elSplashNameInput.value.trim().slice(0, 24);
    setStoredPlayerName(playerName);
    startGame(false, elSplashSeedInput.value, arcade);
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

window.onload = () => {
    renderFieldPickers();
    showSplash();
};
