/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm323.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 24). Re-run
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
(script 323)
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
(instance public rm323 of Rm
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
			= glitchText "Turn on the radio and sing along badly through it."
		)
		= choice PrintChoices(
			"Nowhere dramatic. Just the car, in a parking lot, engine off, and the thing you've been holding all week finally lets go."
			"The Quiet Car Where You Finally Cry"
			290
			glitchText
		"Stop it fast. Fix your face. Go inside like nothing happened." 0
		"Apologize to no one in an empty car for crying at all." 1
		"Let it happen. Stay in the car until it passes on its own." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are now simultaneously crying and singing off-key. A rare skill.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You closed the door on something that had only just cracked open.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You apologized to an empty passenger seat. It was unmoved.")
		)
		(case 2
			ApplyChoiceEffects(-20 0 20 TAG_SECURE)
			Print("You let the parking lot hold something you'd been carrying all week.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
