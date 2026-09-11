/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents7.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 the original js/events/home.js. Re-run that script after
 editing the source event data.

 HOME-zone events 24-27: each is a standalone
 HomeEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS7_SCRIPT)
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
		= glitchText "Start browsing apartments you can't afford, in a city you don't live in."
	)
	= choice PrintChoices(
		"An envelope from the landlord. The number at the bottom is $200 more than last year, effective next month."
		"The Rent Increase"
		290
		glitchText
		"Set the letter on the counter and don't look at the actual number again for a week." 0
		"Draft a furious response citing every unfixed thing in the apartment." 1
		"Sit down and actually rework the budget around the new number." 2
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
		= glitchText "Unplug everything in the apartment out of spite."
	)
	= choice PrintChoices(
		"The electric bill arrives at nearly twice last month's number, with no explanation you can find."
		"The Utility Bill That Doubled"
		290
		glitchText
		"Set up autopay so you never have to actually look at the number again." 0
		"Reread every line item like it's a puzzle with a solution." 1
		"Call and actually ask what changed, instead of guessing." 2
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
		= glitchText "Buy something small and unnecessary out of pure defiance."
	)
	= choice PrintChoices(
		"Your phone buzzes. Balance below zero. You do the math and know exactly which charge did it."
		"The Overdraft Notification"
		290
		glitchText
		"Text a family member asking, in a roundabout way, if they're doing okay 'financially, generally.'" 0
		"Don't check the account again until the fee posts." 1
		"Move what you can, call the bank, and actually look at the number." 2
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
		= glitchText "Audit every single subscription you have in the middle of the night."
	)
	= choice PrintChoices(
		"A charge you don't recognize turns out to be a subscription you forgot to cancel eight months ago."
		"The Forgotten Subscription Charge"
		290
		glitchText
		"Decide it's not worth the hassle of calling to complain." 0
		"Call and argue for a full refund on principle." 1
		"Cancel it, note the loss, and set a reminder to check for others." 2
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
