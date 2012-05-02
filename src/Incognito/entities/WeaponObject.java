package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import Incognito.utils.Constants;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

public class WeaponObject extends Entity{
	
	private boolean canShoot = true;
	private Vector2f bulletVector;
	
	private static PlayerObject player;
	private Image gun = new Image("img/anim/gun.png");

	public WeaponObject(PlayerObject player) throws SlickException{
		
		super(player.x, player.y);
		
		setGraphic(gun);
		this.centered = true;
		depth = 12;
		
		this.player = player;
		collidable = false;
		
		define("SHOOT", Input.MOUSE_LEFT_BUTTON);
		
		/* An alarm which will be fired each time the player shoots */
		setAlarm("FIRE_RATE", Constants.WEAPON_FIRE_RATE, true, false);
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		
		Input input = gameContainer.getInput();
		this.x= player.x;
		this.y = player.y - 10;
		
		// MousePos + CameraPos
		float mouseX = input.getMouseX() + ME.world.camera.cameraX +7;
		float mouseY = input.getMouseY() + ME.world.camera.cameraY +7;
		
		bulletVector = new Vector2f(mouseX - x, mouseY - y);
		bulletVector.normalise();

		double angle = Math.toDegrees(Math.atan2((mouseY)- (this.y), mouseX - this.x));
		
		setAngle((int)angle);

		if(!player.isDirectionRight()){
			setGraphic(gun.getFlippedCopy(false, true));
		}
		else{
			setGraphic(gun);
		}
		

				
		if(check("SHOOT") && canShoot){
			
			
			if(player.getAmmo() > 0){
				Bullet bullet = new Bullet(((this.width/2)* bulletVector.getX() + x)+ (bulletVector.getY()),
						((this.width/2) * bulletVector.getY() + y)+ (bulletVector.getX()));	
				
				bullet.shoot(bulletVector);
				
				/* Makes the player unable to fire and start the FIRE_RATE alarm*/
				canShoot = false;
				restartAlarm("FIRE_RATE");
				
				ME.world.add(bullet);
				player.setAmmo(player.getAmmo()-1);
			}	
		}
		
		super.update(gameContainer, delta);		
	}
	
	@Override
	public void alarmTriggered(String name) {
		if(name.equals("FIRE_RATE"))
			canShoot = true;
	}

}
