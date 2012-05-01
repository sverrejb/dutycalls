package Incognito.entities.enemies;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;
import it.marteEngine.entity.PhysicsEntity;

public class EnemyObject extends PhysicsEntity{
	
	private enum STATES {KILL_MODE, WANDER, DEAD}
	
	private int health = 20;
	
	private boolean isRight = true;
	
	List<Entity> bulletHits = new ArrayList<Entity>();
	
	public EnemyObject(int x, int y, Image image){
		super(x, y);		
		
		setGraphic(image);
		collidable = true;
		setHitBox(0, 0, image.getWidth(), image.getHeight(), true);
		
		addType(Entity.ENEMY);
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
		
		if(health <= 0){
			
			destroy();
			
			if(this instanceof GroundEnemy)
				((GroundEnemy) this).weapon.destroy();
		}
		
		
		
		//float playerPosX = ((PlayerObject)ME.world.getEntities(PLAYER)).x;
		//float playerPosY = ((PlayerObject)ME.world.getEntities(PLAYER)).y;
	}
	
	public void shot(int damage, Entity bullet){
		//Herp derp løsning
		if(!bulletHits.contains(bullet)){
			bulletHits.add(bullet);
			health -= damage;
		}
	}
	
	public boolean isDirectionRight(){
		return isRight;
	}
}
