package com.math.game;

import com.math.game.systems.InputSystem;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class PlayerInputListener extends InputAdapter{
	InputSystem input;
	
	public PlayerInputListener(InputSystem i) {
		input = i;
	}
	
	@Override
	public boolean keyDown (int keycode) {
		switch(keycode) {
			case(Keys.LEFT):
				input.xaccel = -25.0f;
				break;
			case(Keys.RIGHT):
				input.xaccel = 25.0f;
				break;
			case(Keys.UP):
				input.yaccel = 25.0f;
				break;
			case(Keys.DOWN):
				input.yaccel = -25.0f;
				break;
		}
		return true;
	}

	public boolean keyUp (int keycode) {
		switch(keycode) {
			case(Keys.LEFT):
				input.xaccel = 0.0f;
				break;
			case(Keys.RIGHT):
				input.xaccel = 0.0f;
				break;
			case(Keys.UP):
				input.yaccel = 0.0f;
				break;
			case(Keys.DOWN):
				input.yaccel = 0.0f;
				break;
		}
		return true;
	}
	
	public boolean touchDown (int x, int y, int pointer, int button) {
		input.newtarget = new Vector2(x, 480 - y);
		return true;
	}
	
	public boolean touchUp() {
		input.newtarget = null;
		return true;
	}
}
