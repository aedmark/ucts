/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm331.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 32). Re-run
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
(script 331)
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
(instance public rm331 of Rm
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
			= glitchText "Buy all the equipment for it and start absolutely nothing else."
		)
		= choice PrintChoices(
			"There's something you've wanted to start for a long time. You're still waiting to feel ready, and the feeling hasn't come."
			"The Thing You're Waiting to Feel Ready For"
			290
			glitchText
		"Keep waiting for the readiness to arrive on its own schedule." 0
		"Tell people you're 'about to start soon' again, to keep the pressure off." 1
		"Do one small piece of it today, ready or not." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now own everything you need and have used none of it. A familiar shape.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("It didn't arrive. It rarely does on its own.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You bought yourself another round of not starting, at the cost of a small lie.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Turns out readiness was never actually the requirement.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
