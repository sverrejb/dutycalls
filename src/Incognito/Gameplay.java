package Incognito;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Incognito.Action;

public class Gameplay extends BasicGameState{

	private int stateID = -1;
	
	private PlayerObject player;
	private GroundObject ground;
	
	//Enemy i list
	
	//våpen i list
	
	//skudd i list

	public Gameplay(int stateID) {
		this.stateID = stateID;
		
		player = new PlayerObject(100f, 300f);
		ground = new GroundObject();
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		player.init(gameContainer, stateBasedGame);
		ground.init(gameContainer, stateBasedGame);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		//Background
		ground.render(gameContainer, stateBasedGame, g);
		player.render(gameContainer, stateBasedGame, g);
		//enemies
		//weapons
		//shots
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		

		
		//Hente keyboard input
		//--> gi til player
		
		Input input = gameContainer.getInput();
		
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
			player.movePlayer(Action.SHOOT);
		}
		
		
		//sjekke for kollisjon
		//Opdatere onGround hvis nødvednigt
		player.collision(ground);
		
		//legge til gravitasjon
		player.applyGravitation();
				
		ground.update(gameContainer, stateBasedGame, delta);
		player.update(gameContainer, stateBasedGame, delta);
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
