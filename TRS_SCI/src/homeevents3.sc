/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 16-23: each is a standalone
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
(procedure public (HomeEvent16)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Call back using a dramatically\ndifferent, unexplained voice."
	)
	= choice PrintChoices(
		"Two missed calls and a voicemail you haven't pressed play on yet."
		"The Missed Call From Mom"
		290
		glitchText
		"Let the voicemail sit unheard for\nthe rest of the day." 0
		"Call back immediately, bracing for\nwhatever it is." 1
		"Listen to the voicemail first. Then\ndecide." 2
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
		= glitchText "Build a small, dignified pillow fort\nin the empty space and watch Netflix\nin it."
	)
	= choice PrintChoices(
		"They're traveling this week. The apartment is exactly the same size and feels twice as big."
		"The Empty Side Of The Bed"
		290
		glitchText
		"Leave the TV on all night just to\nfill the quiet." 0
		"Text constant updates about your\nevening, needing the thread to stay\nbusy." 1
		"Let the apartment be quiet. It's\ntemporary." 2
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
		= glitchText "Shake the envelope and try to guess\nthe contents like a game show."
	)
	= choice PrintChoices(
		"A letter arrives from someone you haven't spoken to in years. You've been staring at the envelope, unopened, for ten minutes."
		"The Return Address You Don't Recognize"
		290
		glitchText
		"Put it in a drawer. Deal with it\n'later.'" 0
		"Open it and immediately plan an\napologetic, generous reply." 1
		"Open it. Read it. Feel whatever you\nfeel." 2
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
		= glitchText "Attempt to forge a matching\nhandwriting style for your own notes\nfrom now on."
	)
	= choice PrintChoices(
		"You found it while cleaning out a drawer. The handwriting stops you cold for a second you didn't expect."
		"The Recipe Card In Her Handwriting"
		290
		glitchText
		"Put it back exactly where it was and\nclose the drawer." 0
		"Make the recipe tonight. Let it mean\nwhatever it means." 1
		"Get frustrated at how much a card\ncan do to you." 2
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
(procedure public (HomeEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Redraw the seating chart yourself,\nexiling the two feuding relatives to\nthe garage."
	)
	= choice PrintChoices(
		"You've been placed, again, between the two relatives most likely to start a debate over the mashed potatoes."
		"The Holiday Seating Chart"
		290
		glitchText
		"Prepare a mental list of neutral\ntopics to redirect toward." 0
		"Accept your fate and mentally leave\nthe table early." 1
		"Re-assign yourself to the kids table\nand enjoy yourself." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The garage now has better conversation than the dining room.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 10 -10 TAG_FAWN)
			Print("You showed up armed with small talk as crowd control.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You were present in body only, and only barely. Nobody noticed.")
		)
		(case 2
			ApplyChoiceEffects(-5 -5 10 TAG_SECURE)
			Print("You provided your own accommodation and now your nephews think you're cool.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent21)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Leap up and greet them in full\ndetective-noir monologue."
	)
	= choice PrintChoices(
		"Someone's home later than expected. You're awake now, doing math you don't want to be doing."
		"The Sound Of The Garage Door At 2 AM"
		290
		glitchText
		"Lie perfectly still, pretending to\nbe asleep, wide awake." 0
		"Get up and greet them cheerfully,\nhiding that you were worried." 1
		"Ask in the morning if everything's\nokay." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'You've got some explaining to do! At 2 AM! ...In this economy!'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You performed sleep you weren't getting.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You covered the worry with a smile at 2 AM.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You waited for daylight and asked a plain question. They just lost track of time.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent22)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start referring to the wall as 'the\nincident' from now on."
	)
	= choice PrintChoices(
		"You come home to a wall that's a different color than it was this morning. Nobody mentioned it was happening."
		"The New Paint Color They Didn't Ask About"
		290
		glitchText
		"Say you love it, immediately, before\nyou've decided if you do." 0
		"Ask why you weren't part of the\nconversation." 1
		"Say nothing and just quietly start\ndisliking the room." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The wall is aware of its new title. It does not react.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You approved a decision before forming an opinion.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You named the part that actually bothered you: not the color.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a wall become a symbol instead of just a wall.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent23)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Narrate the empty apartment like a\nnature documentary."
	)
	= choice PrintChoices(
		"Everyone's finally gone. The apartment is quiet in a way that feels, for one disorienting second, like something's wrong."
		"The Silence After The Front Door Closes"
		290
		glitchText
		"Immediately turn on background noise\nto fill the silence." 0
		"Start texting people to check if\neveryone got home okay." 1
		"Let the silence be silence for a\nminute before doing anything." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'And here, in its natural habitat, the human finally exhales.'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("You filled a quiet room out of habit, not need.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You reached outward to avoid sitting in the quiet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("An empty room turned out to be just an empty room.")
		)
		)
	)
)
/******************************************************************************/
