package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {
	
	private Image background = null;
	private Image startGameOption = null;
	private Image exitOption = null;
	
	private final String versionName = "Incognito InDev";
	
	private int stateID = -1;
	private float menuX = 120;
	private float menuY = 160;
	private float scaleStep = 0.001f;
	private int buttonSpace = 80;
	private float StartbuttonScale = 1;
	private float ExitbuttonScale = 1;
	
	public MainMenu(int stateID) {
		this.stateID = stateID;
	}

	@Override
		// TODO Auto-generated method stub
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
		background = new Image("img/background.png");
		Image menuOptions = new Image("img/menuOptions.png");
		startGameOption = menuOptions.getSubImage(0, 0, 377, 71); 
		exitOption = menuOptions.getSubImage(0, 71, 377, 71);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		background.draw(0, 0);
		g.drawString(versionName, 600, 10);
		
		startGameOption.draw(menuX, menuY, StartbuttonScale); 
		exitOption.draw(menuX, menuY+buttonSpace, ExitbuttonScale);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)throws SlickException {
		
		Input input = gameContainer.getInput();
		
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		boolean insideStartGame = false;
		boolean insideExit = false;
		
		if( ( mouseX >= menuX && mouseX <= menuX + startGameOption.getWidth()) &&
				( mouseY >= menuY && mouseY <= menuY + startGameOption.getHeight()) ){
			insideStartGame = true;
		}
		else if( ( mouseX >= menuX && mouseX <= menuX+ exitOption.getWidth()) &&
	          ( mouseY >= menuY+buttonSpace && mouseY <= menuY+buttonSpace + exitOption.getHeight()) ){
			insideExit = true;
		}
		
		if(insideStartGame){
			  if(StartbuttonScale < 1.05f)
			    StartbuttonScale += scaleStep * delta;
			 
			  if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
			    stateBasedGame.enterState(GameState.GAMEPLAYSTATE);
			  }
			}else{
			  if(StartbuttonScale > 1.0f)
			    StartbuttonScale -= scaleStep * delta;
			}
			 
			if(insideExit)
			{
			   if(ExitbuttonScale < 1.05f)
				   ExitbuttonScale +=  scaleStep * delta;
			   if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
				    gameContainer.exit();
			}else{
			  if(ExitbuttonScale > 1.0f)
				  ExitbuttonScale -= scaleStep * delta;
			}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
