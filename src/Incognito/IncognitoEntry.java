package Incognito;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import Incognito.utils.Constants;
import Incognito.utils.Globals;

import org.lwjgl.LWJGLUtil;

import java.io.File;

import Incognito.states.GameState;


public class IncognitoEntry{
	
	
	public static void main(String[] args) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "lib/native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		AppGameContainer app = new AppGameContainer(new GameState("Incognito"));
		Globals.ac = app;
		app.setDisplayMode(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, Constants.FULL_SCREEN);
		app.setTargetFrameRate(Constants.FPS);
		app.setVSync(true);
		app.start();
		
	}
}
