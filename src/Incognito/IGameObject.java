package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/*
 * Interface for the GameObject
 */
public interface IGameObject {
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException;
	
	public void unLoad();
	
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException;
	
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException;
	
	/*
	 * Coallision detection
	 */
	
	public boolean coallision(GameObject other);
}
