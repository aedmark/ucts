/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents6.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 20-23: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS6_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (PublicEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask if you can hand out flyers too. You don't know what they're for."
	)
	= choice PrintChoices(
		"A stranger presses a flyer into your hand on the sidewalk and watches, expectantly, to see what you'll do with it."
		"The Flyer Someone Hands You"
		290
		glitchText
		"Take it, thank them warmly, and hold onto it for three more blocks before tossing it." 0
		"Speed up before they can even fully extend their arm." 1
		"Say 'no thanks' and keep walking at your normal pace." 2
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
		= glitchText "Start narrating the argument quietly to yourself like a nature documentary."
	)
	= choice PrintChoices(
		"Two strangers are having a very loud, very personal fight three feet from where you're standing, and there's nowhere to look that isn't at them."
		"The Argument You Overheard"
		290
		glitchText
		"Step in and try to smooth things over between two people you've never met." 0
		"Stand frozen, unsure whether moving will make it worse." 1
		"Quietly step away and let it be their business, not yours." 2
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
		= glitchText "Walk them there yourself, twelve full blocks out of your way."
	)
	= choice PrintChoices(
		"Someone stops you on the street, clearly lost, and you're maybe seventy percent sure you know the way they need to go."
		"The Stranger Asking For Directions"
		290
		glitchText
		"Give confident, detailed directions despite the seventy percent confidence." 0
		"Say you're not sure and hurry off before they can ask a follow-up." 1
		"Say what you actually know and what you don't, and point them toward someone who might know more." 2
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
		= glitchText "Ask if they want to just come get lunch with you instead."
	)
	= choice PrintChoices(
		"Someone asks if you have any spare change, right as you're fumbling for your keys and trying not to make eye contact."
		"The Person Asking For Change"
		290
		glitchText
		"Hand over more than you meant to and apologize for not having more than that." 0
		"Say 'sorry, not today' and keep walking without slowing down." 1
		"Say no if you mean no, or give what you can if you mean yes, and mean it either way." 2
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
