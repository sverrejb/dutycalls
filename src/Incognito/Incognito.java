package Incognito;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Incognito {
	
	private static final int FPS = 60; 
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameState("Incognito"));
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(FPS);
		app.start();
		System.out.println(GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE));
	}
}
