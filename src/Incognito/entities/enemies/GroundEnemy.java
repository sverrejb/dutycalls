package Incognito.entities.enemies;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

import java.util.ArrayList;
import java.util.List;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


import Incognito.utils.Bresenham;
import Incognito.utils.Constants;
import Incognito.utils.Globals;

public class GroundEnemy extends EnemyObject {
	
	private List<Vector2f> waypoints;
	private int currentWaypoint = 0;
	private int direction = 1;
	
	private int eyeRange = Constants.ENEMY_EYE_RANGE;
	
	private Bresenham bresenham = new Bresenham();
	
	protected EnemyWeapon weapon;

	public GroundEnemy(int x, int y, Image image) throws SlickException {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		addType(Entity.ENEMY);
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		//colPoints = new ArrayList<Point>();
		super.update(gameContainer, delta);
		
		
		/*
		 * Checks if the player is seen by our eyes
		 */
		if(playerSeen())
		{
			//Stops the enemmy
			speed.x = 0;
			
			/*
			 * find the angle that we can give to the enemys weapon and bullets
			 */
			//Player vector position
			Vector2f playerCenter = new Vector2f(Globals.player.x + (Globals.player.width/2), Globals.player.y + (Globals.player.height/2));
				
			//Angle from enemy to player according to centerpos
			Vector2f diff = playerCenter.sub(new Vector2f(x + (width/2), y - (height/2)));
			int relativeAngle = (((int) diff.getTheta()) + 90) % 360;
			
			
			
			/*
			 * Attack the player
			 * 
			 */
			if(weapon != null){
				weapon.setAimX(Globals.player.x + (Globals.player.width/2));
				weapon.setAimY(Globals.player.y + (Globals.player.height/2));
			}
			
			
			
		}
		else{
			weapon.setAimDir((int)(x + (width/2) + 20),(int)(y + (height/2)));
		}
		
		/*
		 * Player is not seen
		 * Go in wayPoint direction
		 */
		if(waypoints != null){

			//Are we close to the waypoint?
			if(this.x > waypoints.get(currentWaypoint).x - 10 && x < waypoints.get(currentWaypoint).x + 10){
				currentWaypoint += direction;
			}
			//Fix which waypoint to go after
			if(currentWaypoint >= waypoints.size() -1)
				direction = -1;
			else if(currentWaypoint <= 0 )
				direction = 1;
			
			// set acceleration to nothing
			acceleration.x = 0;

			// increase acceeration, if we're not going too fast			
			//* distance? 
			//Go correct direction
			if(x > waypoints.get(currentWaypoint).x  && speed.x > -maxSpeed.x){
				acceleration.x += Constants.ENEMY_MOVE_SPEED * -1;
				isRight = false;
			}
			else if (x < waypoints.get(currentWaypoint).x && speed.x < maxSpeed.x){
				acceleration.x += Constants.ENEMY_MOVE_SPEED;
				isRight = true;
			}
		}
		
		
		//float playerPosX = ((PlayerObject)ME.world.getEntities(PLAYER)).x;
		//float playerPosY = ((PlayerObject)ME.world.getEntities(PLAYER)).y;
	}
	
	private boolean playerSeen(){
		int playerCenterX = (int)((Globals.player.x + (Globals.player.width/2)));
		int playerCenterY = (int)((Globals.player.y + (Globals.player.height/2)));
		int centerX = (int)((x + (width/2)));
		int centerY = (int)((y + (height/2)));
		
		//Is the enemy even in the screen?
		//Avoid that he waits for us
		//INSERT CODE
		
		
		/*
		 * Check if player is near
		 * If that is the case, then attack
		 */
		//Is the enemy facing the correct direction to spot the player?
		if(centerX > playerCenterX) //enemy to the right of player
			if(isRight) //If enemy watches to the right, then wrong direction
				return false;
		else if(centerX < playerCenterX)
			if(!isRight)
				return false;
		
			
		//uses Bresenhams algorithm to plot a line
		//And check if he is within eyesight
		//can this be optimized? Use maptiles insted of pixels?
		if(bresenham.plot(centerX, centerY, playerCenterX, playerCenterY) > eyeRange)
			return false;
			
		/*
		 * Traces the ray and checks for wall collision
		 */
		while(bresenham.next()){
			Entity wall = ME.world.find(bresenham.getX() , bresenham.getY(), Entity.GROUND); //Make it possible to find different entities?
				
			/*
			 * We have collided with a wall, and the player is therefore not seen
			 */
			if(wall != null)
				return false;
		}
		
		/*
		 * The player has been found
		 */
		return true;
	}
	
	public void addWaypoints(Vector2f waypoints){
		if(this.waypoints == null)
			this.waypoints = new ArrayList<Vector2f>();
		
		this.waypoints.add(waypoints);
	}
	
	public void setWeapon(EnemyWeapon weapon){
		this.weapon = weapon;
	}

}
