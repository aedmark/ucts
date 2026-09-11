#!/usr/bin/env node
// Generates TRS_SCI/src/rm266.sc..rm298.sc (one room per SOCIAL event) from
// the original browser game's js/events/social.js. See
// tools/lib/zone-events.js for the shared generator logic (used by every
// zone, not just SOCIAL) and game.sh for why room numbers, not a
// dispatcher script, and why 266 (SOCIAL_ROOM_BASE) specifically.
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/social.js',
	srcJsGlobal: 'CONTENT_EVENTS_SOCIAL',
	genScriptName: 'gen-social-events.js',
	zoneLabel: 'SOCIAL',
	roomBase: 266, // must match game.sh's SOCIAL_ROOM_BASE exactly
});
