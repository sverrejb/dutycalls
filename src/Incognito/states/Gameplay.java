package Incognito.states;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.marteEngine.Camera;
import it.marteEngine.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Incognito.entities.BackgroundObject;
import Incognito.entities.Bullet;
import Incognito.entities.GroundEnemy;
import Incognito.entities.GroundObject;
import Incognito.entities.PlayerObject;
import Incognito.entities.WeaponObject;
import Incognito.utils.Action;
import Incognito.utils.Constants;
import Incognito.utils.Globals;

public class Gameplay extends World{

	
	private PlayerObject player;
	private GroundObject ground;
	private WeaponObject weapon;
	private BackgroundObject backGround;
	
	//Enemy i list
	
	//v�pen i list
	
	//skudd i list

	public Gameplay(int id) throws SlickException{
		super(id);
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		super.init(gameContainer, stateBasedGame);		
		
		player = new PlayerObject(100, 300);
		weapon = new WeaponObject(player);
		
		backGround = new BackgroundObject(0, 0, new Image("res/img/jungle.png"));
		
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
		
		int mapTileWidth = ground.getTileWidth();
		int mapTileHeight = ground.getTileHeight();
		
		int tileFixWidth;
		int tileFixHeight;
		
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
					groundObject = new GroundObject(x*mapTileWidth,(y*mapTileHeight) - temp.getHeight() + mapTileHeight, temp);
					groundObject.collidable = false;
					groundObject.depth = 2;
					add(groundObject);
				}
				
				/* Draw collideable images */				
				temp = ground.getTileImage(x, y, 1);
				
				if(temp != null){
					groundObject = new GroundObject(x * mapTileWidth, (y * mapTileHeight) - temp.getHeight() + mapTileHeight, temp);
					groundObject.depth = 2;
					add(groundObject);
				}	
				
				/* extra blocked not visible*/
				temp = ground.getTileImage(x, y, 2);
				
				if(temp != null){
					groundObject = new GroundObject(x * mapTileWidth, (y * mapTileHeight) - temp.getHeight() + mapTileHeight, temp.getWidth(), temp.getHeight());
					groundObject.depth = 2;
					add(groundObject);
				}
				
				/* Foreground not blocked*/
				temp = ground.getTileImage(x, y, 3);
				
				if(temp != null){
					groundObject = new GroundObject(x * mapTileWidth, (y * mapTileHeight) - temp.getHeight() + mapTileHeight, temp);
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
					
					if("ground".equals(value)){ //ground1, ground1. for punkter � g� og??
						//temp = new Image("img/anim/r1.png");
						//GroundEnemy groundEnemy = new GroundEnemy(x * mapTileWidth, (y * mapTileHeight) - temp.getHeight() + mapTileHeight, temp);
						//groundEnemy.depth = 2;
						//Istedenfor bare legge inn groundEnemy etterp�? Bare lagre posisjonene her og spawn fienden p� f�rste?
						//Vector2f[] vectors = {
						//		new Vector2f(100,100), new Vector2f(400, 400)
						//		};
						//groundEnemy.setWaypoints(vectors);
						//add(groundEnemy);
						
						//enemyWaypoints.
						temp = new Image("img/anim/r1.png");
						enemyWaypoints.get(i-4).add(new Vector2f(x * mapTileWidth, (y * mapTileHeight) - temp.getHeight() + mapTileHeight));
					}
					else if("flying".equals(value)){
						
					}
				}
			}
		
		
		for (int i = 0; i < enemyWaypoints.size(); i++) {
			GroundEnemy groundEnemy = new GroundEnemy((int)enemyWaypoints.get(i).get(0).x, (int)enemyWaypoints.get(i).get(0).y, new Image("img/anim/r1.png"));
			groundEnemy.depth = 2;
			for (int j = 0; j < enemyWaypoints.get(i).size(); j++) {
				groundEnemy.addWaypoints(enemyWaypoints.get(i).get(j));
			}
			add(groundEnemy);
		}
		
		
		/*
		 * Add camera
		 */		
		setWidth(ground.getWidth() * mapTileWidth);
		setHeight(ground.getHeight() * mapTileHeight);
		
		setCamera(new Camera(this, player, container.getWidth(), container.getHeight(), ground.getWidth() * mapTileWidth, ground.getHeight() * mapTileHeight, new Vector2f(8,8)));
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		//bg.render(gameContainer, g);
		super.render(gameContainer, stateBasedGame, g);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		super.update(gameContainer, stateBasedGame, delta);
		
		Input input = gameContainer.getInput();
		//tilbake til mainmenu
		if(input.isKeyDown(input.KEY_ESCAPE))
			stateBasedGame.enterState(Constants.MAINMENU_STATE);
		
		/*
		if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
			if(ammo.size() < currentAmmo){
				ammo.add(new Bullet((int)player.getX(), (int)player.getY()));
				ammo.get(ammo.size() -1).init(gameContainer, stateBasedGame);
				ammo.get(ammo.size() -1).shoot(mouseX, mouseY, player.getX(), player.getY());
				currentAmmo--;
			}
			
		}*/		
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {

		Globals.ac.setMouseCursor(("/res/img/cursor.gif"), 0, 0);		

		super.enter(container, game);
	}

	


}
