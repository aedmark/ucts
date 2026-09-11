#!/usr/bin/env node
// Generates TRS_SCI/src/rm200.sc..rm233.sc (one room per WORK event) from
// the original browser game's js/events/work.js. See
// tools/lib/zone-events.js for the shared generator logic (used by every
// zone, not just WORK) and game.sh for why room numbers, not a dispatcher
// script, and why 200 (WORK_ROOM_BASE) specifically.
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/work.js',
	srcJsGlobal: 'CONTENT_EVENTS_WORK',
	genScriptName: 'gen-work-events.js',
	zoneLabel: 'WORK',
	roomBase: 200, // must match game.sh's WORK_ROOM_BASE exactly
});
