/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm354.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 22). Re-run
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
(script 354)
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
(instance public rm354 of Rm
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
			= glitchText "Learn to crochet and make everyone cozies for their mugs."
		)
		= choice PrintChoices(
			"Someone sets a mug down a little too hard and your entire body flinches like it's under attack."
			"The Full-Body Flinch"
			290
			glitchText
		"Laugh it off immediately, extra loud, extra fast." 0
		"Make an equally loud noise which is actually much louder and actually disruptive." 1
		"Let it be visible, no cover story required." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are the Andy Warhol of passive aggression.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You covered a real reaction with a performed one, instantly.")
		)
		(case 1
			ApplyChoiceEffects(-5 -15 0 TAG_FIGHT)
			Print("You made your nervous system's problem everyone else's problem.")
		)
		(case 2
			ApplyChoiceEffects(-5 -10 15 TAG_SECURE)
			Print("You let your body's honest reaction stand without editing it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
