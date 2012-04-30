package Incognito.entities;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import Incognito.utils.Constants;

public class GroundEnemy extends EnemyObject {
	
	private List<Vector2f> waypoints;
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
			if(x > waypoints.get(currentWaypoint).x  && speed.x > -maxSpeed.x)
				acceleration.x += Constants.ENEMY_MOVE_SPEED * -1;
			else if (x < waypoints.get(currentWaypoint).x && speed.x < maxSpeed.x)
				acceleration.x += Constants.ENEMY_MOVE_SPEED;
		}
		
		
		//float playerPosX = ((PlayerObject)ME.world.getEntities(PLAYER)).x;
		//float playerPosY = ((PlayerObject)ME.world.getEntities(PLAYER)).y;
	}
	
	public void addWaypoints(Vector2f waypoints){
		if(this.waypoints == null)
			this.waypoints = new ArrayList<Vector2f>();
		
		this.waypoints.add(waypoints);
	}

}
