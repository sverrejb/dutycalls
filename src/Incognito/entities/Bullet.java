package Incognito.entities;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;
import it.marteEngine.entity.PhysicsEntity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import Incognito.utils.Constants;
import Incognito.entities.enemies.EnemyObject;

public class Bullet extends PhysicsEntity {
	
	private static Image bullet;
	
	public static int bulletsCount;
	
	private int damage = 10;
	
	public Bullet(float x, float y) throws SlickException{
		super(x, y);
		
		bulletsCount++;
		
		if(bullet == null)
			bullet = new Image("img/bullet.png");
		
		setGraphic(bullet);
		setHitBox(0, 0, bullet.getWidth(), bullet.getHeight(), true);
		
		depth = 3;
		
		/* Remove gravity */
		this.gravity = 0f;
		
		
		this.addType(Entity.BULLET, Entity.SOLID);
	}
	
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
		if(y < ME.world.camera.camraCenterY - Constants.BULLET_BOUNDRY || y > ME.world.camera.camraCenterY + Constants.BULLET_BOUNDRY
				|| x > ME.world.camera.camraCenterX + Constants.BULLET_BOUNDRY || x < ME.world.camera.camraCenterX -Constants.BULLET_BOUNDRY)
			destroy();
	}
	

	public void unLoad() {
		bullet = null;
	}
	
	public void shoot(Vector2f movment) {
		speed.set(movment.getX() * Constants.BULLET_SPEED, movment.getY() * Constants.BULLET_SPEED);
	}
	
	@Override
	public void collisionResponse(Entity other) {
		// called when colliding with another entity
		//if(!other.isType(PLAYER)){
		if(other.isType(Entity.ENEMY)){
			((EnemyObject)other).shot(damage, this);
		}
		else if(other.isType(PLAYER))
			((PlayerObject)other).shot(damage, this);
			
		destroy();
		bulletsCount--;
	}
}
