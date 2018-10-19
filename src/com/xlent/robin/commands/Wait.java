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
		ms = (int) args.getOrDefault("Time", ms);
		arguments.put("Time (ms)", ms);
	}

	@Override
	public void run() {
		robban.wait(ms);
	}

}
