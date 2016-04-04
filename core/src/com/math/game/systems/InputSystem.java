package com.math.game.systems;

import com.math.game.components.Velocity;
import com.math.game.components.InputCom;
import com.math.game.components.Pathfinding;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class InputSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<Velocity> velocitymapper = ComponentMapper.getFor(Velocity.class);
	private ComponentMapper<Pathfinding> pathmapper = ComponentMapper.getFor(Pathfinding.class);
	
	public float xaccel;
	public float yaccel;
	
	public Vector2 newtarget = null;
	
	public InputSystem() {
		xaccel = 0.0f;
		yaccel = 0.0f;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(InputCom.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		
		for(Entity e : entities) {
			Pathfinding path = pathmapper.get(e);
			Velocity velocity = velocitymapper.get(e);
			
			//if(path.target != null) System.out.println("Target x: " + path.target.x + " Target y: " + path.target.y);
			//else System.out.println("Target is null");

			if(newtarget != null) path.target = newtarget;
			//else System.out.println("Newtarget is null");
			newtarget = null;
						
			//Slow down
			if(velocity.x > 0) velocity.x -= 10.0f;
			if(velocity.x < 0) velocity.x += 10.0f;
			if(velocity.y > 0) velocity.y -= 10.0f;
			if(velocity.y < 0) velocity.y += 10.0f;
			
			velocity.x += xaccel;
			velocity.y += yaccel;
			
			if(velocity.x > 250.0f) velocity.x = 250.0f;
			if(velocity.x < -250.0f) velocity.x = -250.0f;
			if(velocity.x < 10.0f && velocity.x > -10.0f) velocity.x = 0;
			if(velocity.y > 250.0f) velocity.y = 250.0f;
			if(velocity.y < -250.0f) velocity.y  = -250.0f;	
			if(velocity.y < 10.0f && velocity.y > -10.0f) velocity.y = 0;
			
		}
	}
}
