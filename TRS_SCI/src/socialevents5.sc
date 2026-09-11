/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents5.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 17-20: each is a standalone
 SocialEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS5_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SocialEvent17)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Send the group a full, dramatic exit statement the next morning."
	)
	= choice PrintChoices(
		"You said you were tired. You weren't tired. You just needed to be somewhere with fewer people in it."
		"The Party You Left Early"
		290
		glitchText
		"Send an apologetic follow-up text explaining yourself." 0
		"Lie awake replaying whether anyone noticed you'd gone." 1
		"Let leaving early just be a thing you did. No debrief required." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It reads like a press release nobody asked for.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You justified a boundary nobody actually questioned.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You audited an exit that was, to everyone else, unremarkable and totally fine.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You left when you needed to and didn't file a report about it. Irish Goodbye FTW.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent18)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Stand up and propose your own toast, entirely about yourself."
	)
	= choice PrintChoices(
		"A long, warm speech naming almost everyone important in the room. Almost."
		"The Toast You Weren't Mentioned In"
		290
		glitchText
		"Smile and clap while quietly re-ranking your own importance." 0
		"Compliment the speech extra hard afterward." 1
		"Let one omission be one omission, not a verdict." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It runs nine minutes. The room claps out of confusion, mostly.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("A toast became a scoreboard, and you lost.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You praised loudest the thing that left you out.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You weren't named. You were, notably, still there.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent19)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Invent an equally mysterious new friend of your own to mention constantly."
	)
	= choice PrintChoices(
		"Your friend has a new person they mention constantly now. Inside jokes you're not part of. You're happy for them. Mostly."
		"The New Best Friend"
		290
		glitchText
		"Ask enthusiastic questions about the new friend, overselling interest." 0
		"Quietly pull back from making plans, without saying why." 1
		"Name the feeling to yourself: a little jealous, and that's okay." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Your new friend, 'Gary,' may not exist, but this plan works better than expected.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You interviewed your own replacement with a big smile on.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You left the room before anyone asked you to.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Jealousy, acknowledged, took up less room than it usually does. You all go out for drinks later.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Actually pull out a calendar and demand a date, right now."
	)
	= choice PrintChoices(
		"The fourth time this month someone's said it. Nobody, including you, has ever proposed an actual date."
		"The 'We Should Hang Out Sometime' That Never Happens"
		290
		glitchText
		"Say it back warmly, knowing it means nothing either time." 0
		"Actually suggest a specific day and time." 1
		"Let the phrase pass, again, unchallenged." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("A date gets picked. Everyone is stunned, including the calendar.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You kept a nice-sounding ritual alive instead of a friendship.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You called the bluff, including your own. It worked.")
		)
		(case 2
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("A fifth 'sometime' joined the pile of the first four. Nice collection!")
		)
		)
	)
)
/******************************************************************************/
