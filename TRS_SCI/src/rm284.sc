/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm284.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 18). Re-run
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
(script 284)
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
(instance public rm284 of Rm
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
			= glitchText "Stand up and propose your own toast, entirely about yourself."
		)
		= choice PrintChoices(
			"A long, warm speech naming almost everyone important in the room. Almost."
			"The Toast You Weren't Mentioned In"
			290
			glitchText
		"Smile and clap while quietly re-ranking your own importance." 0
		"Compliment the speech extra hard afterward." 1
		"Let one omission be one omission, not a verdict." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It runs nine minutes. The room claps out of confusion, mostly.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("A toast became a scoreboard, and you lost.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You praised loudest the thing that left you out.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You weren't named. You were, notably, still there.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
