/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm237.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js (HOME event 3). Re-run
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
(script 237)
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
(instance public rm237 of Rm
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
			= glitchText "Start narrating the drive like a hushed golf commentator."
		)
		= choice PrintChoices(
			"Twenty minutes home. Nobody has said anything since you left. You are replaying the entire evening."
			"The Silent Car Ride"
			290
			glitchText
		"Turn the radio up to fill the space." 0
		"Ask 'you okay?' four separate times." 1
		"Sit in it. Actually just sit in it." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'And she signals... she signals early. Remarkable composure. Will she return her hands to ten and two? Stay tuned to find out...'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You outsourced the silence to a pop song.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You checked the temperature of a room that was comfortable, not cold.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("Twenty minutes of quiet did not, in fact, kill you.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
