/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 13-16: each is a standalone
 SocialEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS4_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
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
