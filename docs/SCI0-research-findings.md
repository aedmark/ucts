# **`Sierra SCI0 Engine`**

## *Systems & Data Structures*

---

*`The Sierra Creative Interpreter version 0 (SCI0), utilized between 1988 and 1990 across flagship titles including King's Quest IV, Space Quest III, Police Quest II, Leisure Suit Larry III, and Hero's Quest / Quest for Glory I, represents a 16-bit object-oriented virtual machine targeting the 320x200 16-color IBM Enhanced Graphics Adapter (EGA) standard1.`*

*`Modern developers utilizing tools such as SCI Companion 3 encounter substantial documentation gaps because primary technical repositories prioritize the later SCI1.1 point-and-click engine, obscuring the script patterns, memory architectures, and binary file structures native to SCI02.`*

*`This study provides a technical deconstruction of SCI0 interface construction, resource volume mapping, class libraries, file input/output routines, memory constraints, and audio hardware protocols.`*

---

# **`Multi-Option Dialogue Architectures and UI Construction`**

### 

`A common misconception regarding Sierra's early catalog asserts that Hero's Quest / Quest for Glory I (1989, SCI0) provided a graphical, scrollable topic list for player-NPC conversations1. Technical decompilation and script disassembly reveal that the original EGA release of Quest for Glory I possessed no graphical conversation menus4. Player-NPC interactions operated strictly through the command-line parser: players pressed the spacebar to open the parser input field and manually typed conversational queries such as ask about brigands, ask about curse, or talk centaur5.`

`The hierarchical, mouse-driven conversation menu often referenced by researchers was introduced exclusively in the July 1992 256-color VGA remake (Quest for Glory I: So You Want to Be a Hero), which was authored on the point-and-click SCI1.1 interpreter using the newly introduced Messager, Conversation, and ChoiceTalker classes4.`

`Consequently, native SCI0 games did not contain generalized conversation script frameworks; classes such as Messager, Conversation, and ChoiceTalker are entirely absent from the SCI0 class table3.`

| `Feature or Architecture` | `Sierra SCI0 (1988–1990)` | `Sierra SCI1 / SCI1.1 (1991–1993)` |
| :---- | :---- | :---- |
| **`Primary Interaction Paradigm`** | `Text Parser via Said specs and vocab.000 [cite: 1, 2]` | `Point-and-Click Icon Bar (IconItem)2` |
| **`Dialogue Methodology`** | `Parser queries (e.g., ask about <topic>)5` | `Graphical topic trees and dynamic menus4` |
| **`Stock UI Procedures`** | `Print with horizontal button allocation8` | `Messager, Talker, and ChoiceTalker [cite: 8, 11]` |
| **`Text Storage Format`** | `Contiguous ASCIIZ strings in text.XXX [cite: 1, 3]` | `Structured 4-tuple strings in msg.XXX [cite: 3, 10, 11]` |
| **`Stock Scrolling Widget`** | `DSelector (restricted to Save/Restore)12` | `DSelector, ScrollableInventory [cite: 8, 14]` |

### 

### **`The Mechanics of Print and Horizontal Button Allocation`**

`The stock Print procedure defined in Controls.sc operates as a temporary wrapper around the Dialog class and several primitive Control subclasses8. When #button directives are parsed, Print evaluates button text widths using the TextSize kernel call and positions each button sequentially along a single horizontal tracking plane8. The internal layout routine does not implement line breaks, wrapping logic, or vertical coordinate advancing for button controls8.`  
`When presenting multiple verbose options (such as four or five responses comprising 20 to 50 characters each), the aggregate horizontal bounding box quickly exceeds the physical 320-pixel display width1. The engine then renders buttons overlapping each other or clips them outside the screen boundaries, rendering the dialog unusable1.`

`Historical SCI0 titles avoided this failure mode because buttons generated through Print were intentionally restricted to two or three terse choices (such as "Yes", "No", "Restore", "Restart", or "Quit")10.`

### **`Native Scrollable Lists: The DSelector Control`**

`When Sierra titles required multi-entry selection within SCI0—most notably in the standard Save and Restore interfaces—they avoided Print entirely12. The engine provided a specialized GUI control class named DSelector, defined within Controls.sc (and documented in legacy SCI Studio references)8.`

