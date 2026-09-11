/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 0-4: each is a standalone
 SelfEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SelfEvent0)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Track down the exact cleaning product, immediately, mid-errand."
	)
	= choice PrintChoices(
		"You catch a whiff of a specific cleaning product that smells exactly like your childhood home."
		"The Nostalgic Smell"
		290
		glitchText
		"Hold your breath until you pass out." 0
		"Cry silently in a bathroom stall." 1
		"Make a dark, self-deprecating joke to a coworker." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now own four bottles of it. This is a lot of bottles.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(30 0 -5 TAG_FLIGHT)
			Print("You physically rejected the memory.")
		)
		(case 1
			ApplyChoiceEffects(-20 -10 20 TAG_SECURE)
			Print("You processed an emotion privately. It's okay.")
		)
		(case 2
			ApplyChoiceEffects(5 -15 -10 TAG_FIGHT)
			Print("You weaponized the trauma for comedy.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent1)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Introduce yourself to the reflection like you've never met."
	)
	= choice PrintChoices(
		"You catch your reflection in a dark window and don't recognize yourself for a full second."
		"The Mirror"
		290
		glitchText
		"Immediately look away and think about anything else." 0
		"Stare longer, looking for evidence of who you used to be." 1
		"Say something kind to it, out loud, even though it's weird." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'Hi, I'm you.' The reflection does not seem convinced either.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You declined an invitation from your own face.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You interrogated a witness who can't testify.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 15 TAG_SECURE)
			Print("You addressed yourself like a person worth addressing. Because you are.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent2)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Write a formal reply to nineteen-year-old you, dated properly."
	)
	= choice PrintChoices(
		"You find something you wrote at nineteen. It is more honest than anything you've said out loud this year."
		"The Old Journal Entry"
		290
		glitchText
		"Close it immediately and never mention this happened." 0
		"Feel a specific, targeted contempt for who you used to be." 1
		"Let it be true. You used to know something you forgot." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have now started a pen-pal relationship with your past self. Neat!")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FLIGHT)
			Print("You returned the evidence to its tomb.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -20 TAG_FIGHT)
			Print("You picked a fight with a nineteen-year-old and lost.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 20 TAG_SECURE)
			Print("You let an old truth back into the room. It feels warm.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent3)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Get up and reorganize the spice rack, aggressively, at 3 AM."
	)
	= choice PrintChoices(
		"No reason. No noise. Just awake, and your brain has already opened seventeen tabs of decade-old conversations."
		"The 3 AM Wake-Up"
		290
		glitchText
		"Start drafting an apology text you'll delete by morning." 0
		"Get up and reorganize something small and physical." 1
		"Name it: this is just cortisol, not prophecy." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The spices are now alphabetized. You are not more at peace, but the spices are.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -15 TAG_FAWN)
			Print("You litigated a conversation the other person forgot happened.")
		)
		(case 1
			ApplyChoiceEffects(10 0 0 TAG_FLIGHT)
			Print("You redirected 3 AM into a drawer.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("You out-argued your own nervous system and won. You sleep in victory.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent4)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Declare it a national holiday, just for yourself, effective immediately."
	)
	= choice PrintChoices(
		"Nothing is scheduled. Nobody needs anything from you. This is, somehow, the hardest part of the week."
		"The Empty Sunday"
		290
		glitchText
		"Invent an urgent task to feel useful again." 0
		"Scroll until the day disappears without you in it." 1
		"Sit with the unscheduled hour and let it be boring." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Attendance is mandatory. You are the only attendee.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You manufactured a purpose to avoid the quiet.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -15 TAG_FLIGHT)
			Print("You outsourced eight hours to a feed.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 15 TAG_SECURE)
			Print("You survived free time without earning it first. You didn't explode.")
		)
		)
	)
)
/******************************************************************************/
