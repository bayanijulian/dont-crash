package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;

public class SpaceShip extends Entity implements InputProcessor{
	private float speed;
	private int fuel;
	private float centerPosition;
	private float topPosition;
	private float bottomPosition;
	public SpaceShip(){
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		//this.image.setSize(64, 64);
		/* Positions */
		this.centerPosition = Gdx.graphics.getHeight() * (3/6f);
		this.topPosition = Gdx.graphics.getHeight() * (5/6f);
		this.bottomPosition = Gdx.graphics.getHeight() * (1/6f);
		
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		this.fuel = 100;
		this.speed = 200f;
		this.x = 0;
		this.y = centerPosition;
		this.image.setCenter(this.x, this.y);
		this.image.rotate(-90);
		rectangleForCollision = this.image.getBoundingRectangle();
		
	}
	@Override
	public void update(float delta){
		move(delta);
	}
	private void move(float delta){
		this.x+= speed * delta;
		this.image.setCenter(this.x, this.y);
		updateCollisionBounds();
		updateCamera();
	}
	private void updateCollisionBounds(){
		rectangleForCollision = this.image.getBoundingRectangle();
	}
	private void updateCamera(){
		DontCrash.cam.position.x = (this.x) + (.4f*Gdx.graphics.getWidth());
		DontCrash.cam.update();
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode==Keys.W||keycode==Keys.A)
		{
			this.y = topPosition;
			this.image.setCenter(this.x, this.y);
		}
		if(keycode==Keys.S||keycode==Keys.D)
		{
			this.y = bottomPosition;
			this.image.setCenter(this.x, this.y);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		this.y = centerPosition;
		this.image.setCenter(this.x, this.y);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
