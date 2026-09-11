/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents6.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 21-24: each is a standalone
 SelfEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS6_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
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
