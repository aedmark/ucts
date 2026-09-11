/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm255.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js (HOME event 21). Re-run
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
(script 255)
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
(instance public rm255 of Rm
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
			= glitchText "Leap up and greet them in full detective-noir monologue."
		)
		= choice PrintChoices(
			"Someone's home later than expected. You're awake now, doing math you don't want to be doing."
			"The Sound Of The Garage Door At 2 AM"
			290
			glitchText
		"Lie perfectly still, pretending to be asleep, wide awake." 0
		"Get up and greet them cheerfully, hiding that you were worried." 1
		"Ask in the morning if everything's okay." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'You've got some explaining to do! At 2 AM! ...In this economy!'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You performed sleep you weren't getting.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You covered the worry with a smile at 2 AM.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You waited for daylight and asked a plain question. They just lost track of time.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
