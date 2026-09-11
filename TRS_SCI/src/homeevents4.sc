/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 12-15: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS4_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Answer your own question instead, at great length, to the room."
	)
	= choice PrintChoices(
		"You asked. They said 'fine' and kept scrolling. That was ten minutes ago."
		"The Unanswered 'How Was Your Day'"
		290
		glitchText
		"Sit in the same room in total silence, waiting for more." 0
		"Fill the silence with details about your own day, unprompted." 1
		"Let 'fine' be enough for now. Try again later." 2
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
		= glitchText "Install a tiny sign declaring the thermostat a neutral zone."
	)
	= choice PrintChoices(
		"It's been adjusted three times today. Nobody has said a word about it out loud."
		"The Thermostat War"
		290
		glitchText
		"Set it where you want it and leave a note explaining why." 0
		"Just wear a sweater and say nothing, forever. Probably." 1
		"Leave it wherever they last set it, every time." 2
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
		= glitchText "Start a dramatic heist-movie-style plan to retrieve it."
	)
	= choice PrintChoices(
		"You lent it three weeks ago. You need it now. Asking for it back feels, somehow, enormously selfish."
		"The Borrowed Thing, Not Returned"
		290
		glitchText
		"Buy a replacement instead of asking for it back." 0
		"Ask for it back with four qualifiers and two apologies." 1
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
		= glitchText "Photobomb the memory. Mentally. With your current face."
	)
	= choice PrintChoices(
		"It's on the hallway wall, everyone smiling, from a year you remember very differently than the picture suggests."
		"The Photo From An Easier Year"
		290
		glitchText
		"Stop and stare at it longer than you meant to, every time." 0
		"Consider taking it down. Don't, yet." 1
		"Let the photo be a photo, not a verdict on the year." 2
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
