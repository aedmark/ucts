/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents5.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js. Re-run that script after
 editing the source event data.

 BODY-zone events 16-19: each is a standalone
 BodyEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script BODYEVENTS5_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (BodyEvent16)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Buy an entire tube of anti-itch cream for a psychosomatic itch."
	)
	= choice PrintChoices(
		"Something itches. You've checked twice. There is nothing there. It still itches."
		"The Itch That Isn't There"
		290
		glitchText
		"Scratch it anyway, repeatedly, in front of everyone." 0
		"Ignore it and hope it forgets about you first." 1
		"Notice it's stress, not skin, and address the actual thing." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The itch was never dermatological. The cream is very soothing regardless.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You enabled a feeling that had no physical location.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You waited out a sensation that was never going to negotiate.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You correctly diagnosed a body signal for once.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent17)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Set six hourly water alarms you will immediately start ignoring."
	)
	= choice PrintChoices(
		"You realize the water bottle on your desk has been full since yesterday morning. You feel vaguely awful and can't say why."
		"The Forgotten Water Bottle"
		290
		glitchText
		"Drink coffee instead, that's basically water." 0
		"Feel guilty about it and do nothing differently." 1
		"Drink the whole thing right now, slowly." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Your phone now nags you about hydration. You remain thirsty.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You addressed thirst with a substance that specializes in the opposite.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized to your own body and changed nothing.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You met an actual need instead of a performed one.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent18)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Do twenty jumping jacks in a supply closet to 'reset your energy.'"
	)
	= choice PrintChoices(
		"Your energy leaves your body like water out of a bathtub, all at once."
		"The Crash"
		290
		glitchText
		"Chug an energy drink and pretend the crash isn't happening." 0
		"Push through on pure spite and bad posture." 1
		"Take five real minutes doing nothing." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are now tired in a completely new and different way. Innovative.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You borrowed energy from tonight-you, who was not consulted and does not appreciate it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You ran the tank to empty and called it discipline.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let the crash happen instead of arguing with it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent19)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Punch a couch cushion with genuine, focused intent."
	)
	= choice PrintChoices(
		"You notice your fists have been closed, nails in your palms, for who knows how long."
		"The Clenched Fists"
		290
		glitchText
		"Unclench them fast and act like it never happened." 0
		"Squeeze harder, actually, see what that does." 1
		"Open them slowly and shake them out on purpose." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The cushion did not deserve this. The cushion will recover.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You hid the evidence and skipped the follow-up question.")
		)
		(case 1
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("You gave the anger a place to go. It chose your own hands. You have have broken some skin.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You released something before it decided to dig in further.")
		)
		)
	)
)
/******************************************************************************/
