/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents7.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js. Re-run that script after
 editing the source event data.

 BODY-zone events 24-27: each is a standalone
 BodyEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script BODYEVENTS7_SCRIPT)
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
