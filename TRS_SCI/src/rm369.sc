/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm369.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 5). Re-run
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
(script 369)
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
(instance public rm369 of Rm
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
			= glitchText "Decide this is a sign and order something you've never tried."
		)
		= choice PrintChoices(
			"The order that arrives is not, in any respect, the order you placed. Correcting it means being 'A Problem' in front of everyone in line."
			"The Wrong Order"
			290
			glitchText
		"Eat it anyway and never mention it to anyone." 0
		"Send it back loudly enough that the whole counter hears the complaint." 1
		"Quietly flag the mistake and ask for it to be corrected." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's fine. Not what you wanted. An acceptable plot twist regardless.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(8 5 -10 TAG_FAWN)
			Print("You absorbed someone else's mistake so nobody's day would get slightly harder.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("The order got fixed. So did everyone's opinion of you, briefly.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You asked for the right thing without turning it into a scene.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
