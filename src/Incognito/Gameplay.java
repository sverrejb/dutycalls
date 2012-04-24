package Incognito;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
	List<Bullet> ammo = new LinkedList<Bullet>();
	//Enemy i list
	
	//våpen i list
	
	//skudd i list

	public Gameplay(int stateID) {
		this.stateID = stateID;
		
		player = new PlayerObject(100f, 400f);
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
		
		if(!ammo.isEmpty())
			for(Bullet bullet : ammo)
				bullet.render(gameContainer, stateBasedGame, g);
		//enemies
		//weapons
		//shots
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta)
			throws SlickException {
		
		Input input = gameContainer.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		//tilbake til mainmenu
		if(input.isKeyDown(input.KEY_ESCAPE)){
			stateBasedGame.enterState(GameState.MAINMENUSTATE);
			//player.unLoad();
		}

		if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
			ammo.add(new Bullet(player.pointX, player.pointY));
			ammo.get(ammo.size() -1).init(gameContainer, stateBasedGame);
			ammo.get(ammo.size() -1).shoot(mouseX, mouseY, player.pointX, player.pointY);
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

	@Override
	public int getID() {
		return stateID;
	}

}
