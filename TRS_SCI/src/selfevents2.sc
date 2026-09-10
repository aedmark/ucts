/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 9-16: each is a standalone
 SelfEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS2_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SelfEvent9)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Lean all the way in and do a full,\ncommitted impression of them."
	)
	= choice PrintChoices(
		"You caught yourself doing the exact thing, the exact way, that used to make you flinch when they did it."
		"The Habit You Picked Up From A Parent"
		290
		glitchText
		"Get angry at yourself for the\nresemblance." 0
		"Pretend you didn't notice and keep\ndoing it anyway." 1
		"Notice it. Name it. Try, gently, to\ndo the next one differently." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's uncannily accurate. Everyone is deeply unsettled.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You punished the habit instead of just noticing it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let the pattern run unexamined, again.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught a pattern mid-motion, which is most of the work. Keep going.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent10)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Talk back to it out loud, in public,\nwith real conviction."
	)
	= choice PrintChoices(
		"The criticism arrives in a tone you recognize. It's not how you'd talk to anyone else. It's exactly how someone once talked to you."
		"The Voice In Your Head That Isn't Yours"
		290
		glitchText
		"Agree with it. It's probably right,\nlike it always was." 0
		"Argue back at it, harshly, in your\nown head." 1
		"Notice it's not your voice. You\ndon't have to use it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("A stranger gives you a wide berth. The voice, notably, has no comeback.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -15 TAG_FAWN)
			Print("You gave an old voice the final word again.")
		)
		(case 1
			ApplyChoiceEffects(-5 0 -5 TAG_FIGHT)
			Print("You fought a voice with the same volume it uses.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You separated the message from the messenger you inherited it from.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent11)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Blame it, loudly and specifically,\non Mercury being in retrograde."
	)
	= choice PrintChoices(
		"Your shoulders have been up by your ears for an hour and you genuinely cannot remember when that started."
		"The Ache With No Origin"
		290
		glitchText
		"Ignore it. It'll probably go away on\nits own." 0
		"Push through it, it's fine,\neveryone's tired, this is normal." 1
		"Stop. Relax. Roll your shoulders.\nUnclench your jaw. Take one real\nbreath." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("This explains nothing. It helps somehow, anyway.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You outsourced the problem to future-you. Again. (Jerk).")
		)
		(case 1
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You neglected a body that was actively asking for something.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You gave your body the attention it was asking for. It thanks you by flooding your brain with dopamine.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Lean all the way into it."
	)
	= choice PrintChoices(
		"You hear the sentence leave your mouth and recognize it a half-second too late. It's not yours. It's theirs."
		"The Thing You Said You'd Never Become"
		290
		glitchText
		"Spiral into a full self-indictment\nfor the next hour." 0
		"Laugh it off in the moment and never\nthink about it again... You swear!" 1
		"Notice it. Decide, calmly, that\nnoticing is the first step, not a\nfailure." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have never committed to a bit this hard. Everyone is a little worried about you.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -20 TAG_FIGHT)
			Print("You sentenced yourself over one inherited sentence.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You buried it under a laugh instead of a look.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught the echo without deciding it was proof of anything permanent.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent13)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Write your own unhinged, deeply\nspecific counter-list right now."
	)
	= choice PrintChoices(
		"You're scrolling through someone else's milestones; house, promotion, wedding, etc.  You keep checking your own life against it."
		"The List of Things You're Supposed To Want"
		290
		glitchText
		"Keep scrolling, keep comparing, feel\nworse with each one." 0
		"Convince yourself you want all of it\ntoo, just to feel aligned." 1
		"Close the app. Ask yourself,\nhonestly, what you actually want." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Item four is just 'made a really good sandwich.' You stand by it.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You audited your life against an idealized list you didn't write. Of course you fell short.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -15 TAG_FAWN)
			Print("You borrowed someone else's wants because yours felt too quiet to trust.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You checked the list against your own name instead of theirs.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent14)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Give the day an oddly specific,\nceremonial little ritual."
	)
	= choice PrintChoices(
		"You've been off all day and couldn't say why. Then you check the date."
		"The Anniversary Your Body Remembers Before You Do"
		290
		glitchText
		"Push through the day as if you\nhadn't noticed anything at all." 0
		"Apologize to everyone around you for\nbeing 'off' today." 1
		"Let today be a harder day. You don't\nowe anyone your usual output." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have invented a holiday nobody else knows about. It helps.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("Your body kept the appointment even after your mind tried to skip it.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You apologized for grief that arrived on schedule, uninvited.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You gave a hard day permission to be hard.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent15)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Write them a postcard. Mail it to\nyourself, unironically."
	)
	= choice PrintChoices(
		"You try to picture yourself a decade back and feel a strange mix of tenderness and secondhand embarrassment."
		"The You From Ten Years Ago"
		290
		glitchText
		"Cringe hard and mentally list\neverything that version got wrong." 0
		"Change the subject in your own head\nbefore it goes anywhere real." 1
		"Send a little compassion backward.\nThey didn't know what you know now." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It arrives in three days. Past-you would be delighted it worked.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You put a decade-old version of yourself on trial.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You closed the door on a version of you who was just trying, too.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You forgave a person for not having information they didn't learn yet.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent16)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Draft their apology yourself, in\nfull, and read it aloud\ndramatically."
	)
	= choice PrintChoices(
		"You've rehearsed the conversation where they finally say it. It hasn't happened. It might not."
		"The Apology You Never Got"
		290
		glitchText
		"Keep rehearsing the conversation, on\na loop, indefinitely." 0
		"Draft the message you'd send them.\nDon't send it. Yet." 1
		"Consider that closure might have to\ncome from you instead." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It's a great apology. Extremely well-written and deeply, deeply fake.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You kept a courtroom open for a trial nobody else is attending.")
		)
		(case 1
			ApplyChoiceEffects(-5 0 5 TAG_FIGHT)
			Print("You gave the anger somewhere to go besides in circles.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You stopped waiting for a door someone else may never open.")
		)
		)
	)
)
/******************************************************************************/
