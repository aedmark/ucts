/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm374.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 10). Re-run
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
(script 374)
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
(instance public rm374 of Rm
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
			= glitchText "Ask the driver a series of increasingly personal questions right back."
		)
		= choice PrintChoices(
			"The driver wants to talk. You have eighteen minutes left and a headache forming right behind your left eye."
			"The Rideshare Small Talk"
			290
			glitchText
		"Answer every question with enthusiasm you do not currently possess." 0
		"Put in headphones without actually playing anything." 1
		"Say, kindly, that you're wiped and would rather ride quiet." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now know more about their custody arrangement than the last three passengers combined.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed 'Friendly Passenger' for eighteen minutes straight through a headache.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 3 TAG_FLIGHT)
			Print("You faked a soundtrack to buy yourself a real silence.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You asked for what you needed from a stranger you'll know for eighteen more minutes.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
