/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm231.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 31). Re-run
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
(script 231)
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
(instance public rm231 of Rm
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
			= glitchText "Frame the cancellation email and hang it somewhere you'll see it daily."
		)
		= choice PrintChoices(
			"Three months of work gets killed in a two-line message. 'Deprioritizing this for now.'"
			"The Project That Got Cancelled"
			290
			glitchText
		"Point out, at length, exactly how much time this wasted." 0
		"Reply 'Totally understand, happy to pivot!' before you've processed it at all." 1
		"Ask what, if anything, from the work can be reused elsewhere." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It is, unexpectedly, a little bit funny now.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-12 -15 0 TAG_FIGHT)
			Print("True and unhelpful, in roughly equal measure.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You agreed with a decision you haven't actually forgiven yet.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You looked for what survives instead of just what died.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
