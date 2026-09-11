/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm204.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 4). Re-run
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
(script 204)
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
(instance public rm204 of Rm
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
			= glitchText "Show up in full riot gear, 'just in case.'"
		)
		= choice PrintChoices(
			"'Quick Chat' with your manager. No agenda. Fifteen minutes from now."
			"The Calendar Invite"
			290
			glitchText
		"Spend the fifteen minutes drafting a resignation letter." 0
		"Ask a coworker if they've heard anything. They haven't." 1
		"Walk in assuming it's fine." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It was your annual review you forgot about. You are overdressed and underprepared.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(25 0 -15 TAG_FLIGHT)
			Print("You pre-grieved a job you still have.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -5 TAG_FAWN)
			Print("You outsourced your anxiety to someone equally unequipped.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("It was never about you. It was about the parking lot construction.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
