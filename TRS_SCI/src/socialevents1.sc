/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 the original js/events/social.js. Re-run that script after
 editing the source event data.

 SOCIAL-zone events 0-8: each is a standalone
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
		= glitchText "Ask them, with complete sincerity,\nfor their thoughts on oat milk."
	)
	= choice PrintChoices(
		"Someone is standing exactly in front of the specific brand of oat milk you need. They are taking a very long time."
		"The Grocery Store Aisle"
		290
		glitchText
		"Pretend to look at regular milk\nuntil they leave." 0
		"Say 'Excuse me' using a voice three\noctaves higher than normal." 1
		"Abandon the oat milk. You didn't\ndeserve it anyway." 2
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
		= glitchText "Reply with a single, deeply dramatic\ntear emoji."
	)
	= choice PrintChoices(
		"A friend replied 'K.' to a vulnerable paragraph you sent them."
		"The Unread Notification"
		290
		glitchText
		"Start searching for a completely new\nfriend group." 0
		"Send 4 memes immediately to lighten\nthe mood." 1
		"Throw your phone into a soft pile of\nlaundry." 2
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
		= glitchText "Reply with forty exclamation points,\nunprompted, for no reason at all."
	)
	= choice PrintChoices(
		"Your friend's reply is just 'oh nice' where it used to be three exclamation points."
		"The Uneven Text Energy"
		290
		glitchText
		"Reread every message you've sent\nthem for the last month." 0
		"Match their energy exactly, one for\none." 1
		"Text them something low-stakes and\nlet it go." 2
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
		= glitchText "Comment demanding a formal recount\nof the likes."
	)
	= choice PrintChoices(
		"You've been tagged. The angle is unkind. Twelve people have already liked it."
		"The Group Photo"
		290
		glitchText
		"Untag yourself and message the\nposter to take it down." 0
		"Leave it up and never look at that\npost again." 1
		"Zoom in and catalog every flaw for\nlater." 2
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
		= glitchText "Start humming the elevator music,\nbadly, out loud."
	)
	= choice PrintChoices(
		"Stuck in an elevator with a coworker for four floors. Neither of you has said anything for eleven seconds."
		"The Small Talk Void"
		290
		glitchText
		"Comment on the elevator's slowness\nlike it's breaking news." 0
		"Stare at the floor numbers with\nreligious intensity." 1
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
(procedure public (SocialEvent5)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Claim, boldly, that you were\nactually a time traveler that day."
	)
	= choice PrintChoices(
		"Someone brings up a specific thing you said, off-hand, eight months ago. You have no memory of saying it."
		"The Friend Who Remembers Everything"
		290
		glitchText
		"Panic-scan your own memory for\ncontext you don't have." 0
		"Agree enthusiastically, like you\nabsolutely remember." 1
		"Say 'I don't actually remember that,\ntell me more,' and mean it." 2
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
		= glitchText "Show up in full costume, several\ngenres removed from the event's\ntheme."
	)
	= choice PrintChoices(
		"You said yes to something two weeks ago. It's tonight. Every fiber of you wants to cancel."
		"The RSVP You Regret"
		290
		glitchText
		"Draft a vague excuse about not\nfeeling well." 0
		"Text 'actually can't make it' with\nzero elaboration and hit send." 1
		"Go anyway. Perform enthusiasm you do\nnot currently possess." 2
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
		= glitchText "Order enough food for four and\nnarrate an imaginary dinner party."
	)
	= choice PrintChoices(
		"You get to the restaurant first. You sit alone at a table for four, aware of exactly how alone you look."
		"The Table for One"
		290
		glitchText
		"Stare at your phone intensely so you\nlook busy, not waiting." 0
		"Apologize to the host for taking up\na table for four." 1
		"Sit there. Look around. Let it be\nfine." 2
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
		= glitchText "Stand up anyway and deliver your own\nspeech, uninvited, mid-reception."
	)
	= choice PrintChoices(
		"You've known the groom for a decade. Someone he met two years ago is giving the speech instead."
		"The Best Man Speech You Weren't Asked to Give"
		290
		glitchText
		"Smile through the whole thing while\ndoing quiet, silent math." 0
		"Decide to bring it up with him,\ngently, another day." 1
		"Clap the loudest and mean absolutely\nnone of it." 2
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
