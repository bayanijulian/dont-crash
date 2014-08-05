package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;

public class SpaceShip extends Entity implements InputProcessor{
	private float speed;
	private int fuel;
	private float centerPosition;
	private float topPosition;
	private float bottomPosition;
	private float fireRate;
	private float fireCounter;
	
	private Animation strafeRight;
	private Animation strafeLeft;
	
	/* Used for Dodge Method */
	private float percent;
	private float moveRate;
	private float moveCounter;
	private float yTarget;
	private boolean dodgeFinished;
	public SpaceShip(){
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		
		/* Positions */
		this.centerPosition = Gdx.graphics.getHeight() * (3/6f);
		this.topPosition = Gdx.graphics.getHeight() * (5/6f);
		this.bottomPosition = Gdx.graphics.getHeight() * (1/6f);
		
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		this.fuel = 100;
		this.speed = 400f;
		this.x = 0;
		this.y = centerPosition;
		this.image.setCenter(this.x, this.y);
		this.image.rotate(-90);
		rectangleForCollision = this.image.getBoundingRectangle();
		
		this.fireRate = 3f;
		this.fireCounter = 3f;
		this.active = true;
		
		this.moveCounter = 0;
		this.moveRate = 5f;
		this.yTarget = centerPosition;
		this.percent = 0;
		this.dodgeFinished = true;
		
		strafeRight = new Animation(1f,
						(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_right")),
						(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal")));
		strafeLeft = new Animation(1f,
				(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_left")),
				(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal")));
	}
	
	private void dodge(float delta){
		percent = moveCounter/moveRate;
		moveCounter+=delta;
		this.y = this.y + (yTarget - this.y) * percent;
		this.image.setCenter(this.x, this.y);
		if(moveCounter >= moveRate){
			moveCounter = 0;
			dodgeFinished = true;
		}
	}
	@Override
	public void update(float delta){
		move(delta);
		updateCounters(delta);
		if(!dodgeFinished)dodge(delta);
	}
	private void updateCounters(float delta){
		fireCounter += delta;
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
	public void collisionWithSpaceRock(){
	//	DontCrash.running = false;
	}
	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode==Keys.W||keycode==Keys.A)
		{
			this.yTarget = topPosition;
			dodgeFinished = false;
		}
		if(keycode==Keys.S||keycode==Keys.D)
		{
			this.yTarget = bottomPosition;
			dodgeFinished = false;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if(keycode==Keys.SPACE&&fireCounter>=fireRate){
			DontCrash.createProjectile(this.x + 50, this.y);
			fireCounter = 0;
		}
		else{
			this.yTarget = centerPosition;
			moveCounter = 0;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}
	
}
