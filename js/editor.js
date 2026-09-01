// ============================================================
// CONTENT EDITOR — editor.html only. Depends on content.js only.
// ============================================================
const elEditorBody = document.getElementById('editor-body');
const elEditorStatus = document.getElementById('editor-status');
const elPlayPackLink = document.getElementById('play-pack-link');

let editorDraft = null;

function ensureEditorDraft() {
    if (!editorDraft) editorDraft = deepClone(getContent());
}

function labeledWrap(labelText, el) {
    const box = document.createElement('div');
    const label = document.createElement('label');
    label.className = "editor-label";
    label.textContent = labelText;
    box.appendChild(label);
    box.appendChild(el);
    return box;
}

function renderEditor() {
    elEditorBody.innerHTML = '';
    elEditorBody.appendChild(buildConfigSection());
    elEditorBody.appendChild(buildZonesSection());
    elEditorBody.appendChild(buildMechanismsSection());
    elEditorBody.appendChild(buildEndingsSection());
    elEditorBody.appendChild(buildEventsSection());
}

function buildConfigSection() {
    const wrap = document.createElement('div');
    wrap.className = "editor-section";
    const title = document.createElement('h3');
    title.className = "text-xs uppercase tracking-widest text-white";
    title.textContent = "Config";
    wrap.appendChild(title);

    const grid = document.createElement('div');
    grid.className = "grid grid-cols-2 md:grid-cols-3 gap-3";
    wrap.appendChild(grid);

    const cfg = editorDraft.config;
    const fields = [
        { label: "Starting Repression", get: () => cfg.startingStats.repression, set: v => cfg.startingStats.repression = v },
        { label: "Starting Mask", get: () => cfg.startingStats.mask, set: v => cfg.startingStats.mask = v },
        { label: "Starting Inner Child", get: () => cfg.startingStats.child, set: v => cfg.startingStats.child = v },
        { label: "Base Max Turns", get: () => cfg.maxTurns, set: v => cfg.maxTurns = v },
        { label: "Extended Therapy Turns", get: () => cfg.hardModeTurns, set: v => cfg.hardModeTurns = v },
        { label: "Extended Therapy Multiplier", get: () => cfg.hardModeMultiplier, set: v => cfg.hardModeMultiplier = v, step: 0.05 },
        { label: "Mechanism Unlock Threshold", get: () => cfg.unlockThreshold, set: v => cfg.unlockThreshold = v },
        { label: "Glitch Chance (0-1)", get: () => cfg.glitchChance, set: v => cfg.glitchChance = v, step: 0.01 },
        { label: "Weak Zone Weight", get: () => cfg.weakZoneWeight, set: v => cfg.weakZoneWeight = v, step: 0.1 }
    ];

    fields.forEach(f => {
        const input = document.createElement('input');
        input.type = 'number';
        input.step = f.step || 1;
        input.value = f.get();
        input.className = "editor-input";
        input.oninput = () => { const v = parseFloat(input.value); if (!isNaN(v)) f.set(v); };
        grid.appendChild(labeledWrap(f.label, input));
    });

    return wrap;
}

