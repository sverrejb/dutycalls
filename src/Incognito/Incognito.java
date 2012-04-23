package Incognito;

import org.lwjgl.LWJGLUtil;
import java.io.File;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Incognito {
	
	private static final int FPS = 60; 
	
	public static void main(String[] args) throws SlickException {
		//Fikse natives?
		//http://slick.cokeandcode.com/wiki/doku.php?id=native_libraries
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		AppGameContainer app = new AppGameContainer(new GameState("Incognito"));
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(FPS);
		app.start();
	}
}
