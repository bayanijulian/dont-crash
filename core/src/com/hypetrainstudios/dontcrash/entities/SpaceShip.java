package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;
import com.hypetrainstudios.dontcrash.ui.GameUI;

public class SpaceShip extends Entity implements InputProcessor{
	private float speed;
	private float maxSpeed;
	/* Used for Fuel */
	private float distanceOnFull;
	private float distanceTraveled;
	//used to move the ship
	private float centerPosition;
	private float topPosition;
	private float bottomPosition;
	//used for firing
	private float fireRate;
	private float fireCounter;
	
	/* Used for Dodging */
	private float dodgePercent;
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
	
	/* Used For Input */
	
	private int currentInput;
	private int currentTouchX;
	private int currentTouchY;
	
	
	public SpaceShip(){
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		
		/* Positions */
		this.centerPosition = Gdx.graphics.getHeight() * (3/6f);
		this.topPosition = Gdx.graphics.getHeight() * (5/6f);
		this.bottomPosition = Gdx.graphics.getHeight() * (1/6f);
		
		this.distanceOnFull = 17500f;
		this.distanceTraveled = 0;
		
		this.maxSpeed = 1700f;
		this.speed = 800f;
		this.x = -2500;
		this.y = centerPosition;
		this.active = true;
		
		this.image.setCenter(this.x, this.y);
		this.image.rotate(-90);

		this.updateCollisionBounds();
		
		this.fireRate = .75f;
		this.fireCounter = .75f;
		
		this.dodgeCounter = .25f;
		this.dodgeRate = .5f;
		
		this.dodgeStart = bottomPosition;
		this.dodgeEnd = topPosition;
		this.dodgePercent = 0;
		
		this.dodgeFinished = true;
		
		this.dodgeTimeMiddle = .25f;
		this.dodgeTimeTop = .5f;
		this.dodgeTimeBottom = 0;

		this.dodgeTimeTemp = this.dodgeTimeMiddle;
		this.dodgeDirection = 1;
		
		/* Input */
		this.currentInput = -1;
		this.currentTouchX = -1;
		this.currentTouchY = -1;
	}
	@Override
	public void update(float delta){
		this.move(delta);
		this.updateCounters(delta);
		if(!this.dodgeFinished) this.dodge(delta);
		
	}
	@Override
	public void collisionWithSpaceRock(){
		DontCrash.endGame();
	}
	
