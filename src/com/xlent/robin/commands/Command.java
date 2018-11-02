package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return name;
	}
	
	public abstract void changeParameters(Map<String, String> args);
	
	public abstract void run();
}
