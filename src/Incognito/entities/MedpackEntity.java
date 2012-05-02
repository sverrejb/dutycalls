package Incognito.entities;

import it.marteEngine.entity.Entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import Incognito.utils.Constants;

public class MedpackEntity extends ItemEntity {

	public MedpackEntity(float x, float y) throws SlickException {
		super(x, y, new Image("img/healthpack.png"), new Sound("sound/ammoPickup.wav"));
		collidable = true;
	}
	
	@Override
	public void collisionResponse(Entity other) {
		
		if(other.isType(PLAYER)){
			this.destroy();
			if(!isUsed()){
				pickup.play(1f, Constants.EFFECTS_VOLUM);
				((PlayerObject)other).addHealth(Constants.HEALTH_PACK_AMOUNT + (int)(Math.random()*Constants.HEALTH_PACK_BONUSAMOUNT));	
				setUsed(true);
			}
		}
	}
	


}
