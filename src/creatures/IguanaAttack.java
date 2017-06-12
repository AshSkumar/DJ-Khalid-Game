package creatures;

import java.awt.image.BufferedImage;

import game.Game; 

public class IguanaAttack extends Creature{
	
	private int tick=240;
	public boolean facingRight = false;
	private int upwardsVelocity=1;
	public double djX = 0, djY;
	
	public IguanaAttack(double x, double y, int width, int height, BufferedImage texture) {
			
		super(x, y, width, height, texture);
		
	}
	
	/**
	 * Updates attack
	 */
	@Override
	public void tick(){	
		
		
		if(facingRight)
			speed = 7;
		else
			speed = -7;
		
		if(getY() < 0)
		{
			upwardsVelocity = 0;
			return;
		}
			
		if(facingRight)
			setX(getX()+7);
		if(!facingRight)
			setX(getX()-7);
		
		if(canMove(0,1)){
			upwardsVelocity+=1;
		}
		else
			upwardsVelocity=-12;
		
		
		setY(getY()+upwardsVelocity);
		
		getHitbox().setLocation((int) getX(), (int)getY());
		
		if(getHitbox().intersects(Game.dj.getHitbox()))
			Game.dj.decreaseHealth();
		
			
		
	}
	
	

    //required implementations for Creature
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void selectSprite() {
		// TODO Auto-generated method stub
		
	}
}
