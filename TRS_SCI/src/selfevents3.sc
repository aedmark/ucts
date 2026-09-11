/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 9-12: each is a standalone
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
(procedure public (SelfEvent9)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Lean all the way in and do a full, committed impression of them."
	)
	= choice PrintChoices(
		"You caught yourself doing the exact thing, the exact way, that used to make you flinch when they did it."
		"The Habit You Picked Up From A Parent"
		290
		glitchText
		"Get angry at yourself for the resemblance." 0
		"Pretend you didn't notice and keep doing it anyway." 1
		"Notice it. Name it. Try, gently, to do the next one differently." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's uncannily accurate. Everyone is deeply unsettled.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You punished the habit instead of just noticing it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let the pattern run unexamined, again.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught a pattern mid-motion, which is most of the work. Keep going.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent10)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Talk back to it out loud, in public, with real conviction."
	)
	= choice PrintChoices(
		"The criticism arrives in a tone you recognize. It's not how you'd talk to anyone else. It's exactly how someone once talked to you."
		"The Voice In Your Head That Isn't Yours"
		290
		glitchText
		"Agree with it. It's probably right, like it always was." 0
		"Argue back at it, harshly, in your own head." 1
		"Notice it's not your voice. You don't have to use it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("A stranger gives you a wide berth. The voice, notably, has no comeback.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -15 TAG_FAWN)
			Print("You gave an old voice the final word again.")
		)
		(case 1
			ApplyChoiceEffects(-5 0 -5 TAG_FIGHT)
			Print("You fought a voice with the same volume it uses.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You separated the message from the messenger you inherited it from.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent11)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Blame it, loudly and specifically, on Mercury being in retrograde."
	)
	= choice PrintChoices(
		"Your shoulders have been up by your ears for an hour and you genuinely cannot remember when that started."
		"The Ache With No Origin"
		290
		glitchText
		"Ignore it. It'll probably go away on its own." 0
		"Push through it, it's fine, everyone's tired, this is normal." 1
		"Stop. Relax. Roll your shoulders. Unclench your jaw. Take one real breath." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("This explains nothing. It helps somehow, anyway.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You outsourced the problem to future-you. Again. (Jerk).")
		)
		(case 1
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You neglected a body that was actively asking for something.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You gave your body the attention it was asking for. It thanks you by flooding your brain with dopamine.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Lean all the way into it."
	)
	= choice PrintChoices(
		"You hear the sentence leave your mouth and recognize it a half-second too late. It's not yours. It's theirs."
		"The Thing You Said You'd Never Become"
		290
		glitchText
		"Spiral into a full self-indictment for the next hour." 0
		"Laugh it off in the moment and never think about it again... You swear!" 1
		"Notice it. Decide, calmly, that noticing is the first step, not a failure." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have never committed to a bit this hard. Everyone is a little worried about you.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -20 TAG_FIGHT)
			Print("You sentenced yourself over one inherited sentence.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You buried it under a laugh instead of a look.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught the echo without deciding it was proof of anything permanent.")
		)
		)
	)
)
/******************************************************************************/
