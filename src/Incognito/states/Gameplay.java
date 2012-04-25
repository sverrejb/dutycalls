package Incognito.states;

import java.util.LinkedList;
import java.util.List;

import it.randomtower.engine.World;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Incognito.entities.Bullet;
import Incognito.entities.GroundObject;
import Incognito.entities.PlayerObject;
import Incognito.utils.Action;
import Incognito.utils.Constants;

public class Gameplay extends World{

	
	private PlayerObject player;
	private GroundObject ground;
	
	List<Bullet> ammo = new LinkedList<Bullet>();
	int maxAmmo = 40;
	int currentAmmo; 
	
	//Enemy i list
	
	//våpen i list
	
	//skudd i list

	public Gameplay(int id) {
		super(id);
		player = new PlayerObject(100, 300);
		ground = new GroundObject(0, 0);
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		super.init(gameContainer, stateBasedGame);

		player.init(gameContainer, stateBasedGame);
		ground.init(gameContainer, stateBasedGame);
		
		currentAmmo = maxAmmo;
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		super.render(gameContainer, stateBasedGame, g);
		//Background
		ground.render(gameContainer, stateBasedGame, g);
		player.render(gameContainer, stateBasedGame, g);
		//enemies
		//weapons
		//shots
		g.drawString("Ammo: " + currentAmmo, 700, 10);
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		super.update(gameContainer, stateBasedGame, delta);
		
		Input input = gameContainer.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		//tilbake til mainmenu
		if(input.isKeyDown(input.KEY_ESCAPE))
			stateBasedGame.enterState(Constants.INGAME_STATE);
		
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
		
		
		if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
			if(ammo.size() < maxAmmo){
				ammo.add(new Bullet((int)player.getX(), (int)player.getY()));
				ammo.get(ammo.size() -1).init(gameContainer, stateBasedGame);
				ammo.get(ammo.size() -1).shoot(mouseX, mouseY, player.getX(), player.getY());
				currentAmmo--;
			}
			
		}
		
		//sjekke for kollisjon
		//Opdatere onGround hvis nødvednigt
		player.collision(ground);
		
		//legge til gravitasjon
		player.applyGravitation();
				
		ground.update(gameContainer, stateBasedGame, delta);
		player.update(gameContainer, stateBasedGame, delta);
		
		if(!ammo.isEmpty())
			for(Bullet bullet : ammo)
				bullet.update(gameContainer, stateBasedGame, delta);
		
	}

	


}
