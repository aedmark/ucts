/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm303.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 4). Re-run
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
(script 303)
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
(instance public rm303 of Rm
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
			= glitchText "Declare it a national holiday, just for yourself, effective immediately."
		)
		= choice PrintChoices(
			"Nothing is scheduled. Nobody needs anything from you. This is, somehow, the hardest part of the week."
			"The Empty Sunday"
			290
			glitchText
		"Invent an urgent task to feel useful again." 0
		"Scroll until the day disappears without you in it." 1
		"Sit with the unscheduled hour and let it be boring." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Attendance is mandatory. You are the only attendee.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You manufactured a purpose to avoid the quiet.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -15 TAG_FLIGHT)
			Print("You outsourced eight hours to a feed.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 15 TAG_SECURE)
			Print("You survived free time without earning it first. You didn't explode.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
