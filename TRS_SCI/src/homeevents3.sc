/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 8-11: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS3_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent8)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with a single dramatic movie-trailer voice line."
	)
	= choice PrintChoices(
		"Four words. No context. Sent an hour ago, and you've only just seen it."
		"The 'We Need To Talk' Text"
		290
		glitchText
		"Spend the hour composing worst-case scenarios instead of replying." 0
		"Reply with three apologies before you know what for." 1
		"Reply: 'Okay. I'm here when you're ready.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'In a world where nobody explains anything...'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(25 0 -15 TAG_FREEZE)
			Print("You lived through several futures that hadn't happened yet.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You pled guilty to a charge that hadn't been read yet.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("You left room for the conversation instead of finishing it alone.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent9)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Cover the entire kitchen in post-its of your own."
	)
	= choice PrintChoices(
		"'Please rinse dishes before leaving in sink :)'\n\n\n The smiley face is doing a lot of unpaid emotional labor."
		"The Passive-Aggressive Post-it"
		290
		glitchText
		"Rinse everything in the house preemptively for a week." 0
		"Leave a post-it back, on a clean dish and slightly too pointed." 1
		"Just talk to your roommate about it, out loud, later." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The kitchen now resembles a ransom note made of passively aggressive politeness.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You overcorrected to outrun one sticky note.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 5 TAG_FIGHT)
			Print("You returned the passive-aggression with interest. That'll show 'em.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You used your words instead of a sticky note war. You both get pizza later and use paper plates.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent10)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Knock back in a rhythm, just to see if they knock again."
	)
	= choice PrintChoices(
		"You can hear your neighbors arguing again, muffled but unmistakable, through a wall that was not built for privacy."
		"The Wall Between Apartments"
		290
		glitchText
		"Turn up the TV as loud as you can and hope they can heart it." 0
		"Consider, seriously, banging on the wall. Do nothing instead." 1
		"Put on headphones and let it be someone else's problem." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("A wall-based friendship begins, tentatively, in Morse-adjacent taps.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You drowned out a sound you couldn't control.")
		)
		(case 1
			ApplyChoiceEffects(-5 5 -5 TAG_FREEZE)
			Print("You almost inserted yourself into a fight that isn't yours.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Not every wall's noise is yours to carry.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent11)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Launch a full forensic investigation, complete with a labeled evidence board."
	)
	= choice PrintChoices(
		"The thing you were saving for tomorrow is gone. No note, no explanation."
		"The Empty Fridge Shelf"
		290
		glitchText
		"Ask, directly, who ate it." 0
		"Say nothing and quietly recalculate your whole week's meals." 1
		"Decide it's fine, you didn't really need it anyway." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have connected several pieces of red string to absolutely nothing helpful.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You asked the small, direct question instead of stewing.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("One missing item rearranged your entire life, silently.")
		)
		(case 2
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You talked yourself out of a want that was real, and into being a doormat.")
		)
		)
	)
)
/******************************************************************************/
