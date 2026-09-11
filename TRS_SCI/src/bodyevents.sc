/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 bodyevents.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js.
 Dispatcher only: DoBodyEvent(index) is the public entry point other
 scripts call; the BODY-zone events' implementations live in
 bodyevents1.sc..bodyevents8.sc.
 Loads only the chunk script the requested index lives in, and disposes it
 again right after the call -- see the comment on genDispatcher() in
 tools/lib/zone-events.js for why that's load-bearing, not just tidiness.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script BODYEVENTS_SCRIPT)
/******************************************************************************/
(use "bodyevents1")
(use "bodyevents2")
(use "bodyevents3")
(use "bodyevents4")
(use "bodyevents5")
(use "bodyevents6")
(use "bodyevents7")
(use "bodyevents8")
/******************************************************************************/
(procedure public (DoBodyEvent index)
	(if((>= index 0) and (<= index 3))
		Load(rsSCRIPT BODYEVENTS1_SCRIPT)
		(switch(index)
			(case 0 BodyEvent0())
			(case 1 BodyEvent1())
			(case 2 BodyEvent2())
			(case 3 BodyEvent3())
		)
		DisposeScript(BODYEVENTS1_SCRIPT)
		return
	)
	(if((>= index 4) and (<= index 7))
		Load(rsSCRIPT BODYEVENTS2_SCRIPT)
		(switch(index)
			(case 4 BodyEvent4())
			(case 5 BodyEvent5())
			(case 6 BodyEvent6())
			(case 7 BodyEvent7())
		)
		DisposeScript(BODYEVENTS2_SCRIPT)
		return
	)
	(if((>= index 8) and (<= index 11))
		Load(rsSCRIPT BODYEVENTS3_SCRIPT)
		(switch(index)
			(case 8 BodyEvent8())
			(case 9 BodyEvent9())
			(case 10 BodyEvent10())
			(case 11 BodyEvent11())
		)
		DisposeScript(BODYEVENTS3_SCRIPT)
		return
	)
	(if((>= index 12) and (<= index 15))
		Load(rsSCRIPT BODYEVENTS4_SCRIPT)
		(switch(index)
			(case 12 BodyEvent12())
			(case 13 BodyEvent13())
			(case 14 BodyEvent14())
			(case 15 BodyEvent15())
		)
		DisposeScript(BODYEVENTS4_SCRIPT)
		return
	)
	(if((>= index 16) and (<= index 19))
		Load(rsSCRIPT BODYEVENTS5_SCRIPT)
		(switch(index)
			(case 16 BodyEvent16())
			(case 17 BodyEvent17())
			(case 18 BodyEvent18())
			(case 19 BodyEvent19())
		)
		DisposeScript(BODYEVENTS5_SCRIPT)
		return
	)
	(if((>= index 20) and (<= index 23))
		Load(rsSCRIPT BODYEVENTS6_SCRIPT)
		(switch(index)
			(case 20 BodyEvent20())
			(case 21 BodyEvent21())
			(case 22 BodyEvent22())
			(case 23 BodyEvent23())
		)
		DisposeScript(BODYEVENTS6_SCRIPT)
		return
	)
	(if((>= index 24) and (<= index 27))
		Load(rsSCRIPT BODYEVENTS7_SCRIPT)
		(switch(index)
			(case 24 BodyEvent24())
			(case 25 BodyEvent25())
			(case 26 BodyEvent26())
			(case 27 BodyEvent27())
		)
		DisposeScript(BODYEVENTS7_SCRIPT)
		return
	)
	(if((>= index 28) and (<= index 31))
		Load(rsSCRIPT BODYEVENTS8_SCRIPT)
		(switch(index)
			(case 28 BodyEvent28())
			(case 29 BodyEvent29())
			(case 30 BodyEvent30())
			(case 31 BodyEvent31())
		)
		DisposeScript(BODYEVENTS8_SCRIPT)
		return
	)
)
/******************************************************************************/
