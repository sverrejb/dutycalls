package Incognito.states;

import java.awt.Cursor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.state.StateBasedGame;

import Incognito.utils.Globals;

import it.marteEngine.World;

public class GameOverState extends World{

	public GameOverState(int id) {
		super(id);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Globals.ac.setMouseCursor((ImageData) new Cursor(Cursor.DEFAULT_CURSOR), 0, 0);
		super.enter(container, game);
		
	}

}
