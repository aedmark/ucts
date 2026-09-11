/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm222.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 22). Re-run
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
(script 222)
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
(instance public rm222 of Rm
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
			= glitchText "Take a bow for the growing audience behind you."
		)
		= choice PrintChoices(
			"Your badge doesn't scan. Three times in a row. There's a line building behind you now."
			"The Badge Scan Fail"
			290
			glitchText
		"Apologize to everyone behind you individually." 0
		"Freeze up completely, badge in hand, brain empty except for the intense panic that you've been fired." 1
		"Step aside, let people pass, try again without an audience." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Someone starts filming. This will outlive your employment here.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You issued apologies for a malfunctioning badge reader.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("The door and your nervous system both stopped responding.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You removed the audience instead of performing through it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
