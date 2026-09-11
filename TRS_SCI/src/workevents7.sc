/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents7.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 26-29: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS7_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (WorkEvent26)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask your manager to list, out loud, every mistake you've made this quarter."
	)
	= choice PrintChoices(
		"Your manager says the presentation went great. You spend the next hour building a case for why she's wrong."
		"The Imposter Spiral"
		290
		glitchText
		"Deflect the compliment immediately and list everyone else who helped." 0
		"Reread your own slides looking for the mistake everyone's too polite to mention." 1
		"Say 'thank you' and let it be true." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("She could only think of one. You are unreasonably disappointed. She now thinks you're hiding something.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You gave the credit away before anyone could take a closer look at you.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("You found four typos and no evidence of the disaster you were sure was coming.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You accepted a good thing without an asterisk on it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent27)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply-all to the rumor thread with a single question mark."
	)
	= choice PrintChoices(
		"A screenshot from an anonymous coworker forum is going around. It names your department. Nobody official has said a word."
		"The Layoff Rumor"
		290
		glitchText
		"Refresh your email every four minutes for the rest of the day." 0
		"Close every work tab and watch something mindless until it's time to log off." 1
		"Update your resume calmly, then close the laptop for the night." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Forty people saw it. Nobody answered. You have created a new, smaller panic.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -5 TAG_FREEZE)
			Print("You got nothing but a calendar reminder and a headache.")
		)
		(case 1
			ApplyChoiceEffects(-8 -8 0 TAG_FLIGHT)
			Print("You bought yourself a few hours of not knowing, on purpose.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You prepared for the worst without living inside it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent28)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Casually ask three more coworkers what they make."
	)
	= choice PrintChoices(
		"A spreadsheet gets shared by accident. Someone with two fewer years than you makes more."
		"The Salary You Found Out"
		290
		glitchText
		"Draft a furious email to HR and send it before you can think twice." 0
		"Decide you're probably not remembering your own worth correctly, and let it go." 1
		"Write down exactly what you'd ask for and schedule the actual conversation." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now know everyone's salary, yet still cannot grasp your own self-worth. Progress?")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-15 -15 0 TAG_FIGHT)
			Print("It's out there now. There is no version of tomorrow's meeting that isn't about this.")
		)
		(case 1
			ApplyChoiceEffects(8 5 -12 TAG_FAWN)
			Print("You talked yourself out of being angry about something worth being angry about.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You turned a number in a spreadsheet into a plan.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent29)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start a betting pool on how long the mandate really lasts."
	)
	= choice PrintChoices(
		"An all-staff email announces four days in office starting next month. Your commute is about to double."
		"The Return-to-Office Mandate"
		290
		glitchText
		"Reply to the announcement thread with an enthusiastic 'Exciting news!!'" 0
		"Don't say anything. Just stare at the new calendar for a while." 1
		"Block out the commute time and start planning around the real cost." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are up eleven dollars and strangely invested in a policy you hate.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed excitement you will need a long drive to recover from.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The calendar did not change. Neither did you.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You dealt with the schedule instead of the feeling about the schedule.")
		)
		)
	)
)
/******************************************************************************/
