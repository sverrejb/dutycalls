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
	private int ammo = 40;
	private boolean canShoot = false;
	
	private EnemyObject enemy;

	private static Image gun;
	
	private float aimX = 0f;
	private float aimY = 0f;
	
	private Vector2f trajectory;
	
	private boolean shootingAlarmActive = false;

	public EnemyWeapon(EnemyObject player) throws SlickException{
		super(player.x, player.y);
		
		gun = new Image("img/anim/gun.png");
		
		setGraphic(gun);
		this.centered = true;
		depth = 12;
		
		this.enemy = player;
		collidable = false;
		
		/* An alarm wich will be fires each time the player shoots */
		setAlarm("SHOOT", Constants.ENEMY_TRIGGER_TIME, false, false);
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {	
				
		this.x = enemy.x;
		this.y = enemy.y -10;
		
		
		trajectory = new Vector2f(aimX - x, aimY - y);
		trajectory.normalise();
		
		setAngle((int)trajectory.getTheta());

		if(!enemy.isDirectionRight()){
			setGraphic(gun.getFlippedCopy(false, true));
		}
		else{
			setGraphic(gun);
		}
		

				
		if(canShoot){
			canShoot = false;
			
			if(ammo > 0){
				Bullet bullet = new Bullet(((this.width/2)* trajectory.getX() + x)+ (trajectory.getY()),
						((this.width/2) * trajectory.getY() + y)+ (trajectory.getX()));	
				
				bullet.shoot(trajectory);
				
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
	
	public void tryShoot(){
		if(!shootingAlarmActive){
			restartAlarm("SHOOT");
			shootingAlarmActive = true;
		}
	}
	
	public void stopShoot(){
		if(shootingAlarmActive){
			pauseAlarm("BULLET");
			shootingAlarmActive = false;
		}
	}
	
	@Override
	public void alarmTriggered(String name) {
		if(name.equals("SHOOT")){
			canShoot = true;
		}
	}
}
