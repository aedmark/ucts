#!/usr/bin/env node
// Generates TRS_SCI/src/rm299.sc..rm331.sc (one room per SELF event) from
// the original browser game's js/events/self.js. See
// tools/lib/zone-events.js for the shared generator logic (used by every
// zone, not just SELF) and game.sh for why room numbers, not a dispatcher
// script, and why 299 (SELF_ROOM_BASE) specifically.
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/self.js',
	srcJsGlobal: 'CONTENT_EVENTS_SELF',
	genScriptName: 'gen-self-events.js',
	zoneLabel: 'SELF',
	roomBase: 299, // must match game.sh's SELF_ROOM_BASE exactly
});
