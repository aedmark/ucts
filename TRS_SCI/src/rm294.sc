/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm294.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 28). Re-run
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
(script 294)
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
(instance public rm294 of Rm
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
			= glitchText "Show up to the venue alone anyway, on principle."
		)
		= choice PrintChoices(
			"Twenty minutes before you were supposed to meet, the same friend cancels for the third time this month."
			"The Friend Who Cancelled Last Minute (Again)"
			290
			glitchText
		"Reply 'no worries at all!' and mean less than half of it." 0
		"Send a pointed text about the pattern, right then, while you're still annoyed." 1
		"Say it's fine tonight, and bring up the pattern later when you're not standing in your coat." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You had a fine time by yourself. This was not the point you were trying to prove.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -10 TAG_FAWN)
			Print("You absorbed the disappointment so smoothly nobody, including you, noticed it happened.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("Accurate. Badly timed. Both true at once.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You separated the moment from the pattern instead of dumping both at once.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
