/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm219.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 19). Re-run
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
(script 219)
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
(instance public rm219 of Rm
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
			= glitchText "Bring a tiny gift as tribute, like visiting a shrine."
		)
		= choice PrintChoices(
			"Your manager says 'my door is always open' during a meeting. Their door has, notably, never once been open."
			"The Open Door Policy"
			290
			glitchText
		"File it away as one more thing you won't actually bring up." 0
		"Nod like the sentence was true and useful." 1
		"Test it. Walk over and knock." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The door, astonishingly, opens. You are unprepared for this outcome.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You added a line to a list nobody's reading but you.")
		)
		(case 1
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You agreed with a door that stays shut.")
		)
		(case 2
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You checked the claim against the evidence. You get lunch together and have a good time.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
