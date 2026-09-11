/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm341.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 9). Re-run
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
(script 341)
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
(instance public rm341 of Rm
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
			= glitchText "Turn the bouncing into a full drum solo on the desk."
		)
		= choice PrintChoices(
			"Your leg has been bouncing under the desk for forty-five minutes. You only notice when someone asks if you're okay."
			"The Restless Leg"
			290
			glitchText
		"Say you're fine, laugh, keep bouncing." 0
		"Get up and pace the hallway instead." 1
		"Name it: 'I think I'm anxious about something.'" 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Everyone in the room now knows exactly how you feel. Loudly.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You answered the question the body was already answering.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 0 TAG_FLIGHT)
			Print("You gave the energy somewhere bigger to go.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let the leg tell on you, and believed it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
