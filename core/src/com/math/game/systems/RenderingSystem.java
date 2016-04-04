package com.math.game.systems;

import com.math.game.components.Position;
import com.math.game.components.TextureCom;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class RenderingSystem extends EntitySystem {
	
	private OrthographicCamera cam;
	private SpriteBatch batch;
	
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<Position> positionmapper = ComponentMapper.getFor(Position.class); 
	private ComponentMapper<TextureCom> texturemapper = ComponentMapper.getFor(TextureCom.class);
	
	public RenderingSystem(OrthographicCamera cam) {
		
		batch = new SpriteBatch();
		
		this.cam = cam;
		
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(Position.class, TextureCom.class).get());
	}
	
	@Override
	public void removedFromEngine (Engine engine) {
		entities = engine.getEntitiesFor(Family.all(Position.class, TextureCom.class).get());
	}	
	
	@Override
	public void update(float deltaTime) {		
		Position position;
		TextureCom texture;
		
		cam.update();
		
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		for (Entity entity : entities) {
			position = positionmapper.get(entity);
			texture = texturemapper.get(entity);
			
			batch.draw(texture.texture, position.x, position.y);
		}
		batch.end();
	}
}
