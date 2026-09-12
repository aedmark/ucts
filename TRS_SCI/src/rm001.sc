/******************************************************************************
 SCI Template Game
 By Brian Provinciano
 ******************************************************************************
 rm001.sc
 Contains the first room of your game.

 T.R.S.: entry point for a run, not the turn loop -- one room per event
 (game.sh) means each of the 196 event rooms ends its own turn via
 EndTurn() (mechanisms.sc) and transitions directly to the next. This
 room just does the per-run reset and the Extended Therapy mode choice,
 then bootstraps the first turn through EndTurn() with gTurn pre-set to
 0. Never revisited mid-run.
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

  		// Reset per-run state -- runs through here on every path that
  		// starts/restarts a run (title screen, "Restart Game", and
  		// rm002.sc's clickable computer, which uses a plain newRoom()
  		// with no other reset). Deliberately does NOT touch
  		// gCF0..107/gNgPlusUnlocked -- those are the persistent,
  		// cross-run record.
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
  		ResetSeenEvents()		/* gSeenEvent persists all session, needs its own per-run reset */

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
		// Pure dialog-driven stat loop, no walking-around gameplay --
		// hide ego and take back the control PlayerControl() grants by
		// default (SetUpEgo() calls it internally).
		ProgramControl()
		(send gEgo:hide())

		// Background music, sound resource 3, gm.drv/General MIDI (see
		// resource.cfg). stop() first so a restart never layers tracks.
		// Sound objects persist across room transitions once started.
		(send gTheMusic:
			prevSignal(0)
			stop()
			number(3)
			loop(-1)
			play()
		)

		// Extended Therapy mode choice -- asked here (top of every run)
		// rather than TitleScreen.sc, since "Restart Game" skips the
		// title screen and jumps straight here.
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

		// Bootstrap the first turn through EndTurn()'s own logic
		// (mechanisms.sc) instead of duplicating it -- gTurn=0 going in
		// means it sees turn 1 exactly like any other turn's own check.
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
