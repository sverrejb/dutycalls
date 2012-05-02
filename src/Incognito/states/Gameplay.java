package Incognito.states;

import java.util.ArrayList;
import java.util.List;

import it.marteEngine.Camera;
import it.marteEngine.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Incognito.entities.BackgroundObject;
import Incognito.entities.enemies.EnemyWeapon;
import Incognito.entities.enemies.GroundEnemy;
import Incognito.entities.GroundObject;
import Incognito.entities.PlayerObject;
import Incognito.entities.WeaponObject;
import Incognito.utils.Constants;
import Incognito.utils.Globals;

public class Gameplay extends World{

	
	private PlayerObject player;
	private WeaponObject weapon;
	private BackgroundObject backGround;
	private Sound fx = null;
	private boolean soundPlaying = false;
	
	public Gameplay(int id) throws SlickException{
		super(id);
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		super.init(gameContainer, stateBasedGame);
		
		fx = new Sound("sound/fortunate_son.wav");
		
		player = new PlayerObject(100, 900);
		Globals.player = player;
		
		weapon = new WeaponObject(player);
		

		backGround = new BackgroundObject(0, 0, new Image("res/img/jungle2.png"));
		
		/*
		 * Add to MarteEngine world
		 */
		add(player);
		add(weapon);
		add(backGround);
		
		/*
		 * Add the world
		 */
		TiledMap ground = new TiledMap("ground/level1test.tmx");
		GroundObject groundObject;
		
		Globals.mapTileWidth = ground.getTileWidth();
		Globals.mapTileHeight = ground.getTileHeight();
		
		/* List containing every waypoint for each enemy */
		List<List<Vector2f>> enemyWaypoints = null;
		
		if(ground.getLayerCount() > 3){
			enemyWaypoints = new ArrayList<List<Vector2f>>();
			for(int i = 4; i < ground.getLayerCount(); i++){
				enemyWaypoints.add(new ArrayList<Vector2f>());
			}
		}
		
		//Goes through every pixel. Needs optimization   x = x + 16?
		for (int x = 0; x < ground.getWidth(); x++) 
			for (int y = 0; y < ground.getHeight(); y++) {
				//Layer 0 : background not blocked
				//layer 1 : ground blocked
				//layer 2 : extra blocked properties not visible
				//layer 3 : foreground not blocked
				//layer 4 -> n:  enemies 
				
				/* Render not collideable images */
				Image temp = ground.getTileImage(x, y, 0);
				
				if(temp != null){
					groundObject = new GroundObject(x*Globals.mapTileWidth,(y*Globals.mapTileHeight) - temp.getHeight() + Globals.mapTileHeight, temp);
					groundObject.collidable = false;
					groundObject.depth = 1;
					add(groundObject);
				}
				
				/* Draw collideable images */				
				temp = ground.getTileImage(x, y, 1);
				
				if(temp != null){
					groundObject = new GroundObject(x * Globals.mapTileWidth, (y * Globals.mapTileHeight) - temp.getHeight() + Globals.mapTileHeight, temp);
					groundObject.depth = 2;
					add(groundObject);
				}	
				
				/* extra blocked not visible*/
				temp = ground.getTileImage(x, y, 2);
				
				if(temp != null){
					groundObject = new GroundObject(x * Globals.mapTileWidth, (y * Globals.mapTileHeight) - temp.getHeight() + Globals.mapTileHeight, temp.getWidth(), temp.getHeight());
					groundObject.depth = 2;
					add(groundObject);
				}
				
				/* Foreground not blocked*/
				temp = ground.getTileImage(x, y, 3);
				
				if(temp != null){
					groundObject = new GroundObject(x * Globals.mapTileWidth, (y * Globals.mapTileHeight) - temp.getHeight() + Globals.mapTileHeight, temp);
					groundObject.depth = 6;
					groundObject.collidable = false;
					add(groundObject);
				}
				
				
				/* Spawn enemies*/
				for(int i = 4; i < ground.getLayerCount(); i++){
					int ID = ground.getTileId(x, y, i);
					
					//ID == 0 ==> no tile, nothing to do here... move on sir.
					if(ID == 0)
						continue;
					
					String value = ground.getTileProperty(ID, "enemy", "null");
					
					if("ground".equals(value)){ 
						
						enemyWaypoints.get(i-4).add(new Vector2f(x * Globals.mapTileWidth, (y * Globals.mapTileHeight) - 160 + Globals.mapTileHeight));
					}
					else if("flying".equals(value)){
						
					}
				}
			}
		
		
		/*
		 * Add enmies
		 */
		for (int i = 0; i < enemyWaypoints.size(); i++) {
			GroundEnemy groundEnemy = new GroundEnemy((int)enemyWaypoints.get(i).get(0).x, (int)enemyWaypoints.get(i).get(0).y + 80, new SpriteSheet(("img/anim/enemywalk.png"), 103, 160));
			groundEnemy.depth = 2;
			for (int j = 0; j < enemyWaypoints.get(i).size(); j++) {
				groundEnemy.addWaypoints(enemyWaypoints.get(i).get(j));
			}
			/*
			 * Give enemy weapon
			 */
			EnemyWeapon weapon = new EnemyWeapon(groundEnemy);
			weapon.collidable = false;
			weapon.depth = 12;
			
			groundEnemy.setWeapon(weapon);
			
			add(groundEnemy);
			add(weapon);
			
		}
		
		/*
		 * Add camera
		 */		
		setWidth(ground.getWidth() * Globals.mapTileWidth);
		setHeight(ground.getHeight() * Globals.mapTileHeight);
		
		setCamera(new Camera(this, player, container.getWidth(), container.getHeight(), ground.getWidth() * Globals.mapTileWidth, ground.getHeight() * Globals.mapTileHeight, new Vector2f(8,3)));
	
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		super.render(gameContainer, stateBasedGame, g);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		if(!soundPlaying ){
			fx.loop(1f, Constants.MUSIC_VOLUM);
			soundPlaying = true;
		}
		super.update(gameContainer, stateBasedGame, delta);
		
		Input input = gameContainer.getInput();
		
		//tilbake til mainmenu
		if(input.isKeyDown(input.KEY_ESCAPE)){			
			fx.stop();
			soundPlaying = false;
			this.removeAll();
			stateBasedGame.enterState(Constants.MAINMENU_STATE);	
		}
		if(player.isDead()){
			fx.stop();
			soundPlaying = false;
			this.removeAll();
			Globals.game.enterState(Constants.LOST_STATE);
		}
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		//Uglyfugly fix
		this.removeAll();
		init(container, game);
		
		Globals.ac.setMouseCursor(("img/cursor.gif"), 0, 0);
		super.enter(container, game);
	}

	


}
