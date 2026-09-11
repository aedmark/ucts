/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js. Re-run that script after
 editing the source event data.

 BODY-zone events 8-11: each is a standalone
 BodyEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script BODYEVENTS3_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (BodyEvent8)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Buy the extremely bitter anti-biting nail polish."
	)
	= choice PrintChoices(
		"You look down mid-meeting and realize you've bitten a nail down past comfortable. You don't remember starting."
		"The Bitten Nails"
		290
		glitchText
		"Hide your hands and keep going like nothing happened." 0
		"Sit on your hands for the rest of the meeting." 1
		"Notice it, put your hands flat on the table, and let it be a fact." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now know exactly how bitter it is. Repeatedly.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You concealed the evidence. The habit remains unfiled.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -5 TAG_FREEZE)
			Print("You restrained the symptom instead of asking about the cause.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You caught yourself mid-habit without turning it into a crisis.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent9)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Turn the bouncing into a full drum solo on the desk."
	)
	= choice PrintChoices(
		"Your leg has been bouncing under the desk for forty-five minutes. You only notice when someone asks if you're okay."
		"The Restless Leg"
		290
		glitchText
		"Say you're fine, laugh, keep bouncing." 0
		"Get up and pace the hallway instead." 1
		"Name it: 'I think I'm anxious about something.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Everyone in the room now knows exactly how you feel. Loudly.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You answered the question the body was already answering.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 0 TAG_FLIGHT)
			Print("You gave the energy somewhere bigger to go.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let the leg tell on you, and believed it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent10)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Self-medicate with four different remedies simultaneously."
	)
	= choice PrintChoices(
		"You've had the same low-grade cold for three weeks. It appeared right after things got hard and hasn't left since."
		"The Cold That Won't Quit"
		290
		glitchText
		"Power through, colds are for people with time off." 0
		"Blame the office AC loudly to anyone who'll listen." 1
		"Actually take the day, and actually rest on it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are now buzzing gently and no less congested. A wash.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You outsourced your recovery to a future that keeps not arriving.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You found a villain. It wasn't the AC.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 15 TAG_SECURE)
			Print("You let your immune system have the meeting instead of you.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent11)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Convince yourself you're still dreaming for a solid ninety seconds."
	)
	= choice PrintChoices(
		"You wake up two hours late. Your alarm went off. You have no memory of turning it off."
		"The Alarm You Don't Remember Silencing"
		290
		glitchText
		"Rush out the door pretending this is fine and normal." 0
		"Lie there a while longer, the day's already ruined anyway." 1
		"Get up slowly, text that you're late, actually wake up first." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You were not dreaming. You were extremely, demonstrably late.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -5 TAG_FAWN)
			Print("You performed 'on schedule' at a very unconvincing level.")
		)
		(case 1
			ApplyChoiceEffects(5 -5 -10 TAG_FREEZE)
			Print("You let one bad start decide the whole day's verdict.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let your body finish waking up before you asked it to perform.")
		)
		)
	)
)
/******************************************************************************/
