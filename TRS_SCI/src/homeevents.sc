/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 homeevents.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js.
 Dispatcher only: DoHomeEvent(index) is the public entry point other
 scripts call; the HOME-zone events' implementations live in
 homeevents1.sc..homeevents8.sc.
 Loads only the chunk script the requested index lives in, and disposes it
 again right after the call -- see the comment on genDispatcher() in
 tools/lib/zone-events.js for why that's load-bearing, not just tidiness.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script HOMEEVENTS_SCRIPT)
/******************************************************************************/
(use "homeevents1")
(use "homeevents2")
(use "homeevents3")
(use "homeevents4")
(use "homeevents5")
(use "homeevents6")
(use "homeevents7")
(use "homeevents8")
/******************************************************************************/
(procedure public (DoHomeEvent index)
	(if((>= index 0) and (<= index 3))
		Load(rsSCRIPT HOMEEVENTS1_SCRIPT)
		(switch(index)
			(case 0 HomeEvent0())
			(case 1 HomeEvent1())
			(case 2 HomeEvent2())
			(case 3 HomeEvent3())
		)
		DisposeScript(HOMEEVENTS1_SCRIPT)
		return
	)
	(if((>= index 4) and (<= index 7))
		Load(rsSCRIPT HOMEEVENTS2_SCRIPT)
		(switch(index)
			(case 4 HomeEvent4())
			(case 5 HomeEvent5())
			(case 6 HomeEvent6())
			(case 7 HomeEvent7())
		)
		DisposeScript(HOMEEVENTS2_SCRIPT)
		return
	)
	(if((>= index 8) and (<= index 11))
		Load(rsSCRIPT HOMEEVENTS3_SCRIPT)
		(switch(index)
			(case 8 HomeEvent8())
			(case 9 HomeEvent9())
			(case 10 HomeEvent10())
			(case 11 HomeEvent11())
		)
		DisposeScript(HOMEEVENTS3_SCRIPT)
		return
	)
	(if((>= index 12) and (<= index 15))
		Load(rsSCRIPT HOMEEVENTS4_SCRIPT)
		(switch(index)
			(case 12 HomeEvent12())
			(case 13 HomeEvent13())
			(case 14 HomeEvent14())
			(case 15 HomeEvent15())
		)
		DisposeScript(HOMEEVENTS4_SCRIPT)
		return
	)
	(if((>= index 16) and (<= index 19))
		Load(rsSCRIPT HOMEEVENTS5_SCRIPT)
		(switch(index)
			(case 16 HomeEvent16())
			(case 17 HomeEvent17())
			(case 18 HomeEvent18())
			(case 19 HomeEvent19())
		)
		DisposeScript(HOMEEVENTS5_SCRIPT)
		return
	)
	(if((>= index 20) and (<= index 23))
		Load(rsSCRIPT HOMEEVENTS6_SCRIPT)
		(switch(index)
			(case 20 HomeEvent20())
			(case 21 HomeEvent21())
			(case 22 HomeEvent22())
			(case 23 HomeEvent23())
		)
		DisposeScript(HOMEEVENTS6_SCRIPT)
		return
	)
	(if((>= index 24) and (<= index 27))
		Load(rsSCRIPT HOMEEVENTS7_SCRIPT)
		(switch(index)
			(case 24 HomeEvent24())
			(case 25 HomeEvent25())
			(case 26 HomeEvent26())
			(case 27 HomeEvent27())
		)
		DisposeScript(HOMEEVENTS7_SCRIPT)
		return
	)
	(if((>= index 28) and (<= index 31))
		Load(rsSCRIPT HOMEEVENTS8_SCRIPT)
		(switch(index)
			(case 28 HomeEvent28())
			(case 29 HomeEvent29())
			(case 30 HomeEvent30())
			(case 31 HomeEvent31())
		)
		DisposeScript(HOMEEVENTS8_SCRIPT)
		return
	)
)
/******************************************************************************/
