package com.math.game.systems;

import com.math.game.components.Position;
import com.math.game.components.Velocity;

import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;

public class MovementSystem extends IteratingSystem {
	private ComponentMapper<Position> positionmapper = ComponentMapper.getFor(Position.class);
	private ComponentMapper<Velocity> velocitymapper = ComponentMapper.getFor(Velocity.class);

	public MovementSystem() {
		super(Family.all(Position.class, Velocity.class).get());
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		Position position = positionmapper.get(entity);
		Velocity velocity = velocitymapper.get(entity);
		
		velocity.lastx = position.x;
		velocity.lasty = position.y;
		
		position.x += velocity.x * deltaTime;
		position.y += velocity.y * deltaTime;	
	}
}
