/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents5.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 16-19: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS5_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent16)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Call back using a dramatically different, unexplained voice."
	)
	= choice PrintChoices(
		"Two missed calls and a voicemail you haven't pressed play on yet."
		"The Missed Call From Mom"
		290
		glitchText
		"Let the voicemail sit unheard for the rest of the day." 0
		"Call back immediately, bracing for whatever it is." 1
		"Listen to the voicemail first. Then decide." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are now, for reasons unclear, doing a full Irish accent.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("An unopened voicemail can hold a lot of imagined weight.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You armored up before you even knew what for.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Information turned out to be less scary than the dread. Have fun at Disney World!")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent17)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Build a small, dignified pillow fort in the empty space and watch Netflix in it."
	)
	= choice PrintChoices(
		"They're traveling this week. The apartment is exactly the same size and feels twice as big."
		"The Empty Side Of The Bed"
		290
		glitchText
		"Leave the TV on all night just to fill the quiet." 0
		"Text constant updates about your evening, needing the thread to stay busy." 1
		"Let the apartment be quiet. It's temporary." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The fort has excellent structural integrity and zero strategic purpose. ")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("You drowned a feeling in a sitcom rerun.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You kept the connection loud so it wouldn't feel absent.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Quiet turned out to be survivable, if not fun.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent18)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Shake the envelope and try to guess the contents like a game show."
	)
	= choice PrintChoices(
		"A letter arrives from someone you haven't spoken to in years. You've been staring at the envelope, unopened, for ten minutes."
		"The Return Address You Don't Recognize"
		290
		glitchText
		"Put it in a drawer. Deal with it 'later.'" 0
		"Open it and immediately plan an apologetic, generous reply." 1
		"Open it. Read it. Feel whatever you feel." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have guessed 'ferret' three times. You weren't even close.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You filed the unknown away instead of facing it.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You started drafting amends before reading the actual letter.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let the envelope just be information.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent19)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Attempt to forge a matching handwriting style for your own notes from now on."
	)
	= choice PrintChoices(
		"You found it while cleaning out a drawer. The handwriting stops you cold for a second you didn't expect."
		"The Recipe Card In Her Handwriting"
		290
		glitchText
		"Put it back exactly where it was and close the drawer." 0
		"Make the recipe tonight. Let it mean whatever it means." 1
		"Get frustrated at how much a card can do to you." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Your grocery lists now look faintly, movingly ancestral.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You returned the moment to the drawer, unopened.")
		)
		(case 1
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You let an ordinary moment hold an old, specific grief.")
		)
		(case 2
			ApplyChoiceEffects(5 -5 -5 TAG_FIGHT)
			Print("You argued with your own feelings and lost. Now you're sad, hungry, and the drawer is still dirty.")
		)
		)
	)
)
/******************************************************************************/
