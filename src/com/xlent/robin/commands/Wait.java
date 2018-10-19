package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

import com.xlent.robin.commands.Command;

public class Wait extends Command {

	private int ms;
	
	public Wait() throws AWTException {
		super("Wait");
		ms = 42;
	}

	@Override
	public void changeParameters(Map<String, Object> args) {
		ms = (int) args.getOrDefault("Time", ms);
		ms = 1000 * ( (int) args.getOrDefault("Time in sec", (ms/1000)) );
		ms = 60000 * ( (int) args.getOrDefault("Time in min", (ms/60000)) );
	}

	@Override
	public void run() {
		robban.wait(ms);
	}

}
