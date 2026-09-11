/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm316.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 17). Re-run
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
(script 316)
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
(instance public rm316 of Rm
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
			= glitchText "Chase them down the street to actually answer honestly."
		)
		= choice PrintChoices(
			"'Are you doing okay, actually?' - from someone who barely knows you, at exactly the wrong-yet-right moment."
			"The Question A Stranger Asked That Undid You"
			290
			glitchText
		"Say 'I'm fine!' faster than the question finished." 0
		"Deflect and change the subject immediately." 1
		"Pause. Tell a small piece of the truth." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They were already gone. The honesty remains, unclaimed, in the middle of the sidewalk.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You answered before you let yourself hear the question.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You closed a door a stranger had, kindly, tried to open.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("A stranger's question got an honest, small answer. Mutual respect intensifies.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
