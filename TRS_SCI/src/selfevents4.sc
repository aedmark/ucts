/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 13-16: each is a standalone
 SelfEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS4_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (SelfEvent13)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Write your own unhinged, deeply specific counter-list right now."
	)
	= choice PrintChoices(
		"You're scrolling through someone else's milestones; house, promotion, wedding, etc.  You keep checking your own life against it."
		"The List of Things You're Supposed To Want"
		290
		glitchText
		"Keep scrolling, keep comparing, feel worse with each one." 0
		"Convince yourself you want all of it too, just to feel aligned." 1
		"Close the app. Ask yourself, honestly, what you actually want." 2
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
		= glitchText "Give the day an oddly specific, ceremonial little ritual."
	)
	= choice PrintChoices(
		"You've been off all day and couldn't say why. Then you check the date."
		"The Anniversary Your Body Remembers Before You Do"
		290
		glitchText
		"Push through the day as if you hadn't noticed anything at all." 0
		"Apologize to everyone around you for being 'off' today." 1
		"Let today be a harder day. You don't owe anyone your usual output." 2
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
		= glitchText "Write them a postcard. Mail it to yourself, unironically."
	)
	= choice PrintChoices(
		"You try to picture yourself a decade back and feel a strange mix of tenderness and secondhand embarrassment."
		"The You From Ten Years Ago"
		290
		glitchText
		"Cringe hard and mentally list everything that version got wrong." 0
		"Change the subject in your own head before it goes anywhere real." 1
		"Send a little compassion backward. They didn't know what you know now." 2
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
		= glitchText "Draft their apology yourself, in full, and read it aloud dramatically."
	)
	= choice PrintChoices(
		"You've rehearsed the conversation where they finally say it. It hasn't happened. It might not."
		"The Apology You Never Got"
		290
		glitchText
		"Keep rehearsing the conversation, on a loop, indefinitely." 0
		"Draft the message you'd send them. Don't send it. Yet." 1
		"Consider that closure might have to come from you instead." 2
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
