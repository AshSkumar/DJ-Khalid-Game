package creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;
import images.Pictures;
import runner.Runner;

public class Iguana extends Enemy {
	
	int speedX;
	int speedY;
	private int xOffset;
	private int yOffset;
	private Rectangle headBox;
	BufferedImage right, left;
	private int health = 100;
	private int tick=240;
	public boolean facingRight = false;
	private int upwardsVelocity=1;
	private int attackTicks=120;
	int rand;
	boolean johnWall = false;
	IguanaAttack attack = new IguanaAttack(-100,-100,70,70, Pictures.fAttack[rand]);

	public Iguana(double x, double y, int width, int height, int speed, BufferedImage left, BufferedImage right) {
		super(x, y, width, height, left);
		this.speedX = speed;
		this.speedY = speed-2;
		this.right = right;
		this.left = left;
		xOffset = (int)(.25*width);
		headBox = new Rectangle(xOffset, yOffset, (int) (.4*width), (int)(.2*height));
	}
	
	public void init()
	{
		
	}

	/**
	 * Updates enemy
	 */
	@Override
	public void tick() {
		
		if(dead)
		{
			deadTick();
			return;
		}
		
		if(getHitbox().intersects(c.attack.getHitbox()))
		{
			health-=5;
			for(int x = 0; x < 140; x++)
				c.attack.tick();
			if(health <= 0)
			{
				kill();
				tick = 0;
			}
		}
					
		
		move();
		getHitbox().setLocation((int)getX(), (int)getY());
		
		
		if(attackTicks == 180){		
				
			rand=(int)(Math.random()*3);
				attack.setTexture(Pictures.fAttack[rand]);
		 		johnWall = true;
		 		attack.djX = Game.dj.getX();
		 		attack.djY = Game.dj.getY();
		 		attack.setX(getX());
		 		attack.setY(getY());
		 		if(facingRight)
		 			attack.setX(getX() + getWidth());
		 		attackTicks=0;
		 		attack.facingRight = facingRight;
		 		if(attack.facingRight)
					attack.speed = 7;
				else
					attack.speed = -7;
		}
		
		
		if(johnWall)
			attack.tick();
		else
		{
			attack.setY(-100);
			attack.getHitbox().y = -100;
		}
			
		if(attack.canMove(0, 1) && !attack.canMove((int) attack.speed, 0))
			johnWall = false;
		
		
		attackTicks++;	
			
		headBox.setLocation((int) getX()+xOffset, (int) getY());
		
	}
	
	/**
	 * Updates enemy's sprite when dead
	 */
	private void deadTick() {
		tick++;
		
		if(tick == 180){
			remove();
			Runner.loop.loadState("Menu");
			Game.stageNumber = 1;
		}
					
		
		setTexture(Pictures.exp);
		
	}

	/**
	 * Updates enemy position
	 */
	@Override
	public void move() {
		
		if(facingRight)
			setTexture(right);
		else
			setTexture(left);
				
		
		if(c.getY() < getY())
		{
			if(canMove(0, -speedY))
				setY(getY()-speedY);
		}
		else if(c.getY() > getY())
		{
			if(canMove(0, speedY))
				setY(getY()+speedY);
		}
		if(c.getX() < getX()+getWidth()/2) 
		{
			if(canMove(-speedX, 0))
				setX(getX()-speedX);
			attack.setSpeed(-9);
			facingRight = false;
		}
		else if(c.getX() > getX()+getWidth()/2)
		{
			if(canMove(speedX, 0))
				setX(getX()+speedX);
			attack.setSpeed(9);
			facingRight = true;
		}
		
	}

	@Override
	public void selectSprite() {
	}
	
	@Override
	public void interact(Character character) {
		
		if(c.getUpwardsVelocity() > 0 && headBox.intersects(c.footBox))
		{
			c.bounce();
			c.setSpeed(attack.speed*2);
			health-=20;
			addScore(100);
			if(health <= 0)
			{
				kill();
				tick = 0;
			}
				
		}
		else
			c.decreaseHealth();
	}
	
	/**
	 * Overriden render method to display boss health bar
	 * @param g - Game's Graphics object
	 */
	@Override
	public void render(Graphics g)
	{
		super.render(g);
		attack.render(g);
		g.setColor(Color.black);
		g.drawRect(400, 50, 801, 20);
		g.setColor(Color.RED);
		g.fillRect(401, 51, 800, 19);
		g.setColor(Color.GREEN);
		g.fillRect(401, 51, 8*health, 19);
//		g.drawRect((int) (headBox.x - Game.getxOffset()), headBox.y, headBox.width, headBox.height);
	}

}