| `Class Attribute` | `Specification Details` |
| :---- | :---- |
| **`Superclass`** | `Control [cite: 13]` |
| **`Instance Properties`** | `type, state, nsTop, nsLeft, nsBottom, nsRight, key, said, value, font, x, y, text, cursor, lsTop, mark [cite: 13]` |
| **`Primary Method: handleEvent`** | `Captures keyboard directional arrows and mouse clicks to update list position13` |
| **`Primary Method: setSize`** | `Calculates total bounding geometry based on font dimensions and visible row counts13` |
| **`Primary Method: indexOf`** | `Scans list buffers and returns the zero-based index of a string parameter13` |
| **`Primary Method: at`** | `Returns a pointer to the text string located at the specified index13` |
| **`Primary Method: advance`** | `Increments the cursor selection downward by a specified integer offset13` |
| **`Primary Method: retreat`** | `Decrements the cursor selection upward by a specified integer offset13` |

`The text property of a DSelector points to a contiguous memory buffer containing fixed-length string entries13. The lsTop property maintains the visual window scroll offset, cursor records the current selection index, and mark defines the visual indicator character13. By embedding a DSelector inside a customized Dialog instance, an application can manage extensive textual lists while enforcing bounded screen dimensions13.`

### **`Implementing Custom Multi-Option Vertical Choice Dialogs`**

`When game scenarios demanded multiple full-sentence choices rather than scrolling list boxes—exemplified by the age-verification trivia quiz in Leisure Suit Larry III (script room 290), where questions presented four distinct, verbose answer choices—Sierra developers bypassed Print and manually constructed a modal Dialog populated with vertically stacked DButton controls16.`

`The implementation pattern requires instantiating a base Dialog, attaching a DText control for the scenario prompt, and iteratively positioning DButton instances below one another by tracking vertical bounding offsets:`

Lisp  
(procedure (VerticalChoice prompt str1 str2 str3 str4 \&tmp dlg pText b1 b2 b3 b4 rVal curY)  
    (\= dlg (Dialog new:))  
    (\= curY 4)

    ;; Create and attach the prompt narrative  
    (\= pText (DText new:))  
    (pText  
        text: prompt,  
        font: 0,  
        moveTo: 4 curY,  
        setSize: 280  
    )  
    (dlg add: pText)  
    (\= curY (\+ curY (\- (pText nsBottom?) (pText nsTop?)) 6))

    ;; Instantiate first choice button  
    (\= b1 (DButton new:))  
    (b1  
        text: str1,  
        value: 1,  
        moveTo: 10 curY,  
        setSize:  
    )  
    (dlg add: b1)  
    (\= curY (\+ curY (\- (b1 nsBottom?) (b1 nsTop?)) 3))

    ;; Instantiate second choice button  
    (if (\>= argc 3)  
        (\= b2 (DButton new:))  
        (b2  
            text: str2,  
            value: 2,  
            moveTo: 10 curY,  
            setSize:  
        )  
        (dlg add: b2)  
        (\= curY (\+ curY (\- (b2 nsBottom?) (b2 nsTop?)) 3))  
    )

    ;; Instantiate third choice button  
    (if (\>= argc 4)  
        (\= b3 (DButton new:))  
        (b3  
            text: str3,  
            value: 3,  
            moveTo: 10 curY,  
            setSize:  
        )  
        (dlg add: b3)  
        (\= curY (\+ curY (\- (b3 nsBottom?) (b3 nsTop?)) 3))  
    )

    ;; Instantiate fourth choice button  
    (if (\>= argc 5)  
        (\= b4 (DButton new:))  
        (b4  
            text: str4,  
            value: 4,  
            moveTo: 10 curY,  
            setSize:  
        )  
        (dlg add: b4)  
    )

    ;; Finalize bounding rectangles, display modal window, and cleanup  
    (dlg setSize:, center:)  
    (\= rVal (dlg doit: b1))  
    (dlg dispose:)  
    (return rVal)  
)

`By computing vertical placement via control heights (- (control nsBottom?) (control nsTop?)) and assigning unique integers to the value property, developers achieve robust, non-overlapping layouts that operate cleanly within native SCI0 memory and display systems8.`

## **`Resource Map Indexing & Package Binary Formats`**

`The resource storage system in SCI0 strictly decouples index tables (RESOURCE.MAP) from concatenated binary data archives (RESOURCE.000, RESOURCE.001, etc.)1.`

### **`RESOURCE.MAP Binary Specification`**

