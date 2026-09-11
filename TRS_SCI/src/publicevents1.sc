/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 0-3: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (PublicEvent0)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask the cashier how THEIR day is going, in aggressive detail."
	)
	= choice PrintChoices(
		"You're barely holding it together behind a very calm face. The cashier asks how your day is going. "
		"The Checkout Line"
		290
		glitchText
		"Say 'great, thanks!' with way more enthusiasm than you actually have." 0
		"Give a flat, one-word answer and stare at the card reader." 1
		"Be honest and let it be a real, if brief, answer." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("They were not prepared for this. Neither were you.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed 'great' for someone who will forget this conversation in four seconds.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You disappeared into the transaction and let the small talk die there.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You told a stranger a true thing instead of a convenient one. They gave you a sticker. ")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent1)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask to speak to the hold music's manager."
	)
	= choice PrintChoices(
		"Forty minutes on hold, then a real person finally picks up, and you have to sound pleasant immediately."
		"The Customer Service Call"
		290
		glitchText
		"Apologize for 'bothering them' before you've even explained the problem." 0
		"Let all forty minutes of frustration out on the person who just picked up." 1
		"Take a breath, state the problem plainly, and stay civil." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("There is no such position. You asked anyway. You were put on hold again.\n\n\n Close enough.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You apologized for needing help before anyone accused you of it.")
		)
		(case 1
			ApplyChoiceEffects(-12 -15 0 TAG_FIGHT)
			Print("Correct target: the hold music. Actual target: an underpaid stranger.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You separated the wait from the person now trying to fix it.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent2)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask them to write the compliment down so you can frame it."
	)
	= choice PrintChoices(
		"Someone in line says, out of nowhere, that they like your energy today. You will never see them again."
		"The Compliment From A Stranger You'll Never See Again"
		290
		glitchText
		"Say a fast 'thanks' and physically speed up to end the interaction." 0
		"Freeze up and say nothing until they look away, confused." 1
		"Say thank you and let it land." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You are now holding a receipt that says 'good energy, 2:47 PM.' You will keep this forever.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You outran a kind word like it was a threat.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("A nice moment got the silent treatment. It wasn't personal. Probably.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("A stranger's small kindness got all the way in, for once.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent3)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Loudly announce to the whole bus that you're not crying it's just allergies."
	)
	= choice PrintChoices(
		"It hits you three stops from home and there is nowhere to put it. Everyone very pointedly looks at their phones."
		"The Public Cry On The Bus"
		290
		glitchText
		"Smile at the person next to you like everything's fine, tears and all." 0
		"Stare straight ahead and let it happen without acknowledging it at all." 1
		"Let it happen, wipe your face when it passes, and don't apologize for it." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("Nobody asked. Several people now have to carry a bit of something from you they didn't want.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You performed 'fine' with visible evidence directly contradicting it.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("You rode it out like weather. Nobody said anything. That's the deal on public transit.")
		)
		(case 2
			ApplyChoiceEffects(-10 3 10 TAG_SECURE)
			Print("You cried on a bus and didn't make it mean anything about you as a person.")
		)
		)
	)
)
/******************************************************************************/
