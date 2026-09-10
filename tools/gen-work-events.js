#!/usr/bin/env node
// Generates TRS_SCI/src/workevents.sc + workevents1-4.sc from the original
// browser game's js/events/work.js. See tools/lib/zone-events.js for the
// shared generator logic (used by every zone, not just WORK).
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/work.js',
	srcJsGlobal: 'CONTENT_EVENTS_WORK',
	genScriptName: 'gen-work-events.js',
	outPrefix: 'workevents',
	procName: 'WorkEvent',
	dispatchProcName: 'DoWorkEvent',
	scriptConstPrefix: 'WORKEVENTS',
	dispatcherScriptConst: 'WORKEVENTS_SCRIPT',
	zoneLabel: 'WORK-zone',
});
