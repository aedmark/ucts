/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 17-24: each is a standalone
 SelfEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS3_SCRIPT)
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
(procedure public (SelfEvent21)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Apologize to an inanimate object instead. With real emotion."
	)
	= choice PrintChoices(
		"Someone bumps into you. You apologize. To them. For being bumped into."
		"The Reflex To Apologize For Existing"
		290
		glitchText
		"Apologize again, just to be safe." 0
		"Notice it happened and feel weird about it for the rest of the day." 1
		"Notice the reflex. Don't perform an apology for the apology." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The doorknob accepts your apology graciously. It has no other choice.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You doubled down on an apology that was never yours to give.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a two-second reflex become an all-day mood.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught the reflex mid-air and let it just pass through.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent22)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Post the most unhinged, honest thing you can think of instead."
	)
	= choice PrintChoices(
		"You've refreshed the same three apps four times in the last ten minutes, looking for a number that proves something."
		"The Urge To Check If You're Still Likable"
		290
		glitchText
		"Keep refreshing. The number hasn't proven anything yet, but the next one might." 0
		"Post something calibrated to perform well, just to be sure." 1
		"Put the phone down. The question doesn't need an answer right now." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Engagement still goes way down... but you, somehow, feel better.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You outsourced your worth to a number that resets every day.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You engineered proof instead of just existing for a minute.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You let the urge exist without feeding it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent23)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start an entirely new, deliberately pointless streak instead."
	)
	= choice PrintChoices(
		"Forty-one days. You missed yesterday. The app has already reset the number to zero, and it feels disproportionately catastrophic."
		"The Streak You Broke"
		290
		glitchText
		"Berate yourself for the missed day, at length." 0
		"Decide the whole habit is ruined now and quietly stop." 1
		"Start again today. The forty-one days still happened." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Day one of 'touching a doorframe for luck.' It's going great.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You treated one missed day like it undid the other forty-one.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("One broken streak took the whole habit down with it.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("A number resetting didn't erase what you'd actually built.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent24)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Turn on the radio and sing along badly through it."
	)
	= choice PrintChoices(
		"Nowhere dramatic. Just the car, in a parking lot, engine off, and the thing you've been holding all week finally lets go."
		"The Quiet Car Where You Finally Cry"
		290
		glitchText
		"Stop it fast. Fix your face. Go inside like nothing happened." 0
		"Apologize to no one in an empty car for crying at all." 1
		"Let it happen. Stay in the car until it passes on its own." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are now simultaneously crying and singing off-key. A rare skill.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You closed the door on something that had only just cracked open.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You apologized to an empty passenger seat. It was unmoved.")
		)
		(case 2
			ApplyChoiceEffects(-20 0 20 TAG_SECURE)
			Print("You let the parking lot hold something you'd been carrying all week.")
		)
		)
	)
)
/******************************************************************************/
