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
(use "endingcontent1")
(use "endingcontent2")
(use "endingcontent3")
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
	)
	(method (printEnding)
		// Full ending-variant port (see SESSION_HANDOFF.md, game.sh,
		// tools/gen-endings.js): each PrintFailureEndingN()/
		// PrintSurvivalEndingN() (endingcontent1-3.sc) now picks one of
		// several real flavor variants at random and marks the matching
		// flat Case Files slot itself -- this method just decides WHICH
		// pool applies, exactly the same condition table/order as before.
		(if(>= gRepression 100)
			PrintFailureEnding0()
			return
		)
		(if(<= gMask 0)
			PrintFailureEnding1()
			return
		)
		(if(<= gChild 0)
			PrintFailureEnding2()
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
		(if(not gHardMode)
			UnlockNgPlus()
		)
		// Same condition table/order as the original's CONTENT_ENDINGS
		// (js/content-endings.js, first match wins) -- matched via a flat
		// sequence of early-return ifs rather than a chained else-if, see
		// SESSION_HANDOFF.md's if/else gotcha for why.
		(if(>= gRepression 70)
			PrintSurvivalEnding0()
			return
		)
		(if((>= gMask 85) and (<= gChild 25))
			PrintSurvivalEnding1()
			return
		)
		(if((>= gChild 75) and (<= gMask 40))
			PrintSurvivalEnding2()
			return
		)
		(if((<= gMask 25) and (>= gChild 25))
			PrintSurvivalEnding3()
			return
		)
		(if((>= gMask 41) and (<= gChild 25))
			PrintSurvivalEnding4()
			return
		)
		(if((<= gRepression 30) and (>= gMask 40) and (<= gMask 70) and (>= gChild 40) and (<= gChild 70))
			PrintSurvivalEnding5()
			return
		)
		(if((<= gRepression 30) and (>= gMask 60) and (>= gChild 60))
			PrintSurvivalEnding6()
			return
		)
		(if((>= gRepression 31) and (<= gRepression 69) and (>= gMask 40) and (>= gChild 40))
			PrintSurvivalEnding7()
			return
		)
		PrintSurvivalEnding8()
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
                        ShowCaseFiles()
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
