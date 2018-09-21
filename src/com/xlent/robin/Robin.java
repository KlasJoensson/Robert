package com.xlent.robin;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * This is a wrapper class to make the use of java.awt.Robot easier.
 *   
 * @author Klas Jönsson
 *
 */
public class Robin {
	
	/**
	 * Enumeration for the different modifier keys. E.g. Shift, alt or command key
	 * 
	 * @author Klas Jönsson
	 *
	 */
	public enum ModifierKey {
		SHIFT,
		ALT,
		ALT_GR,
		COMMAND,
		WINDOWS,
		META,
		NON
	}
	
	static Robin robin;
	static Robot robert;
	
	private Robin() throws AWTException {	
		robert = new Robot();
		robert.setAutoDelay(40);
		robert.setAutoWaitForIdle(true);
	}

	/**
	 * Creates and returns an new instance of the Robert Robin.
	 * 
	 * @return An instance of Robin
	 * @throws AWTException If the platform dosn't allow low-level input control.
	 */
	public static Robin getInstance() throws AWTException {
		
		if (robin == null)
			robin = new Robin();
		
		return robin;
	}
	
	/**
	 * Makes it wait for a while.
	 * 
	 * @param ms The time to wait in ms.
	 */
	public void wait(int ms) {
		robert.delay(ms);
	}
	
	/**
	 * Opens a program. The name has to be the real app name not the translated, e.g. "Chess" instead of "Schack".
	 * 
	 * @param name The name of the program.
	 * @throws IOException If the program isn't fond
	 */
	public void openApp(String name) throws IOException {
		
		StringBuilder sb = new StringBuilder("/Applications/");
		sb.append(name);
		sb.append(".app");

		Desktop.getDesktop().open(new File(sb.toString()));
		
	}
	
	/*************************************
	 * Methods for controlling the mouse *
	 *************************************/
	
	/**
	 * Makes a right click with the mouse
	 */
	public void rightClick() {
		mouseClick(InputEvent.BUTTON3_MASK);
	}
	
	/**
	 * Makes a wheel click with the mouse
	 */
	public void wheelClick() {
		mouseClick(InputEvent.BUTTON2_MASK);
	}
	
	/**
	 * Makes a left click with the mouse
	 */
	public void leftClick() {
		mouseClick(InputEvent.BUTTON1_MASK);
	}
	
	/**
	 * Makes a left click with the mouse
	 */
	private void mouseClick(int button) {
		robert.mousePress(button);
		robert.delay(200);
		robert.mouseRelease(button);
		robert.delay(200);
	}
	
	/*
	 * Makes a double click with the left mouse button
	 */
	public void dubbleClick() {
		leftClick();
		robert.delay(500);
		leftClick();
	}
	
	/**
	 * Moves the mouse pointer to a specific location on the screen.
	 * 
	 * @param x The x-coordinate to move the mouse to
	 * @param y The y-coordinate to move the mouse to
	 */
	public void moveMouseTo(int x, int y) {
		robert.mouseMove(x, y);
		robert.delay(500);
	}
	
	/**
	 * Moves the mouses wheel "up", e.g. to scroll up a web-page.
	 * 
	 * @param notches The number of "notches" to move the mouse wheel
	 */
	public void scrollUp(int notches) {
		scrollUp(notches, 0);
	}
	
	/**
	 * Moves the mouses wheel "up", e.g. to scroll up a web-page, with a delay to get a more smooth scrolling.
	 * 
	 * @param notches The number of "notches" to move the mouse wheel
	 * @param delay The delay between each notch in ms
	 */
	public void scrollUp(int notches, int delay) {
		while(notches-- > 0) {
			robert.mouseWheel(1);
			robert.delay(delay);
		}
	}
	
	/**
	 * Moves the mouses wheel "down", e.g. to scroll down a web-page.
	 * 
	 * @param notches The number of "notches" to move the mouse wheel
	 */
	public void scrollDown(int notches) {
		scrollDown(notches, 0);
	}
	
	/**
	 * Moves the mouses wheel "down", e.g. to scroll down a web-page, with a delay to get a more smooth scrolling.
	 * 
	 * @param notches The number of "notches" to move the mouse wheel
	 * @param delay The delay between each notch in ms
	 */
	public void scrollDown(int notches, int delay) {
		while(notches-- > 0) {
			robert.mouseWheel(-1);
			robert.delay(delay);
		}
	}
	
