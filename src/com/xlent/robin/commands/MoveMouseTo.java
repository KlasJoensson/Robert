package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

public class MoveMouseTo extends Command {

	private int x;
	private int y;
	
	public MoveMouseTo() throws AWTException {
		super("Move to");
		this.x = 42;
		this.y = 42;
	}

	public MoveMouseTo(int x, int y) throws AWTException {
		super("Move to");
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void changeParameters(Map<String, Object> args) {
		Object xObj = args.get("x");
		Object yObj = args.get("y");
		if (xObj instanceof Integer && yObj instanceof Integer) {
			x = (int) xObj;
			y = (int) yObj;
		}
	}

	@Override
	public void run() {
		robban.moveMouseTo(x, y);
	}

}
