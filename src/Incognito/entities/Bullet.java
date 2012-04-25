package Incognito.entities;

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
	
	public Bullet(float x, float y) throws SlickException{
		super(x, y);
		
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
		
	}
	

	public void unLoad() {
		bullet = null;
	}
	
	public void shoot(float mouseX, float mouseY, float pointX, float pointY) {
		Vector2f movment = new Vector2f(mouseX - pointX,mouseY - pointY);
		movment.normalise();
		speed.set(movment.getX() * Constants.BULLET_SPEED, movment.getY() * Constants.BULLET_SPEED);

	}
	
	
}
