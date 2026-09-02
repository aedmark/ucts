// ============================================================
// FIELD LOG — real, persistent journaling using the same tag vocabulary
// as the simulation. index.html only. Depends on content.js.
// ============================================================
const FIELD_LOG_KEY = 'uct_field_log_v1';
let selectedFieldTag = null;
let selectedFieldZone = null;
let fieldLogCache = null;

function loadFieldLog() {
    if (fieldLogCache) return fieldLogCache;
    try { fieldLogCache = JSON.parse(localStorage.getItem(FIELD_LOG_KEY)) || []; }
    catch (e) { fieldLogCache = []; }
    return fieldLogCache;
}
function saveFieldLog(entries) {
    fieldLogCache = entries;
    try { localStorage.setItem(FIELD_LOG_KEY, JSON.stringify(entries)); }
    catch (e) { /* storage unavailable, entries persist for this session only */ }
}

function deleteFieldEntry(id) {
    if (!window.confirm("Delete this entry? This can't be undone.")) return;
    saveFieldLog(loadFieldLog().filter(e => e.id !== id));
    renderFieldEntries();
    renderPattern();
}

// DOM Elements
const elFieldNote = document.getElementById('field-note');
const elFieldTagPicker = document.getElementById('field-tag-picker');
const elFieldZonePicker = document.getElementById('field-zone-picker');
const elFieldPatternBars = document.getElementById('field-pattern-bars');
const elFieldReading = document.getElementById('field-reading');
const elFieldEntries = document.getElementById('field-entries');

function renderFieldPickers() {
    const content = getContent();

    elFieldTagPicker.innerHTML = '';
    Object.keys(content.mechanisms).forEach(tag => {
        const btn = document.createElement('button');
        btn.type = 'button';
        btn.dataset.tag = tag;
        btn.textContent = tag;
        btn.className = "field-picker-btn tag";
        btn.onclick = () => {
            selectedFieldTag = tag;
            [...elFieldTagPicker.children].forEach(b => {
                const active = b.dataset.tag === tag;
                b.classList.toggle('active', active);
            });
        };
        elFieldTagPicker.appendChild(btn);
    });

    elFieldZonePicker.innerHTML = '';
    content.zones.map(z => z.key).forEach(zone => {
        const btn = document.createElement('button');
        btn.type = 'button';
        btn.dataset.zone = zone;
        btn.textContent = zone;
        btn.className = "field-picker-btn zone";
        btn.onclick = () => {
            selectedFieldZone = (selectedFieldZone === zone) ? null : zone;
            [...elFieldZonePicker.children].forEach(b => {
                const active = b.dataset.zone === selectedFieldZone;
                b.classList.toggle('active', active);
            });
        };
        elFieldZonePicker.appendChild(btn);
    });
}

function submitFieldEntry() {
    const note = elFieldNote.value.trim();
    if (!note || !selectedFieldTag) {
        elFieldReading.textContent = !note
            ? "Write down what actually happened first."
            : "Pick the response that fits closest. None of them are wrong.";
        return;
    }

    const entries = loadFieldLog();
    entries.push({
        id: Date.now() + Math.random().toString(36).slice(2),
        date: new Date().toISOString(),
        tag: selectedFieldTag,
        zone: selectedFieldZone,
        note
    });
    saveFieldLog(entries);

    elFieldNote.value = '';
    selectedFieldTag = null;
    selectedFieldZone = null;
    renderFieldPickers();
    renderFieldEntries();
    renderPattern();
}

function renderFieldEntries() {
    const entries = loadFieldLog().slice().sort((a, b) => new Date(b.date) - new Date(a.date));
    elFieldEntries.innerHTML = '';

    if (entries.length === 0) {
        const empty = document.createElement('p');
        empty.className = "field-entries-empty";
        empty.textContent = "No entries yet. The blank page is doing a lot of the work here.";
        elFieldEntries.appendChild(empty);
        return;
    }

    entries.forEach(entry => {
        const row = document.createElement('div');
        row.className = "field-entry";

        const headRow = document.createElement('div');
        headRow.className = "field-entry-head";
        const when = document.createElement('span');
        when.textContent = new Date(entry.date).toLocaleString(undefined, {
            month: 'short', day: 'numeric', hour: 'numeric', minute: '2-digit'
        });
        const tagZone = document.createElement('span');
        tagZone.textContent = entry.tag + (entry.zone ? ' · ' + entry.zone : '');
        const delBtn = document.createElement('button');
        delBtn.type = 'button';
        delBtn.textContent = '\u00d7';
        delBtn.title = 'Delete this entry';
        delBtn.className = "field-entry-delete";
        delBtn.onclick = () => deleteFieldEntry(entry.id);
        const rightGroup = document.createElement('span');
        rightGroup.className = "row";
        rightGroup.appendChild(tagZone);
        rightGroup.appendChild(delBtn);
        headRow.appendChild(when);
        headRow.appendChild(rightGroup);

        const noteP = document.createElement('p');
        noteP.className = "field-entry-note";
        noteP.textContent = entry.note;

        row.appendChild(headRow);
        row.appendChild(noteP);
        elFieldEntries.appendChild(row);
    });
}

