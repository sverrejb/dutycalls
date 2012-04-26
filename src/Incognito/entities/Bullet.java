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

public class Bullet extends PhysicsEntity {
	
	private static Image bullet;
	
	public static int bulletsCount;
	
	public Bullet(float x, float y) throws SlickException{
		super(x, y);
		
		bulletsCount++;
		
		if(bullet == null)
			bullet = new Image("img/bullet.png");
		
		setGraphic(bullet);
		setHitBox(0, 0, bullet.getWidth(), bullet.getHeight(), true);
		
		
		this.addType(Entity.SOLID);
	}
	
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
		if(y < Constants.GAME_ROOF || y > -Constants.GAME_ROOF)
			destroy();
		
	}
	

	public void unLoad() {
		bullet = null;
	}
	
	public void shoot(float mouseX, float mouseY) {
		Vector2f movment = new Vector2f(mouseX - x,mouseY - y);
		movment.normalise();
		
		//acceleration.add(new Vector2f(movment.getX() * Constants.BULLET_SPEED, movment.getY() * Constants.BULLET_SPEED));
		speed.set(movment.getX() * Constants.BULLET_SPEED, movment.getY() * Constants.BULLET_SPEED);
	}
	
	@Override
	public void collisionResponse(Entity other) {
		// called when colliding with another entity
		if(!other.isType(PLAYER)){
			bulletsCount--;
			//System.out.println("byebye");
			destroy();
			//ME.world.remove(this);
		}
	}
	
	
}
