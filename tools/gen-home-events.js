#!/usr/bin/env node
// Generates TRS_SCI/src/homeevents.sc + homeevents1-4.sc from the original
// browser game's js/events/home.js. See tools/lib/zone-events.js for the
// shared generator logic (used by every zone, not just HOME).
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/home.js',
	srcJsGlobal: 'CONTENT_EVENTS_HOME',
	genScriptName: 'gen-home-events.js',
	outPrefix: 'homeevents',
	procName: 'HomeEvent',
	dispatchProcName: 'DoHomeEvent',
	scriptConstPrefix: 'HOMEEVENTS',
	dispatcherScriptConst: 'HOMEEVENTS_SCRIPT',
	zoneLabel: 'HOME-zone',
});
