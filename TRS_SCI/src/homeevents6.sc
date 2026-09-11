/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents6.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 20-23: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS6_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Redraw the seating chart yourself, exiling the two feuding relatives to the garage."
	)
	= choice PrintChoices(
		"You've been placed, again, between the two relatives most likely to start a debate over the mashed potatoes."
		"The Holiday Seating Chart"
		290
		glitchText
		"Prepare a mental list of neutral topics to redirect toward." 0
		"Accept your fate and mentally leave the table early." 1
		"Re-assign yourself to the kids table and enjoy yourself." 2
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
		= glitchText "Leap up and greet them in full detective-noir monologue."
	)
	= choice PrintChoices(
		"Someone's home later than expected. You're awake now, doing math you don't want to be doing."
		"The Sound Of The Garage Door At 2 AM"
		290
		glitchText
		"Lie perfectly still, pretending to be asleep, wide awake." 0
		"Get up and greet them cheerfully, hiding that you were worried." 1
		"Ask in the morning if everything's okay." 2
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
		= glitchText "Start referring to the wall as 'the incident' from now on."
	)
	= choice PrintChoices(
		"You come home to a wall that's a different color than it was this morning. Nobody mentioned it was happening."
		"The New Paint Color They Didn't Ask About"
		290
		glitchText
		"Say you love it, immediately, before you've decided if you do." 0
		"Ask why you weren't part of the conversation." 1
		"Say nothing and just quietly start disliking the room." 2
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
		= glitchText "Narrate the empty apartment like a nature documentary."
	)
	= choice PrintChoices(
		"Everyone's finally gone. The apartment is quiet in a way that feels, for one disorienting second, like something's wrong."
		"The Silence After The Front Door Closes"
		290
		glitchText
		"Immediately turn on background noise to fill the silence." 0
		"Start texting people to check if everyone got home okay." 1
		"Let the silence be silence for a minute before doing anything." 2
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
