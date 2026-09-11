/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm385.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 21). Re-run
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
(script 385)
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
(instance public rm385 of Rm
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
			= glitchText "Start narrating the argument quietly to yourself like a nature documentary."
		)
		= choice PrintChoices(
			"Two strangers are having a very loud, very personal fight three feet from where you're standing, and there's nowhere to look that isn't at them."
			"The Argument You Overheard"
			290
			glitchText
		"Step in and try to smooth things over between two people you've never met." 0
		"Stand frozen, unsure whether moving will make it worse." 1
		"Quietly step away and let it be their business, not yours." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("One of them heard you. You have made an enemy and, somehow, also a fan.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You took responsibility for a stranger's conflict resolution. They didn't accept your help.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("You became furniture until the argument found somewhere else to be.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You gave two strangers the privacy of a fight that was never going to include you.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
