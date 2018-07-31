package com.xlent.robin;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

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
	public static void leftClick() {
		robert.mousePress(InputEvent.BUTTON1_MASK);
		robert.delay(200);
		robert.mouseRelease(InputEvent.BUTTON1_MASK);
		robert.delay(200);
	}
	
	/*
	 * Makes a double click with the left mouse button
	 */
	public static void dubbleClick() {
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
	public static void moveMouseTo(int x, int y) {
		robert.mouseMove(x, y);
		robert.delay(500);
	}
	
	/**
	 * Moves the mouse pointer to a new location relative to its position.
	 * 
	 * @param x The number of pixels the mouse will move along the x-axis
	 * @param y The number of pixels the mouse will move along the y-axis
	 */
	public static void moveMouse(int x, int y) {
		Point location = MouseInfo.getPointerInfo().getLocation();
		moveMouseTo(location.x + x, location.y + y);
	}
	
	/*
	 * Types a string, at the moment it only handles [A-Z]
	 */
	public static void type(String str) {
		byte[] bytes = str.getBytes();
		for (byte b:bytes) {
			int code = b;
			if (code > 96 && code <123 )
				code -= 32;
			pressKey(code);
		}
	}
	
	/**
	 * Presses a key on the keyboard.
	 * 
	 * @param i An integer that corresponds to the wanted key
	 */
	private static void pressKey(int i) {
		robert.delay(40);
		robert.keyPress(i);
		robert.keyRelease(i);
	}
	
}
