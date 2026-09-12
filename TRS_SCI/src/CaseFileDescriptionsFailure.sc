/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsFailure.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Failure ending descriptions for the Case Files viewer's "View" detail
 screen, flat indices 72-101 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_FAILURE_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionFailure index)
	(switch(index)
		(case 72 return("Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet."))
		(case 73 return("Repression maxed out the scale. Something that was supposed to stay buried came up all at once, in the worst possible meeting."))
		(case 74 return("Repression blown. You said the quiet part out loud. To the wrong person."))
		(case 75 return("You held it in until you couldn't. Now everyone knows exactly how you feel, whether they asked or not."))
		(case 76 return("Repression hit 100%. You cried in the car for forty minutes before you could turn the key."))
		(case 77 return("You reached capacity. The thing that finally broke you was, embarrassingly, very minor."))
		(case 78 return("It wasn't the big thing. It was the small thing on top of the big thing. Repression is at zero now, mostly because you have none left to hold."))
		(case 79 return("Repression hit 100% in front of everybody. There is no version of Monday that fixes this."))
		(case 80 return("You were just fine! You've been fine this whole time! You planned on being fine all day... \n\n\n You are not fine."))
		(case 81 return("Repression maxed out and took the rest of your composure down with it."))
		(case 82 return("Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but free."))
		(case 83 return("Social Mask hit 0%. You said what you actually thought, out loud, in a group chat with your entire extended family."))
		(case 84 return("Mask hit zero. Turns out the truth doesn't need permission to leave your mouth."))
		(case 85 return("You ran out of mask exactly when someone asked how you were and they got the real answer."))
		(case 86 return("Social Mask bottomed out. Everyone can now see exactly what you've been holding back, and they have thoughts."))
		(case 87 return("Mask hit 0%. You told your boss what you actually think of the quarterly review. It felt incredible for six seconds."))
		(case 88 return("You stopped performing how 'fine' you are, and the room noticed immediately."))
		(case 89 return("Mask hit 0% at the worst possible time in the company meeting. At least it's memorable. You try to tell yourself they were laughing *with* you."))
		(case 90 return("You said the true thing instead of the nice thing. The silence afterward was very loud (and awkward)."))
		(case 91 return("Mask dropped to zero. You are now saying things out loud that used to just be inside thoughts."))
		(case 92 return("Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory.\n\n You feel nothing."))
		(case 93 return("Inner Child hit 0%. You drove home and don't remember any of it."))
		(case 94 return("Inner Child bottomed out. You're going through the motions, and the motions are all that's left."))
		(case 95 return("Inner Child hit zero. Someone asked how you were and you answered before you'd actually heard the question."))
		(case 96 return("Inner Child hit 0%. Nothing feels good. Nothing feels bad.\n\nNothing feels at all."))
		(case 97 return("Inner Child bottomed out. You are functioning perfectly and feeling absolutely no joy from it."))
		(case 98 return("Inner Child hit zero. There's a version of you still doing the tasks. You're not entirely sure where the rest of you went."))
		(case 99 return("Inner Child hit 0%. You're present in the room in the way furniture is also present."))
		(case 100 return("Inner Child bottomed out somewhere between one task and the next. You didn't notice it happen. That's the part that should worry you."))
		(case 101 return("Inner Child hit zero. You are technically fine. Technically is doing a lot of work in that sentence."))
	)
	return("")
)
/******************************************************************************/
