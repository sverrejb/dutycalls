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
	
	private static PlayerObject player;
	
	private final float gunCenterX = 0f;
	private final float gunCenterY = -20f;
	
	private float playerGunStartPosY = 75f;
	
	private final float playerGunPosX = 50f;
	private float playerGunPosY = playerGunStartPosY;
	private Image gun = new Image("img/anim/gun.png");
	
	private Vector2f bulletExit;

	public WeaponObject(PlayerObject player) throws SlickException{
		super(player.x, player.y);
		
		
		setGraphic(gun);
		
		gun.setCenterOfRotation(400, 400);
		this.centered = true;
		
		depth = 12;
		
		this.player = player;
		
		collidable = false;
		
		define("SHOOT", Input.MOUSE_LEFT_BUTTON);
		
		/* An alarm wich will be fires each time the player shoots */
		setAlarm("FIRE_RATE", Constants.WEAPON_FIRE_RATE, true, false);
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		Input input = gameContainer.getInput();
		
		float centerX = player.x + playerGunPosX;
		float centerY = player.y + playerGunPosY;
		
		
		// + Fixes for Camera movement
		float mouseX = input.getMouseX() + ME.world.camera.cameraX +7;
		float mouseY = input.getMouseY() + ME.world.camera.cameraY +7;
		
		bulletExit = new Vector2f(mouseX - x, mouseY - y);
		bulletExit.normalise();
		//2. Find out their position relative to each other (angle)
		//arctan(Y/X) - arctan(Y/X)	
		double angle = Math.toDegrees(Math.atan2((mouseY)- (centerY), mouseX - centerX));
		
		//Position gun in the middle
		centerX += gunCenterX;
		centerY += gunCenterY;
		
		this.x = centerX;
		this.y = centerY;
		
		gun.setCenterOfRotation(400, 400);
		setAngle((int)angle);

		if(!player.isDirectionRight()){
			setGraphic(gun.getFlippedCopy(false, true));
		}
		else{
			setGraphic(gun);
		}
		

				
		if(check("SHOOT") && canShoot){
			
			
			if(player.getAmmo() > 0){
				Bullet bullet = new Bullet(((this.width/2)* bulletExit.getX() + x)+ (bulletExit.getY()),
						((this.width/2) * bulletExit.getY() + y)+ (bulletExit.getX()));	
				
				bullet.shoot(bulletExit);
				
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
