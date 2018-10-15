package com.xlent.robin.commands;

import java.awt.AWTException;
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
	
	public Command(String name) throws AWTException {
		this.name = name;
		robban = Robin.getInstance();
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	public abstract void changeParameters(Map<String, Object> args);
	
	public abstract void run();
}
