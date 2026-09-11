/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 0-3: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent0)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Sigh back, louder, escalating into a full operatic aria."
	)
	= choice PrintChoices(
		"Your partner sighs audibly in the other room. You have absolutely zero context for why."
		"The Sigh"
		290
		glitchText
		"Assume it's your fault and quietly clean the kitchen." 0
		"Ask aggressively, 'IS SOMETHING WRONG?!'" 1
		"Put on noise-canceling headphones." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Neither of you knows what started this. Both of you are committed now.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 15 -20 TAG_FAWN)
			Print("You traded self-worth for perceived safety and clean dishes.")
		)
		(case 1
			ApplyChoiceEffects(-15 -25 5 TAG_FIGHT)
			Print("You struck first to avoid being struck. Classic.")
		)
		(case 2
			ApplyChoiceEffects(25 -10 0 TAG_FLIGHT)
			Print("Avoidance achieved. The tension is stored in your jaw.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent1)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Build an elaborate dish-based sculpture instead of washing them."
	)
	= choice PrintChoices(
		"Your partner says 'hey, whenever you get a chance' about the dishes. Their tone was completely neutral."
		"The Dishes in the Sink"
		290
		glitchText
		"Do the dishes at 11 PM, narrating your resentment internally while sighing externally, and heavily." 0
		"Say 'I was GOING to' with more heat than the sentence needed." 1
		"Leave the room to 'find something' for four minutes. Flee state." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's actually kind of impressive. It does not count as washing them, though.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 10 -15 TAG_FAWN)
			Print("You complied loudly. Nobody is impressed.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 5 TAG_FIGHT)
			Print("The dishes were never the real defendant.")
		)
		(case 2
			ApplyChoiceEffects(10 -5 -5 TAG_FLIGHT)
			Print("A tactical retreat from a sink.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent2)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply only in cryptic, unrelated fortune-cookie wisdom."
	)
	= choice PrintChoices(
		"Your aunt just brought up something from Thanksgiving 2019. Nobody asked."
		"The Family Group Chat"
		290
		glitchText
		"Mute the chat and pretend your phone is broken." 0
		"Draft a measured correction, then delete it three times." 1
		"Send a single laughing emoji and nothing else." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'The sink remembers what the heart forgets.' Nobody asked. Nobody replies.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 -5 0 TAG_FLIGHT)
			Print("You unplugged from the family server.")
		)
		(case 1
			ApplyChoiceEffects(20 5 -10 TAG_FREEZE)
			Print("You drafted diplomacy and shipped silence.")
		)
		(case 2
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You de-escalated with punctuation.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent3)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start narrating the drive like a hushed golf commentator."
	)
	= choice PrintChoices(
		"Twenty minutes home. Nobody has said anything since you left. You are replaying the entire evening."
		"The Silent Car Ride"
		290
		glitchText
		"Turn the radio up to fill the space." 0
		"Ask 'you okay?' four separate times." 1
		"Sit in it. Actually just sit in it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'And she signals... she signals early. Remarkable composure. Will she return her hands to ten and two? Stay tuned to find out...'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You outsourced the silence to a pop song.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You checked the temperature of a room that was comfortable, not cold.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("Twenty minutes of quiet did not, in fact, kill you.")
		)
		)
	)
)
/******************************************************************************/
