/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm337.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 5). Re-run
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
(script 337)
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
(instance public rm337 of Rm
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
			= glitchText "Book a massage you will cancel twice and never reschedule."
		)
		= choice PrintChoices(
			"Someone points out that you look really tense. You notice your shoulders are up to your ears."
			"The Shoulders"
			290
			glitchText
		"Laugh it off, drop them for exactly four seconds." 0
		"Get defensive about your own posture." 1
		"Actually roll them out and admit you're tensed up for no reason." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's the thought that counts. Your shoulders disagree.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -5 TAG_FAWN)
			Print("You roleplay as a relaxed person. Your shoulders forgot their lines immediately.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("Now everyone thinks you're hiding something... Or you think that they think you're hiding something. ")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You told the truth about your own body. Small, but real. Everyone else rolls their shoulders, too.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
