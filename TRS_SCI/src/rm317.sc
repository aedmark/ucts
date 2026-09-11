/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm317.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 18). Re-run
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
(script 317)
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
(instance public rm317 of Rm
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
			= glitchText "Call them right now and just say it, all of it, fast."
		)
		= choice PrintChoices(
			"The words were right there. You had the opening. You changed the subject instead, and you're still thinking about it hours later."
			"The Time You Almost Told Someone"
			290
			glitchText
		"Replay the moment, cataloging exactly why you didn't say it." 0
		"Decide it's better this way for everyone. Probably." 1
		"Note that the opening will come again. It's not your only chance." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's out. The world, remarkably, keeps turning.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You held a post-mortem for a conversation that never happened.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You reframed silence as consideration for other people.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You released the pressure of a single missed moment.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
