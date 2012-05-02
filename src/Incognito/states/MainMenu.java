package Incognito.states;

import it.marteEngine.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Incognito.utils.Globals;
import Incognito.utils.Constants;

public class MainMenu extends World {

	private Image background = null;
	private Image title = null;
	private Image startGameOption = null;
	private Image exitOption = null;
	private Sound buttonSound = null;
	private Sound intro = null;

	private final String versionName = "Incognito InDev";

	private int stateID = -1;
	private float menuX = Constants.GAME_WIDTH / 2 - 250;
	private float menuY = 300;
	private float scaleStep = 0.001f;
	private int buttonSpace = 80;
	private float StartbuttonScale = 1;
	private float ExitbuttonScale = 1;
	private boolean soundPlayed1 = false;
	private boolean soundPlayed2 = false;
	private boolean introPlaying = false;

	public MainMenu(int stateID) {
		super(stateID);
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		background = new Image("img/background.png");
		Image menuOptions = new Image("img/menu.png");
		title = menuOptions.getSubImage(50, 50, 570, 100);
		startGameOption = menuOptions.getSubImage(290, 180, 319, 66);
		exitOption = menuOptions.getSubImage(290, 246, 150, 66);

		buttonSound = new Sound("/res/sound/buttonOver.wav");
		intro = new Sound("/res/sound/intro.wav");
	}

	@Override
	public void render(GameContainer gameContainer,
			StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		background.draw(0, 0);
		//g.drawString(versionName, 600, 10);
		title.draw(menuX - 20, 150);
		startGameOption.draw(menuX, menuY, StartbuttonScale);
		exitOption.draw(menuX, menuY + buttonSpace, ExitbuttonScale);
	}

	@Override
	public void update(GameContainer gameContainer,
			StateBasedGame stateBasedGame, int delta) throws SlickException {
		if(!introPlaying ){
			intro.loop(1f, Constants.MUSIC_VOLUM);
			introPlaying = true;
		}

		Input input = gameContainer.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;
		boolean insideExit = false;

		if ((mouseX >= menuX && mouseX <= menuX + startGameOption.getWidth())
				&& (mouseY >= menuY && mouseY <= menuY
						+ startGameOption.getHeight())) {
			insideStartGame = true;
		} else if ((mouseX >= menuX && mouseX <= menuX + exitOption.getWidth())
				&& (mouseY >= menuY + buttonSpace && mouseY <= menuY
						+ buttonSpace + exitOption.getHeight())) {
			insideExit = true;
		}

		if (insideStartGame) {
			
			if (!soundPlayed1) {
				buttonSound.play(1f, Constants.EFFECTS_VOLUM);
				soundPlayed1 = true;
			}
			if (StartbuttonScale < 1.05f)
				StartbuttonScale += scaleStep * delta;

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				
				intro.stop();
				introPlaying = false;
				// This will run init on Gameplay state
				Globals.game.enterState(Constants.INGAME_STATE,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));

			}
		} else {
			soundPlayed1 = false;
			if (StartbuttonScale > 1.0f)
				StartbuttonScale -= scaleStep * delta;
		}

		if (insideExit) {
			if (!soundPlayed2) {
				buttonSound.play(1f, Constants.EFFECTS_VOLUM);
				soundPlayed2 = true;
			}
			if (ExitbuttonScale < 1.05f)
				ExitbuttonScale += scaleStep * delta;
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				gameContainer.exit();
		} else {
			soundPlayed2 = false;
			if (ExitbuttonScale > 1.0f)
				ExitbuttonScale -= scaleStep * delta;
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
