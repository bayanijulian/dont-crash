package com.hypetrainstudios.dontcrash.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hypetrainstudios.dontcrash.DontCrash;
import com.hypetrainstudios.dontcrash.handlers.AssetHandler;



public class MenuUI {
	public static Stage stage;
	private static FitViewport view;
	
	private static ButtonStyle playBtnStyle;
	private static Button playBtn;
	private static ButtonStyle optionsBtnStyle;
	private static Button optionsBtn;
	private static ButtonStyle exitBtnStyle;
	private static Button exitBtn;
	
	private static Listener listener;
	
	public static void setup(){
		view = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(view,DontCrash.batch);
		
		listener = new Listener();
		
		createButtons();
		addActors();
	}
	private static void createButtons(){
		/*		Styles		*/
//		pauseBtnStyle = new ButtonStyle(
//				new TextureRegionDrawable(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("pauseBtnNormal")), //UP
//				new TextureRegionDrawable(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("pauseBtnPressed")),//DOWN
//				new TextureRegionDrawable(AssetHandler.manager.get(AssetHandler.atlasImages).findRegion("pauseBtnNormal")));//CHECKED
		
		/*		Buttons		*/
//		pauseBtn = new Button(pauseBtnStyle);
		
		/*		Positions		*/
//		pauseBtn.setPosition(Gdx.graphics.getWidth()-pauseBtn.getWidth(), Gdx.graphics.getHeight()-pauseBtn.getHeight());
		
		/*		Listeners		*/
//		pauseBtn.addListener(listener);
		
	}
	
	
	private static void addActors(){
		
	}
	
	public static void draw(){
		stage.draw();
	}
	public static void update(float delta){
		stage.act();
	}
	
	private static class Listener extends ChangeListener{
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			
			
		}
	}
}
