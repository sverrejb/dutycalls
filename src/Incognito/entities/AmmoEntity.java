package Incognito.entities;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AmmoEntity extends ItemEntity{


	public AmmoEntity(float x, float y, float sizeX, float sizeY) throws SlickException {
		super(x, y, new Image("res/img/ammo.png"));
		}
	
	@Override
	public void collisionResponse(Entity other) {
		
		if(other.isType(PLAYER)){
			destroy();
			((PlayerObject)other).addAmmo(20 + (int)Math.random()*20);
		}
	}

}