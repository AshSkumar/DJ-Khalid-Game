package creatures;

import java.awt.image.BufferedImage;

public class BlockEnemy extends Enemy{
	
	int jumpHeight;
	int jumpTop = 150;
	boolean disguised = true;
	boolean goingUp = false;
	int speed;
	int ticks = 0; 
	int endTick = 30;
	public boolean fake = false;

	public BlockEnemy(double x, double y, int width, int height, BufferedImage texture, int pieces, int row, boolean fake) {
		super(x, y, width, height, texture);
		jumpHeight = getHeight()*2;
		speed = 8;
		ticks = (int) (((double)row/pieces)*endTick/2);
		if(fake == true)
			speed = 0;
	}

	/**
	 * Updates block piece
	 */
	@Override
	public void tick() {
		
		if(disguised)
		{
			if(Math.abs(c.getX()- getX()) < 140)
				disguised = false;
			return;
		}
		
		if(getHitbox().intersects(c.attack.getHitbox()))
			kill();
		
		if(dead)
			remove();
		
		if(ticks == endTick)
			move();
		else
			ticks++;
		
		getHitbox().setLocation((int)getX(), (int)getY());
		
	}

	/**
	 * Updates position
	 */
	@Override
	public void move() {	
		if(goingUp)
		{
			if(getY() > jumpTop)
			{
				setY(getY() - 1);
			}
			else
			{
				goingUp = false;
			}
		}
		else
		{
			if(canMove(0, 1))
			{
				setY(getY() + 1);
			}
			else
			{
				ticks = 0;
				endTick = generateWaitTime();
				goingUp = true;
				jumpTop = (int) (getY() - jumpHeight);
			}
				
		}		
		if(c.getX() < getX())
		{
			if(canMove(-speed, 0))
				setX(getX()-speed);
		}
		else if(c.getX() > getX())
		{
			if(canMove(speed, 0))
				setX(getX()+speed);
		}
	
	}

	/**
	 * @return
	 */
	private int generateWaitTime() {
		double rand = (Math.random()*100);
		rand += 100;
		if(getWidth()<20)
		{
			rand/=3;	
		}
		else if(getWidth()<50)
		{
			rand/=2;
		}
		return (int) rand;
	}

	@Override
	public void selectSprite() {
		
		
	}

	@Override
	public void init() {
		
		
	}

}
