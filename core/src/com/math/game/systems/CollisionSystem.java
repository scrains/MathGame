package com.math.game.systems;

import com.math.game.components.Boundary;
import com.math.game.components.EntityName;
import com.math.game.components.Pathfinding;
import com.math.game.components.Position;
import com.math.game.components.Size;
import com.math.game.components.Velocity;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;

public class CollisionSystem extends EntitySystem {
	private ImmutableArray<Entity> boundentities;
	private ImmutableArray<Entity> movingentities;
	private Family targetfamily;
	
	ComponentMapper<Boundary> boundmapper = ComponentMapper.getFor(Boundary.class);
	ComponentMapper<Velocity> velocitymapper = ComponentMapper.getFor(Velocity.class);
	ComponentMapper<Position> positionmapper = ComponentMapper.getFor(Position.class);
	ComponentMapper<Size> sizemapper = ComponentMapper.getFor(Size.class);
	ComponentMapper<Pathfinding> pathmapper = ComponentMapper.getFor(Pathfinding.class);
	
	@Override
	public void addedToEngine(Engine engine) {
		boundentities = engine.getEntitiesFor(Family.all(Boundary.class, EntityName.class).get());
		movingentities = engine.getEntitiesFor(Family.all(Boundary.class, Velocity.class, EntityName.class).get());
		targetfamily = Family.all(Pathfinding.class).get();
	}
	
	@Override
	public void update(float deltaTime) {
		for(Entity e: movingentities) {
			Boundary b1 = boundmapper.get(e);

			Position position = positionmapper.get(e);
			Size size = sizemapper.get(e);
			
			b1.boundary[0].x = position.x;
			b1.boundary[0].y = position.y;
			b1.boundary[1].x = position.x + size.width;
			b1.boundary[1].y = position.y + size.height / 2;
						
			for(Entity e2: boundentities) {
				if(e.equals(e2)) {}
				else{
					Boundary b2 = boundmapper.get(e2);
										
					if(isOverlapping(b1.boundary, b2.boundary)) {
						Velocity v = velocitymapper.get(e);
						/*v.x = 0.0f;
						v.y = 0.0f;*/
						
						position.x = v.lastx;
						position.y = v.lasty;
						
						if(targetfamily.matches(e)) {
							Pathfinding path = pathmapper.get(e);
							path.target = null;
						}
					}
				}
			}
		}
	}
	
	public boolean isOverlapping(Vector2[] v1, Vector2[] v2) {
		if(v1[0].x < v2[1].x && v1[1].x > v2[0].x && v1[0].y < v2[1].y && v1[1].y > v2[0].y) return true;
		return false;
	}
}
