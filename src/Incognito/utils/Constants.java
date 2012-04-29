package Incognito.utils;

import org.newdawn.slick.geom.Vector2f;

public class Constants {
	/* Game states*/
	public static final int MAINMENU_STATE = 0;
	public static final int INGAME_STATE = 1;
	public static final int LOST_STATE = 2;
	public static final int ABOUT_STATE = 3;
	public static final int HIGHSCORE_STATE = 4;
	
	/* Games constants*/
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final boolean DEBUG = false;
	public static final int CAMRA_TWEEK_X = 0;
	public static final int CAMRA_TWEEK_Y = 0;
	public static final int FPS = 60;
	
	/*Physics constants*/
	public static final float GRAVITY = 0.2f;
	public static final Vector2f FRICTION = new Vector2f(0.5f, 0.5f);
	public static final Vector2f MAX_SPEED = new Vector2f(3, 8);
	
	/* Player constants*/
	public static final int PLAYER_ANIMATION_SPEED = 100; 
	public static final int PLAYER_MOVE_SPEED = 1;
	public static final int PLAYER_JUMP_SPEED = 6;
	public static final String STAND_STILL_RIGHT = "STAND_STILL_RIGHT"; // Now implemented in ME
	public static final String STAND_STILL_LEFT = "STAND_STILL_LEFT";
	
	/* Enemy constants */
	public static final int ENEMY_MOVE_SPEED = 1;
	public static final int ENEMY_ANIMATION_SPEED = 100;
	
	/* Weapon constants*/
	public static final float BULLET_SPEED = 40f;
	public static final int BULLET_BOUNDRY = 1200;
	public static final int WEAPON_FIRE_RATE = 30; //halvt sek
	public static final int MAXAMMO = 40;
}
