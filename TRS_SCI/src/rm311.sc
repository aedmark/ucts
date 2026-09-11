/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm311.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 12). Re-run
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
(script 311)
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
(instance public rm311 of Rm
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
			= glitchText "Lean all the way into it."
		)
		= choice PrintChoices(
			"You hear the sentence leave your mouth and recognize it a half-second too late. It's not yours. It's theirs."
			"The Thing You Said You'd Never Become"
			290
			glitchText
		"Spiral into a full self-indictment for the next hour." 0
		"Laugh it off in the moment and never think about it again... You swear!" 1
		"Notice it. Decide, calmly, that noticing is the first step, not a failure." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have never committed to a bit this hard. Everyone is a little worried about you.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -20 TAG_FIGHT)
			Print("You sentenced yourself over one inherited sentence.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You buried it under a laugh instead of a look.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught the echo without deciding it was proof of anything permanent.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
