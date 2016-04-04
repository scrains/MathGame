package com.math.game;

import com.math.game.EntityGenerator;
import com.math.game.PlayerInputListener;
import com.math.game.systems.CollisionSystem;
import com.math.game.systems.GridSystem;
import com.math.game.systems.RenderingSystem;
import com.math.game.systems.InputSystem;
import com.math.game.systems.MovementSystem;
import com.math.game.systems.PathfindingSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MathGame extends ApplicationAdapter {

	private EntityGenerator entitygen;
	
	private Entity player;
	private Entity setting;
	private Entity wall;
	private Entity bottom;
	private Entity left;
	private Entity right;
	
	private RenderingSystem render;
	private InputSystem input;
	private MovementSystem move;
	private GridSystem grid;
	private PathfindingSystem path;
	private CollisionSystem collision;
	
	private Engine engine;
	
	private PlayerInputListener il;
	private OrthographicCamera cam;
				
	@Override
	public void create () {
				
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);
		cam.position.set(cam.viewportWidth / 2.0f, cam.viewportHeight / 2.0f, 0);
		
		input = new InputSystem();
		render = new RenderingSystem(cam);
		move = new MovementSystem();
		grid = new GridSystem(2.0f);
		path = new PathfindingSystem();
		collision = new CollisionSystem();

		entitygen = new EntityGenerator();
		
		setting = entitygen.createSetting(cam, 0, 0, 800, 480, "TestSetting.png", "TestSetting");
		player = entitygen.createPlayerCharacter(30, 30, 40, 60, "p1_front.png", "Player");
		wall = entitygen.createBoundaryObject(0, 215, 900, 480 - 215, "Wall");
		bottom = entitygen.createBoundaryObject(0, 0, 900, 3, "Bottom");
		left = entitygen.createBoundaryObject(0, 0, 3, 480, "Left");
		right = entitygen.createBoundaryObject(800 - 20, 0, 3, 480, "Right");
		
		engine = new Engine();
		engine.addEntity(setting);
		engine.addEntity(player);
		engine.addEntity(wall);
		engine.addEntity(bottom);
		engine.addEntity(left);
		engine.addEntity(right);
		//engine.addSystem(grid);
		engine.addSystem(render);
		engine.addSystem(input);
		engine.addSystem(move);
		engine.addSystem(path);
		engine.addSystem(collision);
		
		il = new PlayerInputListener(input);
		Gdx.input.setInputProcessor(il);
		
	}
	
	@Override
	public void render() {		
		//OpenGL background clear
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		engine.update(Gdx.graphics.getDeltaTime());

	}

}
