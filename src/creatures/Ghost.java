package creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import sound.SoundPlayer;

public class Ghost extends Enemy {
	BufferedImage left, right;
	private int xOffset, xOffset2;
	private int yOffset, yOffset2;
	private int speed;
	private Rectangle headBox;

	public Ghost(int x, int y, int width, int height, int speed, BufferedImage left, BufferedImage right) {
		super(x, y, width, height, left);
		this.left = left;
		this.right = right;
		this.speed = speed;
		xOffset = 0;
		yOffset = 0;
		getHitbox().setSize((int)(.6*width), (int) (.6*height));
		xOffset2 = 0;
		yOffset2 = 0;
		
	}

	/**
	 * Moves enemy down to perfectly sit on ground. Used to avoid needing vertical hit detection.
	 */
	@Override
	public void init() {
		
		
	}

	/**
	 * Updates enemy
	 */
	@Override
	public void tick() {
		
		move();
		getHitbox().setLocation((int)getX() + xOffset, (int)getY() + yOffset);
		
	}

	/**
	 * Updates enemy position
	 */
	@Override
	public void move() {
		
		if(c.getY() < getY())
		{
			if(canMove(0, -1)){
				setY(getY()-1);
			}
		}
		else if(c.getY() > getY())
		{
			if(canMove(0, 1)){
				setY(getY()+1);
			}
		}
		
		
		if(c.getX() > getX())
		{
			if(canMove(speed, 0)){
				setX(getX()+speed);
				facingRight = true;
				setTexture(right);
			}
		}
		else if(c.getX() < getX()){
			if(canMove(-speed, 0)){
				setX(getX()-speed);
				facingRight = false;
				setTexture(left);
			}
		}
	}
	
	/**
	 * Ovverriden interaction method to allow Character to kill by jumping on enemy's head
	 */
	@Override
	public void interact(Character character) {
		c.decreaseHealth();
	}

	@Override
	public void selectSprite() {
		
		
	}
	
	/**
	 * Overriden render to allow for displaying head's hitbox
	 * @param g - Game's Graphics Object
	 */
	@Override
	public void render(Graphics g)
	{
		super.render(g);
//		g.setColor(Color.GREEN);
//		g.drawRect((int) (headBox.x - Game.getxOffset()), headBox.y, headBox.width, headBox.height);
		
	}
}
