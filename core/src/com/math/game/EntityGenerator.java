package com.math.game;

import com.math.game.components.Boundary;
import com.math.game.components.EntityName;
import com.math.game.components.Grid;
import com.math.game.components.GridBox;
import com.math.game.components.Position;
import com.math.game.components.TextureCom;
import com.math.game.components.Velocity;
import com.math.game.components.Size;
import com.math.game.components.InputCom;
import com.math.game.components.Pathfinding;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.Gdx;

public class EntityGenerator {
	
	public Entity createPlayerCharacter(float x, float y, float width, float height, String filename, String name) {
		EntityName eName = new EntityName();
		eName.name = name;
		
		Boundary boundary = new Boundary();
		boundary.boundary[0].x = x;
		boundary.boundary[0].y = y;
		boundary.boundary[1].x = x + width;
		boundary.boundary[1].y = y + height /  2;
		
		Position position = new Position();
		position.x = x;
		position.y = y;
		
		Size size = new Size();
		size.width = width;
		size.height = height;
		
		TextureCom texture = new TextureCom();
		texture.texture = new Texture(Gdx.files.internal(filename));
		
		Velocity velocity = new Velocity();
		InputCom input = new InputCom();
		Pathfinding path = new Pathfinding();
		Entity player = new Entity();
		
		player.add(boundary);
		player.add(position);
		player.add(size);
		player.add(texture);
		player.add(velocity);
		player.add(input);
		player.add(path);
		player.add(eName);
		
		return player;
	}
	
	public Entity createSetting(OrthographicCamera cam, float x, float y, float width, float height, String filename, String name) {
		EntityName eName = new EntityName();
		eName.name = name;
		
		Position position = new Position();
		position.x = x;
		position.y = y;
		
		Size size = new Size();
		size.width = width;
		size.height = height;
		
		TextureCom texture = new TextureCom();
		texture.texture = new Texture(Gdx.files.internal(filename));
		
		//Grid grid = createGrid(cam, 10);
		
		Entity setting = new Entity();
		
		setting.add(position);
		setting.add(size);
		setting.add(texture);
		setting.add(eName);
		//setting.add(grid);
		
		return setting;
	}
	
	public Entity createBoundaryObject(float x, float y, float width, float height, String name) {
		EntityName eName = new EntityName();
		eName.name = name;
		
		Boundary boundary = new Boundary();
		boundary.boundary[0].x = x;
		boundary.boundary[0].y = y;
		boundary.boundary[1].x = x + width;
		boundary.boundary[1].y = y + height;		
		Entity bound = new Entity();
		
		bound.add(boundary);
		bound.add(eName);
		
		return bound;
	}
	
	/*public Grid createGrid(OrthographicCamera cam, int boxside){
		Grid g = new Grid();
		
		float width = cam.viewportWidth;
		float height = cam.viewportHeight;
		
		for(int i = 0; i <= (width / boxside) * (height / boxside); i++) {
			GridBox gb = new GridBox();
			gb.gridcoord = new Vector2(i % (width / boxside), (float) Math.floor(i / (width / boxside)));
			gb.nodex = gb.gridcoord.x * boxside + boxside / 2;
			gb.nodey = gb.gridcoord.y * boxside + boxside / 2;
			gb.open = true;
			
			g.grid.add(gb);	
		}
		
		return g;
	}*/
}