function buildZonesSection() {
    const wrap = document.createElement('div');
    wrap.className = "editor-section";
    const title = document.createElement('h3');
    title.className = "text-xs uppercase tracking-widest text-white";
    title.textContent = "Zones";
    wrap.appendChild(title);
    const note = document.createElement('p');
    note.className = "text-[10px] text-gray-600";
    note.textContent = "Stat Bias controls which zone gets weighted heavier when that stat is in danger.";
    wrap.appendChild(note);

    editorDraft.zones.forEach((zone, idx) => {
        const row = document.createElement('div');
        row.className = "flex items-center gap-2";

        const keySpan = document.createElement('span');
        keySpan.className = "text-xs text-gray-300 w-24 uppercase tracking-widest";
        keySpan.textContent = zone.key;

        const select = document.createElement('select');
        select.className = "editor-input";
        select.style.width = "auto";
        ["repression", "mask", "child"].forEach(stat => {
            const opt = document.createElement('option');
            opt.value = stat;
            opt.textContent = stat;
            if (zone.statBias === stat) opt.selected = true;
            select.appendChild(opt);
        });
        select.onchange = () => { zone.statBias = select.value; };

        const delBtn = document.createElement('button');
        delBtn.textContent = "Remove";
        delBtn.className = "editor-btn-danger";
        delBtn.onclick = () => {
            const inUse = editorDraft.events.some(e => e.zone === zone.key);
            if (inUse) { alert(`Can't remove "${zone.key}" — events still use it. Reassign or delete those events first.`); return; }
            if (editorDraft.zones.length <= 1) { alert("Keep at least one zone."); return; }
            editorDraft.zones.splice(idx, 1);
            renderEditor();
        };

        row.appendChild(keySpan);
        row.appendChild(select);
        row.appendChild(delBtn);
        wrap.appendChild(row);
    });

    const addRow = document.createElement('div');
    addRow.className = "flex items-center gap-2 mt-1";
    const keyInput = document.createElement('input');
    keyInput.placeholder = "NEW ZONE KEY";
    keyInput.className = "editor-input";
    keyInput.style.width = "10rem";
    const biasSelect = document.createElement('select');
    biasSelect.className = "editor-input";
    biasSelect.style.width = "auto";
    ["repression", "mask", "child"].forEach(stat => {
        const opt = document.createElement('option');
        opt.value = stat; opt.textContent = stat;
        biasSelect.appendChild(opt);
    });
    const addBtn = document.createElement('button');
    addBtn.textContent = "+ Add Zone";
    addBtn.className = "editor-btn";
    addBtn.onclick = () => {
        const key = keyInput.value.trim().toUpperCase();
        if (!key) { alert("Zone needs a name."); return; }
        if (editorDraft.zones.some(z => z.key === key)) { alert("That zone key already exists."); return; }
        editorDraft.zones.push({ key, statBias: biasSelect.value });
        renderEditor();
    };
    addRow.appendChild(keyInput);
    addRow.appendChild(biasSelect);
    addRow.appendChild(addBtn);
    wrap.appendChild(addRow);

    return wrap;
}

function buildMechanismsSection() {
    const wrap = document.createElement('div');
    wrap.className = "editor-section";
    const title = document.createElement('h3');
    title.className = "text-xs uppercase tracking-widest text-white";
    title.textContent = "Coping Mechanisms";
    wrap.appendChild(title);
    const note = document.createElement('p');
    note.className = "text-[10px] text-gray-600";
    note.textContent = "The five response identities (fawn/flight/fight/freeze/secure) are fixed — the engine and the Field Log both depend on them. Rename their display name and retune what they do once unlocked.";
    wrap.appendChild(note);

    Object.entries(editorDraft.mechanisms).forEach(([tag, mech]) => {
        const row = document.createElement('div');
        row.className = "editor-card";

        const head = document.createElement('div');
        head.className = "flex items-center gap-2";
        const tagLabel = document.createElement('span');
        tagLabel.className = "text-[10px] uppercase tracking-widest text-gray-500 w-16 flex-shrink-0";
        tagLabel.textContent = tag;
        const nameInput = document.createElement('input');
        nameInput.className = "editor-input";
        nameInput.value = mech.name;
        nameInput.oninput = () => { mech.name = nameInput.value; };
        head.appendChild(tagLabel);
        head.appendChild(nameInput);
        row.appendChild(head);

        const modGrid = document.createElement('div');
        modGrid.className = "grid grid-cols-3 gap-2";
        ["rep", "mask", "child"].forEach(k => {
            const input = document.createElement('input');
            input.type = 'number';
            input.className = "editor-input";
            input.value = mech.mod[k] || 0;
            input.oninput = () => { const v = parseFloat(input.value); mech.mod[k] = isNaN(v) ? 0 : v; };
            modGrid.appendChild(labeledWrap(`Mod: ${k}`, input));
        });
        row.appendChild(modGrid);

        wrap.appendChild(row);
    });

    return wrap;
}

