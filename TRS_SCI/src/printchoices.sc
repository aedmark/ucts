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
/******************************************************************************/
(procedure public (PrintChoices message titleText width glitchText params)
	(var hDialog, hDText, hButtons[6], buttonCnt, paramCnt, curY, btnPressed)
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
	= hDText (DText:new())
	(send hDText:
		text(message)
		moveTo(4 4)
		font(gDefaultFont)
		setSize(width)
	)
	(send hDialog:add(hDText))
	= curY (+ (send hDText:nsBottom) 6)
	= paramCnt 0
	(while(< paramCnt paramTotal)
		= hButtons[buttonCnt] (DButton:new())
		(send hButtons[buttonCnt]:
			text(params[paramCnt])
			value(params[+ paramCnt 1])
			font(SMALL_FONT)
			setSize()
		)
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
			setSize()
		)
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
