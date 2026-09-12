/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm251.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 17).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 251)
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
(instance public rm251 of Rm
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
			= glitchText "Build a small, dignified pillow fort in the empty space and watch Netflix in it."
		)
		= choice PrintChoices(
			"They're traveling this week. The apartment is exactly the same size and feels twice as big."
			"The Empty Side Of The Bed"
			290
			glitchText
		"Leave the TV on all night just to fill the quiet." 0
		"Text constant updates about your evening, needing the thread to stay busy." 1
		"Let the apartment be quiet. It's temporary." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The fort has excellent structural integrity and zero strategic purpose. ")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("You drowned a feeling in a sitcom rerun.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You kept the connection loud so it wouldn't feel absent.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Quiet turned out to be survivable, if not fun.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
