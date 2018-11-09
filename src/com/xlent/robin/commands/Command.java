package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.xlent.robin.Robin;

/**
 * A super class for the different commands.
 * 
 * @author Klas Jönsson
 *
 */
public abstract class Command {

	private String name;
	protected Robin robban;
	protected Map<String, Object> arguments = new HashMap<>();
	protected Map<String, Object> argumentMap = new HashMap<>();
	
	public Command(String name) throws AWTException {
		this.name = name;
		robban = Robin.getInstance();
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, Object> getArgunments() {
		return arguments;
	}
	
	public Map<String, Object> getArgumentAlternetivs() {
		return argumentMap;
	}
	
	public String toString() {
		StringBuilder comandStr = new StringBuilder(name);
		comandStr.append(" [ ");
		Set<String> args = arguments.keySet();
		for(String arg:args) {
			comandStr.append(arg);
			comandStr.append("=");
			comandStr.append( arguments.get(arg) );
			comandStr.append("; ");
		}
		comandStr.append("]");
		
		return comandStr.toString();
	}
	
	public abstract void changeParameters(Map<String, String> args);
	
	public abstract void run();
}
