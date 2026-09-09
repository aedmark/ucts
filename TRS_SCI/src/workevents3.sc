/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 18-25: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS3_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (WorkEvent18)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with the same screenshot,\ncircled in red, underlined in red,\nand highlighted in red."
	)
	= choice PrintChoices(
		"Someone replies to your third follow-up with 'per my last email' and a screenshot of an answer that was not, in fact, an answer."
		"The 'Per My Last Email'"
		290
		glitchText
		"Reply quoting the exact unanswered\nquestion, again." 0
		"Apologize for 'missing' the answer\nthat wasn't there." 1
		"Close the thread and decide to just\nfigure it out yourself." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have made your point. Several points, actually. All very helpful.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -15 10 TAG_FIGHT)
			Print("You made the gap impossible to miss a second time. They answer the question.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You took the blame for someone else's incompetence.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You absorbed the extra work rather than the friction. Nobody learns anything.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent19)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Bring a tiny gift as tribute, like\nvisiting a shrine."
	)
	= choice PrintChoices(
		"Your manager says 'my door is always open' during a meeting. Their door has, notably, never once been open."
		"The Open Door Policy"
		290
		glitchText
		"File it away as one more thing you\nwon't actually bring up." 0
		"Nod like the sentence was true and\nuseful." 1
		"Test it. Walk over and knock." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The door, astonishingly, opens. You are unprepared for this outcome.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You added a line to a list nobody's reading but you.")
		)
		(case 1
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You agreed with a door that stays shut.")
		)
		(case 2
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You checked the claim against the evidence. You get lunch together and have a good time.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent20)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Comment using only a single, deeply\nambiguous emoji."
	)
	= choice PrintChoices(
		"A peer from two roles ago just posted 'excited to announce' a title you quietly wanted for yourself."
		"The LinkedIn Congrats"
		290
		glitchText
		"Like the post and write a warm,\nspecific comment." 0
		"Close the app. Reopen it four\nminutes later." 1
		"Feel the envy, don't perform past\nit, close the app anyway." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Seventeen people have now liked your emoji. Nobody knows what it means. Least of all you.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You performed happiness at a volume you didn't feel.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You kept checking a wound to see if it still hurt.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let a small ugly feeling exist without narrating it to anyone or destroying you.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent21)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Start a spontaneous printer-line\ntrivia night to pass the time."
	)
	= choice PrintChoices(
		"You're fourth in line at the printer, running late, and the person ahead of you is scrolling their phone between pages."
		"The Printer Line"
		290
		glitchText
		"Stand there, saying nothing, doing\nthe math on how late you'll be." 0
		"Ask, politely but directly, if you\ncan jump in for one page." 1
		"Wait it out and tell yourself it's\nfine, it's fine, it's fine." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody knew this much about the printer. Nobody wanted to.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You calculated the cost instead of asking for the copy.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You asked for the small thing you actually needed. You got it without any dirty looks.")
		)
		(case 2
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You narrated a calm you weren't actually experiencing, accomplishing nothing.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent22)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Take a bow for the growing audience\nbehind you."
	)
	= choice PrintChoices(
		"Your badge doesn't scan. Three times in a row. There's a line building behind you now."
		"The Badge Scan Fail"
		290
		glitchText
		"Apologize to everyone behind you\nindividually." 0
		"Freeze up completely, badge in hand,\nbrain empty except for the intense\npanic that you've been fired." 1
		"Step aside, let people pass, try\nagain without an audience." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Someone starts filming. This will outlive your employment here.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You issued apologies for a malfunctioning badge reader.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("The door and your nervous system both stopped responding.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You removed the audience instead of performing through it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent23)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply fully in character as the\nother person."
	)
	= choice PrintChoices(
		"You get an email addressed to someone else's name, clearly copy-pasted from a different, more glowing thread."
		"The Wrong Name in the Email"
		290
		glitchText
		"Assume you're actually being\ncompared unfavorably to that person." 0
		"Reply, lightly, pointing out the\nname mismatch." 1
		"Ignore it and answer as if it were\naddressed to you correctly." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are now, professionally speaking, someone else. It's going well.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You built a rivalry with someone who doesn't know you exist.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You named the small error instead of absorbing a large story about it.")
		)
		(case 2
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You let the mistake pass to keep things smooth.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent24)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply instantly with 'Your wish is\nmy command, Master.'"
	)
	= choice PrintChoices(
		"A message lands in your inbox at 5:58 PM, two minutes before you were going to log off. It starts with 'quick question.'"
		"The End-of-Day Ping"
		290
		glitchText
		"Stay online another forty-five\nminutes to answer it fully." 0
		"Reply first thing tomorrow, on\npurpose." 1
		"Stare at the message, unable to\ndecide, until it's 7 PM anyway." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("This was not the reassurance they were hoping for. You never hear from them again.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 10 -15 TAG_FAWN)
			Print("You extended the day to protect someone else's evening. How noble.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let 5:58 PM mean what it says.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("Indecision cost you the boundary you meant to keep.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (WorkEvent25)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Reply with an equally cryptic single\nword of your own."
	)
	= choice PrintChoices(
		"You send a detailed update. The reply is a single word: 'Noted.'"
		"The Manager's One-Word Reply"
		290
		glitchText
		"Reread the word eleven times,\nhunting for a tone that isn't there." 0
		"Send a follow-up asking if\neverything's okay." 1
		"Take the word at face value and move\non with your day." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("A one-word war has begun. Nobody will walk away alive.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You built an entire mood off four letters. Now your tummy hurts.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You went looking for reassurance and found a boundary.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Sometimes 'noted' just means noted.")
		)
		)
	)
)
/******************************************************************************/
