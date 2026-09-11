/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 0-4: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (WorkEvent0)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply in fluent, unhinged interpretive dance. Over email. Somehow."
	)
	= choice PrintChoices(
		"You sent an email to your boss with a minor typo. Your brain immediately interprets this as a fatal error that will result in death or financial ruin."
		"The Typo"
		290
		glitchText
		"Send a frantic three-paragraph apology." 0
		"Stare at the wall and dissociate for 20 minutes." 1
		"Tell yourself 'it's just a typo' while sweating profusely." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("HR has several follow-up questions. So does everyone else. Nobody's mad, though.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 10 -15 TAG_FAWN)
			Print("You debased yourself for a misplaced comma.")
		)
		(case 1
			ApplyChoiceEffects(20 0 -5 TAG_FREEZE)
			Print("You fled your physical body. The typo remains. Nobody died.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You attempted self-soothing. It was highly unconvincing and only mildly successful.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent1)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Announce, loudly, that you accept this compliment on behalf of all of humanity."
	)
	= choice PrintChoices(
		"A coworker tells you that you did a 'really great job' on the presentation. It sounds sincere."
		"The Compliment"
		290
		glitchText
		"Deflect immediately and credit the team." 0
		"Assume they are setting you up for a massive failure later and prepare a defense." 1
		"Say 'Thank you' and let the discomfort burn your throat." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Someone starts a slow clap. It does not catch on.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -10 TAG_FAWN)
			Print("You physically swatted away the affection. Idiot.")
		)
		(case 1
			ApplyChoiceEffects(15 -5 -15 TAG_FIGHT)
			Print("You chose paranoia over pride. Hooray?")
		)
		(case 2
			ApplyChoiceEffects(-10 -10 15 TAG_SECURE)
			Print("You accepted love. It hurt less than you feared.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent2)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Slide a note under the door reading only: 'I KNOW.'"
	)
	= choice PrintChoices(
		"You walk past a conference room where two managers are talking. They look at you and the door slowly closes."
		"The Closed Door"
		290
		glitchText
		"Begin packing up your desk mentally." 0
		"Work at 300% capacity for the next four hours." 1
		"Remind yourself you are an adult and this is fine." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You know nothing. The door remains closed. Deeply satisfying anyway.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -20 TAG_FLIGHT)
			Print("You braced for the worst-case scenario. Which was Nothing.")
		)
		(case 1
			ApplyChoiceEffects(10 20 -25 TAG_FAWN)
			Print("You overcompensated through labor. Capitalism wins.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 5 TAG_SECURE)
			Print("You chose logic. Your amygdala chose to ignore it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent3)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply-all a third time, this one entirely in Comic Sans and indecipherable."
	)
	= choice PrintChoices(
		"You meant to reply privately with a joke about the meeting length. You did not reply privately."
		"The Reply-All"
		290
		glitchText
		"Immediately close your laptop and compose a new identity." 0
		"Send a follow-up message that says 'ignore that. lol.'" 1
		"Own it. Reply-all again with an article about workplace inefficiency.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Comic Sans has never been used with such menace.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 -20 -10 TAG_FLIGHT)
			Print("You attempted to physically escape the timeline. There is no escape.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You tried to laugh-track your way out of consequences. Hopefully it worked...")
		)
		(case 2
			ApplyChoiceEffects(-10 -15 15 TAG_FIGHT)
			Print("You chose chaos over shame. Bold move with your charisma levels.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent4)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Show up in full riot gear, 'just in case.'"
	)
	= choice PrintChoices(
		"'Quick Chat' with your manager. No agenda. Fifteen minutes from now."
		"The Calendar Invite"
		290
		glitchText
		"Spend the fifteen minutes drafting a resignation letter." 0
		"Ask a coworker if they've heard anything. They haven't." 1
		"Walk in assuming it's fine." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It was your annual review you forgot about. You are overdressed and underprepared.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(25 0 -15 TAG_FLIGHT)
			Print("You pre-grieved a job you still have.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -5 TAG_FAWN)
			Print("You outsourced your anxiety to someone equally unequipped.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("It was never about you. It was about the parking lot construction.")
		)
		)
	)
)
/******************************************************************************/
