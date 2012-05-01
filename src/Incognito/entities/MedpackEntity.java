package Incognito.entities;

import it.marteEngine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Incognito.utils.Constants;

public class MedpackEntity extends ItemEntity {

	public MedpackEntity(float x, float y) throws SlickException {
		super(x, y, new Image("res/img/healthpack.png"));
		collidable = true;
	}
	
	@Override
	public void collisionResponse(Entity other) {
		
		if(other.isType(PLAYER)){
			this.destroy();
			if(!isUsed()){
				((PlayerObject)other).addHealth(Constants.HEALTH_PACK_AMOUNT + (int)(Math.random()*Constants.HEALTH_PACK_BONUSAMOUNT));	
				setUsed(true);
			}
		}
	}
	


}
