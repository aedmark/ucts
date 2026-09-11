/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm267.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 1). Re-run
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
(script 267)
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
(instance public rm267 of Rm
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
			= glitchText "Reply with a single, deeply dramatic tear emoji."
		)
		= choice PrintChoices(
			"A friend replied 'K.' to a vulnerable paragraph you sent them."
			"The Unread Notification"
			290
			glitchText
		"Start searching for a completely new friend group." 0
		"Send 4 memes immediately to lighten the mood." 1
		"Throw your phone into a soft pile of laundry." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They have not responded. The emoji stands alone, weeping into the void.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 -15 -15 TAG_FIGHT)
			Print("You burned the bridge in your mind before there was even a threat to identify.")
		)
		(case 1
			ApplyChoiceEffects(10 10 -10 TAG_FAWN)
			Print("You performed as the Jester to avoid abandonment. They didn't react. You internalize it deeply.")
		)
		(case 2
			ApplyChoiceEffects(10 0 10 TAG_FLIGHT)
			Print("You successfully removed the object of your pain. Until you get up again.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
