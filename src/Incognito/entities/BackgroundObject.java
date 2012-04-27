package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;


public class BackgroundObject extends Entity {
	
	private Image image;
	
	private float fixPosX = 0f;
	private float fixPosY = 0f;
	
	public BackgroundObject(int x, int y, Image image){
		super(x,y);
		
		this.image = image;
		
		setGraphic(image);
		//setHitBox(0, 0, image.getWidth(), image.getHeight(), false);
		collidable = false;
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		float camPosX = ME.world.camera.cameraX;
		float camPosY = ME.world.camera.cameraY;
		
		if( image.getWidth() % camPosX == 0)
			fixPosX = camPosX;
			
		x = fixPosX;
		y = fixPosY;
			
		super.update(gameContainer, delta);
		x = ME.world.camera.cameraX/2;
	}
	
}
