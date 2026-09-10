/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 the original js/events/public.js. Re-run that script after
 editing the source event data.

 PUBLIC-zone events 8-15: each is a standalone
 PublicEvent<N> procedure showing the event's PrintChoices dialog, then
 applying the chosen response's stat effects and printing its log line.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS2_SCRIPT)
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
		= glitchText "Strike up a conversation with your\nnumber ticket like it's a person."
	)
	= choice PrintChoices(
		"You've been standing in the same six feet of floor for forty minutes. The number on your ticket has not moved. Neither has anyone's mood."
		"The DMV Line"
		290
		glitchText
		"Apologize to the person behind you\nfor existing in their line of sight." 0
		"Loudly ask if anyone else thinks\nthis is insane." 1
		"Accept the wait for what it is and\nlet your mind go somewhere else." 2
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
		= glitchText "Tell the bailiff you've actually\nalways wanted to see how this works."
	)
	= choice PrintChoices(
		"You're in a room full of strangers being told that your normal lives are on hold for an unknown number of days."
		"The Jury Duty Summons"
		290
		glitchText
		"Raise your hand immediately to\nvolunteer for anything that gets you\nexcused." 0
		"Sit very still and hope your name\nsimply never gets called." 1
		"Answer the questions honestly and\nlet the process do what it does." 2
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
		= glitchText "Ask the driver a series of\nincreasingly personal questions\nright back."
	)
	= choice PrintChoices(
		"The driver wants to talk. You have eighteen minutes left and a headache forming right behind your left eye."
		"The Rideshare Small Talk"
		290
		glitchText
		"Answer every question with\nenthusiasm you do not currently\npossess." 0
		"Put in headphones without actually\nplaying anything." 1
		"Say, kindly, that you're wiped and\nwould rather ride quiet." 2
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
		= glitchText "Ask to see the clipboard and start\nsigning up other pedestrians\nyourself."
	)
	= choice PrintChoices(
		"They've made eye contact from twenty feet away and are already walking toward you with a clipboard and a warm, practiced smile."
		"The Street Canvasser With A Clipboard"
		290
		glitchText
		"Sign up for something you don't care\nabout just to end the conversation\nfaster." 0
		"Pretend to be mid-phone-call and\nspeed-walk past." 1
		"Make eye contact, say 'not today,\nthanks,' and keep walking." 2
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
(procedure public (PublicEvent12)
	(var choice, glitchText)
	= glitchText NULL
	(if(< Random(0 99) GLITCH_CHANCE_PCT)
		= glitchText "Ask the screen, out loud, if it has\nany tips for you."
	)
	= choice PrintChoices(
		"You're paying with a card. The screen turns around. It's asking for a tip, and it's asking in front of the person you'd be tipping."
		"The Tip Jar"
		290
		glitchText
		"Tip more than you can afford so they\ndon't think badly of you." 0
		"Hit 'no tip' fast and avoid eye\ncontact for the rest of the\ntransaction." 1
		"Tip what you can actually afford and\nlet that be enough." 2
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
		= glitchText "Offer to hold the baby. You have\nnever held a baby."
	)
	= choice PrintChoices(
		"Row fourteen has been going for twenty minutes. You can feel the whole cabin's patience thinning at the same rate as yours."
		"The Crying Baby On The Plane"
		290
		glitchText
		"Shoot the parents a series of\nsympathetic smiles you don't\nactually feel." 0
		"Sigh loudly enough that row fourteen\nis guaranteed to hear it." 1
		"Put in earplugs and let it be\nsomeone else's hard day, not a\nreferendum on yours." 2
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
		= glitchText "Applaud, as if it were an\nintentional stunt."
	)
	= choice PrintChoices(
		"They went down hard on the sidewalk two steps ahead of you, and now there's a very short window to decide what kind of stranger you are."
		"The Person Who Fell In Front Of You"
		290
		glitchText
		"Rush over and apologize on their\nbehalf before they've even said\nanything." 0
		"Freeze for a second too long, unsure\nif helping is your job here." 1
		"Ask if they're okay and help them up\nwithout making it a scene." 2
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
		= glitchText "Ask them to just tell you the whole\nstory anyway. You're invested now."
	)
	= choice PrintChoices(
		"A stranger is on the other end of the line, mid-sentence, about something urgent that has nothing to do with you."
		"The Wrong Number You Answered"
		290
		glitchText
		"Stay on the line and try to help\nthem anyway, even though you have no\nidea who they meant to call." 0
		"Hang up immediately without saying a\nword." 1
		"Politely tell them they've got the\nwrong number and wish them luck." 2
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
