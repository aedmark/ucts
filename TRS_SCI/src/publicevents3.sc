/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 16-23: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS3_SCRIPT)
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
		= glitchText "Get out of the car and offer to play\nrock paper scissors."
	)
	= choice PrintChoices(
		"You both saw the spot at the same time. Neither of you has backed down, and there is now a small line of cars forming behind you both."
		"The Parking Lot Standoff"
		290
		glitchText
		"Wave them through with a big smile\neven though you got there first." 0
		"Rev the engine and hold your ground\nuntil they give up." 1
		"Point them to the spot you can see\nopen two rows down instead." 2
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
		= glitchText "Model the outfit for the entire\nwaiting area whether they asked or\nnot."
	)
	= choice PrintChoices(
		"Three angles of unflattering light and a mirror that doesn't care about your feelings. Someone outside asks how it's going in there."
		"The Fitting Room Mirror"
		290
		glitchText
		"Call out 'great, thanks!' before\nyou've even looked down." 0
		"Stand very still and stare until the\nmoment passes on its own." 1
		"Say 'still deciding' and actually\ntake a second to decide." 2
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
		= glitchText "Start a betting pool on how long the\nline will actually take."
	)
	= choice PrintChoices(
		"There's one working stall, a line of eight, and someone near the front who is very audibly running out of patience with the wait."
		"The Restroom Line"
		290
		glitchText
		"Let three people cut ahead of you so\nno one thinks you mind." 0
		"Loudly point out that this line has\nnot moved once in ten minutes." 1
		"Hold your spot, wait it out, and\nmake small talk with the person next\nto you." 2
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
		= glitchText "Introduce yourself as if this were\nthe first time you've ever met."
	)
	= choice PrintChoices(
		"Someone waves at you like they know you well. You have absolutely no idea who this is, and they are now three feet away and closing."
		"The Face You Can't Place"
		290
		glitchText
		"Fake total recognition and hope\ncontext clues fill in the blanks." 0
		"Suddenly find your phone extremely\nurgent and duck the interaction." 1
		"Admit you're blanking and ask them\nto remind you how you know each\nother." 2
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
(procedure public (PublicEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask if you can hand out flyers too.\nYou don't know what they're for."
	)
	= choice PrintChoices(
		"A stranger presses a flyer into your hand on the sidewalk and watches, expectantly, to see what you'll do with it."
		"The Flyer Someone Hands You"
		290
		glitchText
		"Take it, thank them warmly, and hold\nonto it for three more blocks before\ntossing it." 0
		"Speed up before they can even fully\nextend their arm." 1
		"Say 'no thanks' and keep walking at\nyour normal pace." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now work here, apparently, for the next twenty minutes and zero dollars.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You carried a flyer you didn't want for three blocks so a stranger wouldn't watch you not want it.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You dodged a piece of paper like it was an arrow.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You declined a piece of paper without treating it like a moral event.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent21)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start narrating the argument quietly\nto yourself like a nature\ndocumentary."
	)
	= choice PrintChoices(
		"Two strangers are having a very loud, very personal fight three feet from where you're standing, and there's nowhere to look that isn't at them."
		"The Argument You Overheard"
		290
		glitchText
		"Step in and try to smooth things\nover between two people you've never\nmet." 0
		"Stand frozen, unsure whether moving\nwill make it worse." 1
		"Quietly step away and let it be\ntheir business, not yours." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("One of them heard you. You have made an enemy and, somehow, also a fan.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You took responsibility for a stranger's conflict resolution. They didn't accept your help.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("You became furniture until the argument found somewhere else to be.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You gave two strangers the privacy of a fight that was never going to include you.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent22)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Walk them there yourself, twelve\nfull blocks out of your way."
	)
	= choice PrintChoices(
		"Someone stops you on the street, clearly lost, and you're maybe seventy percent sure you know the way they need to go."
		"The Stranger Asking For Directions"
		290
		glitchText
		"Give confident, detailed directions\ndespite the seventy percent\nconfidence." 0
		"Say you're not sure and hurry off\nbefore they can ask a follow-up." 1
		"Say what you actually know and what\nyou don't, and point them toward\nsomeone who might know more." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are now further from your destination than they were from theirs. You made a friend, sort of.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You handed a stranger a confident answer instead of an honest maybe.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You left a lost person slightly more lost, at record speed.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave a stranger the size of your knowledge instead of an inflated one.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent23)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask if they want to just come get\nlunch with you instead."
	)
	= choice PrintChoices(
		"Someone asks if you have any spare change, right as you're fumbling for your keys and trying not to make eye contact."
		"The Person Asking For Change"
		290
		glitchText
		"Hand over more than you meant to and\napologize for not having more than\nthat." 0
		"Say 'sorry, not today' and keep\nwalking without slowing down." 1
		"Say no if you mean no, or give what\nyou can if you mean yes, and mean it\neither way." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You had lunch with a total stranger and heard a genuinely wild story. You paid. Worth it.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized to a stranger for the size of your own generosity.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You gave a fast, plain no and let it be exactly that, no more.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("Whatever you did, you did it without performing guilt about it afterward.")
		)
		)
	)
)
/******************************************************************************/
