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
(define ENDING_ROOM			2)
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
// 133-135 (ENDINGCONTENT1/2/3_SCRIPT) freed -- those 3 bundled-4-5-pools-
// per-file scripts were replaced by the 12 one-pool-per-file
// ENDINGSURVIVAL0-8_SCRIPT/ENDINGFAILURE0-2_SCRIPT below (real
// heap-exhaustion fix, see SESSION_HANDOFF.md). Left unused rather than
// reassigned, matching this project's own "fresh numbers, not a
// renumbering" discipline used for the WORKEVENTS5-8_SCRIPT-style range
// just below.
(define CASEFILEACCESS_SCRIPT	136)
(define CASEFILETITLES_SCRIPT	137)

// Second half of each zone's chunk split (CHUNK_COUNT 4 -> 8 in
// tools/lib/zone-events.js -- see SESSION_HANDOFF.md's heap-exhaustion
// investigation). Fresh script numbers, not a renumbering of anything
// above -- halves each chunk's compiled size (~6300-8300 bytes down to
// roughly half that) so a chunk load still fits in the ~7100-7400 bytes
// that's realistically left after a turn's own permanent heap cost.
(define WORKEVENTS5_SCRIPT	138)
(define WORKEVENTS6_SCRIPT	139)
(define WORKEVENTS7_SCRIPT	140)
(define WORKEVENTS8_SCRIPT	141)
(define HOMEEVENTS5_SCRIPT	142)
(define HOMEEVENTS6_SCRIPT	143)
(define HOMEEVENTS7_SCRIPT	144)
(define HOMEEVENTS8_SCRIPT	145)
(define SOCIALEVENTS5_SCRIPT	146)
(define SOCIALEVENTS6_SCRIPT	147)
(define SOCIALEVENTS7_SCRIPT	148)
(define SOCIALEVENTS8_SCRIPT	149)
(define SELFEVENTS5_SCRIPT	150)
(define SELFEVENTS6_SCRIPT	151)
(define SELFEVENTS7_SCRIPT	152)
(define SELFEVENTS8_SCRIPT	153)
(define BODYEVENTS5_SCRIPT	154)
(define BODYEVENTS6_SCRIPT	155)
(define BODYEVENTS7_SCRIPT	156)
(define BODYEVENTS8_SCRIPT	157)
(define PUBLICEVENTS5_SCRIPT	158)
(define PUBLICEVENTS6_SCRIPT	159)
(define PUBLICEVENTS7_SCRIPT	160)
(define PUBLICEVENTS8_SCRIPT	161)

// One script per ending POOL (real heap-exhaustion fix -- see
// SESSION_HANDOFF.md): rm002.sc's printEnding()/printSurvivalEnding()
// only ever calls exactly one PrintSurvivalEndingN()/PrintFailureEndingN()
// per ending shown, but the old ENDINGCONTENT1/2/3_SCRIPT bundled 4-5
// pools per file (each ~9.1-11.9KB), so loading the one pool actually
// needed dragged in several others' worth of dead bytecode too -- on top
// of MarkCaseFile()'s own nested Load/DisposeScript of
// CASEFILEACCESS_SCRIPT firing while that whole bundle was still
// resident. A real run confirmed reaching turn 10 cleanly under the
// WORKEVENTS5-8_SCRIPT-style per-turn fix above, then hitting "Out of
// heap space" right after the ending card printed -- this is that fix's
// counterpart for the once-per-run ending path.
(define ENDINGSURVIVAL0_SCRIPT	162)
(define ENDINGSURVIVAL1_SCRIPT	163)
(define ENDINGSURVIVAL2_SCRIPT	164)
(define ENDINGSURVIVAL3_SCRIPT	165)
(define ENDINGSURVIVAL4_SCRIPT	166)
(define ENDINGSURVIVAL5_SCRIPT	167)
(define ENDINGSURVIVAL6_SCRIPT	168)
(define ENDINGSURVIVAL7_SCRIPT	169)
(define ENDINGSURVIVAL8_SCRIPT	170)
(define ENDINGFAILURE0_SCRIPT	171)
(define ENDINGFAILURE1_SCRIPT	172)
(define ENDINGFAILURE2_SCRIPT	173)

