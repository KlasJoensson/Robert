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
	public void changeParameters(Map<String, String> args) {
	
		ms = Integer.parseInt(args.getOrDefault("Time (ms)", "" + ms));
		
		arguments.put("Time (ms)", ms);
	}

	@Override
	public void run() {
		robban.wait(ms);
	}

}
