/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents6.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js. Re-run that script after
 editing the source event data.

 BODY-zone events 20-23: each is a standalone
 BodyEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script BODYEVENTS6_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (BodyEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Do one dramatic stretch and count it as a full workout."
	)
	= choice PrintChoices(
		"You had a plan to move your body today. Your body voted no."
		"The Skipped Workout"
		290
		glitchText
		"Guilt yourself about it for the rest of the evening." 0
		"Declare fitness dead to you and order takeout in protest." 1
		"Skip it without ceremony, no apology required." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Technically movement occurred. The bar has been set, and it is on the floor.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("You turned a rest day into a tribunal.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 5 TAG_FIGHT)
			Print("You staged a small rebellion against your own to-do list.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let 'not today' be a complete sentence.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent21)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Announce to the whole room that you are, in fact, fine."
	)
	= choice PrintChoices(
		"Your nose starts bleeding out of nowhere. Around people."
		"The Sudden Nosebleed"
		290
		glitchText
		"Play it off smoothly like this happens all the time." 0
		"Panic slightly and leave the room without explanation." 1
		"Tilt your head forward, breathe, and just deal with it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody was worried until you said something. Now everyone's worried.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -5 TAG_FAWN)
			Print("You narrated your own bleeding nose as a completely normal event.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FLIGHT)
			Print("You exited stage left, mid-sentence, mid-nosebleed.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You handled a small crisis like an adult, briefly, on purpose.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent22)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Learn to crochet and make everyone cozies for their mugs."
	)
	= choice PrintChoices(
		"Someone sets a mug down a little too hard and your entire body flinches like it's under attack."
		"The Full-Body Flinch"
		290
		glitchText
		"Laugh it off immediately, extra loud, extra fast." 0
		"Make an equally loud noise which is actually much louder and actually disruptive." 1
		"Let it be visible, no cover story required." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are the Andy Warhol of passive aggression.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You covered a real reaction with a performed one, instantly.")
		)
		(case 1
			ApplyChoiceEffects(-5 -15 0 TAG_FIGHT)
			Print("You made your nervous system's problem everyone else's problem.")
		)
		(case 2
			ApplyChoiceEffects(-5 -10 15 TAG_SECURE)
			Print("You let your body's honest reaction stand without editing it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent23)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Deep-clean the entire bathroom instead of just brushing your teeth."
	)
	= choice PrintChoices(
		"It's almost bedtime and you realize you haven't done a single part of your evening routine. Or your morning one."
		"The Unbrushed Teeth"
		290
		glitchText
		"Do the bare minimum and call it a wash." 0
		"Spiral about what this says about you as a person." 1
		"Just do it now, no narrative required." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The sink has never been shinier. Your teeth remain exactly as they were.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 0 -5 TAG_FLIGHT)
			Print("You lowered the bar until you could technically step over it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You turned one skipped routine into a referendum on your whole character.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You took care of yourself late instead of not at all.")
		)
		)
	)
)
/******************************************************************************/
