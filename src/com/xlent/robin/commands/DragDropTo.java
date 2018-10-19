package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

public class DragDropTo extends Command {

	private int x;
	private int y;
	
	public DragDropTo() throws AWTException {
		super("Drag-drop to");
		this.x = 42;
		this.y = 42;
		this.arguments.put("x", x);
		this.arguments.put("y", y);
	}

	public DragDropTo(int x, int y) throws AWTException {
		super("Drag-drop to");
		this.x = x;
		this.y = y;
		this.arguments.put("x", x);
		this.arguments.put("y", y);
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
		robban.dragDrop(x, y);
	}

}
