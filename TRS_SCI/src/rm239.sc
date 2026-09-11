/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm239.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js (HOME event 5). Re-run
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
(script 239)
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
(instance public rm239 of Rm
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
			= glitchText "Answer back in an equally strange, unexplained voice of your own."
		)
		= choice PrintChoices(
			"Your parent picks up sounding smaller than you remember. You don't know why yet."
			"The Different Voice on the Phone"
			290
			glitchText
		"Immediately go bright and cheerful to lift the mood before you've even asked what's wrong." 0
		"Ask, flatly, exactly what's going on. No preamble." 1
		"Say 'oh, okay' and let the conversation drift somewhere safer." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Neither of you addresses it. Some things stay sacred and weird.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You showed up as sunshine before you knew what kind of day it was.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 5 TAG_FIGHT)
			Print("You skipped the small talk. It cost you nothing you needed.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a real question evaporate into weather talk.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
