/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm378.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 14). Re-run
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
(script 378)
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
(instance public rm378 of Rm
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
			= glitchText "Applaud, as if it were an intentional stunt."
		)
		= choice PrintChoices(
			"They went down hard on the sidewalk two steps ahead of you, and now there's a very short window to decide what kind of stranger you are."
			"The Person Who Fell In Front Of You"
			290
			glitchText
		"Rush over and apologize on their behalf before they've even said anything." 0
		"Freeze for a second too long, unsure if helping is your job here." 1
		"Ask if they're okay and help them up without making it a scene." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They did not take a bow. They did, eventually, laugh.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You absorbed a stranger's embarrassment for them before they'd even located it themselves.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("By the time you moved, someone else already had. You still feel the half-second of not moving.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You did the plain, obvious thing. It was enough.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
