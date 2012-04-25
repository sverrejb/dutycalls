package Incognito.entities;

import javax.swing.text.Position;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Incognito.entities.GameObject;

public class Bullet extends GameObject {
	
	Image bullet = null;
	
	float moveX = 0f;
	float moveY = 0f;
	float totalSpeed = 10f;
	float speedX = 0;
	float speedY = 0;
	Position pos = null;
	
	
	public Bullet(int pointX, int pointY){
		super(pointX, pointY);
		this.pointX = pointX;
		this.pointY = pointY;
	}
	

	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
		bullet = new Image("img/bullet.png");
	}
	
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException{
		bullet.draw(this.pointX,this.pointY);
	}
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		pointX += speedX;
		pointY += speedY;
	}
	
	@Override
	public void unLoad() {
	
	}
	public void shoot(float mouseX, float mouseY, float pointX, float pointY) {
		Vector2f movment = new Vector2f(mouseX - pointX,mouseY - pointY);
		movment.normalise();
		speedX = movment.getX()*totalSpeed;
		speedY = movment.getY()*totalSpeed;
	}
	
	
}
