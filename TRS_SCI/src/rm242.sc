/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm242.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js (HOME event 8). Re-run
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
(script 242)
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
(instance public rm242 of Rm
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
			= glitchText "Reply with a single dramatic movie-trailer voice line."
		)
		= choice PrintChoices(
			"Four words. No context. Sent an hour ago, and you've only just seen it."
			"The 'We Need To Talk' Text"
			290
			glitchText
		"Spend the hour composing worst-case scenarios instead of replying." 0
		"Reply with three apologies before you know what for." 1
		"Reply: 'Okay. I'm here when you're ready.'" 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'In a world where nobody explains anything...'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(25 0 -15 TAG_FREEZE)
			Print("You lived through several futures that hadn't happened yet.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You pled guilty to a charge that hadn't been read yet.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("You left room for the conversation instead of finishing it alone.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
