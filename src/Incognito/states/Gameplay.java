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
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		super.update(gameContainer, stateBasedGame, delta);
		
		Input input = gameContainer.getInput();
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
		
		//sjekke for kollisjon
		//Opdatere onGround hvis nødvednigt
		player.collision(ground);
		
		//legge til gravitasjon
		player.applyGravitation();
				
		ground.update(gameContainer, stateBasedGame, delta);
		player.update(gameContainer, stateBasedGame, delta);
		
		
	}

	


}