function computeTagCounts(entries) {
    const counts = { fawn: 0, flight: 0, fight: 0, freeze: 0, secure: 0 };
    entries.forEach(e => { if (counts.hasOwnProperty(e.tag)) counts[e.tag]++; });
    return counts;
}

function renderPattern() {
    const content = getContent();
    const all = loadFieldLog();
    const sevenDaysAgo = Date.now() - 7 * 24 * 60 * 60 * 1000;
    let windowEntries = all.filter(e => new Date(e.date).getTime() >= sevenDaysAgo);
    let windowLabel = "last 7 days";
    if (windowEntries.length === 0 && all.length > 0) {
        windowEntries = all;
        windowLabel = "all time";
    }

    const counts = computeTagCounts(windowEntries);
    const max = Math.max(1, ...Object.values(counts));

    elFieldPatternBars.innerHTML = '';
    Object.entries(counts).forEach(([tag, count]) => {
        const row = document.createElement('div');
        row.className = "field-pattern-row";
        row.innerHTML = `
            <span class="field-pattern-tag">${tag}</span>
            <div class="stat-bar-container field-pattern-track">
                <div class="stat-bar-fill gray" style="width: ${(count / max) * 100}%;"></div>
            </div>
            <span class="field-pattern-count">${count}</span>
        `;
        elFieldPatternBars.appendChild(row);
    });

    if (all.length === 0) {
        elFieldReading.textContent = "Nothing logged yet.";
        return;
    }

    const topCount = Math.max(...Object.values(counts));
    if (topCount === 0) {
        elFieldReading.textContent = `No pattern yet in the ${windowLabel}.`;
        return;
    }

    // A tie on count used to get broken silently by object key order (fawn always
    // beat flight, no matter what actually happened). Break it on something real
    // instead: whichever tied response showed up most recently in the window. If
    // it's still a genuine tie, say so rather than pretending one response won.
    const mostRecentTimestamp = tag => {
        const hits = windowEntries.filter(e => e.tag === tag);
        return hits.length ? Math.max(...hits.map(e => new Date(e.date).getTime())) : -Infinity;
    };
    const contenders = Object.keys(counts)
        .filter(tag => counts[tag] === topCount)
        .sort((a, b) => mostRecentTimestamp(b) - mostRecentTimestamp(a));

    const dominantTag = contenders[0];
    const mechName = content.mechanisms[dominantTag] ? content.mechanisms[dominantTag].name : dominantTag;
    let reading = `Most common response in the ${windowLabel}: ${dominantTag} (${mechName}), ${topCount} of ${windowEntries.length} ${windowEntries.length === 1 ? 'entry' : 'entries'}.`;

    if (contenders.length > 1) {
        const otherNames = contenders.slice(1).map(t => content.mechanisms[t] ? content.mechanisms[t].name : t);
        reading += ` Tied at ${topCount} with ${otherNames.join(', ')} \u2014 ${dominantTag} takes it for showing up most recently.`;
    }

    elFieldReading.textContent = reading;
}

function exportFieldLog() {
    const entries = loadFieldLog().slice().sort((a, b) => new Date(a.date) - new Date(b.date));
    if (entries.length === 0) return;

    const lines = entries.map(e =>
        `[${new Date(e.date).toLocaleString()}] ${e.tag}${e.zone ? ' / ' + e.zone : ''}\n${e.note}\n`
    );
    const blob = new Blob([lines.join('\n')], { type: 'text/plain' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `field-log-${new Date().toISOString().slice(0, 10)}.txt`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
}

function clearFieldLog() {
    if (loadFieldLog().length === 0) return;
    if (!window.confirm("Delete your entire field log? This can't be undone.")) return;
    saveFieldLog([]);
    renderFieldEntries();
    renderPattern();
}
