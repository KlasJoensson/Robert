package com.xlent.robin;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.AWTException;

/**
 * This class is for making use of the little robot Robin.
 * 
 * @author Klas Jönsson
 */
public class RunRobin {
	
	public static void main(String[] args) {
		try {
			Robin robban = Robin.getInstance();
			clickOnTab(robban);
		} catch (AWTException e) {
			fail("Could not initiate: " + e.getMessage() );
			e.printStackTrace();
		}
	}
	
	private static void clickOnTab(Robin robban) {
		robban.moveMouseTo(200, 50);
		robban.leftClick();
	}
	
	private static void openNewTab(Robin robban) {
		// kringla + t öpnar ny tab
		robban.moveMouseTo(200, 50);
		robban.leftClick();
		
	}
}
