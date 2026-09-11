/******************************************************************************
 SCI Template Game
 By Brian Provinciano
 ******************************************************************************
 main.sc
 Contains the game's main instance and inventory items.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script MAIN_SCRIPT)
/******************************************************************************/
(use "controls")
(use "sound")
(use "syswindow")
(use "cycle")
(use "game")
(use "inv")
(use "user")
(use "menubar")
(use "feature")
(use "obj")
(use "disposeload")
(use "casefiles")
(use "casefileaccess")
/******************************************************************************
 * These are the global variables. You can access them from any script as long
 * as it "use"es this script
 ******************************************************************************/
(local
	gEgo					/* points to the ego's class */
	gGame					/* points to the game instance */
	gRoom					/* points to the current room instance */
	gSpeed 					/* the game speed (delay each interpreter cycle) */
	gQuitGame = FALSE		/* if set to TRUE, the game will exit */
	
	gCast					/* points to the cast class (list of actors) */
	gRegions				/* points to the regions class (list of regions) */
	gLocales 				/* points to the locales class (list of locales) */
	gTimers					/* points to the timers class (list of timers) */
	gSounds					/* points to the sounds class (list of sounds) */
	gInv					/* points to the inventory class */
	gAddToPics				/* points to the add to pics class */
	gFeatures				/* points to the add to features class */		
	gSFeatures				/* points to the add to sfeatures class */
	
	gRoomNumberExit			/* room number exit */
	gPreviousRoomNumber		/* the number of the previous room */
	gRoomNumber				/* the number of the current room */ 
	gDebugOnExit = FALSE	/* enter debug mode on room exit */ 
	
	gScore = 0				/* the game score */
	gMaxScore = 0			/* the maximum game score */
	gOldScore				/* previous score */

	gRepression = STARTING_REPRESSION	/* T.R.S. stat: repression, loses at 100 */
	gMask = STARTING_MASK				/* T.R.S. stat: social mask, loses at 0 */
	gChild = STARTING_CHILD			/* T.R.S. stat: inner child, loses at 0 */
	gTurn = 1				/* T.R.S.: current turn number, 1-based */
	gMaxTurns = DEFAULT_MAX_TURNS	/* T.R.S.: survive this many turns to win -- set per-run in rm001.sc's init() (DEFAULT_MAX_TURNS or HARD_MODE_TURNS depending on gHardMode) */
	gHardMode = FALSE		/* T.R.S. Extended Therapy: TRUE for the rest of this run once chosen at rm001.sc's init() -- never persisted itself, see gNgPlusUnlocked below */
	gNgPlusUnlocked = FALSE	/* T.R.S.: permanently TRUE once any standard-session run has ever survived. An in-memory mirror of gCF17/CASEFILE_NGPLUS (see gCF0..17 below) -- synced from it right after LoadCaseFiles() in Template:init(), and set directly by UnlockNgPlus() (casefiles.sc, alongside its own MarkCaseFile(CASEFILE_NGPLUS) call) purely so call sites can read a plain global instead of GetCaseFile(CASEFILE_NGPLUS) every time */

	/* T.R.S. coping-mechanism tracking: count of same-tag choices made so
	   far this run, and whether that mechanism has permanently unlocked
	   (count reaching UNLOCK_THRESHOLD flips it once and for all -- see
	   ApplyChoiceEffects in mechanisms.sc) */
	gFawnCount = 0
	gFawnUnlocked = FALSE
	gFlightCount = 0
	gFlightUnlocked = FALSE
	gFightCount = 0
	gFightUnlocked = FALSE
	gFreezeCount = 0
	gFreezeUnlocked = FALSE
	gSecureCount = 0
	gSecureUnlocked = FALSE

	/* T.R.S. Case Files: cross-run "have I ever seen this" record (every
	   ending VARIANT, mechanisms, plus the unrelated Extended Therapy
	   unlock flag riding along in slot 107 -- see game.sh's full index
	   scheme). Persisted to disk -- see casefiles.sc. Each defaults to 0
	   (never seen); LoadCaseFiles() (called once from Template:init())
	   overwrites these from disk if a save file already exists.
	   Individual scalars, not an array -- an earlier gCaseFiles[N] array
	   global compiled but wasn't visible from other scripts via
	   (use "main") the way scalar globals are (no working precedent for
	   that anywhere in this codebase; see SESSION_HANDOFF.md). 108 of them
	   now (was 18) after the full ending-variant port -- see game.sh for
	   the index ranges, tools/gen-endings.js for how 0-101 get generated. */
	gCF0 = 0
	gCF1 = 0
	gCF2 = 0
	gCF3 = 0
	gCF4 = 0
	gCF5 = 0
	gCF6 = 0
	gCF7 = 0
	gCF8 = 0
	gCF9 = 0
	gCF10 = 0
	gCF11 = 0
	gCF12 = 0
	gCF13 = 0
	gCF14 = 0
	gCF15 = 0
	gCF16 = 0
	gCF17 = 0
	gCF18 = 0
	gCF19 = 0
	gCF20 = 0
	gCF21 = 0
	gCF22 = 0
	gCF23 = 0
	gCF24 = 0
	gCF25 = 0
	gCF26 = 0
	gCF27 = 0
	gCF28 = 0
	gCF29 = 0
	gCF30 = 0
	gCF31 = 0
	gCF32 = 0
	gCF33 = 0
	gCF34 = 0
	gCF35 = 0
	gCF36 = 0
	gCF37 = 0
	gCF38 = 0
	gCF39 = 0
	gCF40 = 0
	gCF41 = 0
	gCF42 = 0
	gCF43 = 0
	gCF44 = 0
	gCF45 = 0
	gCF46 = 0
	gCF47 = 0
	gCF48 = 0
	gCF49 = 0
	gCF50 = 0
	gCF51 = 0
	gCF52 = 0
	gCF53 = 0
	gCF54 = 0
	gCF55 = 0
	gCF56 = 0
	gCF57 = 0
	gCF58 = 0
	gCF59 = 0
	gCF60 = 0
	gCF61 = 0
	gCF62 = 0
	gCF63 = 0
	gCF64 = 0
	gCF65 = 0
	gCF66 = 0
	gCF67 = 0
	gCF68 = 0
	gCF69 = 0
	gCF70 = 0
	gCF71 = 0
	gCF72 = 0
	gCF73 = 0
	gCF74 = 0
	gCF75 = 0
	gCF76 = 0
	gCF77 = 0
	gCF78 = 0
	gCF79 = 0
	gCF80 = 0
	gCF81 = 0
	gCF82 = 0
	gCF83 = 0
	gCF84 = 0
	gCF85 = 0
	gCF86 = 0
	gCF87 = 0
	gCF88 = 0
	gCF89 = 0
	gCF90 = 0
	gCF91 = 0
	gCF92 = 0
	gCF93 = 0
	gCF94 = 0
	gCF95 = 0
	gCF96 = 0
	gCF97 = 0
	gCF98 = 0
	gCF99 = 0
	gCF100 = 0
	gCF101 = 0
	gCF102 = 0
	gCF103 = 0
	gCF104 = 0
	gCF105 = 0
	gCF106 = 0
	gCF107 = 0

	gCurrentCursor			/* the number of the current cursor */
	gNormalCursor = 999		/* the number of the normal cursor (ie. arrow) */
	gLoadingCursor = 997	/* the number of the loading cursor (ie. hand) */
	
	gDefaultFont = 1		/* the number of the default font */
	gSaveRestoreFont = 0	/* the number of the font for the save/restore dialogs*/
	gDeadFont = 0			/* the number of the font for the dialog when ego dies */
	
	gUserEvent				/* points to the user's event object */
	gPrintDlg				/* points to the current Print() window */
	gVolume					/* the sound volume */
	gVersion				/* the version string */
	gSaveDirPtr				/* points to the save directory string */
	gCheckAniWait			/* the checkAni delay */
	gSetRegions				/* a flag -- see User:doit() */
	
	gPicAngle				/* the room's pic angle */
	gOverlay = -1			/* whether to overlay the picture when drawing */
	gDefaultPicAni			/* the default pic animation */
	gDefaultPalette			/* the default palette to use for the pictures (0-3) */
	gCastMotionCue			/* if set, the cast's motionCue() is called */
	
	gTheWindow				/* points to the window class */
	gWndColor				/* the colour of the game's windows foreground (ie. text) */
	gWndBack				/* the colour of the game's windows background */

	gOldPort				/* the previous port */
	
	gEgoView				/* ego's current view number */
	
	/* hh:mm:ss | gTimeHours:gTimeMinutes:gTimeSeconds */
	/* the time elapsed since the game started */
	gTimeSeconds			/* the seconds */
	gTimeMinutes			/* the minutes */
	gTimeHours				/* the hours */
	
	gCurrentTime				/* the current time in seconds */
	
	gTheMusic				/* points to the music class */
	gTheSoundFX				/* points to the sound fx class */
	gProgramControl 		/* states whether the program has control or the user */
)
/******************************************************************************/
(instance public Template of Game
	(properties)
	(method (init)
        // Set up the base window
		= gTheWindow theWindow
		= gWndColor clBLACK
		= gWndBack clWHITE
		(send gTheWindow:
			color(gWndColor)
		    back(gWndBack)
		)

        // Initialize
		(super:init())

        /******************************
         * Set your game version here *
         ******************************/
		= gVersion "1.0"

		// Cross-run Case Files record (endings/mechanisms ever seen, plus
		// the Extended Therapy unlock flag in slot CASEFILE_NGPLUS -- see
		// game.sh) -- load once at boot; defaults to all-zero (gCaseFiles'
		// declared initial state) if no save file exists yet. Load/
		// DisposeScript wrapped -- CaseFiles.sc is ~12KB and was
		// permanently resident from this very first call onward (nothing
		// ever disposed it), a sizeable chunk of permanent baseline heap
		// given how thin margins already are -- see SESSION_HANDOFF.md.
		// Same idiom as CASEFILEACCESS_SCRIPT right below.
		Load(rsSCRIPT CASEFILES_SCRIPT)
		LoadCaseFiles()
		DisposeScript(CASEFILES_SCRIPT)
		// Mirror gCF17 into the plain gNgPlusUnlocked global every call
		// site outside this file actually reads -- see its declaration
		// above for why. Load/DisposeScript wrapped -- this single call
		// was the one thing making the whole of casefileaccess.sc
		// permanently resident for the entire game session (scripts
		// auto-load on call but never auto-unload); a real, confirmed
		// contributor to a heap-exhaustion bug during ordinary per-turn
		// play, unrelated to anything about Case Files itself -- see
		// SESSION_HANDOFF.md.
		Load(rsSCRIPT CASEFILEACCESS_SCRIPT)
		= gNgPlusUnlocked GetCaseFile(CASEFILE_NGPLUS)
		DisposeScript(CASEFILEACCESS_SCRIPT)

		// General initialization stuff
	    = gVolume 15
	    DoSound(sndVOLUME gVolume)
		    
	    (SL:code(statusCode))
	    (TheMenuBar:init())
    
	    (scoreSound:
	    	owner(self)
	    	init()
	    )
	    = gTheMusic theMusic
	    (send gTheMusic:
	    	owner(self)
	    	init()
	    )
		= gTheSoundFX theSoundFX
	    (send gTheSoundFX:
	    	owner(self)
	    	init()
	    )
	    
	    = gEgo ego
	    (User:
	    	alterEgo(gEgo)
	    	blocks(0)
	    	y(150)
	    )
			
		Load(rsFONT		gDeadFont)
		Load(rsFONT		gDefaultFont)
		Load(rsFONT		gSaveRestoreFont)
		Load(rsFONT		SMALL_FONT)
	
		Load(rsCURSOR	gNormalCursor)
		Load(rsCURSOR	gLoadingCursor)

		// Player portrait (view 801) -- see game.sh and
		// printchoices.sc's GetPortraitMood()/DIcon usage.
		Load(rsVIEW		PORTRAIT_VIEW)

	    (if(HaveMouse())
	    	(send gGame:setCursor(gNormalCursor SET_CURSOR_VISIBLE))
		)(else
	    	(send gGame:setCursor(gNormalCursor SET_CURSOR_VISIBLE 304 174))
		)
			
        /*************************************************
         * Initialize the inventory with it's items here *
         *************************************************/
		(Inv:
			add(
				{Nothing}
				{Test Object}
			)
		)
			
		// Start the room
		(if(GameIsRestarting())
	    	(self:newRoom(INITROOMS_SCRIPT))
	    )(else
	    	(self:newRoom(TITLESCREEN_SCRIPT))
		)
	)
	(method (doit)
		(super:doit())
		(if(gProgramControl)
			(User:
				canControl(FALSE)
				canInput(FALSE)
			)
		)
		(if(<> gCurrentTime (= gCurrentTime GetTime(gtTIME_OF_DAY)))
		    (if(>= ++gTimeSeconds 60)
		    	= gTimeSeconds 0
		    	++ gTimeMinutes
		    	(if(>= gTimeMinutes 60)
		    		= gTimeMinutes 0
		    		++ gTimeHours
		    	)
			)
		)
  		(if(> gOldScore gScore)
		    	= gOldScore gScore
				(SL:doit())
		)
		(if(< gOldScore gScore)
		    = gOldScore gScore
			(SL:doit())
		)
	)
	(method (replay)
  		(TheMenuBar:draw())
  		(SL:enable())
  		
  		(if(DoSound(sndSET_SOUND))
		    SetMenu(MENU_TOGGLESOUND #text "Turn Off")
		)(else
		    SetMenu(MENU_TOGGLESOUND #text "Turn On")
		)
		(super:replay())
	)
	(method (newRoom roomNum picAni)
  			DisposePrintDlg()
  			Load(rsFONT		gDeadFont)
  			Load(rsFONT		gDefaultFont)
  			Load(rsFONT		gSaveRestoreFont)
  			Load(rsFONT		SMALL_FONT)

  			Load(rsCURSOR	gNormalCursor)
  			Load(rsCURSOR	gLoadingCursor)

  			// Player portrait (view 801) -- see game.sh and
  			// printchoices.sc's GetPortraitMood()/DIcon usage.
  			Load(rsVIEW		PORTRAIT_VIEW)

			(super:newRoom(roomNum))
			(if(< paramTotal 2)
				= gDefaultPicAni Random(0 5)
			)(else
				= gDefaultPicAni picAni
			)
	)
	(method (startRoom roomNum)
		DisposeLoad(
			NULL
			FILEIO_SCRIPT JUMP_SCRIPT EXTRA_SCRIPT WINDOW_SCRIPT
		        TIMER_SCRIPT FOLLOW_SCRIPT REV_SCRIPT DCICON_SCRIPT
		        DOOR_SCRIPT AUTODOOR_SCRIPT WANDER_SCRIPT AVOID_SCRIPT DPATH_SCRIPT
		)
		DisposeScript(DISPOSELOAD_SCRIPT)
		(if(gDebugOnExit)
			= gDebugOnExit FALSE
			SetDebug()
		)
		(send gTheSoundFX:
			stop()
			number(1)
		)
		(super:startRoom(roomNum))
		(if(== gTheSoundFX 11)
		 	(send gEgo:baseSetter(NormalBase))
		)
	)
	(method (changeScore addScore)
		= gScore + gScore addScore
		(if(> addScore 0)
			(scoreSound:playMaybe())
		)
	)
  	(method (handleEvent pEvent)

      	//////////////////////////////////////////////////////////////////////
		// ** This is debug functionality                                   //
		// ** Comment it out if you don't want people to cheat in your game //
            //////////////////////////////////////////////////////////////////////
	      (if (== evKEYBOARD (send pEvent:type))
	         // Check for ALT keys
	         (switch (send pEvent:message)
	            (case $2f00 // alt-v
	               Show(1) // Show visual
	            )
	            (case $2e00 // alt-c
	               Show(4) // Show control
	            )
	            (case $1900 // alt-p
	               Show(2) // Show priority
	            )
	            (case $3200 // alt-m
	               // Show memory usage
	                  ShowFree()
	                  FormatPrint(
	                      "Free Heap: %u Bytes\nLargest ptr: %u Bytes\nFreeHunk: %u KBytes\nLargest hunk: %u Bytes"
	                      MemoryInfo(miFREEHEAP)
	                      MemoryInfo(miLARGESTPTR)
	                      (>> MemoryInfo(miFREEHUNK) 6)
	                      MemoryInfo(miLARGESTHUNK)
	                  )
	            )
	            (case $1400 // alt-t
	               // teleport to room
	                    (send gRoom:newRoom(GetNumber("Which Room Number?")))
	            )
	            (case $1700 // alt-i
	               // get inventory
	               (send gEgo:get(GetNumber("Which inventory#?")))
	            )
	            (case $1f00 // alt-s
	               // Show cast
	               (send gCast:eachElementDo(#showSelf))
	            )
	         )
	      )
	      ////////////////////////////////////////////////////
	      // End of debug functionality                     //
	      ////////////////////////////////////////////////////

		(super:handleEvent(pEvent))
		(if((<> (send pEvent:type) evSAID) or (send pEvent:claimed))
		    return(TRUE)
		)

        /***********************************/
        /* Add global said statements here */
        /***********************************/
        
        (if(Said('hi'))
            Print("Well hello to you too!")
        )
  
  
		return(FALSE)
  	)
)
/******************************************************************************/
(class Iitem of InvI
	(properties
		said 0
		description 0
		owner 0
		view 0
		loop 0
		cel 0
		script 0
	)
	(method (showSelf)
		Print(
            description
			#title objectName
			#icon view loop cel
		)
	)
)
/******************************************************************************/
(instance statusCode  of Code
	(properties)
 	(method (doit param1)
  		Format(param1 " T.R.S.     Repression: %d  Mask: %d  Child: %d " gRepression gMask gChild)
 	)
)
/******************************************************************************/
(instance ego of Ego
	(properties
		y 1111
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $2000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		edgeHit 0
	)
)
/******************************************************************************/
(instance scoreSound of Sound
	(properties
		state 0
		number SCORE_SOUND
		priority 10
		loop 1
		handle 0
		signal 0
		prevSignal 0
		client 0
		owner 0
	)
)
/******************************************************************************/
(instance theMusic  of Sound
	(properties
		state 0
		number 1
		priority 0
		loop 1
		handle 0
		signal 0
		prevSignal 0
		client 0
		owner 0
	)
)
/******************************************************************************/
(instance theSoundFX  of Sound
	(properties
		state 0
		number 1
		priority 5
		loop 1
		handle 0
		signal 0
		prevSignal 0
		client 0
		owner 0
	)
)
/******************************************************************************/
(instance theWindow  of SysWindow
	(properties)
	(method (open)
		(if(< Graph(grGET_COLOURS) 9)
			(if( (< color 7) or (== color 8) )
				= color 0
				= back  15
			)(else
				= color 15
				= back  0
			)
  		)
  		(super:open())
	)
)
/******************************************************************************/
(instance NormalBase of Code
	(properties)
	(method (doit)
		(var temp0)
		(if(== gRoomNumberExit 253)
		    = temp0 22
		)(else
  			= temp0 10
		)
		(send gEgo:
			brBottom(
				(+ (send gEgo:y) 1)
			)
		)
		(send gEgo:
			brTop(
				(- (send gEgo:brBottom) (send gEgo:yStep))
			)
		)
		(send gEgo:
			brLeft(
				(- (send gEgo:x) temp0)
			)
		)
		(send gEgo:
			brRight(
				(+ (send gEgo:x) temp0)
			)
		)
	)
)

/******************************************************************************
 * THE PUBLIC PROCEDURES
 ******************************************************************************/
(procedure public (SetUpEgo theLoop theView)
	PlayerControl()
	(send gEgo:edgeHit(EDGE_NONE))
	(switch(paramTotal)
		(case 0
			SetUpActor(gEgo (send gEgo:loop) gEgoView)
		)
  		(case 1
			SetUpActor(gEgo theLoop gEgoView)
		)
  		(case 2
		    SetUpActor(gEgo theLoop theView)
  		)
	)
)
/******************************************************************************/
(procedure public (SetUpActor pActor theLoop theView)
	(if(> paramTotal 1)
		(send pActor:loop(theLoop))
	)
	(if(> paramTotal 2)
		(send pActor:view(theView))
	)
	(send pActor:
		setLoop(-1)
		setPri(-1)
		setStep(3 2)
		setCycle(Walk)
		illegalBits($8000)
		cycleSpeed(0)
		moveSpeed(0)
		ignoreActors(0)
	)
)
/******************************************************************************/
(procedure public (ProgramControl)
	(User:
		canControl(FALSE)
		canInput(FALSE)
	)
	(send gEgo:setMotion(NULL))
)
/******************************************************************************/
(procedure public (PlayerControl)
	(User:
		canControl(TRUE)
		canInput(TRUE)
	)
	(send gEgo:setMotion(NULL))
)
/******************************************************************************/
(procedure public (DisposePrintDlg)
	(if(gPrintDlg)
		(send gPrintDlg:dispose())
	)
)
/******************************************************************************/
(procedure public (ClampStats)
	(if(< gRepression 0)
		= gRepression 0
	)
	(if(> gRepression 100)
		= gRepression 100
	)
	(if(< gMask 0)
		= gMask 0
	)
	(if(> gMask 100)
		= gMask 100
	)
	(if(< gChild 0)
		= gChild 0
	)
	(if(> gChild 100)
		= gChild 100
	)
	(SL:doit())
)
/******************************************************************************/
(procedure public (PrintOK)
	Print("O.K.")
)
/******************************************************************************/
(procedure public (PrintItIs)
	Print("It is.")
)
/******************************************************************************/
(procedure public (PrintYouAre)
	Print("You are.")
)
/******************************************************************************/
(procedure public (PrintGoodIdea)
	Print("Good idea. You might try that again later.")
)
/******************************************************************************/
(procedure public (PrintNotCloseEnough)
	Print("You're not close enough.")
)
/******************************************************************************/
(procedure public (PrintAlreadyTookIt)
	Print("You already took it.")
)
/******************************************************************************/
(procedure public (PrintDontHaveIt)
	Print("You don't have it.")
)
/******************************************************************************/
(procedure public (PrintCantDoThat mem)
	(if(> MemoryInfo(miLARGESTPTR) mem)
		return(TRUE)
	)(else
		Print("You can't do that here; at least, not now.")
		return(FALSE)
	)
)
/******************************************************************************/
(procedure public (AddViewToPic pView)
	(var hView)
	(if(pView)
		= hView (View:new())
		(send hView:
		  	view(send pView:view)
			loop(send pView:loop)
			cel(send pView:cel)
			priority(send pView:priority)
			posn( (send pView:x) (send pView:y) )
			addToPic()
		)
		(send pView:
			posn( (send pView:x) (+ 1000 (send pView:y)) )
		)
	)
)
/******************************************************************************/
(procedure public (SetInvIOwner index owner)
	(var hInvI)
	= hInvI (send gInv:at(index))
	(if(< paramTotal 2)
		(send hInvI:owner(gRoomNumberExit))
	)(else
		(send hInvI:owner(owner))
	)
)


/******************************************************************************
 * THE INVENTORY ITEMS                                                        *
 ******************************************************************************/
(instance Nothing  of Iitem
	(properties)
)
/******************************************************************************/
(instance {Test Object} of Iitem
	(properties
		said 'object'
		description
             "This is a test object in your inventory."
		owner 0
		view 800
		loop 0
		cel 0
		script 0
	)
)
/******************************************************************************/

