package Incognito.utils;

import java.awt.Point;

/*
 * CLASS OBSOLETE
 */
public enum Action {	
	LEFT(-1, 0, 30),
	RIGHT(1, 0, 30),
	JUMP(0, -1, 120),
	SHOOT(0, 0, 0);
	
	public static final int MOVE_SPEED = 10;
	public static final int JUMP_SPEED = 60;
	
	private final int xDir, yDir, value;
	
	Action(int xDir, int yDir, int value)
	{
		this.xDir = xDir;
		this.yDir = yDir;
		this.value = value;
	}
	
	public Point getDir(){
		return new Point(xDir, yDir);
	}
	
	public int getValue(){
		return value;
	}
}
