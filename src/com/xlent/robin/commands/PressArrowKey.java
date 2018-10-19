package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

import com.xlent.robin.Robin.ModifierKey;
import com.xlent.robin.commands.Command;
import com.xlent.robin.commands.MouseClick.MouseButton;

public class PressArrowKey extends Command {

	enum ArrowKey {
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
		keyPressed = (ArrowKey) args.getOrDefault("Key", this.keyPressed);
		times = (int) args.getOrDefault("Times", this.times);
		modifier = (ModifierKey) args.getOrDefault("ModifierKey", this.modifier);
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
