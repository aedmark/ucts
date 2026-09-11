/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js. Re-run that script after
 editing the source event data.

 BODY-zone events 0-7: each is a standalone
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
(procedure public (BodyEvent4)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Diagnose yourself with seven unrelated conditions via search engine."
	)
	= choice PrintChoices(
		"A headache has been building behind your left eye since you woke up."
		"The Tension Headache"
		290
		glitchText
		"Apologize to it and keep working through the pain." 0
		"Snap the laptop shut and lie in a dark room out of spite." 1
		"Drink water, step outside, take a break." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("According to the internet, it's either dehydration or something terminal. No in-between.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You negotiated with a headache. The headache won.")
		)
		(case 1
			ApplyChoiceEffects(-15 -10 5 TAG_FIGHT)
			Print("You declared war on productivity and productivity lost.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("You treated the cause instead of white-knuckling the symptom.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent5)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Book a massage you will cancel twice and never reschedule."
	)
	= choice PrintChoices(
		"Someone points out that you look really tense. You notice your shoulders are up to your ears."
		"The Shoulders"
		290
		glitchText
		"Laugh it off, drop them for exactly four seconds." 0
		"Get defensive about your own posture." 1
		"Actually roll them out and admit you're tensed up for no reason." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's the thought that counts. Your shoulders disagree.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -5 TAG_FAWN)
			Print("You roleplay as a relaxed person. Your shoulders forgot their lines immediately.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("Now everyone thinks you're hiding something... Or you think that they think you're hiding something. ")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You told the truth about your own body. Small, but real. Everyone else rolls their shoulders, too.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent6)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Have a coworker read it out loud to you instead."
	)
	= choice PrintChoices(
		"An email notification appears. Your stomach drops before your eyes even finish reading the subject line."
		"The Stomach Drop"
		290
		glitchText
		"Open it immediately, brace for impact." 0
		"Let it sit unread while your stomach keeps dropping anyway." 1
		"Take one breath, then open it at your own pace." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Outsourcing Dread: an underrated coping strategy.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You ran toward the thing that scared you. Godspeed.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("You delayed the information, not the feeling. Then you dry heave into the wastebasket.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 5 TAG_SECURE)
			Print("You let your body catch up before you made a decision.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent7)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Google your symptoms while your chest gets tighter reading the results."
	)
	= choice PrintChoices(
		"Your chest has felt tight since the second cup of coffee. It's been four hours."
		"The Tight Chest"
		290
		glitchText
		"Have a third cup, see what happens." 0
		"Ignore it, it'll pass, it always passes." 1
		"Switch to water and sit somewhere quiet for a minute." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Peak efficiency: causing the exact symptom you were worried about.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -5 TAG_FIGHT)
			Print("You met a warning sign with more of the thing that caused it.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You filed it under 'later' along with everything else.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 5 TAG_SECURE)
			Print("You gave your nervous system one thing to not fight.")
		)
		)
	)
)
/******************************************************************************/
