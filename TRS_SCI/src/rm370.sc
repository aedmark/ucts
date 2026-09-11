/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm370.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 6). Re-run
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
(script 370)
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
(instance public rm370 of Rm
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
			= glitchText "Answer with something completely unhinged just to see what happens."
		)
		= choice PrintChoices(
			"The facilitator goes around the circle. You have about ten seconds to decide what a room full of near-strangers gets to know about you."
			"The Icebreaker"
			290
			glitchText
		"Give the safest, most forgettable answer possible." 0
		"Go completely blank when it's your turn and stall for time." 1
		"Say something small but actually true instead of the safe version." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The circle is now unsettled. You have made an impression. Possibly a good one; it's too early to tell.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You disappeared into the icebreaker without anyone noticing you were even in it.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("Eight seconds of silence in front of strangers. You counted every one. So did they.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave the room slightly more of the real thing than it was expecting.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
