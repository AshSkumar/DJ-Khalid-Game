package creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import sound.SoundPlayer;

public class PlantEnemy extends Enemy{
	
	BufferedImage left, right;
	private int xOffset, xOffset2;
	private int yOffset, yOffset2;
	private int speed;
	private Rectangle headBox;

	public PlantEnemy(int x, int y, int width, int height, int speed, BufferedImage left, BufferedImage right) {
		super(x, y, width, height, left);
		this.left = left;
		this.right = right;
		this.speed = speed;
		xOffset = (int) (.2 * width);
		yOffset = (int) (.4 * height);
		getHitbox().setSize((int)(.6*width), (int) (.6*height));
		xOffset2 = (int)(.2*width);
		yOffset2 = (int)(.3*width);
		headBox = new Rectangle(xOffset2, yOffset2, (int) (.6*width), (int)(.2*height));
	}

	/**
	 * Moves enemy down to perfectly sit on ground. Used to avoid needing vertical hit detection.
	 */
	@Override
	public void init() {
		
		while(canMove(0,1))
		{
			setY(getY()+1);
		}
	}

	/**
	 * Updates enemy
	 */
	@Override
	public void tick() {
		
		if(getHitbox().intersects(c.attack.getHitbox()))
		{
			kill();
			c.attack.setY(-1000);
		}
		
		if(dead)
		{
			deadTick();
			return;
		}
			
		
		move();
		getHitbox().setLocation((int)getX() + xOffset, (int)getY() + yOffset);
		headBox.setLocation((int) getX()+xOffset2, (int) getY()+yOffset2);
		
	}

	/**
	 * Crushes sprite when dead
	 */
	private void deadTick() {
		
		if(getHeight() > 5)
		{
			setY(getY() + 0.11*getHeight());
			setHeight((int) (.9*getHeight()));
		}
		else
			remove();
		
	}

	/**
	 * Updates enemy position
	 */
	@Override
	public void move() {
		
		if(!facingRight)
		{
			if(canMove(-speed, 0))
				setX(getX()-speed);
			else
			{
				facingRight = true;
				setTexture(right);
			}
			
		}
		else
		{
			if(canMove(speed, 0))
				setX(getX()+speed);
			else
			{
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
		
		if(c.getUpwardsVelocity() > 0 &&headBox.intersects(c.footBox))
		{
			c.bounce();
			kill();
			SoundPlayer.playSound("anotherOne.wav", false);
			addScore(100);
		}
		else
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
