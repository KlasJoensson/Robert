package com.xlent.robin;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

/**
 * This class is for making use of the little robot Robin.
 * 
 * @author Klas Jönsson
 */
public class RunRobin {
	
	private static void clickOnTab(Robin robban) {
		robban.moveMouseTo(200, 50);
		robban.leftClick();
	}
	
	private static void openNewTab(Robin robban) {
		robban.moveMouseTo(1000, 60);
		robban.leftClick();
		robban.pressCommandPlusKey('t');
		robban.write("smp.se\n");
	}
	
	private static void writeNote(Robin robban) {
		robban.moveMouseTo(600, 1000);
		robban.leftClick();
		robban.write("2 ` 3 ~ 4");
		//robban.write("1 ' 2 - 3 ^ 4 _ 5 ` 6 ~");//! \" # $ % & ' ( ) * + , - . /");
		//robban.write("Testar @ 1 \\ 2 | 3 /");
		//robban.write("Testar 1 / 2 [ 3 \\ 4 ]5 ^ 6 _ 7 `"); // 1 - 2 å 3 ' 4 ¨5  6  7 <
	}
	
	public static void main(String[] args) {
		try {
			Robin robban = Robin.getInstance();
			//clickOnTab(robban);
			//openNewTab(robban);
			writeNote(robban);
			//System.out.println( KeyEvent.getKeyText(58) );
			//System.out.println( KeyEvent.getExtendedKeyCodeForChar('t') );
			//System.out.println( KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar('t')) );
		} catch (AWTException e) {
			fail("Could not initiate: " + e.getMessage() );
			e.printStackTrace();
		}
	}
}
