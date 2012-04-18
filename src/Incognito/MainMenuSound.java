package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuSound extends Sound {

	public MainMenuSound(String arg0) throws SlickException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	Sound buttonOver = null;
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)throws SlickException {
		buttonOver = new Sound("sound/buttonOver.Wav");
	}

	public void update(int MouseX, int MouseY) throws SlickException {
		
	}

}
