/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm327.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 28). Re-run
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
(script 327)
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
(instance public rm327 of Rm
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
			= glitchText "Look up someone from high school you haven't thought about in a decade instead."
		)
		= choice PrintChoices(
			"There's one person (you know exactly who) whose life you measure your own against, always unfavorably, without ever really deciding to."
			"The Specific Person You Compare Yourself To"
			290
			glitchText
		"Check what they're up to again, even though it never actually helps." 0
		"Congratulate their latest thing extra warmly to prove to yourself you're not bitter." 1
		"Notice you're comparing your whole self to their highlight reel, and put the scale down." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("New person to compare yourself to. Same old habit, different target.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("Same result as every other time. You checked anyway.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("The warmth was mostly for your own benefit, not theirs.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You stopped measuring two different things with the same ruler.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
