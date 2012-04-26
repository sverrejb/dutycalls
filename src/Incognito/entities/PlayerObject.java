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
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Incognito.utils.Constants;


public class PlayerObject extends PlatformerEntity {
	
	
	private int pixelsPerPicX = 103;
	private int pixelsPerPicY = 150;

	
	public PlayerObject(int pointX, int pointY ) throws SlickException{
		super(pointX, pointY);
		//mage test = new Image("img/anim/r1.png");
		SpriteSheet test = new SpriteSheet("img/anim/playerRightTest.png", pixelsPerPicX, pixelsPerPicY);
		setGraphic(test);
		
		/* Each frame duration for animation */
		duration = Constants.PLAYER_ANIMATION_SPEED;		
		
		//setCentered(true);
		
		/* Animation */
		addAnimation(Constants.STAND_STILL_RIGHT, false, 0, 1);
		addFlippedAnimation(Constants.STAND_STILL_LEFT, true, true, false, 0, 1);
		addAnimation(ME.WALK_RIGHT, true, 0, 0, 1, 2, 3, 4, 5);
		addFlippedAnimation(ME.WALK_LEFT, true, true, false, 0, 0, 1, 2, 3, 4, 5);

		
		lastDirection = ME.WALK_RIGHT;
		
		setHitBox(0, 0, pixelsPerPicX, pixelsPerPicY, true);
		
		this.addType(Entity.PLAYER);
		
		
	} 

	
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);/*
	
		
		/*
		 * SKYTING
		 */
		
	}
	

	public void unLoad() {
		
	}
}
