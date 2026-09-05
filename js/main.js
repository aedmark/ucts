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

function toggleHelp(show) {
    elHelpScreen.classList.toggle('hidden', !show);
}

// Shown once on load, covering the whole app until dismissed — the actual
// run (startGame) doesn't begin until then. Falls back to generic text so
// packs saved before this field existed still get a title screen rather
// than none at all.
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

    elSplashScreen.classList.remove('hidden');
}

function dismissSplash() {
    elSplashScreen.classList.add('hidden');
    startGame(false);
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
