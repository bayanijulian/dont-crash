package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;

public class SpaceRock extends Entity{
	public SpaceRock(float x, float y){
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("space_rock"));
		this.x = x;
		this.y = y;
		this.image.setCenter(this.x, this.y);
		rectangleForCollision = this.image.getBoundingRectangle();
	}
}
