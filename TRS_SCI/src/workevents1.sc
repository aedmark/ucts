/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 0-8: each is a standalone
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
		= glitchText "Reply in fluent, unhinged\ninterpretive dance. Over email.\nSomehow."
	)
	= choice PrintChoices(
		"You sent an email to your boss with a minor typo. Your brain immediately interprets this as a fatal error that will result in death or financial ruin."
		"The Typo"
		290
		glitchText
		"Send a frantic three-paragraph\napology." 0
		"Stare at the wall and dissociate for\n20 minutes." 1
		"Tell yourself 'it's just a typo'\nwhile sweating profusely." 2
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
		= glitchText "Announce, loudly, that you accept\nthis compliment on behalf of all of\nhumanity."
	)
	= choice PrintChoices(
		"A coworker tells you that you did a 'really great job' on the presentation. It sounds sincere."
		"The Compliment"
		290
		glitchText
		"Deflect immediately and credit the\nteam." 0
		"Assume they are setting you up for a\nmassive failure later and prepare a\ndefense." 1
		"Say 'Thank you' and let the\ndiscomfort burn your throat." 2
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
		= glitchText "Slide a note under the door reading\nonly: 'I KNOW.'"
	)
	= choice PrintChoices(
		"You walk past a conference room where two managers are talking. They look at you and the door slowly closes."
		"The Closed Door"
		290
		glitchText
		"Begin packing up your desk mentally." 0
		"Work at 300% capacity for the next\nfour hours." 1
		"Remind yourself you are an adult and\nthis is fine." 2
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
		= glitchText "Reply-all a third time, this one\nentirely in Comic Sans and\nindecipherable."
	)
	= choice PrintChoices(
		"You meant to reply privately with a joke about the meeting length. You did not reply privately."
		"The Reply-All"
		290
		glitchText
		"Immediately close your laptop and\ncompose a new identity." 0
		"Send a follow-up message that says\n'ignore that. lol.'" 1
		"Own it. Reply-all again with an\narticle about workplace\ninefficiency.'" 2
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
		= glitchText "Show up in full riot gear, 'just in\ncase.'"
	)
	= choice PrintChoices(
		"'Quick Chat' with your manager. No agenda. Fifteen minutes from now."
		"The Calendar Invite"
		290
		glitchText
		"Spend the fifteen minutes drafting a\nresignation letter." 0
		"Ask a coworker if they've heard\nanything. They haven't." 1
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
(procedure public (WorkEvent5)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Send a single, unprompted photo of a\nraccoon looking concerned."
	)
	= choice PrintChoices(
		"Seen 3:12 PM. It is now 6:47 PM. No reply."
		"The Read Receipt"
		290
		glitchText
		"Send a second message: 'no worries\njust following up!'" 0
		"Close Slack and refuse to open it\nuntil tomorrow." 1
		"Assume you've been quietly deleted\nfrom their mind." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The raccoon says what you cannot. Which is nothing of importance.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You apologized for existing in their inbox (and being impatient).")
		)
		(case 1
			ApplyChoiceEffects(15 -5 0 TAG_FLIGHT)
			Print("Out of sight, technically not out of mind. Close enough.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You Sherlocked your way to a panic attack with zero evidence.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent6)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Lean in. Claim the typo was\navant-garde on purpose."
	)
	= choice PrintChoices(
		"You meant to type something professional. Autocorrect had other plans. It has already been sent."
		"The Autocorrect Betrayal"
		290
		glitchText
		"Send six frantic follow-ups\nexplaining what you meant." 0
		"Screenshot it, close the laptop,\nstare at the wall." 1
		"Let the typo be funny. It's kind of\nfunny." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have accidentally invented a new art movement and the accountants are fond of you, now.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 10 -15 TAG_FAWN)
			Print("You buried the joke under a small avalanche of context and shame.")
		)
		(case 1
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You archived the evidence and fled the scene of a victimless crime.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You allowed yourself to be a person who makes typos. You survived.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent7)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Respond back at them entirely in\nweaponized corporate buzzwords."
	)
	= choice PrintChoices(
		"Your manager says 'let's discuss your growth areas' in a tone that reveals absolutely nothing."
		"The Performance Review Buzzword"
		290
		glitchText
		"Mentally practice your groveling\ntechnique to beg for your job back." 0
		"Walk in and ask directly what\n'growth areas' means, specifically." 1
		"Prepare a mental defense file of\nevery accomplishment from the last\nthree years." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have synergized so hard the meeting ended early out of confusion. Nobody dares to circle back.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(25 0 -10 TAG_FREEZE)
			Print("You spent energy defending a career you still have.")
		)
		(case 1
			ApplyChoiceEffects(-15 -10 10 TAG_FIGHT)
			Print("You demanded the noun behind the euphemism.")
		)
		(case 2
			ApplyChoiceEffects(10 10 -10 TAG_FAWN)
			Print("You built a case for a trial nobody scheduled.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent8)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Stand up and simply read the email\naloud instead."
	)
	= choice PrintChoices(
		"Forty-five minutes, twelve people, and the entire message could have fit in three sentences (in an email)."
		"The Meeting That Did Not Need To Be"
		290
		glitchText
		"Nod along and add a supportive\n'great point' to three different\ntangents." 0
		"Mentally exit the call and return\nonly when your name is said." 1
		"Ask, once, if this could be a\ntwo-line message next time." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The meeting ends four seconds later. Nobody claps, but you think they maybe want to.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You applauded the detour instead of naming it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("Your body stayed. The rest of you clocked out early. Good for you.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You said the quiet part. People respect you for it.")
		)
		)
	)
)
/******************************************************************************/
