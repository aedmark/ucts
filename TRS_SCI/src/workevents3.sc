/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 the original js/events/work.js. Re-run that script after
 editing the source event data.

 WORK-zone events 10-13: each is a standalone
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
