/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 publicevents.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js.
 Dispatcher only: DoPublicEvent(index) is the public entry point other
 scripts call; the PUBLIC-zone events' implementations live in
 publicevents1.sc..publicevents4.sc.
 Loads only the chunk script the requested index lives in, and disposes it
 again right after the call -- see the comment on genDispatcher() in
 tools/lib/zone-events.js for why that's load-bearing, not just tidiness.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PUBLICEVENTS_SCRIPT)
/******************************************************************************/
(use "publicevents1")
(use "publicevents2")
(use "publicevents3")
(use "publicevents4")
/******************************************************************************/
(procedure public (DoPublicEvent index)
	(if((>= index 0) and (<= index 7))
		Load(rsSCRIPT PUBLICEVENTS1_SCRIPT)
		(switch(index)
			(case 0 PublicEvent0())
			(case 1 PublicEvent1())
			(case 2 PublicEvent2())
			(case 3 PublicEvent3())
			(case 4 PublicEvent4())
			(case 5 PublicEvent5())
			(case 6 PublicEvent6())
			(case 7 PublicEvent7())
		)
		DisposeScript(PUBLICEVENTS1_SCRIPT)
		return
	)
	(if((>= index 8) and (<= index 15))
		Load(rsSCRIPT PUBLICEVENTS2_SCRIPT)
		(switch(index)
			(case 8 PublicEvent8())
			(case 9 PublicEvent9())
			(case 10 PublicEvent10())
			(case 11 PublicEvent11())
			(case 12 PublicEvent12())
			(case 13 PublicEvent13())
			(case 14 PublicEvent14())
			(case 15 PublicEvent15())
		)
		DisposeScript(PUBLICEVENTS2_SCRIPT)
		return
	)
	(if((>= index 16) and (<= index 23))
		Load(rsSCRIPT PUBLICEVENTS3_SCRIPT)
		(switch(index)
			(case 16 PublicEvent16())
			(case 17 PublicEvent17())
			(case 18 PublicEvent18())
			(case 19 PublicEvent19())
			(case 20 PublicEvent20())
			(case 21 PublicEvent21())
			(case 22 PublicEvent22())
			(case 23 PublicEvent23())
		)
		DisposeScript(PUBLICEVENTS3_SCRIPT)
		return
	)
	(if((>= index 24) and (<= index 31))
		Load(rsSCRIPT PUBLICEVENTS4_SCRIPT)
		(switch(index)
			(case 24 PublicEvent24())
			(case 25 PublicEvent25())
			(case 26 PublicEvent26())
			(case 27 PublicEvent27())
			(case 28 PublicEvent28())
			(case 29 PublicEvent29())
			(case 30 PublicEvent30())
			(case 31 PublicEvent31())
		)
		DisposeScript(PUBLICEVENTS4_SCRIPT)
		return
	)
)
/******************************************************************************/
