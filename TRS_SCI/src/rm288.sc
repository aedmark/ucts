/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm288.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 22). Re-run
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
(script 288)
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
(instance public rm288 of Rm
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
			= glitchText "Sit on the floor with great, theatrical dignity instead."
		)
		= choice PrintChoices(
			"You go to sit down. 'Oh, that one's taken,' said kindly, but you're now standing in a room full of seated people."
			"The Seat Saved For Someone Else"
			290
			glitchText
		"Laugh it off and hover near the wall instead." 0
		"Stand there a beat too long, unsure what to do with your body." 1
		"Ask, simply, if there's another seat open." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The floor, it turns out, has an excellent view.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You made your own displacement look easy.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("A missing chair became a small, public crisis.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You solved a chair problem with a chair question.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
