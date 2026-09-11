/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 the original js/events/self.js. Re-run that script after
 editing the source event data.

 SELF-zone events 5-8: each is a standalone
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
(procedure public (SelfEvent5)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Finish it in one unhinged, caffeinated burst, right now."
	)
	= choice PrintChoices(
		"A project, a hobby, a draft you were once genuinely excited about sits untouched in a folder you avoid opening."
		"The Unfinished Thing"
		290
		glitchText
		"Open the folder, look at it, close it again without touching anything." 0
		"Start something new and shinier instead." 1
		"Open it. Change one small thing. Close it again." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("It is done. It is also, somehow, about cats now.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You visited the grave without bringing flowers or a shovel. Ingrate.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You outran the old excitement with a fresh one.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 15 TAG_SECURE)
			Print("You proved the thing wasn't actually dead, just resting.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent6)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Say it out loud again, three more times, increasingly loudly."
	)
	= choice PrintChoices(
		"You catch yourself thinking something kind about your own work, unprompted, and immediately feel weird about it."
		"The Accidental Self-Compliment"
		290
		glitchText
		"Correct yourself internally: find the flaw, restore the natural order." 0
		"Change the subject in your own head immediately." 1
		"Let the thought stand. Don't correct it. Just let it be true for a second." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The dog looks concerned. The compliment stands.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 0 -15 TAG_FIGHT)
			Print("You disqualified the thought before it could get comfortable.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You fled a compliment like it was a fire alarm.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 20 TAG_SECURE)
			Print("You let something kind about yourself survive contact with your own scrutiny.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent7)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Play it on full volume and have a small, unscheduled concert."
	)
	= choice PrintChoices(
		"Three seconds of a song you haven't heard in years, and your chest does something you didn't authorize."
		"The Song That Still Does This To You"
		290
		glitchText
		"Skip it immediately and pretend you didn't feel that." 0
		"Get irritated that a song still has this much power over you." 1
		"Let it play. Feel whatever it wants you to feel." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The neighbors have opinions. You have zero regrets.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You outran a feeling that was already three seconds ahead of you.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -5 TAG_FIGHT)
			Print("You picked a fight with your own nervous system. It won.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You let three minutes of sound be exactly as small as it actually was.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (SelfEvent8)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Interview the photo directly, out loud, like a documentary subject."
	)
	= choice PrintChoices(
		"You're smiling in it. You don't remember if you were actually happy or just good at looking like it, even then."
		"The Childhood Photo You Can't Place A Feeling On"
		290
		glitchText
		"Stare at it longer, trying to force a memory that isn't there." 0
		"Decide it must have been a happy day. Move on quickly." 1
		"Let 'I don't know how I felt' be a complete, acceptable answer." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The photo declines to comment... Which is ironic, since they're worth a thousand words each, minimum.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You interrogated a photograph for information it doesn't have.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You assigned the photo a feeling so you wouldn't have to sit with the unknown.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 15 TAG_SECURE)
			Print("Not knowing turned out to be allowed. It always is.")
		)
		)
	)
)
/******************************************************************************/
