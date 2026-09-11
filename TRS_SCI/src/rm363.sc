/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm363.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 31). Re-run
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
(script 363)
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
(instance public rm363 of Rm
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
			= glitchText "Answer with a completely unrelated fact about your day instead."
		)
		= choice PrintChoices(
			"Someone asks if you're okay, and your throat closes around the answer before you can decide what it actually is."
			"The Tight Throat"
			290
			glitchText
		"Force out a bright 'I'm fine!' before the tightness can turn into anything else." 0
		"Say nothing and just nod until the moment passes on its own." 1
		"Say 'actually, not really' and let the sentence stop there for now." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody knows what just happened, including you. The moment is over, at least.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -10 TAG_FAWN)
			Print("You got the words out. They weren't the true ones. Nobody believes you.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The moment passed. The tightness didn't, not really.")
		)
		(case 2
			ApplyChoiceEffects(-10 3 10 TAG_SECURE)
			Print("Three honest words did more than a paragraph of fine would have.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
