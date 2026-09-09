/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 workevents.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js.
 Dispatcher only: DoWorkEvent(index) is the public entry point other scripts
 call; the 34 WorkEvent<N> implementations live in workevents1.sc..workevents4.sc.
 Loads only the chunk script the requested index lives in, and disposes it
 again right after the call -- see the comment on genDispatcher() in
 tools/gen-work-events.js for why that's load-bearing, not just tidiness.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script WORKEVENTS_SCRIPT)
/******************************************************************************/
(use "workevents1")
(use "workevents2")
(use "workevents3")
(use "workevents4")
/******************************************************************************/
(procedure public (DoWorkEvent index)
	(if((>= index 0) and (<= index 8))
		Load(rsSCRIPT WORKEVENTS1_SCRIPT)
		(switch(index)
			(case 0 WorkEvent0())
			(case 1 WorkEvent1())
			(case 2 WorkEvent2())
			(case 3 WorkEvent3())
			(case 4 WorkEvent4())
			(case 5 WorkEvent5())
			(case 6 WorkEvent6())
			(case 7 WorkEvent7())
			(case 8 WorkEvent8())
		)
		DisposeScript(WORKEVENTS1_SCRIPT)
		return
	)
	(if((>= index 9) and (<= index 17))
		Load(rsSCRIPT WORKEVENTS2_SCRIPT)
		(switch(index)
			(case 9 WorkEvent9())
			(case 10 WorkEvent10())
			(case 11 WorkEvent11())
			(case 12 WorkEvent12())
			(case 13 WorkEvent13())
			(case 14 WorkEvent14())
			(case 15 WorkEvent15())
			(case 16 WorkEvent16())
			(case 17 WorkEvent17())
		)
		DisposeScript(WORKEVENTS2_SCRIPT)
		return
	)
	(if((>= index 18) and (<= index 25))
		Load(rsSCRIPT WORKEVENTS3_SCRIPT)
		(switch(index)
			(case 18 WorkEvent18())
			(case 19 WorkEvent19())
			(case 20 WorkEvent20())
			(case 21 WorkEvent21())
			(case 22 WorkEvent22())
			(case 23 WorkEvent23())
			(case 24 WorkEvent24())
			(case 25 WorkEvent25())
		)
		DisposeScript(WORKEVENTS3_SCRIPT)
		return
	)
	(if((>= index 26) and (<= index 33))
		Load(rsSCRIPT WORKEVENTS4_SCRIPT)
		(switch(index)
			(case 26 WorkEvent26())
			(case 27 WorkEvent27())
			(case 28 WorkEvent28())
			(case 29 WorkEvent29())
			(case 30 WorkEvent30())
			(case 31 WorkEvent31())
			(case 32 WorkEvent32())
			(case 33 WorkEvent33())
		)
		DisposeScript(WORKEVENTS4_SCRIPT)
		return
	)
)
/******************************************************************************/
