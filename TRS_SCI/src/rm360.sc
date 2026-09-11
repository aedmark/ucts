/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm360.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 28). Re-run
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
(script 360)
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
(instance public rm360 of Rm
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
			= glitchText "Set a recurring hourly alarm labeled 'BREATHE.'"
		)
		= choice PrintChoices(
			"You catch yourself mid-email, barely breathing, jaw clenched, for who knows how long."
			"The Shallow Breathing Mid-Task"
			290
			glitchText
		"Notice it, feel briefly alarmed, and keep typing exactly the same way." 0
		"Apologize to no one in particular for being 'a little tense today.'" 1
		"Stop, take three actual breaths, and unclench your shoulders on purpose." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It will go off in the middle of a meeting later. This is fine.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You clocked it and kept going anyway. The body filed a complaint it can't really enforce.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("You apologized for your own nervous system to an indifferent room.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Thirty seconds of actually breathing did more than the last hour of holding it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
