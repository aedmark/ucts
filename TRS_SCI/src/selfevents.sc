/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 selfevents.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js.
 Dispatcher only: DoSelfEvent(index) is the public entry point other
 scripts call; the SELF-zone events' implementations live in
 selfevents1.sc..selfevents8.sc.
 Loads only the chunk script the requested index lives in, and disposes it
 again right after the call -- see the comment on genDispatcher() in
 tools/lib/zone-events.js for why that's load-bearing, not just tidiness.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SELFEVENTS_SCRIPT)
/******************************************************************************/
(use "selfevents1")
(use "selfevents2")
(use "selfevents3")
(use "selfevents4")
(use "selfevents5")
(use "selfevents6")
(use "selfevents7")
(use "selfevents8")
/******************************************************************************/
(procedure public (DoSelfEvent index)
	(if((>= index 0) and (<= index 4))
		Load(rsSCRIPT SELFEVENTS1_SCRIPT)
		(switch(index)
			(case 0 SelfEvent0())
			(case 1 SelfEvent1())
			(case 2 SelfEvent2())
			(case 3 SelfEvent3())
			(case 4 SelfEvent4())
		)
		DisposeScript(SELFEVENTS1_SCRIPT)
		return
	)
	(if((>= index 5) and (<= index 8))
		Load(rsSCRIPT SELFEVENTS2_SCRIPT)
		(switch(index)
			(case 5 SelfEvent5())
			(case 6 SelfEvent6())
			(case 7 SelfEvent7())
			(case 8 SelfEvent8())
		)
		DisposeScript(SELFEVENTS2_SCRIPT)
		return
	)
	(if((>= index 9) and (<= index 12))
		Load(rsSCRIPT SELFEVENTS3_SCRIPT)
		(switch(index)
			(case 9 SelfEvent9())
			(case 10 SelfEvent10())
			(case 11 SelfEvent11())
			(case 12 SelfEvent12())
		)
		DisposeScript(SELFEVENTS3_SCRIPT)
		return
	)
	(if((>= index 13) and (<= index 16))
		Load(rsSCRIPT SELFEVENTS4_SCRIPT)
		(switch(index)
			(case 13 SelfEvent13())
			(case 14 SelfEvent14())
			(case 15 SelfEvent15())
			(case 16 SelfEvent16())
		)
		DisposeScript(SELFEVENTS4_SCRIPT)
		return
	)
	(if((>= index 17) and (<= index 20))
		Load(rsSCRIPT SELFEVENTS5_SCRIPT)
		(switch(index)
			(case 17 SelfEvent17())
			(case 18 SelfEvent18())
			(case 19 SelfEvent19())
			(case 20 SelfEvent20())
		)
		DisposeScript(SELFEVENTS5_SCRIPT)
		return
	)
	(if((>= index 21) and (<= index 24))
		Load(rsSCRIPT SELFEVENTS6_SCRIPT)
		(switch(index)
			(case 21 SelfEvent21())
			(case 22 SelfEvent22())
			(case 23 SelfEvent23())
			(case 24 SelfEvent24())
		)
		DisposeScript(SELFEVENTS6_SCRIPT)
		return
	)
	(if((>= index 25) and (<= index 28))
		Load(rsSCRIPT SELFEVENTS7_SCRIPT)
		(switch(index)
			(case 25 SelfEvent25())
			(case 26 SelfEvent26())
			(case 27 SelfEvent27())
			(case 28 SelfEvent28())
		)
		DisposeScript(SELFEVENTS7_SCRIPT)
		return
	)
	(if((>= index 29) and (<= index 32))
		Load(rsSCRIPT SELFEVENTS8_SCRIPT)
		(switch(index)
			(case 29 SelfEvent29())
			(case 30 SelfEvent30())
			(case 31 SelfEvent31())
			(case 32 SelfEvent32())
		)
		DisposeScript(SELFEVENTS8_SCRIPT)
		return
	)
)
/******************************************************************************/
