package Incognito;

/*
 * Physics engine
 */
import org.dyn4j.collision.Bounds;
import org.dyn4j.collision.RectangularBounds;
import org.dyn4j.dynamics.Force;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;

import org.dyn4j.collision.broadphase.*;
import org.dyn4j.collision.*;

/*
 * Game engine
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.dyn4j.Version;
import org.dyn4j.collision.Fixture;
import org.dyn4j.collision.broadphase.BroadphaseDetector;
import org.dyn4j.collision.broadphase.DynamicAABBTree;
import org.dyn4j.collision.broadphase.SapBruteForce;
import org.dyn4j.collision.broadphase.SapIncremental;
import org.dyn4j.collision.broadphase.SapTree;
import org.dyn4j.collision.continuous.ConservativeAdvancement;
import org.dyn4j.collision.manifold.ClippingManifoldSolver;
import org.dyn4j.collision.narrowphase.Gjk;
import org.dyn4j.collision.narrowphase.Sat;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.MouseJoint;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Segment;
import org.dyn4j.geometry.Vector2;

/*
 * Random shizz
 */
import Incognito.Action;

public class Gameplay extends BasicGameState{

	private int stateID = -1;
	
	private PlayerObject player;
	private GroundObject ground;
	
	
	private World world;
	
	
	//Enemy i list
	
	//våpen i list
	
	//skudd i list

	public Gameplay(int stateID) {
		this.stateID = stateID;
		
		player = new PlayerObject(0,0);//100f, 300f);
		ground = new GroundObject();
		world = new World();
		
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		
		player.init(gameContainer, stateBasedGame);
		ground.init(gameContainer, stateBasedGame);
		
		world.setGravity(world.EARTH_GRAVITY.getNegative());
		//world.setGravity(world.ZERO_GRAVITY);
		world.setCollisionListener(new CollisionHandler());
		
		/*The ground defines the size of the world*/
		Bounds bounds = new RectangularBounds(Geometry.createRectangle(ground.getWidth(), ground.getHeight()));
		//bounds.translate(0, 0);
		world.setBounds(bounds);
		
		System.out.println(gameContainer.getWidth()/ 32 +  "   " + gameContainer.getHeight());
		System.out.println(world.getBounds().getTransform().getTranslationY());
		world.add(ground);
		world.add(player);
		//world.

	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		//Background
		ground.render(gameContainer, stateBasedGame, g);
		player.render(gameContainer, stateBasedGame, g);
		
		if(world.getBounds().isOutside(player))
			System.out.println(player.getTransform().getTranslationX() + "  " + player.getTransform().getTranslationY());
		
		
		//enemies
		//weapons
		//shots
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		
		Input input = gameContainer.getInput();
		
		//tilbake til mainmenu
		if(input.isKeyDown(input.KEY_ESCAPE))
			stateBasedGame.enterState(GameState.MAINMENUSTATE);
		
		//Hente keyboard input
		//--> gi til player
		
		
		
		if(input.isKeyDown(input.KEY_A)){
			player.movePlayer(Action.LEFT);
		}
		
		if(input.isKeyDown(input.KEY_D)){
			player.movePlayer(Action.RIGHT);
		}
		
		if(input.isKeyDown(input.KEY_W)){
			player.movePlayer(Action.JUMP);
		}
		
		if(input.isKeyDown(input.KEY_S)){
		}
		
		if(input.isKeyDown(input.KEY_SPACE)){
			player.movePlayer(Action.SHOOT);
			
		}
		
		
		//sjekke for kollisjon
		//Opdatere onGround hvis nødvednigt
		//if(player.collision(ground)){
		//	player.collides(ground);
		//}
		
		//legge til gravitasjon
		//player.applyGravitation();
			
		world.update(delta*0.001);
		//ground.update(gameContainer, stateBasedGame, delta);
		//player.update(gameContainer, stateBasedGame, delta);
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
