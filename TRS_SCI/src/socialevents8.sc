/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents8.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 29-32: each is a standalone
 SocialEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS8_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SocialEvent29)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start giving them unsolicited advice back, immediately, at the same volume."
	)
	= choice PrintChoices(
		"You mention, in passing, that you're a little stressed. What you get back is a twelve-point plan for fixing your entire life."
		"The Unsolicited Advice"
		290
		glitchText
		"Nod, say 'that's a good point,' and absorb advice you didn't ask for." 0
		"Cut them off and say you weren't actually asking for a solution." 1
		"Say gently that you just wanted to vent, not fix it right now. But thank you." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Neither of you asked for this exchange. It is happening regardless.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You outsourced twelve minutes of your afternoon to someone else's certainty.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("True, and it landed like a slap instead of a boundary. And made your stress feel worse.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You named exactly what you needed instead of quietly enduring the wrong thing.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent30)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Post something deliberately, aggressively mundane in response."
	)
	= choice PrintChoices(
		"You open the app for one notification and forty minutes later you're deep in someone else's vacation photos, doing math on their life against yours."
		"The Comparison Scroll"
		290
		glitchText
		"Keep scrolling well past the point it started feeling bad." 0
		"Like everything, generously, while feeling worse with every tap." 1
		"Close the app and name, out loud, that it's a highlight reel, not a life." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Twelve people liked a photo of your ceiling. Justice, kinda.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -10 TAG_FREEZE)
			Print("You know more about a stranger's trip to Portugal than you do about how you're actually doing right now.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You performed happiness for people while quietly auditing your own life against theirs.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You put the comparison down instead of finishing the whole plate of it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent31)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Try to convince everyone your account got hacked."
	)
	= choice PrintChoices(
		"You mean to vent about your coworker to one friend. You send it to the group chat that includes that coworker."
		"The Wrong Group Chat Text"
		290
		glitchText
		"Stare at the message, unsent-but-sent, for a full minute before doing anything." 0
		"Immediately send eight apology messages in a row." 1
		"Send one clear apology, own it, and stop typing." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody believes this. You did not really expect them to.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -8 TAG_FREEZE)
			Print("Time did not undo it. It rarely does.")
		)
		(case 1
			ApplyChoiceEffects(8 8 -10 TAG_FAWN)
			Print("Eight messages later, everyone has now read about the mistake three extra times.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 8 TAG_SECURE)
			Print("One honest sentence did more than eight nervous ones would have. The coworker actually admits they deserved it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent32)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "'Accidentally' plan something that conflicts with their next couple's outing."
	)
	= choice PrintChoices(
		"Your friend has a new partner, and lately every group hangout quietly becomes about the two of them."
		"The New Partner Absorbing the Friend Group"
		290
		glitchText
		"Say how much you love the new couple, louder than you feel it." 0
		"Start finding reasons to skip the group hangouts for a while." 1
		"Tell your friend directly that you miss the just-us version of hanging out." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Petty, effective, and everyone can tell exactly what you did.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You clapped for something that, honestly, you have mixed feelings about.")
		)
		(case 1
			ApplyChoiceEffects(-5 -8 0 TAG_FLIGHT)
			Print("Easier than saying anything. Also, quietly, lonelier.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You said how you felt instead of just fading out of the group chat.")
		)
		)
	)
)
/******************************************************************************/
