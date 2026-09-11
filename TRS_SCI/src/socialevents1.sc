/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 0-4: each is a standalone
 SocialEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SocialEvent0)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask them, with complete sincerity, for their thoughts on oat milk."
	)
	= choice PrintChoices(
		"Someone is standing exactly in front of the specific brand of oat milk you need. They are taking a very long time."
		"The Grocery Store Aisle"
		290
		glitchText
		"Pretend to look at regular milk until they leave." 0
		"Say 'Excuse me' using a voice three octaves higher than normal." 1
		"Abandon the oat milk. You didn't deserve it anyway." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now know more about oat milk than many food scientists. And have a new tennis partner.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 10 -10 TAG_FREEZE)
			Print("You sacrificed your time and dignity to avoid taking up space.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You utilized the Customer Service Voice. It was effective.")
		)
		(case 2
			ApplyChoiceEffects(15 5 -10 TAG_FLIGHT)
			Print("You punished yourself for a stranger's existence.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent1)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with a single, deeply dramatic tear emoji."
	)
	= choice PrintChoices(
		"A friend replied 'K.' to a vulnerable paragraph you sent them."
		"The Unread Notification"
		290
		glitchText
		"Start searching for a completely new friend group." 0
		"Send 4 memes immediately to lighten the mood." 1
		"Throw your phone into a soft pile of laundry." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("They have not responded. The emoji stands alone, weeping into the void.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 -15 -15 TAG_FIGHT)
			Print("You burned the bridge in your mind before there was even a threat to identify.")
		)
		(case 1
			ApplyChoiceEffects(10 10 -10 TAG_FAWN)
			Print("You performed as the Jester to avoid abandonment. They didn't react. You internalize it deeply.")
		)
		(case 2
			ApplyChoiceEffects(10 0 10 TAG_FLIGHT)
			Print("You successfully removed the object of your pain. Until you get up again.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent2)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with forty exclamation points, unprompted, for no reason at all."
	)
	= choice PrintChoices(
		"Your friend's reply is just 'oh nice' where it used to be three exclamation points."
		"The Uneven Text Energy"
		290
		glitchText
		"Reread every message you've sent them for the last month." 0
		"Match their energy exactly, one for one." 1
		"Text them something low-stakes and let it go." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have single-handedly restored the energy. Possibly too much of it.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You audited a friendship for crimes that don't exist.")
		)
		(case 1
			ApplyChoiceEffects(5 -10 0 TAG_FIGHT)
			Print("Mutually assured emotional disengagement.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 5 TAG_SECURE)
			Print("You extended trust without an audit.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent3)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Comment demanding a formal recount of the likes."
	)
	= choice PrintChoices(
		"You've been tagged. The angle is unkind. Twelve people have already liked it."
		"The Group Photo"
		290
		glitchText
		"Untag yourself and message the poster to take it down." 0
		"Leave it up and never look at that post again." 1
		"Zoom in and catalog every flaw for later." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The recount is denied. Someone photoshops a powdered wig on your head in said photo. Democracy has failed you again.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -10 5 TAG_FIGHT)
			Print("You defended your own image. Feels illegal, but it isn't.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -10 TAG_FLIGHT)
			Print("You conceded to the internet, this round.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -20 TAG_FREEZE)
			Print("You built a case against your own face. You won... but also... lost?")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent4)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start humming the elevator music, badly, out loud."
	)
	= choice PrintChoices(
		"Stuck in an elevator with a coworker for four floors. Neither of you has said anything for eleven seconds."
		"The Small Talk Void"
		290
		glitchText
		"Comment on the elevator's slowness like it's breaking news." 0
		"Stare at the floor numbers with religious intensity." 1
		"Let the silence be silence." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The coworker joins in. This is now, somehow, a duet.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -5 TAG_FAWN)
			Print("You filled the void with the safest possible noise. Coward.")
		)
		(case 1
			ApplyChoiceEffects(15 -5 0 TAG_FREEZE)
			Print("You willed the doors open through sheer discomfort.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 5 TAG_SECURE)
			Print("Eleven seconds of quiet did not erase you from this earth.")
		)
		)
	)
)
/******************************************************************************/
