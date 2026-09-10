/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 8-15: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS2_SCRIPT)
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
		= glitchText "Reply with a single dramatic\nmovie-trailer voice line."
	)
	= choice PrintChoices(
		"Four words. No context. Sent an hour ago, and you've only just seen it."
		"The 'We Need To Talk' Text"
		290
		glitchText
		"Spend the hour composing worst-case\nscenarios instead of replying." 0
		"Reply with three apologies before\nyou know what for." 1
		"Reply: 'Okay. I'm here when you're\nready.'" 2
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
		= glitchText "Cover the entire kitchen in post-its\nof your own."
	)
	= choice PrintChoices(
		"'Please rinse dishes before leaving in sink :)'\n\n\n The smiley face is doing a lot of unpaid emotional labor."
		"The Passive-Aggressive Post-it"
		290
		glitchText
		"Rinse everything in the house\npreemptively for a week." 0
		"Leave a post-it back, on a clean\ndish and slightly too pointed." 1
		"Just talk to your roommate about it,\nout loud, later." 2
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
		= glitchText "Knock back in a rhythm, just to see\nif they knock again."
	)
	= choice PrintChoices(
		"You can hear your neighbors arguing again, muffled but unmistakable, through a wall that was not built for privacy."
		"The Wall Between Apartments"
		290
		glitchText
		"Turn up the TV as loud as you can\nand hope they can heart it." 0
		"Consider, seriously, banging on the\nwall. Do nothing instead." 1
		"Put on headphones and let it be\nsomeone else's problem." 2
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
		= glitchText "Launch a full forensic\ninvestigation, complete with a\nlabeled evidence board."
	)
	= choice PrintChoices(
		"The thing you were saving for tomorrow is gone. No note, no explanation."
		"The Empty Fridge Shelf"
		290
		glitchText
		"Ask, directly, who ate it." 0
		"Say nothing and quietly recalculate\nyour whole week's meals." 1
		"Decide it's fine, you didn't really\nneed it anyway." 2
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
(procedure public (HomeEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Answer your own question instead, at\ngreat length, to the room."
	)
	= choice PrintChoices(
		"You asked. They said 'fine' and kept scrolling. That was ten minutes ago."
		"The Unanswered 'How Was Your Day'"
		290
		glitchText
		"Sit in the same room in total\nsilence, waiting for more." 0
		"Fill the silence with details about\nyour own day, unprompted." 1
		"Let 'fine' be enough for now. Try\nagain later." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The room does not respond either. Hurtful, but fair.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You waited for a door that wasn't going to open, anyway.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You performed the conversation for the both of you.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Not every silence needs to be filled immediately or be analyzed.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent13)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Install a tiny sign declaring the\nthermostat a neutral zone."
	)
	= choice PrintChoices(
		"It's been adjusted three times today. Nobody has said a word about it out loud."
		"The Thermostat War"
		290
		glitchText
		"Set it where you want it and leave a\nnote explaining why." 0
		"Just wear a sweater and say nothing,\nforever. Probably." 1
		"Leave it wherever they last set it,\nevery time." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Switzerland would be proud. (Nobody honors the treaty).")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You made your preference visible instead of silent. The note lasted 2 hours before it went missing.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You sacrificed your physical comfort to avoid a two-minute conversation.")
		)
		(case 2
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You denied yourself a temperature change just to keep the peace.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent14)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start a dramatic heist-movie-style\nplan to retrieve it."
	)
	= choice PrintChoices(
		"You lent it three weeks ago. You need it now. Asking for it back feels, somehow, enormously selfish."
		"The Borrowed Thing, Not Returned"
		290
		glitchText
		"Buy a replacement instead of asking\nfor it back." 0
		"Ask for it back with four qualifiers\nand two apologies." 1
		"Ask for it back plainly. It's yours." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The plan involves zero actual heisting and a lot of standing outside their door whining.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You paid money to avoid a ten-second conversation.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You apologized for wanting your own thing back.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("This did not, in fact, end the friendship. They bought you dinner in appreciation.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent15)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Photobomb the memory. Mentally. With\nyour current face."
	)
	= choice PrintChoices(
		"It's on the hallway wall, everyone smiling, from a year you remember very differently than the picture suggests."
		"The Photo From An Easier Year"
		290
		glitchText
		"Stop and stare at it longer than you\nmeant to, every time." 0
		"Consider taking it down. Don't, yet." 1
		"Let the photo be a photo, not a\nverdict on the year." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Past-you and present-you now occupy the same photograph, spiritually. So there.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You keep visiting a version of the year that didn't happen.")
		)
		(case 1
			ApplyChoiceEffects(0 -5 5 TAG_FIGHT)
			Print("You noticed the mismatch and let yourself notice it.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("One picture doesn't get to outvote your memory.")
		)
		)
	)
)
/******************************************************************************/
