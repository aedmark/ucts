/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents8.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 30-33: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS8_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (WorkEvent30)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Send the recruiter a reply arguing your own case, after the decision's already final."
	)
	= choice PrintChoices(
		"The recruiter finally writes back. 'We've decided to move forward with other candidates at this time.'"
		"The Rejection Email"
		290
		glitchText
		"Reread the email six times looking for a hidden opening." 0
		"Close the tab, close the laptop, and do literally anything else." 1
		"Let yourself be disappointed for a minute, then ask for feedback anyway." 2
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
		= glitchText "Frame the cancellation email and hang it somewhere you'll see it daily."
	)
	= choice PrintChoices(
		"Three months of work gets killed in a two-line message. 'Deprioritizing this for now.'"
		"The Project That Got Cancelled"
		290
		glitchText
		"Point out, at length, exactly how much time this wasted." 0
		"Reply 'Totally understand, happy to pivot!' before you've processed it at all." 1
		"Ask what, if anything, from the work can be reused elsewhere." 2
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
		= glitchText "Overshare something of your own in return, just to even the score."
	)
	= choice PrintChoices(
		"Someone uses your two-minute standup slot to tell the whole team about their divorce."
		"The Oversharing Coworker"
		290
		glitchText
		"Nod along and offer sympathetic follow-up questions you don't have time for." 0
		"Suddenly remember an urgent message you have to go check." 1
		"Say gently that you hope they're okay, and steer the meeting back on track." 2
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
		= glitchText "Reply-all asking everyone else what they got."
	)
	= choice PrintChoices(
		"The open enrollment email arrives. This year's raise, after inflation, comes out to functionally nothing."
		"The Raise That Wasn't"
		290
		glitchText
		"Do the math four different ways hoping for a different number." 0
		"Thank your manager for the raise in the team channel anyway." 1
		"Note the real number down and start pricing out what a market-rate offer looks like." 2
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
