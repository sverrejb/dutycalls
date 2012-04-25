package Incognito.entities;

import it.marteEngine.entity.Entity;
import it.marteEngine.entity.PlatformerEntity;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class PlayerObject extends PlatformerEntity {
	
	Animation walkRight = null;
	
	Animation playerSprite = null;
	
	float moveX = 0f;
	float moveY = 0f;
	List<Bullet> ammo = new LinkedList<Bullet>();
	int maxAmmo = 40;
	int currentAmmo; 

	
	public PlayerObject(int pointX, int pointY ) throws SlickException{
		super(pointX, pointY);
		Image test = new Image("img/anim/r1.png");
		
		setGraphic(test);
		setHitBox(0, 0, test.getWidth(), test.getHeight(), true);
		
		this.addType(Entity.PLAYER);
		
		//define("SHOOT", Input.MOUSE_LEFT_BUTTON);
		
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
		/*
		Input input = gameContainer.getInput();
		
		float mouseX;
		float mouseY;
		
		if(check("SHOOT")){
			mouseX = input.getMouseX();
			mouseY = input.getMouseY();
			if(ammo.size() < maxAmmo){
				ammo.add(new Bullet((int)this.getX(), (int)this.getY()));
				ammo.get(ammo.size() -1).init(gameContainer, stateBasedGame);
				ammo.get(ammo.size() -1).shoot(mouseX, mouseY, this.getX(), this.getY());
				currentAmmo--;
			}
			
		}
		
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
