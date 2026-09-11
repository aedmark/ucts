/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm312.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 13). Re-run
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
(script 312)
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
(instance public rm312 of Rm
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
			= glitchText "Write your own unhinged, deeply specific counter-list right now."
		)
		= choice PrintChoices(
			"You're scrolling through someone else's milestones; house, promotion, wedding, etc.  You keep checking your own life against it."
			"The List of Things You're Supposed To Want"
			290
			glitchText
		"Keep scrolling, keep comparing, feel worse with each one." 0
		"Convince yourself you want all of it too, just to feel aligned." 1
		"Close the app. Ask yourself, honestly, what you actually want." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Item four is just 'made a really good sandwich.' You stand by it.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You audited your life against an idealized list you didn't write. Of course you fell short.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -15 TAG_FAWN)
			Print("You borrowed someone else's wants because yours felt too quiet to trust.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You checked the list against your own name instead of theirs.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
