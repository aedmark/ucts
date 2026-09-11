/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents8.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 29-32: each is a standalone
 SelfEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS8_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SelfEvent29)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Answer with the most impulsive thing that comes to mind, unfiltered."
	)
	= choice PrintChoices(
		"Someone asks, casually, 'so what do you actually want?' and your mind goes completely, embarrassingly blank."
		"The Question You Can't Answer"
		290
		glitchText
		"Give the answer you think they want to hear instead of sitting in the blank." 0
		"Laugh it off and change the subject before the silence gets uncomfortable." 1
		"Just say, honestly, 'I don't actually know yet.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You surprised yourself with the answer. You're still not sure it's 100% true.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You answered the question they asked instead of the one that stumped you.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("The subject changed. The blank is still there, waiting for next time.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("Not knowing, said out loud, turned out to be survivable.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent30)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Try to recreate the exact photo, right now, alone in your room."
	)
	= choice PrintChoices(
		"You find an old photo. You're laughing, genuinely, in a way you don't fully remember how to do anymore. You can't place the feeling."
		"The Photo Where You Look Happy"
		290
		glitchText
		"Stare at it a while, then close the folder without really processing anything." 0
		"Post it with a caption implying everything's still that happy now." 1
		"Let yourself actually miss that feeling instead of performing that you still have it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It does not look the same. It was never really about the photo.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(8 0 -8 TAG_FREEZE)
			Print("You looked at the feeling instead of into it. Close, but not quite.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("The caption performed something the photo didn't actually contain anymore.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 12 TAG_SECURE)
			Print("Missing something honestly takes up less room than pretending you don't.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent31)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Accept a plan you don't want to go to, purely to prove a point to yourself."
	)
	= choice PrintChoices(
		"You've cancelled the last four plans in a row and told yourself, each time, that you're just 'an introvert who needs space.'"
		"The Urge to Label 'Isolation' as 'Introversion'"
		290
		glitchText
		"Cancel the fifth one too, and repeat the same explanation to yourself." 0
		"Don't examine it. Just stay in and let the pattern keep going." 1
		"Ask yourself honestly whether this is self-care or avoidance, and answer honestly." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You went. It was fine. It proved absolutely nothing either way.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -8 0 TAG_FLIGHT)
			Print("The explanation is getting worn out. You use it anyway because you know the only person holding you accountable is you.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("Staying in didn't recharge anything this time. It just kept happening.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You looked at the label before deciding whether to trust it. The answer was, as usual, the quantumly optimal 'Yes.'")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent32)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Buy all the equipment for it and start absolutely nothing else."
	)
	= choice PrintChoices(
		"There's something you've wanted to start for a long time. You're still waiting to feel ready, and the feeling hasn't come."
		"The Thing You're Waiting to Feel Ready For"
		290
		glitchText
		"Keep waiting for the readiness to arrive on its own schedule." 0
		"Tell people you're 'about to start soon' again, to keep the pressure off." 1
		"Do one small piece of it today, ready or not." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now own everything you need and have used none of it. A familiar shape.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("It didn't arrive. It rarely does on its own.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You bought yourself another round of not starting, at the cost of a small lie.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Turns out readiness was never actually the requirement.")
		)
		)
	)
)
/******************************************************************************/
