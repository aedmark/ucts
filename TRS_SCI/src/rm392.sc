/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm392.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 28). Re-run
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
(script 392)
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
(instance public rm392 of Rm
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
			= glitchText "Ask the room, out loud, if anyone else wants to talk about what's on the TV."
		)
		= choice PrintChoices(
			"The TV is playing something no one chose, at a volume no one agreed to, and everyone is very carefully not looking at each other."
			"The Waiting Room"
			290
			glitchText
		"Smile blandly at anyone who glances your way, just in case." 0
		"Stare at your phone so hard you could probably describe none of what's on it later." 1
		"Just sit there, bored, and let the boredom be boring." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("One person, astonishingly, did. You now have opinions about a show you weren't watching an hour ago.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You maintained a friendly face for a room full of people who were not looking at you closely enough to notice.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("You scrolled through a phone you weren't actually reading for the better part of an hour.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You let a waiting room be exactly as uneventful as it actually was.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
