package Incognito;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import Incognito.states.GameState;


public class Incognito {
	
	private static final int FPS = 60; 
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameState("Incognito"));
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(FPS);
		app.start();
	}
}
