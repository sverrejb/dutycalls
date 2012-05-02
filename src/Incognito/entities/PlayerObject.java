package Incognito.entities;

import it.marteEngine.entity.Entity;
import it.marteEngine.entity.PlatformerEntity;
import it.marteEngine.ME;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Incognito.utils.Constants;
import Incognito.utils.Globals;


public class PlayerObject extends PlatformerEntity {
	
	
	private int pixelsPerPicX = 103;
	private int pixelsPerPicY = 150;
	private int maxHealth = 100;
	private int health = maxHealth;
	private int maxAmmo = 100;
	private int ammo = maxAmmo;

	
	public PlayerObject(int pointX, int pointY ) throws SlickException{
		super(pointX, pointY);
		//mage test = new Image("img/anim/r1.png");
		SpriteSheet test = new SpriteSheet("img/anim/playerRightTest.png", pixelsPerPicX, pixelsPerPicY);
		setGraphic(test);
		this.centered = true;
		
		/* Each frame duration for animation */
		duration = Constants.PLAYER_ANIMATION_SPEED;		
		
		//setCentered(true);
		depth = 4;
		
		/* Animation */
		addAnimation(Constants.STAND_STILL_RIGHT, false, 0, 1);
		addFlippedAnimation(Constants.STAND_STILL_LEFT, true, true, false, 0, 1);
		addAnimation(ME.WALK_RIGHT, true, 0, 0, 1, 2, 3, 4, 5);
		addFlippedAnimation(ME.WALK_LEFT, true, true, false, 0, 0, 1, 2, 3, 4, 5);

		
		lastDirection = ME.WALK_RIGHT;
		
		setHitBox(20 - pixelsPerPicX/2, -pixelsPerPicY/2, pixelsPerPicX - 37, pixelsPerPicY, true);
		
		this.addType(Entity.PLAYER);
		
		
	} 

	
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
		g.drawString("Ammo:    "+ ammo, ME.world.camera.cameraX + 10, 35);
		g.drawString("Health:    "+ health, ME.world.camera.cameraX + 160, 35);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		if(y > Constants.GAME_HEIGHT)
			Globals.game.enterState(Constants.LOST_STATE,  new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		
		super.update(gameContainer, delta);		
	}
	

	public void unLoad() {
		
	}



	public void addHealth(int i) {
		if(health + i <= maxHealth)
			health  += i;
		else
			health = maxHealth;
	}



	public int getAmmo() {
		return ammo;
	}



	public void setAmmo(int i) {
		ammo = i;
	}



	public void addAmmo(int i) {
		if(ammo + i <= maxAmmo)
			ammo  += i;
		else
			ammo = maxAmmo;
	}
}
