package Incognito.entities;

import it.randomtower.engine.entity.Entity;
import it.randomtower.engine.entity.PhysicsEntity;
import it.randomtower.engine.entity.PlatformerEntity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Incognito.utils.Action;

public class PlayerObject extends PhysicsEntity {
	
	Animation walkRight = null;
	
	Animation playerSprite = null;
	
	float moveX = 0f;
	float moveY = 0f;
	

	private static final String CMD_LEFT = "left";
	private static final String CMD_RIGHT = "right";
	private static final String CMD_JUMP = "jump";
	private boolean onGround = false;
	private int moveSpeed = 1;
	private int jumpSpeed = 6;

	
	public PlayerObject(int pointX, int pointY ) throws SlickException{
		super(pointX, pointY);
		
		//this.pointX = pointX;
		//this.pointY = pointY;
		
		this.addType(Entity.PLAYER);
		//curr
		
		//gameContainer.set
		
		//Image[] right = {new Image("img/anim/r1.png"), new Image("img/anim/r2.png"), new Image("img/anim/r3.png"), new Image("img/anim/r4.png"), new Image("img/anim/r5.png"), new Image("img/anim/r6.png")};
		//int [] duration = {150, 150, 150, 150, 150, 150};
		//walkRight = new Animation (right, duration, true);
		
		Image test = new Image("img/anim/r1.png");
		
		setGraphic(test);
		setHitBox(0, 0, test.getWidth(), test.getHeight(), true);
		
		//width = walkRight.getWidth();
		//height = walkRight.getHeight();
		
		//playerSprite = walkRight;
		
		depth = 10;
		
		define(CMD_JUMP, Input.KEY_UP, Input.KEY_X, Input.KEY_W);
		define(CMD_RIGHT, Input.KEY_RIGHT, Input.KEY_D);
		define(CMD_LEFT, Input.KEY_LEFT, Input.KEY_A);
	} 
	/*
	@Override
	public void init(GameContainer gameContainer) throws SlickException{
		super.init(gameContainer);
		
		//gameContainer.set
		
		//Image[] right = {new Image("img/anim/r1.png"), new Image("img/anim/r2.png"), new Image("img/anim/r3.png"), new Image("img/anim/r4.png"), new Image("img/anim/r5.png"), new Image("img/anim/r6.png")};
		//int [] duration = {150, 150, 150, 150, 150, 150};
		//walkRight = new Animation (right, duration, true);
		
		Image test = new Image("img/anim/r1.png");
		
		setGraphic(test);
		setHitBox(0, 0, test.getWidth(), test.getHeight(), true);
		
		//width = walkRight.getWidth();
		//height = walkRight.getHeight();
		
		//playerSprite = walkRight;
		
	}*/
	
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
		
		//playerSprite.draw(pointX,pointY);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
		//are we on the ground?
		onGround = false;
		if (collide(SOLID, x, y + 1) != null) 
		{ 
			onGround = true;
		}
		
		//set acceleration to nothing
		acceleration.x = 0;
		
		//increase acceeration, if we're not going too fast
		if (check(CMD_LEFT) && speed.x > -maxSpeed.x) {
			acceleration.x = - moveSpeed;
		}
		if (check(CMD_RIGHT) && speed.x < maxSpeed.x) {
			acceleration.x = moveSpeed;
		}
		
		//friction (apply if we're not moving, or if our speed.x is larger than maxspeed)
		if ( (! check(CMD_LEFT) && ! check(CMD_RIGHT)) || Math.abs(speed.x) > maxSpeed.x ) {
			friction(true, false);
		}
		
		//jump
		if ( pressed(CMD_JUMP) ) 
		{
			//normal jump
			if (onGround) { 
				speed.y = -jumpSpeed; 
			}
		}
		
		//set the gravity
		gravity(delta);
		
		//make sure we're not going too fast vertically
		//the reason we don't stop the player from moving too fast left/right is because
		//that would (partially) destroy the walljumping. Instead, we just make sure the player,
		//using the arrow keys, can't go faster than the max speed, and if we are going faster
		//than the max speed, descrease it with friction slowly.
		maxspeed(false, true);
		
		//variable jumping (tripple gravity)
		if (speed.y < 0 && !check(CMD_JUMP)) {
			gravity(delta);
			gravity(delta);
		}
		
		//set the motion. We set this later so it stops all movement if we should be stopped
		motion(true, true);
		
		previousx = x;
		previousy = y;
	}
	

	public void unLoad() {
		walkRight = null;
		
		playerSprite = null;
	}
	/*
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
		
		if(action == Action.JUMP){
			moveY = action.getDir().y * action.getValue();
			//onGround = false;
		}
	}*/
}
