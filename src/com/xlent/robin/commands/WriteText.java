package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

import com.xlent.robin.commands.Command;

public class WriteText extends Command {

	private String text;
	
	public WriteText() throws AWTException {
		super("Write text");
		text = "";
	}

	@Override
	public void changeParameters(Map<String, Object> args) {
		text = (String) args.getOrDefault("Text", text);
	}

	@Override
	public void run() {
		robban.write(text);
	}

}
