/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm371.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 7). Re-run
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
(script 371)
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
(instance public rm371 of Rm
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
			= glitchText "Strike up a completely unnecessary full conversation for the rest of the hallway."
		)
		= choice PrintChoices(
			"They held the door. You said thanks. Now you're both walking the exact same direction down an empty hallway, well past the point where silence is normal."
			"The Person Who Held The Door"
			290
			glitchText
		"Suddenly develop an urgent need to check your phone until they're gone." 0
		"Keep walking in dead silence and hope the hallway ends soon." 1
		"Just laugh and say 'well, this is a long hallway.'" 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now know a stranger's opinion on parking garages. Neither of you asked for this, but someone needed it all the same.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You performed an emergency to escape a hallway. The emergency was silence.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("Neither of you said anything else. The hallway did, eventually, end.")
		)
		(case 2
			ApplyChoiceEffects(-8 5 5 TAG_SECURE)
			Print("You named the awkward thing out loud and it immediately stopped being awkward.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
