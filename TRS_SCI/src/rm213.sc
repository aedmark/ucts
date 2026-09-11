/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm213.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 13). Re-run
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
(script 213)
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
(instance public rm213 of Rm
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
			= glitchText "Set your own auto-reply to something ominous and cryptic."
		)
		= choice PrintChoices(
			"You email a colleague something urgent. The auto-reply says they've been out since yesterday. Nobody told you."
			"The Out-of-Office Reply"
			290
			glitchText
		"Apologize to their inbox for bothering them at all." 0
		"Send a slightly sharp message to whoever should have flagged this." 1
		"Sit with the urgent thing, now un-urgent, doing nothing." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It now reads: 'I am also out. Of my mind. Please check back later.'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You apologized to an away message.")
		)
		(case 1
			ApplyChoiceEffects(-5 -15 5 TAG_FIGHT)
			Print("You aimed the frustration at the actual gap, not yourself.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("The fire kept burning with nobody assigned to it. Insurance won't cover it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
