package Incognito;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet extends GameObject {
	
	Image bullet = null;
	
	float moveX = 0f;
	float moveY = 0f;
	float totalSpeed = 10f;
	float speedX = 0;
	float speedY = 0;
	
	
	Bullet(float pointX, float pointY){
		super();
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
	public void shoot(int mouseX, int mouseY, float pointX, float pointY) {
		this.pointX = pointX;
		this.pointY = pointY;
		float angle = (float) Math.toDegrees(Math.atan2(mouseY - pointY, mouseX - pointX));
		if(angle < 90 && angle > -90)
			speedY = (angle/90) * totalSpeed;
		else
			speedY = (angle/120) * totalSpeed;
		System.out.println(speedY);
		speedX = totalSpeed - speedY;
		
		
		//System.out.println(angle);
	}
	
	
}
