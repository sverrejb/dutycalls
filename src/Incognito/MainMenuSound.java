package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuSound implements IGameSound {

	Sound buttonOver = null;
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)throws SlickException {
		buttonOver = new Sound("sound/buttonOver.Wav");
	}

	@Override
	public void update(GameContainer gameContainer,StateBasedGame stateBasedGame, int delta) throws SlickException {
		
	}

}
