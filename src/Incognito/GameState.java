package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends StateBasedGame{
	
	public static final int MAINMENUSTATE = 1;
	public static final int GAMEPLAYSTATE = 0;

	public GameState(String name) {
		super(name);
		this.addState(new MainMenu(MAINMENUSTATE));
		this.addState(new Gameplay(GAMEPLAYSTATE));
		this.enterState(MAINMENUSTATE);
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.getState(MAINMENUSTATE).init(gameContainer, this);
		//Not necessary, should make a flag for init'ed
		//this.getState(GAMEPLAYSTATE).init(gameContainer, this);
	}
	
}
