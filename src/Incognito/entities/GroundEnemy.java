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
			
			
			//Are we close to the waypoint?
			if(this.x > waypoints[currentWaypoint].x - 10 && x < waypoints[currentWaypoint].x + 10){
				currentWaypoint += direction;
				System.out.println("dafuq");
			}
			
			//Fix which waypoint to go after
			if(currentWaypoint >= waypoints.length -1)
				direction = -1;
			else if(currentWaypoint <= 0 )
				direction = 1;
			
			// set acceleration to nothing
			acceleration.x = 0;

			// increase acceeration, if we're not going too fast			
			//* distance? 
			//Go correct direction
			if(x > waypoints[currentWaypoint].x  && speed.x > -maxSpeed.x)
				acceleration.x += Constants.ENEMY_MOVE_SPEED * -1;
			else if (x < waypoints[currentWaypoint].x && speed.x < maxSpeed.x)
				acceleration.x += Constants.ENEMY_MOVE_SPEED;
		}
		
		
		//float playerPosX = ((PlayerObject)ME.world.getEntities(PLAYER)).x;
		//float playerPosY = ((PlayerObject)ME.world.getEntities(PLAYER)).y;
	}
	
	public void setWaypoints(Vector2f[] waypoints){
		this.waypoints = waypoints.clone();
	}

}
