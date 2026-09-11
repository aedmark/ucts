/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm201.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 1). Re-run
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
(script 201)
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
(instance public rm201 of Rm
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
			= glitchText "Announce, loudly, that you accept this compliment on behalf of all of humanity."
		)
		= choice PrintChoices(
			"A coworker tells you that you did a 'really great job' on the presentation. It sounds sincere."
			"The Compliment"
			290
			glitchText
		"Deflect immediately and credit the team." 0
		"Assume they are setting you up for a massive failure later and prepare a defense." 1
		"Say 'Thank you' and let the discomfort burn your throat." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Someone starts a slow clap. It does not catch on.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -10 TAG_FAWN)
			Print("You physically swatted away the affection. Idiot.")
		)
		(case 1
			ApplyChoiceEffects(15 -5 -15 TAG_FIGHT)
			Print("You chose paranoia over pride. Hooray?")
		)
		(case 2
			ApplyChoiceEffects(-10 -10 15 TAG_SECURE)
			Print("You accepted love. It hurt less than you feared.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
