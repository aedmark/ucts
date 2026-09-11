/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 5-9: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS2_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (WorkEvent5)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Send a single, unprompted photo of a raccoon looking concerned."
	)
	= choice PrintChoices(
		"Seen 3:12 PM. It is now 6:47 PM. No reply."
		"The Read Receipt"
		290
		glitchText
		"Send a second message: 'no worries just following up!'" 0
		"Close Slack and refuse to open it until tomorrow." 1
		"Assume you've been quietly deleted from their mind." 2
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
		= glitchText "Lean in. Claim the typo was avant-garde on purpose."
	)
	= choice PrintChoices(
		"You meant to type something professional. Autocorrect had other plans. It has already been sent."
		"The Autocorrect Betrayal"
		290
		glitchText
		"Send six frantic follow-ups explaining what you meant." 0
		"Screenshot it, close the laptop, stare at the wall." 1
		"Let the typo be funny. It's kind of funny." 2
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
		= glitchText "Respond back at them entirely in weaponized corporate buzzwords."
	)
	= choice PrintChoices(
		"Your manager says 'let's discuss your growth areas' in a tone that reveals absolutely nothing."
		"The Performance Review Buzzword"
		290
		glitchText
		"Mentally practice your groveling technique to beg for your job back." 0
		"Walk in and ask directly what 'growth areas' means, specifically." 1
		"Prepare a mental defense file of every accomplishment from the last three years." 2
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
		= glitchText "Stand up and simply read the email aloud instead."
	)
	= choice PrintChoices(
		"Forty-five minutes, twelve people, and the entire message could have fit in three sentences (in an email)."
		"The Meeting That Did Not Need To Be"
		290
		glitchText
		"Nod along and add a supportive 'great point' to three different tangents." 0
		"Mentally exit the call and return only when your name is said." 1
		"Ask, once, if this could be a two-line message next time." 2
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
(procedure public (WorkEvent9)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with a photo of an actual empty desk, for evidence."
	)
	= choice PrintChoices(
		"You set your status to 'in a meeting' twenty minutes ago and never actually joined one. Someone just pinged you directly."
		"The Away Message"
		290
		glitchText
		"Type 'sorry, just wrapped up, what's up!' like it's true." 0
		"Let the message sit unread for another eleven minutes." 1
		"Reply honestly: 'Just saw this, give me a minute.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The desk is, notably, yours. This raises more questions than it answers. You're okay with this.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You performed availability you didn't have.")
		)
		(case 1
			ApplyChoiceEffects(15 -5 0 TAG_FLIGHT)
			Print("You bought time you'll have to pay back later.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You told a true, small, unremarkable thing.")
		)
		)
	)
)
/******************************************************************************/
