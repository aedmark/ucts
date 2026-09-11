/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents6.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 21-24: each is a standalone
 SocialEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS6_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SocialEvent21)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Walk over and ask, directly, if it's about you."
	)
	= choice PrintChoices(
		"A burst of laughter from a group nearby. You have no evidence it's about you. You're immediately certain it is."
		"The Loud Laugh Across The Room"
		290
		glitchText
		"Replay your last ten minutes of behavior for embarrassing material." 0
		"Change your position in the room, just in case." 1
		"Let the laugh be about literally anything else. It probably is." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It was not about you. Good, because that could have been embarrassing!")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You conducted a full review with zero actual evidence.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You relocated to escape a theory you invented about yourself.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Most laughter in a crowded room has nothing to do with you. Unless you're doing something funny.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent22)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Sit on the floor with great, theatrical dignity instead."
	)
	= choice PrintChoices(
		"You go to sit down. 'Oh, that one's taken,' said kindly, but you're now standing in a room full of seated people."
		"The Seat Saved For Someone Else"
		290
		glitchText
		"Laugh it off and hover near the wall instead." 0
		"Stand there a beat too long, unsure what to do with your body." 1
		"Ask, simply, if there's another seat open." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The floor, it turns out, has an excellent view.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You made your own displacement look easy.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("A missing chair became a small, public crisis.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You solved a chair problem with a chair question.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent23)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Comment on every photo with a single, ominous 'interesting.'"
	)
	= choice PrintChoices(
		"The photos are already up. It looks like it was a good one. You didn't know it was happening."
		"The Group Trip You Weren't Invited To"
		290
		glitchText
		"Scroll through every photo, cataloging who's in how many." 0
		"Like every photo enthusiastically, extra hearts included." 1
		"Close the app. Ask yourself later, calmly, if it's worth mentioning." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody knows what you meant. You are not entirely sure either. Regardless, it IS interesting.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You turned a vacation you weren't on into forensic evidence.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You applauded a trip that stung to see.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the feeling time before deciding what to do with it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent24)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply via a voicemail of your own, without listening to theirs first."
	)
	= choice PrintChoices(
		"A friend called instead of texting, which never happens. The voicemail icon has been sitting there for two hours."
		"The Voicemail You Haven't Listened To"
		290
		glitchText
		"Let it sit. Voicemails are for people who don't need you to reply fast." 0
		"Call back immediately, bracing yourself for bad news that might not be there." 1
		"Listen to it first. Then respond to what's actually there." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Two unheard voicemails now orbit each other, unopened, forever.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("An unopened voicemail can hold a lot of imagined weight.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FIGHT)
			Print("You armored up for a threat you hadn't confirmed yet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("The message turned out to be smaller than the dread around it.")
		)
		)
	)
)
/******************************************************************************/
