/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 4-7: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS2_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent4)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Send one more message: just a single, staring emoji."
	)
	= choice PrintChoices(
		"You sent a long message to a parent, explaining how you actually feel. Marked read. Nothing since."
		"The Left-On-Read Text"
		290
		glitchText
		"Open your messages to literally anyone else and get absorbed in something safer." 0
		"Reread your message eleven times, hunting for the sentence that broke it." 1
		"Send nothing else. Let the silence belong to them, not you." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The read receipt updates. The silence gets a face now.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You changed the channel on your own heart.")
		)
		(case 1
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You performed an autopsy on a conversation that isn't dead yet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You stopped staring at a doorknob that isn't yours to turn.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent5)
	(var choice, glitchText)
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
)
/******************************************************************************/
(procedure public (HomeEvent6)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Hold a tiny, formal funeral for the leftovers."
	)
	= choice PrintChoices(
		"You made extra on purpose, left a note. It's still in the fridge, exactly where you left it."
		"The Leftover They Didn't Eat"
		290
		glitchText
		"Eat it yourself and say nothing about the note." 0
		"Leave it in there for three more days, unable to deal with it." 1
		"Ask, simply, if they want any leftovers before you eat the rest." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Several words were said. None of them were 'I'm sorry.'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You absorbed the disappointment along with the leftovers.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("The food and the feeling both went untouched.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A question, asked plainly, is not an accusation. Plus, you get dinner.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent7)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Slide a folded paper airplane of concern underneath it."
	)
	= choice PrintChoices(
		"It's usually open. Tonight it's closed, and you don't know why."
		"The Closed Bedroom Door"
		290
		glitchText
		"Stand outside it for a full minute, deciding nothing." 0
		"Knock and immediately apologize for whatever it is." 1
		"Knock. Ask if everything is okay." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It does not fly well on carpet. The gesture remains.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You waited outside a door you could have just knocked on.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You apologized before you knew the charge.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You offered an opening instead of an assumption.")
		)
		)
	)
)
/******************************************************************************/
