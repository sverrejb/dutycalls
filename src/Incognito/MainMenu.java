package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class MainMenu extends BasicGameState {
	
	TiledMap map;
	int stateID = -1;
	public MainMenu(int stateID) {
		// TODO Auto-generated constructor stub
		this.stateID = stateID;
	}

	@Override
		// TODO Auto-generated method stub
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		map = new TiledMap("Resources/maps/untitled.tmx", "Resources");
		
	}

	@Override
		// TODO Auto-generated method stub
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		map.render(0, 0);
		
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
