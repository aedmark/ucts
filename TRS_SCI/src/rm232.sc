/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm232.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 32). Re-run
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
(script 232)
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
(instance public rm232 of Rm
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
			= glitchText "Overshare something of your own in return, just to even the score."
		)
		= choice PrintChoices(
			"Someone uses your two-minute standup slot to tell the whole team about their divorce."
			"The Oversharing Coworker"
			290
			glitchText
		"Nod along and offer sympathetic follow-up questions you don't have time for." 0
		"Suddenly remember an urgent message you have to go check." 1
		"Say gently that you hope they're okay, and steer the meeting back on track." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The meeting is now forty minutes long and everyone knows too much about the both of you.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You gave away ten minutes you didn't have to a conversation you didn't start.")
		)
		(case 1
			ApplyChoiceEffects(-5 -8 0 TAG_FLIGHT)
			Print("You escaped clean. The next person in the meeting was not so lucky.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You were kind and still kept the meeting alive. Both things, at once. Everyone feels lighter now.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
