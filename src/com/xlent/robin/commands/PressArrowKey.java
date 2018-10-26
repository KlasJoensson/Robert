package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

import com.xlent.robin.Robin;
import com.xlent.robin.Robin.ModifierKey;
import com.xlent.robin.commands.Command;
import com.xlent.robin.commands.MouseClick.MouseButton;

public class PressArrowKey extends Command {

	public enum ArrowKey {
		DOWN,
		LEFT,
		NON,
		RIGHT,
		UP
	}
	
	private ArrowKey keyPressed;
	private int times;
	private ModifierKey modifier;
	
	public PressArrowKey(String name) throws AWTException {
		super(name);
		setKeyPressedFromName(name);
		times = 1;
		modifier = ModifierKey.NON;
		arguments.put("Key", keyPressed);
		arguments.put("Times", times);
		arguments.put("ModifierKey", modifier);
		argumentMap.put("Arrow up", ArrowKey.UP);
		argumentMap.put("Arrow down", ArrowKey.DOWN);
		argumentMap.put("Arrow right", ArrowKey.RIGHT);
		argumentMap.put("Arrow left", ArrowKey.LEFT);
	}

	private void setKeyPressedFromName(String name) {
		if (name.toLowerCase().contains("up")) {
			keyPressed = ArrowKey.UP;
		} else if (name.toLowerCase().contains("left")) {
			keyPressed = ArrowKey.LEFT;
		} else if (name.toLowerCase().contains("right")) {
			keyPressed = ArrowKey.RIGHT;
		} else if (name.toLowerCase().contains("down")) {
			keyPressed = ArrowKey.DOWN;
		} else {
			keyPressed = ArrowKey.NON;
		}
	}
	
	@Override
	public void changeParameters(Map<String, Object> args) {
		Object kpObj = args.get("Arrow Key");
		if (kpObj instanceof ArrowKey) {
			keyPressed = (ArrowKey) kpObj;
		} else if (kpObj instanceof String) {
			setKeyPressedFromName( (String) kpObj );
		}
		Object tObj = args.getOrDefault("Times", this.times);
		if (tObj instanceof Integer) {
			times = (int) tObj;
		}
		
		Object modObj = args.get("ModifierKey");
		if(modObj instanceof ModifierKey) {
			modifier = (ModifierKey) modObj;
		} else if(modObj instanceof String) {
			modifier = Robin.getModifierKeys().getOrDefault((String) modObj, ModifierKey.NON);
		}
		arguments.put("Key", keyPressed);
		arguments.put("Times", times);
		arguments.put("ModifierKey", modifier);		
	}
	
	@Override
	public void run() {
		switch (keyPressed) {
		case UP:
			robban.pressArrowUp(times, modifier);
			break;
		case DOWN:
			robban.pressArrowDown(times, modifier);
			break;
		case LEFT:
			robban.pressArrowLeft(times, modifier);
			break;
		case RIGHT:
			robban.pressArrowRight(times, modifier);
			break;
		case NON:
		default:
			break;
		}
	}
	
}
