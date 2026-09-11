/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm289.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 23). Re-run
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
(script 289)
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
(instance public rm289 of Rm
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
			= glitchText "Comment on every photo with a single, ominous 'interesting.'"
		)
		= choice PrintChoices(
			"The photos are already up. It looks like it was a good one. You didn't know it was happening."
			"The Group Trip You Weren't Invited To"
			290
			glitchText
		"Scroll through every photo, cataloging who's in how many." 0
		"Like every photo enthusiastically, extra hearts included." 1
		"Close the app. Ask yourself later, calmly, if it's worth mentioning." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody knows what you meant. You are not entirely sure either. Regardless, it IS interesting.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You turned a vacation you weren't on into forensic evidence.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You applauded a trip that stung to see.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the feeling time before deciding what to do with it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
