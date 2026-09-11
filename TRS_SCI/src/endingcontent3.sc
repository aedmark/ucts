/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingcontent3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Failure ending pools (3 pools x 10 variants = 30), matching CONTENT_FAILURE_ENDINGS in js/content-endings.js. Flat Case Files indices 72-101 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGCONTENT3_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintFailureEnding0)
	(var variantIndex)
	= variantIndex Random(0 9)
	(switch(variantIndex)
		(case 0
			Print("Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet." #title "Panic Attack")
			(if(MarkCaseFile(72))
				Print("Case Files: Panic Attack, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Repression maxed out the scale. Something that was supposed to stay buried came up all at once, in the worst possible meeting." #title "Tectonic Reset")
			(if(MarkCaseFile(73))
				Print("Case Files: Tectonic Reset, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Repression blown. You said the quiet part out loud. To the wrong person." #title "The Blowup")
			(if(MarkCaseFile(74))
				Print("Case Files: The Blowup, filed." #title "New Case File")
			)
		)
		(case 3
			Print("You held it in until you couldn't. Now everyone knows exactly how you feel, whether they asked or not." #title "System Overpressure")
			(if(MarkCaseFile(75))
				Print("Case Files: System Overpressure, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Repression hit 100%. You cried in the car for forty minutes before you could turn the key." #title "Full Meltdown")
			(if(MarkCaseFile(76))
				Print("Case Files: Full Meltdown, filed." #title "New Case File")
			)
		)
		(case 5
			Print("You reached capacity. The thing that finally broke you was, embarrassingly, very minor." #title "The Snap")
			(if(MarkCaseFile(77))
				Print("Case Files: The Snap, filed." #title "New Case File")
			)
		)
		(case 6
			Print("It wasn't the big thing. It was the small thing on top of the big thing. Repression is at zero now, mostly because you have none left to hold." #title "Delayed Detonation")
			(if(MarkCaseFile(78))
				Print("Case Files: Delayed Detonation, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Repression hit 100% in front of everybody. There is no version of Monday that fixes this." #title "Public Unraveling")
			(if(MarkCaseFile(79))
				Print("Case Files: Public Unraveling, filed." #title "New Case File")
			)
		)
		(case 8
			Print("You were just fine! You've been fine this whole time! You planned on being fine all day... \n\n\n You are not fine." #title "The Overflow")
			(if(MarkCaseFile(80))
				Print("Case Files: The Overflow, filed." #title "New Case File")
			)
		)
		(case 9
			Print("Repression maxed out and took the rest of your composure down with it." #title "Structural Failure")
			(if(MarkCaseFile(81))
				Print("Case Files: Structural Failure, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
(procedure public (PrintFailureEnding1)
	(var variantIndex)
	= variantIndex Random(0 9)
	(switch(variantIndex)
		(case 0
			Print("Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but free." #title "Social Exile")
			(if(MarkCaseFile(82))
				Print("Case Files: Social Exile, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Social Mask hit 0%. You said what you actually thought, out loud, in a group chat with your entire extended family." #title "The Unmasking")
			(if(MarkCaseFile(83))
				Print("Case Files: The Unmasking, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Mask hit zero. Turns out the truth doesn't need permission to leave your mouth." #title "Honesty, Uninvited")
			(if(MarkCaseFile(84))
				Print("Case Files: Honesty, Uninvited, filed." #title "New Case File")
			)
		)
		(case 3
			Print("You ran out of mask exactly when someone asked how you were and they got the real answer." #title "No Filter Left")
			(if(MarkCaseFile(85))
				Print("Case Files: No Filter Left, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Social Mask bottomed out. Everyone can now see exactly what you've been holding back, and they have thoughts." #title "The Reveal")
			(if(MarkCaseFile(86))
				Print("Case Files: The Reveal, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Mask hit 0%. You told your boss what you actually think of the quarterly review. It felt incredible for six seconds." #title "Radical Candor (Involuntary)")
			(if(MarkCaseFile(87))
				Print("Case Files: Radical Candor (Involuntary), filed." #title "New Case File")
			)
		)
		(case 6
			Print("You stopped performing how 'fine' you are, and the room noticed immediately." #title "Exiled")
			(if(MarkCaseFile(88))
				Print("Case Files: Exiled, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Mask hit 0% at the worst possible time in the company meeting. At least it's memorable. You try to tell yourself they were laughing *with* you." #title "The Real You, Unscheduled")
			(if(MarkCaseFile(89))
				Print("Case Files: The Real You, Unscheduled, filed." #title "New Case File")
			)
		)
		(case 8
			Print("You said the true thing instead of the nice thing. The silence afterward was very loud (and awkward)." #title "Social Combustion")
			(if(MarkCaseFile(90))
				Print("Case Files: Social Combustion, filed." #title "New Case File")
			)
		)
		(case 9
			Print("Mask dropped to zero. You are now saying things out loud that used to just be inside thoughts." #title "Unfiltered")
			(if(MarkCaseFile(91))
				Print("Case Files: Unfiltered, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
(procedure public (PrintFailureEnding2)
	(var variantIndex)
	= variantIndex Random(0 9)
	(switch(variantIndex)
		(case 0
			Print("Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory.\n\n You feel nothing." #title "Total Disassociation")
			(if(MarkCaseFile(92))
				Print("Case Files: Total Disassociation, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Inner Child hit 0%. You drove home and don't remember any of it." #title "Autopilot Engaged")
			(if(MarkCaseFile(93))
				Print("Case Files: Autopilot Engaged, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Inner Child bottomed out. You're going through the motions, and the motions are all that's left." #title "The Hollow")
			(if(MarkCaseFile(94))
				Print("Case Files: The Hollow, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Inner Child hit zero. Someone asked how you were and you answered before you'd actually heard the question." #title "Nobody Home")
			(if(MarkCaseFile(95))
				Print("Case Files: Nobody Home, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Inner Child hit 0%. Nothing feels good. Nothing feels bad.\n\nNothing feels at all." #title "Flatline")
			(if(MarkCaseFile(96))
				Print("Case Files: Flatline, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Inner Child bottomed out. You are functioning perfectly and feeling absolutely no joy from it." #title "Muscle Memory Only")
			(if(MarkCaseFile(97))
				Print("Case Files: Muscle Memory Only, filed." #title "New Case File")
			)
		)
		(case 6
			Print("Inner Child hit zero. There's a version of you still doing the tasks. You're not entirely sure where the rest of you went." #title "The Static")
			(if(MarkCaseFile(98))
				Print("Case Files: The Static, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Inner Child hit 0%. You're present in the room in the way furniture is also present." #title "Checked Out")
			(if(MarkCaseFile(99))
				Print("Case Files: Checked Out, filed." #title "New Case File")
			)
		)
		(case 8
			Print("Inner Child bottomed out somewhere between one task and the next. You didn't notice it happen. That's the part that should worry you." #title "The Long Blink")
			(if(MarkCaseFile(100))
				Print("Case Files: The Long Blink, filed." #title "New Case File")
			)
		)
		(case 9
			Print("Inner Child hit zero. You are technically fine. Technically is doing a lot of work in that sentence." #title "Running on Empty")
			(if(MarkCaseFile(101))
				Print("Case Files: Running on Empty, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
