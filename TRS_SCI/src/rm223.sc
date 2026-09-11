/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm223.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js (WORK event 23). Re-run
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
(script 223)
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
(instance public rm223 of Rm
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
			= glitchText "Reply fully in character as the other person."
		)
		= choice PrintChoices(
			"You get an email addressed to someone else's name, clearly copy-pasted from a different, more glowing thread."
			"The Wrong Name in the Email"
			290
			glitchText
		"Assume you're actually being compared unfavorably to that person." 0
		"Reply, lightly, pointing out the name mismatch." 1
		"Ignore it and answer as if it were addressed to you correctly." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are now, professionally speaking, someone else. It's going well.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You built a rivalry with someone who doesn't know you exist.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You named the small error instead of absorbing a large story about it.")
		)
		(case 2
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You let the mistake pass to keep things smooth.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
