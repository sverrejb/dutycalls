package Incognito;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/*
 * This class defines everything for all collideable objects in the game
 */
public abstract class GameObject implements IGameObject{
	/*
	 * Constants
	 */
	private final float scale = 1f;
	
	//Not necessary in abtract? Use more specific instead?
	private TiledMap map;
	
	private static double gravityForce = 9.81; //Use formula instead
	
	/*
	 * Variables
	 */
	//Placement
	private float pointX = 0f;
	private float pointY = 0f;
	private float rotation = 0f; // In degrees
	
	//Physics
	private float velocity = 0f;
	private float mass = 0f;
	
	//Only render if the object is placed on the screen
	private boolean onScreen = false;
	
	/*
	 * Constructor
	 */
	public GameObject(){
		
	}
	
	/*
	 * Initializes the data in the object
	 */
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
		
	}
	
	/*
	 * Removes the data from the object
	 * Garbage throwing
	 */
	public void unLoad(){
		map = null;
	}
	
	/*
	 * Updates the object accordingly
	 */
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta){
		
	}
	
	/*
	 * Draw the object
	 */
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		if(onScreen){
			
		}
	}
	
	
	
	
	
}
