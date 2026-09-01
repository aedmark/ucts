// ============================================================
// MODE SWITCHING + BOOT — index.html only.
// Depends on content.js, engine.js, field-log.js.
// ============================================================
const elTabSim = document.getElementById('tab-sim');
const elTabField = document.getElementById('tab-field');
const elSimView = document.getElementById('sim-view');
const elFieldView = document.getElementById('field-view');

function setMode(mode) {
    const views = { sim: elSimView, field: elFieldView };
    const tabs = { sim: elTabSim, field: elTabField };

    Object.keys(views).forEach(key => {
        views[key].classList.toggle('hidden', key !== mode);
        tabs[key].classList.toggle('bg-[#1a1a1a]', key === mode);
        tabs[key].classList.toggle('text-white', key === mode);
        tabs[key].classList.toggle('text-gray-500', key !== mode);
    });

    if (mode === 'field') {
        renderFieldEntries();
        renderPattern();
    }
}

// Boot
window.onload = () => {
    renderFieldPickers();
    startGame(false);
};
