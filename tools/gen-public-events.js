#!/usr/bin/env node
// Generates TRS_SCI/src/rm364.sc..rm395.sc (one room per PUBLIC event) from
// the original browser game's js/events/public.js. See
// tools/lib/zone-events.js for the shared generator logic (used by every
// zone, not just PUBLIC) and game.sh for why room numbers, not a
// dispatcher script, and why 364 (PUBLIC_ROOM_BASE) specifically.
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/public.js',
	srcJsGlobal: 'CONTENT_EVENTS_PUBLIC',
	genScriptName: 'gen-public-events.js',
	zoneLabel: 'PUBLIC',
	roomBase: 364, // must match game.sh's PUBLIC_ROOM_BASE exactly
});