`The RESOURCE.MAP index is composed of consecutive 6-byte binary records terminated by a 6-byte sentinel sequence consisting of all binary ones (0xFFFFFFFFFFFF)19. Records in RESOURCE.MAP are not ordered sequentially by resource ID; the engine scans the table linearly until the sentinel sequence is reached19.`

| `Byte Range` | `Data Type` | `Structural Allocation and Bitmasking` |
| :---- | :---- | :---- |
| **`0x00 .. 0x01`** | `uint16 (Little-Endian)` | **`Composite Resource ID (resId)`** `• Bits 15..11 (5 bits): Resource Type (Range: 0x00 to 0x1F)19 • Bits 10..00 (11 bits): Resource Number (Range: 0 to 2047)19` |
| **`0x02 .. 0x05`** | `uint32 (Little-Endian)` | **`Composite Archive Offset and Volume (offsetAndVol)`** `• Bits 31..28 (4 bits): Package Volume Index N (RESOURCE.00N)19 • Bits 27..00 (28 bits): Absolute Byte Offset within RESOURCE.00N [cite: 19]` |

`Bitwise mathematical extraction logic for mapping entries:`  
`![][image1]`  
`![][image2]`  
`![][image3]`  
`![][image4]`  
`Because RESOURCE.MAP lookup is a linear traversal that can contain duplicate resource identifiers, the engine accepts the newest matching record encountered during initialization19. This design allows SCI0 toolchains to function as append-only transaction logs: updated scripts or visual assets are appended to the volume archive and appended to RESOURCE.MAP without rewriting preceding binary data2.`

| `Type ID` | `Symbolic Name` | `File Extension` | `Engine Functionality` |
| :---- | :---- | :---- | :---- |
| **`0x00`** | `VIEW` | `.v56 / view.XXX` | `Multi-cel, multi-loop 16-color raster sprite graphics1` |
| **`0x01`** | `PIC` | `.p56 / pic.XXX` | `Background vector instructions, priority layers, control maps1` |
| **`0x02`** | `SCRIPT` | `.scr / script.XXX` | `Compiled virtual machine bytecode, object exports, locals1` |
| **`0x03`** | `TEXT` | `.tex / text.XXX` | `Sequential null-terminated strings for narrative text1` |
| **`0x04`** | `SOUND` | `.snd / sound.XXX` | `MIDI track sequences and multi-device playback instructions3` |
| **`0x05`** | `MEMORY` | `None` | `Reserved internal memory handle references3` |
| **`0x06`** | `VOCAB` | `.voc / vocab.XXX` | `Word parsing groups, grammar specifications, selector tables2` |
| **`0x07`** | `FONT` | `.fon / font.XXX` | `Raster character matrices and glyph metrics1` |
| **`0x08`** | `CURSOR` | `.cur / cursor.XXX` | `Mouse pointer bitmaps and hot-spot pixel offsets1` |
| **`0x09`** | `PATCH` | `.pat / patch.XXX` | `Audio hardware synthesis patch maps and driver parameters3` |

### 

### **`Package Volume Binary Layout (RESOURCE.00N)`**

`At the byte location dictated by ArchiveOffset, every compressed or uncompressed resource begins with an 8-byte header structure19:`

| `Byte Offset` | `Field Type` | `Field Description` |
| :---- | :---- | :---- |
| **`0x00 .. 0x01`** | `uint16 (Little-Endian)` | `Resource Identifier: Must match the resId defined in RESOURCE.MAP [cite: 19]` |
| **`0x02 .. 0x03`** | `uint16 (Little-Endian)` | `Compressed Length: Data size of the on-disk resource payload19` |
| **`0x04 .. 0x05`** | `uint16 (Little-Endian)` | `Decompressed Length: Total byte footprint required in system memory19` |
| **`0x06 .. 0x07`** | `uint16 (Little-Endian)` | `Compression Method: 0x00 = Raw; 0x01 = LZW; 0x02 = Huffman19` |

`If external development patch files (such as script.000 or pic.001) are present directly in the game directory, the SCI0 loader intercepts the request before inspecting RESOURCE.MAP1. External patch files omit the 8-byte volume header, prepending a simple 2-byte prefix: byte 0 contains the resource type bitwise OR-ed with 0x80, and byte 1 contains an unused padding byte1.`

## **`SCI0 System Class Architecture and Runtime Subsystems`**

`The stock SCI0 class architecture is streamlined around a strict single-inheritance model rooted at Object8.`

