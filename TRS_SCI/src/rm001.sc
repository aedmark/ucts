/******************************************************************************
 SCI Template Game
 By Brian Provinciano
 ******************************************************************************
 rm001.sc
 Contains the first room of your game.

 T.R.S.: entry point for a run, not the turn loop anymore -- the old
 runShift() (a single room looping through gMaxTurns turns via manual
 Load/DisposeScript chunk-cycling) was replaced by one-room-per-event (see
 game.sh and SESSION_HANDOFF.md for why: real, repeatable SCI0 heap
 fragmentation in the old chunk-cycling that splitting chunks smaller could
 reduce but never eliminate). This room now only handles the per-run
 reset + Extended Therapy mode-choice dialog, then calls EndTurn()
 (mechanisms.sc) once with gTurn pre-set to 0 to bootstrap the first turn
 through the same increment-check-branch logic every one of the 196 event
 rooms uses to end its own turn. rm001 is never revisited mid-run --
 event rooms transition directly to each other via GoToNextEvent().
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
  		// No-repeat event pool (mechanisms.sc) -- gSeenEvent persists for
  		// the whole session once mechanisms.sc first loads, so it needs
  		// the same explicit per-run reset as the mechanism counts above.
  		ResetSeenEvents()

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
		// This room (and every event room after it) is a pure dialog-
		// driven stat loop, no walking-around gameplay -- hide ego and
		// take back control rather than leaving it visible/movable under
		// the PlayerControl() SetUpEgo() grants by default.
		ProgramControl()
		(send gEgo:hide())

		// Background music for the whole run (Vivaldi's "Winter", sound
		// resource 3 -- see game.ini's [Sound] section and
		// resource.cfg's soundDrv, switched to gm.drv/General MIDI
		// specifically so this plays back with real instrument sound
		// rather than through whatever STD.DRV's more limited default
		// was). Moved here from the stock template's own commented-out
		// placement (which sat AFTER the EndTurn() call below and so
		// would never actually have run -- EndTurn() immediately
		// transitions to the first event room). Sound objects persist
		// independently of room transitions once started, so this plays
		// continuously through however many event rooms a run visits;
		// stop() first in case a previous run's music (or this same
		// call, on a restart via the clickable computer) is still
		// playing, so it always restarts cleanly from the top rather
		// than layering or silently no-op'ing.
		(send gTheMusic:
			prevSignal(0)
			stop()
			number(3)
			loop(-1)
			play()
		)

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
		// then the first event's own PrintChoices dialog cover this spot
		// immediately), and PrintChoices now shows the portrait live
		// inside its own dialog instead. See mechanisms.sc/printchoices.sc.

		// Bootstrap the first turn through EndTurn()'s own
		// increment-check-branch logic (mechanisms.sc) rather than
		// duplicating it here -- gTurn=0 going in means EndTurn() sees
		// gTurn=1 after its own ++gTurn, checks it against gMaxTurns (and
		// the freshly-reset stats, which can't be in a failure state yet)
		// exactly like every subsequent turn's own end-of-turn check, then
		// calls GoToNextEvent() to transition to the actual first event
		// room. rm001 itself is never revisited after this.
		= gTurn 0
		EndTurn()

        /**************************************************
         * Add the rest of your initialization stuff here *
         **************************************************/
  )
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
