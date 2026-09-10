#!/usr/bin/env node
// Generates TRS_SCI/src/publicevents.sc + publicevents1-4.sc from the
// original browser game's js/events/public.js. See tools/lib/zone-events.js
// for the shared generator logic (used by every zone, not just PUBLIC).
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/public.js',
	srcJsGlobal: 'CONTENT_EVENTS_PUBLIC',
	genScriptName: 'gen-public-events.js',
	outPrefix: 'publicevents',
	procName: 'PublicEvent',
	dispatchProcName: 'DoPublicEvent',
	scriptConstPrefix: 'PUBLICEVENTS',
	dispatcherScriptConst: 'PUBLICEVENTS_SCRIPT',
	zoneLabel: 'PUBLIC-zone',
});
