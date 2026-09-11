/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm373.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 9). Re-run
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
(script 373)
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
(instance public rm373 of Rm
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
			= glitchText "Tell the bailiff you've actually always wanted to see how this works."
		)
		= choice PrintChoices(
			"You're in a room full of strangers being told that your normal lives are on hold for an unknown number of days."
			"The Jury Duty Summons"
			290
			glitchText
		"Raise your hand immediately to volunteer for anything that gets you excused." 0
		"Sit very still and hope your name simply never gets called." 1
		"Answer the questions honestly and let the process do what it does." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("This is, apparently, an unusual thing to say out loud. You are now Juror Number One.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You ran from an obligation using the exact process built to enforce it.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You made yourself as unnoticeable as a person in a numbered chair can be.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You told the truth to a room of strangers deciding your next two weeks. It felt strange, but fine.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
