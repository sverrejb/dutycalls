package Incognito.entities.enemies;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import Incognito.entities.AmmoEntity;
import Incognito.entities.MedpackEntity;
import Incognito.utils.Constants;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;
import it.marteEngine.entity.PhysicsEntity;

public class EnemyObject extends PhysicsEntity{
	private int health = 20;
	private int picWidth = 103;
	private int picHight = 160;
	private Sound shoot = null;
	
	protected boolean isRight = true;
	
	List<Entity> bulletHits = new ArrayList<Entity>();
	
	public EnemyObject(int x, int y, SpriteSheet spriteSheet) throws SlickException{
		super(x, y);		
		
		setGraphic(spriteSheet);
		collidable = true;
		this.centered = true;
		setHitBox(-picWidth/2, -picHight/2, picWidth, picHight, true);
		//setHitBox(0, 0, picWidth, picHight, true)
		duration = Constants.PLAYER_ANIMATION_SPEED;
		
		addAnimation(Constants.STAND_STILL_RIGHT, false, 0, 1);
		addFlippedAnimation(Constants.STAND_STILL_LEFT, true, true, false, 0, 1);
		addAnimation(ME.WALK_RIGHT, true, 0, 0, 1, 2);
		addFlippedAnimation(ME.WALK_LEFT, true, true, false, 0, 0, 1, 2);
		
		addType(Entity.ENEMY);
		
		shoot = new Sound("sound/pain.wav");
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
		
		if(health <= 0){
			int dice = (int)(Math.random()*100);
			
			if(dice >= 0 && dice < Constants.HEALTH_PACK_SPAWNCHANCE)
				ME.world.add(new MedpackEntity(this.x, this.y));
			else if(dice >= Constants.HEALTH_PACK_SPAWNCHANCE && dice < Constants.HEALTH_PACK_SPAWNCHANCE + Constants.AMMO_PACK_SPAWNCHANCE)
				ME.world.add(new AmmoEntity(this.x, this.y));
			shoot.play(1f, Constants.EFFECTS_VOLUM);
			destroy();			
			
			if(this instanceof GroundEnemy)
				((GroundEnemy) this).weapon.destroy();
		}
		
		
		
		//float playerPosX = ((PlayerObject)ME.world.getEntities(PLAYER)).x;
		//float playerPosY = ((PlayerObject)ME.world.getEntities(PLAYER)).y;
	}
	
	public void shot(int damage, Entity bullet){
		//Herp derp l�sning
		if(!bulletHits.contains(bullet)){
			bulletHits.add(bullet);
			health -= damage;
		}
	}
	
	public boolean isDirectionRight(){
		return isRight;
	}
}
