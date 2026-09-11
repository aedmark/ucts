/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm273.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 7). Re-run
 that script after editing the source event data.

 One room per event (see game.sh and SESSION_HANDOFF.md) -- this room IS
 the event: shows its PrintChoices dialog, applies the chosen response's
 effects, prints its log line, then hands off via EndTurn() (mechanisms.sc)
 to either the next event's room or the ending room. No custom RoomScript
 -- ego is hidden/program-controlled and there's nothing here to click or
 "look" at, and Rm's own `script` property defaults to 0 (a valid,
 handled no-script state) if never set.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 273)
/******************************************************************************/
(use "main")
(use "controls")
(use "cycle")
(use "game")
(use "feature")
(use "obj")
(use "inv")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(instance public rm273 of Rm
	(properties
		picture 1
		north 0
		east 0
		south 0
		west 0
	)
	(method (init)
		(var choice, glitchText)
		(super:init())
		SetUpEgo()
		(send gEgo:init())
		ProgramControl()
		(send gEgo:hide())
		= glitchText NULL
		(if(< Random(0 99) GLITCH_CHANCE_PCT)
			= glitchText "Order enough food for four and narrate an imaginary dinner party."
		)
		= choice PrintChoices(
			"You get to the restaurant first. You sit alone at a table for four, aware of exactly how alone you look."
			"The Table for One"
			290
			glitchText
		"Stare at your phone intensely so you look busy, not waiting." 0
		"Apologize to the host for taking up a table for four." 1
		"Sit there. Look around. Let it be fine." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your imaginary guests are excellent listeners and terrible tippers.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You performed occupation to avoid looking like a 'loser.'")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You apologized for the size of a table you didn't choose.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Being visibly alone turned out fine; you got fresh breadsticks and didn't have to share any.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
