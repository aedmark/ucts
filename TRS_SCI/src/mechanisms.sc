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
(use "casefiles")
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
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_FAWN))
						Print("Case Files: The Approval Loop, filed." #title "New Case File")
					)
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
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_FLIGHT))
						Print("Case Files: The Exit Strategy, filed." #title "New Case File")
					)
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
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_FIGHT))
						Print("Case Files: Hair-Trigger, filed." #title "New Case File")
					)
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
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_FREEZE))
						Print("Case Files: The Void, filed." #title "New Case File")
					)
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
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_SECURE))
						Print("Case Files: Earned Security, filed." #title "New Case File")
					)
				)
			)
		)
	)
	= repDelta ScaleHardMode(repDelta)
	= maskDelta ScaleHardMode(maskDelta)
	= childDelta ScaleHardMode(childDelta)
	= gRepression (+ gRepression repDelta)
	= gMask (+ gMask maskDelta)
	= gChild (+ gChild childDelta)
	ClampStats()
	// No DrawPortraitMood() call here anymore -- PrintChoices now shows
	// the portrait live inside its own dialog (printchoices.sc), computed
	// fresh via GetPortraitMood() on every call, so a background redraw
	// here would just be a duplicate immediately hidden by that same
	// dialog on the very next turn.
)
/******************************************************************************/
(procedure public (ApplyGlitch logMsg)
	// The glitch wildcard (matches js/engine.js's handleGlitchChoice):
	// fully random effects each in [-25, 25], untagged -- never counts
	// toward a coping-mechanism unlock. handleGlitchChoice routes through
	// the same handleChoice() as everything else in the original, so the
	// Extended Therapy multiplier applies here too.
	= gRepression (+ gRepression ScaleHardMode(- Random(0 50) 25))
	= gMask (+ gMask ScaleHardMode(- Random(0 50) 25))
	= gChild (+ gChild ScaleHardMode(- Random(0 50) 25))
	ClampStats()
	Print(logMsg)
)
/******************************************************************************/
(procedure public (ScaleHardMode delta)
	// Extended Therapy's stat-swing multiplier (matches js/content.js's
	// hardModeMultiplier: 1.25, applied in js/engine.js's handleChoice).
	// SCI0 has no floats -- 1.25 = 5/4, applied as an integer
	// multiply-then-divide with the +2 numerator nudge rounding to the
	// nearest whole number instead of truncating (so e.g. a delta of 4
	// scales to 5, not silently staying 4 via floor division). Deliberately
	// works on the absolute value and reapplies the sign afterward rather
	// than dividing a negative numerator directly -- this codebase has no
	// confirmed precedent either way for how SCI0's "/" rounds negative
	// operands, and this sidesteps needing one.
	(var absDelta, scaled)
	(if(not gHardMode)
		return(delta)
	)
	(if(< delta 0)
		= absDelta (- 0 delta)
		= scaled (/ (+ (* absDelta HARD_MODE_MULT_NUM) 2) HARD_MODE_MULT_DEN)
		return(- 0 scaled)
	)
	return(/ (+ (* delta HARD_MODE_MULT_NUM) 2) HARD_MODE_MULT_DEN)
)
/******************************************************************************/
(procedure public (PickWorstStat)
	// 0 = repression, 1 = mask, 2 = child. Matches the original's danger
	// comparison (repression/100, (100-mask)/100, (100-child)/100) --
	// mask/child inverted here into "danger" terms too so all three
	// compare the same way (higher = worse), then compared as plain
	// integers since SCI0 has no floats. Ties favor the earlier stat in
	// the list (repression over mask, mask over child), matching the
	// original's Array.reduce order exactly. Shared by rm001.sc's
	// PickZone() (zone-weighting) and DrawPortraitMood() below -- lives
	// here rather than in rm001.sc so both a room and this always-
	// resident utility script can call it without a room depending on
	// another room, or a new circular use-pair between scripts that have
	// never bootstrapped each other before (see the casefiles.sc saga in
	// SESSION_HANDOFF.md for why that's worth avoiding).
	(var worst, worstDanger, dangerMask, dangerChild)
	= worst 0
	= worstDanger gRepression
	= dangerMask (- 100 gMask)
	(if(> dangerMask worstDanger)
		= worst 1
		= worstDanger dangerMask
	)
	= dangerChild (- 100 gChild)
	(if(> dangerChild worstDanger)
		= worst 2
		= worstDanger dangerChild
	)
	return(worst)
)
/******************************************************************************/
(procedure public (GetPortraitMood)
	// Loop numbers double as mood: 0 neutral, 1 repression, 2 mask,
	// 3 child. Split out of DrawPortraitMood() below so PrintChoices
	// (printchoices.sc) can also ask for the current mood directly, to
	// show the portrait inside the event dialog itself rather than only
	// on the room background (see SESSION_HANDOFF.md).
	(var worst, worstDanger, dangerMask, dangerChild)
	= worst 0
	= worstDanger gRepression
	= dangerMask (- 100 gMask)
	(if(> dangerMask worstDanger)
		= worst 1
		= worstDanger dangerMask
	)
	= dangerChild (- 100 gChild)
	(if(> dangerChild worstDanger)
		= worst 2
		= worstDanger dangerChild
	)
	(if(< worstDanger PORTRAIT_NEUTRAL_THRESHOLD)
		return(PORTRAIT_MOOD_NEUTRAL)
	)
	return(+ worst 1)
)
/******************************************************************************/