| `Class Hierarchy Level` | `Class Identifier` | `Primary Role and Subsystem Responsibilities` |
| :---- | :---- | :---- |
| `Root` | `Object` | `Base dispatch: defines property resolution, doit, and dispose [cite: 8, 22]` |
| `Functor` | `Code` | `Abstract execution block; encapsulates callback methods without state8` |
| `Collections` | `Collection` | `Dynamic pointer array holding object instances8` |
| `Collections` | `List` | `Linked collection supporting indexed retrieval (at, first, next)8` |
| `Collections` | `Set` | `Specialized list enforcing unique object references8` |
| `Collections` | `EventHandler` | `Dispatches user inputs via handleEvent across active subscriber sets8` |
| `Spatial Hierarchy` | `Feature` | `Base interactive screen zone; contains bounding boxes and parser said specs8` |
| `Spatial Hierarchy` | `View` | `Visible raster entity; encapsulates view number, loop, cel, and priority8` |
| `Spatial Hierarchy` | `Prop` | `View subclass containing a cycler reference for frame animation8` |
| `Spatial Hierarchy` | `Actor` | `Dynamic animating entity; incorporates movement routines (Mover)8` |
| `Spatial Hierarchy` | `Ego` | `Player-directed avatar; interfaces with user motion events and keyboards8` |
| `Scene Management` | `Rm (Room)` | `Manages local scene assets, picture resources, boundaries, and logic8` |
| `Scene Management` | `Script` | `Finite state coordinator; advances internal states via cue notifications8` |
| `User Interface` | `Dialog` | `Modal window manager; controls event loop execution for sub-widgets8` |
| `User Interface` | `Control` | `Abstract GUI element defining rectangular boundaries and hit-testing8` |
| `User Interface` | `DText` | `Text-rendering widget within modal dialog frames8` |
| `User Interface` | `DButton` | `Selectable button control; returns assigned values upon activation8` |
| `User Interface` | `DSelector` | `Scrollable vertical selection box for long text arrays8` |
| `User Interface` | `DEdit` | `Single-line editable text input control8` |
| `Audio Subsystem` | `Sound` | `Interfaces with sound drivers; controls MIDI playback and cue triggers8` |

### 

### **`Architectural Discrepancies Between SCI0 and SCI1.1`**

`The operational disparities between interpreter generations dictate how systems must be implemented:`

* **`Text and Localization Handling:`** `SCI0 manages textual dialog through raw string literals compiled into bytecode or sequential entries stored in text.XXX files, accessed using the (Format ...) kernel command1. SCI1.1 abandons this paradigm, replacing it with an external message subsystem (msg.XXX) addressed by noun, verb, condition, and sequence coordinates, accompanied by the Messager and Talker script layers3.`  
* **`Obstacle Avoidance and Pathfinding:`** `SCI0 derives movement boundaries entirely from the 16-color Control Map embedded within the pic.XXX background resource, supplemented by rectangular Cage boundaries and the primitive Avoider class1. SCI1.1 replaces control pixels with arbitrary vector polygon arrays (Polygon), dynamic pathfinding (PolyPath), and polygonal obstacle avoidance (PAvoider)8.`  
* **`Input and Event Distribution:`** `In SCI0, player input flows through the global User object into Said specifiers evaluated against the vocab.000 parser grammar1. In SCI1.1, parser routines are stripped out; events are classified by semantic verbs (Walk, Look, Do, Talk) and processed through an on-screen IconBar2.`

## 

## **`State Persistence, Save/Restore Internals, and File I/O`**

### **`The Monolithic Save/Restore Virtual Machine Mechanism`**

`Standard state serialization in SCI0 is executed by kernel-level functions rather than script code12. The scripts Save.sc and Restore.sc act as visual frontends invoking primitive engine operations11:`

* `(CheckSaveGame name num [version]): Verifies the presence and version alignment of a save file11.`  
* `(GetSaveFiles gameName buffer): Retrieves directory entries to populate save slot lists within DSelector instances11.`  
* `(GetSaveDir): Queries configuration variables for the active save directory target18.`  
* `(SaveGame name slot description version): Serializes an execution image directly to storage12.`  
* `(RestoreGame name slot version): Wipes the active execution heap, restores memory state from disk, and resumes execution12.`

`The native save image encapsulates an exhaustive snapshot: all dynamic heap allocations, active object properties, variable values, script execution frames, timers, and room states3. It does not provide mechanisms for reading, writing, or exporting isolated application state3.`

