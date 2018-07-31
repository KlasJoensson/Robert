package com.xlent.robin;

import java.awt.AWTException;
import java.awt.Robot;

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
	}

	public static Robin getInstace() throws AWTException {
		
		if (robin == null)
			robin = new Robin();
		
		return robin;
	}
	
	
}
