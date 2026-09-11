/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents8.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 28-31: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS8_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent28)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Watch seventeen YouTube tutorials on fixing it yourself."
	)
	= choice PrintChoices(
		"The washing machine makes a sound it shouldn't and then stops entirely. The repair quote costs almost as much as a new one."
		"The Broken Appliance No One Can Afford to Fix"
		290
		glitchText
		"Leave the wet laundry in it for two days while you decide what to do." 0
		"Tell whoever asks that it's 'basically fine, just being weird.'" 1
		"Get a second quote and actually compare the real numbers." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now understand washing machine repair on a conceptual level. The machine remains tangibly broken.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("The laundry did not decide anything on your behalf.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("It is not fine. You've just decided saying so is easier.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You treated it like a decision instead of a crisis.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent29)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Offer to lend double what they asked for, out of guilt."
	)
	= choice PrintChoices(
		"A family member asks to borrow money you don't really have room to lend, in a tone that makes it hard to say no."
		"The Family Loan Request"
		290
		glitchText
		"Say yes immediately and figure out the math later." 0
		"Say no bluntly, then feel terrible about how it came out." 1
		"Say what you can actually afford, clearly, without over-explaining." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Your bank account did not consent to this decision. You may never financially recover from this.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -15 TAG_FAWN)
			Print("You said yes with your mouth before your budget got a vote.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("The no was correct. The delivery could use some work.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave an honest number instead of an apologetic one.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent30)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Propose settling it with a coin flip."
	)
	= choice PrintChoices(
		"Whoever you live with wants to talk about the shared expenses again. The conversation you've both been avoiding for a month."
		"The Split Bill Argument"
		290
		glitchText
		"Say you're too tired to get into it tonight, again." 0
		"Bring up every uneven expense from the last six months at once." 1
		"Actually sit down, split it fairly, and let the conversation be boring." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody agreed to this. You flipped it anyway. Somehow it worked flawlessly, anyway.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -8 0 TAG_FLIGHT)
			Print("You bought one more night of not talking about it. The bill didn't wait.")
		)
		(case 1
			ApplyChoiceEffects(-12 -15 0 TAG_FIGHT)
			Print("You were right about most of it. It still didn't land well.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 8 TAG_SECURE)
			Print("Nothing dramatic happened. That was sort of the point.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent31)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Offer the pet seventeen different foods in immediate succession."
	)
	= choice PrintChoices(
		"Your dog, or cat, walks right past a full food bowl for the second day in a row, and the vet visit you can't quite afford yet keeps not happening."
		"The Pet Skipping Meals"
		290
		glitchText
		"Tell yourself it's probably nothing and keep watching from across the room." 0
		"Post about it online and let strangers' reassurance stand in for an actual appointment." 1
		"Call and ask what a visit actually costs, then book it anyway." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nothing worked. You now have six open cans and one unimpressed animal.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("Watching didn't make the bowl any emptier or any fuller.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("Forty people said it's probably fine. None of them are a vet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let the real number decide instead of your fear of it.")
		)
		)
	)
)
/******************************************************************************/
