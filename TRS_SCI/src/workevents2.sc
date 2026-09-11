/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 9-17: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS2_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (WorkEvent9)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with a photo of an actual empty desk, for evidence."
	)
	= choice PrintChoices(
		"You set your status to 'in a meeting' twenty minutes ago and never actually joined one. Someone just pinged you directly."
		"The Away Message"
		290
		glitchText
		"Type 'sorry, just wrapped up, what's up!' like it's true." 0
		"Let the message sit unread for another eleven minutes." 1
		"Reply honestly: 'Just saw this, give me a minute.'" 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The desk is, notably, yours. This raises more questions than it answers. You're okay with this.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You performed availability you didn't have.")
		)
		(case 1
			ApplyChoiceEffects(15 -5 0 TAG_FLIGHT)
			Print("You bought time you'll have to pay back later.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You told a true, small, unremarkable thing.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent10)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply-All to the entire company. Let everyone share in this learning experience."
	)
	= choice PrintChoices(
		"A coworker loops your manager into a thread about a mistake that was mostly, but not entirely, your fault."
		"The CC'd Boss"
		290
		glitchText
		"Immediately reply-all with a full, apologetic breakdown." 0
		"Reply-all clarifying, calmly, which parts were actually yours." 1
		"Leave the thread unanswered and refresh your inbox every four minutes." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Democracy, but for blame. Nobody wins but nobody loses, either. Except maybe some brain cells.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You built the case against yourself before anyone asked for one.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You drew a line around the blame instead of absorbing all of it.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You watched the thread instead of joining it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent11)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Stand up and take a long, silent bow."
	)
	= choice PrintChoices(
		"In the meeting, your manager describes your idea as something the team 'landed on together.' Nobody looks at you."
		"The Unclaimed Credit"
		290
		glitchText
		"Say nothing. Add it to the list you're keeping in your head." 0
		"Laugh it off and agree it really was a group effort." 1
		"Mention afterward, privately and plainly, that the idea was yours." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Three people clap before realizing they don't know why. Doesn't matter; validation is transferable but NOT refundable.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You filed it under evidence, case still open.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You co-signed the erasure to keep the room comfortable.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 15 TAG_SECURE)
			Print("You said the true thing to one person instead of nobody. Feel better?")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start typing back before they've even sent anything."
	)
	= choice PrintChoices(
		"Your manager's typing indicator appears, disappears, appears again. Three times. Still no message."
		"The Typing Indicator"
		290
		glitchText
		"Stare at the little dots like they're a polygraph." 0
		"Close the tab so you can't watch it happen." 1
		"Keep working. Whatever it is will say itself eventually." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Your dots meet their dots. A standoff of pure anticipation. Who will win? ...")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You interrogated punctuation that hadn't arrived yet.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -5 TAG_FLIGHT)
			Print("You removed the evidence, not the feeling.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let an unfinished sentence stay unfinished.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent13)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Set your own auto-reply to something ominous and cryptic."
	)
	= choice PrintChoices(
		"You email a colleague something urgent. The auto-reply says they've been out since yesterday. Nobody told you."
		"The Out-of-Office Reply"
		290
		glitchText
		"Apologize to their inbox for bothering them at all." 0
		"Send a slightly sharp message to whoever should have flagged this." 1
		"Sit with the urgent thing, now un-urgent, doing nothing." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It now reads: 'I am also out. Of my mind. Please check back later.'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You apologized to an away message.")
		)
		(case 1
			ApplyChoiceEffects(-5 -15 5 TAG_FIGHT)
			Print("You aimed the frustration at the actual gap, not yourself.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("The fire kept burning with nobody assigned to it. Insurance won't cover it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent14)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Challenge them to a desk-chair race down the hallway."
	)
	= choice PrintChoices(
		"Someone who started three weeks ago just solved, casually, the thing that's been quietly humiliating you for a month."
		"The New Hire Who's Already Better At This"
		290
		glitchText
		"Smile, say 'nice,' and mentally recalculate your entire worth." 0
		"Ask them to walk you through it, overpraising every step." 1
		"Ask them to walk you through it. Just that." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You lose. You also nearly take out a filing cabinet. It's a bonding experience.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -20 TAG_FREEZE)
			Print("You ran a full audit off one data point. Maybe they can't do math?")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You turned learning into a small performance of gratitude.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 15 TAG_SECURE)
			Print("You let not-knowing be a normal thing.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent15)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with only a countdown timer emoji, repeated forty times."
	)
	= choice PrintChoices(
		"'Small change:' the deadline that was next month is now Friday. The email has an exclamation point in it."
		"The Deadline Moved Up"
		290
		glitchText
		"Reply 'no problem!' before you've checked if it's a problem." 0
		"Push back, in writing, on what's actually possible by Friday." 1
		"Open the file. Close the file. Open a different file." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody has ever communicated dread this efficiently. You're promoted to CEO.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 15 -15 TAG_FAWN)
			Print("You agreed to the math before calculating it. Your team hates you now. Probably.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You said the number out loud. The exclamation point has no power over you.")
		)
		(case 2
			ApplyChoiceEffects(25 0 -15 TAG_FREEZE)
			Print("You orbited the work without landing on it. Nothing gets done.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent16)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Lean into the frozen frame. Hold the expression on purpose now."
	)
	= choice PrintChoices(
		"Your video froze mid-sentence on an expression you didn't choose. Twelve people saw it for four full seconds."
		"The Zoom Freeze"
		290
		glitchText
		"Open with a self-deprecating joke about your wifi." 0
		"Turn your camera off for the rest of the call." 1
		"Say 'sorry, it's a freezing in here' and keep going." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have become a meme in your own meeting. People appreciate your whimsy, but respect you slightly less.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You pre-apologized for a router's decision.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You removed the risk by removing yourself.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A frozen face is not, it turns out, a permanent record. It's universal.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent17)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Enter dramatically, out of breath, with an incredulous look in your eye."
	)
	= choice PrintChoices(
		"You join the call two minutes late. Everyone's already talking. Nobody pauses to catch you up."
		"The Two Minutes Late"
		290
		glitchText
		"Whisper 'sorry, sorry' three times while finding your seat." 0
		"Sit silently, too embarrassed to ask what you missed." 1
		"Ask, once, what you missed. Move on." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("'Quick! There's no time to explain!'")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You apologized for the two minutes and the seven after it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You chose confusion over one slightly awkward question. Phew!")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You caught up effortlessly. Three people quietly thanked you because they weren't paying attention.")
		)
		)
	)
)
/******************************************************************************/
