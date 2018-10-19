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
		
	}

	@Override
	public void changeParameters(Map<String, Object> args) {
		type = (MouseButton) args.getOrDefault("Button", MouseButton.NON);
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
