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
		modifierKeys[0] = ModifierKey.NON;
		modifierKeys[1] = ModifierKey.NON;
		modifierKeys[2] = ModifierKey.NON;
		this.arguments.put("Key", key);
		this.arguments.put("ModifierKey0", ModifierKey.NON);
		this.arguments.put("ModifierKey1", ModifierKey.NON);
		this.arguments.put("ModifierKey2", ModifierKey.NON);
	}

	@Override
	public void changeParameters(Map<String, String> args) {
		String newKey = args.getOrDefault("Key", "" + key);
		key = newKey.charAt(0);
	
		modifierKeys[0] = Robin.getModifierKeys().getOrDefault(args.getOrDefault("Modifier Key", modifierKeys[0].toString()), ModifierKey.NON);
		modifierKeys[1] = Robin.getModifierKeys().getOrDefault(args.getOrDefault("Modifier Key", modifierKeys[1].toString()), ModifierKey.NON);
		modifierKeys[2] = Robin.getModifierKeys().getOrDefault(args.getOrDefault("Modifier Key", modifierKeys[2].toString()), ModifierKey.NON);
		
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
