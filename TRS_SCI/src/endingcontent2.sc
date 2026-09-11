/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingcontent2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pools 5-8 (4 pools x 8 variants = 32), matching CONTENT_ENDINGS in js/content-endings.js. Flat Case Files indices 40-71 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGCONTENT2_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding5)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("Nothing is fixed. Nothing is on fire. This might be what okay feels like." #title "Fragile Equilibrium")
			(if(MarkCaseFile(40))
				Print("Case Files: Fragile Equilibrium, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Not great, not terrible. You're starting to suspect that's just what most days actually are." #title "The Uneasy Middle")
			(if(MarkCaseFile(41))
				Print("Case Files: The Uneasy Middle, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Everything's balanced today. You know better than to assume that's permanent. You'll take it anyway." #title "Level, For Now")
			(if(MarkCaseFile(42))
				Print("Case Files: Level, For Now, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Nothing demanded a reaction today. That's rarer than it should be, and you noticed it." #title "A Quiet Enough Day")
			(if(MarkCaseFile(43))
				Print("Case Files: A Quiet Enough Day, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Not fixed. Not falling apart. Just steady, in a way that felt almost unfamiliar by the end of it." #title "Holding Steady")
			(if(MarkCaseFile(44))
				Print("Case Files: Holding Steady, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Not thriving, not failing. Somewhere in the wide, unglamorous middle most of life actually happens in." #title "The In-Between")
			(if(MarkCaseFile(45))
				Print("Case Files: The In-Between, filed." #title "New Case File")
			)
		)
		(case 6
			Print("Nothing today asked more of you than you had. That in itself felt like a small, quiet win." #title "Manageable")
			(if(MarkCaseFile(46))
				Print("Case Files: Manageable, filed." #title "New Case File")
			)
		)
		(case 7
			Print("The boat didn't rock much today. You're starting to remember what that's supposed to feel like." #title "Even Keel")
			(if(MarkCaseFile(47))
				Print("Case Files: Even Keel, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
(procedure public (PrintSurvivalEnding6)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("Not surviving. Not performing. Just, for once, actually okay. You can tell the difference from the inside." #title "Actually Okay")
			(if(MarkCaseFile(48))
				Print("Case Files: Actually Okay, filed." #title "New Case File")
			)
		)
		(case 1
			Print("This isn't the version of fine you perform for other people. This is the actual, unperformed version." #title "The Real Thing")
			(if(MarkCaseFile(49))
				Print("Case Files: The Real Thing, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Good, without a footnote explaining why it doesn't count. You keep waiting for the footnote. It doesn't come." #title "No Asterisk")
			(if(MarkCaseFile(50))
				Print("Case Files: No Asterisk, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Nobody's throwing you a parade for this, and it doesn't need one. You're just genuinely doing well, today." #title "Quietly Thriving")
			(if(MarkCaseFile(51))
				Print("Case Files: Quietly Thriving, filed." #title "New Case File")
			)
		)
		(case 4
			Print("You didn't have to build this feeling. It was just there today, the way it's supposed to be sometimes." #title "The Unforced Smile")
			(if(MarkCaseFile(52))
				Print("Case Files: The Unforced Smile, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Nothing underneath you feels like it's about to give way. You're still getting used to that." #title "Solid Ground")
			(if(MarkCaseFile(53))
				Print("Case Files: Solid Ground, filed." #title "New Case File")
			)
		)
		(case 6
			Print("This good day wasn't a mask. You checked. Twice. It held up both times." #title "Earned, Not Performed")
			(if(MarkCaseFile(54))
				Print("Case Files: Earned, Not Performed, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Same shape as a good day you'd fake for someone else's benefit. The difference is nobody had to be convinced, including you." #title "The Genuine Article")
			(if(MarkCaseFile(55))
				Print("Case Files: The Genuine Article, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
(procedure public (PrintSurvivalEnding7)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("You're carrying more than you'd like to admit, and carrying it fine, for now." #title "The Long Fuse")
			(if(MarkCaseFile(56))
				Print("Case Files: The Long Fuse, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Nothing broke today. A few things bent. You're still counting that as a win." #title "Holding It Together, Mostly")
			(if(MarkCaseFile(57))
				Print("Case Files: Holding It Together, Mostly, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Not thriving. Not drowning. Actively, deliberately managing, which is its own quiet kind of work." #title "Managing")
			(if(MarkCaseFile(58))
				Print("Case Files: Managing, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Something in you is pulled tight and hasn't snapped. You've gotten used to the tightness." #title "The Working Tension")
			(if(MarkCaseFile(59))
				Print("Case Files: The Working Tension, filed." #title "New Case File")
			)
		)
		(case 4
			Print("The weight's real. You're still standing under it. That's not nothing." #title "Under Pressure, Upright")
			(if(MarkCaseFile(60))
				Print("Case Files: Under Pressure, Upright, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Not boiling. Not cold. Just holding at a low, steady heat that takes real effort to maintain." #title "Simmer Setting")
			(if(MarkCaseFile(61))
				Print("Case Files: Simmer Setting, filed." #title "New Case File")
			)
		)
		(case 6
			Print("This isn't a crisis. It's just a lot, held at a pace you can actually keep up." #title "The Sustainable Strain")
			(if(MarkCaseFile(62))
				Print("Case Files: The Sustainable Strain, filed." #title "New Case File")
			)
		)
		(case 7
			Print("You're wound tighter than you'd like. You're also, somehow, still getting things done." #title "Tightly Wound, Still Functional")
			(if(MarkCaseFile(63))
				Print("Case Files: Tightly Wound, Still Functional, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
(procedure public (PrintSurvivalEnding8)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("You made it to tomorrow. Good job." #title "Functional Enough")
			(if(MarkCaseFile(64))
				Print("Case Files: Functional Enough, filed." #title "New Case File")
			)
		)
		(case 1
			Print("That's it. Some days that's the entire accomplishment." #title "You're Still Here")
			(if(MarkCaseFile(65))
				Print("Case Files: You're Still Here, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Nothing about today fits a neater category than this. You got through it. That counts." #title "Day Survived")
			(if(MarkCaseFile(66))
				Print("Case Files: Day Survived, filed." #title "New Case File")
			)
		)
		(case 3
			Print("No dramatic collapse, no dramatic triumph. Just a day, ending, with you still in it." #title "The Unremarkable Ending")
			(if(MarkCaseFile(67))
				Print("Case Files: The Unremarkable Ending, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Not the best day. Not the worst. A perfectly forgettable, perfectly fine day, and those matter too." #title "Adequate")
			(if(MarkCaseFile(68))
				Print("Case Files: Adequate, filed." #title "New Case File")
			)
		)
		(case 5
			Print("This isn't the ending with a moral. It's just the one where you made it to the last turn." #title "Good Enough, For Now")
			(if(MarkCaseFile(69))
				Print("Case Files: Good Enough, For Now, filed." #title "New Case File")
			)
		)
		(case 6
			Print("No collapse. No breakthrough. Just a day that happened, the way most of them do." #title "Nothing To Report")
			(if(MarkCaseFile(70))
				Print("Case Files: Nothing To Report, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Most days end like this: quietly, without a headline. This was one of those." #title "The Ordinary Ending")
			(if(MarkCaseFile(71))
				Print("Case Files: The Ordinary Ending, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