function buildEndingsSection() {
    const wrap = document.createElement('div');
    wrap.className = "editor-section";
    const title = document.createElement('h3');
    title.className = "text-xs uppercase tracking-widest text-white";
    title.textContent = "Survival Endings";
    wrap.appendChild(title);
    const note = document.createElement('p');
    note.className = "text-[10px] text-gray-600";
    note.textContent = "Evaluated top to bottom. First ending whose conditions all match wins. An ending with no conditions always matches — keep one at the bottom as a fallback.";
    wrap.appendChild(note);

    editorDraft.endings.forEach((ending, idx) => {
        const card = document.createElement('div');
        card.className = "editor-card";

        const headRow = document.createElement('div');
        headRow.className = "flex items-center gap-2";
        const titleInput = document.createElement('input');
        titleInput.className = "editor-input";
        titleInput.value = ending.title;
        titleInput.oninput = () => { ending.title = titleInput.value; };
        headRow.appendChild(titleInput);

        const upBtn = document.createElement('button');
        upBtn.textContent = "↑";
        upBtn.className = "editor-btn";
        upBtn.disabled = idx === 0;
        upBtn.onclick = () => {
            [editorDraft.endings[idx - 1], editorDraft.endings[idx]] = [editorDraft.endings[idx], editorDraft.endings[idx - 1]];
            renderEditor();
        };
        const downBtn = document.createElement('button');
        downBtn.textContent = "↓";
        downBtn.className = "editor-btn";
        downBtn.disabled = idx === editorDraft.endings.length - 1;
        downBtn.onclick = () => {
            [editorDraft.endings[idx + 1], editorDraft.endings[idx]] = [editorDraft.endings[idx], editorDraft.endings[idx + 1]];
            renderEditor();
        };
        const delBtn = document.createElement('button');
        delBtn.textContent = "Remove";
        delBtn.className = "editor-btn-danger";
        delBtn.onclick = () => {
            if (editorDraft.endings.length <= 1) { alert("Keep at least one ending."); return; }
            editorDraft.endings.splice(idx, 1);
            renderEditor();
        };
        headRow.appendChild(upBtn);
        headRow.appendChild(downBtn);
        headRow.appendChild(delBtn);
        card.appendChild(headRow);

        const descArea = document.createElement('textarea');
        descArea.rows = 2;
        descArea.className = "editor-input";
        descArea.value = ending.desc;
        descArea.oninput = () => { ending.desc = descArea.value; };
        card.appendChild(descArea);

        const condWrap = document.createElement('div');
        condWrap.className = "flex flex-col gap-1";
        (ending.conditions || []).forEach((cond, cIdx) => {
            const condRow = document.createElement('div');
            condRow.className = "flex items-center gap-2";

            const statSelect = document.createElement('select');
            statSelect.className = "editor-input";
            statSelect.style.width = "auto";
            ["repression", "mask", "child"].forEach(stat => {
                const opt = document.createElement('option');
                opt.value = stat; opt.textContent = stat;
                if (cond.stat === stat) opt.selected = true;
                statSelect.appendChild(opt);
            });
            statSelect.onchange = () => { cond.stat = statSelect.value; };

            const opSelect = document.createElement('select');
            opSelect.className = "editor-input";
            opSelect.style.width = "auto";
            [">=", "<=", ">", "<", "=="].forEach(op => {
                const opt = document.createElement('option');
                opt.value = op; opt.textContent = op;
                if (cond.op === op) opt.selected = true;
                opSelect.appendChild(opt);
            });
            opSelect.onchange = () => { cond.op = opSelect.value; };

            const valInput = document.createElement('input');
            valInput.type = 'number';
            valInput.className = "editor-input";
            valInput.style.width = "5rem";
            valInput.value = cond.value;
            valInput.oninput = () => { const v = parseFloat(valInput.value); if (!isNaN(v)) cond.value = v; };

            const removeCondBtn = document.createElement('button');
            removeCondBtn.textContent = "×";
            removeCondBtn.className = "editor-btn-danger";
            removeCondBtn.onclick = () => { ending.conditions.splice(cIdx, 1); renderEditor(); };

            condRow.appendChild(statSelect);
            condRow.appendChild(opSelect);
            condRow.appendChild(valInput);
            condRow.appendChild(removeCondBtn);
            condWrap.appendChild(condRow);
        });
        card.appendChild(condWrap);

        const addCondBtn = document.createElement('button');
        addCondBtn.textContent = "+ Add Condition";
        addCondBtn.className = "editor-btn self-start";
        addCondBtn.onclick = () => {
            ending.conditions = ending.conditions || [];
            ending.conditions.push({ stat: "repression", op: ">=", value: 50 });
            renderEditor();
        };
        card.appendChild(addCondBtn);

        wrap.appendChild(card);
    });

    const addEndingBtn = document.createElement('button');
    addEndingBtn.textContent = "+ Add Ending";
    addEndingBtn.className = "editor-btn self-start";
    addEndingBtn.onclick = () => {
        editorDraft.endings.push({ title: "New Ending", desc: "Describe what this ending means.", conditions: [] });
        renderEditor();
    };
    wrap.appendChild(addEndingBtn);

    return wrap;
}

