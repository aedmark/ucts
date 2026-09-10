/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 0-7: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent0)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Sigh back, louder, escalating into a\nfull operatic aria."
	)
	= choice PrintChoices(
		"Your partner sighs audibly in the other room. You have absolutely zero context for why."
		"The Sigh"
		290
		glitchText
		"Assume it's your fault and quietly\nclean the kitchen." 0
		"Ask aggressively, 'IS SOMETHING\nWRONG?!'" 1
		"Put on noise-canceling headphones." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Neither of you knows what started this. Both of you are committed now.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 15 -20 TAG_FAWN)
			Print("You traded self-worth for perceived safety and clean dishes.")
		)
		(case 1
			ApplyChoiceEffects(-15 -25 5 TAG_FIGHT)
			Print("You struck first to avoid being struck. Classic.")
		)
		(case 2
			ApplyChoiceEffects(25 -10 0 TAG_FLIGHT)
			Print("Avoidance achieved. The tension is stored in your jaw.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent1)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Build an elaborate dish-based\nsculpture instead of washing them."
	)
	= choice PrintChoices(
		"Your partner says 'hey, whenever you get a chance' about the dishes. Their tone was completely neutral."
		"The Dishes in the Sink"
		290
		glitchText
		"Do the dishes at 11 PM, narrating\nyour resentment internally while\nsighing externally, and heavily." 0
		"Say 'I was GOING to' with more heat\nthan the sentence needed." 1
		"Leave the room to 'find something'\nfor four minutes. Flee state." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's actually kind of impressive. It does not count as washing them, though.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 10 -15 TAG_FAWN)
			Print("You complied loudly. Nobody is impressed.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 5 TAG_FIGHT)
			Print("The dishes were never the real defendant.")
		)
		(case 2
			ApplyChoiceEffects(10 -5 -5 TAG_FLIGHT)
			Print("A tactical retreat from a sink.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent2)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply only in cryptic, unrelated\nfortune-cookie wisdom."
	)
	= choice PrintChoices(
		"Your aunt just brought up something from Thanksgiving 2019. Nobody asked."
		"The Family Group Chat"
		290
		glitchText
		"Mute the chat and pretend your phone\nis broken." 0
		"Draft a measured correction, then\ndelete it three times." 1
		"Send a single laughing emoji and\nnothing else." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'The sink remembers what the heart forgets.' Nobody asked. Nobody replies.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 -5 0 TAG_FLIGHT)
			Print("You unplugged from the family server.")
		)
		(case 1
			ApplyChoiceEffects(20 5 -10 TAG_FREEZE)
			Print("You drafted diplomacy and shipped silence.")
		)
		(case 2
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You de-escalated with punctuation.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent3)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start narrating the drive like a\nhushed golf commentator."
	)
	= choice PrintChoices(
		"Twenty minutes home. Nobody has said anything since you left. You are replaying the entire evening."
		"The Silent Car Ride"
		290
		glitchText
		"Turn the radio up to fill the space." 0
		"Ask 'you okay?' four separate times." 1
		"Sit in it. Actually just sit in it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'And she signals... she signals early. Remarkable composure. Will she return her hands to ten and two? Stay tuned to find out...'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You outsourced the silence to a pop song.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You checked the temperature of a room that was comfortable, not cold.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("Twenty minutes of quiet did not, in fact, kill you.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent4)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Send one more message: just a\nsingle, staring emoji."
	)
	= choice PrintChoices(
		"You sent a long message to a parent, explaining how you actually feel. Marked read. Nothing since."
		"The Left-On-Read Text"
		290
		glitchText
		"Open your messages to literally\nanyone else and get absorbed in\nsomething safer." 0
		"Reread your message eleven times,\nhunting for the sentence that broke\nit." 1
		"Send nothing else. Let the silence\nbelong to them, not you." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The read receipt updates. The silence gets a face now.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You changed the channel on your own heart.")
		)
		(case 1
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You performed an autopsy on a conversation that isn't dead yet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You stopped staring at a doorknob that isn't yours to turn.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent5)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Answer back in an equally strange,\nunexplained voice of your own."
	)
	= choice PrintChoices(
		"Your parent picks up sounding smaller than you remember. You don't know why yet."
		"The Different Voice on the Phone"
		290
		glitchText
		"Immediately go bright and cheerful\nto lift the mood before you've even\nasked what's wrong." 0
		"Ask, flatly, exactly what's going\non. No preamble." 1
		"Say 'oh, okay' and let the\nconversation drift somewhere safer." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Neither of you addresses it. Some things stay sacred and weird.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You showed up as sunshine before you knew what kind of day it was.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 5 TAG_FIGHT)
			Print("You skipped the small talk. It cost you nothing you needed.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a real question evaporate into weather talk.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent6)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Hold a tiny, formal funeral for the\nleftovers."
	)
	= choice PrintChoices(
		"You made extra on purpose, left a note. It's still in the fridge, exactly where you left it."
		"The Leftover They Didn't Eat"
		290
		glitchText
		"Eat it yourself and say nothing\nabout the note." 0
		"Leave it in there for three more\ndays, unable to deal with it." 1
		"Ask, simply, if they want any\nleftovers before you eat the rest." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Several words were said. None of them were 'I'm sorry.'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You absorbed the disappointment along with the leftovers.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("The food and the feeling both went untouched.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A question, asked plainly, is not an accusation. Plus, you get dinner.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent7)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Slide a folded paper airplane of\nconcern underneath it."
	)
	= choice PrintChoices(
		"It's usually open. Tonight it's closed, and you don't know why."
		"The Closed Bedroom Door"
		290
		glitchText
		"Stand outside it for a full minute,\ndeciding nothing." 0
		"Knock and immediately apologize for\nwhatever it is." 1
		"Knock. Ask if everything is okay." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It does not fly well on carpet. The gesture remains.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You waited outside a door you could have just knocked on.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You apologized before you knew the charge.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You offered an opening instead of an assumption.")
		)
		)
	)
)
/******************************************************************************/
