/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 4-7: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS2_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (PublicEvent4)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Answer with a completely unrelated childhood memory instead."
	)
	= choice PrintChoices(
		"The interviewer asks you to describe your biggest weakness, and you have exactly one chance to answer this well."
		"The Interview With A Stranger Who Holds All The Cards"
		290
		glitchText
		"Give the fake answer everyone knows is fake because it's clearly a humblebrag in jackass clothing." 0
		"Blank out and give a rambling non-answer that goes nowhere." 1
		"Give an actual, specific weakness and what you're doing about it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The interviewer is now taking very different notes than they were a minute ago.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed vulnerability shaped like win. It wins you nothing but an eyeroll.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("You watched yourself not answer the question and could not intervene.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You told a stranger something true, on purpose, in a room built for performance.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent5)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Decide this is a sign and order something you've never tried."
	)
	= choice PrintChoices(
		"The order that arrives is not, in any respect, the order you placed. Correcting it means being 'A Problem' in front of everyone in line."
		"The Wrong Order"
		290
		glitchText
		"Eat it anyway and never mention it to anyone." 0
		"Send it back loudly enough that the whole counter hears the complaint." 1
		"Quietly flag the mistake and ask for it to be corrected." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's fine. Not what you wanted. An acceptable plot twist regardless.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(8 5 -10 TAG_FAWN)
			Print("You absorbed someone else's mistake so nobody's day would get slightly harder.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("The order got fixed. So did everyone's opinion of you, briefly.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You asked for the right thing without turning it into a scene.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent6)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Answer with something completely unhinged just to see what happens."
	)
	= choice PrintChoices(
		"The facilitator goes around the circle. You have about ten seconds to decide what a room full of near-strangers gets to know about you."
		"The Icebreaker"
		290
		glitchText
		"Give the safest, most forgettable answer possible." 0
		"Go completely blank when it's your turn and stall for time." 1
		"Say something small but actually true instead of the safe version." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The circle is now unsettled. You have made an impression. Possibly a good one; it's too early to tell.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You disappeared into the icebreaker without anyone noticing you were even in it.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("Eight seconds of silence in front of strangers. You counted every one. So did they.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave the room slightly more of the real thing than it was expecting.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent7)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Strike up a completely unnecessary full conversation for the rest of the hallway."
	)
	= choice PrintChoices(
		"They held the door. You said thanks. Now you're both walking the exact same direction down an empty hallway, well past the point where silence is normal."
		"The Person Who Held The Door"
		290
		glitchText
		"Suddenly develop an urgent need to check your phone until they're gone." 0
		"Keep walking in dead silence and hope the hallway ends soon." 1
		"Just laugh and say 'well, this is a long hallway.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now know a stranger's opinion on parking garages. Neither of you asked for this, but someone needed it all the same.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You performed an emergency to escape a hallway. The emergency was silence.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("Neither of you said anything else. The hallway did, eventually, end.")
		)
		(case 2
			ApplyChoiceEffects(-8 5 5 TAG_SECURE)
			Print("You named the awkward thing out loud and it immediately stopped being awkward.")
		)
		)
	)
)
/******************************************************************************/
