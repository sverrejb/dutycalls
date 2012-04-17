package Incognito;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class MainMenu extends BasicGameState {
	
	Image background = null;
	private int stateID = -1;
	private String versionName = "Incognito InDev";
	
	public MainMenu(int stateID) {
		// TODO Auto-generated constructor stub
		this.stateID = stateID;
		
		//System.out.println(GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE));
	}

	@Override
		// TODO Auto-generated method stub
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		background = new Image("img/background.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		background.draw(0, 0);
		g.drawString(versionName, 600, 10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}
