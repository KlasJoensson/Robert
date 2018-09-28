package com.xlent.robin;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;

import com.xlent.robin.Robin.ModifierKey;

/**
 * This class is for making use of the little robot Robin.
 * 
 * @author Klas Jönsson
 */
public class RunRobin {
	
	private static void dragDropFile(Robin robban) {
		robban.moveMouseTo(1200, 100);
		robban.leftClick();
		robban.moveMouseTo(1200, 190);
		//robban.dragDrop(-110, 110);
		robban.dragDropTo(875, 300);
		//robban.moveMouseTo(875, 300);
	}
	
	private static void clickOnTab(Robin robban) {
		robban.moveMouseTo(200, 50);
	}
	
	private static void openMail(Robin robban) {
		robban.moveMouseTo(200, 50);
		robban.leftClick();
		robban.moveMouseTo(360, 325);
		robban.rightClick();
		robban.moveMouse(30, 10);
		robban.leftClick();
	}
	
	private static void openNewTab(Robin robban) {
		robban.moveMouseTo(1400, 60);
		//robban.leftClick();
		//robban.moveMouse(0, 35);
		robban.leftClick();
		robban.pressKey('t', Robin.ModifierKey.COMMAND);
		robban.write("smp.se\n");
		robban.moveMouse(0, 200);
		robban.wait(5000);
		robban.scrollDown(20, 50);
		robban.scrollUp(10, 0);
	}
	
	private static void openFromDock(Robin robban) {
		robban.moveMouseTo(1000, 1048);
		robban.wait(500);
		robban.moveMouse(0, -35);
		robban.wait(500);
		robban.leftClick();
	}
	
	private static void writeNote(Robin robban) {
		robban.moveMouseTo(600, 1000);
		robban.leftClick();
		robban.pressKey('k', Robin.ModifierKey.SHIFT);
		robban.pressKey(' ');
		Robin.ModifierKey[] mk = {Robin.ModifierKey.SHIFT, Robin.ModifierKey.ALT};
		robban.pressKey('9', mk);
		
		//robban.write("Klas Jönsson \\ ,. ;:{}[] ©");
		//robban.write("1 ' 2 - 3 ^ 4 _ 5 ` 6 ~");//! \" # $ % & ' ( ) * + , - . /");
		//robban.write("Testar @ 1 \\ 2 | 3 /");
		//robban.write("Testar 1 / 2 [ 3 \\ 4 ]5 ^ 6 _ 7 `"); // 1 - 2 å 3 ' 4 ¨5  6  7 <
	}
	
	private static void moveCursor(Robin robban) {
		robban.moveMouseTo(2500, -100);
		robban.leftClick();
		robban.pressArrowDown(5);
		robban.pressArrowRight(10, ModifierKey.SHIFT);
	}
	
	
	
	public static void main(String[] args) {
		try {
			Robin robban = Robin.getInstance();
			robban.openApp("Schack");
			//dragDropFile(robban);
			
			/*Preferences test = new Preferences();
			Map<String, String> appMap = test.getMap();
			for (String key:appMap.keySet()) {
				System.out.println(key+" => "+appMap.get(key));
			}*/
			/*String testStr = test.getTranslatedAppName("Schack");
			if (testStr != null && !testStr.isEmpty() ) {
				System.out.println("Found it! ("+testStr+").");
			} else {
				System.out.println("Did not found it... ");
			}*/
				
			
			//openMail(robban);
			//clickOnTab(robban);
			//openNewTab(robban);
			//openFromDock(robban);
			//writeNote(robban);
			//moveCursor(robban);
			/* Just for checking my screen sizes, witch is: 1680x1050 px
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width0 = screenSize.getWidth();
			double height0 = screenSize.getHeight();
			System.out.println("With Toolkit:");
			System.out.println("Width: " + width0);
			System.out.println("height: " + height0);
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width1 = gd.getDisplayMode().getWidth();
			int height1 = gd.getDisplayMode().getHeight();
			System.out.println("With GraphicsEnvironment:");
			System.out.println("Width: " + width1);
			System.out.println("height: " + height1);*/		
			//System.out.println( KeyEvent.getKeyText(58) );
			//System.out.println( KeyEvent.getExtendedKeyCodeForChar('t') );
			//System.out.println( KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar('t')) );
			/*try {
				robban.openApp("Google Chrome");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		} catch (AWTException e) {
			fail("Could not initiate: " + e.getMessage() );
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
