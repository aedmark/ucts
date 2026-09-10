(script 107)

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

 gCF0..gCF16 (declared in Main.sc, CASEFILE_COUNT = 17 of them) hold the
 in-memory discovery flags for this session -- individual scalar globals,
 not an array (see SESSION_HANDOFF.md for why). GetCaseFile/SetCaseFile
 below are the array-like accessors everything else in this file (and
 MarkCaseFile's callers in mechanisms.sc/rm002.sc) actually uses; nothing
 outside this file needs to know they're really 17 separate globals.
 LoadCaseFiles()/SaveCaseFiles() sync all 17 with TRSCASE.DAT (a bare
 filename -- resolves relative to the game's own directory, same as the
 stock SRDialog code's own file access).
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
	)
)
/******************************************************************************/
(procedure public (LoadCaseFiles)
	(var hFile, i, lineBuf[6])
	= hFile FOpen("TRSCASE.DAT" fOPENFAIL)
	(if(== hFile -1)
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
