package Incognito.entities;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Incognito.utils.Action;

public class PlayerObject extends GameObject {
	
	Animation walkRight = null;
	
	Animation playerSprite = null;
	
	float moveX = 0f;
	float moveY = 0f;
	List<Bullet> ammo = new LinkedList<Bullet>();
	int maxAmmo = 40;
	int currentAmmo; 
	
	public PlayerObject(int pointX, int pointY ){
		super(pointX, pointY);
		
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
		
		currentAmmo = maxAmmo;
		
	}
	
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		playerSprite.draw(pointX,pointY);
		g.drawString("Ammo: " + currentAmmo, 700, 10);
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
		Input input = gameContainer.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		double newX = moveX/delta;
		pointX += newX;
		
		moveX -= newX;
		
		double newY = moveY/delta;
		pointY += newY;
		
		moveY -= newY;
		
		super.update(gameContainer, stateBasedGame, delta);
		
		if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
			if(ammo.size() < maxAmmo){
				ammo.add(new Bullet((int)this.getX(), (int)this.getY()));
				ammo.get(ammo.size() -1).init(gameContainer, stateBasedGame);
				ammo.get(ammo.size() -1).shoot(mouseX, mouseY, this.getX(), this.getY());
				currentAmmo--;
			}
			
		}
		
		if(!ammo.isEmpty())
			for(Bullet bullet : ammo)
				bullet.update(gameContainer, stateBasedGame, delta);
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
		
		if(action == Action.JUMP){
			moveY = action.getDir().y * action.getValue();
			//onGround = false;
		}
	}
}
