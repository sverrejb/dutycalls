package Incognito.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import Incognito.utils.Constants;

public class GroundEnemy extends EnemyObject {
	
	private Vector2f[] waypoints;
	private int currentWaypoint = 0;
	private int direction = 1;

	public GroundEnemy(int x, int y, Image image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics g) throws SlickException{
		super.render(gameContainer, g);
	}
	
	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		super.update(gameContainer, delta);
			if(waypoints != null){
			//Fix which waypoint to go after
			if(currentWaypoint > waypoints.length)
				direction = -1;
			else if(currentWaypoint < 0 )
				direction = 1;
			
			//Are we close to the waypoint?
			if(this.x > waypoints[currentWaypoint].x + 10 && x < waypoints[currentWaypoint].x - 10)
				currentWaypoint += direction;
			
			if(true)
				return;
			
			//* distance? 
			//Go correct direction
			if(this.x > waypoints[currentWaypoint].x)
				acceleration.x += Constants.ENEMY_MOVE_SPEED * -1;
			else 
				acceleration.x += Constants.ENEMY_MOVE_SPEED;
		}
		
			System.out.println(direction);
		
		
		//float playerPosX = ((PlayerObject)ME.world.getEntities(PLAYER)).x;
		//float playerPosY = ((PlayerObject)ME.world.getEntities(PLAYER)).y;
	}
	
	public void setWaypoints(Vector2f[] waypoints){
		this.waypoints = waypoints.clone();
	}

}
