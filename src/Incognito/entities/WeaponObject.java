package Incognito.entities;

import java.util.LinkedList;
import java.util.List;

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
 
	private int ammo = Constants.MAXAMMO;
	private static PlayerObject player;
	
	private final float gunCenterX = 0f;
	private final float gunCenterY = -20f;
	
	private final float playerGunPosX = 50f;
	private final float playerGUnPosY = 75f;
	
	private Vector2f bulletExit;

	public WeaponObject(PlayerObject player) throws SlickException{
		super(player.x, player.y);
		
		Image gun = new Image("img/anim/gun.png");
		
		setGraphic(gun);
		
		
		depth = 12;
		
		this.player = player;
		
		collidable = false;
		
		define("SHOOT", Input.MOUSE_LEFT_BUTTON);
		
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
		g.drawString("Ammo:    "+ ammo, ME.world.camera.cameraX + 10, 35);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		Input input = gameContainer.getInput();
		
		float centerX = player.x + playerGunPosX;
		float centerY = player.y + playerGUnPosY;
		
		// + Fixes for Camera movement
		float mouseX = input.getMouseX() + ME.world.camera.cameraX +7;
		float mouseY = input.getMouseY() + ME.world.camera.cameraY +7;
		
		//2. Find out their position relative to each other (angle)
		//arctan(Y/X) - arctan(Y/X)	
		double angle = Math.toDegrees(Math.atan2((mouseY)- (centerY), mouseX - centerX));
		
		//Position gun in the middle
		centerX += gunCenterX;
		centerY += gunCenterY;
		
		this.x = centerX;
		this.y = centerY;
		
		setAngle((int)angle);
		
		
		super.update(gameContainer, delta);
				
		if(check("SHOOT")){
			bulletExit = new Vector2f(mouseX - x, mouseY - y);
			bulletExit.normalise();
			if(ammo > 0){
				Bullet bullet = new Bullet(((this.width)* bulletExit.getX() + x)+ (bulletExit.getY()) * (this.height/5),
						((this.width) * bulletExit.getY() + y)+ (bulletExit.getX()) * (this.height/5));	
				
				bullet.shoot(bulletExit);
				ME.world.add(bullet);
				ammo--;
			}	
		}
		
	}

}
