/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 socialevents.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js.
 Dispatcher only: DoSocialEvent(index) is the public entry point other
 scripts call; the SOCIAL-zone events' implementations live in
 socialevents1.sc..socialevents4.sc.
 Loads only the chunk script the requested index lives in, and disposes it
 again right after the call -- see the comment on genDispatcher() in
 tools/lib/zone-events.js for why that's load-bearing, not just tidiness.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script SOCIALEVENTS_SCRIPT)
/******************************************************************************/
(use "socialevents1")
(use "socialevents2")
(use "socialevents3")
(use "socialevents4")
/******************************************************************************/
(procedure public (DoSocialEvent index)
	(if((>= index 0) and (<= index 8))
		Load(rsSCRIPT SOCIALEVENTS1_SCRIPT)
		(switch(index)
			(case 0 SocialEvent0())
			(case 1 SocialEvent1())
			(case 2 SocialEvent2())
			(case 3 SocialEvent3())
			(case 4 SocialEvent4())
			(case 5 SocialEvent5())
			(case 6 SocialEvent6())
			(case 7 SocialEvent7())
			(case 8 SocialEvent8())
		)
		DisposeScript(SOCIALEVENTS1_SCRIPT)
		return
	)
	(if((>= index 9) and (<= index 16))
		Load(rsSCRIPT SOCIALEVENTS2_SCRIPT)
		(switch(index)
			(case 9 SocialEvent9())
			(case 10 SocialEvent10())
			(case 11 SocialEvent11())
			(case 12 SocialEvent12())
			(case 13 SocialEvent13())
			(case 14 SocialEvent14())
			(case 15 SocialEvent15())
			(case 16 SocialEvent16())
		)
		DisposeScript(SOCIALEVENTS2_SCRIPT)
		return
	)
	(if((>= index 17) and (<= index 24))
		Load(rsSCRIPT SOCIALEVENTS3_SCRIPT)
		(switch(index)
			(case 17 SocialEvent17())
			(case 18 SocialEvent18())
			(case 19 SocialEvent19())
			(case 20 SocialEvent20())
			(case 21 SocialEvent21())
			(case 22 SocialEvent22())
			(case 23 SocialEvent23())
			(case 24 SocialEvent24())
		)
		DisposeScript(SOCIALEVENTS3_SCRIPT)
		return
	)
	(if((>= index 25) and (<= index 32))
		Load(rsSCRIPT SOCIALEVENTS4_SCRIPT)
		(switch(index)
			(case 25 SocialEvent25())
			(case 26 SocialEvent26())
			(case 27 SocialEvent27())
			(case 28 SocialEvent28())
			(case 29 SocialEvent29())
			(case 30 SocialEvent30())
			(case 31 SocialEvent31())
			(case 32 SocialEvent32())
		)
		DisposeScript(SOCIALEVENTS4_SCRIPT)
		return
	)
)
/******************************************************************************/
