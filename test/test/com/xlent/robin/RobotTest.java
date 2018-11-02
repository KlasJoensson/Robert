package test.com.xlent.robin;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;

import org.junit.jupiter.api.Test;

import com.xlent.robin.Robin;

class RobotTest {

	Robin robot;
	
	private void instanciateRobot() {
		try {
			robot = Robin.getInstance();			
		} catch (AWTException e) {
			fail("Could not initiate: " + e.getMessage() );
			e.printStackTrace();
		}

	}
	
	/**
	 * Tests to initiate the robot.
	 */
	@Test
	void testGetInstance() {
		instanciateRobot();
		assertNotNull(robot);
	}
	
	/**
	 * Tests if it moves the mouse to a point.
	 */
	@Test
	void testMoveMouseTo() {
		instanciateRobot();
		robot.moveMouseTo(100, 100);
		Point location = MouseInfo.getPointerInfo().getLocation();
		assertAll("Move mouse to [100 ,100]", 
				() -> assertEquals(100, location.x), 
				() -> assertEquals(100, location.y) );
	}
	
	/**
	 * Tests to move the mouse to point relative to its previous point.
	 */
	@Test
	void testMoveMouse() {
		instanciateRobot();
		Point beforeMove = MouseInfo.getPointerInfo().getLocation();
		robot.moveMouse(50, 25);
		Point afterMove = MouseInfo.getPointerInfo().getLocation();
		assertAll("Move mouse 50 points along x-axis and 25 along the y-axis", 
				() -> assertEquals(beforeMove.x + 50, afterMove.x), 
				() -> assertEquals(beforeMove.y + 25, afterMove.y) );
	}
	
}
