package Incognito.entities;

import it.marteEngine.entity.Entity;
import it.marteEngine.entity.PlatformerEntity;
import it.marteEngine.ME;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Incognito.utils.Constants;


public class PlayerObject extends PlatformerEntity {
	
	Animation walkRight = null;
	
	Animation playerSprite = null;
	
	float moveX = 0f;
	float moveY = 0f;
	
	List<Bullet> ammo = new LinkedList<Bullet>();
	
	int currentAmmo = Constants.MAXAMMO; 
	
	private int pixelsPerPicX = 103;
	private int pixelsPerPicY = 150;

	
	public PlayerObject(int pointX, int pointY ) throws SlickException{
		super(pointX, pointY);
		//mage test = new Image("img/anim/r1.png");
		SpriteSheet test = new SpriteSheet("img/anim/playerRightTest.png", pixelsPerPicX, pixelsPerPicY);
		setGraphic(test);
		duration = Constants.ANIMATION_SPEED;		
		addAnimation(Constants.STAND_STILL_RIGHT, false, 0, 1);
		addFlippedAnimation(Constants.STAND_STILL_LEFT, true, true, false, 0, 1);
		addAnimation(ME.WALK_RIGHT, true, 0, 0, 1, 2, 3, 4, 5);
		addFlippedAnimation(ME.WALK_LEFT, true, true, false, 0, 0, 1, 2, 3, 4, 5);
		
		lastDirection = ME.WALK_RIGHT;
		
		setHitBox(0, 0, pixelsPerPicX, pixelsPerPicY, true);
		
		this.addType(Entity.PLAYER);
		
		define("SHOOT", Input.MOUSE_LEFT_BUTTON);
		
		//gameContainer.set
		
		//Image[] right = {new Image("img/anim/r1.png"), new Image("img/anim/r2.png"), new Image("img/anim/r3.png"), new Image("img/anim/r4.png"), new Image("img/anim/r5.png"), new Image("img/anim/r6.png")};
		//int [] duration = {150, 150, 150, 150, 150, 150};
		//walkRight = new Animation (right, duration, true);
		

		
		//width = walkRight.getWidth();
		//height = walkRight.getHeight();
		
		//playerSprite = walkRight;
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
		g.drawString("Ammo: " + currentAmmo, 700, 10);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);/*
	
		
		/*
		 * SKYTING
		 */
		
		Input input = gameContainer.getInput();
		
		float mouseX;
		float mouseY;
		
		if(check("SHOOT")){
			mouseX = input.getMouseX();
			mouseY = input.getMouseY();
			

			if(Bullet.bulletsCount <= Constants.MAXAMMO){
				Bullet bullet = new Bullet(x + 100, y);
				
				bullet.shoot(mouseX + 100, mouseY);
				System.out.println(ME.world.getCount());
				ME.world.add(bullet);
				System.out.println(ME.world.getCount());
			}
			
			/*
			if(ammo.size() < Constants.MAXAMMO){
				ammo.add(new Bullet(this.x, this.y));
				//ammo.get(ammo.size() -1).init(gameContainer, stateBasedGame);
				ammo.get(ammo.size() -1).shoot(mouseX, mouseY, this.x, this.y);
				currentAmmo--;
			}*/
			
		}
		/*
		if(!ammo.isEmpty())
			for(Bullet bullet : ammo)
				bullet.update(gameContainer, delta);*/

		
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
