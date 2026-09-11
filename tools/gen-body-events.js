#!/usr/bin/env node
// Generates TRS_SCI/src/rm332.sc..rm363.sc (one room per BODY event) from
// the original browser game's js/events/body.js. See
// tools/lib/zone-events.js for the shared generator logic (used by every
// zone, not just BODY) and game.sh for why room numbers, not a dispatcher
// script, and why 332 (BODY_ROOM_BASE) specifically.
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/body.js',
	srcJsGlobal: 'CONTENT_EVENTS_BODY',
	genScriptName: 'gen-body-events.js',
	zoneLabel: 'BODY',
	roomBase: 332, // must match game.sh's BODY_ROOM_BASE exactly
});
