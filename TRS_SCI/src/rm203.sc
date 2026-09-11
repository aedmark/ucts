/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm203.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 3). Re-run
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
(script 203)
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
(instance public rm203 of Rm
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
			= glitchText "Reply-all a third time, this one entirely in Comic Sans and indecipherable."
		)
		= choice PrintChoices(
			"You meant to reply privately with a joke about the meeting length. You did not reply privately."
			"The Reply-All"
			290
			glitchText
		"Immediately close your laptop and compose a new identity." 0
		"Send a follow-up message that says 'ignore that. lol.'" 1
		"Own it. Reply-all again with an article about workplace inefficiency.'" 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Comic Sans has never been used with such menace.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 -20 -10 TAG_FLIGHT)
			Print("You attempted to physically escape the timeline. There is no escape.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You tried to laugh-track your way out of consequences. Hopefully it worked...")
		)
		(case 2
			ApplyChoiceEffects(-10 -15 15 TAG_FIGHT)
			Print("You chose chaos over shame. Bold move with your charisma levels.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
