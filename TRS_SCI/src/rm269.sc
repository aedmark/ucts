/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm269.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js (SOCIAL event 3). Re-run
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
(script 269)
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
(instance public rm269 of Rm
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
			= glitchText "Comment demanding a formal recount of the likes."
		)
		= choice PrintChoices(
			"You've been tagged. The angle is unkind. Twelve people have already liked it."
			"The Group Photo"
			290
			glitchText
		"Untag yourself and message the poster to take it down." 0
		"Leave it up and never look at that post again." 1
		"Zoom in and catalog every flaw for later." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The recount is denied. Someone photoshops a powdered wig on your head in said photo. Democracy has failed you again.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -10 5 TAG_FIGHT)
			Print("You defended your own image. Feels illegal, but it isn't.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -10 TAG_FLIGHT)
			Print("You conceded to the internet, this round.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -20 TAG_FREEZE)
			Print("You built a case against your own face. You won... but also... lost?")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
