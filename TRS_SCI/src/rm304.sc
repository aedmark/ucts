/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm304.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js (SELF event 5). Re-run
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
(script 304)
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
(instance public rm304 of Rm
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
			= glitchText "Finish it in one unhinged, caffeinated burst, right now."
		)
		= choice PrintChoices(
			"A project, a hobby, a draft you were once genuinely excited about sits untouched in a folder you avoid opening."
			"The Unfinished Thing"
			290
			glitchText
		"Open the folder, look at it, close it again without touching anything." 0
		"Start something new and shinier instead." 1
		"Open it. Change one small thing. Close it again." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It is done. It is also, somehow, about cats now.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You visited the grave without bringing flowers or a shovel. Ingrate.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You outran the old excitement with a fresh one.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 15 TAG_SECURE)
			Print("You proved the thing wasn't actually dead, just resting.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
