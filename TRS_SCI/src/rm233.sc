/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm233.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 33). Re-run
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
(script 233)
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
(instance public rm233 of Rm
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
			= glitchText "Reply-all asking everyone else what they got."
		)
		= choice PrintChoices(
			"The open enrollment email arrives. This year's raise, after inflation, comes out to functionally nothing."
			"The Raise That Wasn't"
			290
			glitchText
		"Do the math four different ways hoping for a different number." 0
		"Thank your manager for the raise in the team channel anyway." 1
		"Note the real number down and start pricing out what a market-rate offer looks like." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody answers. Somehow the silence answers anyway.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The number stayed the same across every method. Math is like that.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You said thank you for something that, functionally, cost you more than just money.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You stopped waiting for the number to feel different and used it instead.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
