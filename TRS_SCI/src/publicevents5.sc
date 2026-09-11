/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents5.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 16-19: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS5_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (PublicEvent16)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Get out of the car and offer to play rock paper scissors."
	)
	= choice PrintChoices(
		"You both saw the spot at the same time. Neither of you has backed down, and there is now a small line of cars forming behind you both."
		"The Parking Lot Standoff"
		290
		glitchText
		"Wave them through with a big smile even though you got there first." 0
		"Rev the engine and hold your ground until they give up." 1
		"Point them to the spot you can see open two rows down instead." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You always play rock first. This time, you didn't. And you lost.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("You gave up a spot you were entitled to so a stranger wouldn't be briefly annoyed at you.")
		)
		(case 1
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("You won a parking spot and lost thirty seconds of goodwill from everyone now stuck behind you.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You solved the actual problem instead of winning the argument about it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent17)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Model the outfit for the entire waiting area whether they asked or not."
	)
	= choice PrintChoices(
		"Three angles of unflattering light and a mirror that doesn't care about your feelings. Someone outside asks how it's going in there."
		"The Fitting Room Mirror"
		290
		glitchText
		"Call out 'great, thanks!' before you've even looked down." 0
		"Stand very still and stare until the moment passes on its own." 1
		"Say 'still deciding' and actually take a second to decide." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You received two genuine compliments and one very confused nod. Worth it.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You reassured a stranger through a door about a feeling you hadn't actually had yet.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You had a small, private standoff with a mirror and the mirror won by default.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You let the honest answer be the answer, even through a curtain.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent18)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start a betting pool on how long the line will actually take."
	)
	= choice PrintChoices(
		"There's one working stall, a line of eight, and someone near the front who is very audibly running out of patience with the wait."
		"The Restroom Line"
		290
		glitchText
		"Let three people cut ahead of you so no one thinks you mind." 0
		"Loudly point out that this line has not moved once in ten minutes." 1
		"Hold your spot, wait it out, and make small talk with the person next to you." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You won four dollars. The line still has not moved. Worth it.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("You gave away your spot in line three separate times to avoid being thought of as difficult.")
		)
		(case 1
			ApplyChoiceEffects(-8 -8 0 TAG_FIGHT)
			Print("You said the true thing everyone was thinking. It did not fix the plumbing.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You turned a bad wait into a slightly less bad one by just being a person about it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent19)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Introduce yourself as if this were the first time you've ever met."
	)
	= choice PrintChoices(
		"Someone waves at you like they know you well. You have absolutely no idea who this is, and they are now three feet away and closing."
		"The Face You Can't Place"
		290
		glitchText
		"Fake total recognition and hope context clues fill in the blanks." 0
		"Suddenly find your phone extremely urgent and duck the interaction." 1
		"Admit you're blanking and ask them to remind you how you know each other." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It was, in fact, the first time. You had actually never met this person.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed an entire friendship's worth of warmth for someone whose name you can't remember.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You outran a conversation you were fully capable of having.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You told the truth instead of performing memory you didn't have. They didn't mind at all.")
		)
		)
	)
)
/******************************************************************************/
