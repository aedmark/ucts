/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm351.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js (BODY event 19). Re-run
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
(script 351)
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
(instance public rm351 of Rm
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
			= glitchText "Punch a couch cushion with genuine, focused intent."
		)
		= choice PrintChoices(
			"You notice your fists have been closed, nails in your palms, for who knows how long."
			"The Clenched Fists"
			290
			glitchText
		"Unclench them fast and act like it never happened." 0
		"Squeeze harder, actually, see what that does." 1
		"Open them slowly and shake them out on purpose." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The cushion did not deserve this. The cushion will recover.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You hid the evidence and skipped the follow-up question.")
		)
		(case 1
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("You gave the anger a place to go. It chose your own hands. You have have broken some skin.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You released something before it decided to dig in further.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
