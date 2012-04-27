package Incognito;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import Incognito.utils.Globals;

import org.lwjgl.LWJGLUtil;

import java.awt.Cursor;
import java.io.File;

import Incognito.states.GameState;


public class Incognito{
	
	private static final int FPS = 60;
	
	
	public static void main(String[] args) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "lib/native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		AppGameContainer app = new AppGameContainer(new GameState("Incognito"));
		Globals.ac = app;
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(FPS);
		
		app.start();
		
	}
}
