/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm395.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js (PUBLIC event 31). Re-run
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
(script 395)
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
(instance public rm395 of Rm
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
			= glitchText "Take a bow for the sneeze, like it was the whole point of coming today."
		)
		= choice PrintChoices(
			"It happens right in the middle of a silence you did not create and loud enough that it may as well have been an announcement."
			"The Sneeze In The Quiet Room"
			290
			glitchText
		"Apologize three separate times to three separate directions." 0
		"Freeze completely and hope everyone just forgets it happened." 1
		"Say a quiet 'excuse me' and let the room move on, because it will." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Someone actually said 'bless you' with real enthusiasm. A win, of sorts, for the sneeze.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized for a biological reflex like it was a breach of contract.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -3 TAG_FREEZE)
			Print("You went very still, as if stillness now could undo a sneeze from three seconds ago.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You let a small, human noise be exactly that small.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
