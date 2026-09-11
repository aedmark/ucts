/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm211.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 11). Re-run
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
(script 211)
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
(instance public rm211 of Rm
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
			= glitchText "Stand up and take a long, silent bow."
		)
		= choice PrintChoices(
			"In the meeting, your manager describes your idea as something the team 'landed on together.' Nobody looks at you."
			"The Unclaimed Credit"
			290
			glitchText
		"Say nothing. Add it to the list you're keeping in your head." 0
		"Laugh it off and agree it really was a group effort." 1
		"Mention afterward, privately and plainly, that the idea was yours." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Three people clap before realizing they don't know why. Doesn't matter; validation is transferable but NOT refundable.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You filed it under evidence, case still open.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You co-signed the erasure to keep the room comfortable.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 15 TAG_SECURE)
			Print("You said the true thing to one person instead of nobody. Feel better?")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
