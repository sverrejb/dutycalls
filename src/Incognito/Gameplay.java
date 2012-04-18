package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Gameplay extends BasicGameState{

	private int stateID = -1;
	
	private PlayerObject player;

	public Gameplay(int stateID) {
		this.stateID = stateID;
		player = new PlayerObject();
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		player.init(gameContainer, stateBasedGame);
		
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		player.render(gameContainer, stateBasedGame, g);
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		player.update(gameContainer, stateBasedGame, delta);
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
