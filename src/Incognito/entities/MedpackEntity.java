package Incognito.entities;

import it.marteEngine.entity.Entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MedpackEntity extends ItemEntity {

	public MedpackEntity(float x, float y, float sizeX, float sizeY) throws SlickException {
		super(x, y, new Image("res/img/healthpack.png"));
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void collisionResponse(Entity other) {
		
		if(other.isType(PLAYER)){
			destroy();
			((PlayerObject)other).addHealth(20 + (int)Math.random()*20);
		}
	}

}
