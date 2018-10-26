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
		this.arguments.put("Key", key);
		this.arguments.put("ModifierKey0", modifierKeys[0]);
		this.arguments.put("ModifierKey1", modifierKeys[1]);
		this.arguments.put("ModifierKey2", modifierKeys[2]);
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
		Object modObj = args.get("ModifierKey0");
		if(modObj instanceof ModifierKey) {
			modifierKeys[0] = (ModifierKey) modObj;
		} else if(modObj instanceof String) {
			modifierKeys[0] = Robin.getModifierKeys().getOrDefault((String) modObj, ModifierKey.NON);
		}
		modObj = args.get("ModifierKey1");
		if(modObj instanceof ModifierKey) {
			modifierKeys[1] = (ModifierKey) modObj;
		} else if(modObj instanceof String) {
			modifierKeys[1] = Robin.getModifierKeys().getOrDefault((String) modObj, ModifierKey.NON);
		}
		modObj = args.get("ModifierKey2");
		if(modObj instanceof ModifierKey) {
			modifierKeys[2] = (ModifierKey) modObj;
		} else if(modObj instanceof String) {
			modifierKeys[2] = Robin.getModifierKeys().getOrDefault((String) modObj, ModifierKey.NON);
		}
		
		this.arguments.put("Key", key);
		this.arguments.put("ModifierKey0", modifierKeys[0]);
		this.arguments.put("ModifierKey1", modifierKeys[1]);
		this.arguments.put("ModifierKey2", modifierKeys[2]);
	}

	@Override
	public void run() {
		robban.pressKey(key, modifierKeys);
	}

}
