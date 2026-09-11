/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 9-12: each is a standalone
 SocialEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS3_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SocialEvent9)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start your own group chat. Name it something deeply petty."
	)
	= choice PrintChoices(
		"You find out, by accident, that there's a group chat that doesn't include you. It's been active for months."
		"The Group Chat Without You"
		290
		glitchText
		"Say nothing and quietly recalibrate every friendship in your life." 0
		"Act completely unbothered, performing it a little too well." 1
		"Notice it stings. Don't make it a bigger story than it is." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It has one member. It is thriving.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -20 TAG_FREEZE)
			Print("One missing chat became a referendum on everyone you know.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You built a very convincing case for a feeling you don't have.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A feeling, felt and not expanded, passes on its own.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent10)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Throw yourself a tiny, one-person parade around the living room."
	)
	= choice PrintChoices(
		"It's 8 PM. No text, no call, nothing. You know they're busy. You also know that nobody is THAT busy..."
		"The Birthday They Forgot"
		290
		glitchText
		"Refresh your phone every few minutes without admitting why." 0
		"Post something upbeat so nobody suspects you noticed." 1
		"Let yourself be a little sad about it. That's allowed." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The confetti will be found in strange places for weeks.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You kept checking for something you'd already concluded wasn't coming.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You broadcast 'fine' to cover a very 'not fine' feeling.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You didn't need to perform that you were okay, you arrived there naturally.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent11)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Walk over and loudly introduce yourself as if you'd never met."
	)
	= choice PrintChoices(
		"You didn't know they'd be here. They just walked in, laughing at something, not looking your way yet."
		"The Ex at the Party"
		290
		glitchText
		"Find a reason to be near the exit for the rest of the night." 0
		"Go say 'hi' first, overly warm, before they can find you." 1
		"Stay where you are. Say 'hi' if it happens naturally." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("This confuses everyone, including, eventually, you.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You mapped an escape route instead of a plan.")
		)
		(case 1
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You got there first so you could control the narrative. They still make you feel powerless.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You didn't need to manage the whole room to survive; they left before they even saw you.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Demand a formal, itemized list of exactly how much better."
	)
	= choice PrintChoices(
		"'You look so much better than you used to' is technically a compliment, but it somehow lands like backdoor insult."
		"The Compliment That Felt Like An Audit"
		290
		glitchText
		"Say 'thank you!' brightly and file the sting away for later." 0
		"Say, lightly, 'I looked good before, too!'" 1
		"Replay the sentence for the rest of the night." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("They did not prepare a spreadsheet. You are disappointed in their inability to verify their claims.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You smiled through the part that actually stung. Now your face hurts.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You corrected the record instead of just absorbing it. Mad respect.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("One sentence got more airtime in your head than the whole party. It's all you remember.")
		)
		)
	)
)
/******************************************************************************/
