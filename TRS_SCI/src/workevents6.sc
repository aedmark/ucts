/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents6.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 22-25: each is a standalone
 WorkEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS6_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (WorkEvent22)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Take a bow for the growing audience behind you."
	)
	= choice PrintChoices(
		"Your badge doesn't scan. Three times in a row. There's a line building behind you now."
		"The Badge Scan Fail"
		290
		glitchText
		"Apologize to everyone behind you individually." 0
		"Freeze up completely, badge in hand, brain empty except for the intense panic that you've been fired." 1
		"Step aside, let people pass, try again without an audience." 2
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
		= glitchText "Reply fully in character as the other person."
	)
	= choice PrintChoices(
		"You get an email addressed to someone else's name, clearly copy-pasted from a different, more glowing thread."
		"The Wrong Name in the Email"
		290
		glitchText
		"Assume you're actually being compared unfavorably to that person." 0
		"Reply, lightly, pointing out the name mismatch." 1
		"Ignore it and answer as if it were addressed to you correctly." 2
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
		= glitchText "Reply instantly with 'Your wish is my command, Master.'"
	)
	= choice PrintChoices(
		"A message lands in your inbox at 5:58 PM, two minutes before you were going to log off. It starts with 'quick question.'"
		"The End-of-Day Ping"
		290
		glitchText
		"Stay online another forty-five minutes to answer it fully." 0
		"Reply first thing tomorrow, on purpose." 1
		"Stare at the message, unable to decide, until it's 7 PM anyway." 2
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
		= glitchText "Reply with an equally cryptic single word of your own."
	)
	= choice PrintChoices(
		"You send a detailed update. The reply is a single word: 'Noted.'"
		"The Manager's One-Word Reply"
		290
		glitchText
		"Reread the word eleven times, hunting for a tone that isn't there." 0
		"Send a follow-up asking if everything's okay." 1
		"Take the word at face value and move on with your day." 2
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
