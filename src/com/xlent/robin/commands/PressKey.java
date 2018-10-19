package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

import com.xlent.robin.Robin;
import com.xlent.robin.Robin.ModifierKey;

public class PressKey extends Command {

	private char key;
	private Robin.ModifierKey[] modifierKeys;
	
	public PressKey(String name) throws AWTException {
		super(name);
		key = ' ';
		modifierKeys = new Robin.ModifierKey[3];
	}

	@Override
	public void changeParameters(Map<String, Object> args) {
		Object keyObj = args.get("Key");
		if (keyObj instanceof char[]) {
			key = (char) keyObj;
		} else if (keyObj instanceof String) {
			key = (char) ((String)keyObj).charAt(0);
		}
	
		modifierKeys[0] = (ModifierKey) args.getOrDefault("ModifierKey", ModifierKey.NON);
		modifierKeys[0] = (ModifierKey) args.getOrDefault("ModifierKey0", ModifierKey.NON);
		modifierKeys[1] = (ModifierKey) args.getOrDefault("ModifierKey1", ModifierKey.NON);
		modifierKeys[2] = (ModifierKey) args.getOrDefault("ModifierKey2", ModifierKey.NON);

	}

	@Override
	public void run() {
		robban.pressKey(key, modifierKeys);
	}

}
