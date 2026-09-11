/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm295.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 29). Re-run
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
(script 295)
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
(instance public rm295 of Rm
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
			= glitchText "Start giving them unsolicited advice back, immediately, at the same volume."
		)
		= choice PrintChoices(
			"You mention, in passing, that you're a little stressed. What you get back is a twelve-point plan for fixing your entire life."
			"The Unsolicited Advice"
			290
			glitchText
		"Nod, say 'that's a good point,' and absorb advice you didn't ask for." 0
		"Cut them off and say you weren't actually asking for a solution." 1
		"Say gently that you just wanted to vent, not fix it right now. But thank you." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Neither of you asked for this exchange. It is happening regardless.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You outsourced twelve minutes of your afternoon to someone else's certainty.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("True, and it landed like a slap instead of a boundary. And made your stress feel worse.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You named exactly what you needed instead of quietly enduring the wrong thing.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
