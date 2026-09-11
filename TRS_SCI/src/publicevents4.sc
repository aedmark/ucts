/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 12-15: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS4_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (PublicEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask the screen, out loud, if it has any tips for you."
	)
	= choice PrintChoices(
		"You're paying with a card. The screen turns around. It's asking for a tip, and it's asking in front of the person you'd be tipping."
		"The Tip Jar"
		290
		glitchText
		"Tip more than you can afford so they don't think badly of you." 0
		"Hit 'no tip' fast and avoid eye contact for the rest of the transaction." 1
		"Tip what you can actually afford and let that be enough." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The screen did not answer. The cashier, after a pause, actually did.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You paid for a stranger's good opinion of you with money you didn't have to spare.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("You made a budget decision and then flinched from it like it was a crime.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You made a number decision and didn't turn it into a referendum on your character.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent13)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Offer to hold the baby. You have never held a baby."
	)
	= choice PrintChoices(
		"Row fourteen has been going for twenty minutes. You can feel the whole cabin's patience thinning at the same rate as yours."
		"The Crying Baby On The Plane"
		290
		glitchText
		"Shoot the parents a series of sympathetic smiles you don't actually feel." 0
		"Sigh loudly enough that row fourteen is guaranteed to hear it." 1
		"Put in earplugs and let it be someone else's hard day, not a referendum on yours." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("The baby, astonishingly, stopped crying. You are now afraid to move for the rest of the flight.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You performed patience at a stranger so they wouldn't feel what the whole cabin was actually feeling.")
		)
		(case 1
			ApplyChoiceEffects(-8 -10 0 TAG_FIGHT)
			Print("You made your irritation a cabin announcement. The baby was unmoved. The parents were not.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave a stranger's rough flight the space to just be theirs.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent14)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Applaud, as if it were an intentional stunt."
	)
	= choice PrintChoices(
		"They went down hard on the sidewalk two steps ahead of you, and now there's a very short window to decide what kind of stranger you are."
		"The Person Who Fell In Front Of You"
		290
		glitchText
		"Rush over and apologize on their behalf before they've even said anything." 0
		"Freeze for a second too long, unsure if helping is your job here." 1
		"Ask if they're okay and help them up without making it a scene." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("They did not take a bow. They did, eventually, laugh.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You absorbed a stranger's embarrassment for them before they'd even located it themselves.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("By the time you moved, someone else already had. You still feel the half-second of not moving.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You did the plain, obvious thing. It was enough.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent15)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask them to just tell you the whole story anyway. You're invested now."
	)
	= choice PrintChoices(
		"A stranger is on the other end of the line, mid-sentence, about something urgent that has nothing to do with you."
		"The Wrong Number You Answered"
		290
		glitchText
		"Stay on the line and try to help them anyway, even though you have no idea who they meant to call." 0
		"Hang up immediately without saying a word." 1
		"Politely tell them they've got the wrong number and wish them luck." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now know entirely too much about someone named Gary's custody hearing.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You took on a stranger's emergency because hanging up felt ruder than staying confused.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You removed yourself from a problem that was never yours in the first place.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You closed the door gently instead of slamming it or leaving it open.")
		)
		)
	)
)
/******************************************************************************/
