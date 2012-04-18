package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public interface IGameSound {
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException;
	
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException;
}
