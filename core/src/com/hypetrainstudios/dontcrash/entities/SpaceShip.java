package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;

public class SpaceShip extends Entity implements InputProcessor{
	private float speed;
	
	private float centerPosition;
	private float topPosition;
	private float bottomPosition;
	private float fireRate;
	private float fireCounter;
	
	/* Used for Dodge Method */
	private float percent;
	private float dodgeRate;
	private float dodgeCounter;
	private float dodgeEnd;
	private float dodgeStart;
	private boolean dodgeFinished;
	private int dodgeDirection;
	private float dodgeTimeTop;
	private float dodgeTimeMiddle;
	private float dodgeTimeBottom;
	private float dodgeTimeTemp;
	private int currentInput;
	private float distanceTraveled;
	public SpaceShip(){
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		
		/* Positions */
		this.centerPosition = Gdx.graphics.getHeight() * (3/6f);
		System.out.println("center position is " + centerPosition );
		this.topPosition = Gdx.graphics.getHeight() * (5/6f);
		this.bottomPosition = Gdx.graphics.getHeight() * (1/6f);
		this.distanceTraveled = 0;
		
		this.speed = 400f;
		this.x = 0;
		this.y = centerPosition;
		this.image.setCenter(this.x, this.y);
		this.active = true;
		
		this.image.rotate(-90);

		this.updateCollisionBounds();
		
		this.fireRate = 1.5f;
		this.fireCounter = 1.5f;
		
		
		this.dodgeCounter = .25f;
		this.dodgeRate = .5f;
		
		this.dodgeStart = bottomPosition;
		this.dodgeEnd = topPosition;
		this.percent = 0;
		this.dodgeFinished = true;
		
		
		this.dodgeTimeMiddle = .25f;
		this.dodgeTimeTop = .5f;
		this.dodgeTimeBottom = 0;
		
		this.dodgeTimeTemp = this.dodgeTimeMiddle;
		this.dodgeDirection = 1;
		
		this.currentInput = -1;
		
		//increaseDodgeRate(1f);
		
		
	}
	
	private void dodge(float delta){
		this.percent = this.dodgeCounter/this.dodgeRate;
		this.dodgeCounter = this.dodgeCounter + (dodgeDirection * delta);
		
		this.y = this.dodgeStart + (this.dodgeEnd - this.dodgeStart) * this.percent;
		this.image.setCenter(this.x, this.y);
		System.out.println("currently dodging y is : " + this.y + "\tpercent is : " + this.percent);
		if(MathUtils.isEqual(dodgeCounter, dodgeTimeMiddle, .12f))	image.setRegion(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		else if(dodgeDirection==-1)	image.setRegion(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_right"));
		else if(dodgeDirection==1)	image.setRegion(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_left"));
		
		if(MathUtils.isEqual(dodgeCounter, dodgeTimeTemp, .01f)){
			dodgeFinished = true;
			System.out.println("dodgecounter and dodgetimetemp are equal within .05f   :" +  dodgeCounter + " : " + dodgeTimeTemp);
		}
		
		
	}
	@Override
	public void update(float delta){
		this.move(delta);
		
		this.updateCounters(delta);
		if(!this.dodgeFinished) this.dodge(delta);
		
	}
	private void updateCounters(float delta){
		this.fireCounter += delta;
	}
	private void move(float delta){
		this.x+= speed * delta;
		this.distanceTraveled+= speed*delta;
		DontCrash.fuelMeter.updateProgress(distanceTraveled/10000f);
		this.image.setCenter(this.x, this.y);
		this.updateCollisionBounds();
		this.updateCamera();
	}
	public void resetDistanceTraveled(){
		this.distanceTraveled = 0;
	}
	public float getSpeed(){
		return this.speed;
	}
	public void increaseDodgeRate(float newRate){
		this.dodgeRate = newRate;
		this.dodgeTimeBottom = 0;
		this.dodgeTimeMiddle = newRate/2f;
		this.dodgeTimeTop = newRate;
	}
	private void updateCamera(){
		DontCrash.cam.position.x = (this.x) + (.4f*Gdx.graphics.getWidth());
		DontCrash.cam.update();
	}
	@Override
	public void collisionWithSpaceRock(){
		DontCrash.running = false;
	}

	/* 		Input Handling 		*/
	
	//DESKTOP INPUT		
	

	private void goingDown(boolean goToCenter){
		if(goToCenter){
			this.dodgeFinished = false;
			this.dodgeDirection = -1;
			this.dodgeTimeTemp = dodgeTimeMiddle;
		}
		else{
			this.dodgeFinished = false;
			this.dodgeDirection = -1;
			this.dodgeTimeTemp = dodgeTimeBottom;
		}
	}
	private void goingUp(boolean goToCenter){
		if(goToCenter){
			this.dodgeFinished = false;
			this.dodgeDirection = 1;
			this.dodgeTimeTemp = this.dodgeTimeMiddle;
		}
		else{
			this.dodgeFinished = false;
			this.dodgeDirection = 1;
			this.dodgeTimeTemp = this.dodgeTimeTop;
		}
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.W||keycode==Keys.S){
			currentInput = keycode;
			if(currentInput==Keys.W)
				this.goingUp(false);
				
			if(currentInput==Keys.S)
				this.goingDown(false);
		}
		
		
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		if(fireCounter>=fireRate&&dodgeFinished&&keycode==Keys.SPACE){
			DontCrash.createProjectile(this.x + 50, this.y);
			fireCounter = 0;
		}
		if(keycode==Keys.W||keycode==Keys.S){
		
		if(currentInput==Keys.W||currentInput==Keys.A){
			if(this.y>=this.centerPosition)	this.goingDown(true);
			if(this.y<this.centerPosition)	this.goingUp(true);
			currentInput = -1;
		}
		if(currentInput==Keys.S||currentInput==Keys.D){
			if(this.y>=this.centerPosition)	this.goingDown(true);
			if(this.y<this.centerPosition)	this.goingUp(true);
			currentInput = -1;
		}
		}
		return false;
	}

	
	//Mobile
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

	public void increaseSpeed() {
		this.speed += 100;
		
	}
	
}
