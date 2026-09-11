/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm342.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 10). Re-run
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
(script 342)
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
(instance public rm342 of Rm
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
			= glitchText "Self-medicate with four different remedies simultaneously."
		)
		= choice PrintChoices(
			"You've had the same low-grade cold for three weeks. It appeared right after things got hard and hasn't left since."
			"The Cold That Won't Quit"
			290
			glitchText
		"Power through, colds are for people with time off." 0
		"Blame the office AC loudly to anyone who'll listen." 1
		"Actually take the day, and actually rest on it." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are now buzzing gently and no less congested. A wash.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You outsourced your recovery to a future that keeps not arriving.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You found a villain. It wasn't the AC.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 15 TAG_SECURE)
			Print("You let your immune system have the meeting instead of you.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
