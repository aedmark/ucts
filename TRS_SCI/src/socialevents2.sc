/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 9-16: each is a standalone
 SocialEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS2_SCRIPT)
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
(procedure public (SocialEvent13)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Commit fully: sprint over and hug a near-stranger."
	)
	= choice PrintChoices(
		"Someone you sort of know, from somewhere, makes eye contact across the room. Neither of you commits to a greeting."
		"The Awkward Wave"
		290
		glitchText
		"You suddenly find your phone extremely interesting." 0
		"Overcommit to a huge wave and walk over, unsure why." 1
		"Give a small, real wave and let it be whatever it is." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("This escalated several social contracts beyond what either of you agreed to. It feels right.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You disappeared into a screen to avoid making a decision.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You escalated a maybe into a whole interaction. It's awkward.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 5 TAG_SECURE)
			Print("A half-known person got a half-committed, perfectly adequate wave.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent14)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Produce an actual calculator and do the math out loud, in real time."
	)
	= choice PrintChoices(
		"You had a salad and water. Someone else had three cocktails. The bill's being split evenly, and everyone's already agreeing."
		"The Split-the-Bill Math"
		290
		glitchText
		"Agree to split evenly and say nothing about the math." 0
		"Suggest, casually, splitting it by what people actually got." 1
		"Pay your share silently and feel weird about it for days." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The table goes silent. You do not stop calculating.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You paid for cocktails you didn't order to keep the table easy.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You named the math out loud before it became a resentment. Three other people suddenly agree.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a bill you didn't agree with become a slow-burn grudge.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent15)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Post a single cryptic status implying you know things."
	)
	= choice PrintChoices(
		"You don't know when it happened. You just noticed, scrolling, that a number is one smaller than it used to be."
		"The Unfollow You Noticed"
		290
		glitchText
		"Spend twenty minutes trying to figure out who it was." 0
		"Post something extra likable to make up the difference." 1
		"Close the app. It's one person. It's fine." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You know anything. The mystery deepens for everyone, including you.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You ran a full investigation into a single missing follower.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You tried to outperform a number you can't actually see.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A number went down. The sky, notably, did not fall. Their loss.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent16)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Deliver an unhinged, overly dramatic weather forecast, mid-conversation."
	)
	= choice PrintChoices(
		"Third conversation this week that's stayed entirely on the weather. You're both clearly capable of more."
		"The Small Talk About The Weather (Again)"
		290
		glitchText
		"Keep it light and safe, matching their energy exactly." 0
		"Ask a real question and see what happens." 1
		"Let the conversation end there, like it always does." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'Sixty percent chance of feelings, with scattered vulnerability by evening.'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You stayed in the shallow end because it felt safer there.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You risked it all on a real question. You get a real answer (and a new friend).")
		)
		(case 2
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("Another conversation stayed exactly as deep as the last one. This is fine.")
		)
		)
	)
)
/******************************************************************************/
