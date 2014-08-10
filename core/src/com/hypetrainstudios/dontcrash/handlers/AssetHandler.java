package com.hypetrainstudios.dontcrash.handlers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetHandler {
	
	public static final AssetManager manager = new AssetManager();
	public static final AssetDescriptor<TextureAtlas> atlasImages = new AssetDescriptor<TextureAtlas>("images.pack", TextureAtlas.class);
	public static final AssetDescriptor<TextureAtlas> atlasFuelMeter = new AssetDescriptor<TextureAtlas>("fuelmeter.pack", TextureAtlas.class);
	
	public static final AssetDescriptor<BitmapFont> audiowideFont = new AssetDescriptor<BitmapFont>("audiowideFont/audiowide.fnt", BitmapFont.class);
	
	public static void load(){
		manager.load(atlasImages);
		manager.load(atlasFuelMeter);
		manager.load(audiowideFont);
	}
	public static void dispose(){
		manager.dispose();
	}
}
