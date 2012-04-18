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
	private TiledMap ground;
	
	public GroundObject() {
		super();
	}
	
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
		System.out.println(GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE));
		
		//System.exit(0);
		
		ground = new TiledMap("tiles/testLevel.tmx");
		
	}
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		ground.render(0, 0);
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
	}
}
