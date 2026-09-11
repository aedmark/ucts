/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 printchoices.sc
 Vertical multi-choice dialog, standing in for stock Print()'s horizontal
 button row (which overlaps/clips past 2-3 short choices on a 320px screen).
 Builds a Dialog with a DText prompt and one DButton per choice, stacked
 top-to-bottom by tracking each control's own height. Returns the value of
 whichever button was pressed. Extracted out of rm001.sc so any room/script
 can use it, not just the room it was first proven in.
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
	(var hDialog, hDText, hIcon, hButtons[6], buttonCnt, paramCnt, curY, btnPressed)
	= paramTotal (- paramTotal 4)
	= buttonCnt 0
	= hDialog (Dialog:new())
	(send hDialog:
		window(gTheWindow)
		name("PrintD")
	)
	(if(titleText)
		(send hDialog:text(titleText))
	)
	// Portrait, embedded directly in the dialog -- same DIcon-plus-DText
	// side-by-side layout the stock Print()'s own #icon option already
	// uses (Controls.sc), just built by hand here since PrintChoices is
	// its own custom dialog rather than going through Print() itself.
	// Room-background DrawPortraitMood() calls are barely ever visible in
	// practice (a PrintChoices dialog covers that spot almost every turn)
	// -- this is what actually shows the current mood during real play.
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
	// Buttons start below whichever of the icon/text block ends up lower
	// -- the icon is a fixed height, the text isn't, so either could be
	// the taller one depending on the event.
	= curY (send hDText:nsBottom)
	(if(> (send hIcon:nsBottom) curY)
		= curY (send hIcon:nsBottom)
	)
	= curY (+ curY 6)
	= paramCnt 0
	(while(< paramCnt paramTotal)
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
		= paramCnt (+ paramCnt 2)
	)
	(if(glitchText)
		// The glitch wildcard: an extra button offered ~15% of the time
		// (rolled by the caller before this call). GLITCH_CHOICE is a
		// sentinel value that can't collide with a real choice index.
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
	(send hDialog:
		setSize()
		center()
	)
	(if(< (send hDialog:nsTop) 2)
		// A tall dialog (long description + several wrapped choices) can
		// compute a negative nsTop when centered in the ~190px-high
		// available area -- confirmed to render as garbled/corrupted
		// screen content rather than clipping cleanly. Pin it to the top
		// margin instead of letting it go negative.
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
	return(btnPressed)
)
/******************************************************************************/
(procedure public (SizeButtonToWidth hButton maxWidth)
	// Same computation as the stock DButton:setSize() in Controls.sc
	// (the +2 padding, the round-up-to-16px width), except that stock
	// version calls TextSize(@rect text font) with NO width limit at
	// all -- button width there is whatever the longest already-embedded
	// line (from the generator's BUTTON_WRAP_LEN) happens to measure,
	// completely uncapped. Confirmed as a real bug via a screenshot: a
	// choice's natural width exceeded the screen, pushing the whole
	// dialog border out of bounds on the right, even though the
	// description text (which does respect a width cap) was fine. This
	// forces a real cap onto every button too -- BUTTON_MAX_WIDTH
	// (game.sh), not the description's own `width` parameter, since
	// buttons don't share horizontal space with the portrait icon the
	// way the description does and can safely run wider (the first,
	// more conservative version of this fix reused `width` directly and
	// left buttons wrapping well before they needed to -- reported back
	// as wasted space).
	(var rect[4])
	TextSize(@rect (send hButton:text) (send hButton:font) maxWidth)
	= rect[rtBOTTOM] (+ rect[rtBOTTOM] 2)
	= rect[rtRIGHT] (+ rect[rtRIGHT] 2)
	(send hButton:nsBottom( (+ (send hButton:nsTop) rect[rtBOTTOM]) ))
	= rect[rtRIGHT] (* (/ (+ rect[rtRIGHT] 15) 16) 16)
	(send hButton:nsRight( (+ rect[rtRIGHT] (send hButton:nsLeft)) ))
)
/******************************************************************************/
