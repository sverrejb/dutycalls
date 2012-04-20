package Incognito;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerObject extends GameObject {
	
	Animation playerSprite = null;
	
	public PlayerObject(float pointX, float pointY ){
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		
	} 
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
		//gameContainer.set
		
		Image[] right = {new Image("img/anim/r1.png"), new Image("img/anim/r2.png"), new Image("img/anim/r3.png"), new Image("img/anim/r4.png"), new Image("img/anim/r5.png"), new Image("img/anim/r6.png")};
		int [] duration = {150, 150, 150, 150, 150, 150};
		Animation walk = new Animation (right, duration, true);
		width = walk.getWidth();
		height = walk.getHeight();
		
		playerSprite = walk;
		
	}
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		playerSprite.draw(pointX,pointY);
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		super.update(gameContainer, stateBasedGame, delta);
	}
}
