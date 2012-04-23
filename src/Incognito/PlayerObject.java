package Incognito;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.Force;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerObject extends StandardObject {
	
	Animation walkRight = null;
	
	Animation playerSprite = null;
	
	private float moveX = 0f;
	private float moveY = 0f;
	
	public PlayerObject(float pointX, float pointY ){
		super();
		this.pointX = pointX /SCALE;
		this.pointY = pointY /SCALE;
		
	} 
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
		//gameContainer.set
		
		Image[] right = {new Image("img/anim/r1.png"), new Image("img/anim/r2.png"), new Image("img/anim/r3.png"), new Image("img/anim/r4.png"), new Image("img/anim/r5.png"), new Image("img/anim/r6.png")};
		int [] duration = {150, 150, 150, 150, 150, 150};
		walkRight = new Animation (right, duration, true);
		
		width = walkRight.getWidth()/ SCALE;
		height = walkRight.getHeight() / SCALE;
		
		playerSprite = walkRight;
		
		Rectangle rect = new Rectangle(width, height);
		//rect.translate(pointX, pointY);
		addFixture(new BodyFixture(rect));
		
		setMass();
		
		//System.out.println(transform.);
		
		transform.setTranslation(new Vector2(pointX, pointY));
		
		//System.out.println(this.getFixture(0).getShape().);
		
	}
	
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		//Vector2 vector = this.getFixture(0).getShape().getCenter();
		
		playerSprite.draw((float)this.transform.getTranslationX() * SCALE, (float)this.transform.getTranslationY() * SCALE);
		//System.out.println((float)this.transform.getTranslationX() + "   "+ (float)this.transform.getTranslationY());
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		/*
		double newX = moveX/delta;
		dx += newX;
		
		moveX -= newX;
		
		double newY = moveY/delta;
		dy += newY;
		
		moveY -= newY;*/
		
		super.update(gameContainer, stateBasedGame, delta);
	}
	
	@Override
	public void unLoad() {
		walkRight = null;
		
		playerSprite = null;
	}
	
	public void movePlayer(Action action){
		if(action == Action.SHOOT){
			//System.out.println((float)this.transform.getTranslationX() * SCALE+ "  " + (float)this.transform.getTranslationY() * SCALE);
		}
		
		if(action == Action.LEFT){
			moveX = action.getDir().x * action.getValue();
			this.apply(new Force(action.getDir().x * action.getValue(), 0));
			
		}
		
		if(action == Action.RIGHT){
			moveX = action.getDir().x * action.getValue();
			this.apply(new Force(action.getDir().x * action.getValue(), 0));
			
		}
		
		if(action == Action.JUMP){
			
			this.apply(new Force(0, -400));
		}
	}
}