function buildEventsSection() {
    const wrap = document.createElement('div');
    wrap.className = "editor-section";
    const title = document.createElement('h3');
    title.className = "text-xs uppercase tracking-widest text-white";
    title.textContent = `Events (${editorDraft.events.length})`;
    wrap.appendChild(title);

    editorDraft.events.forEach((evt, idx) => {
        wrap.appendChild(buildEventCard(evt, idx));
    });

    const addBtn = document.createElement('button');
    addBtn.textContent = "+ Add Event";
    addBtn.className = "editor-btn self-start";
    addBtn.onclick = () => {
        editorDraft.events.push({
            zone: editorDraft.zones[0] ? editorDraft.zones[0].key : "SELF",
            title: "New Event",
            desc: "Describe the trigger.",
            choices: [
                { text: "First response.", tag: "fawn", effects: { rep: 0, mask: 0, child: 0 }, log: "Logged reaction." },
                { text: "Second response.", tag: "secure", effects: { rep: 0, mask: 0, child: 0 }, log: "Logged reaction." }
            ]
        });
        renderEditor();
    };
    wrap.appendChild(addBtn);

    return wrap;
}

function buildEventCard(evt, idx) {
    const card = document.createElement('div');
    card.className = "editor-card";

    const headRow = document.createElement('div');
    headRow.className = "flex items-center gap-2";

    const zoneSelect = document.createElement('select');
    zoneSelect.className = "editor-input";
    zoneSelect.style.width = "auto";
    editorDraft.zones.forEach(z => {
        const opt = document.createElement('option');
        opt.value = z.key; opt.textContent = z.key;
        if (evt.zone === z.key) opt.selected = true;
        zoneSelect.appendChild(opt);
    });
    zoneSelect.onchange = () => { evt.zone = zoneSelect.value; };

    const titleInput = document.createElement('input');
    titleInput.className = "editor-input";
    titleInput.value = evt.title;
    titleInput.oninput = () => { evt.title = titleInput.value; };

    const delBtn = document.createElement('button');
    delBtn.textContent = "Remove Event";
    delBtn.className = "editor-btn-danger";
    delBtn.onclick = () => {
        if (editorDraft.events.length <= 1) { alert("Keep at least one event."); return; }
        editorDraft.events.splice(idx, 1);
        renderEditor();
    };

    headRow.appendChild(zoneSelect);
    headRow.appendChild(titleInput);
    headRow.appendChild(delBtn);
    card.appendChild(headRow);

    const descArea = document.createElement('textarea');
    descArea.rows = 2;
    descArea.className = "editor-input";
    descArea.value = evt.desc;
    descArea.oninput = () => { evt.desc = descArea.value; };
    card.appendChild(descArea);

    const choicesWrap = document.createElement('div');
    choicesWrap.className = "flex flex-col gap-2 pl-3 border-l border-gray-800";
    evt.choices.forEach((choice, cIdx) => {
        choicesWrap.appendChild(buildChoiceRow(evt, choice, cIdx));
    });
    card.appendChild(choicesWrap);

    const addChoiceBtn = document.createElement('button');
    addChoiceBtn.textContent = "+ Add Choice";
    addChoiceBtn.className = "editor-btn self-start";
    addChoiceBtn.disabled = evt.choices.length >= 5;
    addChoiceBtn.onclick = () => {
        if (evt.choices.length >= 5) return;
        evt.choices.push({ text: "New response.", tag: "fawn", effects: { rep: 0, mask: 0, child: 0 }, log: "Logged reaction." });
        renderEditor();
    };
    card.appendChild(addChoiceBtn);

    return card;
}

