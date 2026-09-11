/******************************************************************************
 SCI Template Game
 By Brian Provinciano
 ******************************************************************************
 rm001.sc
 Contains the first room of your game. 
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 1)
/******************************************************************************/
(use "main")
(use "controls")
(use "cycle")
(use "game")
(use "feature")
(use "obj")
(use "inv")
(use "door")
(use "jump")
(use "dpath")
(use "printchoices")
(use "workevents")
(use "homeevents")
(use "socialevents")
(use "selfevents")
(use "bodyevents")
(use "publicevents")
(use "mechanisms")
/******************************************************************************/
(instance public rm001 of Rm
	(properties
		picture scriptNumber
		// Set up the rooms to go to/come from here
		north 0
		east 0
		south 0
		west 0
	)
	(method (init)
		// same in every script, starts things up
  		(super:init())
  		(self:setScript(RoomScript))

  		// Reset per-run state to starting values unconditionally, every
  		// time this room's init runs -- the one place every way of
  		// starting/restarting a run actually passes through (title
  		// screen -> here, the "Restart Game" menu item's kernel-level
  		// RestartGame() -> here, and rm002.sc's clickable computer ->
  		// here via a plain newRoom(), no VM reset). The first two paths
  		// already have these at their declared defaults by the time this
  		// runs (either freshly booted, or reset by RestartGame() itself),
  		// so this is a harmless no-op for them -- it's only load-bearing
  		// for the third, since a plain newRoom() doesn't touch globals at
  		// all. Does NOT touch gCaseFiles/gCF0..17 or gNgPlusUnlocked --
  		// those are the persistent, cross-run record and must survive a
  		// new run starting.
  		= gRepression STARTING_REPRESSION
  		= gMask STARTING_MASK
  		= gChild STARTING_CHILD
  		= gFawnCount 0
  		= gFawnUnlocked FALSE
  		= gFlightCount 0
  		= gFlightUnlocked FALSE
  		= gFightCount 0
  		= gFightUnlocked FALSE
  		= gFreezeCount 0
  		= gFreezeUnlocked FALSE
  		= gSecureCount 0
  		= gSecureUnlocked FALSE

  		// Check which room ego came from and position it
  		(switch(gPreviousRoomNumber)
            /******************************************************
             * Put the cases here for the rooms ego can come from *
             ******************************************************/ /*
            (case north
  				(send gEgo:
  					posn(210 110)
  					loop(2)
  				)
  			)*/
            // Set up ego's position if it hasn't come from any room
  			(default
  				(send gEgo:
  					posn(150 130)
  					loop(1)
  				)
  			)
  		)
		
		// Set up the ego
		SetUpEgo()
		(send gEgo:init())
		// This room is a pure dialog-driven stat loop, no walking-around
		// gameplay -- same reasoning as rm002, hide ego and take back
		// control rather than leaving it visible/movable under the
		// PlayerControl() SetUpEgo() grants by default.
		ProgramControl()
		(send gEgo:hide())

		// Extended Therapy / New Game+ mode choice (matches js/engine.js's
		// NG+ button, gated the same way -- only offered once a standard-
		// session survival has ever unlocked it, see UnlockNgPlus() in
		// casefiles.sc). Asked here, at the top of every run, rather than
		// on TitleScreen.sc, because "Restart Game" (menubar.sc) skips the
		// title screen entirely and jumps straight to this room (see
		// GameIsRestarting() in Main.sc) -- this is the one place every run
		// actually passes through.
		(if(gNgPlusUnlocked)
			= gHardMode PrintChoices(
				"You've survived a session before. Choose how this one goes."
				"New Session"
				290
				NULL
				"Standard Session (10 turns)" FALSE
				"Extended Therapy (20 turns, harder swings)" TRUE
			)
		)(else
			= gHardMode FALSE
		)
		(if(gHardMode)
			= gMaxTurns HARD_MODE_TURNS
			Print("Extended session initiated. Your nervous system has been here before." #title "Extended Therapy")
		)(else
			= gMaxTurns DEFAULT_MAX_TURNS
		)

		// No DrawPortraitMood() call here anymore -- this room never has
		// an idle moment to actually show it (the mode-choice dialog and
		// then runShift()'s turn loop cover this spot immediately), and
		// PrintChoices now shows the portrait live inside its own dialog
		// instead. See mechanisms.sc/printchoices.sc.

		(self:runShift())

        /****************************************
         * Set up the room's music to play here *
         ****************************************/ /*
		(send gTheMusic:
			prevSignal(0)
			stop()
			number(scriptNumber)
			loop(-1)
			play()
		)*/

        /**************************************************
         * Add the rest of your initialization stuff here *
         **************************************************/
  )
	(method (runShift)
		(var zone)
		// Turn loop: fires a random event from a zone-weighted pick (see
		// PickZone() below and PickWorstStat() in mechanisms.sc -- leans
		// toward whichever zone matches the current worst stat, matching
		// the original's pickWeightedEvent()) each turn, until a stat crosses its
		// failure threshold or gMaxTurns is reached. Add a new zone by
		// adding one more (case N ...) here AND in ZoneStatBias() below,
		// bumping ZONE_COUNT in game.sh, and adding its EVENT_COUNT
		// constant + (use "<zone>events") above.
		= gTurn 1
		// TEMPORARY DEBUG INSTRUMENTATION -- remove once the heap-
		// exhaustion bug is actually found. Prints free-heap AND largest-
		// contiguous-free-block (MemoryInfo miFREEHEAP / miLARGESTPTR) at
		// every Load/call/Dispose boundary each turn, using the exact
		// same kernel calls as the stock alt-M debug dialog already wired
		// up in Main.sc:handleEvent -- that hotkey itself can't be reached
		// mid-run since PrintChoices' modal Dialog:doit() loop never lets
		// events reach the room (see the clickable-office-objects finding
		// in SESSION_HANDOFF.md), hence printing unconditionally here
		// instead. miLARGESTPTR matters as much as miFREEHEAP: SCI0's heap
		// is segmented, so it's possible to have plenty of aggregate free
		// bytes while no single contiguous block is big enough to fit the
		// next ~10-14KB chunk -- that would show up as miFREEHEAP looking
		// fine while miLARGESTPTR craters, which total-free-bytes alone
		// would hide.
		FormatPrint("DEBUG boot: heap=%u largest=%u" MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
		(while((<= gTurn gMaxTurns) and (< gRepression 100) and (> gMask 0) and (> gChild 0))
			= zone PickZone()
			FormatPrint("DEBUG T%d Z%d pre-switch: heap=%u largest=%u" gTurn zone MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
			(switch(zone)
				(case 0
					Load(rsSCRIPT WORKEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Load(WORKEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DoWorkEvent(Random(0 (- WORK_EVENT_COUNT 1)))
					FormatPrint("DEBUG T%d post-DoWorkEvent: heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DisposeScript(WORKEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Dispose(WORKEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
				)
				(case 1
					Load(rsSCRIPT HOMEEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Load(HOMEEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DoHomeEvent(Random(0 (- HOME_EVENT_COUNT 1)))
					FormatPrint("DEBUG T%d post-DoHomeEvent: heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DisposeScript(HOMEEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Dispose(HOMEEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
				)
				(case 2
					Load(rsSCRIPT SOCIALEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Load(SOCIALEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DoSocialEvent(Random(0 (- SOCIAL_EVENT_COUNT 1)))
					FormatPrint("DEBUG T%d post-DoSocialEvent: heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DisposeScript(SOCIALEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Dispose(SOCIALEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
				)
				(case 3
					Load(rsSCRIPT SELFEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Load(SELFEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DoSelfEvent(Random(0 (- SELF_EVENT_COUNT 1)))
					FormatPrint("DEBUG T%d post-DoSelfEvent: heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DisposeScript(SELFEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Dispose(SELFEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
				)
				(case 4
					Load(rsSCRIPT BODYEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Load(BODYEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DoBodyEvent(Random(0 (- BODY_EVENT_COUNT 1)))
					FormatPrint("DEBUG T%d post-DoBodyEvent: heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DisposeScript(BODYEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Dispose(BODYEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
				)
				(case 5
					Load(rsSCRIPT PUBLICEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Load(PUBLICEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DoPublicEvent(Random(0 (- PUBLIC_EVENT_COUNT 1)))
					FormatPrint("DEBUG T%d post-DoPublicEvent: heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
					DisposeScript(PUBLICEVENTS_SCRIPT)
					FormatPrint("DEBUG T%d post-Dispose(PUBLICEVENTS): heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
				)
			)
			ClampStats()
			FormatPrint("DEBUG T%d post-ClampStats: heap=%u largest=%u" gTurn MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
			++gTurn
		)
		// Final stat values are already sitting in gRepression/gMask/gChild;
		// rm002 re-checks the same thresholds to pick which ending to show.
		// Handing off via a real room transition (rather than printing the
		// ending here and falling through to normal room control) frees
		// whatever this room's turn accumulated, same as any other SCI
		// room-to-room transition -- see SESSION_HANDOFF.md.
		(send gRoom:newRoom(ENDING_ROOM))
	)
)
/******************************************************************************/
(procedure public (ZoneStatBias zoneIndex)
	// Same zone -> stat-bias mapping as the original's content.js `zones`
	// array. Return value encoding matches PickWorstStat: 0=repression,
	// 1=mask, 2=child.
	(switch(zoneIndex)
		(case 0 return(0))		/* WORK -> repression */
		(case 1 return(2))		/* HOME -> child */
		(case 2 return(1))		/* SOCIAL -> mask */
		(case 3 return(2))		/* SELF -> child */
		(case 4 return(0))		/* BODY -> repression */
		(case 5 return(1))		/* PUBLIC -> mask */
	)
	return(-1)
)
/******************************************************************************/
(procedure public (PickZone)
	// Weighted zone selection matching the original's pickWeightedEvent():
	// zones whose stat bias matches the current worst stat get extra
	// weight (the original's weakZoneWeight is 2.5x; scaled here to
	// integer weights 5 vs 2, same ratio, since SCI0 arithmetic is
	// integer-only). With exactly 2 of the current 6 zones matching any
	// given worst stat, total weight is always 4*2 + 2*5 = 18 -- if the
	// zone/stat-bias mix ever changes this hardcoded 18 (and the Random
	// bound below) would need recomputing.
	// NOT replicated: the original's "don't repeat an event already seen
	// this run" pool -- a separate, bigger feature (needs per-event seen-
	// tracking across all 196 events), not attempted here.
	(var worstStat, i, r, w)
	= worstStat PickWorstStat()
	= r Random(0 17)
	(for (= i 0) (< i ZONE_COUNT) (++i)
		(if(== ZoneStatBias(i) worstStat)
			= w 5
		)(else
			= w 2
		)
		(if(< r w)
			return(i)
		)
		= r (- r w)
	)
	return(0)
)
/******************************************************************************/
(instance RoomScript of Script
	(properties)
	(method (handleEvent pEvent)
        (super:handleEvent(pEvent))

        /*****************************************
         * Handle the possible said phrases here *
         *****************************************/
        (if(Said('look'))
            Print("You are in an empty room")
        )
 	)
)
/******************************************************************************/
