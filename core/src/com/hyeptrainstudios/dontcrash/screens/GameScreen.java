package com.hyeptrainstudios.dontcrash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.ChunkHandler;
import com.hypetrainstudios.dontcrash.handlers.LogicHandler;

public class GameScreen implements Screen{
	
	@Override
	public void render(float delta) {
		clearScreen();
		draw();
		if(DontCrash.running){
			update(delta);
		}
	}
	public void clearScreen(){
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	public void update(float delta){
		LogicHandler.update(delta);
	}
	public void draw(){
		DontCrash.batch.setProjectionMatrix(DontCrash.cam.combined);
		DontCrash.batch.begin();
		
		DontCrash.spaceShip.getSprite().draw(DontCrash.batch);
		
		for(int i = 0; i<DontCrash.spaceRocks.size(); i ++)
			DontCrash.spaceRocks.get(i).getSprite().draw(DontCrash.batch);
		
		for(int i = 0; i<DontCrash.projectiles.size(); i ++)
			DontCrash.projectiles.get(i).getSprite().draw(DontCrash.batch);
		
		DontCrash.batch.end();
	}
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
		Gdx.input.setInputProcessor(DontCrash.spaceShip);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
