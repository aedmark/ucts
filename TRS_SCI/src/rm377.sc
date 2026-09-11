/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm377.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 13). Re-run
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
(script 377)
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
(instance public rm377 of Rm
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
			= glitchText "Offer to hold the baby. You have never held a baby."
		)
		= choice PrintChoices(
			"Row fourteen has been going for twenty minutes. You can feel the whole cabin's patience thinning at the same rate as yours."
			"The Crying Baby On The Plane"
			290
			glitchText
		"Shoot the parents a series of sympathetic smiles you don't actually feel." 0
		"Sigh loudly enough that row fourteen is guaranteed to hear it." 1
		"Put in earplugs and let it be someone else's hard day, not a referendum on yours." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The baby, astonishingly, stopped crying. You are now afraid to move for the rest of the flight.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You performed patience at a stranger so they wouldn't feel what the whole cabin was actually feeling.")
		)
		(case 1
			ApplyChoiceEffects(-8 -10 0 TAG_FIGHT)
			Print("You made your irritation a cabin announcement. The baby was unmoved. The parents were not.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave a stranger's rough flight the space to just be theirs.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
