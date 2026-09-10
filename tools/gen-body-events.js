#!/usr/bin/env node
// Generates TRS_SCI/src/bodyevents.sc + bodyevents1-4.sc from the original
// browser game's js/events/body.js. See tools/lib/zone-events.js for the
// shared generator logic (used by every zone, not just BODY).
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/body.js',
	srcJsGlobal: 'CONTENT_EVENTS_BODY',
	genScriptName: 'gen-body-events.js',
	outPrefix: 'bodyevents',
	procName: 'BodyEvent',
	dispatchProcName: 'DoBodyEvent',
	scriptConstPrefix: 'BODYEVENTS',
	dispatcherScriptConst: 'BODYEVENTS_SCRIPT',
	zoneLabel: 'BODY-zone',
});
