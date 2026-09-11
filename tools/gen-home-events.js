#!/usr/bin/env node
// Generates TRS_SCI/src/rm234.sc..rm265.sc (one room per HOME event) from
// the original browser game's js/events/home.js. See
// tools/lib/zone-events.js for the shared generator logic (used by every
// zone, not just HOME) and game.sh for why room numbers, not a dispatcher
// script, and why 234 (HOME_ROOM_BASE) specifically.
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/home.js',
	srcJsGlobal: 'CONTENT_EVENTS_HOME',
	genScriptName: 'gen-home-events.js',
	zoneLabel: 'HOME',
	roomBase: 234, // must match game.sh's HOME_ROOM_BASE exactly
});
