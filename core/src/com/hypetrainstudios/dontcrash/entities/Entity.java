package com.hypetrainstudios.dontcrash.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
	protected float x;
	protected float y;
	protected Sprite image;
	protected boolean active;
	protected Rectangle rectangleForCollision;
	public Entity(){
		this.x = 0;
		this.y = 0;
		this.image = new Sprite();
		active = true;
		rectangleForCollision = image.getBoundingRectangle();
	}
	public Entity(Sprite image,float x,float y){
		this.image = image;
		this.x = x;
		this.y = y;
		active = true;
		rectangleForCollision = image.getBoundingRectangle();
	}
	public Sprite getSprite(){	return this.image;	}
	
	public void update(float delta){}
	
	public void deactivate(){
		this.active = false;
	}
	public boolean isActive(){
		return this.active;
	}
	public Rectangle getRectangle(){	return this.rectangleForCollision;	}
	
	/*		Collision Handling		*/
	public void collisionWithProjectile(){
		
	}
	public void collisionWithSpaceRock(){
		
	}
	public void collisionWithSpaceShip(){
		
	}
	public void collisionWithFuel(){
		
	}
	
}
