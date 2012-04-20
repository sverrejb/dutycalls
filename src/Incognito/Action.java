package Incognito;

import java.awt.Point;

public enum Action {
	LEFT(-1, 0, -3),
	RIGHT(1, 0, 3),
	JUMP(0, -1, 6),
	SHOOT(0, 0, 0);
	
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
