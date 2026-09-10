/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 24-31: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS4_SCRIPT)
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
		= glitchText "Push every button on the panel, just\nto see what happens."
	)
	= choice PrintChoices(
		"It's a short ride, but the silence with the one other passenger has gone on for one floor too long to still feel normal."
		"The Silent Elevator"
		290
		glitchText
		"Say something bright and pointless\njust to fill the air." 0
		"Study the floor numbers with the\nintensity of someone defusing a\nbomb." 1
		"Let the silence just be a normal,\nunremarkable silence." 2
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
		= glitchText "Take a bow, as if the drop were a\nrehearsed part of the show."
	)
	= choice PrintChoices(
		"It hit the floor loudly and rolled somewhere. Multiple strangers definitely saw. You have to decide how big a deal this is."
		"The Thing You Dropped In Public"
		290
		glitchText
		"Laugh it off way harder than it\ndeserves so no one thinks you're\nbothered." 0
		"Grab it fast and pretend it never\nhappened at all." 1
		"Pick it up, shrug, and move on\nwithout narrating it." 2
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
		= glitchText "Ask an employee, with real hope, if\nyou can still buy your two items\noutside."
	)
	= choice PrintChoices(
		"It goes off while you're two items into your shopping list, and everyone has to decide, together, how seriously to take it."
		"The Fire Alarm Mid-Errand"
		290
		glitchText
		"Apologize to the employee at the\ndoor like the alarm is somehow your\nfault." 0
		"Sprint outside faster than the\nactual emergency protocol requires." 1
		"Walk out calmly with everyone else\nand wait for the all-clear." 2
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
		= glitchText "Applaud their confidence and ask for\ntips on doing it yourself."
	)
	= choice PrintChoices(
		"Someone just walked straight past eleven people, including you, and set their items down at the front like it's nothing."
		"The Line-Cutter"
		290
		glitchText
		"Say nothing and let it happen so you\ndon't have to be The Person Who Says\nSomething." 0
		"Call it out loudly enough that the\nwhole line hears you." 1
		"Say, calmly, that the line ends back\nthere." 2
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
(procedure public (PublicEvent28)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask the room, out loud, if anyone\nelse wants to talk about what's on\nthe TV."
	)
	= choice PrintChoices(
		"The TV is playing something no one chose, at a volume no one agreed to, and everyone is very carefully not looking at each other."
		"The Waiting Room"
		290
		glitchText
		"Smile blandly at anyone who glances\nyour way, just in case." 0
		"Stare at your phone so hard you\ncould probably describe none of\nwhat's on it later." 1
		"Just sit there, bored, and let the\nboredom be boring." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("One person, astonishingly, did. You now have opinions about a show you weren't watching an hour ago.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You maintained a friendly face for a room full of people who were not looking at you closely enough to notice.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("You scrolled through a phone you weren't actually reading for the better part of an hour.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You let a waiting room be exactly as uneventful as it actually was.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent29)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Try to upstage the performer\nentirely."
	)
	= choice PrintChoices(
		"They've locked eyes with you specifically, mid-act, in front of a small crowd, and are clearly hoping you'll play along."
		"The Street Performer's Eye Contact"
		290
		glitchText
		"Go along with whatever bit they're\ndoing, way past your comfort line." 0
		"Break eye contact and walk fast, out\nof the crowd entirely." 1
		"Play along a little, on your own\nterms, and enjoy it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The crowd's attention has shifted to you completely. The performer looks personally betrayed.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You gave a stranger's act more of your dignity than the bit actually required.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You exited a public performance as if you were guilty of a crime you didn't actually commit.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You let yourself be a little bit silly in public, on purpose, for no reason but that it was fun.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent30)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Lean all the way in and give them a\nfull store tour anyway."
	)
	= choice PrintChoices(
		"You're wearing a shirt that happens to be the same color as the store's uniform, and a stranger is now approaching you with a question and a basket."
		"The 'Do You Work Here?' Moment"
		290
		glitchText
		"Try your best to actually help them\nfind what they need." 0
		"Say nothing and duck around the next\naisle before they finish the\nsentence." 1
		"Say 'sorry, I don't work here, but I\nthink it's in aisle six.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You just gave a stranger better customer service than most actual employees. No one paid you. You feel weirdly great about it.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You performed employment at a store that has never once paid you.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You evaded a mild case of mistaken identity like it was a subpoena.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You corrected the mistake and still helped, because both things were easy to do at once.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent31)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Take a bow for the sneeze, like it\nwas the whole point of coming today."
	)
	= choice PrintChoices(
		"It happens right in the middle of a silence you did not create and loud enough that it may as well have been an announcement."
		"The Sneeze In The Quiet Room"
		290
		glitchText
		"Apologize three separate times to\nthree separate directions." 0
		"Freeze completely and hope everyone\njust forgets it happened." 1
		"Say a quiet 'excuse me' and let the\nroom move on, because it will." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Someone actually said 'bless you' with real enthusiasm. A win, of sorts, for the sneeze.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized for a biological reflex like it was a breach of contract.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -3 TAG_FREEZE)
			Print("You went very still, as if stillness now could undo a sneeze from three seconds ago.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You let a small, human noise be exactly that small.")
		)
		)
	)
)
/******************************************************************************/
