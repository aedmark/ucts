/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 8-11: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS3_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(procedure public (PublicEvent8)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Strike up a conversation with your number ticket like it's a person."
	)
	= choice PrintChoices(
		"You've been standing in the same six feet of floor for forty minutes. The number on your ticket has not moved. Neither has anyone's mood."
		"The DMV Line"
		290
		glitchText
		"Apologize to the person behind you for existing in their line of sight." 0
		"Loudly ask if anyone else thinks this is insane." 1
		"Accept the wait for what it is and let your mind go somewhere else." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("B-247 did not respond. You feel closer to it anyway.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You said sorry for taking up floor space that was yours to take up.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("Several strangers agreed with you. Nobody moved any faster because of it.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave the wait exactly the amount of your life it was going to take anyway.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent9)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Tell the bailiff you've actually always wanted to see how this works."
	)
	= choice PrintChoices(
		"You're in a room full of strangers being told that your normal lives are on hold for an unknown number of days."
		"The Jury Duty Summons"
		290
		glitchText
		"Raise your hand immediately to volunteer for anything that gets you excused." 0
		"Sit very still and hope your name simply never gets called." 1
		"Answer the questions honestly and let the process do what it does." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("This is, apparently, an unusual thing to say out loud. You are now Juror Number One.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You ran from an obligation using the exact process built to enforce it.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You made yourself as unnoticeable as a person in a numbered chair can be.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You told the truth to a room of strangers deciding your next two weeks. It felt strange, but fine.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent10)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask the driver a series of increasingly personal questions right back."
	)
	= choice PrintChoices(
		"The driver wants to talk. You have eighteen minutes left and a headache forming right behind your left eye."
		"The Rideshare Small Talk"
		290
		glitchText
		"Answer every question with enthusiasm you do not currently possess." 0
		"Put in headphones without actually playing anything." 1
		"Say, kindly, that you're wiped and would rather ride quiet." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You now know more about their custody arrangement than the last three passengers combined.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed 'Friendly Passenger' for eighteen minutes straight through a headache.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 3 TAG_FLIGHT)
			Print("You faked a soundtrack to buy yourself a real silence.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You asked for what you needed from a stranger you'll know for eighteen more minutes.")
		)
		)
	)
)
/******************************************************************************/
(procedure public (PublicEvent11)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask to see the clipboard and start signing up other pedestrians yourself."
	)
	= choice PrintChoices(
		"They've made eye contact from twenty feet away and are already walking toward you with a clipboard and a warm, practiced smile."
		"The Street Canvasser With A Clipboard"
		290
		glitchText
		"Sign up for something you don't care about just to end the conversation faster." 0
		"Pretend to be mid-phone-call and speed-walk past." 1
		"Make eye contact, say 'not today, thanks,' and keep walking." 2
	)
	(if(== choice GLITCH_CHOICE)
		ApplyGlitch("You have somehow become their best volunteer of the day. This was not the plan.")
	)(else
		(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You bought your own escape with a monthly donation you will forget about until it's not forgettable.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You held a phone to your ear and spoke to no one to avoid speaking to someone.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You said no in four words and nothing bad happened.")
		)
		)
	)
)
/******************************************************************************/