function buildChoiceRow(evt, choice, cIdx) {
    choice.effects = choice.effects || { rep: 0, mask: 0, child: 0 };
    const row = document.createElement('div');
    row.className = "flex flex-col gap-1 border border-gray-800 p-2";

    const textInput = document.createElement('input');
    textInput.className = "editor-input";
    textInput.value = choice.text;
    textInput.placeholder = "Choice text";
    textInput.oninput = () => { choice.text = textInput.value; };
    row.appendChild(textInput);

    const metaRow = document.createElement('div');
    metaRow.className = "grid grid-cols-2 md:grid-cols-6 gap-2 items-end";

    const tagSelect = document.createElement('select');
    tagSelect.className = "editor-input";
    Object.keys(editorDraft.mechanisms).forEach(tag => {
        const opt = document.createElement('option');
        opt.value = tag; opt.textContent = tag;
        if (choice.tag === tag) opt.selected = true;
        tagSelect.appendChild(opt);
    });
    tagSelect.onchange = () => { choice.tag = tagSelect.value; };
    metaRow.appendChild(labeledWrap("Response", tagSelect));

    ["rep", "mask", "child"].forEach(k => {
        const input = document.createElement('input');
        input.type = 'number';
        input.className = "editor-input";
        input.value = choice.effects[k] || 0;
        input.oninput = () => { const v = parseFloat(input.value); choice.effects[k] = isNaN(v) ? 0 : v; };
        metaRow.appendChild(labeledWrap(k, input));
    });

    const removeChoiceBtn = document.createElement('button');
    removeChoiceBtn.textContent = "Remove";
    removeChoiceBtn.className = "editor-btn-danger";
    removeChoiceBtn.disabled = evt.choices.length <= 2;
    removeChoiceBtn.onclick = () => {
        if (evt.choices.length <= 2) return;
        evt.choices.splice(cIdx, 1);
        renderEditor();
    };
    metaRow.appendChild(removeChoiceBtn);

    row.appendChild(metaRow);

    const logInput = document.createElement('input');
    logInput.className = "editor-input";
    logInput.placeholder = "Action log line";
    logInput.value = choice.log;
    logInput.oninput = () => { choice.log = logInput.value; };
    row.appendChild(logInput);

    return row;
}

function logEditorStatus(msg) {
    elEditorStatus.textContent = msg;
}

function saveContentDraft() {
    if (!editorDraft) return;
    const cfg = editorDraft.config;
    cfg.glitchChance = Math.max(0, Math.min(1, cfg.glitchChance));
    cfg.unlockThreshold = Math.max(1, Math.round(cfg.unlockThreshold));
    cfg.maxTurns = Math.max(1, Math.round(cfg.maxTurns));
    cfg.hardModeTurns = Math.max(1, Math.round(cfg.hardModeTurns));
    cfg.hardModeMultiplier = Math.max(0.1, cfg.hardModeMultiplier);
    cfg.weakZoneWeight = Math.max(0, cfg.weakZoneWeight);

    saveContent(editorDraft);
    logEditorStatus("Saved. Open the simulator to play with these changes.");
    elPlayPackLink.classList.remove('hidden');
}

function discardEditorDraft() {
    editorDraft = deepClone(getContent());
    renderEditor();
    logEditorStatus("Draft reverted to last saved content.");
}

function resetContentPack() {
    if (!window.confirm("Reset to the default content pack? Your custom events and mechanics will be discarded.")) return;
    resetContent();
    editorDraft = deepClone(getContent());
    renderEditor();
    logEditorStatus("Reverted to default content.");
    elPlayPackLink.classList.remove('hidden');
}

function exportContentPack() {
    const pack = editorDraft || getContent();
    const blob = new Blob([JSON.stringify(pack, null, 2)], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `uct-content-pack-${new Date().toISOString().slice(0, 10)}.json`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
}

function handleImportFile(event) {
    const file = event.target.files[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onload = () => {
        try {
            const parsed = JSON.parse(reader.result);
            if (!isValidContentPack(parsed)) throw new Error("Missing required fields.");
            editorDraft = parsed;
            renderEditor();
            logEditorStatus("Pack imported. Review it below, then Save All Changes to apply.");
        } catch (e) {
            alert("That file doesn't look like a valid content pack: " + e.message);
        }
        event.target.value = '';
    };
    reader.readAsText(file);
}

// Boot
window.onload = () => {
    ensureEditorDraft();
    renderEditor();
};
