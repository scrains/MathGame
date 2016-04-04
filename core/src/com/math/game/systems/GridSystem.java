package com.math.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.math.game.components.Boundary;
import com.math.game.components.Grid;
import com.math.game.components.GridBox;

public class GridSystem extends IntervalSystem {
	private ComponentMapper<Grid> gridmapper = ComponentMapper.getFor(Grid.class);
	private ComponentMapper<Boundary> boundmapper = ComponentMapper.getFor(Boundary.class);
	
	public Grid grid;
	
	ImmutableArray<Entity> boundentities;
	ImmutableArray<Entity> gridentities;
	
	public GridSystem(float interval) {
		super(interval);
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		boundentities = engine.getEntitiesFor(Family.all(Boundary.class).get());
		gridentities = engine.getEntitiesFor(Family.all(Grid.class).get());
	}

	@Override
	protected void updateInterval() {
		grid = new Grid();
		
		for(Entity entity: gridentities) {
			grid = gridmapper.get(entity);
		}
		
		for(Entity entity: boundentities) {
			Boundary bound = boundmapper.get(entity);
		
			for(GridBox gb: grid.grid) {
				BoundingBox gbound = new BoundingBox();
				gbound.set(new Vector3(gb.nodex - 5, gb.nodey - 5, 0), new Vector3(gb.nodex + 5, gb.nodey + 5, 0));
				
				BoundingBox bbound = new BoundingBox();
				bbound.set(new Vector3(bound.boundary[0], 0), new Vector3(bound.boundary[1], 0));
				
				if(gbound.contains(bbound)) {
					gb.open = false;
					System.out.println("GridBox " + gb.gridcoord + " is closed");
				}
			}
		}
	}
}