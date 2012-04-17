package SlickTetris;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SlickBlocksGame extends StateBasedGame {

	public static final int MAINMENUSTATE = 1;
	public static final int GAMEPLAYSTATE = 1;
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new SlickBlocksGame());
		app.setDisplayMode(800, 600, false);
		app.start();
	}

	public SlickBlocksGame() {
		super("SlickBlocks");

		this.addState(new MainMenuState(MAINMENUSTATE));
		//this.addState(new GameplayState(GAMEPLAYSTATE));
		this.enterState(MAINMENUSTATE);
	}

	@Override
	public void initStatesList(GameContainer gameContainer)
			throws SlickException {

		this.getState(MAINMENUSTATE).init(gameContainer, this);
		this.getState(GAMEPLAYSTATE).init(gameContainer, this);
	}
}