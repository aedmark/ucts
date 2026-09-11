/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm209.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 9). Re-run
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
(script 209)
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
(instance public rm209 of Rm
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
			= glitchText "Reply with a photo of an actual empty desk, for evidence."
		)
		= choice PrintChoices(
			"You set your status to 'in a meeting' twenty minutes ago and never actually joined one. Someone just pinged you directly."
			"The Away Message"
			290
			glitchText
		"Type 'sorry, just wrapped up, what's up!' like it's true." 0
		"Let the message sit unread for another eleven minutes." 1
		"Reply honestly: 'Just saw this, give me a minute.'" 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The desk is, notably, yours. This raises more questions than it answers. You're okay with this.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You performed availability you didn't have.")
		)
		(case 1
			ApplyChoiceEffects(15 -5 0 TAG_FLIGHT)
			Print("You bought time you'll have to pay back later.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You told a true, small, unremarkable thing.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