### **`File System Kernel Primitives`**

`To facilitate persistence independent of save-slot snapshots, SCI0 provides low-level operating system file operations30:`

| `Primitive Signature` | `Operating Mode and Functional Constraints` |
| :---- | :---- |
| `handle = FOpen(filePath, mode)` | `Initializes file pointer31: • fOPENFAIL (0): Opens existing file; returns NULL if missing31 • fOPENCREATE (1): Opens file; creates it if absent31 • fCREATE (2): Creates new file, truncating existing data to 0 bytes32` |
| `count = FGets(buffer, maxBytes, handle)` | `Reads sequential characters into buffer until newline or byte limit30` |
| `void FPuts(handle, buffer)` | `Appends null-terminated ASCIIZ string data to the specified file32` |
| `void FClose(handle)` | `Flushes internal OS caches and releases file descriptor handles32` |

### 

### **`Case Study: Quest for Glory Character Export Mechanics`**

`The character export implementation in Hero's Quest / Quest for Glory I (executed within room 840) provides a clear model for external game state serialization29. The program bypasses SaveGame, opening an external file (typically <HERONAME>.SAV) using FOpen with mode fCREATE29.`

`The character data is assembled into sequential string buffers via (Format ...) and written sequentially via FPuts32:`

* `The player character's name string, terminated by line feed (0x0A)29.`  
* `Primary character class identifiers (Fighter = 0, Magic User = 1, Thief = 2)4.`  
* `Character core attributes: Strength, Intelligence, Agility, Vitality, Luck, Weapon Use, Parry, Dodge, Stealth, Lockpicking, and Magic proficiencies4.`  
* `Acquired spell competencies (e.g., Open, Detect, Trigger, Flame Dart, Fetch)6.`  
* `Final currency totals (gold and silver tallies)5.`  
* `An algorithmic checksum integer calculated over preceding parameters to guard against manual tampering.`

`When Quest for Glory II processes character importation, it opens the target file using FOpen with mode fOPENFAIL, parses sequential string tokens using FGets, validates checksum integrity, and writes the resulting statistics directly into global variable arrays and gEgo properties29.`

## **`Memory Ceilings & Practical Engine Constraints`**

`Real-mode x86 execution environments under 16-bit DOS imposed rigid capacity limitations on the SCI0 interpreter architecture2:`

| `Architectural Metric` | `Capacity Limit` | `Operational Root Cause and Constraints` |
| :---- | :---- | :---- |
| **`Max Resource Number`** | `0 to 999` | `Standard Sierra tooling convention (11-bit mask limit is 2047)2` |
| **`Allocatable Heap Segment`** | `64 Kilobytes` | `x86 real-mode 64 KB memory segmentation boundaries2` |
| **`Compiled Script Size`** | `~64 Kilobytes` | `Hard segment ceiling; practical scripts remain below 16 KB2` |
| **`Global Variables`** | `256 entries` | `Global index dispatch limit (indices 0 through 255)15` |
| **`Display Resolution`** | `320 x 200` | `Fixed physical resolution of IBM EGA display hardware2` |
| **`Display Palette`** | `16 colors` | `Fixed EGA register DAC configuration2` |
| **`Maximum String Buffer`** | `255 characters` | `Fixed buffer allocations native to Sierra default templates` |

`The 64 KB heap segment ceiling represents the most acute restriction during development. The heap must simultaneously accommodate bytecode for loaded scripts, class definitions, active object instances, dynamic string allocations, and graphical node lists3. Exceeding this boundary produces immediate memory fragmentation faults or engine aborts3. Authors must structure gameplay across discrete room numbers (Rm), ensuring assets loaded in preceding rooms are purged from memory before allocating new entities3.`

## **`SCI0 Audio Format Specifications`**

`Audio assets in SCI0 (sound.XXX) combine standard MIDI event streams with hardware routing headers that map tracks to varied audio hardware, including the Roland MT-32, Yamaha YM3812 (AdLib), Tandy 1000 3-voice, and IBM PC Speaker1.`

