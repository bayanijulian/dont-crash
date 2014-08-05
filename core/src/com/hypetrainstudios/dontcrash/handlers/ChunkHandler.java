package com.hypetrainstudios.dontcrash.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.hypetrainstudios.dontcrash.DontCrash;

public class ChunkHandler {
	public static float gapX = Gdx.graphics.getWidth()/2;
	public static int waveCounter = 0;
	public static int chunkCounter = 0;
	public static final float centerPosition = Gdx.graphics.getHeight() * (3/6f);
	public static final float topPosition = Gdx.graphics.getHeight() * (5/6f);
	public static final float bottomPosition = Gdx.graphics.getHeight() * (1/6f);
	public static Rectangle rectangleForGenerationOfNewChunk = new Rectangle();
	public static Rectangle rectangleForRemovalOfOldChunk = new Rectangle();
	
	public static void init(){
		createChunk();
		chunkCounter++;
		rectangleForRemovalOfOldChunk.set((gapX * ((chunkCounter*10)-5)), 0, (Gdx.graphics.getWidth()*.1f), (Gdx.graphics.getHeight()));
		rectangleForGenerationOfNewChunk.set((gapX * ((chunkCounter*10)-5)), 0, (Gdx.graphics.getWidth()*.1f), (Gdx.graphics.getHeight()));
	}
	public static void update(){
		if(DontCrash.spaceShip.getRectangle().overlaps(rectangleForGenerationOfNewChunk)){
			chunkCounter++;
			rectangleForGenerationOfNewChunk.set((gapX * ((chunkCounter*10)-5)), 0, (Gdx.graphics.getWidth()*.1f), (Gdx.graphics.getHeight()));
			createChunk();
		}
		if(DontCrash.spaceShip.getRectangle().overlaps(rectangleForRemovalOfOldChunk)){
			rectangleForRemovalOfOldChunk.set((gapX * ((chunkCounter*10)-5)), 0, (Gdx.graphics.getWidth()*.1f), (Gdx.graphics.getHeight()));
		}
	}
	public static void destroyChunk(){
		for(int i = 0; i < DontCrash.spaceRocks.size(); i ++){
			DontCrash.spaceRocks.get(i).deactivate();
		}
	}
	public static void createChunk(){
		for(int i = 0; i<10; i++){
			waveCounter++;
			int numberOfRocksPerWave = MathUtils.random(1, 3);
			if(numberOfRocksPerWave==3){
				DontCrash.createSpaceRock((gapX * waveCounter),topPosition);
				DontCrash.createSpaceRock((gapX * waveCounter),centerPosition);
				DontCrash.createSpaceRock((gapX * waveCounter),bottomPosition);
			}
			else if(numberOfRocksPerWave==2){
				int rdm = MathUtils.random(2);
				if(rdm==0){
					DontCrash.createSpaceRock((gapX * waveCounter),topPosition);
					DontCrash.createSpaceRock((gapX * waveCounter),centerPosition);
				}
				else if(rdm==1){
					DontCrash.createSpaceRock((gapX * waveCounter),topPosition);
					DontCrash.createSpaceRock((gapX * waveCounter),bottomPosition);
				}
				else{
					DontCrash.createSpaceRock((gapX * waveCounter),bottomPosition);
					DontCrash.createSpaceRock((gapX * waveCounter),centerPosition);
				}
			}
			else{
				int rdm = MathUtils.random(2);
				if(rdm==0)	DontCrash.createSpaceRock((gapX * waveCounter),topPosition);
				else if(rdm==1)	DontCrash.createSpaceRock((gapX * waveCounter),centerPosition);
				else	DontCrash.createSpaceRock((gapX * waveCounter),bottomPosition);
			}
		}
	}
}
