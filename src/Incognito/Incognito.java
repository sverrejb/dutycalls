package Incognito;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Incognito {
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameState(null));
		app.setDisplayMode(800, 600, false);
		app.start();
	}
}
