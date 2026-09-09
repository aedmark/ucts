/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 26-33: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS4_SCRIPT)
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
		= glitchText "Ask your manager to list, out loud,\nevery mistake you've made this\nquarter."
	)
	= choice PrintChoices(
		"Your manager says the presentation went great. You spend the next hour building a case for why she's wrong."
		"The Imposter Spiral"
		290
		glitchText
		"Deflect the compliment immediately\nand list everyone else who helped." 0
		"Reread your own slides looking for\nthe mistake everyone's too polite to\nmention." 1
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
		= glitchText "Reply-all to the rumor thread with a\nsingle question mark."
	)
	= choice PrintChoices(
		"A screenshot from an anonymous coworker forum is going around. It names your department. Nobody official has said a word."
		"The Layoff Rumor"
		290
		glitchText
		"Refresh your email every four\nminutes for the rest of the day." 0
		"Close every work tab and watch\nsomething mindless until it's time\nto log off." 1
		"Update your resume calmly, then\nclose the laptop for the night." 2
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
		= glitchText "Casually ask three more coworkers\nwhat they make."
	)
	= choice PrintChoices(
		"A spreadsheet gets shared by accident. Someone with two fewer years than you makes more."
		"The Salary You Found Out"
		290
		glitchText
		"Draft a furious email to HR and send\nit before you can think twice." 0
		"Decide you're probably not\nremembering your own worth\ncorrectly, and let it go." 1
		"Write down exactly what you'd ask\nfor and schedule the actual\nconversation." 2
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
		= glitchText "Start a betting pool on how long the\nmandate really lasts."
	)
	= choice PrintChoices(
		"An all-staff email announces four days in office starting next month. Your commute is about to double."
		"The Return-to-Office Mandate"
		290
		glitchText
		"Reply to the announcement thread\nwith an enthusiastic 'Exciting\nnews!!'" 0
		"Don't say anything. Just stare at\nthe new calendar for a while." 1
		"Block out the commute time and start\nplanning around the real cost." 2
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
(procedure public (WorkEvent30)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Send the recruiter a reply arguing\nyour own case, after the decision's\nalready final."
	)
	= choice PrintChoices(
		"The recruiter finally writes back. 'We've decided to move forward with other candidates at this time.'"
		"The Rejection Email"
		290
		glitchText
		"Reread the email six times looking\nfor a hidden opening." 0
		"Close the tab, close the laptop, and\ndo literally anything else." 1
		"Let yourself be disappointed for a\nminute, then ask for feedback\nanyway." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It changed nothing. For a brief moment, you feel like you really accomplished something.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("The email said the same thing on the sixth read as it did on the first.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("The feeling is still there. It's just unsupervised now.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You made room for the disappointment and asked a useful question anyway.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent31)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Frame the cancellation email and\nhang it somewhere you'll see it\ndaily."
	)
	= choice PrintChoices(
		"Three months of work gets killed in a two-line message. 'Deprioritizing this for now.'"
		"The Project That Got Cancelled"
		290
		glitchText
		"Point out, at length, exactly how\nmuch time this wasted." 0
		"Reply 'Totally understand, happy to\npivot!' before you've processed it\nat all." 1
		"Ask what, if anything, from the work\ncan be reused elsewhere." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It is, unexpectedly, a little bit funny now.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-12 -15 0 TAG_FIGHT)
			Print("True and unhelpful, in roughly equal measure.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You agreed with a decision you haven't actually forgiven yet.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You looked for what survives instead of just what died.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent32)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Overshare something of your own in\nreturn, just to even the score."
	)
	= choice PrintChoices(
		"Someone uses your two-minute standup slot to tell the whole team about their divorce."
		"The Oversharing Coworker"
		290
		glitchText
		"Nod along and offer sympathetic\nfollow-up questions you don't have\ntime for." 0
		"Suddenly remember an urgent message\nyou have to go check." 1
		"Say gently that you hope they're\nokay, and steer the meeting back on\ntrack." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The meeting is now forty minutes long and everyone knows too much about the both of you.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You gave away ten minutes you didn't have to a conversation you didn't start.")
		)
		(case 1
			ApplyChoiceEffects(-5 -8 0 TAG_FLIGHT)
			Print("You escaped clean. The next person in the meeting was not so lucky.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You were kind and still kept the meeting alive. Both things, at once. Everyone feels lighter now.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent33)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply-all asking everyone else what\nthey got."
	)
	= choice PrintChoices(
		"The open enrollment email arrives. This year's raise, after inflation, comes out to functionally nothing."
		"The Raise That Wasn't"
		290
		glitchText
		"Do the math four different ways\nhoping for a different number." 0
		"Thank your manager for the raise in\nthe team channel anyway." 1
		"Note the real number down and start\npricing out what a market-rate offer\nlooks like." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody answers. Somehow the silence answers anyway.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The number stayed the same across every method. Math is like that.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You said thank you for something that, functionally, cost you more than just money.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You stopped waiting for the number to feel different and used it instead.")
		)
		)
	)
)
/******************************************************************************/
