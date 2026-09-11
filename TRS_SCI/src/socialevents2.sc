/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 5-8: each is a standalone
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
(procedure public (SocialEvent5)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Claim, boldly, that you were actually a time traveler that day."
	)
	= choice PrintChoices(
		"Someone brings up a specific thing you said, off-hand, eight months ago. You have no memory of saying it."
		"The Friend Who Remembers Everything"
		290
		glitchText
		"Panic-scan your own memory for context you don't have." 0
		"Agree enthusiastically, like you absolutely remember." 1
		"Say 'I don't actually remember that, tell me more,' and mean it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("They seem to accept this explanation more readily than expected. Someone asks if you've ever met Elvis.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 -5 0 TAG_FREEZE)
			Print("You audited a version of yourself with no paper trail and came up empty handed.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You co-signed a memory that isn't yours.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let not-knowing be an ordinary, survivable thing. Because it is.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent6)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Show up in full costume, several genres removed from the event's theme."
	)
	= choice PrintChoices(
		"You said yes to something two weeks ago. It's tonight. Every fiber of you wants to cancel."
		"The RSVP You Regret"
		290
		glitchText
		"Draft a vague excuse about not feeling well." 0
		"Text 'actually can't make it' with zero elaboration and hit send." 1
		"Go anyway. Perform enthusiasm you do not currently possess." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are the only knight at what turns out to be a beach party.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 -5 -5 TAG_FLIGHT)
			Print("You built an exit out of half a lie. Nobody believes you.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 10 TAG_FIGHT)
			Print("You chose a white lie over a padded truth. You spend the night regretting it.")
		)
		(case 2
			ApplyChoiceEffects(10 15 -20 TAG_FAWN)
			Print("You showed up as the version of you that RSVPs on time. You have fun, anyway.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent7)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Order enough food for four and narrate an imaginary dinner party."
	)
	= choice PrintChoices(
		"You get to the restaurant first. You sit alone at a table for four, aware of exactly how alone you look."
		"The Table for One"
		290
		glitchText
		"Stare at your phone intensely so you look busy, not waiting." 0
		"Apologize to the host for taking up a table for four." 1
		"Sit there. Look around. Let it be fine." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Your imaginary guests are excellent listeners and terrible tippers.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You performed occupation to avoid looking like a 'loser.'")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You apologized for the size of a table you didn't choose.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Being visibly alone turned out fine; you got fresh breadsticks and didn't have to share any.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SocialEvent8)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Stand up anyway and deliver your own speech, uninvited, mid-reception."
	)
	= choice PrintChoices(
		"You've known the groom for a decade. Someone he met two years ago is giving the speech instead."
		"The Best Man Speech You Weren't Asked to Give"
		290
		glitchText
		"Smile through the whole thing while doing quiet, silent math." 0
		"Decide to bring it up with him, gently, another day." 1
		"Clap the loudest and mean absolutely none of it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The DJ, unsure what else to do, plays dramatic entrance music.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You ran a decade-long audit during a five-minute toast.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 5 TAG_FIGHT)
			Print("You chose to name the hurt instead of just carrying it.")
		)
		(case 2
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You applauded harder than you felt to hide what you felt.")
		)
		)
	)
)
/******************************************************************************/
