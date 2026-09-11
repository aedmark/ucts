/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm306.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 7). Re-run
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
(script 306)
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
(instance public rm306 of Rm
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
			= glitchText "Play it on full volume and have a small, unscheduled concert."
		)
		= choice PrintChoices(
			"Three seconds of a song you haven't heard in years, and your chest does something you didn't authorize."
			"The Song That Still Does This To You"
			290
			glitchText
		"Skip it immediately and pretend you didn't feel that." 0
		"Get irritated that a song still has this much power over you." 1
		"Let it play. Feel whatever it wants you to feel." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The neighbors have opinions. You have zero regrets.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You outran a feeling that was already three seconds ahead of you.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -5 TAG_FIGHT)
			Print("You picked a fight with your own nervous system. It won.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You let three minutes of sound be exactly as small as it actually was.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
