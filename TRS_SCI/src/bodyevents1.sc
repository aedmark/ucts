/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js. Re-run that script after
 editing the source event data.

 BODY-zone events 0-3: each is a standalone
 BodyEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script BODYEVENTS1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (BodyEvent0)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reorganize your entire closet."
	)
	= choice PrintChoices(
		"You're awake at an ungodly hour for no reason, running a full inventory of every mistake you've made since birth."
		"The Wake-Up Call"
		290
		glitchText
		"Get up and scroll your phone until the sky turns on again" 0
		"Lie perfectly still and pretend this isn't happening." 1
		"Get up, write down the one thing actually bothering you, and go back to bed." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Your shirts are now sorted by emotional association. Impressive. And unhelpful.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You traded sleep for a different, worse kind of tired.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -5 TAG_FREEZE)
			Print("You waited it out. It did not go anywhere.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the thought somewhere else to live besides your brain.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent1)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Buy a $40 mouthguard you will wear exactly once."
	)
	= choice PrintChoices(
		"You catch yourself clenching your jaw so hard your teeth ache. You have no memory of starting."
		"The Locked Jaw"
		290
		glitchText
		"Force a smile until the muscles relax on their own." 0
		"Snap at the next person who asks you a simple question." 1
		"Actually stretch it out and breathe for ten seconds." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's in a drawer now. You develop TNJ.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -5 TAG_FAWN)
			Print("You performed relaxation until it was almost real. You even broke a sweat!")
		)
		(case 1
			ApplyChoiceEffects(-15 -15 0 TAG_FIGHT)
			Print("Your jaw unclenched. Someone else's day is now ruined. Good job.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You noticed the tension and let it go. On purpose (for once).")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent2)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Genuinely consider whether you're being paged by the universe."
	)
	= choice PrintChoices(
		"You feel your phone buzz in your pocket. It's not there. You're not wearing anything with pockets."
		"The Phantom Vibration"
		290
		glitchText
		"Check anyway and feign shock when you can't find your phone." 0
		"Laugh it off and immediately forget it happened." 1
		"Notice it, name it, and let it pass." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are not. The universe isn't on speaking terms with you, currently.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(0 -5 -5 TAG_FAWN)
			Print("You reflexively obeyed the call of a muscle spasm.")
		)
		(case 1
			ApplyChoiceEffects(-5 5 0 TAG_FLIGHT)
			Print("You buried the weird little moment under a joke.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You clocked your nervous system doing a bit and didn't argue with it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent3)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Eat a mystery item from the back of the freezer instead."
	)
	= choice PrintChoices(
		"It's been hours since your lunch break. You still have not eaten. You only just now noticed the shaking."
		"The Skipped Lunch"
		290
		glitchText
		"Push through, you'll eat when this is 'actually done.'" 0
		"Eat standing up over the sink in under ninety seconds." 1
		"Sit down and finally enjoy your meal." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Unidentifiable but savory, slightly freezer-burned, and somehow the best part of your day.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You deferred a basic need to a deadline that keeps moving.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 0 TAG_FLIGHT)
			Print("Technically food. Technically eaten. Moving on. (Your colon hates you).")
		)
		(case 2
			ApplyChoiceEffects(-10 0 15 TAG_SECURE)
			Print("Wild concept: feeding yourself food when your body needs it because you matter.")
		)
		)
	)
)
/******************************************************************************/
