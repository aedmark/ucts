/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm214.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 14). Re-run
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
(script 214)
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
(instance public rm214 of Rm
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
			= glitchText "Challenge them to a desk-chair race down the hallway."
		)
		= choice PrintChoices(
			"Someone who started three weeks ago just solved, casually, the thing that's been quietly humiliating you for a month."
			"The New Hire Who's Already Better At This"
			290
			glitchText
		"Smile, say 'nice,' and mentally recalculate your entire worth." 0
		"Ask them to walk you through it, overpraising every step." 1
		"Ask them to walk you through it. Just that." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You lose. You also nearly take out a filing cabinet. It's a bonding experience.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -20 TAG_FREEZE)
			Print("You ran a full audit off one data point. Maybe they can't do math?")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You turned learning into a small performance of gratitude.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 15 TAG_SECURE)
			Print("You let not-knowing be a normal thing.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
