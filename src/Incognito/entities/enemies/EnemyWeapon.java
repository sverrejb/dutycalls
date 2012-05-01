package Incognito.entities.enemies;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import Incognito.entities.Bullet;
import Incognito.utils.Constants;

public class EnemyWeapon extends Entity{
	private int ammo = Constants.MAXAMMO;
	private boolean canShoot = false;
	
	private EnemyObject enemy;
	
	private final float gunCenterX = 0f;
	private final float gunCenterY = -20f;

	private float playerGunStartPosY = 75f;
	
	private final float enemyGunPosX = 50f;
	
	private float enemyGunPosY = playerGunStartPosY;
	private static Image gun;
	
	private float aimX = 0f;
	private float aimY = 0f;
	
	private Vector2f bulletExit;

	public EnemyWeapon(EnemyObject player) throws SlickException{
		super(player.x, player.y);
		
		if(gun == null)
			gun = new Image("img/anim/gun.png");
		
		setGraphic(gun);
		
		gun.setCenterOfRotation(400, 400);
		this.centered = true;
		
		depth = 12;
		
		this.enemy = player;
		
		collidable = false;
		
		/* An alarm wich will be fires each time the player shoots */
		setAlarm("SHOOT", Constants.WEAPON_FIRE_RATE, false, false);
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {	
				
		float centerX = enemy.x + enemyGunPosX;
		float centerY = enemy.y + enemyGunPosY;
		
		
		// + Fixes for Camera movement
		aimX += 7;
		aimY += 7;
		
		bulletExit = new Vector2f(aimX - x, aimY - y);
		bulletExit.normalise();
		//2. Find out their position relative to each other (angle)
		//arctan(Y/X) - arctan(Y/X)	
		double angle = Math.toDegrees(Math.atan2((aimY)- (centerY), aimX - centerX));
		
		//Position gun in the middle
		centerX += gunCenterX;
		centerY += gunCenterY;
		
		this.x = centerX;
		this.y = centerY;
		
		gun.setCenterOfRotation(400, 400);
		setAngle((int)angle);

		if(!enemy.isDirectionRight()){
			setGraphic(gun.getFlippedCopy(false, true));
		}
		else{
			setGraphic(gun);
		}
		

				
		if(canShoot){
			canShoot = false;
			
			if(ammo > 0){
				Bullet bullet = new Bullet(((this.width/2)* bulletExit.getX() + x)+ (bulletExit.getY()),
						((this.width/2) * bulletExit.getY() + y)+ (bulletExit.getX()));	
				
				bullet.shoot(bulletExit);
				
				/* Makes the player unable to fire and start the FIRE_RATE alarm*/
				canShoot = false;
				restartAlarm("FIRE_RATE");
				
				ME.world.add(bullet);
				ammo--;
			}	
		}
		
		super.update(gameContainer, delta);		
	}
	
	public void setAimDir(int x, int y){
		aimX = x;
		aimY = y;
	}
	
	public void setAimX(float aimX) {
		this.aimX = aimX;
	}

	public void setAimY(float aimY) {
		this.aimY = aimY;
	}
	
	@Override
	public void alarmTriggered(String name) {
		if(name.equals("SHOOT")){
			canShoot = true;
		}
	}
}
