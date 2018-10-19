package com.xlent.robin;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.xlent.robin.commands.Command;
import com.xlent.robin.commands.DragDropTo;
import com.xlent.robin.commands.MouseClick;
import com.xlent.robin.commands.MoveMouseTo;
import com.xlent.robin.commands.PressArrowKey;
import com.xlent.robin.commands.PressKey;
import com.xlent.robin.commands.ScrollMouseWheel;
import com.xlent.robin.commands.Wait;
import com.xlent.robin.commands.WriteText;

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
		textActions.add("Wait");
		commandTree.put("Other", textActions);
		
		return commandTree;
	}
	
	public Command createCommand(String name, Map<String, Object> args) throws AWTException {
		Command command = createCommand(name);
		command.changeParameters(args);
		
		return command;
	}
	
	public Command createCommand(String name) throws AWTException {

		Command command;
		switch (name) {
		case "Move to":
			command = new MoveMouseTo();
			break;
		case "Scroll wheel":
			command = new ScrollMouseWheel();
			break;
		case "Drag drop to":
			command = new DragDropTo();
			break;
		case "Click left button":
		case "Click wheel button":
		case "Click right button":
		case "Double Click":
			command = new MouseClick(name);
			break;
		case "Press arrowkey":
			command = new PressArrowKey(name);
			break;
		case "Use short cut":
			command = new PressKey(name);
			break;
		case "Write text":
			command = new WriteText();
			break;
		case "Wait":
			command = new Wait();
			break;
		default:
			return null;
		}
		
		return command;
	}
}

