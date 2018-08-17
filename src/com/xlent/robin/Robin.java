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
		System.out.println( i + " => " + KeyEvent.getKeyText(i) );
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
		if (keyCode == KeyEvent.VK_UNDEFINED)
			throw new IllegalArgumentException("Could not find any code for the char '" + c + "'.");
		else {
			if(c >'A' && c < 'Z') {
				pressShiftPlusKey(c);
			} else {
				pressKey(keyCode);
			}
		}
	}
	
	public void pressCommandPlusKey(char c) {
		robert.keyPress( KeyEvent.VK_META );
		robin.pressKey(c);
		robert.keyRelease(KeyEvent.VK_META);
	}
	
	public void pressShiftPlusKey(char c) {
		robert.keyPress( KeyEvent.VK_SHIFT );
		robin.pressKey(c);
		robert.keyRelease(KeyEvent.VK_SHIFT);
	}
	
	public void pressAltPlusKey(char c) {
		robert.keyPress( KeyEvent.VK_ALT );
		robin.pressKey(c);
		robert.keyRelease(KeyEvent.VK_ALT);
	}
	
	public void pressAltGrPlusKey(char c) {
		robert.keyPress( KeyEvent.VK_ALT_GRAPH );
		robin.pressKey(c);
		robert.keyRelease(KeyEvent.VK_ALT_GRAPH );
	}
}
