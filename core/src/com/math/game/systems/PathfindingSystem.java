package com.math.game.systems;

import java.util.ArrayList;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.math.game.components.Boundary;
import com.math.game.components.GridBox;
import com.math.game.components.Position;
import com.math.game.components.Velocity;
import com.math.game.components.Pathfinding;

public class PathfindingSystem extends IteratingSystem {
		
	private ComponentMapper<Position> positionmapper = ComponentMapper.getFor(Position.class);	
	private ComponentMapper<Velocity> velocitymapper = ComponentMapper.getFor(Velocity.class);
	private ComponentMapper<Pathfinding> pathmapper = ComponentMapper.getFor(Pathfinding.class);
	
	private ImmutableArray<Entity> boundentities;
		
	public PathfindingSystem() {
		super(Family.all(Pathfinding.class).get());
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		Position position = positionmapper.get(entity);
		Velocity velocity = velocitymapper.get(entity);
		Pathfinding path = pathmapper.get(entity);
		
		/*boundentities = super.getEngine().getEntitiesFor(Family.all(Boundary.class).get());
		
		Vector3 pos = new Vector3(position.x, position.y, 0);
		Vector2 thisBox = new Vector2(99999, 99999);
		Vector2 targetBox = new Vector2(99999, 99999); 
		
		ArrayList<GridBox> openlist = new ArrayList<GridBox>();*/
		
		Vector2 target = path.target;
		
		//GridBox nextBox = new GridBox();

			/*for(GridBox gb : gs.grid.grid) {
				BoundingBox bound = new BoundingBox();
				bound.set(new Vector3(gb.nodex - 5, gb.nodey - 5, 0), new Vector3(gb.nodex + 5, gb.nodey + 5, 0));
			
				if(bound.contains(new Vector3(target, 0))) targetBox = gb.gridcoord;
				if(bound.contains(pos)){ 
					openlist.add(gb);
					thisBox = gb.gridcoord;
				} else if(thisBox.dst(gb.gridcoord) < 2){
					openlist.add(gb);
				}
			
				for(Entity e: boundentities) {
					Boundary b = e.getComponent(Boundary.class);
					if(bound.contains(new BoundingBox(new Vector3(b.boundary[0], 0), new Vector3(b.boundary[1], 0)))) {
						gb.open = false;
					}
				}
			}
		
			for(GridBox gb: openlist) {
				if(gb.gridcoord.x == thisBox.x || gb.gridcoord.y == thisBox.y) {
					gb.score = 10 + Math.abs(gb.gridcoord.x - targetBox.x) * 10 + Math.abs(gb.gridcoord.y - targetBox.y) * 10;
				} else {
					gb.score = 14 + Math.abs(gb.gridcoord.x - targetBox.x) * 10 + Math.abs(gb.gridcoord.y - targetBox.y) * 10;
				}
			}
		
			float score = 1000000000;
		
			for(GridBox gb: openlist) {
				if(gb.score < score) {
					nextBox = gb;
					score = gb.score;
				}
			}*/			
		if(target != null){

			if(target.x > position.x + 25.0f) velocity.x += 25.0f;
				else if(target.x > position.x + 15.0f) velocity.x += 15.0f;
					else if(target.x > position.x + 5.0f) velocity.x += 5.0f;
						else if(target.x > position.x + 2.5f) velocity.x += 2.5f;

			if(target.x < position.x - 25.0f) velocity.x -= 25.0f;
				else if(target.x < position.x - 15.0f) velocity.x -= 15.0f;
					else if(target.x < position.x - 5.0f) velocity.x -= 5.0f;
						else if(target.x < position.x - 2.5f) velocity.x -= 2.5f;
				
			if(target.y > position.y + 25.0f) velocity.y += 25.0f;
				else if(target.y > position.y + 15.0f) velocity.y += 15.0f;
					else if(target.y > position.y + 5.0f) velocity.y += 5.0f;
						else if(target.y > position.y + 2.5f) velocity.y += 2.5f;		
		
			if(target.y < position.y) velocity.y -= 25.0f;
				else if(target.y < position.y - 15.0f) velocity.y -= 15.0f;
					else if(target.y < position.y - 5.0f) velocity.y -= 5.0f;
						else if(target.y < position.y - 2.5f) velocity.y -= 2.5f;		
		
			if(target.dst(position.x, position.y) < 10.0f) {
				velocity.x = 0.0f;
				velocity.y = 0.0f;
				path.target = null;
			}
		
			if(velocity.x > 250.0f) velocity.x = 250.0f;
			if(velocity.x < -250.0f) velocity.x = -250.0f;
			if(velocity.y > 250.0f) velocity.y = 250.0f;
			if(velocity.y < -250.0f) velocity.y  = -250.0f;	
		}
	}
}