| `Hardware Bitmask Flag` | `Target Synthesis Architecture` |
| :---- | :---- |
| **`0x01`** | `Roland MT-32 / LAPC-I Multi-Timbral MIDI Module20` |
| **`0x02`** | `Yamaha FB-01 4-Operator FM Synthesizer` |
| **`0x04`** | `AdLib / Sound Blaster (Yamaha YM3812 / OPL2 FM)20` |
| **`0x08`** | `Casio Synthesizer Architecture` |
| **`0x10`** | `Tandy 1000 / PCjr Complex Sound Generator (3-Voice)20` |
| **`0x20`** | `Standard IBM PC Speaker (1-Bit PWM Square Wave)20` |
| **`0x40`** | `Standard MPU-401 External MIDI Output Interface` |

### 

### **`Header Configuration: Standard SCI0 vs. Early SCI0`**

`Standard SCI0 sound resources begin with a 33-byte channel routing header20:`

* **`Byte 0x00:`** `Digital Audio Sample Indicator. A value of 0x00 specifies pure MIDI data; a value of 0x02 indicates digital PCM audio samples are appended to the resource file20.`  
* **`Bytes 0x01 .. 0x20:`** `16 successive 2-byte channel configuration structures corresponding to MIDI channels 0 through 1520.`  
  * *`Byte 1:`* `Upper nibble defines required polyphonic voice capacity; lower nibble specifies driver device index20.`  
  * *`Byte 2:`* `Hardware channel enable bitmask (matching the bitmask table above). Channels matching the user's audio device are played; non-matching channels are muted20.`

`An earlier variant exists in King's Quest IV (1988) and the 1988 Sierra Christmas Card20. This early format uses a 17-byte header consisting of the single digital sample byte followed by 16 single-byte channel definitions that compress voice counts and driver masks into an 8-bit allocation20. Following the header bytes, both variants provide a standard MIDI event stream (Note On, Note Off, Controller Change, Pitch Bend) interleaved with variable-length delta times20.`

## **`Authoritative Documentation Repositories`**

### **`ScummVM SCI Specifications: Chapter 2 - Resource Files`**

