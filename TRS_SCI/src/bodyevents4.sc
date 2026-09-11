/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js. Re-run that script after
 editing the source event data.

 BODY-zone events 24-31: each is a standalone
 BodyEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script BODYEVENTS4_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (BodyEvent24)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start reorganizing your phone's apps by color."
	)
	= choice PrintChoices(
		"It's 2 AM. Your body is exhausted. Your brain has opened fourteen tabs and refuses to close any of them."
		"The Insomnia Loop"
		290
		glitchText
		"Keep lying there, perfectly still, willing sleep to just happen." 0
		"Give up and scroll your phone until your eyes finally give out first." 1
		"Get up, write down whatever's looping, and try again without it in your head." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Your home screen has never looked better. You are still awake.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -5 TAG_FREEZE)
			Print("Stillness didn't work. It never really does.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You traded one kind of awake for an eye-straining kind.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the thought somewhere to be that wasn't just your pillow.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent25)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Overcorrect by hugging everyone in the room, unprompted."
	)
	= choice PrintChoices(
		"Someone reaches out for a hug, or just to touch your arm, and your whole body stiffens for a second before you can stop it."
		"The Flinch"
		290
		glitchText
		"Force the hug to look natural and hope nobody noticed the flinch." 0
		"Get through the contact by mentally leaving the room while your body stays in it." 1
		"Let the flinch happen and just say, lightly, 'sorry, jumpy today.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Several people are now confused but more fulfilled. Your arms are tired.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed comfortable so well even you almost believed it.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("You were there for the hug. Technically.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You named it instead of hiding it, and the moment passed anyway.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent26)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Diagnose yourself with something dramatic via a search engine"
	)
	= choice PrintChoices(
		"A dull ache starts behind your eyes around hour six of sustained screen time, and it isn't going anywhere."
		"The Strain Headache"
		290
		glitchText
		"Push through it and keep working like the headache isn't happening." 0
		"Snap the laptop shut harder than necessary and complain to whoever's nearby." 1
		"Actually step away for ten whole minutes." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The internet has once again ruined your day. You are tired, not sick.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The headache did not care that you ignored it. It rarely does.")
		)
		(case 1
			ApplyChoiceEffects(-8 -10 0 TAG_FIGHT)
			Print("The laptop survived. Your reputation for calmness took the hit.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("Time away did more than the last two hours of pushing through did.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent27)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Immediately open a second bag out of pure defiance."
	)
	= choice PrintChoices(
		"You look down and an entire bag of something is gone. You don't remember deciding to eat any of it."
		"The Bag You Don't Remember Opening"
		290
		glitchText
		"Laugh it off if anyone mentions it and change the subject fast." 0
		"Say nothing to anyone, including yourself, and just move on like it didn't happen." 1
		"Notice, without judgment, that you might be stressed about something specific." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Neither bag solved anything. Both are now empty.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You made a joke out of it before anyone could ask a real question.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("Not talking about it didn't make it not have happened.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You looked past the bag to the actual thing underneath it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent28)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Set a recurring hourly alarm labeled 'BREATHE.'"
	)
	= choice PrintChoices(
		"You catch yourself mid-email, barely breathing, jaw clenched, for who knows how long."
		"The Shallow Breathing Mid-Task"
		290
		glitchText
		"Notice it, feel briefly alarmed, and keep typing exactly the same way." 0
		"Apologize to no one in particular for being 'a little tense today.'" 1
		"Stop, take three actual breaths, and unclench your shoulders on purpose." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It will go off in the middle of a meeting later. This is fine.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You clocked it and kept going anyway. The body filed a complaint it can't really enforce.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("You apologized for your own nervous system to an indifferent room.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Thirty seconds of actually breathing did more than the last hour of holding it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent29)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Drink a fourth coffee and decide this will definitely be the one that works."
	)
	= choice PrintChoices(
		"You slept eight hours. You wake up exactly as tired as when you laid down, if not more so."
		"The Exhaustion Sleep Didn't Fix"
		290
		glitchText
		"Push through the day on caffeine and sheer stubbornness." 0
		"Tell everyone who asks that you're 'just a little tired, no big deal.'" 1
		"Actually cancel one non-essential thing today to protect what's left of your energy." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It was not the one that worked. Your entire body is vibrating and still tired.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("You made it through. 'Made it through' is doing a lot of work in that sentence.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You minimized it into something small enough that nobody, including you, has to look at it.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You spent the energy on rest instead of one more obligation you didn't have room for.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent30)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Blow on your hands dramatically like you're in a survival documentary."
	)
	= choice PrintChoices(
		"Your hands have been cold for an hour, in a room that isn't. You notice it right as you're trying to sign something important."
		"The Cold Hands"
		290
		glitchText
		"Apologize for the shaky signature and joke about the room being cold." 0
		"Just push through and hope no one notices your hands." 1
		"Pause, shake it out, and just wait until your hands actually feel steady." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It didn't warm anything up. It did get a laugh.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You blamed the thermostat for something the thermostat had nothing to do with.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("Someone noticed. You'll never know if it mattered.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You waited for your own body instead of overriding it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent31)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Answer with a completely unrelated fact about your day instead."
	)
	= choice PrintChoices(
		"Someone asks if you're okay, and your throat closes around the answer before you can decide what it actually is."
		"The Tight Throat"
		290
		glitchText
		"Force out a bright 'I'm fine!' before the tightness can turn into anything else." 0
		"Say nothing and just nod until the moment passes on its own." 1
		"Say 'actually, not really' and let the sentence stop there for now." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody knows what just happened, including you. The moment is over, at least.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -10 TAG_FAWN)
			Print("You got the words out. They weren't the true ones. Nobody believes you.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The moment passed. The tightness didn't, not really.")
		)
		(case 2
			ApplyChoiceEffects(-10 3 10 TAG_SECURE)
			Print("Three honest words did more than a paragraph of fine would have.")
		)
		)
	)
)
/******************************************************************************/
