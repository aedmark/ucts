/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents5.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 18-21: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS5_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (WorkEvent18)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with the same screenshot, circled in red, underlined in red, and highlighted in red."
	)
	= choice PrintChoices(
		"Someone replies to your third follow-up with 'per my last email' and a screenshot of an answer that was not, in fact, an answer."
		"The 'Per My Last Email'"
		290
		glitchText
		"Reply quoting the exact unanswered question, again." 0
		"Apologize for 'missing' the answer that wasn't there." 1
		"Close the thread and decide to just figure it out yourself." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have made your point. Several points, actually. All very helpful.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -15 10 TAG_FIGHT)
			Print("You made the gap impossible to miss a second time. They answer the question.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You took the blame for someone else's incompetence.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You absorbed the extra work rather than the friction. Nobody learns anything.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent19)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Bring a tiny gift as tribute, like visiting a shrine."
	)
	= choice PrintChoices(
		"Your manager says 'my door is always open' during a meeting. Their door has, notably, never once been open."
		"The Open Door Policy"
		290
		glitchText
		"File it away as one more thing you won't actually bring up." 0
		"Nod like the sentence was true and useful." 1
		"Test it. Walk over and knock." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The door, astonishingly, opens. You are unprepared for this outcome.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You added a line to a list nobody's reading but you.")
		)
		(case 1
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You agreed with a door that stays shut.")
		)
		(case 2
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You checked the claim against the evidence. You get lunch together and have a good time.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Comment using only a single, deeply ambiguous emoji."
	)
	= choice PrintChoices(
		"A peer from two roles ago just posted 'excited to announce' a title you quietly wanted for yourself."
		"The LinkedIn Congrats"
		290
		glitchText
		"Like the post and write a warm, specific comment." 0
		"Close the app. Reopen it four minutes later." 1
		"Feel the envy, don't perform past it, close the app anyway." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Seventeen people have now liked your emoji. Nobody knows what it means. Least of all you.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You performed happiness at a volume you didn't feel.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You kept checking a wound to see if it still hurt.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let a small ugly feeling exist without narrating it to anyone or destroying you.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent21)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start a spontaneous printer-line trivia night to pass the time."
	)
	= choice PrintChoices(
		"You're fourth in line at the printer, running late, and the person ahead of you is scrolling their phone between pages."
		"The Printer Line"
		290
		glitchText
		"Stand there, saying nothing, doing the math on how late you'll be." 0
		"Ask, politely but directly, if you can jump in for one page." 1
		"Wait it out and tell yourself it's fine, it's fine, it's fine." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody knew this much about the printer. Nobody wanted to.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You calculated the cost instead of asking for the copy.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You asked for the small thing you actually needed. You got it without any dirty looks.")
		)
		(case 2
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You narrated a calm you weren't actually experiencing, accomplishing nothing.")
		)
		)
	)
)
/******************************************************************************/
