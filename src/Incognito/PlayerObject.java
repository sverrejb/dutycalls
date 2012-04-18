package Incognito;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerObject extends GameObject {
	
	Image playerSprite = null;
	
	public PlayerObject(float pointX, float pointY ){
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		
	} 
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
		//gameContainer.set
		playerSprite = new Image("img/sprite.png");
		
	}
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		playerSprite.draw(pointX,pointY);
		System.out.println(pointX + "  " + pointY);
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		super.update(gameContainer, stateBasedGame, delta);
	}
}
