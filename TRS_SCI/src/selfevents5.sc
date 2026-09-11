/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents5.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 17-20: each is a standalone
 SelfEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS5_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SelfEvent17)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Chase them down the street to actually answer honestly."
	)
	= choice PrintChoices(
		"'Are you doing okay, actually?' - from someone who barely knows you, at exactly the wrong-yet-right moment."
		"The Question A Stranger Asked That Undid You"
		290
		glitchText
		"Say 'I'm fine!' faster than the question finished." 0
		"Deflect and change the subject immediately." 1
		"Pause. Tell a small piece of the truth." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("They were already gone. The honesty remains, unclaimed, in the middle of the sidewalk.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You answered before you let yourself hear the question.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You closed a door a stranger had, kindly, tried to open.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("A stranger's question got an honest, small answer. Mutual respect intensifies.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent18)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Call them right now and just say it, all of it, fast."
	)
	= choice PrintChoices(
		"The words were right there. You had the opening. You changed the subject instead, and you're still thinking about it hours later."
		"The Time You Almost Told Someone"
		290
		glitchText
		"Replay the moment, cataloging exactly why you didn't say it." 0
		"Decide it's better this way for everyone. Probably." 1
		"Note that the opening will come again. It's not your only chance." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's out. The world, remarkably, keeps turning.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You held a post-mortem for a conversation that never happened.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You reframed silence as consideration for other people.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You released the pressure of a single missed moment.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent19)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Announce it to a stranger on the street, at volume."
	)
	= choice PrintChoices(
		"You did something genuinely well today. Saying so, even to yourself, feels like it's asking for trouble."
		"The Pride You Can't Say Out Loud"
		290
		glitchText
		"Immediately find the flaw to balance out the pride." 0
		"Feel the pride, quietly, and never mention it to anyone." 1
		"Say it out loud, once, to yourself. 'I did that well.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("A stranger claps for you. You're both very proud of yourself..")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You disqualified the good thing before it could get comfortable.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You let it exist, but only in a locked room.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 20 TAG_SECURE)
			Print("You let something good about yourself survive contact with your own scrutiny, again.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Draw it from memory, badly, in crayon, right now."
	)
	= choice PrintChoices(
		"You woke up at 4 AM with your heart going and no clear memory of why. The feeling stayed. The plot didn't."
		"The Nightmare You Can't Fully Remember"
		290
		glitchText
		"Lie there, awake, trying to force the memory back." 0
		"Get up and immediately start the day like nothing happened." 1
		"Let the feeling exist without the plot. You don't need the whole story." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The drawing is somehow both deeply upsetting and extremely funny.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You chased a plot your brain had already decided to withhold.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -15 TAG_FLIGHT)
			Print("You outran a feeling by scheduling over it.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You let your body's alarm matter even without a confirmed cause.")
		)
		)
	)
)
/******************************************************************************/
