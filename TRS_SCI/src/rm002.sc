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
		(if(>= gRepression 100)
			Print(
				"Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet."
				#title "Panic Attack"
			)
			(if(MarkCaseFile(9))
				Print("Case Files: Panic Attack, filed." #title "New Case File")
			)
			return
		)
		(if(<= gMask 0)
			Print(
				"Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but free."
				#title "Social Exile"
			)
			(if(MarkCaseFile(10))
				Print("Case Files: Social Exile, filed." #title "New Case File")
			)
			return
		)
		(if(<= gChild 0)
			Print(
				"Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory. You feel nothing."
				#title "Total Disassociation"
			)
			(if(MarkCaseFile(11))
				Print("Case Files: Total Disassociation, filed." #title "New Case File")
			)
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
		// One representative title/desc per condition from the original's
		// CONTENT_ENDINGS (js/content-endings.js) -- same condition table,
		// same order (first match wins), just one flavor variant each
		// instead of the original's 8 per pool, to keep this well within
		// the per-script memory budget. Conditions matched via a flat
		// sequence of early-return ifs rather than a chained else-if --
		// see SESSION_HANDOFF.md's if/else gotcha for why.
		(if(>= gRepression 70)
			Print("You didn't explode. You just got very, very good at ticking." #title "The Powder Keg")
			(if(MarkCaseFile(0))
				Print("Case Files: The Powder Keg, filed." #title "New Case File")
			)
			return
		)
		(if((>= gMask 85) and (<= gChild 25))
			Print("Nobody has seen the real you in years, including you." #title "The Performer")
			(if(MarkCaseFile(1))
				Print("Case Files: The Performer, filed." #title "New Case File")
			)
			return
		)
		(if((>= gChild 75) and (<= gMask 40))
			Print("You stopped hiding. It cost you more than you expected, but you kept yourself." #title "Radically Undone")
			(if(MarkCaseFile(2))
				Print("Case Files: Radically Undone, filed." #title "New Case File")
			)
			return
		)
		(if((<= gMask 25) and (>= gChild 25))
			Print("You stopped filtering. Everything's a little too loud and a little too close to the surface right now." #title "Raw Nerve")
			(if(MarkCaseFile(3))
				Print("Case Files: Raw Nerve, filed." #title "New Case File")
			)
			return
		)
		(if((>= gMask 41) and (<= gChild 25))
			Print("You looked fine all day. You have no real idea what was actually fueling that." #title "Coasting on Empty")
			(if(MarkCaseFile(4))
				Print("Case Files: Coasting on Empty, filed." #title "New Case File")
			)
			return
		)
		(if((<= gRepression 30) and (>= gMask 40) and (<= gMask 70) and (>= gChild 40) and (<= gChild 70))
			Print("Nothing is fixed. Nothing is on fire. This might be what okay feels like." #title "Fragile Equilibrium")
			(if(MarkCaseFile(5))
				Print("Case Files: Fragile Equilibrium, filed." #title "New Case File")
			)
			return
		)
		(if((<= gRepression 30) and (>= gMask 60) and (>= gChild 60))
			Print("Not surviving. Not performing. Just, for once, actually okay. You can tell the difference from the inside." #title "Actually Okay")
			(if(MarkCaseFile(6))
				Print("Case Files: Actually Okay, filed." #title "New Case File")
			)
			return
		)
		(if((>= gRepression 31) and (<= gRepression 69) and (>= gMask 40) and (>= gChild 40))
			Print("You're carrying more than you'd like to admit, and carrying it fine, for now." #title "The Long Fuse")
			(if(MarkCaseFile(7))
				Print("Case Files: The Long Fuse, filed." #title "New Case File")
			)
			return
		)
		Print("You made it to tomorrow. Good job." #title "Functional Enough")
		(if(MarkCaseFile(8))
			Print("Case Files: Functional Enough, filed." #title "New Case File")
		)
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
