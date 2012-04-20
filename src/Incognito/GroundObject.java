package Incognito;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/*
 * Controls the ground
 */
public class GroundObject extends GameObject {
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
		
		for (int x = 0; x < ground.getWidth(); x++) 
			for (int y = 0; y < ground.getHeight(); y++) {
				//ID of the tile on x, y position layer 0
				int ID = ground.getTileId(x, y, 0);
				
				String value = ground.getTileProperty(ID, "blocked", "false");
				
				if("true".equals(value)){
					blocked[x][y] = true;
					System.out.println("BLOCKING");
				}
				else
				{
					System.out.println("NOT BLOCKING");
				}
			}
		
		
	}
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		ground.render(0, 0);
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
	}
}
