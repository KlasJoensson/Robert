package com.xlent.robin;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * This is a wrapper class to make the use of java.awt.Robot easier.
 *   
 * @author Klas Jönsson
 *
 */
public class Robin {
	
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
	 * Makes a left click with the mouse
	 */
	public void leftClick() {
		robert.mousePress(InputEvent.BUTTON1_MASK);
		robert.delay(200);
		robert.mouseRelease(InputEvent.BUTTON1_MASK);
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
	 * Moves the mouse pointer to a new location relative to its position.
	 * 
	 * @param x The number of pixels the mouse will move along the x-axis
	 * @param y The number of pixels the mouse will move along the y-axis
	 */
	public void moveMouse(int x, int y) {
		Point location = MouseInfo.getPointerInfo().getLocation();
		moveMouseTo(location.x + x, location.y + y);
	}
	
	/*
	 * Types a string, at the moment it only handles [A-Z]
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
		//System.out.println( i + " => " + KeyEvent.getKeyText(i) );
		robert.delay(40);
		robert.keyPress(i);
		robert.delay(40);
		robert.keyRelease(i);
		robert.delay(40);
	}
	
	/**
	 * Writes a string in small caps.
	 * 
	 * @param str The string to be written
	 */
	public void write(String str) {
		str.chars().forEach( c -> pressKey( (char) c) );
	}
	
	public void pressKey(char c) {
		int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
		System.out.println( c + " => " + keyCode );
		/*if (keyCode == KeyEvent.VK_UNDEFINED)
			throw new IllegalArgumentException("Could not find any code for the char '" + c + "'.");
		else {*/
			if(c >= 'A' && c <= 'Z') {
				pressShiftPlusKey(keyCode);
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
			} else {//if(c > 32 && c < 48 ) {
				writeSpecialCharters(c);
			//} else {
				//pressKey(keyCode);
			}
		//}
	}
	
	/**
	 * Handles the chars in with ASCII nr. 33 to 47. Except comma and period, since they are handled elsewhere.
	 * Don't work: ' -
	 * 
	 * @param c
	 */
	private void writeAscii33to47(char c) {
		System.out.println("In writeAscii33to47");
		switch (c) {
		case '!':
			pressShiftPlusKey(KeyEvent.VK_1);
			break;
		case '"':
			pressShiftPlusKey(KeyEvent.VK_2);
			break;
		case '#':
			pressShiftPlusKey(KeyEvent.VK_3);
			break;		
		case '$':
			pressAltPlusKey(KeyEvent.VK_4);
			break;
		case '%':
			pressShiftPlusKey(KeyEvent.VK_5);
			break;
		case '&':
			pressShiftPlusKey(KeyEvent.VK_6);
			break;
		case '\'':
			System.out.println("Found char '");
			pressKey(KeyEvent.VK_QUOTE);
			break;
		case '(':
			pressShiftPlusKey(KeyEvent.VK_8);
			break;
		case ')':
			pressShiftPlusKey(KeyEvent.VK_9);
			break;
		case '*':
			pressKey(KeyEvent.VK_MULTIPLY);
			break;
		case '+':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('-') );
			break;
		case '-':
			System.out.println("Found char -");
			pressKey( KeyEvent.VK_MINUS );
			break;
		case '/':
			pressShiftPlusKey(KeyEvent.VK_7);
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Handles the chars in with ASCII nr. 58 to 64. Except comma and period, since they are handled elsewhere.
	 * 
	 * @param c
	 */
	private void writeAscii58to64(char c) {
		switch (c) {
		case ';':
			pressShiftPlusKey( KeyEvent.getExtendedKeyCodeForChar(',') );
			break;
		case ':':
			pressShiftPlusKey( KeyEvent.getExtendedKeyCodeForChar('.') );
			break;
		case '<':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('`') );
			break;
		case '=':
			pressShiftPlusKey(KeyEvent.VK_0);
			break;
		case '>':
			pressShiftPlusKey( KeyEvent.getExtendedKeyCodeForChar('`') );
			break;
		case '?':
			pressShiftPlusKey('-');
			break;	
		case '@':
			pressAltPlusKey('2');
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Handles the chars in with ASCII nr. 91 to 96. Except comma and period, since they are handled elsewhere.
	 * This chars don't work: ^ _ `
	 * 
	 * @param c
	 */
	private void writeAscii91to96(char c) {
		System.out.println("In writeAscii91to69");
		switch (c) {
		case '[':
			pressAltPlusKey(KeyEvent.VK_8);
			break;
		case '\\':
			robert.keyPress( KeyEvent.VK_ALT );
			pressShiftPlusKey(KeyEvent.VK_7);
			robert.keyRelease( KeyEvent.VK_ALT);
			break;
		case ']':
			pressAltPlusKey(KeyEvent.VK_9);
			break;
		case '^':
			System.out.println("Found char ^");
			pressShiftPlusKey( KeyEvent.getExtendedKeyCodeForChar('^') );
			pressKey(' ');
			break;
		case '_':
			System.out.println("Found char _");
			pressKey(KeyEvent.VK_UNDERSCORE);
			break;
		case '`':
			System.out.println("Found char `");
			pressShiftPlusKey(KeyEvent.getExtendedKeyCodeForChar('´'));
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Handles the chars in with ASCII nr. 123 to 126. Except comma and period, since they are handled elsewhere.
	 * This char don't work: ~
	 * @param c
	 */
	private void writeAscii123to126(char c) {
		System.out.println("In writeAscii91to69");
		switch (c) {
		case '{':
			robert.keyPress( KeyEvent.VK_ALT );
			pressShiftPlusKey(KeyEvent.VK_8);
			robert.keyRelease( KeyEvent.VK_ALT);
			break;
		case '|':
			pressAltPlusKey(KeyEvent.VK_7);
			break;
		case '}':
			robert.keyPress( KeyEvent.VK_ALT );
			pressShiftPlusKey(KeyEvent.VK_9);
			robert.keyRelease( KeyEvent.VK_ALT);
			break;
		case '~':
			System.out.println("Found char ~");
			robert.keyPress( KeyEvent.VK_ALT );
			pressKey( '¨');
			robert.keyRelease( KeyEvent.VK_ALT);
			pressKey(KeyEvent.VK_SEPARATER);
			break;
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
		}
	}
	
	/**
	 * Some of chars from the extended ASCII table (i.e. 128 to 255).
	 * 
	 * @param c
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
			pressShiftPlusKey('[');
			break;
		case 'Ä':
			pressShiftPlusKey('\'');
			break;
		case 'Ö':
			pressShiftPlusKey(';');
			break;
		case '€':
			pressShiftPlusKey(KeyEvent.VK_4);
			break;		
		case '\'':
			pressKey( KeyEvent.getExtendedKeyCodeForChar('\\') );
			break;
		case '©':
			pressAltPlusKey( '1' );
			break;
		case '±':
			pressAltPlusKey( '-' );
			break;
		
		default:
			pressKey( KeyEvent.getExtendedKeyCodeForChar(c) );
			//throw new IllegalArgumentException("Could not find any code for the char '" + c + "'.");
		}
	}
	
	/**
	 * Simulates pressing Meta or Command key before pressing the key for the char, 
	 * e.g. with the argument 'a' it would be like pressing Meta + a on the keyboard.
	 * 
	 * @param char The char to be combined with the Meta key
	 */
	public void pressCommandPlusKey(char c) {
		robert.keyPress( KeyEvent.VK_META );
		robin.pressKey(c);
		robert.keyRelease(KeyEvent.VK_META);
	}
	
	/**
	 * Simulates pressing Shift before pressing the key for the char, 
	 * e.g. with the argument 'a' it would be like pressing shift + a on the keyboard.
	 * 
	 * @param char The char to shift
	 */
	public void pressShiftPlusKey(char c) {
		pressShiftPlusKey( KeyEvent.getExtendedKeyCodeForChar(c) );
	}
	
	/**
	 * Simulates pressing Shift before pressing the key with the key code.
	 * 
	 * @param keyCode the Java.KeyEvent code for the key 
	 */
	private void pressShiftPlusKey(int keyCode) {
		robert.keyPress( KeyEvent.VK_SHIFT );
		robin.pressKey(keyCode);
		robert.keyRelease( KeyEvent.VK_SHIFT );
	}
	
	/**
	 * Simulates pressing Alt key before pressing the key for the char, 
	 * e.g. with the argument 'a' it would be like pressing Alt + a on the keyboard.
	 * 
	 * @param char The char to be combined with the Alt key
	 */
	public void pressAltPlusKey(char c) {
		pressAltPlusKey( KeyEvent.getExtendedKeyCodeForChar(c) );
	}
	
	private void pressAltPlusKey(int keyCode) {
		robert.keyPress( KeyEvent.VK_ALT );
		robin.pressKey(keyCode);
		robert.keyRelease( KeyEvent.VK_ALT);
	}
	
	/**
	 * Simulates pressing Alt Graph key before pressing the key for the char, 
	 * e.g. with the argument 'a' it would be like pressing AltGr + a on the keyboard.
	 * 
	 * Note: Alt Graph don't exists on macbook pro, only Alt (with the same result...)
	 * 
	 * @param char The char to be combined with the Alt Graph key
	 */
	public void pressAltGrPlusKey(char c) {
		pressAltPlusKey( KeyEvent.getExtendedKeyCodeForChar(c) );
	}
	
	private void pressAltGrPlusKey(int keyCode) {
		pressAltPlusKey(keyCode);
	}
	
	/**
	 * Simulates pressing both shift and Alt Graph key before pressing the key for the char, 
	 * e.g. with the argument 'a' it would be like pressing Shift + AltGr + a on the keyboard.
	 * 
	 * @param char The char to be combined with the shift and Alt Graph key
	 */
	public void pressShiftAltGrPlusKey(char c) {
		pressAltGrPlusKey( KeyEvent.getExtendedKeyCodeForChar(c) );
	}
	
	private void pressShiftAltGrPlusKey(int keyCode) {
		robert.keyPress( KeyEvent.VK_SHIFT );
		pressAltGrPlusKey(keyCode);
		robert.keyRelease( KeyEvent.VK_SHIFT );
	}
}
