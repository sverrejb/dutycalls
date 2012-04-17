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
	
	private Image background = null;
	private Image startGameOption = null;
	private Image exitOption = null;
	
	private final String versionName = "Incognito InDev";
	
	private int stateID = -1;
	private float menuX = 120;
	private float menuY = 160;
	
	public MainMenu(int stateID) {
		// TODO Auto-generated constructor stub
		this.stateID = stateID;
	}

	@Override
		// TODO Auto-generated method stub
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
		background = new Image("img/background.png");
		Image menuOptions = new Image("img/menuOptions.png");
		startGameOption = menuOptions.getSubImage(0, 0, 377, 71); 
		exitOption = menuOptions.getSubImage(0, 71, 377, 71);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		background.draw(0, 0);
		g.drawString(versionName, 600, 10);
		
		startGameOption.draw(menuX, menuY, 1); 
		exitOption.draw(menuX, menuY+80, 1);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}
