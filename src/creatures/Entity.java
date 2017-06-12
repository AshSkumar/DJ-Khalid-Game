package creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;
import images.Writer;


public abstract class Entity {
	
	private double x, y;
	private int width, height;
	private Rectangle hitbox;
	
	private BufferedImage texture;
	public static int score = 0;
	public static int health = 5;
	
	public Entity(double x, double y, int width, int height, BufferedImage texture)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
		hitbox = new Rectangle((int)x, (int)y, width, height);
	}
		
	public double getX() {
		return x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public void addScore(int points)
	{
		score += points;
		new Writer(x, y, points);		
	}

	public abstract void tick();
	

	/**
	 * Renders entity
	 * @param g -Game's Graphics object
	 */
	public void render(Graphics g) {
		g.drawImage(texture, (int) (x - Game.getxOffset()), (int) (y - Game.getyOffset()), width, height,
				 null);
//		g.setColor(Color.RED);
//		g.drawRect((int) (hitbox.x - Game.getxOffset()), (int) (hitbox.y - Game.getyOffset()), hitbox.width, hitbox.height);
	}
	

	/**
	 * Writes score
	 * @param g - Game's Graphics object
	 */
	public static void writeScore(Graphics g){
		g.setColor(Color.RED);
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 3F);
		g.setFont(newFont);
		g.drawString("Score: " + score, 50, 50);
	}
	
}