[`ScummVM SCI Specifications: Chapter 2 - Resource Files`](https://sciwiki.sierrahelp.com/index.php/SCI_Specifications:_Chapter_2_-_Resource_files) `provides the foundational technical specification for SCI0 through SCI1 package storage formats19. It documents the 6-byte records and FFFFFFFFFFFFh termination sequence of RESOURCE.MAP, describes bitwise unpacking for type and volume indices, details the 8-byte volume package header, and provides technical descriptions of the Huffman and LZW decompression algorithms19.`

> `"The SCI0 map file format is pretty simple: It consists of 6-byte entries, terminated by the sequence 0xffff ffff ffff. The first 2 bytes, interpreted as little endian 16 bit integer, encode resource type (high 5 bits) and number (low 11 bits)... SCI0 resource entries start with a four-tuple of little endian 16 bit words, which we will call (id, comp_size, decomp_size, method)."19`  
> 

### **`ScummVM SCI Specifications: Chapter 1 - Introduction`**

[`ScummVM SCI Specifications: Chapter 1 - Introduction`](https://wiki.scummvm.org/index.php/SCI/Specifications/Introduction) `provides a comprehensive architectural overview of the SCI virtual machine1. The documentation categorizes SCI0 resource families, explains how external patch files take precedence over monolithic resource packages, and details the coordinate layers of pic resources (visual vectors, priority maps, and control maps)1.`

> `"Individual resources can be stored in one of two ways: Either in resource files (which, surprisingly, are called something like 'resource.000' or 'resource.001'), or in external patch files... The external files are called something like 'pic.100' or 'script.000', and they take precedence over data from resource files... Pic resources also include two additional 'maps': The priority map... and the control map, which delimits the walking area."1`  
> 

### **`SCI Studio Help Documentation: Class DSelector`**

[`SCI Studio Help Documentation: Class DSelector`](https://sierrahelp.com/SCI/SCIStudio3Help/SCC/Class_DSelector.html) `contains detailed technical documentation for the native SCI0 list-selection control13. It defines the class inheritance chain (Control -> DSelector), outlines instance properties for cursor tracking and viewport boundaries, and provides method signatures for list manipulation13.`  
> `"The DSelector class is the control class for selector controls. These allow the user to select an item from a list... Methods: bool handleEvent(heapPtr pEvent) - Handles the selector's input, allowing the user to select items in the list. void setSize() - Sets up the control's size. number indexOf(string aString) - Returns the index of specified string in the list of items... string at(number position) - Returns string at the specified position in the list."13`  
> 

### **`SCI Studio Help Documentation: Low-Level File Kernel Operations`**

[`SCI Studio Help Documentation: Low-Level File Kernel Operations`](https://sierrahelp.com/SCI/SCIStudio3Help/SCC/Kernel_FPuts.html) `documents the kernel-level file management primitives (FOpen, FGets, FPuts, FClose)30. It defines operating modes, memory handling expectations, and procedural usage for reading and writing data outside standard engine save states30.`

> `"void FPuts(number handle, string buffer) Writes the string contained in buffer to the file pointed to by handle. Example: (if( <> NULL (= hFile FOpen("somefile.txt" fCREATE)) ) FPuts(hFile "Hello World!") FClose(hFile) )... number FGets(string buffer, number max, number handle) Reads a string from the file pointed to by handle and stores it in buffer."30`  
> 

### **`The SCI0 Sound Resource Format Specification`**

[`The SCI0 Sound Resource Format Specification`](https://wiki.scummvm.org/index.php/SCI/Specifications/Sound/SCI0_Resource_Format) `presents Ravi Iyengar's reverse-engineering analysis of SCI0 audio storage20. It explains hardware target channel bitmasks, details the differences between early 17-byte headers and standard 33-byte headers, and describes the storage and playback of digital audio samples20.`

> `"The identifier is the resource type (04h for sound) OR-ed with 80h and stored as a word... The sound resource data itself is a header with channel initialization followed by a series of MIDI events. The header provides the sound driver with 2 pieces of information about each channel... The upper 4 bits of that byte specify how many voices each logical MIDI channel will be playing. The lower 4 bits specify which drivers should react on that channel."20`  
> 

### **`Sierra On-Line Internal Archive: SCI Changes and Updates`**

[`Sierra On-Line Internal Archive: SCI Changes and Updates`](https://sciwiki.sierrahelp.com/images/e/e7/SCI_Changes_%26_Updates.pdf) `is an authentic Sierra internal development document tracking engine transitions from late SCI0 through SCI1.111. It details why older controls were replaced, records changes to DSelector, and documents the migration from raw script-based text parsing to the decoupled audio-compatible Messager system11.`

> `"Since selector items can't read from a file and VERBS.SH is game-specific, the selector can't adjust itself from game to game... Messager and Narrator classes are now fully audio-compatible. Messager makes use of the new (Message MsgKey) kernel call, which retrieves the current message pointers from the kernel and stores them into an array."11`  
> 

## **`Conclusions and Architectural Implementation Plan`**

* **`Resolution for Choice Interactions:`** `Stock SCI0 games never presented multi-option dialogue trees via Print10. For long choice interactions in Trauma Response Simulator, avoid Print with multiple #button parameters entirely. Instead, deploy the custom VerticalChoice routine to stack DButton instances vertically by calculating control heights, or utilize DSelector embedded within a modal Dialog when displaying scrollable option lists13.`  
* **`Resource Tooling Pipeline:`** `When interfacing with binary archives, serialize RESOURCE.MAP entries using 6-byte records containing (Type << 11) | Number and (Volume << 28) | Offset, terminated with 0xFFFFFFFFFFFF19. Leverage the append-only design of RESOURCE.MAP during active development to append modified resources without rebuilding full volumes2.`  
* **`Persistent Stat Tracking:`** `Implement stat exports and gameplay tracking via FOpen, FPuts, FGets, and FClose rather than the monolithic SaveGame system12. Structuring external files using formatted ASCII strings mirrors the approach established in Quest for Glory I, allowing persistent data to be loaded and verified cleanly across game sessions29.`  
* **`Execution Footprint Management:`** `Enforce modular separation across distinct room scripts (Rm), ensuring transient dialogue trees and variable allocations are disposed of upon scene exits to preserve space within the 64 KB heap segment limit2.`

#### **`Works cited`**

> 1. `SCI/Specifications/Introduction - ScummVM :: Wiki, https://wiki.scummvm.org/index.php/SCI/Specifications/Introduction`  
> 2. `Getting Started — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/intro.html`  
> 3. `SCI Specifications: Chapter 1 - Introduction, https://sciwiki.sierrahelp.com/index.php/SCI_Specifications:_Chapter_1_-_Introduction`  
> 4. `Quest for Glory: So You Want to Be a Hero - Wikipedia, https://en.wikipedia.org/wiki/Quest_for_Glory:_So_You_Want_to_Be_a_Hero`  
> 5. `Let's Play Quest for Glory: So You Want to be a Hero?, https://www.falselogic.net/LetsPlay/QuestforGlory1.html`  
> 6. `Quest for Glory I: So You Want To Be a Hero - Hardcore Gaming 101, https://www.hardcoregaming101.net/quest-for-glory/`  
> 7. `Game 77: Hero's Quest: So You Want to Be a Hero - The CRPG Addict, http://crpgaddict.blogspot.com/2012/11/game-77-heros-quest-so-you-want-to-be_20.html`  
> 8. `DButton (of Control) — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/Classes/DButton.html`  
> 9. `Quest for Glory I - The Adventure Game Database, https://www.adventuregamedb.com/g/quest_for_glory_i_vga`  
> 10. `Print (of Object) — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/Classes/Print.html`  
> 11. `S C I C H A N G E S & U P D A T E S - SCI Wiki, https://sciwiki.sierrahelp.com/images/e/e7/SCI_Changes_%26_Updates.pdf`  
> 12. `SaveRestoreDialog (of Dialog) — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/Classes/SaveRestoreDialog.html`  
> 13. `Class: DSelector - The Sierra Help Pages, https://sierrahelp.com/SCI/SCIStudio3Help/SCC/Class_DSelector.html`  
> 14. `ScrollableInventory (of InventoryBase) - SCI Companion, https://scicompanion.com/Documentation/Classes/ScrollableInventory.html`  
> 15. `SCI Companion V3 - alpha build notes/bugs/feature requests, https://sciprogramming.com/community/index.php?topic=1420.870`  
> 16. `Leisure Suit Larry III: Passionate Patti in Pursuit of the Pulsating, https://gamefaqs.gamespot.com/pc/565082-leisure-suit-larry-iii-passionate-patti-in-pursuit-of-the/faqs/11845`  
> 17. `Leisure Suit Larry 3 – Passionate Patti in Pursuit of the Pulsating, https://gaolioccasionallyreviews.wordpress.com/2020/10/18/leisure-suit-larry-3/`  
> 18. `Anatomy of an SCI game — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/anatomy.html`  
> 19. `SCI Specifications: Chapter 2 - Resource files, https://sciwiki.sierrahelp.com/index.php/SCI_Specifications:_Chapter_2_-_Resource_files`  
> 20. `SCI/Specifications/Sound/SCI0 Resource Format - ScummVM :: Wiki, https://wiki.scummvm.org/index.php/SCI/Specifications/Sound/SCI0_Resource_Format`  
> 21. `SCI Specifications - SCI Wiki, https://sciwiki.sierrahelp.com/index.php/SCI_Specifications`  
> 22. `Code (of Object) — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/Classes/Code.html`  
> 23. `Collection (of Object) — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/Classes/Collection.html`  
> 24. `Event (of Object) — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/Classes/Event.html`  
> 25. `View (of Feature) — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/Classes/View.html`  
> 26. `Scripts: DCIcon - The Sierra Help Pages, https://sierrahelp.com/SCI/SCIStudio3Help/SCC/Script_DCIcon.html`  
> 27. `PAvoider (of Code) — SCICompanion 3.0 documentation, https://scicompanion.com/Documentation/Classes/PAvoider.html`  
> 28. `SCI/Specifications/SCI virtual machine/Kernel functions - ScummVM, https://wiki.scummvm.org/index.php/SCI/Specifications/SCI_virtual_machine/Kernel_functions`  
> 29. `The Quest for Glory Character Files - AGDInteractive - AGD Interactive, http://www.agdinteractive.com/forum/viewtopic.php?t=7992`  
> 30. `Kernel: FGets - The Sierra Help Pages, https://sierrahelp.com/SCI/SCIStudio3Help/SCC/Kernel_FGets.html`  
> 31. `Kernel: FOpen - The Sierra Help Pages, https://sierrahelp.com/SCI/SCIStudio3Help/SCC/Kernel_FOpen.html`  
> 32. `Kernel: FPuts - The Sierra Help Pages, https://sierrahelp.com/SCI/SCIStudio3Help/SCC/Kernel_FPuts.html`  
> 33. `9281: davidtki's DOS Quest for Glory II: Trial by Fire "game end glitch, https://tasvideos.org/9281S`

[image1]: <data:image/png;base64,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>

[image2]: <data:image/png;base64,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>

[image3]: <data:image/png;base64,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>

[image4]: <data:image/png;base64,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>