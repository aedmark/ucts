/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm300.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 1). Re-run
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
(script 300)
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
(instance public rm300 of Rm
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
			= glitchText "Introduce yourself to the reflection like you've never met."
		)
		= choice PrintChoices(
			"You catch your reflection in a dark window and don't recognize yourself for a full second."
			"The Mirror"
			290
			glitchText
		"Immediately look away and think about anything else." 0
		"Stare longer, looking for evidence of who you used to be." 1
		"Say something kind to it, out loud, even though it's weird." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'Hi, I'm you.' The reflection does not seem convinced either.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You declined an invitation from your own face.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You interrogated a witness who can't testify.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 15 TAG_SECURE)
			Print("You addressed yourself like a person worth addressing. Because you are.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
