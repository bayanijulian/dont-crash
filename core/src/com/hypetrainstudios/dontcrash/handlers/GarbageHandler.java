package com.hypetrainstudios.dontcrash.handlers;

import com.hypetrainstudios.dontcrash.DontCrash;

public class GarbageHandler {
	public static void update(){
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
