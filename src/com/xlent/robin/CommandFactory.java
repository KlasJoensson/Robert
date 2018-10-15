package com.xlent.robin;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.xlent.robin.commands.Command;
import com.xlent.robin.commands.DragDropTo;
import com.xlent.robin.commands.MoveMouseTo;
import com.xlent.robin.commands.ScrollMouseWheel;

public class CommandFactory {

	public CommandFactory() {
		
	}
	
	/**
	 * Returns a map with the different commands name sorted in categories.
	 * 
	 * @return The name of the commands
	 */
	//TODO Add commands for all methods
	public static HashMap<String, ArrayList<String>> getCommandsAsTree() {
		HashMap<String, ArrayList<String>> commandTree = new HashMap<>();
		
		ArrayList<String> mouseActions = new ArrayList<String>();
		mouseActions.add("Click left button");
		mouseActions.add("Click wheel button");
		mouseActions.add("Click right button");
		mouseActions.add("Double Click");
		commandTree.put("Mouse button", mouseActions);
		
		ArrayList<String> mouseMoves = new ArrayList<String>();
		mouseMoves.add("Move to");
		mouseMoves.add("Drag drop to");
		mouseMoves.add("Scroll wheel");
		commandTree.put("Move mouse", mouseMoves);
		
		ArrayList<String> keyboardActions = new ArrayList<String>();
		keyboardActions.add("Use short cut");
		keyboardActions.add("Press arrowkey");
		commandTree.put("Keyboard", keyboardActions);
		
		ArrayList<String> textActions = new ArrayList<String>();
		textActions.add("Write text");
		commandTree.put("Other", textActions);
		
		return commandTree;
	}
	
	public Command createCommand(String name, Map<String, Object> args) throws AWTException {

		Command command;
		switch (name) {
		case "Move to":
			command = new MoveMouseTo();
			break;
		case "Scroll wheel":
			command = new ScrollMouseWheel();
		case "Drag drop to":
			command = new DragDropTo();
		default:
			return null;
		}
		
		command.changeParameters(args);
		
		return command;
	}
}

