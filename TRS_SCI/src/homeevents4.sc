/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 24-31: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS4_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (HomeEvent24)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start browsing apartments you can't\nafford, in a city you don't live in."
	)
	= choice PrintChoices(
		"An envelope from the landlord. The number at the bottom is $200 more than last year, effective next month."
		"The Rent Increase"
		290
		glitchText
		"Set the letter on the counter and\ndon't look at the actual number\nagain for a week." 0
		"Draft a furious response citing\nevery unfixed thing in the\napartment." 1
		"Sit down and actually rework the\nbudget around the new number." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You've mentally moved to a lake house. Your higher rent is still due next month.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -8 TAG_FREEZE)
			Print("The number didn't go away. It just waited.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("All true. None of it will lower the rent. Or your blood pressure.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You made the number smaller by making it real instead of avoided.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent25)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Unplug everything in the apartment\nout of spite."
	)
	= choice PrintChoices(
		"The electric bill arrives at nearly twice last month's number, with no explanation you can find."
		"The Utility Bill That Doubled"
		290
		glitchText
		"Set up autopay so you never have to\nactually look at the number again." 0
		"Reread every line item like it's a\npuzzle with a solution." 1
		"Call and actually ask what changed,\ninstead of guessing." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now sit in the dark, on principle, having solved nothing.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("Out of sight. Still, technically, out of your account.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("There is no puzzle. There is just a bigger number.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("Turns out asking a real question gets you a real answer.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent26)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Buy something small and unnecessary\nout of pure defiance."
	)
	= choice PrintChoices(
		"Your phone buzzes. Balance below zero. You do the math and know exactly which charge did it."
		"The Overdraft Notification"
		290
		glitchText
		"Text a family member asking, in a\nroundabout way, if they're doing\nokay 'financially, generally.'" 0
		"Don't check the account again until\nthe fee posts." 1
		"Move what you can, call the bank,\nand actually look at the number." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The balance is more negative. You feel powerful. Briefly.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You asked about someone else's money to avoid saying anything about yours.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The fee posted anyway. It always does.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You looked right at it, which turns out to be the hard part.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent27)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Audit every single subscription you\nhave in the middle of the night."
	)
	= choice PrintChoices(
		"A charge you don't recognize turns out to be a subscription you forgot to cancel eight months ago."
		"The Forgotten Subscription Charge"
		290
		glitchText
		"Decide it's not worth the hassle of\ncalling to complain." 0
		"Call and argue for a full refund on\nprinciple." 1
		"Cancel it, note the loss, and set a\nreminder to check for others." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You found six more. You are now afraid of your own bank statement. And your goldfish-like memory.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("You let it go, mostly because asking felt like more of a cost than the actual money lost.")
		)
		(case 1
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("You got half the money back and a mild reputation with customer service.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You closed the leak instead of just being mad about the water.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (HomeEvent28)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Watch seventeen YouTube tutorials on\nfixing it yourself."
	)
	= choice PrintChoices(
		"The washing machine makes a sound it shouldn't and then stops entirely. The repair quote costs almost as much as a new one."
		"The Broken Appliance No One Can Afford to Fix"
		290
		glitchText
		"Leave the wet laundry in it for two\ndays while you decide what to do." 0
		"Tell whoever asks that it's\n'basically fine, just being weird.'" 1
		"Get a second quote and actually\ncompare the real numbers." 2
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
		= glitchText "Offer to lend double what they asked\nfor, out of guilt."
	)
	= choice PrintChoices(
		"A family member asks to borrow money you don't really have room to lend, in a tone that makes it hard to say no."
		"The Family Loan Request"
		290
		glitchText
		"Say yes immediately and figure out\nthe math later." 0
		"Say no bluntly, then feel terrible\nabout how it came out." 1
		"Say what you can actually afford,\nclearly, without over-explaining." 2
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
		= glitchText "Propose settling it with a coin\nflip."
	)
	= choice PrintChoices(
		"Whoever you live with wants to talk about the shared expenses again. The conversation you've both been avoiding for a month."
		"The Split Bill Argument"
		290
		glitchText
		"Say you're too tired to get into it\ntonight, again." 0
		"Bring up every uneven expense from\nthe last six months at once." 1
		"Actually sit down, split it fairly,\nand let the conversation be boring." 2
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
		= glitchText "Offer the pet seventeen different\nfoods in immediate succession."
	)
	= choice PrintChoices(
		"Your dog, or cat, walks right past a full food bowl for the second day in a row, and the vet visit you can't quite afford yet keeps not happening."
		"The Pet Skipping Meals"
		290
		glitchText
		"Tell yourself it's probably nothing\nand keep watching from across the\nroom." 0
		"Post about it online and let\nstrangers' reassurance stand in for\nan actual appointment." 1
		"Call and ask what a visit actually\ncosts, then book it anyway." 2
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
