package com.xlent.robin.commands;

import java.awt.AWTException;
import java.util.Map;

public class MouseClick extends Command {
	
	enum MouseButton {
		LEFT,
		MIDDLE,
		WHEEL,
		RIGHT,
		DOUBLE_CLICK,
		NON
	}
	
	MouseButton type;
	
	public MouseClick(String name) throws AWTException {
		super(name);
		setTypeByName(name);
		
		arguments.put("Button", type);
		argumentMap.put("Left button", MouseButton.LEFT);
		argumentMap.put("Right button", MouseButton.RIGHT);
		argumentMap.put("Wheel button", MouseButton.WHEEL);
		argumentMap.put("Middle button", MouseButton.MIDDLE);
		argumentMap.put("Dobble click", MouseButton.DOUBLE_CLICK);
	}

	@Override
	public void changeParameters(Map<String, Object> args) {
		Object arg = args.get("Button");
		if (arg instanceof MouseButton) {
			type = (MouseButton) arg;
		} else if (arg instanceof String) {
			setTypeByName((String) arg);
		}
		arguments.put("Button", type);
	}

	private void setTypeByName(String name) {
		if (name.toLowerCase().contains("double")) {
			type = MouseButton.DOUBLE_CLICK;
		} else if (name.toLowerCase().contains("left")) {
			type = MouseButton.LEFT;
		} else if (name.toLowerCase().contains("right")) {
			type = MouseButton.RIGHT;
		} else if (name.toLowerCase().contains("wheel")) {
			type = MouseButton.WHEEL;
		} else if (name.toLowerCase().contains("middle")) {
			type = MouseButton.MIDDLE;
		} else {
			type = MouseButton.NON;
		}
	}
	
	@Override
	public void run() {
		switch(type) {
		case DOUBLE_CLICK:
			robban.dubbleClick();
			break;
		case LEFT:
			robban.leftClick();
			break;
		case RIGHT:
			robban.rightClick();
			break;
		case MIDDLE:
		case WHEEL:
			robban.wheelClick();
			break;
		case NON:
		default:
			break;
		}
	}

}
