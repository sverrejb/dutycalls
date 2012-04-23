package Incognito;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;



/*
 * Controls the ground
 */
public class GroundObject extends StandardObject {
	protected TiledMap ground;
	
	//Two dim array representing each tiles blocked value
	protected boolean[][] blocked;
	
	public GroundObject() {
		super();
	}
	
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{		
		ground = new TiledMap("tiles/testLevel.tmx");
		blocked = new boolean[ground.getWidth()][ground.getHeight()];

		pixelsX = ground.getTileWidth();
		pixelsY = ground.getTileHeight();
		
		width = pixelsX * ground.getWidth()/ SCALE;
		height = pixelsY * ground.getHeight() / SCALE;
		//ground.
		
		for (int x = 0; x < ground.getWidth(); x++) 
			for (int y = 0; y < ground.getHeight(); y++) {
				//ID of the tile on x, y position layer 0
				int ID = ground.getTileId(x, y, 0);
				
				String value = ground.getTileProperty(ID, "blocked", "false");
				
				if("true".equals(value)){
					blocked[x][y] = true;
					Rectangle rect = new Rectangle(pixelsX, pixelsY);
					//Origo i VH??
					//RECT : TRNASLATE HER FEILEN SKEJR!!
					//rect.translate((((x * pixelsX)-pixelsX)) + width/2, ((y * pixelsY) - pixelsY) + height/2);
					this.addFixture(new BodyFixture(rect));
				}
			}
		
		setMass(Mass.Type.INFINITE);
		setGravityScale(0.0);
		transform.setTranslation(new Vector2(0,0));
		
	}
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		ground.render(0, 0);//* SCALE, 0 * SCALE);
		//ground.
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getWidth(){
		return width;
	}
}
