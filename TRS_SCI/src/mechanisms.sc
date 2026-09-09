/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 mechanisms.sc
 Coping-mechanism unlock system (matches js/content-mechanisms.js /
 js/engine.js's trackMechanism + applyMechanismModifiers): lean on the same
 tagged response 3 times in a run and it permanently unlocks, after which
 every further choice of that tag also carries a passive stat modifier on
 top of its own written-in effect, for the rest of the run.

 ApplyChoiceEffects is the single entry point every generated WorkEvent<N>
 choice calls: applies the mechanism modifier (if that tag is already
 unlocked), applies the resulting deltas to the three stats, clamps them,
 and tracks/unlocks the mechanism if this tag isn't unlocked yet.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script MECHANISMS_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
/******************************************************************************/
(procedure public (ApplyChoiceEffects repDelta maskDelta childDelta tag)
	(switch(tag)
		(case TAG_FAWN
			(if(gFawnUnlocked)
				= maskDelta (+ maskDelta 3)
				= childDelta (+ childDelta -5)
			)(else
				++gFawnCount
				(if(>= gFawnCount UNLOCK_THRESHOLD)
					= gFawnUnlocked TRUE
					Print("COPING MECHANISM ACQUIRED: The Approval Loop. This will not be undone.")
				)
			)
		)
		(case TAG_FLIGHT
			(if(gFlightUnlocked)
				= repDelta (+ repDelta -5)
				= maskDelta (+ maskDelta -3)
			)(else
				++gFlightCount
				(if(>= gFlightCount UNLOCK_THRESHOLD)
					= gFlightUnlocked TRUE
					Print("COPING MECHANISM ACQUIRED: The Exit Strategy. This will not be undone.")
				)
			)
		)
		(case TAG_FIGHT
			(if(gFightUnlocked)
				= repDelta (+ repDelta -5)
				= maskDelta (+ maskDelta -8)
			)(else
				++gFightCount
				(if(>= gFightCount UNLOCK_THRESHOLD)
					= gFightUnlocked TRUE
					Print("COPING MECHANISM ACQUIRED: Hair-Trigger. This will not be undone.")
				)
			)
		)
		(case TAG_FREEZE
			(if(gFreezeUnlocked)
				= repDelta (+ repDelta 8)
				= childDelta (+ childDelta -5)
			)(else
				++gFreezeCount
				(if(>= gFreezeCount UNLOCK_THRESHOLD)
					= gFreezeUnlocked TRUE
					Print("COPING MECHANISM ACQUIRED: The Void. This will not be undone.")
				)
			)
		)
		(case TAG_SECURE
			(if(gSecureUnlocked)
				= repDelta (+ repDelta -5)
				= childDelta (+ childDelta 5)
			)(else
				++gSecureCount
				(if(>= gSecureCount UNLOCK_THRESHOLD)
					= gSecureUnlocked TRUE
					Print("COPING MECHANISM ACQUIRED: Earned Security. This will not be undone.")
				)
			)
		)
	)
	= gRepression (+ gRepression repDelta)
	= gMask (+ gMask maskDelta)
	= gChild (+ gChild childDelta)
	ClampStats()
)
/******************************************************************************/
(procedure public (ApplyGlitch logMsg)
	// The glitch wildcard (matches js/engine.js's handleGlitchChoice):
	// fully random effects each in [-25, 25], untagged -- never counts
	// toward a coping-mechanism unlock.
	= gRepression (+ gRepression (- Random(0 50) 25))
	= gMask (+ gMask (- Random(0 50) 25))
	= gChild (+ gChild (- Random(0 50) 25))
	ClampStats()
	Print(logMsg)
)
/******************************************************************************/
