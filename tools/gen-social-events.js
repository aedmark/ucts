#!/usr/bin/env node
// Generates TRS_SCI/src/socialevents.sc + socialevents1-4.sc from the
// original browser game's js/events/social.js. See tools/lib/zone-events.js
// for the shared generator logic (used by every zone, not just SOCIAL).
'use strict';
const path = require('path');
const { generateZoneEvents } = require('./lib/zone-events');

generateZoneEvents({
	repoRoot: path.resolve(__dirname, '..'),
	srcJsRelPath: 'js/events/social.js',
	srcJsGlobal: 'CONTENT_EVENTS_SOCIAL',
	genScriptName: 'gen-social-events.js',
	outPrefix: 'socialevents',
	procName: 'SocialEvent',
	dispatchProcName: 'DoSocialEvent',
	scriptConstPrefix: 'SOCIALEVENTS',
	dispatcherScriptConst: 'SOCIALEVENTS_SCRIPT',
	zoneLabel: 'SOCIAL-zone',
});
