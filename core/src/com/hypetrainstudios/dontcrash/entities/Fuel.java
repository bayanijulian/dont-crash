package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;

public class Fuel extends Entity{
	public Fuel(float x, float y){
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("fuelcan"));
		this.x = x;
		this.y = y;
		this.image.setCenter(this.x, this.y);
		this.active = true;
		this.rectangleForCollision = this.image.getBoundingRectangle();
	}
	@Override
	public void collisionWithSpaceShip(){
		this.active = false;
		DontCrash.spaceShip.resetDistanceTraveled();
	}
}
