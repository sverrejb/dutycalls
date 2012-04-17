package SlickTetris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
	
	  Image background = null;
	  Image startGameOption = null;
	  Image exitOption = null;
	 
	  float startGameScale = 1;
	  float exitScale = 1;

	  int stateID = -1;
	  private float menuX = 400;
	  private float menuY = 150;
	  
	    MainMenuState( int stateID ) 
	    {
	       this.stateID = stateID;
	    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		background = new Image("img/startTuxBg.jpg");
		
		Image menuOptions = new Image("img/menuOptions.png");
		startGameOption = menuOptions.getSubImage(0, 0, 377, 71); 
		exitOption = menuOptions.getSubImage(0, 71, 377, 71);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		background.draw(0, 0);
		 
		// Draw menu
		startGameOption.draw(menuX, menuY, startGameScale);
		 
		exitOption.draw(menuX, menuY+80, exitScale);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}
	
}
