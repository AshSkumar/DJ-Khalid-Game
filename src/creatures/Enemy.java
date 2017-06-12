package creatures;

import java.awt.image.BufferedImage;

import game.Game;

public abstract class Enemy extends Creature {
	
	protected static Character c;
	protected boolean facingRight;
	private int initialX;
	protected boolean dead = false;

	public Enemy(double x, double y, int width, int height, BufferedImage texture) {
		super(x, y, width, height, texture);
	}

	
	public static void setC(Character c) {
		Enemy.c = c;
	}
	
	public abstract void init();

	public void interact(Character character) {
		c.decreaseHealth();
	}
	

	/**
	 * Default interaction with attack is to remove enemy
	 * @param Character's attack
	 */
	public void interact(Attack a) {
		this.remove();
	}
	

	/**
	 * When dead, remove hitbox and set dead to true. Used with enemies that have a death animation
	 */
	public void kill()
	{
		dead = true;
		getHitbox().setBounds(0, 0, 0, 0);
	}


	/**
	 * Removes from game
	 */
	public void remove(){
		Game.getEnemies().remove(this);	
	}
	
}
