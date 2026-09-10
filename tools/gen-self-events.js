#!/usr/bin/env node
// Generates TRS_SCI/src/selfevents.sc + selfevents1-4.sc from the original
// browser game's js/events/self.js. See tools/lib/zone-events.js for the
// shared generator logic (used by every zone, not just SELF).
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/self.js',
	srcJsGlobal: 'CONTENT_EVENTS_SELF',
	genScriptName: 'gen-self-events.js',
	outPrefix: 'selfevents',
	procName: 'SelfEvent',
	dispatchProcName: 'DoSelfEvent',
	scriptConstPrefix: 'SELFEVENTS',
	dispatcherScriptConst: 'SELFEVENTS_SCRIPT',
	zoneLabel: 'SELF-zone',
});
