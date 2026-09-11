/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents7.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 24-27: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS7_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (PublicEvent24)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Push every button on the panel, just to see what happens."
	)
	= choice PrintChoices(
		"It's a short ride, but the silence with the one other passenger has gone on for one floor too long to still feel normal."
		"The Silent Elevator"
		290
		glitchText
		"Say something bright and pointless just to fill the air." 0
		"Study the floor numbers with the intensity of someone defusing a bomb." 1
		"Let the silence just be a normal, unremarkable silence." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are now stopping on every floor. The other passenger has begun to visibly reconsider their life choices.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You performed small talk for two floors so the silence wouldn't have to belong to anyone.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -3 TAG_FREEZE)
			Print("You survived an elevator ride by pretending very hard to be a wall fixture.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("Two people stood quietly near each other for a few seconds. Nothing happened. Nothing needed to.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent25)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Take a bow, as if the drop were a rehearsed part of the show."
	)
	= choice PrintChoices(
		"It hit the floor loudly and rolled somewhere. Multiple strangers definitely saw. You have to decide how big a deal this is."
		"The Thing You Dropped In Public"
		290
		glitchText
		"Laugh it off way harder than it deserves so no one thinks you're bothered." 0
		"Grab it fast and pretend it never happened at all." 1
		"Pick it up, shrug, and move on without narrating it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Someone actually clapped. You are now a performance artist.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You performed 'unbothered' at a volume the moment didn't actually require.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You erased a thirty-cent incident with the urgency of a crime scene.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("A thing fell. You picked it up.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent26)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask an employee, with real hope, if you can still buy your two items outside."
	)
	= choice PrintChoices(
		"It goes off while you're two items into your shopping list, and everyone has to decide, together, how seriously to take it."
		"The Fire Alarm Mid-Errand"
		290
		glitchText
		"Apologize to the employee at the door like the alarm is somehow your fault." 0
		"Sprint outside faster than the actual emergency protocol requires." 1
		"Walk out calmly with everyone else and wait for the all-clear." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("They said no. You asked again. They said no again, more slowly.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized for a fire alarm you did not build, own, or trigger.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 3 TAG_FLIGHT)
			Print("You treated a possible drill like a certain inferno. Better safe, you tell yourself.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You treated an alarm like an alarm: worth responding to, not worth panicking over.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent27)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Applaud their confidence and ask for tips on doing it yourself."
	)
	= choice PrintChoices(
		"Someone just walked straight past eleven people, including you, and set their items down at the front like it's nothing."
		"The Line-Cutter"
		290
		glitchText
		"Say nothing and let it happen so you don't have to be The Person Who Says Something." 0
		"Call it out loudly enough that the whole line hears you." 1
		"Say, calmly, that the line ends back there." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("They actually gave you a few. You will never use them. You appreciate them anyway.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("You watched a small injustice happen live and filed it under things you'll think about later when you should be asleep.")
		)
		(case 1
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("The line briefly became a jury. The cutter lost. So did the general mood of the store.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You named the small unfairness out loud, without turning it into a war.")
		)
		)
	)
)
/******************************************************************************/
