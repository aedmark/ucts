/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 25-32: each is a standalone
 SelfEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS4_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SelfEvent25)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Open three different budgeting apps in the same five minutes."
	)
	= choice PrintChoices(
		"You open the app, see the number, close it immediately, and open it again ninety seconds later like it might have changed."
		"The Banking App"
		290
		glitchText
		"Check it six more times without doing anything differently." 0
		"Tell yourself other people are worse with money, so it's fine." 1
		"Actually write the number down somewhere and make one small plan around it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now have three different opinions on how broke you are.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("The number stayed the same. Checking isn't the same as changing it.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("Comparing down didn't actually move the number either.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You stopped checking and started, in a small way, handling it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent26)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Google 'average age people figure their life out' at midnight."
	)
	= choice PrintChoices(
		"You do the math on how old you are versus how old you thought 'having it together' would happen by. The math is not flattering."
		"The Age You Thought You'd Have This Figured Out By"
		290
		glitchText
		"Get irrationally angry at your younger self for the timeline they set." 0
		"Spiral quietly for the rest of the evening without telling anyone." 1
		"Notice the timeline was arbitrary, made up by a much younger, much less informed you." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The internet does not agree with itself. You are somehow less comforted.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("Younger you didn't know anything. It's a strange thing to resent them for.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("The spiral had an audience of exactly one and no exit.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You fired a deadline nobody real ever actually set.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent27)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Flip a coin and pretend the outcome doesn't matter to you."
	)
	= choice PrintChoices(
		"It's still sitting there, unmade, quietly getting heavier every week you don't touch it."
		"The Decision You've Been Avoiding for Months"
		290
		glitchText
		"Add it to tomorrow's list again, same as every day this month." 0
		"Ask five different people what they'd do, hoping one of them decides for you." 1
		"Give yourself an actual deadline and one criterion to decide by." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It landed. Your reaction to it told you exactly what you actually wanted.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("Tomorrow's list is getting long. This is still at the top of it.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("Five people, five opinions, and the decision is still, somehow, yours.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You made the decision smaller instead of making it disappear.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent28)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Look up someone from high school you haven't thought about in a decade instead."
	)
	= choice PrintChoices(
		"There's one person (you know exactly who) whose life you measure your own against, always unfavorably, without ever really deciding to."
		"The Specific Person You Compare Yourself To"
		290
		glitchText
		"Check what they're up to again, even though it never actually helps." 0
		"Congratulate their latest thing extra warmly to prove to yourself you're not bitter." 1
		"Notice you're comparing your whole self to their highlight reel, and put the scale down." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("New person to compare yourself to. Same old habit, different target.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("Same result as every other time. You checked anyway.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("The warmth was mostly for your own benefit, not theirs.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You stopped measuring two different things with the same ruler.")
		)
		)
	)
)
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
