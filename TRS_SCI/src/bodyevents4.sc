/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 the original js/events/body.js. Re-run that script after
 editing the source event data.

 BODY-zone events 12-15: each is a standalone
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
(procedure public (BodyEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Attempt to crack a joint that does not, physiologically, crack."
	)
	= choice PrintChoices(
		"You've cracked your knuckles, neck, and back four times each in the last hour. It's becoming a whole thing."
		"The Cracking Joints"
		290
		glitchText
		"Keep doing it, quieter, so no one notices." 0
		"Crack something loudly on purpose, right at someone." 1
		"Get up and actually move for a minute instead." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It did not crack. You will try again in eleven minutes.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You hid the fidget instead of asking what it was fidgeting about.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("You turned a nervous habit into a small act of war.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the restlessness an actual job to do.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent13)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Buy one of every single kind out of sheer decision fatigue."
	)
	= choice PrintChoices(
		"You are standing in front of forty kinds of the same cereal and you cannot make your body pick one."
		"The Overwhelm in the Cereal Aisle"
		290
		glitchText
		"Grab the one closest to your hand and leave fast." 0
		"Stand there until someone else's cart forces you to move." 1
		"Breathe, pick the familiar one on purpose, keep walking." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now own more cereal than a household requires. Problem technically solved.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 0 -5 TAG_FLIGHT)
			Print("You escaped the aisle. The cereal was incidental.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("Your body vetoed the decision and nobody overruled it.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 5 TAG_SECURE)
			Print("You made a small decision on purpose. It counts.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent14)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Construct an elaborate, confident lie about how you got it."
	)
	= choice PrintChoices(
		"You notice a bruise on your arm. You have absolutely no memory of how it got there."
		"The Bruise You Don't Remember"
		290
		glitchText
		"Cover it and move on, it's fine, you're fine." 0
		"Poke it a few times, oddly detached from the pain." 1
		"Actually stop and wonder what's been going on with you lately." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody asked. You told them anyway. The story was *very* good.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You concealed the evidence and skipped the question it was asking.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -15 TAG_FREEZE)
			Print("You observed your own body like it belonged to someone else.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 15 TAG_SECURE)
			Print("You treated a small mystery as information instead of noise.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (BodyEvent15)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Strike up a loud, urgent conversation about the weather."
	)
	= choice PrintChoices(
		"The elevator doors close and your heart rate spikes for no reason you can name."
		"The Racing Heart in the Elevator"
		290
		glitchText
		"Smile at the stranger next to you like everything's normal." 0
		"Get out at the wrong floor just to escape the box." 1
		"Count your breaths until the doors open again." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody wanted this conversation. It happened anyway. Heart rate: unchanged.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 10 -5 TAG_FAWN)
			Print("You performed calm at a stranger who will never know otherwise.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 0 TAG_FLIGHT)
			Print("You solved the feeling by relocating the problem, briefly.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("You rode it out instead of running from it.")
		)
		)
	)
)
/******************************************************************************/
