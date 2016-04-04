package com.math.game.components;

import java.util.ArrayList;

import com.badlogic.ashley.core.Component;

public class Grid implements Component {
	public ArrayList<GridBox> grid = new ArrayList<GridBox>();
}
