/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm279.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 13). Re-run
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
(script 279)
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
(instance public rm279 of Rm
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
			= glitchText "Commit fully: sprint over and hug a near-stranger."
		)
		= choice PrintChoices(
			"Someone you sort of know, from somewhere, makes eye contact across the room. Neither of you commits to a greeting."
			"The Awkward Wave"
			290
			glitchText
		"You suddenly find your phone extremely interesting." 0
		"Overcommit to a huge wave and walk over, unsure why." 1
		"Give a small, real wave and let it be whatever it is." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("This escalated several social contracts beyond what either of you agreed to. It feels right.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You disappeared into a screen to avoid making a decision.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You escalated a maybe into a whole interaction. It's awkward.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 5 TAG_SECURE)
			Print("A half-known person got a half-committed, perfectly adequate wave.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
