/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm202.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 2). Re-run
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
(script 202)
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
(instance public rm202 of Rm
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
			= glitchText "Slide a note under the door reading only: 'I KNOW.'"
		)
		= choice PrintChoices(
			"You walk past a conference room where two managers are talking. They look at you and the door slowly closes."
			"The Closed Door"
			290
			glitchText
		"Begin packing up your desk mentally." 0
		"Work at 300% capacity for the next four hours." 1
		"Remind yourself you are an adult and this is fine." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You know nothing. The door remains closed. Deeply satisfying anyway.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -20 TAG_FLIGHT)
			Print("You braced for the worst-case scenario. Which was Nothing.")
		)
		(case 1
			ApplyChoiceEffects(10 20 -25 TAG_FAWN)
			Print("You overcompensated through labor. Capitalism wins.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 5 TAG_SECURE)
			Print("You chose logic. Your amygdala chose to ignore it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
