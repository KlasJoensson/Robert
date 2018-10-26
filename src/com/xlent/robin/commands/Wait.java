package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

import com.xlent.robin.commands.Command;

public class Wait extends Command {

	private int ms;
	
	public Wait() throws AWTException {
		super("Wait");
		ms = 42;
		arguments.put("Time (ms)", ms);
	}

	@Override
	public void changeParameters(Map<String, Object> args) {
		Object arg = args.get("Time (ms)");
		if (arg instanceof Integer) {
			ms = (int) arg;
		} else if (arg instanceof String) {
			ms = Integer.parseInt((String)arg);
		}
		arguments.put("Time (ms)", ms);
	}

	@Override
	public void run() {
		robban.wait(ms);
	}

}
