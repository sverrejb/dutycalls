package Incognito.states;

import java.awt.Cursor;
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
import Incognito.entities.GroundObject;
import Incognito.entities.PlayerObject;
import Incognito.utils.Action;
import Incognito.utils.Constants;

public class Gameplay extends World{

	
	private PlayerObject player;
	private GroundObject ground;
	private BackgroundObject bg;
	
	//Enemy i list
	
	//våpen i list
	
	//skudd i list

	public Gameplay(int id) throws SlickException{
		super(id);
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		super.init(gameContainer, stateBasedGame);
		
		player = new PlayerObject(100, 300);
		
		/*
		 * Add to MarteEngine world
		 */
		add(player);
		
		/*
		 * Add the world
		 */
		TiledMap ground = new TiledMap("tiles/testLevel.tmx");
		GroundObject groundObject;
		Image jungle = new Image("res/img/jungle.png");
		bg = new BackgroundObject(0, 0, jungle);
		
		int pixelsX = ground.getTileWidth();
		int pixelsY = ground.getTileHeight();
		
		for (int x = 0; x < ground.getWidth(); x++) 
			for (int y = 0; y < ground.getHeight(); y++) {
				//ID of the tile on x, y position layer 0
				int ID = ground.getTileId(x, y, 0);
				
				String value = ground.getTileProperty(ID, "blocked", "false");
				
				if("true".equals(value)){
					groundObject = new GroundObject(x*pixelsX, y*pixelsY, ground.getTileImage(x, y, 0));
					add(groundObject);
				}
				else{
					//Render not collideable images
					Image temp = ground.getTileImage(x, y, 0);
					
					if(temp != null){
						groundObject = new GroundObject(x*pixelsX, y*pixelsY, temp);
						groundObject.collidable = false;
						add(groundObject);
					}
				}
			}
		
		/*
		 * Add camera
		 */		
		setWidth(ground.getWidth() * pixelsX);
		setHeight(ground.getHeight() * pixelsY);
		setCamera(new Camera(this, player, container.getWidth(), container.getHeight(), ground.getWidth() * pixelsX, ground.getHeight() * pixelsY, new Vector2f(32,32)));

		gameContainer.getInput().addMouseListener(new MouseListener() {
			
			@Override
			public void setInput(Input arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isAcceptingInput() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void inputStarted() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void inputEnded() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseWheelMoved(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseReleased(int arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(int arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
				System.out.println("hello");
			}
		});
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		bg.render(gameContainer, g);
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
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		
		//org.newdawn.slick.AppGameContainer.setMouseCursor(new Cursor(Cursor.HAND_CURSOR), 2,2);
	}

	


}