	private void dodge(float delta){
		this.dodgePercent = this.dodgeCounter/this.dodgeRate;
		this.dodgeCounter = this.dodgeCounter + (this.dodgeDirection * delta);
		
		this.y = MathUtils.lerp(this.dodgeStart, this.dodgeEnd, dodgePercent);
		this.image.setCenter(this.x, this.y);
		
		
		if(MathUtils.isEqual(dodgeCounter, dodgeTimeMiddle, .005f))	
			this.image.setRegion(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		else if(dodgeDirection==-1)	
			this.image.setRegion(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_right"));
		else if(dodgeDirection==1)	
			this.image.setRegion(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_left"));
		
		
		if(MathUtils.isEqual(dodgeCounter, dodgeTimeTemp, .01f))	dodgeFinished = true;
	}
	private void updateCounters(float delta){
		this.fireCounter += delta;
	}
	private void move(float delta){
		this.x+= speed * delta;
		this.distanceTraveled+= speed*delta;
		GameUI.updateProgressOnFuelMeter(distanceTraveled/distanceOnFull);
		this.image.setCenter(this.x, this.y);
		this.updateCollisionBounds();
		this.updateCamera();
	}
	private void updateCamera(){
		DontCrash.cam.position.x = (this.x) + (.4f*Gdx.graphics.getWidth());
		DontCrash.cam.update();
	}
	
	public void resetDistanceTraveled(){	this.distanceTraveled = 0;	}
	
	public float getSpeed(){	return this.speed;	}
	
	public void increaseDodgeRate(float newRate){
		this.dodgeRate = newRate;
		
		this.dodgeTimeBottom = 0;
		this.dodgeTimeMiddle = newRate/2f;
		this.dodgeTimeTop = newRate;
		
		this.dodgeCounter = dodgeTimeMiddle;
	}
	public void increaseSpeed() {
		if(this.speed>=this.maxSpeed) this.speed = this.maxSpeed;
		else	this.speed += 100f;
	}
	public void reset(){
		this.x = -2500f;
		this.y = this.centerPosition;
		
		this.image.setCenter(this.x, this.y);
		this.image.setRegion(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_ship_normal"));
		
		this.speed = 500f;
		this.active = true;
		
		this.distanceTraveled = 0;
		
		this.currentInput = -1;
		this.currentTouchX = -1;
		this.currentTouchY = -1;
		
		this.updateCollisionBounds();
		
		this.fireRate = .75f;
		this.fireCounter = .75f;
		
		
		this.dodgeCounter = .25f;
		this.dodgeRate = .5f;
		
		this.dodgeStart = bottomPosition;
		this.dodgeEnd = topPosition;
		this.dodgePercent = 0;
		this.dodgeFinished = true;
		
		
		this.dodgeTimeMiddle = .25f;
		this.dodgeTimeTop = .5f;
		this.dodgeTimeBottom = 0;
		
		this.dodgeTimeTemp = this.dodgeTimeMiddle;
		this.dodgeDirection = 1;
	}

	/* 		Input Handling 		*/
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
		if(keycode==Keys.W||keycode==Keys.S||keycode==Keys.UP||keycode==Keys.DOWN){
			currentInput = keycode;
			if(currentInput==Keys.W||currentInput==Keys.UP)
				this.goingUp(false);
				
			if(currentInput==Keys.S||currentInput==Keys.DOWN)
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
		if(keycode==Keys.R){
			DontCrash.reset();
		}
		if(keycode==Keys.W||keycode==Keys.S||keycode==Keys.UP||keycode==Keys.DOWN){
		
			if(currentInput==Keys.W||currentInput==Keys.UP){
				if(this.y>=this.centerPosition)	this.goingDown(true);
				if(this.y<this.centerPosition)	this.goingUp(true);
				currentInput = -1;
			}
			if(currentInput==Keys.S||currentInput==Keys.DOWN){
				if(this.y>=this.centerPosition)	this.goingDown(true);
				if(this.y<this.centerPosition)	this.goingUp(true);
				currentInput = -1;
			}
		}
		  return false;
	}
	@Override
	public boolean keyTyped(char character) {	return false;	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if((screenX<=Gdx.graphics.getWidth()*.2f&&screenY<=Gdx.graphics.getHeight()*.5f)||(screenX<=Gdx.graphics.getWidth()*.2f&&screenY>Gdx.graphics.getHeight()*.5f)){
			this.currentTouchX = screenX;
			this.currentTouchY = screenY;
			
			//left hand side of screen and the lower box
			if(currentTouchX<=Gdx.graphics.getWidth()*.2f&&currentTouchY>=Gdx.graphics.getHeight()*.5f&&currentTouchX>0){
				this.goingDown(false);
			}
			//left hand side of screen and the upper box
			if(currentTouchX<=Gdx.graphics.getWidth()*.2f&&currentTouchY<Gdx.graphics.getHeight()*.5f&&currentTouchX>0){
				this.goingUp(false);
			}
		}
		
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		if(screenX>Gdx.graphics.getWidth()*.2f&&fireCounter>=fireRate&&dodgeFinished){
			DontCrash.createProjectile(this.x + 50, this.y);
			fireCounter = 0;
		}
		
		if((screenX<=Gdx.graphics.getWidth()*.2f&&screenY<=Gdx.graphics.getHeight()*.5f)||(screenX<=Gdx.graphics.getWidth()*.2f&&screenY>Gdx.graphics.getHeight()*.5f)){

			
			//left hand side of screen and the lower box
			if(currentTouchX<=Gdx.graphics.getWidth()*.2f&&currentTouchY>=Gdx.graphics.getHeight()*.5f&&currentTouchX>0){
				if(this.y>=this.centerPosition)	this.goingDown(true);
				if(this.y<this.centerPosition)	this.goingUp(true);
				this.currentTouchX = -1;
				this.currentTouchY = -1;
			}
			//left hand side of screen and the upper box
			if(currentTouchX<=Gdx.graphics.getWidth()*.2f&&currentTouchY<Gdx.graphics.getHeight()*.5f&&currentTouchX>0){
				if(this.y>=this.centerPosition)	this.goingDown(true);
				if(this.y<this.centerPosition)	this.goingUp(true);
				this.currentTouchX = -1;
				this.currentTouchY = -1;
			}
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {	return false;	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {	return false;	}

	@Override
	public boolean scrolled(int amount) {	return false;	}
}
