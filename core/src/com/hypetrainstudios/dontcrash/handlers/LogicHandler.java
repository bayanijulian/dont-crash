package com.hypetrainstudios.dontcrash.handlers;

import com.hypetrainstudios.dontcrash.DontCrash;

public class LogicHandler {
	public static void update(float delta){
		/* Entities Updating */
		DontCrash.fuelMeter.update(delta);
		DontCrash.spaceShip.update(delta);
		for(int i = 0; i < DontCrash.projectiles.size(); i++)
			DontCrash.projectiles.get(i).update(delta);
		/* Handlers Updating */
		ChunkHandler.update();
		CollisionHandler.update();
		GarbageHandler.update();
	}
}
