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
					Load(rsSCRIPT CASEFILES_SCRIPT)
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_FAWN))
						Print("Case Files: The Approval Loop, filed." #title "New Case File")
					)
					DisposeScript(CASEFILES_SCRIPT)
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
					Load(rsSCRIPT CASEFILES_SCRIPT)
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_FLIGHT))
						Print("Case Files: The Exit Strategy, filed." #title "New Case File")
					)
					DisposeScript(CASEFILES_SCRIPT)
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
					Load(rsSCRIPT CASEFILES_SCRIPT)
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_FIGHT))
						Print("Case Files: Hair-Trigger, filed." #title "New Case File")
					)
					DisposeScript(CASEFILES_SCRIPT)
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
					Load(rsSCRIPT CASEFILES_SCRIPT)
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_FREEZE))
						Print("Case Files: The Void, filed." #title "New Case File")
					)
					DisposeScript(CASEFILES_SCRIPT)
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
					Load(rsSCRIPT CASEFILES_SCRIPT)
					(if(MarkCaseFile(+ CASEFILE_MECH_BASE TAG_SECURE))
						Print("Case Files: Earned Security, filed." #title "New Case File")
					)
					DisposeScript(CASEFILES_SCRIPT)
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
// ZoneStatBias/PickZone/GoToNextEvent/EndTurn moved here from rm001.sc as
// part of the one-room-per-event rewrite (see game.sh and
// SESSION_HANDOFF.md) -- rm001 is no longer resident once a run leaves it
// (a normal SCI room, disposed like any other on transition), but every
// one of the 196 event rooms needs to pick+transition to the NEXT event
// after its own turn resolves, so this logic has to live in an
// always-resident script. Same reasoning PickWorstStat() above already
// documents for why it isn't in rm001.sc either.
(procedure public (ZoneStatBias zoneIndex)
	// Same zone -> stat-bias mapping as the original's content.js `zones`
	// array. Return value encoding matches PickWorstStat: 0=repression,
	// 1=mask, 2=child.
	(switch(zoneIndex)
		(case 0 return(0))		/* WORK -> repression */
		(case 1 return(2))		/* HOME -> child */
		(case 2 return(1))		/* SOCIAL -> mask */
		(case 3 return(2))		/* SELF -> child */
		(case 4 return(0))		/* BODY -> repression */
		(case 5 return(1))		/* PUBLIC -> mask */
	)
	return(-1)
)
/******************************************************************************/
(procedure public (PickZone)
	// Weighted zone selection matching the original's pickWeightedEvent():
	// zones whose stat bias matches the current worst stat get extra
	// weight (the original's weakZoneWeight is 2.5x; scaled here to
	// integer weights 5 vs 2, same ratio, since SCI0 arithmetic is
	// integer-only). With exactly 2 of the current 6 zones matching any
	// given worst stat, total weight is always 4*2 + 2*5 = 18 -- if the
	// zone/stat-bias mix ever changes this hardcoded 18 (and the Random
	// bound below) would need recomputing.
	// NOT replicated: the original's "don't repeat an event already seen
	// this run" pool -- a separate, bigger feature (needs per-event seen-
	// tracking across all 196 events), not attempted here.
	(var worstStat, i, r, w)
	= worstStat PickWorstStat()
	= r Random(0 17)
	(for (= i 0) (< i ZONE_COUNT) (++i)
		(if(== ZoneStatBias(i) worstStat)
			= w 5
		)(else
			= w 2
		)
		(if(< r w)
			return(i)
		)
		= r (- r w)
	)
	return(0)
)
/******************************************************************************/
(procedure public (GoToNextEvent)
	// Picks a zone-weighted random event (PickZone() + a uniform index
	// within that zone) and transitions straight to its room --
	// <ZONE>_ROOM_BASE + localIndex (game.sh). No dispatcher script to
	// Load anymore; the room IS the event, and the engine's own
	// newRoom()/room-transition cleanup replaces the old manual
	// Load/DisposeScript chunk-cycling entirely (see game.sh's freed-
	// script-numbers comment for why that was necessary). Called once
	// from rm001.sc's init() (the very first event of a run) and again
	// from EndTurn() below every time a turn continues.
	(var zone, index)
	= zone PickZone()
	(switch(zone)
		(case 0
			= index Random(0 (- WORK_EVENT_COUNT 1))
			(send gRoom:newRoom(+ WORK_ROOM_BASE index))
		)
		(case 1
			= index Random(0 (- HOME_EVENT_COUNT 1))
			(send gRoom:newRoom(+ HOME_ROOM_BASE index))
		)
		(case 2
			= index Random(0 (- SOCIAL_EVENT_COUNT 1))
			(send gRoom:newRoom(+ SOCIAL_ROOM_BASE index))
		)
		(case 3
			= index Random(0 (- SELF_EVENT_COUNT 1))
			(send gRoom:newRoom(+ SELF_ROOM_BASE index))
		)
		(case 4
			= index Random(0 (- BODY_EVENT_COUNT 1))
			(send gRoom:newRoom(+ BODY_ROOM_BASE index))
		)
		(case 5
			= index Random(0 (- PUBLIC_EVENT_COUNT 1))
			(send gRoom:newRoom(+ PUBLIC_ROOM_BASE index))
		)
	)
)
/******************************************************************************/
(procedure public (EndTurn)
	// The one place every event room ends its own turn -- called at the
	// very end of each of the 196 event rooms' init(), after their own
	// PrintChoices/ApplyChoiceEffects/log-line logic has already run.
	// Increments the turn counter, clamps stats (same as the old
	// runShift() loop body did after every DoXEvent call), then checks
	// the exact same end conditions the old while-loop guarded on
	// (De Morgan's negation of "<= gTurn gMaxTurns and all three stats
	// still alive"): if the run is over, hand off to the ending room
	// exactly like the old runShift() did; otherwise pick and go to the
	// next event. rm001.sc's init() also calls this once, with gTurn
	// pre-set to 0, to uniformly bootstrap the very first turn through
	// the same increment-check-branch logic rather than duplicating it.
	++gTurn
	ClampStats()
	(if((> gTurn gMaxTurns) or (>= gRepression 100) or (<= gMask 0) or (<= gChild 0))
		(send gRoom:newRoom(ENDING_ROOM))
		return
	)
	GoToNextEvent()
)
/******************************************************************************/
