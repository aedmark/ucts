/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm349.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 17). Re-run
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
(script 349)
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
(instance public rm349 of Rm
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
			= glitchText "Set six hourly water alarms you will immediately start ignoring."
		)
		= choice PrintChoices(
			"You realize the water bottle on your desk has been full since yesterday morning. You feel vaguely awful and can't say why."
			"The Forgotten Water Bottle"
			290
			glitchText
		"Drink coffee instead, that's basically water." 0
		"Feel guilty about it and do nothing differently." 1
		"Drink the whole thing right now, slowly." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your phone now nags you about hydration. You remain thirsty.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You addressed thirst with a substance that specializes in the opposite.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized to your own body and changed nothing.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You met an actual need instead of a performed one.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
