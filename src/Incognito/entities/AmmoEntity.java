package Incognito.entities;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Incognito.utils.Constants;

public class AmmoEntity extends ItemEntity{


	public AmmoEntity(float x, float y) throws SlickException {
		super(x, y, new Image("res/img/ammo.png"));
		}
	
	@Override
	public void collisionResponse(Entity other) {
		
		if(other.isType(PLAYER)){
			this.destroy();
			if(!isUsed()){
				((PlayerObject)other).addAmmo(Constants.AMMO_PACK_AMOUNT + (int)(Math.random()*Constants.AMMO_PACK_BONUSAMOUNT));	
				setUsed(true);
			}
		}
	}

}