package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;
import java.util.Set;

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

	@Override
	public String toString() {
		StringBuilder comandStr = new StringBuilder( getName() );
		comandStr.append(" [ ");
		Set<String> args = arguments.keySet();
		for(String arg:args) {
			comandStr.append(arg);
			comandStr.append("=");
			if (text.length() > 10) {
				comandStr.append( text.substring(0, 8).replaceAll("\n", "|") );
				comandStr.append("[...]");
			} else {
				comandStr.append( text.replaceAll("\n", "|") );	
			}
			comandStr.append("; ");
		}
		comandStr.append("]");
		
		return comandStr.toString();
	}
}
