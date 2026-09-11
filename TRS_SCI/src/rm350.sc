/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm350.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 18). Re-run
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
(script 350)
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
(instance public rm350 of Rm
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
			= glitchText "Do twenty jumping jacks in a supply closet to 'reset your energy.'"
		)
		= choice PrintChoices(
			"Your energy leaves your body like water out of a bathtub, all at once."
			"The Crash"
			290
			glitchText
		"Chug an energy drink and pretend the crash isn't happening." 0
		"Push through on pure spite and bad posture." 1
		"Take five real minutes doing nothing." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are now tired in a completely new and different way. Innovative.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You borrowed energy from tonight-you, who was not consulted and does not appreciate it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You ran the tank to empty and called it discipline.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let the crash happen instead of arguing with it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
