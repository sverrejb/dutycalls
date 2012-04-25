package Incognito.entities;

import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/*
 * Controls the ground
 */
public class GroundObject extends Entity {
	protected TiledMap ground;
	
	//Two dim array representing each tiles blocked value
	protected boolean[][] blocked;
	
	public GroundObject(int x, int y, Image image) {
		super(x, y);
		
		setGraphic(image);
		
		setHitBox(0, 0, image.getWidth(), image.getHeight(), true);
		
		depth = 0;
		
		addType(Entity.SOLID);
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
		
		//ground.render(0, 0);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
	}
	
	
	@Override
	public void collisionResponse(Entity other) {
		// called when colliding with another entity
	}
}
