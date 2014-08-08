package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;

public class FuelMeter extends Entity {
	
	private Animation fuelAnimation;
	
	public FuelMeter(){
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasFuelMeter).findRegion("fuelmeter"));
		this.image.setSize(700, 50);
		this.x = Gdx.graphics.getWidth()/2-(.1f*Gdx.graphics.getWidth());
		this.y = this.image.getHeight()/2;
		this.image.setCenter(this.x, this.y);
		this.fuelAnimation = new Animation(1/30f, AssetHandler.manager.get(AssetHandler.atlasFuelMeter).findRegions("fuelmeter"));
		this.active = true;
		this.rectangleForCollision = null;
	}
	
	@Override
	public void update(float delta){
		this.x+= DontCrash.spaceShip.getSpeed() * delta;
		this.image.setCenter(this.x, this.y);
	}
	public void updateProgress(float fuelPercentage){
		this.image.setRegion(fuelAnimation.getKeyFrame(fuelPercentage));
		if(fuelAnimation.isAnimationFinished(fuelPercentage)){
			DontCrash.endGame();
		}
	}
}
