package com.hypetrainstudios.dontcrash;


import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hyeptrainstudios.dontcrash.screens.GameScreen;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;
import com.hypetrainstudios.dontcrash.handlers.ChunkHandler;
import com.hypetrainstudios.dontcrash.ui.GameUI;
import com.hypetrainstudios.dontcrash.entities.Fuel;
import com.hypetrainstudios.dontcrash.entities.Projectile;
import com.hypetrainstudios.dontcrash.entities.SpaceRock;
import com.hypetrainstudios.dontcrash.entities.SpaceShip;

public class DontCrash extends Game {
	private Screen gameScreen;
	
	public static OrthographicCamera cam;
	public static SpriteBatch batch;
	public static ArrayList<SpaceRock> spaceRocks;
	public static ArrayList<Fuel> fuel;
	public static ArrayList<Projectile> projectiles;
	public static SpaceShip spaceShip;
	public static boolean running;
	
	public static float gameTime;
	@Override
	public void create() {
		//loads assets to a queue
		AssetHandler.load();
		//loads queue to ram
		AssetHandler.manager.finishLoading();
		//creates the main menu for the game
		gameScreen = new GameScreen();
		running = true;
		init();
		//sets screen to the main menu
		setScreen(gameScreen);
		
		gameTime = 0;
	}
	
	public static void init(){
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		batch.enableBlending();
		spaceRocks = new ArrayList<SpaceRock>();
		fuel = new ArrayList<Fuel>();
		projectiles = new ArrayList<Projectile>();
		spaceShip = new SpaceShip();
		
		ChunkHandler.init();
		GameUI.setup();
	}
	public static void createSpaceRock(float x, float y){
		spaceRocks.add(new SpaceRock(x, y));
	}
	public static void createProjectile(float x, float y){
		projectiles.add(new Projectile(x, y));
	}
	public static void createFuel(float x, float y){
		fuel.add(new Fuel(x, y));
	}
	public static void endGame(){
		running = false;
	}
	public static void reset(){
		spaceRocks.clear();
		fuel.clear();
		projectiles.clear();
		
		running = true;
		
		ChunkHandler.init();
		
		spaceShip.reset();
		
		
		//GameScreen.time = 0;
		
	}
}
