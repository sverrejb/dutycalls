package Incognito.entities;


import it.marteEngine.entity.Entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import Incognito.utils.Constants;

public class AmmoEntity extends ItemEntity{
	

	public AmmoEntity(float x, float y) throws SlickException {
		super(x, y, new Image("res/img/ammo.png"), new Sound("res/sound/ammoPickup.wav"));
		}
	
	@Override
	public void collisionResponse(Entity other) {
		
		if(other.isType(PLAYER)){
			this.destroy();
			if(!isUsed()){
				pickup.play(1f, Constants.EFFECTS_VOLUM);
				((PlayerObject)other).addAmmo(Constants.AMMO_PACK_AMOUNT + (int)(Math.random()*Constants.AMMO_PACK_BONUSAMOUNT));	
				setUsed(true);
			}
		}
	}

}