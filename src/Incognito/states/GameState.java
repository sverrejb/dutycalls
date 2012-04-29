package Incognito.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.*;

import Incognito.utils.Constants;
import Incognito.utils.Globals;

public class GameState extends StateBasedGame{

	public GameState(String name) {
		super(name);
		Globals.game = this;
		
		//this.enterState(Constants.MAINMENU_STATE);
		
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		
		addState(new MainMenu(Constants.MAINMENU_STATE));
		addState(new Gameplay(Constants.INGAME_STATE));
		addState(new GameOverState(Constants.LOST_STATE));
		addState(new AboutState(Constants.ABOUT_STATE));
		addState(new HighScoreState(Constants.HIGHSCORE_STATE));
		//this.getState(Constants.MAINMENU_STATE).init(gameContainer, this);
		
		
		//Not necessary, should make a flag for init'ed
		//this.getState(Constants.INGAME_STATE).init(gameContainer, this);
	}
	
}
