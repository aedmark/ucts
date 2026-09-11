/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm218.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 18). Re-run
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
(script 218)
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
(instance public rm218 of Rm
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
			= glitchText "Reply with the same screenshot, circled in red, underlined in red, and highlighted in red."
		)
		= choice PrintChoices(
			"Someone replies to your third follow-up with 'per my last email' and a screenshot of an answer that was not, in fact, an answer."
			"The 'Per My Last Email'"
			290
			glitchText
		"Reply quoting the exact unanswered question, again." 0
		"Apologize for 'missing' the answer that wasn't there." 1
		"Close the thread and decide to just figure it out yourself." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have made your point. Several points, actually. All very helpful.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -15 10 TAG_FIGHT)
			Print("You made the gap impossible to miss a second time. They answer the question.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You took the blame for someone else's incompetence.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You absorbed the extra work rather than the friction. Nobody learns anything.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
