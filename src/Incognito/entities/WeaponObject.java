package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

import Incognito.utils.Constants;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

public class WeaponObject extends Entity{
	
	private boolean canShoot = true;
	private Vector2f trajectory;
	
	private static PlayerObject player;
	private Image gun = new Image("img/anim/gun.png");
	private Sound shoot = null;

	public WeaponObject(PlayerObject player) throws SlickException{
		
		super(player.x, player.y);
		
		setGraphic(gun);
		this.centered = true;
		depth = 12;
		
		this.player = player;
		collidable = false;
		
		define("SHOOT", Input.MOUSE_LEFT_BUTTON);
		shoot = new Sound("/res/sound/M4A1_Single.wav");
		
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
		//vector for bullet and weapon 
		trajectory = new Vector2f(mouseX - x, mouseY - y);
		trajectory.normalise();
		//sets angle of weapon
		setAngle((int)trajectory.getTheta());
		//sets animation
		if(!player.isDirectionRight())
			setGraphic(gun.getFlippedCopy(false, true));
		else
			setGraphic(gun);
				
		if(check("SHOOT") && canShoot){	
			if(player.getAmmo() > 0){
				Bullet bullet = new Bullet(((this.width/2)* trajectory.getX() + x)+ (trajectory.getY()),
						((this.width/2) * trajectory.getY() + y)+ (trajectory.getX()));		
				
				shoot.play(1f, Constants.EFFECTS_VOLUM);
				bullet.shoot(trajectory);
				
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
