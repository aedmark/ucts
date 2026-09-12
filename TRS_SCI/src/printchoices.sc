/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 printchoices.sc
 Vertical multi-choice dialog, standing in for stock Print()'s horizontal
 button row (which overlaps/clips past 2-3 short choices on a 320px
 screen). Builds a Dialog with a DText prompt and one DButton per choice,
 stacked top-to-bottom by tracking each control's own height. Returns the
 value of whichever button was pressed.

 Paginated at CHOICES_PER_PAGE (game.sh) choices per screen, since events
 have 3-5 real choices: "More options..." (MORE_CHOICES) leads forward
 on every page but the last, "Back" (BACK_CHOICES) leads back on every
 page but the first, and the glitch button only ever shows on the last
 page. Caps every page at the same button count (3 choices + one
 nav/glitch button) already proven to fit within the dialog-height
 budget, regardless of how many total choices an event has.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PRINTCHOICES_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "mechanisms")
/******************************************************************************/
(procedure public (PrintChoices message titleText width glitchText params)
	(var hDialog, hDText, hIcon, hButtons[6], buttonCnt, paramCnt, curY,
		btnPressed, totalChoices, pageStart, pageCount, isLastPage, i,
		isFirstPage)
	= paramTotal (- paramTotal 4)
	= totalChoices (/ paramTotal 2)
	= pageStart 0
	(while(1)
		= buttonCnt 0
		= hDialog (Dialog:new())
		(send hDialog:
			window(gTheWindow)
			name("PrintD")
		)
		(if(titleText)
			(send hDialog:text(titleText))
		)
		// Portrait -- same DIcon+DText side-by-side layout stock Print()'s
		// #icon option uses, built by hand since this dialog doesn't go
		// through Print() itself. Rebuilt every page -- cheap, and keeps
		// every page's layout identical regardless of which page it is.
		= hIcon (DIcon:new())
		(send hIcon:
			view(PORTRAIT_VIEW)
			loop(GetPortraitMood())
			cel(0)
			setSize()
			moveTo(4 4)
		)
		(send hDialog:add(hIcon))
		= hDText (DText:new())
		(send hDText:
			text(message)
			moveTo( (+ 4 (send hIcon:nsRight)) 4 )
			font(gDefaultFont)
			setSize( (- width (+ (send hIcon:nsRight) 4)) )
		)
		(send hDialog:add(hDText))
		// Buttons start below whichever of icon/text ends up lower.
		= curY (send hDText:nsBottom)
		(if(> (send hIcon:nsBottom) curY)
			= curY (send hIcon:nsBottom)
		)
		= curY (+ curY 6)

		// Not the first page: a "Back" button, ahead of this page's real
		// choices so their position stays consistent whether or not Back
		// is present.
		= isFirstPage (== pageStart 0)
		(if(not isFirstPage)
			= hButtons[buttonCnt] (DButton:new())
			(send hButtons[buttonCnt]:
				text("Back")
				value(BACK_CHOICES)
				font(SMALL_FONT)
			)
			SizeButtonToWidth(hButtons[buttonCnt] BUTTON_MAX_WIDTH)
			(send hButtons[buttonCnt]:moveTo(4 curY))
			= curY (+ (send hButtons[buttonCnt]:nsBottom) 3)
			(send hDialog:add(hButtons[buttonCnt]))
			++buttonCnt
		)

		// This page's slice of the choice pairs -- at most
		// CHOICES_PER_PAGE, same button-count ceiling every page already
		// proven to fit regardless of how many total choices exist.
		= pageCount (- totalChoices pageStart)
		(if(> pageCount CHOICES_PER_PAGE)
			= pageCount CHOICES_PER_PAGE
		)
		= isLastPage (== (+ pageStart pageCount) totalChoices)

		(for (= i 0) (< i pageCount) (++i)
			= paramCnt (* (+ pageStart i) 2)
			= hButtons[buttonCnt] (DButton:new())
			(send hButtons[buttonCnt]:
				text(params[paramCnt])
				value(params[+ paramCnt 1])
				font(SMALL_FONT)
			)
			SizeButtonToWidth(hButtons[buttonCnt] BUTTON_MAX_WIDTH)
			(send hButtons[buttonCnt]:moveTo(4 curY))
			= curY (+ (send hButtons[buttonCnt]:nsBottom) 3)
			(send hDialog:add(hButtons[buttonCnt]))
			++buttonCnt
		)

		// Not the last page: a "More options..." button instead of the
		// glitch button -- the glitch only ever shows on the final page,
		// same one-roll-per-turn as before.
		(if(not isLastPage)
			= hButtons[buttonCnt] (DButton:new())
			(send hButtons[buttonCnt]:
				text("More options...")
				value(MORE_CHOICES)
				font(SMALL_FONT)
			)
			SizeButtonToWidth(hButtons[buttonCnt] BUTTON_MAX_WIDTH)
			(send hButtons[buttonCnt]:moveTo(4 curY))
			= curY (+ (send hButtons[buttonCnt]:nsBottom) 3)
			(send hDialog:add(hButtons[buttonCnt]))
			++buttonCnt
		)(else
			(if(glitchText)
				// The glitch wildcard, offered ~15% of the time by the
				// caller. GLITCH_CHOICE is a sentinel that can't collide
				// with a real choice index or MORE_CHOICES.
				= hButtons[buttonCnt] (DButton:new())
				(send hButtons[buttonCnt]:
					text(glitchText)
					value(GLITCH_CHOICE)
					font(SMALL_FONT)
				)
				SizeButtonToWidth(hButtons[buttonCnt] BUTTON_MAX_WIDTH)
				(send hButtons[buttonCnt]:moveTo(4 curY))
				= curY (+ (send hButtons[buttonCnt]:nsBottom) 3)
				(send hDialog:add(hButtons[buttonCnt]))
				++buttonCnt
			)
		)

		(send hDialog:
			setSize()
			center()
		)
		(if(< (send hDialog:nsTop) 2)
			// A tall dialog can center to a negative nsTop, which renders as
			// garbled screen content rather than clipping cleanly -- pin to
			// the top margin instead.
			(send hDialog:moveTo( (send hDialog:nsLeft) 2 ))
		)
		(send hDialog:open(nwTITLE -1))
		= btnPressed (send hDialog:doit(NULL))
		(if(== btnPressed -1)
			= btnPressed 0
		)
		(for (= paramCnt 0) (< paramCnt buttonCnt) (++paramCnt)
			(if(== btnPressed hButtons[paramCnt])
				= btnPressed (send btnPressed:value)
				break
			)
		)
		(send hDialog:dispose())

		// Flat sequence, not chained if/else (no precedent in this
		// codebase for 3+-branch chaining) -- MORE_CHOICES and
		// BACK_CHOICES each adjust pageStart and loop again; anything
		// else (a real choice or GLITCH_CHOICE) returns immediately.
		(if(== btnPressed MORE_CHOICES)
			= pageStart (+ pageStart CHOICES_PER_PAGE)
		)
		(if(== btnPressed BACK_CHOICES)
			= pageStart (- pageStart CHOICES_PER_PAGE)
		)
		(if((<> btnPressed MORE_CHOICES) and (<> btnPressed BACK_CHOICES))
			return(btnPressed)
		)
	)
)
/******************************************************************************/
(procedure public (SizeButtonToWidth hButton maxWidth)
	// Same computation as stock DButton:setSize() (+2 padding, round up
	// to 16px) but WITH a width cap passed to TextSize() -- the stock
	// version has none, so a long choice's natural width could push the
	// dialog border off-screen. BUTTON_MAX_WIDTH (game.sh), not the
	// description's own width, since buttons don't share space with the
	// portrait icon and can run wider.
	(var rect[4])
	TextSize(@rect (send hButton:text) (send hButton:font) maxWidth)
	= rect[rtBOTTOM] (+ rect[rtBOTTOM] 2)
	= rect[rtRIGHT] (+ rect[rtRIGHT] 2)
	(send hButton:nsBottom( (+ (send hButton:nsTop) rect[rtBOTTOM]) ))
	= rect[rtRIGHT] (* (/ (+ rect[rtRIGHT] 15) 16) 16)
	(send hButton:nsRight( (+ rect[rtRIGHT] (send hButton:nsLeft)) ))
)
/******************************************************************************/
