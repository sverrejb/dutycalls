package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.util.pathfinding.*;

import it.marteEngine.ME;
import it.marteEngine.entity.PhysicsEntity;

import Incognito.entities.*;

public class EnemyObject extends PhysicsEntity{
	public EnemyObject(int x, int y){
		super(x, y);		
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
		
		float playerPosX = ((PlayerObject)ME.world.getEntities(PLAYER)).x;
		float playerPosY = ((PlayerObject)ME.world.getEntities(PLAYER)).y;
	}
}
