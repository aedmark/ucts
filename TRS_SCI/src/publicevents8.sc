/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents8.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 28-31: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS8_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (PublicEvent28)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask the room, out loud, if anyone else wants to talk about what's on the TV."
	)
	= choice PrintChoices(
		"The TV is playing something no one chose, at a volume no one agreed to, and everyone is very carefully not looking at each other."
		"The Waiting Room"
		290
		glitchText
		"Smile blandly at anyone who glances your way, just in case." 0
		"Stare at your phone so hard you could probably describe none of what's on it later." 1
		"Just sit there, bored, and let the boredom be boring." 2
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
		= glitchText "Try to upstage the performer entirely."
	)
	= choice PrintChoices(
		"They've locked eyes with you specifically, mid-act, in front of a small crowd, and are clearly hoping you'll play along."
		"The Street Performer's Eye Contact"
		290
		glitchText
		"Go along with whatever bit they're doing, way past your comfort line." 0
		"Break eye contact and walk fast, out of the crowd entirely." 1
		"Play along a little, on your own terms, and enjoy it." 2
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
		= glitchText "Lean all the way in and give them a full store tour anyway."
	)
	= choice PrintChoices(
		"You're wearing a shirt that happens to be the same color as the store's uniform, and a stranger is now approaching you with a question and a basket."
		"The 'Do You Work Here?' Moment"
		290
		glitchText
		"Try your best to actually help them find what they need." 0
		"Say nothing and duck around the next aisle before they finish the sentence." 1
		"Say 'sorry, I don't work here, but I think it's in aisle six.'" 2
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
		= glitchText "Take a bow for the sneeze, like it was the whole point of coming today."
	)
	= choice PrintChoices(
		"It happens right in the middle of a silence you did not create and loud enough that it may as well have been an announcement."
		"The Sneeze In The Quiet Room"
		290
		glitchText
		"Apologize three separate times to three separate directions." 0
		"Freeze completely and hope everyone just forgets it happened." 1
		"Say a quiet 'excuse me' and let the room move on, because it will." 2
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
