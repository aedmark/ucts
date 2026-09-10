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
(use "workevents")
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
		// Turn loop: fires a random WORK event each turn until a stat crosses
		// its failure threshold or gMaxTurns is reached. No event picker
		// weighting yet -- still only WORK-zone content exists, so
		// zone-bias is moot -- see SESSION_HANDOFF.md item 2.
		= gTurn 1
		(while((<= gTurn gMaxTurns) and (< gRepression 100) and (> gMask 0) and (> gChild 0))
			DoWorkEvent(Random(0 33))
			ClampStats()
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
