package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;

public class Projectile extends Entity{
	private float speed;
	private float activeRate;
	private float activeCounter;
	public Projectile(float x, float y){
		this.image = new Sprite(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("projectile"));
		this.x = x;
		this.y = y;
		this.image.setCenter(this.x, this.y);
		this.active = true;
		this.speed = 2000f;
		updateCollisionBounds();
		this.activeRate = .5f;
		this.activeCounter = 0;
	}
	@Override
	public void update(float delta){
		this.activeCounter+=delta;
		if(this.activeCounter>=this.activeRate)
			deactivate();
		move(delta);
	}
	private void move(float delta){
		this.x += speed * delta;
		this.image.setX(this.x);
		updateCollisionBounds();
	}
	public void collisionWithSpaceRock(){
		deactivate();
	}
	
}
