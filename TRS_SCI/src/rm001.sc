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
		(if(>= gRepression 100)
			Print(
				"Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet."
				#title "Panic Attack"
			)
			return
		)
		(if(<= gMask 0)
			Print(
				"Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but free."
				#title "Social Exile"
			)
			return
		)
		(if(<= gChild 0)
			Print(
				"Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory. You feel nothing."
				#title "Total Disassociation"
			)
			return
		)
		(self:printSurvivalEnding())
	)
	(method (printSurvivalEnding)
		// One representative title/desc per condition from the original's
		// CONTENT_ENDINGS (js/content-endings.js) -- same condition table,
		// same order (first match wins), just one flavor variant each
		// instead of the original's 8 per pool, to keep this well within
		// the per-script memory budget. Conditions matched via a flat
		// sequence of early-return ifs rather than a chained else-if --
		// see SESSION_HANDOFF.md's if/else gotcha for why.
		(if(>= gRepression 70)
			Print("You didn't explode. You just got very, very good at ticking." #title "The Powder Keg")
			return
		)
		(if((>= gMask 85) and (<= gChild 25))
			Print("Nobody has seen the real you in years, including you." #title "The Performer")
			return
		)
		(if((>= gChild 75) and (<= gMask 40))
			Print("You stopped hiding. It cost you more than you expected, but you kept yourself." #title "Radically Undone")
			return
		)
		(if((<= gMask 25) and (>= gChild 25))
			Print("You stopped filtering. Everything's a little too loud and a little too close to the surface right now." #title "Raw Nerve")
			return
		)
		(if((>= gMask 41) and (<= gChild 25))
			Print("You looked fine all day. You have no real idea what was actually fueling that." #title "Coasting on Empty")
			return
		)
		(if((<= gRepression 30) and (>= gMask 40) and (<= gMask 70) and (>= gChild 40) and (<= gChild 70))
			Print("Nothing is fixed. Nothing is on fire. This might be what okay feels like." #title "Fragile Equilibrium")
			return
		)
		(if((<= gRepression 30) and (>= gMask 60) and (>= gChild 60))
			Print("Not surviving. Not performing. Just, for once, actually okay. You can tell the difference from the inside." #title "Actually Okay")
			return
		)
		(if((>= gRepression 31) and (<= gRepression 69) and (>= gMask 40) and (>= gChild 40))
			Print("You're carrying more than you'd like to admit, and carrying it fine, for now." #title "The Long Fuse")
			return
		)
		Print("You made it to tomorrow. Good job." #title "Functional Enough")
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
