/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm280.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 14). Re-run
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
(script 280)
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
(instance public rm280 of Rm
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
			= glitchText "Produce an actual calculator and do the math out loud, in real time."
		)
		= choice PrintChoices(
			"You had a salad and water. Someone else had three cocktails. The bill's being split evenly, and everyone's already agreeing."
			"The Split-the-Bill Math"
			290
			glitchText
		"Agree to split evenly and say nothing about the math." 0
		"Suggest, casually, splitting it by what people actually got." 1
		"Pay your share silently and feel weird about it for days." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The table goes silent. You do not stop calculating.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You paid for cocktails you didn't order to keep the table easy.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You named the math out loud before it became a resentment. Three other people suddenly agree.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a bill you didn't agree with become a slow-burn grudge.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
