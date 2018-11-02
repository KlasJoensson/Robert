package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

import com.xlent.robin.commands.Command;

public class WriteText extends Command {

	private String text;
	
	public WriteText() throws AWTException {
		super("Write text");
		text = "";
		arguments.put("Text to write", text);
	}

	@Override
	public void changeParameters(Map<String, String> args) {
		text = (String) args.getOrDefault("Text to write", text);
		arguments.put("Text to write", text);
	}

	@Override
	public void run() {
		robban.write(text);
	}

}
