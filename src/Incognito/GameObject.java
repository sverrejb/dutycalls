package Incognito;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
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
	//private TiledMap map;
	
	private static double gravityForce = 9.81; //Use formula instead
	
	/*
	 * Variables
	 */
	//Placement
	protected float pointX = 0f;
	protected float pointY = 0f;
	protected float width = 0f;
	protected float height = 0f;
	
	private float rotation = 0f; // In degrees
	protected Vector2f velocityVector;
	protected float dx = 0;
	protected float dy = 0;
	
	protected int pixelX;
	protected int pixelY;
	
	
	//Physics
	private float velocity = 0f;
	private float mass = 0f;
	
	//Only render if the object is placed on the screen
	private boolean onScreen = false;
	protected boolean onGround = false;
	/*
	 * Constructor
	 */
	public GameObject(){
		velocityVector = new Vector2f(0,0);
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
		//map = null;
	}
	
	/*
	 * Updates the object accordingly
	 */
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException{
		dy += gravityForce/delta;
	}
	
	public void applyGravitation(){
		if(!onGround){
		pointX += dx;
		pointY += dy;}
		
		//dx = 0;
		//dy = 0;
	}
	
	/*
	 * Draw the object
	 */
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		if(onScreen){
			
		}
	}
	
	/*
	 * Coallision detection
	 * @Para1 other object to check for coallision with
	 * @Return true/false if coallision
	 */
	public boolean collision(GameObject other){
		//
		
		Rectangle first = new Rectangle(pointX, pointY, width, height);
		
		if(other instanceof GroundObject){
			int x = (int) (pointX/other.pixelX);
			int y = (int) (pointY/other.pixelY);
			
			System.out.println(x);
			System.out.println(y);
			
			if(((GroundObject) other).blocked[x][y] == true){
				System.out.println("collision");
				onGround = true;
				return true;
			}
			
			//Rectangle second = new Rectangle();
			//((GroundObject) other).ground.
		}
		//Get other
		//Check intersection
		//On collision, run pixel perfect collision detection
		
		return false;
	}
	
	
	
	
}
