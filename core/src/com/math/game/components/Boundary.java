package com.math.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class Boundary implements Component{
	public Vector2[] boundary = {new Vector2(0, 0), new Vector2(0,0)};
}
