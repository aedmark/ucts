/*************************************************************************
 * SCI Game Header
 * By Brian Provinciano
 *************************************************************************
 * Put all the defines specific to your game in here
 *************************************************************************/

// Base Scripts
(define MAIN_SCRIPT			  0)

(define AVOID_SCRIPT          973)
(define DOOR_SCRIPT		 	974)
(define AUTODOOR_SCRIPT		975)
(define INITROOMS_SCRIPT	976)
(define DYING_SCRIPT		977)
(define DISPOSELOAD_SCRIPT	978)
(define CONTROLS_SCRIPT		979)
(define DPATH_SCRIPT		980)
(define DCICON_SCRIPT		981)
(define REV_SCRIPT			982)
(define WANDER_SCRIPT		983)
(define FOLLOW_SCRIPT		984)
(define TIMER_SCRIPT		985)
(define WINDOW_SCRIPT		986)
(define GAUGE_SCRIPT		987)
(define EXTRA_SCRIPT		988)
(define SOUND_SCRIPT		989)
(define SYSWINDOW_SCRIPT	990)
(define JUMP_SCRIPT			991)
(define CYCLE_SCRIPT		992)
(define FILEIO_SCRIPT		993)
(define GAME_SCRIPT			994)
(define INVENTORY_SCRIPT	995)
(define USER_SCRIPT			996)
(define MENUBAR_SCRIPT		997)
(define FEATURE_SCRIPT		998)
(define OBJ_SCRIPT			999)

// Game Scripts
(define TITLESCREEN_SCRIPT	800)
(define ENDING_ROOM			2)		/* rm002 -- dedicated room for the end-of-run message, entered via a scripted newRoom() from rm001's runShift once a stat hits a fatal threshold or the turn limit is reached */
(define PRINTCHOICES_SCRIPT	100)
(define WORKEVENTS_SCRIPT	101)
(define WORKEVENTS1_SCRIPT	102)
(define WORKEVENTS2_SCRIPT	103)
(define WORKEVENTS3_SCRIPT	104)
(define WORKEVENTS4_SCRIPT	105)
(define MECHANISMS_SCRIPT	106)
(define CASEFILES_SCRIPT	107)
(define HOMEEVENTS_SCRIPT	108)
(define HOMEEVENTS1_SCRIPT	109)
(define HOMEEVENTS2_SCRIPT	110)
(define HOMEEVENTS3_SCRIPT	111)
(define HOMEEVENTS4_SCRIPT	112)
(define SOCIALEVENTS_SCRIPT	113)
(define SOCIALEVENTS1_SCRIPT	114)
(define SOCIALEVENTS2_SCRIPT	115)
(define SOCIALEVENTS3_SCRIPT	116)
(define SOCIALEVENTS4_SCRIPT	117)
(define SELFEVENTS_SCRIPT	118)
(define SELFEVENTS1_SCRIPT	119)
(define SELFEVENTS2_SCRIPT	120)
(define SELFEVENTS3_SCRIPT	121)
(define SELFEVENTS4_SCRIPT	122)
(define BODYEVENTS_SCRIPT	123)
(define BODYEVENTS1_SCRIPT	124)
(define BODYEVENTS2_SCRIPT	125)
(define BODYEVENTS3_SCRIPT	126)
(define BODYEVENTS4_SCRIPT	127)
(define PUBLICEVENTS_SCRIPT	128)
(define PUBLICEVENTS1_SCRIPT	129)
(define PUBLICEVENTS2_SCRIPT	130)
(define PUBLICEVENTS3_SCRIPT	131)
(define PUBLICEVENTS4_SCRIPT	132)

// Case Files indices -- stable, cross-run discovery flags. Backed by 17
// separate scalar globals (gCF0..gCF16 in Main.sc), not an array -- global
// arrays declared in Main.sc aren't visible from other scripts via
// (use "main") the way scalars are (see SESSION_HANDOFF.md). GetCaseFile/
// SetCaseFile in casefiles.sc give array-like access over the scalars.
// 0-8: survival endings, same order as rm002's printSurvivalEnding checks.
// 9-11: failure endings (repression/mask/child, in that order).
// 12-16: coping mechanisms, TAG_FAWN..TAG_SECURE offset by CASEFILE_MECH_BASE.
(define CASEFILE_COUNT				17)
(define CASEFILE_MECH_BASE			12)

// T.R.S. per-zone event counts, for rm001's zone/event picker.
(define WORK_EVENT_COUNT	34)
(define HOME_EVENT_COUNT	32)
(define SOCIAL_EVENT_COUNT	33)
(define SELF_EVENT_COUNT	33)
(define BODY_EVENT_COUNT	32)
(define PUBLIC_EVENT_COUNT	32)
(define ZONE_COUNT			6)		/* WORK, HOME, SOCIAL, SELF, BODY, PUBLIC -- rm001's zone picker does Random(0 ZONE_COUNT-1) */

// T.R.S. coping-mechanism tags (choice.tag from the original js/content*.js)
(define TAG_FAWN			0)
(define TAG_FLIGHT			1)
(define TAG_FIGHT			2)
(define TAG_FREEZE			3)
(define TAG_SECURE			4)

// T.R.S. gameplay constants (matching js/content.js's config)
(define UNLOCK_THRESHOLD	3)		/* same-tag choices before a mechanism unlocks */
(define GLITCH_CHANCE_PCT	15)		/* out of 100, matching glitchChance: 0.15 */
(define GLITCH_CHOICE		99)		/* PrintChoices return value sentinel for "picked the glitch button" -- never collides with a real choice index (0-4) or PrintChoices' own -1 cancel-to-0 case */

// Defaults
(define NORMAL_SPEED		  8)

// Sounds
(define DUMMY_SOUND           1)
(define SCORE_SOUND         900)
(define DEATH_SOUND           2)

// Inventory Items
(define INV_NOTHING           0)
(define INV_TEST_OBJECT       1)

// Fonts
(define DEFAULT_FONT          0)
(define WINDOW_FONT           1)
(define SMALL_FONT            4)
(define LARGE_FONT            9)
(define DEBUG_FONT          999)

// Menu IDs eg. $302 means third menu, second item
(define MENU_ABOUT			$101)
(define MENU_HELP			$102)
(define MENU_RESTART		$201)
(define MENU_SAVE			$202)
(define MENU_RESTORE		$203)
(define MENU_QUIT			$205)
(define MENU_PAUSE			$301)
(define MENU_INVENTORY		$302)
(define MENU_RETYPE			$303)
(define MENU_COLOURS		$305)
(define MENU_CASEFILES		$306)
(define MENU_CHANGESPEED	$401)
(define MENU_FASTERSPEED	$403)
(define MENU_NORMALSPEED	$404)
(define MENU_SLOWERSPEED	$405)
(define MENU_VOLUME			$501)
(define MENU_TOGGLESOUND	$502)
