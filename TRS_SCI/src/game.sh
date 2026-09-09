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
(define PRINTCHOICES_SCRIPT	100)
(define WORKEVENTS_SCRIPT	101)
(define WORKEVENTS1_SCRIPT	102)
(define WORKEVENTS2_SCRIPT	103)
(define WORKEVENTS3_SCRIPT	104)
(define WORKEVENTS4_SCRIPT	105)
(define MECHANISMS_SCRIPT	106)

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
(define MENU_CHANGESPEED	$401)
(define MENU_FASTERSPEED	$403)
(define MENU_NORMALSPEED	$404)
(define MENU_SLOWERSPEED	$405)
(define MENU_VOLUME			$501)
(define MENU_TOGGLESOUND	$502)
