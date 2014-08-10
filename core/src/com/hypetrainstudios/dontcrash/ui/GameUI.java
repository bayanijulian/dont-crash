package com.hypetrainstudios.dontcrash.ui;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;

public class GameUI {
	private static Stage stage;
	private static FitViewport view;
	
	private static LabelStyle mainLblStyle;
	private static Label timeLbl;
	
	private static ButtonStyle pauseBtnStyle;
	private static Button pauseBtn;
	
	private static Animation fuelMeterAnimation;
	private static Image fuelMeterImg;
	
	private static BitmapFont audiowideFont;
	
	private static DecimalFormat df;
	
	public static void init(){
		view = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(view);
		
		df = new DecimalFormat("00");
		
		createFonts();
		createAnimations();
		createImages();
		createLabels();
		addActors();
	}
	
	public static void createGameUI(){
		init();
	}
	public static void createButtons(){
		
	}
	public static void createImages(){
		fuelMeterImg = new Image(AssetHandler.manager.get(AssetHandler.atlasFuelMeter).findRegion("fuelmeter"));
		fuelMeterImg.setCenterPosition((Gdx.graphics.getWidth()/2f), fuelMeterImg.getHeight()/2);
		fuelMeterImg.setScaleY(.5f);
	}
	public static void createAnimations(){
		fuelMeterAnimation = new Animation(1/30f, AssetHandler.manager.get(AssetHandler.atlasFuelMeter).findRegions("fuelmeter"));
	}
	public static void createFonts(){
		audiowideFont = AssetHandler.manager.get(AssetHandler.audiowideFont);
	}
	public static void createLabels(){
		/*	Styles	*/
		mainLblStyle = new LabelStyle(audiowideFont,Color.BLACK);
		
		/*	Labels	*/
		timeLbl = new Label("Time", mainLblStyle);
		timeLbl.setPosition(0, 0);
	}
	public static void addActors(){
		stage.addActor(fuelMeterImg);
	//	stage.addActor(pauseBtn);
		stage.addActor(timeLbl);
	}
	public static void update(float delta){
		stage.act();
		stage.draw();
		DontCrash.gameTime+=delta;
		timeLbl.setText(""+df.format((double)(DontCrash.gameTime)));
		//updatePositions(delta);
	}
	
	public static void updatePositions(float delta){
		
		fuelMeterImg.setCenterPosition(
				fuelMeterImg.getX()+DontCrash.spaceShip.getSpeed() * delta, 
				fuelMeterImg.getY());
	}
	
	public static void updateProgressOnFuelMeter(float fuelPercentage){
		fuelMeterImg.setDrawable(new TextureRegionDrawable(fuelMeterAnimation.getKeyFrame(fuelPercentage)));
		if(fuelMeterAnimation.isAnimationFinished(fuelPercentage)){
			DontCrash.endGame();
		}
	}
	
	public static void dispose(){
		stage.dispose();
	}
}
