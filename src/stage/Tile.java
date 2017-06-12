package stage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;

public abstract class Tile {
	
	private BufferedImage texture;
	
	public static final int defaultWidth = 70; //80
	public static final int defaultHeight = defaultWidth;
	private int width, height;
	private int blocksRight, blocksDown;
	private int x, y;
	private Rectangle hitbox;
	
	public Tile(int blocksRight,  int blocksDown, BufferedImage texture)
	{
		this.setTexture(texture);
		this.setX(blocksRight*Tile.defaultWidth);
		this.y = blocksDown*Tile.defaultHeight;
		width = defaultWidth;
		height = defaultHeight;;
		hitbox = new Rectangle(getX(), y, width, height);
	}
	
	public Tile(int x, int y, int width, int height, BufferedImage texture)
	{
		this.setTexture(texture);
		this.setX(x);
		this.y = y;
		this.width = width;
		this.height = height;
		hitbox = new Rectangle(x, y, width, height);
	}
	
	/**
	 * @return returns 1 to signify that the block is solid
	 */
	public void render(Graphics g) {
		g.drawImage(getTexture(), (int) (getX() - Game.getxOffset()), (int) (y - Game.getyOffset()), width, height,
				 null);
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}

	public abstract int isSolid();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

}
