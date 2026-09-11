/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm206.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 6). Re-run
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
(script 206)
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
(instance public rm206 of Rm
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
			= glitchText "Lean in. Claim the typo was avant-garde on purpose."
		)
		= choice PrintChoices(
			"You meant to type something professional. Autocorrect had other plans. It has already been sent."
			"The Autocorrect Betrayal"
			290
			glitchText
		"Send six frantic follow-ups explaining what you meant." 0
		"Screenshot it, close the laptop, stare at the wall." 1
		"Let the typo be funny. It's kind of funny." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have accidentally invented a new art movement and the accountants are fond of you, now.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 10 -15 TAG_FAWN)
			Print("You buried the joke under a small avalanche of context and shame.")
		)
		(case 1
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You archived the evidence and fled the scene of a victimless crime.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You allowed yourself to be a person who makes typos. You survived.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
