package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;


public class BackgroundObject extends Entity {
	
	private Image image;
	
	public BackgroundObject(int x, int y, Image image){
		super(x,y);
		
		this.image = image;
		
		setGraphic(image);
		setHitBox(0, 0, image.getWidth(), image.getHeight(), true);
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
	}
	
}
