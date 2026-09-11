/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm277.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 11). Re-run
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
(script 277)
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
(instance public rm277 of Rm
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
			= glitchText "Walk over and loudly introduce yourself as if you'd never met."
		)
		= choice PrintChoices(
			"You didn't know they'd be here. They just walked in, laughing at something, not looking your way yet."
			"The Ex at the Party"
			290
			glitchText
		"Find a reason to be near the exit for the rest of the night." 0
		"Go say 'hi' first, overly warm, before they can find you." 1
		"Stay where you are. Say 'hi' if it happens naturally." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("This confuses everyone, including, eventually, you.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You mapped an escape route instead of a plan.")
		)
		(case 1
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You got there first so you could control the narrative. They still make you feel powerless.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You didn't need to manage the whole room to survive; they left before they even saw you.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