// Case Files indices -- stable, cross-run discovery flags. Backed by 108
// separate scalar globals (gCF0..gCF107 in Main.sc), not an array -- global
// arrays declared in Main.sc aren't visible from other scripts via
// (use "main") the way scalars are (see SESSION_HANDOFF.md). GetCaseFile/
// SetCaseFile in casefiles.sc give array-like access over the scalars.
//
// Full ending-variant port (see SESSION_HANDOFF.md): tracks discovery per
// VARIANT now, matching the original browser game's actual "collect every
// ending you've ever seen" mechanic, not just per ending CONDITION like
// this port's earlier one-variant-per-pool scope cut did.
// 0-71: the 9 survival pools x 8 variants each (tools/gen-endings.js's
//       PrintSurvivalEnding0..8, endingcontent1.sc/endingcontent2.sc) --
//       pool N occupies indices (N*8)..(N*8+7), same pool order as
//       rm002.sc's printSurvivalEnding threshold checks.
// 72-101: the 3 failure pools x 10 variants each (PrintFailureEnding0..2,
//       repression/mask/child in that order, endingcontent3.sc) -- pool N
//       occupies indices (72+N*10)..(72+N*10+9).
// 102-106: coping mechanisms, TAG_FAWN..TAG_SECURE offset by
//       CASEFILE_MECH_BASE (unchanged content, just renumbered to make
//       room for the 90 new ending-variant slots above).
// 107: Extended Therapy / New Game+ unlock flag (CASEFILE_NGPLUS) -- not a
// real "case file" (ending/mechanism), riding on this array purely because
// it's the one proven, working cross-run persistence mechanism in this
// codebase (see game.sh's Extended Therapy block below for why a separate
// standalone file was tried first and abandoned). VIEWABLE_CASEFILE_COUNT
// keeps ShowCaseFiles' viewer to everything except that one flag.
(define CASEFILE_COUNT				108)
(define VIEWABLE_CASEFILE_COUNT	107)
(define CASEFILE_MECH_BASE			102)
(define CASEFILE_NGPLUS			107)

// T.R.S. per-zone event counts, for rm001's zone/event picker.
(define WORK_EVENT_COUNT	34)
(define HOME_EVENT_COUNT	32)
(define SOCIAL_EVENT_COUNT	33)
(define SELF_EVENT_COUNT	33)
(define BODY_EVENT_COUNT	32)
(define PUBLIC_EVENT_COUNT	32)
(define ZONE_COUNT			6)

// Player portrait (see SESSION_HANDOFF.md). View 801 is a placeholder
// number (800 is already "Item - Test Object"). Shown only inside
// PrintChoices' own dialog (printchoices.sc, via GetPortraitMood() below
// + a DIcon control) -- deliberately not drawn on any room background
// (not rm001, where a PrintChoices dialog covers that spot almost every
// turn anyway, and not rm002 either, per the user: only show it during
// actual play). PORTRAIT_X/Y are therefore currently unused -- left
// defined (free at compile time either way) in case a background draw
// is ever wanted again. Loop numbers = mood: 0 neutral, 1
// repression/anxious, 2 mask/exposed, 3 child/numb -- whichever stat is
// currently worst, once it's bad enough to actually show
// (PORTRAIT_NEUTRAL_THRESHOLD), matching the same "danger" comparison
// PickWorstStat() already does for zone weighting.
(define PORTRAIT_VIEW				801)
(define PORTRAIT_X					4)
(define PORTRAIT_Y					20)
(define PORTRAIT_MOOD_NEUTRAL		0)
(define PORTRAIT_MOOD_REPRESSION	1)
(define PORTRAIT_MOOD_MASK			2)
(define PORTRAIT_MOOD_CHILD		3)
(define PORTRAIT_NEUTRAL_THRESHOLD	60)

// Clickable office scenery (rm002.sc only -- see SESSION_HANDOFF.md for why
// rm001 can't host these: its whole turn loop is one chain of blocking
// modal PrintChoices() dialogs with no idle moment for the room's own
// handleEvent to ever see a click; rm002, the ending room, reuses the same
// background art (picture 1) and IS genuinely idle). Rectangle is an
// unvalidated guess from a screenshot of the user's actual room art (the
// gray 4-drawer filing cabinet at the room's right edge) -- same
// "guess now, adjust once confirmed" approach as PORTRAIT_X/Y above.
(define CABINET_X1		260)
(define CABINET_Y1		58)
(define CABINET_X2		319)
(define CABINET_Y2		178)

