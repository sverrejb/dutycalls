package Incognito.utils;

import org.newdawn.slick.geom.Vector2f;

public class Constants {
	/* Game states*/
	public static final int MAINMENU_STATE = 0;
	public static final int INGAME_STATE = 1;
	public static final int LOST_STATE = 2;
	public static final int ABOUT_STATE = 3;
	
	/* Games constants*/
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static boolean DEBUG = true;
	
	/*Physics constants*/
	public static final float GRAVITY = 0.2f;
	public static final Vector2f FRICTION = new Vector2f(0.5f, 0.5f);
	public static final Vector2f MAX_SPEED = new Vector2f(3, 8);
	
	/* PlayerObject constants*/
	public static final int PLAYER_ANIMATION_SPEED = 100; 
	public static final int PLAYER_MOVE_SPEED = 1;
	public static final int PLAYER_JUMP_SPEED = 6;
	public static final String STAND_STILL_RIGHT = "STAND_STILL_RIGHT"; // Now implemented in ME
	public static final String STAND_STILL_LEFT = "STAND_STILL_LEFT";
	
	/* Bullet constants*/
	public static final float BULLET_SPEED = 50f;
	public static final int BULLET_BOUNDRY = 1200;
	public static final int MAXAMMO = 40;
}
