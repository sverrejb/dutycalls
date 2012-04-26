package Incognito.entities;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Incognito.utils.Constants;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

public class WeaponObject extends Entity{
	
	//List<Bullet> ammo = new LinkedList<Bullet>();
	
	//int currentAmmo = Constants.MAXAMMO; 
	
	private static PlayerObject player;
	
	private final float gunCenterX = 0f;
	private final float gunCenterY = -20f;
	
	private final float playerGunPosX = 50f;
	private final float playerGUnPosY = 75f;

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
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		Input input = gameContainer.getInput();
		
		float centerX = player.x + playerGunPosX;
		float centerY = player.y + playerGUnPosY;
		
		// + Fixes for Camera movement
		float mouseX = input.getMouseX() + ME.world.camera.cameraX;
		float mouseY = input.getMouseY() + ME.world.camera.cameraY;
		
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
		
		//float mouseX;
		//float mouseY;
		
		if(check("SHOOT")){
			mouseX = input.getMouseX();
			mouseY = input.getMouseY();
			
			System.out.println(Bullet.bulletsCount);
			if(Bullet.bulletsCount <= Constants.MAXAMMO){
				Bullet bullet = new Bullet(x + 150, y);
				
				bullet.shoot(mouseX + 150, mouseY);
			//	System.out.println(ME.world.getCount());
				ME.world.add(bullet);
			//	System.out.println(ME.world.getCount());
			}	
		}
		
	}

}
