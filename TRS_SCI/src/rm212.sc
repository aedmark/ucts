/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm212.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 12). Re-run
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
(script 212)
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
(instance public rm212 of Rm
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
			= glitchText "Start typing back before they've even sent anything."
		)
		= choice PrintChoices(
			"Your manager's typing indicator appears, disappears, appears again. Three times. Still no message."
			"The Typing Indicator"
			290
			glitchText
		"Stare at the little dots like they're a polygraph." 0
		"Close the tab so you can't watch it happen." 1
		"Keep working. Whatever it is will say itself eventually." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your dots meet their dots. A standoff of pure anticipation. Who will win? ...")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You interrogated punctuation that hadn't arrived yet.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -5 TAG_FLIGHT)
			Print("You removed the evidence, not the feeling.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let an unfinished sentence stay unfinished.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
