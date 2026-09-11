/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm245.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js (HOME event 11). Re-run
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
(script 245)
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
(instance public rm245 of Rm
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
			= glitchText "Launch a full forensic investigation, complete with a labeled evidence board."
		)
		= choice PrintChoices(
			"The thing you were saving for tomorrow is gone. No note, no explanation."
			"The Empty Fridge Shelf"
			290
			glitchText
		"Ask, directly, who ate it." 0
		"Say nothing and quietly recalculate your whole week's meals." 1
		"Decide it's fine, you didn't really need it anyway." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have connected several pieces of red string to absolutely nothing helpful.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You asked the small, direct question instead of stewing.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("One missing item rearranged your entire life, silently.")
		)
		(case 2
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You talked yourself out of a want that was real, and into being a doormat.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
