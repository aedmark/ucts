/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm320.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 21). Re-run
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
(script 320)
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
(instance public rm320 of Rm
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
			= glitchText "Apologize to an inanimate object instead. With real emotion."
		)
		= choice PrintChoices(
			"Someone bumps into you. You apologize. To them. For being bumped into."
			"The Reflex To Apologize For Existing"
			290
			glitchText
		"Apologize again, just to be safe." 0
		"Notice it happened and feel weird about it for the rest of the day." 1
		"Notice the reflex. Don't perform an apology for the apology." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The doorknob accepts your apology graciously. It has no other choice.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You doubled down on an apology that was never yours to give.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a two-second reflex become an all-day mood.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught the reflex mid-air and let it just pass through.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
