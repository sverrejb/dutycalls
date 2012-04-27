package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.util.pathfinding.*;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;
import it.marteEngine.entity.PhysicsEntity;

import Incognito.entities.*;

public class EnemyObject extends PhysicsEntity{
	
	private enum STATES {KILL_MODE, WANDER, DEAD}
	
	private int health = 100;
	
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
		
		if(health <= 0)
			destroy();
		
		super.update(gameContainer, delta);
		
		//float playerPosX = ((PlayerObject)ME.world.getEntities(PLAYER)).x;
		//float playerPosY = ((PlayerObject)ME.world.getEntities(PLAYER)).y;
	}
	
	public void shot(int damage){
		health -= damage;
	}
	
	@Override
	public void collisionResponse(Entity other) {
		if(other.isType(BULLET)){
			System.out.println("AUCH");
			other.destroy();
		}
	}
}
