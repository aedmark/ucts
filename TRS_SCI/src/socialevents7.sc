/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents7.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 25-28: each is a standalone
 SocialEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS7_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SocialEvent25)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Rewrite your entire profile at midnight out of spite."
	)
	= choice PrintChoices(
		"Three good days of conversation. Then nothing, mid-sentence, two days ago."
		"The Dating App Match Who Stopped Responding"
		290
		glitchText
		"Reread the whole conversation looking for the exact moment it went wrong." 0
		"Send one more message, lighter this time, just in case they missed the first." 1
		"Unmatch and let it be exactly as unremarkable as it actually is." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now describe yourself as 'allegedly fun.' It's an improvement, actually.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("You found nothing. There was nothing to find.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You performed casual. It did not feel casual.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("Three good days doesn't owe you an explanation for a fourth.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent26)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Have a friend text them from your phone 'as a joke.'"
	)
	= choice PrintChoices(
		"The first date was good. You both said 'we should do this again.' Neither of you has said anything since."
		"The Second Date That Never Got Scheduled"
		290
		glitchText
		"Decide you're clearly not that interested and move on without saying so." 0
		"Wait for them to text first so you don't seem too eager." 1
		"Just text and ask if they want to grab coffee this week." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("This has never worked in the history of dating. It does not work now.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("Cleaner this way, probably. You'll never actually know.")
		)
		(case 1
			ApplyChoiceEffects(8 5 -8 TAG_FAWN)
			Print("You outlasted your own interest waiting for permission to show it.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You said the plain thing instead of waiting for the safer one.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent27)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Look into whether you could realistically drive three states in one weekend."
	)
	= choice PrintChoices(
		"A close friend's wedding, three states away, right when your budget has zero room for flights and a hotel."
		"The Wedding Invite You Can't Afford to Attend"
		290
		glitchText
		"RSVP yes and figure out the money somehow, some way you haven't found yet." 0
		"Don't respond to the invite for as long as you possibly can." 1
		"Call your friend and just tell them the truth about the money." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You could not. You looked anyway.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -12 TAG_FAWN)
			Print("You said yes to the invitation and no, quietly, to your own budget.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FLIGHT)
			Print("The deadline is still coming. It doesn't care that you didn't look at it.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("The honest version of 'I can't' turned out to cost you less than the performance of 'I'll try.'")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent28)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Show up to the venue alone anyway, on principle."
	)
	= choice PrintChoices(
		"Twenty minutes before you were supposed to meet, the same friend cancels for the third time this month."
		"The Friend Who Cancelled Last Minute (Again)"
		290
		glitchText
		"Reply 'no worries at all!' and mean less than half of it." 0
		"Send a pointed text about the pattern, right then, while you're still annoyed." 1
		"Say it's fine tonight, and bring up the pattern later when you're not standing in your coat." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You had a fine time by yourself. This was not the point you were trying to prove.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -10 TAG_FAWN)
			Print("You absorbed the disappointment so smoothly nobody, including you, noticed it happened.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("Accurate. Badly timed. Both true at once.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You separated the moment from the pattern instead of dumping both at once.")
		)
		)
	)
)
/******************************************************************************/
