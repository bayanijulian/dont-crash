package com.hypetrainstudios.dontcrash.handlers;

import com.hypetrainstudios.dontcrash.DontCrash;

public class CollisionHandler {
	public static void update(){
		
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
	public static void collisionBetweenProjectileAndSpaceRock(int i, int n){
		DontCrash.projectiles.get(i).collisionWithSpaceRock();
		DontCrash.spaceRocks.get(n).collisionWithProjectile();
	}
	public static void collisionBetweenSpaceRockAndSpaceShip(int i){
		DontCrash.spaceRocks.get(i).collisionWithSpaceShip();
		DontCrash.spaceShip.collisionWithSpaceRock();
	}
	public static void collisionBetweenFuelAndSpaceShip(int i){
		DontCrash.fuel.get(i).collisionWithSpaceShip();
	}
}
