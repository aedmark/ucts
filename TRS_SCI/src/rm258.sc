/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm258.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js (HOME event 24). Re-run
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
(script 258)
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
(instance public rm258 of Rm
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
			= glitchText "Start browsing apartments you can't afford, in a city you don't live in."
		)
		= choice PrintChoices(
			"An envelope from the landlord. The number at the bottom is $200 more than last year, effective next month."
			"The Rent Increase"
			290
			glitchText
		"Set the letter on the counter and don't look at the actual number again for a week." 0
		"Draft a furious response citing every unfixed thing in the apartment." 1
		"Sit down and actually rework the budget around the new number." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You've mentally moved to a lake house. Your higher rent is still due next month.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -8 TAG_FREEZE)
			Print("The number didn't go away. It just waited.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("All true. None of it will lower the rent. Or your blood pressure.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You made the number smaller by making it real instead of avoided.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
