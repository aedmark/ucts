/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileCategory.sc
 ShowCaseFileCategory(): the per-category scrolling Case Files list (see
 CaseFiles.sc's ShowCaseFiles(), the category menu that leads here).
 Split into its own Load/Dispose-scoped script, separate from
 CaseFiles.sc itself, so browsing a category never also carries
 LoadCaseFiles/SaveCaseFiles/MarkCaseFile/UnlockNgPlus/the category menu
 resident at the same time.

 A "View" click also does as few Load/DisposeScript cycles as possible
 -- confirmed real, not theoretical: this dialect's Load/Dispose cycling
 doesn't reliably reclaim memory (the original heap-fragmentation saga),
 so "Out of heap space" kept recurring here even after each individual
 load got smaller. Both the discovered-flag check and the title text are
 now read from data already computed in the initial list-building loop
 instead of separate CaseFileAccess/CaseFileTitles reloads -- a "View"
 click now does exactly ONE Load/Dispose cycle (whichever description
 script matches this category), not three.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILECATEGORY_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefileaccess")
(use "casefiletitles")
(use "casefiledescriptionssurvival")
(use "casefiledescriptionsfailure")
(use "casefiledescriptionsmechanisms")
/******************************************************************************/
// Script-level local, not per-call -- see CaseFiles.sc's own buf for why.
(local
	buf[3424]
)
/******************************************************************************/
(procedure public (ShowCaseFileCategory baseIndex count catTitle)
	// The scrolling list for one category -- DSelector, windowed to
	// [baseIndex, baseIndex+count), chosen to avoid the dialog-height
	// overflow that a stacked-button layout hits on long lists. "View"
	// shows the highlighted entry's title+description if discovered, or
	// a sealed placeholder.
	(var hDialog, hSelector, hDText, hViewBtn, hCloseBtn, i, curY, hResult,
		localIndex, flatIndex, discovered, descBuf[180], titleBuf[48],
		discoveredFlags[72])
	// buf only needs the largest category's worth (Survival, 72*32=2304
	// of its declared 3424 bytes). discoveredFlags[72] is a per-call local
	// (well under the ~1KB known-safe size) -- caches each entry's
	// GetCaseFile() result from this same loop so the View handler below
	// never needs a second CaseFileAccess Load/Dispose cycle to re-ask it.
	// Load/DisposeScript cycling doesn't reliably reclaim memory in this
	// dialect (the original heap-fragmentation saga), so cutting a whole
	// cycle out of the "View" hot path is worth more than it looks.
	(for (= i 0) (< i (* count 32)) (++i)
		= buf[i] 0
	)
	Load(rsSCRIPT CASEFILETITLES_SCRIPT)
	Load(rsSCRIPT CASEFILEACCESS_SCRIPT)
	(for (= i 0) (< i count) (++i)
		= discoveredFlags[i] GetCaseFile(+ baseIndex i)
		(if(discoveredFlags[i])
			Format((+ @buf (* i 32)) "%d. %s" (+ i 1) CaseFileTitle(+ baseIndex i))
		)(else
			Format((+ @buf (* i 32)) "%d. ??? (sealed)" (+ i 1))
		)
	)
	DisposeScript(CASEFILEACCESS_SCRIPT)
	DisposeScript(CASEFILETITLES_SCRIPT)

	= hDialog (Dialog:new())
	(send hDialog:
		window(gTheWindow)
		name("CaseFilesD")
		text(catTitle)
	)
	= hDText (DText:new())
	(send hDText:
		text("Select an entry, then View to read it.")
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
		y(8)		/* 8, not the old flat list's 10 -- leaves room for the View/Close row below */
		font(SMALL_FONT)
		// state(1), not state(2) -- bit 1 just makes this the dialog's
		// initially-focused control; bit 2 makes ANY claimed event
		// (including a scroll) close the whole modal dialog, which is
		// wrong for a browse-only list.
		state(1)
		moveTo(4 curY)
		setSize()
	)
	(send hDialog:add(hSelector))
	= curY (+ (send hSelector:nsBottom) 4)

	= hViewBtn (DButton:new())
	(send hViewBtn:
		text("View")
		value(1)
		font(SMALL_FONT)
		setSize()
		moveTo(4 curY)
	)
	(send hDialog:add(hViewBtn))

	= hCloseBtn (DButton:new())
	(send hCloseBtn:
		text("Close")
		value(2)
		font(SMALL_FONT)
		setSize()
		moveTo( (+ (send hViewBtn:nsRight) 6) curY)
	)
	(send hDialog:add(hCloseBtn))

	(send hDialog:
		setSize()
		center()
	)
	(if(< (send hDialog:nsTop) 2)
		(send hDialog:moveTo( (send hDialog:nsLeft) 2 ))
	)
	(send hDialog:open(nwTITLE -1))

	// One Dialog:open() for the whole loop -- doit(hSelector) is called
	// again per View click so the selector keeps its scroll position.
	// Escape/Close both fall out identically (doit() returns 0 on Escape;
	// hCloseBtn's pointer never equals hViewBtn).
	(while(1)
		= hResult (send hDialog:doit(hSelector))
		(if(<> hResult hViewBtn)
			break
		)
		// Selected row's buffer offset IS its index in this category --
		// same 32-byte stride the list was built with.
		= localIndex (/ (- (send hSelector:cursor) @buf) 32)
		= flatIndex (+ baseIndex localIndex)
		= discovered discoveredFlags[localIndex]

		(if(discovered)
			// Load one description script at a time and copy its string
			// out immediately, rather than holding titles+descriptions
			// both resident through the whole Print() call. Which script
			// to load is a flat, mutually-exclusive check keyed off
			// baseIndex (always exactly one of the three category bases).
			(if(== baseIndex CASEFILE_SURVIVAL_BASE)
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL_SCRIPT)
				StrCpy(@descBuf CaseFileDescriptionSurvival(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL_SCRIPT)
			)
			(if(== baseIndex CASEFILE_FAILURE_BASE)
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_FAILURE_SCRIPT)
				StrCpy(@descBuf CaseFileDescriptionFailure(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_FAILURE_SCRIPT)
			)
			(if(== baseIndex CASEFILE_MECH_BASE)
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_MECHANISMS_SCRIPT)
				StrCpy(@descBuf CaseFileDescriptionMechanisms(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_MECHANISMS_SCRIPT)
			)
			// Title text is already sitting in buf's row ("N. Title", from
			// the list-building loop above) -- no need for a second
			// CaseFileTitles Load/Dispose cycle to re-fetch it. Scan past
			// the "N. " prefix (find the literal '.', then skip it and the
			// following space) rather than assuming a fixed digit count.
			= i 0
			(while(<> buf[(+ (* localIndex 32) i)] 46)
				++i
			)
			StrCpy(@titleBuf (+ (+ @buf (* localIndex 32)) (+ i 2)))
			Print(@descBuf #title @titleBuf)
		)(else
			Print("Sealed. Not yet discovered." #title "???")
		)
	)
	(send hDialog:dispose())
)
/******************************************************************************/
