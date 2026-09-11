/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm210.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 10). Re-run
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
(script 210)
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
(instance public rm210 of Rm
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
			= glitchText "Reply-All to the entire company. Let everyone share in this learning experience."
		)
		= choice PrintChoices(
			"A coworker loops your manager into a thread about a mistake that was mostly, but not entirely, your fault."
			"The CC'd Boss"
			290
			glitchText
		"Immediately reply-all with a full, apologetic breakdown." 0
		"Reply-all clarifying, calmly, which parts were actually yours." 1
		"Leave the thread unanswered and refresh your inbox every four minutes." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Democracy, but for blame. Nobody wins but nobody loses, either. Except maybe some brain cells.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You built the case against yourself before anyone asked for one.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You drew a line around the blame instead of absorbing all of it.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You watched the thread instead of joining it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
