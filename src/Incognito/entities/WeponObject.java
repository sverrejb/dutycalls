package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

public class WeponObject extends Entity{
	
	private static PlayerObject player;
	
	private final float gunCenterX = 0f;
	private final float gunCenterY = -20f;
	
	private final float playerGunPosX = 50f;
	private final float playerGUnPosY = 75f;

	public WeponObject(PlayerObject player) throws SlickException{
		super(player.x, player.y);
		
		Image gun = new Image("img/anim/gun.png");
		
		setGraphic(gun);
		
		
		depth = 12;
		
		this.player = player;
		
		collidable = false;
		
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
		
	}

}
