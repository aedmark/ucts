/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 casefiles.sc
 Cross-run "Case Files" persistence (matches the original browser game's
 Case Files feature, README.md: "a persistent, cross-run record of every
 ending and coping mechanism you've ever actually seen"). Full ending-
 variant port (see SESSION_HANDOFF.md): tracks discovery per VARIANT now
 (all 9 survival pools x 8 variants + 3 failure pools x 10 variants = 102
 total), matching the original's actual "collect every ending" mechanic --
 not just per ending CONDITION like this port's earlier one-variant-per-
 pool scope cut did. Still a real external file, following the same
 QFG-style pattern documented in docs/SCI0-research-findings.md: plain
 ASCII, one value per line, FOpen/FGets/FPuts/FClose via the template's
 fileio.sc.

 gCF0..gCF107 (declared in Main.sc, CASEFILE_COUNT = 108 of them) hold the
 in-memory discovery flags for this session -- individual scalar globals,
 not an array (see SESSION_HANDOFF.md for why). GetCaseFile/SetCaseFile
 (casefileaccess.sc, split out separately -- see its own header) are the
 array-like accessors everything else (including MarkCaseFile below)
 actually uses; nothing outside casefileaccess.sc needs to know they're
 really 108 separate globals. LoadCaseFiles()/SaveCaseFiles() sync all 108 with
 TRSCASE.DAT (a bare filename -- resolves relative to the game's own
 directory, same as the stock SRDialog code's own file access). Slot 107
 (CASEFILE_NGPLUS) is the unrelated Extended Therapy unlock flag, not a
 real case file -- riding on this file/array purely because it's the one
 proven persistence mechanism in this codebase (see game.sh); ShowCaseFiles()'s
 viewer stays scoped to VIEWABLE_CASEFILE_COUNT (107) so it doesn't show
 up as a bogus 108th entry. See game.sh for the full index scheme
 (0-71 survival variants, 72-101 failure variants, 102-106 mechanisms,
 107 NG+) and tools/gen-endings.js for how the 102 ending-variant slots'
 content actually gets generated.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILES_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefileaccess")
(use "casefiletitles")
/******************************************************************************/
// Script-level local, not a per-call procedure local -- see
// ShowCaseFiles() below and SESSION_HANDOFF.md. Same declaration idiom
// User.sc's own inputStr[51] already uses for a script-local array (a
// (local ...) block right after the (use ...) list, not inside any one
// procedure/method).
(local
	buf[3424]
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
	// description. That'd mean duplicating all 107 flavor-text strings a
	// second time (once here, once in endingcontent1-3.sc/mechanisms.sc)
	// purely for this screen, which isn't worth the extra resident heap
	// for a browse-only reference screen -- can revisit if it's actually
	// wanted.
	(var hDialog, hSelector, hDText, i, curY)
	// buf is a SCRIPT-level local (declared once near the top of this
	// file), not a per-call procedure local here -- real bug, confirmed
	// the hard way: declaring 3424 bytes as this procedure's own (var
	// ...) compiled fine but crashed at runtime the instant this ran
	// ("you did something we didn't expect"), while the exact same size
	// as a script-level local (same idiom User.sc's own inputStr[51]
	// already uses) works. SCI0's per-call procedure-local space is
	// evidently far smaller than the general 64KB heap, a limit this
	// project hadn't hit before (544 bytes, the pre-ending-port size, was
	// apparently still safely within it).
	// 3424 = VIEWABLE_CASEFILE_COUNT (107) * 32-byte stride; DSelector's
	// `x` property is simultaneously the memory stride between entries AND
	// the assumed max display width in characters, so it must match here.
	// DSelector has no concept of "N entries, then stop" -- advance()
	// just keeps scrolling until a slot's first byte happens to be zero
	// (see its (while(amount and StrAt(cursor x)) ...) loop). A local
	// array here isn't zero-initialized (leftover stack garbage), so
	// without explicitly clearing it first, scrolling past the last real
	// entry reads that garbage as more rows -- confirmed in-game as
	// corrupted text and repeated "SSSS" rows below the last entry, back
	// when this had only 17 entries total.
	(for (= i 0) (< i 3424) (++i)
		= buf[i] 0
	)
	// VIEWABLE_CASEFILE_COUNT (107), not the full CASEFILE_COUNT (108) --
	// slot 107 is the Extended Therapy unlock flag, not a real case file,
	// and has no title in CaseFileTitle() below to show anyway.
	// CaseFileTitle() lives in casefiletitles.sc, explicitly Load()ed
	// here and DisposeScript()ed right after -- its 107-case switch is
	// only ever needed for this one screen, and staying permanently
	// resident the way casefileaccess.sc used to (before this fix)
	// contributed to a real heap-exhaustion bug -- see
	// SESSION_HANDOFF.md.
	Load(rsSCRIPT CASEFILETITLES_SCRIPT)
	(for (= i 0) (< i VIEWABLE_CASEFILE_COUNT) (++i)
		(if(GetCaseFile(i))
			Format((+ @buf (* i 32)) "%d. %s" (+ i 1) CaseFileTitle(i))
		)(else
			Format((+ @buf (* i 32)) "%d. ??? (sealed)" (+ i 1))
		)
	)
	DisposeScript(CASEFILETITLES_SCRIPT)

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