// Same deal as the cabinet above, same screenshot -- the desk's computer
// monitor+keyboard, roughly centered on the desk. Click starts a new run
// (see rm002.sc's handleEvent and rm001.sc's init() per-run reset) instead
// of the "Restart Game" menu item's kernel-level RestartGame().
(define COMPUTER_X1	100)
(define COMPUTER_Y1	40)
(define COMPUTER_X2	180)
(define COMPUTER_Y2	105)

// T.R.S. coping-mechanism tags (choice.tag from the original js/content*.js)
(define TAG_FAWN			0)
(define TAG_FLIGHT			1)
(define TAG_FIGHT			2)
(define TAG_FREEZE			3)
(define TAG_SECURE			4)

// T.R.S. gameplay constants (matching js/content.js's config)
(define STARTING_REPRESSION	40)
(define STARTING_MASK			60)
(define STARTING_CHILD			60)
(define UNLOCK_THRESHOLD	3)		/* same-tag choices before a mechanism unlocks */
(define GLITCH_CHANCE_PCT	15)		/* out of 100, matching glitchChance: 0.15 */
(define GLITCH_CHOICE		99)

// PrintChoices' own choice-button width ceiling (printchoices.sc's
// SizeButtonToWidth). Deliberately separate from the description text's
// own width parameter (baked into each generated event as a literal --
// see tools/lib/zone-events.js's DESC_WIDTH) -- that cap has to leave room
// for the portrait icon sitting beside the description, but buttons start
// back at the dialog's full left edge below both, so they can safely use
// more of the screen.
//
// NOT simply "whatever's safely under 320" -- SizeButtonToWidth mirrors
// stock DButton:setSize()'s own math exactly, which (unlike DText's own
// setSize(), a clean nsLeft + measuredWidth with no adjustment) adds +2
// padding and then rounds the result UP to the next multiple of 16. A
// first attempt at 306 was confirmed too generous by the user (dialog
// escaping both screen edges) -- worst case, 306 + 2 = 308, which rounds
// UP to 320, the full screen width, before even adding the button's own
// 4px left margin. This value instead targets a worst-case ROUNDED
// result of 288 (306 - 20 = 286; 286 + 2 = 288, already a multiple of
// 16, so it doesn't round up further) -- comfortably clear of the 320px
// screen even after the button's own margin and whatever the dialog's
// border chrome adds on top.
(define BUTTON_MAX_WIDTH	286)

// Extended Therapy / New Game+ (matches js/content.js's maxTurns: 10,
// hardModeTurns: 20, hardModeMultiplier: 1.25, and js/engine.js's
// isNgPlusUnlocked/unlockNgPlus/handleChoice). Unlocks permanently the
// first time a standard-session run survives (rm002.sc's
// printSurvivalEnding calls UnlockNgPlus(), casefiles.sc). Once unlocked,
// rm001.sc offers a choice at the top of every run. SCI0 has no floats --
// 1.25 is applied as an integer 5/4 multiply-then-round in mechanisms.sc's
// ScaleHardMode().
//
// Persistence: rides on gCaseFiles/TRSCASE.DAT as slot CASEFILE_NGPLUS
// (see above), NOT a separate file. A standalone TRSNGP.DAT +
// LoadNgPlusUnlocked()/SaveNgPlusUnlocked() was tried first, on the reasoning that
// touching the already-debugged CASEFILE_COUNT/ShowCaseFiles machinery
// for one more flag risked reopening its earlier bugs. That approach
// wrote correctly (confirmed via the raw file on disk) but never read
// back correctly on a fresh boot -- FOpen succeeded (valid handle) but
// FGets came back empty every time, survived a same-process reordering
// test, a from-disk-content re-verification, and an explicit
// zero-the-buffer-first fix (the exact pattern that fixed
// ShowCaseFiles' garbage-row bug), with no success and no identified
// root cause. Whatever's actually wrong there remains unexplained.
// LoadCaseFiles/SaveCaseFiles, by contrast, are proven via a genuinely
// cold-boot test (real entries confirmed still "earned" -- not
// reverted to sealed -- after a full relaunch) to correctly round-trip
// through disk, so folding this flag into that array sidesteps the
// mystery entirely rather than continuing to chase it.
(define DEFAULT_MAX_TURNS	10)
(define HARD_MODE_TURNS		20)
(define HARD_MODE_MULT_NUM	5)		/* 5/4 = 1.25x stat-swing multiplier in Extended Therapy */
(define HARD_MODE_MULT_DEN	4)

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
