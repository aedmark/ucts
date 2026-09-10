/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 casefiles.sc
 Cross-run "Case Files" persistence (matches the original browser game's
 Case Files feature, README.md: "a persistent, cross-run record of every
 ending and coping mechanism you've ever actually seen"). Scope cut from
 the original: no in-game gallery/viewer screen yet, and this SCI0 port
 only has 9 survival + 3 failure endings (one flavor variant each, not the
 original's 8-10 per pool -- see SESSION_HANDOFF.md), so there are 12
 ending slots here instead of 102. Still a real external file, following
 the same QFG-style pattern documented in docs/SCI0-research-findings.md:
 plain ASCII, one value per line, FOpen/FGets/FPuts/FClose via the
 template's fileio.sc.

 gCF0..gCF17 (declared in Main.sc, CASEFILE_COUNT = 18 of them) hold the
 in-memory discovery flags for this session -- individual scalar globals,
 not an array (see SESSION_HANDOFF.md for why). GetCaseFile/SetCaseFile
 below are the array-like accessors everything else in this file (and
 MarkCaseFile's callers in mechanisms.sc/rm002.sc) actually uses; nothing
 outside this file needs to know they're really 18 separate globals.
 LoadCaseFiles()/SaveCaseFiles() sync all 18 with TRSCASE.DAT (a bare
 filename -- resolves relative to the game's own directory, same as the
 stock SRDialog code's own file access). Slot 17 (CASEFILE_NGPLUS) is the
 unrelated Extended Therapy unlock flag, not a real case file -- riding
 on this file/array purely because it's the one proven persistence
 mechanism in this codebase (see game.sh); ShowCaseFiles()'s viewer stays
 scoped to VIEWABLE_CASEFILE_COUNT (17) so it doesn't show up as a bogus
 18th entry.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILES_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
/******************************************************************************/
(procedure public (GetCaseFile index)
	(switch(index)
		(case 0 return(gCF0))
		(case 1 return(gCF1))
		(case 2 return(gCF2))
		(case 3 return(gCF3))
		(case 4 return(gCF4))
		(case 5 return(gCF5))
		(case 6 return(gCF6))
		(case 7 return(gCF7))
		(case 8 return(gCF8))
		(case 9 return(gCF9))
		(case 10 return(gCF10))
		(case 11 return(gCF11))
		(case 12 return(gCF12))
		(case 13 return(gCF13))
		(case 14 return(gCF14))
		(case 15 return(gCF15))
		(case 16 return(gCF16))
		(case 17 return(gCF17))
	)
	return(0)
)
/******************************************************************************/
(procedure public (SetCaseFile index value)
	(switch(index)
		(case 0 = gCF0 value)
		(case 1 = gCF1 value)
		(case 2 = gCF2 value)
		(case 3 = gCF3 value)
		(case 4 = gCF4 value)
		(case 5 = gCF5 value)
		(case 6 = gCF6 value)
		(case 7 = gCF7 value)
		(case 8 = gCF8 value)
		(case 9 = gCF9 value)
		(case 10 = gCF10 value)
		(case 11 = gCF11 value)
		(case 12 = gCF12 value)
		(case 13 = gCF13 value)
		(case 14 = gCF14 value)
		(case 15 = gCF15 value)
		(case 16 = gCF16 value)
		(case 17 = gCF17 value)
	)
)
/******************************************************************************/
(procedure public (LoadCaseFiles)
	(var hFile, i, lineBuf[6])
	// Deliberately fOPENCREATE here, not fOPENFAIL -- see game.sh/
	// SESSION_HANDOFF.md: SCI0's fOPENFAIL/fOPENCREATE numeric values
	// are swapped from what their names suggest (confirmed via SCI
	// Companion's own bundled FOpen.html kernel docs and sci.sh's
	// #ifdef SCI_0 block) -- fOPENCREATE is the one that actually
	// means "open existing, abort/fail if not possible", which is
	// the safe read-only semantics this procedure needs. Using
	// fOPENFAIL here (as this procedure did for this entire project
	// up to this fix) actually invokes "open or create" behavior,
	// which turns out to truncate/reset the file's content before
	// this procedure ever gets to read it -- the real root cause of
	// the New Game+ debugging saga above: every load was silently
	// wiping TRSCASE.DAT immediately before trying to read it back.
	= hFile FOpen("TRSCASE.DAT" fOPENCREATE)
	// Checking truthiness rather than == -1 specifically, matching
	// the stock fileio.sc File class's own defensive pattern -- NULL
	// is 0 in this dialect (confirmed via sci.sh), and it's not
	// certain a failed FOpen always returns -1 rather than NULL/0
	// specifically.
	(if(not hFile)
		return
	)
	(for (= i 0) (< i CASEFILE_COUNT) (++i)
		FGets(@lineBuf 6 hFile)
		SetCaseFile(i ReadNumber(@lineBuf))
	)
	FClose(hFile)
)
/******************************************************************************/
(procedure public (SaveCaseFiles)
	(var hFile, i, lineBuf[6])
	= hFile FOpen("TRSCASE.DAT" fCREATE)
	(if(== hFile -1)
		return
	)
	(for (= i 0) (< i CASEFILE_COUNT) (++i)
		Format(@lineBuf "%d\n" GetCaseFile(i))
		FPuts(hFile @lineBuf)
	)
	FClose(hFile)
)
/******************************************************************************/
(procedure public (MarkCaseFile index)
	// Flips this case file permanently and persists it right away if this
	// is the first time it's ever been seen. Returns TRUE exactly when
	// that happened, so callers can decide whether to announce it -- this
	// procedure itself never prints anything.
	(if(not GetCaseFile(index))
		SetCaseFile(index 1)
		SaveCaseFiles()
		return(TRUE)
	)
	return(FALSE)
)
/******************************************************************************/
(procedure public (UnlockNgPlus)
	// Extended Therapy / New Game+ unlock flag (see game.sh's Extended
	// Therapy block for why this rides on gCaseFiles/TRSCASE.DAT as slot
	// CASEFILE_NGPLUS instead of its own file). Matches js/engine.js's
	// unlockNgPlus() -- called from rm002.sc's printSurvivalEnding() exactly
	// where the original calls it from checkGameEnd(). MarkCaseFile already
	// gives the right semantics here: sets the slot and persists
	// immediately, but only actually writes to disk the first time (a
	// harmless, strictly cheaper divergence from the original's
	// unconditional-every-survival localStorage write). gNgPlusUnlocked
	// (the in-memory mirror everything else reads) still gets set every
	// call regardless, matching the original's idempotent-either-way call
	// site.
	MarkCaseFile(CASEFILE_NGPLUS)
	= gNgPlusUnlocked TRUE
)
/******************************************************************************/
(procedure public (CaseFileTitle index)
	// Same 17 titles already used for the "COPING MECHANISM ACQUIRED"/
	// ending Print() messages in mechanisms.sc/rm002.sc -- duplicated
	// here rather than shared, there's no clean way to share a string
	// constant across scripts in this language, and each is short.
	(switch(index)
		(case 0 return("The Powder Keg"))
		(case 1 return("The Performer"))
		(case 2 return("Radically Undone"))
		(case 3 return("Raw Nerve"))
		(case 4 return("Coasting on Empty"))
		(case 5 return("Fragile Equilibrium"))
		(case 6 return("Actually Okay"))
		(case 7 return("The Long Fuse"))
		(case 8 return("Functional Enough"))
		(case 9 return("Panic Attack"))
		(case 10 return("Social Exile"))
		(case 11 return("Total Disassociation"))
		(case 12 return("The Approval Loop"))
		(case 13 return("The Exit Strategy"))
		(case 14 return("Hair-Trigger"))
		(case 15 return("The Void"))
		(case 16 return("Earned Security"))
	)
	return("")
)
/******************************************************************************/
(procedure public (ShowCaseFiles)
	// Scope-cut viewer for the persistent cross-run record (see the file
	// header) -- reachable any time via the "Case Files" menu item
	// (menubar.sc), including from the ending room, since nothing hides
	// the menu bar there. A DSelector (the stock scrollable list control
	// Save/Restore already uses) rather than a plain PrintChoices-style
	// dialog, specifically to avoid the dialog-height problems that hit
	// this project twice already for tall multi-item dialogs -- a fixed-
	// size scrolling viewport doesn't have that failure mode regardless
	// of how many entries there are.
	//
	// v1 is browse-only: no drill-down into a selected entry's full
	// description. That'd mean duplicating all 17 flavor-text strings a
	// second time (once here, once in mechanisms.sc/rm002.sc) purely for
	// this screen, which isn't worth the extra resident heap for a
	// browse-only reference screen -- can revisit if it's actually wanted.
	(var hDialog, hSelector, hDText, buf[544], i, curY)
	// 544 = VIEWABLE_CASEFILE_COUNT (17) * 32-byte stride; DSelector's `x`
	// property
	// is simultaneously the memory stride between entries AND the
	// assumed max display width in characters, so it must match here.
	// DSelector has no concept of "17 entries, then stop" -- advance()
	// just keeps scrolling until a slot's first byte happens to be zero
	// (see its (while(amount and StrAt(cursor x)) ...) loop). A local
	// array here isn't zero-initialized (leftover stack garbage), so
	// without explicitly clearing it first, scrolling past the last real
	// entry reads that garbage as more rows -- confirmed in-game as
	// corrupted text and repeated "SSSS" rows below entry 17.
	(for (= i 0) (< i 544) (++i)
		= buf[i] 0
	)
	// VIEWABLE_CASEFILE_COUNT (17), not the full CASEFILE_COUNT (18) --
	// slot 17 is the Extended Therapy unlock flag, not a real case file,
	// and has no title in CaseFileTitle() below to show anyway.
	(for (= i 0) (< i VIEWABLE_CASEFILE_COUNT) (++i)
		(if(GetCaseFile(i))
			Format((+ @buf (* i 32)) "%d. %s" (+ i 1) CaseFileTitle(i))
		)(else
			Format((+ @buf (* i 32)) "%d. ??? (sealed)" (+ i 1))
		)
	)

	= hDialog (Dialog:new())
	(send hDialog:
		window(gTheWindow)
		name("CaseFilesD")
		text("Case Files")
	)
	= hDText (DText:new())
	(send hDText:
		text("Every ending and coping mechanism you've ever discovered, across every run.")
		font(gDefaultFont)
		moveTo(4 4)
		setSize(290)
	)
	(send hDialog:add(hDText))
	= curY (+ (send hDText:nsBottom) 6)

	= hSelector (DSelector:new())
	(send hSelector:
		text(@buf)
		x(32)
		y(10)
		font(SMALL_FONT)
		// state bit 1 (TRUE) makes this the dialog's initially-focused
		// control so arrow keys/Page Up/Down reach it -- NOT bit 2, which
		// is what actually caused the reported bug (see below).
		state(1)
		moveTo(4 curY)
		setSize()
	)
	(send hDialog:add(hSelector))

	(send hDialog:
		setSize()
		center()
	)
	(if(< (send hDialog:nsTop) 2)
		// Same defensive clamp as PrintChoices -- see SESSION_HANDOFF.md.
		(send hDialog:moveTo( (send hDialog:nsLeft) 2 ))
	)
	(send hDialog:open(nwTITLE -1))
	(send hDialog:doit(NULL))
	(send hDialog:dispose())
)
/******************************************************************************/
