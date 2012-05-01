package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;
import it.marteEngine.entity.PhysicsEntity;

public class ItemEntity extends PhysicsEntity{
	
	private boolean used = false;

	public ItemEntity(float x, float y, Image image) {
		super(x, y);
		
		setGraphic(image);
		collidable = true;
		setHitBox(0, 0, image.getWidth(), image.getHeight(), true);
		addType(Entity.ITEM, Entity.SOLID);
		depth = 4;
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
		
		

	}
	public boolean isUsed(){
		return used;
	}
	public void setUsed(boolean used){
		this.used = used;
	}

}
