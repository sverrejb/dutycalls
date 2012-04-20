package Incognito;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerObject extends GameObject {
	
	Animation walkRight = null;
	
	Animation playerSprite = null;
	
	float moveX = 0f;
	float moveY = 0f;
	
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
		walkRight = new Animation (right, duration, true);
		
		width = walkRight.getWidth();
		height = walkRight.getHeight();
		
		playerSprite = walkRight;
		
	}
	
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		playerSprite.draw(pointX,pointY);
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
		double newX = moveX/delta;
		pointX += newX;
		
		moveX -= newX;
		
		double newY = moveY/delta;
		pointY += newY;
		
		moveY -= newY;
		
		super.update(gameContainer, stateBasedGame, delta);
	}
	
	@Override
	public void unLoad() {
		walkRight = null;
		
		playerSprite = null;
	}
	
	public void movePlayer(Action action){
		if(action == Action.SHOOT){
			System.out.println("pewpew");
		}
		
		if(action == action.LEFT){
			moveX = action.getDir().x * action.getValue();
		}
		
		if(action == action.RIGHT){
			moveX = action.getDir().x * action.getValue();
		}
	}
}
