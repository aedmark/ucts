/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm282.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 16). Re-run
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
(script 282)
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
(instance public rm282 of Rm
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
			= glitchText "Deliver an unhinged, overly dramatic weather forecast, mid-conversation."
		)
		= choice PrintChoices(
			"Third conversation this week that's stayed entirely on the weather. You're both clearly capable of more."
			"The Small Talk About The Weather (Again)"
			290
			glitchText
		"Keep it light and safe, matching their energy exactly." 0
		"Ask a real question and see what happens." 1
		"Let the conversation end there, like it always does." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'Sixty percent chance of feelings, with scattered vulnerability by evening.'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You stayed in the shallow end because it felt safer there.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You risked it all on a real question. You get a real answer (and a new friend).")
		)
		(case 2
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("Another conversation stayed exactly as deep as the last one. This is fine.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