	/**
	 * Moves the mouse pointer to a new location relative to its position.
	 * 
	 * @param x The number of pixels the mouse will move along the x-axis
	 * @param y The number of pixels the mouse will move along the y-axis
	 */
	public void moveMouse(int x, int y) {
		Point location = MouseInfo.getPointerInfo().getLocation();
		moveMouseTo(location.x + x, location.y + y);
	}
	
	/****************************************
	 * Methods for controlling the keyboard *
	 ****************************************/
	
	/**
	 * Types a string in the active field, document etc. At the moment all printebly chars 
	 * 
	 * @param str The string to be written.
	 */
	public void type(String str) {
		byte[] bytes = str.getBytes();
		for (byte b:bytes) {
			int code = b;
			if (code > 96 && code <123 )
				code -= 32;
			pressKey(code);
		}
	}
	
	/**
	 * Presses a key on the keyboard. The integer is the same as the one used by KeyEvent.
	 * 
	 * @param i An integer that corresponds to the wanted key
	 */
	private void pressKey(int i) {
		robert.delay(40);
		robert.keyPress(i);
		robert.delay(40);
		robert.keyRelease(i);
		robert.delay(40);
	}
	
	/**
	 * Writes a string in small caps. The robot thinks the layout is similar to the (US) English International Layout,
	 * but the chars written are matching the Swedish Layout.
	 * The following three chars from the standard ASCII table don't work: ^ ` ~ 
	 * And at the moment it's eleven chars from the extended ASCII table that do work: å ä ö Å Ä Ö € © ± ≈ §
	 * 
	 * @param str The string to be written
	 */
	public void write(String str) {
		str.chars().forEach( c -> pressKey( (char) c) );
	}
	
	/**
	 * Press a key and a modifier key, e.g. Shift + k for K
	 * 
	 * @param c The char on the key to be pressed
	 * @param mk The modifier key
	 */
	public void pressKey(char c, ModifierKey mk) {
		pressKey(KeyEvent.getExtendedKeyCodeForChar(c),  mk);
	}
	
	/**
	 * Press a key and a modifier key, e.g. Shift + k for K
	 * 
	 * @param i An integer representing the key to be pressed
	 * @param mk The modifier key
	 */
	public void pressKey(int i, ModifierKey mk) {
		pressModefierKey(mk);
		pressKey( i );
		releaseModefierKey(mk);
	}
	
	/**
	 * Press a key and a modifier key, e.g. Shift + Alt + 9 for }
	 * 
	 * @param c The char on the key to be pressed
	 * @param mk An array with the modifier keys
	 */
	public void pressKey(char c, ModifierKey[] mk) {
		pressKey(KeyEvent.getExtendedKeyCodeForChar(c),  mk);
	}
	
	/**
	 * Press a key and a modifier key, e.g. Shift + Alt + 9 for }
	 * 
	 * @param i An integer representing the key to be pressed
	 * @param mk An array with the modifier keys
	 */
	public void pressKey(int i, ModifierKey[] mk) {
		Arrays.stream(mk).forEach( key -> pressModefierKey(key) );
		pressKey( i );
		Arrays.stream(mk).forEach( key -> releaseModefierKey(key) );
	}
	
	/**
	 * Press a key (or key combination) representing the char.
	 * 
	 * @param c The char hows key to be pressed
	 */
	public void pressKey(char c) {
		int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			if(c >= 'A' && c <= 'Z') {
				pressKey(keyCode, ModifierKey.SHIFT);
			} else if(c >='a' && c <= 'z' || c == ' ' || c == '.' || c == ','|| c > 47 && c < 58) { 
				//char nr 48 to 57 in the ASCII table gives the same char, i.e. 0-9
				pressKey(keyCode);
			} else if(c > 32 && c < 48) { 
				// Except for . and , which are handle above
				writeAscii33to47(c);
			} else if(c > 57 && c < 65) { 
				writeAscii58to64(c);
			} else if(c > 90 && c < 97) { 
				writeAscii91to96(c);
			} else if(c > 122 && c < 127) { 
				writeAscii123to126(c);
			} else {
				writeSpecialCharters(c);
			}
	}
	
