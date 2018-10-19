package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

public class ScrollMouseWheel extends Command {

	private int notches;
	
	public ScrollMouseWheel() throws AWTException {
		super("Scroll wheel");
		notches = 42;
		arguments.put("Notches", notches);
	}

	public ScrollMouseWheel(int notches) throws AWTException {
		super("Scroll wheel");
		this.notches = notches;
		arguments.put("Notches", notches);
	}
	
	@Override
	public void changeParameters(Map<String, Object> args) {
		Object notchesObj = args.get("notches");
		if (notchesObj instanceof Integer) {
			this.notches = (int) notchesObj;
		}
	}

	@Override
	public void run() {
		if (notches >= 0) {
			robban.scrollDown(notches);
		} else {
			robban.scrollUp(-1 * notches);
		}
	}

}
