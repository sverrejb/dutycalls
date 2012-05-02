package Incognito.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Incognito.utils.Constants;
import Incognito.utils.Globals;

import it.marteEngine.World;

public class GameOverState extends World{
	
	private Image gameOver = new Image("img/gameover.png");

	public GameOverState(int id) throws SlickException {
		super(id);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Globals.ac.setMouseCursor((ImageData) new Cursor(Cursor.DEFAULT_CURSOR), 0, 0);
		super.enter(container, game);
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)throws SlickException {
		Input input = gameContainer.getInput();
		
		if(input.isKeyPressed(input.KEY_SPACE))
			Globals.game.enterState(Constants.MAINMENU_STATE, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		gameOver.draw(0,0);
	}

}
