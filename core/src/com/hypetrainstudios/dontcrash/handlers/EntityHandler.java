package com.hypetrainstudios.dontcrash.handlers;

import com.hypetrainstudios.dontcrash.DontCrash;

public class EntityHandler {
	
	
	public static void update(float delta){
		updateEntities(delta);
		checkCollision();
		checkGarbage();
	}
	
	public static void updateEntities(float delta){
		/* Entities Updating */
		DontCrash.spaceShip.update(delta);
		
		for(int i = 0; i < DontCrash.projectiles.size(); i++)
			DontCrash.projectiles.get(i).update(delta);
	}
	
	public static void checkCollision(){
		
		for(int i = 0; i<DontCrash.projectiles.size();i++){
			for(int n = 0; n < DontCrash.spaceRocks.size(); n ++ ){
				if(DontCrash.projectiles.get(i).getRectangle().overlaps(DontCrash.spaceRocks.get(n).getRectangle())){
					collisionBetweenProjectileAndSpaceRock(i, n);
				}
			}
		}
		
		for(int i = 0; i < DontCrash.fuel.size(); i ++ ){
			if(DontCrash.fuel.get(i).getRectangle().overlaps(DontCrash.spaceShip.getRectangle()))
				collisionBetweenFuelAndSpaceShip(i);
		}
		
		for(int i = 0; i < DontCrash.spaceRocks.size(); i ++ ){
			if(DontCrash.spaceRocks.get(i).getRectangle().overlaps(DontCrash.spaceShip.getRectangle()))
				collisionBetweenSpaceRockAndSpaceShip(i);
		}
	}
	private static void collisionBetweenProjectileAndSpaceRock(int i, int n){
		DontCrash.projectiles.get(i).collisionWithSpaceRock();
		DontCrash.spaceRocks.get(n).collisionWithProjectile();
	}
	private static void collisionBetweenSpaceRockAndSpaceShip(int i){
		DontCrash.spaceRocks.get(i).collisionWithSpaceShip();
		DontCrash.spaceShip.collisionWithSpaceRock();
	}
	private static void collisionBetweenFuelAndSpaceShip(int i){
		DontCrash.fuel.get(i).collisionWithSpaceShip();
	}
	
	public static void checkGarbage(){
		for(int i = 0; i < DontCrash.spaceRocks.size(); i++)
			if(!(DontCrash.spaceRocks.get(i).isActive()))
				DontCrash.spaceRocks.remove(i);
		for(int i = 0; i < DontCrash.fuel.size(); i++)
			if(!(DontCrash.fuel.get(i).isActive()))
				DontCrash.fuel.remove(i);
		for(int i = 0; i < DontCrash.projectiles.size(); i++)
			if(!(DontCrash.projectiles.get(i).isActive()))
				DontCrash.projectiles.remove(i);
	}
	
}
