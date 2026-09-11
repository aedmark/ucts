/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm002.sc
 The end-of-run room: entered via a scripted (send gRoom:newRoom(ENDING_ROOM))
 from rm001's runShift once a stat hits a fatal threshold or gMaxTurns is
 reached. Re-checks the same thresholds/conditions rm001 already evaluated
 (final stat values are still sitting in gRepression/gMask/gChild -- a room
 transition doesn't touch them) to decide which one-line ending to show.
 Split into its own room specifically so the turn's accumulated state gets
 released via the engine's normal room-transition cleanup rather than
 falling through to ordinary ego/room control inside rm001 -- see
 SESSION_HANDOFF.md.
 No restart flow yet -- once the ending prints, ego is just left walking
 around this (currently blank, reusing rm001's background) room. That's
 still-not-built scope, not new to this change.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDING_ROOM)
/******************************************************************************/
(use "main")
(use "controls")
(use "cycle")
(use "game")
(use "feature")
(use "obj")
(use "inv")
(use "casefiles")
(use "mechanisms")
(use "endingsurvival0")
(use "endingsurvival1")
(use "endingsurvival2")
(use "endingsurvival3")
(use "endingsurvival4")
(use "endingsurvival5")
(use "endingsurvival6")
(use "endingsurvival7")
(use "endingsurvival8")
(use "endingfailure0")
(use "endingfailure1")
(use "endingfailure2")
/******************************************************************************/
(instance public rm002 of Rm
	(properties
		picture 1
		// No neighbors -- dead-end ending room, no restart flow yet.
		north 0
		east 0
		south 0
		west 0
	)
	(method (init)
		// TEMPORARY DEBUG INSTRUMENTATION -- remove once the cross-run
		// heap-loss mystery is found (see SESSION_HANDOFF.md). Brackets
		// the rm001->rm002 room transition -- Main.sc's Template:newRoom()
		// (called via super:init() -> Rm:init() -> ... -> newRoom()) re-
		// Load()s fonts/cursors/PORTRAIT_VIEW on every transition without
		// ever disposing them; if that's not a true no-op on an
		// already-resident resource, this is where it'd show up.
		DebugLog("DEBUG rm002 init ENTRY: heap=%u largest=%u" MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
		(super:init())
		(self:setScript(RoomScript))

		(send gEgo:
			posn(150 130)
			loop(1)
		)

		SetUpEgo()
		(send gEgo:init())
		// SetUpEgo() grants player movement control (it calls
		// PlayerControl() internally) -- there's nothing to walk around
		// and interact with in this room yet, so take it back immediately
		// rather than leaving ego free to wander over the ending text.
		ProgramControl()
		// Frozen-but-visible still reads as "broken" now that there's real
		// art -- hide ego outright instead, same call TitleScreen.sc
		// already uses to keep ego off the title screen.
		(send gEgo:hide())
		// No portrait here -- user wants it visible only during actual
		// play (inside PrintChoices' own dialog, printchoices.sc), not on
		// any room background, including the ending room.

		(self:printEnding())
		// DEBUG: brackets printEnding() (UnlockNgPlus's CaseFileAccess
		// load, the one ending pool's Load/Print/MarkCaseFile/Dispose
		// cycle) -- compare against ENTRY above to isolate what the
		// ending sequence itself costs, separate from the room
		// transition that got us here.
		DebugLog("DEBUG rm002 init EXIT: heap=%u largest=%u" MemoryInfo(miFREEHEAP) MemoryInfo(miLARGESTPTR))
	)
	(method (printEnding)
		// Full ending-variant port (see SESSION_HANDOFF.md, game.sh,
		// tools/gen-endings.js): each PrintFailureEndingN()/
		// PrintSurvivalEndingN() (one file per pool -- endingsurvival0-8.sc/
		// endingfailure0-2.sc) picks one of several real flavor variants at
		// random and marks the matching flat Case Files slot itself -- this
		// method just decides WHICH pool applies, exactly the same
		// condition table/order as before. Each call is wrapped in
		// Load(rsSCRIPT ...)/DisposeScript(...) -- real bug, confirmed the
		// hard way: without this, whichever script a given ending lives in
		// auto-loads on first call and never gets disposed, same as any
		// script in this engine. Harmless within a single run, but the
		// clickable "computer -> new run" hotspot (rm002.sc's RoomScript)
		// makes it trivial to rack up many runs without ever fully
		// relaunching, so different endings across different runs kept
		// accumulating several of these scripts permanently resident at
		// once. One script per POOL (not the original's 4-5-pools-per-file
		// bundling) is itself a later fix -- see SESSION_HANDOFF.md's
		// turn-10 heap-exhaustion entry: the bundled files were big enough
		// (~9.1-11.9KB) that loading the one pool actually needed dragged
		// in several dead ones too, on top of MarkCaseFile()'s own nested
		// Load/DisposeScript of CASEFILEACCESS_SCRIPT firing while that
		// whole bundle was still resident.
		// CASEFILES_SCRIPT (MarkCaseFile) now Load/DisposeScript wrapped
		// around each branch too -- CaseFiles.sc was ~12KB and permanently
		// resident from the moment anything ever called MarkCaseFile
		// (mechanisms.sc's unlock branches, or here), a real, sizeable
		// chunk of permanent baseline heap given how thin margins already
		// are (see SESSION_HANDOFF.md). Same idiom as CASEFILEACCESS_SCRIPT
		// and CASEFILETITLES_SCRIPT before it.
		(if(>= gRepression 100)
			Load(rsSCRIPT CASEFILES_SCRIPT)
			Load(rsSCRIPT ENDINGFAILURE0_SCRIPT)
			PrintFailureEnding0()
			DisposeScript(ENDINGFAILURE0_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if(<= gMask 0)
			Load(rsSCRIPT CASEFILES_SCRIPT)
			Load(rsSCRIPT ENDINGFAILURE1_SCRIPT)
			PrintFailureEnding1()
			DisposeScript(ENDINGFAILURE1_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if(<= gChild 0)
			Load(rsSCRIPT CASEFILES_SCRIPT)
			Load(rsSCRIPT ENDINGFAILURE2_SCRIPT)
			PrintFailureEnding2()
			DisposeScript(ENDINGFAILURE2_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(self:printSurvivalEnding())
	)
	(method (printSurvivalEnding)
		// Matches js/engine.js's checkGameEnd(): a standard-session survival
		// permanently unlocks Extended Therapy for all future runs -- an
		// Extended Therapy run surviving doesn't re-trigger anything (it's
		// already unlocked, and the original only calls this when
		// `!state.hardMode` too).
		// One Load(CASEFILES_SCRIPT) covers UnlockNgPlus() (which itself
		// calls MarkCaseFile) AND whichever PrintSurvivalEndingN() branch
		// fires below (each also calls MarkCaseFile for its own variant) --
		// cheaper than disposing/reloading between the two, since both
		// always happen together in this method.
		Load(rsSCRIPT CASEFILES_SCRIPT)
		(if(not gHardMode)
			UnlockNgPlus()
		)
		// Same condition table/order as the original's CONTENT_ENDINGS
		// (js/content-endings.js, first match wins) -- matched via a flat
		// sequence of early-return ifs rather than a chained else-if, see
		// SESSION_HANDOFF.md's if/else gotcha for why.
		(if(>= gRepression 70)
			Load(rsSCRIPT ENDINGSURVIVAL0_SCRIPT)
			PrintSurvivalEnding0()
			DisposeScript(ENDINGSURVIVAL0_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((>= gMask 85) and (<= gChild 25))
			Load(rsSCRIPT ENDINGSURVIVAL1_SCRIPT)
			PrintSurvivalEnding1()
			DisposeScript(ENDINGSURVIVAL1_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((>= gChild 75) and (<= gMask 40))
			Load(rsSCRIPT ENDINGSURVIVAL2_SCRIPT)
			PrintSurvivalEnding2()
			DisposeScript(ENDINGSURVIVAL2_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((<= gMask 25) and (>= gChild 25))
			Load(rsSCRIPT ENDINGSURVIVAL3_SCRIPT)
			PrintSurvivalEnding3()
			DisposeScript(ENDINGSURVIVAL3_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((>= gMask 41) and (<= gChild 25))
			Load(rsSCRIPT ENDINGSURVIVAL4_SCRIPT)
			PrintSurvivalEnding4()
			DisposeScript(ENDINGSURVIVAL4_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((<= gRepression 30) and (>= gMask 40) and (<= gMask 70) and (>= gChild 40) and (<= gChild 70))
			Load(rsSCRIPT ENDINGSURVIVAL5_SCRIPT)
			PrintSurvivalEnding5()
			DisposeScript(ENDINGSURVIVAL5_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((<= gRepression 30) and (>= gMask 60) and (>= gChild 60))
			Load(rsSCRIPT ENDINGSURVIVAL6_SCRIPT)
			PrintSurvivalEnding6()
			DisposeScript(ENDINGSURVIVAL6_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((>= gRepression 31) and (<= gRepression 69) and (>= gMask 40) and (>= gChild 40))
			Load(rsSCRIPT ENDINGSURVIVAL7_SCRIPT)
			PrintSurvivalEnding7()
			DisposeScript(ENDINGSURVIVAL7_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		Load(rsSCRIPT ENDINGSURVIVAL8_SCRIPT)
		PrintSurvivalEnding8()
		DisposeScript(ENDINGSURVIVAL8_SCRIPT)
		DisposeScript(CASEFILES_SCRIPT)
	)
)
/******************************************************************************/
(instance RoomScript of Script
	(properties)
	(method (handleEvent pEvent)
        (super:handleEvent(pEvent))
        // Clickable filing cabinet -> Case Files viewer (see game.sh for
        // why this lives here and not rm001, and for the hotspot rectangle
        // itself). Nested ifs rather than one long and-chain -- this
        // codebase has no confirmed precedent for and-chains longer than
        // 4 terms, and this needs 5 (unclaimed, click type, 2 x-bounds,
        // 2 y-bounds), so it's split into two 2-term chains instead of
        // gambling on an unverified length.
        (if(not (send pEvent:claimed))
            (if(== (send pEvent:type) evMOUSEBUTTON)
                (if((>= (send pEvent:x) CABINET_X1) and (< (send pEvent:x) CABINET_X2))
                    (if((>= (send pEvent:y) CABINET_Y1) and (< (send pEvent:y) CABINET_Y2))
                        (send pEvent:claimed(TRUE))
                        Load(rsSCRIPT CASEFILES_SCRIPT)
                        ShowCaseFiles()
                        DisposeScript(CASEFILES_SCRIPT)
                    )
                )
            )
        )
        // Clickable computer -> starts a new run (rm001.sc's init() does
        // the actual per-run state reset; this just triggers a plain
        // room transition there, same newRoom() idiom runShift() already
        // uses to leave rm001, just in reverse and without any VM-level
        // RestartGame() -- see game.sh and rm001.sc). No confirmation
        // prompt, unlike the "Restart Game" menu item -- that one
        // interrupts a run already in progress and has real progress to
        // lose; this is only ever clickable once a run has already ended,
        // so there's nothing to accidentally lose by clicking it.
        (if(not (send pEvent:claimed))
            (if(== (send pEvent:type) evMOUSEBUTTON)
                (if((>= (send pEvent:x) COMPUTER_X1) and (< (send pEvent:x) COMPUTER_X2))
                    (if((>= (send pEvent:y) COMPUTER_Y1) and (< (send pEvent:y) COMPUTER_Y2))
                        (send pEvent:claimed(TRUE))
                        (send gRoom:newRoom(INITROOMS_SCRIPT))
                    )
                )
            )
        )
        (if(Said('look'))
            Print("You are in an empty room")
        )
 	)
)
/******************************************************************************/