	/**
	 * Handles the chars in with ASCII nr. 33 to 47. Except comma and period, since they are handled elsewhere.
	 * 
	 * @param c The char to be written
	 */
	private void writeAscii33to47(char c) {
		switch (c) {
		case '!':
			pressKey(KeyEvent.VK_1, ModifierKey.SHIFT);
			break;
		case '"':
			pressKey(KeyEvent.VK_2, ModifierKey.SHIFT);
			break;
		case '#':
			pressKey(KeyEvent.VK_3, ModifierKey.SHIFT);
			break;		
		case '$':
			pressKey(KeyEvent.VK_4, ModifierKey.ALT);
			break;
		case '%':
			pressKey(KeyEvent.VK_5, ModifierKey.SHIFT);
			break;
		case '&':
			pressKey(KeyEvent.VK_6, ModifierKey.SHIFT);
			break;
		case '\'':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('\\') );
			break;
		case '(':
			pressKey(KeyEvent.VK_8, ModifierKey.SHIFT);
			break;
		case ')':
			pressKey(KeyEvent.VK_9, ModifierKey.SHIFT);
			break;
		case '*':
			pressKey(KeyEvent.VK_MULTIPLY);
			break;
		case '+':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('-') );
			break;
		case '-':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('/') );
			break;
		case '/':
			pressKey(KeyEvent.VK_7, ModifierKey.SHIFT);
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Handles the chars in with ASCII nr. 58 to 64. Except comma and period, since they are handled elsewhere.
	 * 
	 * @param c The char to be written
	 */
	private void writeAscii58to64(char c) {
		switch (c) {
		case ';':
			pressKey( KeyEvent.getExtendedKeyCodeForChar(','), ModifierKey.SHIFT );
			break;
		case ':':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('.'), ModifierKey.SHIFT );
			break;
		case '<':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('`') );
			break;
		case '=':
			pressKey(KeyEvent.VK_0, ModifierKey.SHIFT);
			break;
		case '>':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('`'), ModifierKey.SHIFT );
			break;
		case '?':
			pressKey('-', ModifierKey.SHIFT);
			break;	
		case '@':
			pressKey('2', ModifierKey.ALT);
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Handles the chars in with ASCII nr. 91 to 96. Except comma and period, since they are handled elsewhere.
	 * This chars don't work: ^ `
	 * 
	 * @param c The char to be written
	 */
	private void writeAscii91to96(char c) {
		switch (c) {
		case '[':
			pressKey(KeyEvent.VK_8, ModifierKey.ALT);
			break;
		case '\\':
			ModifierKey[] modifiers = {ModifierKey.ALT, ModifierKey.SHIFT};
			pressKey(KeyEvent.VK_7, modifiers);
			break;
		case ']':
			pressKey(KeyEvent.VK_9, ModifierKey.ALT);
			break;
		case '^':
			System.out.println("Found char ^");
			pressKey( KeyEvent.getExtendedKeyCodeForChar('^') );
			pressKey(' ');
			break;
		case '_':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('/'), ModifierKey.SHIFT );
			break;
		case '`':
			pressKey(KeyEvent.getExtendedKeyCodeForChar('<'), ModifierKey.SHIFT );
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Handles the chars in with ASCII nr. 123 to 126. Except comma and period, since they are handled elsewhere.
	 * This char don't work: ~
	 * 
	 * @param c The char to be written
	 */
	private void writeAscii123to126(char c) {
		switch (c) {
		case '{':
			ModifierKey[] modifiers0 = {ModifierKey.ALT, ModifierKey.SHIFT};
			pressKey(KeyEvent.VK_8, modifiers0);
			break;
		case '|':
			pressKey(KeyEvent.VK_7, ModifierKey.ALT);
			break;
		case '}':
			ModifierKey[] modifiers1 = {ModifierKey.ALT, ModifierKey.SHIFT};
			pressKey(KeyEvent.VK_9, modifiers1);
			break;
		case '~':
			System.out.println("Found char ~");
			pressKey( KeyEvent.getExtendedKeyCodeForChar('<') );
			/*robert.keyPress( KeyEvent.VK_ALT );
			pressKey( '¨');
			robert.keyRelease( KeyEvent.VK_ALT);
			pressKey(KeyEvent.VK_SEPARATER);*/
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Some of chars from the extended ASCII table (i.e. 128 to 255). At the moment it's 11 chars: 
	 * å ä ö Å Ä Ö € © ± ≈ §
	 * 
	 * @param c The char to be written
	 */
	private void writeSpecialCharters(char c) {
		switch (c) {
		case 'å':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('[') );
			break;
		case 'ä':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('\'') );
			break;
		case 'ö':
			pressKey( KeyEvent.getExtendedKeyCodeForChar(';') );
			break;
		case 'Å':
			pressKey('[', ModifierKey.SHIFT);
			break;
		case 'Ä':
			pressKey('\'', ModifierKey.SHIFT);
			break;
		case 'Ö':
			pressKey(';', ModifierKey.SHIFT);
			break;
		case '€':
			pressKey(KeyEvent.VK_4, ModifierKey.SHIFT);
			break;		
		case '©':
			pressKey( '1', ModifierKey.ALT);
			break;
		case '±':
			pressKey( '-', ModifierKey.ALT );
			break;
		case '≈':
			pressKey( '0', ModifierKey.ALT );
			break;
		case '§':
			pressKey( '6', ModifierKey.ALT );
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Press the arrow up key one or more times.
	 * 
	 * @param times The number of times to press the key
	 */
	public void pressArrowUp(int times) {
		pressArrowUp(times, ModifierKey.NON ); 
	}
	
	/**
	 * Press the arrow up key one or more times with a modifier key, e.g. Shift.
	 * 
	 * @param times The number of times to press the key
	 * @param mk The modifier key to be used
	 */
	public void pressArrowUp(int times, ModifierKey mk) {
		pressModefierKey(mk);
		for (;times>0;times--) {
			robert.keyPress( KeyEvent.VK_UP );
		}
		releaseModefierKey(mk);
	}
	
	/**
	 * Press the arrow down key one or more times.
	 * 
	 * @param times The number of times to press the key
	 */
	public void pressArrowDown(int times) {
		pressArrowDown(times, ModifierKey.NON);
	}
	
	/**
	 * Press the arrow down key one or more times with a modifier key, e.g. Shift.
	 * 
	 * @param times The number of times to press the key
	 * @param mk The modifier key to be used
	 */
	public void pressArrowDown(int times, ModifierKey mk) {
		pressModefierKey(mk);
		for (;times>0;times--) {
			robert.keyPress( KeyEvent.VK_DOWN );
		}
		releaseModefierKey(mk);
	}

	/**
	 * Press the arrow left key one or more times.
	 * 
	 * @param times The number of times to press the key
	 */
	public void pressArrowLeft(int times) {
		pressArrowLeft(times, ModifierKey.NON);
	}
	
	/**
	 * Press the arrow left key one or more times with a modifier key, e.g. Shift.
	 * 
	 * @param times The number of times to press the key
	 * @param mk The modifier key to be used
	 */
	public void pressArrowLeft(int times, ModifierKey mk) {
		pressModefierKey(mk);
		for (;times>0;times--) {
			robert.keyPress( KeyEvent.VK_LEFT );
		}
		releaseModefierKey(mk);
	}
	
	/**
	 * Press the arrow right key one or more times.
	 * 
	 * @param times The number of times to press the key
	 */
	public void pressArrowRight(int times) {
		pressArrowRight(times, ModifierKey.NON);
	}
	
	/**
	 * Press the arrow right key one or more times with a modifier key, e.g. Shift.
	 * 
	 * @param times The number of times to press the key
	 * @param mk The modifier key to be used
	 */
	public void pressArrowRight(int times, ModifierKey mk) {
		pressModefierKey(mk);
		for (;times>0;times--) {
			robert.keyPress( KeyEvent.VK_RIGHT );
		}
		releaseModefierKey(mk);
	}
	
	/**
	 * Press a modifier key.
	 * 
	 * @param mk The modifier key to be pressed
	 */
	private void pressModefierKey(ModifierKey mk) {
		switch (mk) {
		case SHIFT:
			robert.keyPress( KeyEvent.VK_SHIFT );
			break;
		case ALT:
		case ALT_GR:
			robert.keyPress( KeyEvent.VK_ALT );
			break;
		case COMMAND:
		case WINDOWS:
		case META:
			robert.keyPress( KeyEvent.VK_META );
			break;
		case NON:
		default:
			// As default no key is pressed...	
		}
	}
	
	/**
	 * Release a modifier key.
	 * 
	 * @param mk The modifier key to be released
	 */
	private void releaseModefierKey(ModifierKey mk) {
		switch (mk) {
		case SHIFT:
			robert.keyRelease( KeyEvent.VK_SHIFT );
			break;
		case ALT:
		case ALT_GR:
			robert.keyRelease( KeyEvent.VK_ALT );
			break;
		case COMMAND:
		case WINDOWS:
		case META:
			robert.keyRelease( KeyEvent.VK_META );
			break;
		case NON:
		default:
			// As default no key is released...	
		}
	}
}
