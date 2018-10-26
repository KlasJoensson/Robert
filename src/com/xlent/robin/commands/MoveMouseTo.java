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
		this.arguments.put("x", x);
		this.arguments.put("y", y);
	}

	public MoveMouseTo(int x, int y) throws AWTException {
		super("Move to");
		this.x = x;
		this.y = y;
		this.arguments.put("x", x);
		this.arguments.put("y", y);
	}
	
	@Override
	public void changeParameters(Map<String, Object> args) {
		Object xObj = args.get("x");
		Object yObj = args.get("y");
		if (xObj instanceof Integer) {
			x = (int) xObj;
		} else if (xObj instanceof String)  {
			x = Integer.parseInt((String) xObj);
		}
		if (yObj instanceof Integer) {
			y = (int) yObj;
		} else if (yObj instanceof String)  {
			y = Integer.parseInt((String) yObj);
		}
		this.arguments.put("x", x);
		this.arguments.put("y", y);
	}

	@Override
	public void run() {
		robban.moveMouseTo(x, y);
	}

}
