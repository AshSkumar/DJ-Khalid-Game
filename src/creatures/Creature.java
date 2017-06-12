package creatures;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;
import images.Pictures;
import stage.Stage;
import stage.Tile;

public abstract class Creature extends Entity {

	protected double speed = 4;
	private int widthInTiles, heightInTiles;


	public Creature(double x, double y, int width, int height, BufferedImage texture) {
		super(x, y, width, height, texture);
		widthInTiles = (int) Math.ceil((double) width / Tile.defaultWidth);
		heightInTiles = (int) Math.ceil((double) height / Tile.defaultHeight);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public abstract void tick();

	public abstract void move();

	public abstract void selectSprite();


	/**
	 * Checks if creature will collide with stage if moving in this way
	 * @param xChange - change in x attempted to move by
	 * @param yChange - Change in y attempted to move by
	 * @return boolean saying if you can move without colliding
	 */
	protected boolean canMove(int xChange, int yChange) {
		
		Rectangle newLocation = new Rectangle((int) (getX() + xChange), (int) (getY() + yChange), getWidth(),
				getHeight());
		
		if (newLocation.x < 0 || (newLocation.y > Game.getStage().length * Tile.defaultHeight - newLocation.height 
				&& Stage.backgrounds.get((int)newLocation.x/1500) != Pictures.miamihole))
			return false;
		int cornerX = newLocation.x / Tile.defaultWidth;
		int cornerY = newLocation.y / Tile.defaultHeight;

		for (int y = cornerY; y < cornerY + heightInTiles + 2; y++) {
			for (int x = cornerX; x < cornerX + widthInTiles + 2; x++) {

				if (x < 0 || y < 0 || y >= Game.getStage().length || x >= Game.getStage()[0].length) {
					continue;
				}
				if (Game.getStage()[y][x] != null && Game.getStage()[y][x].isSolid() == 1 
						&& newLocation.intersects(Game.getStage()[y][x].getHitbox())) {
					return false;
				}
			}
		}

		return true;
	}
	

	/**
	 * Used in Character class to update Character size and maintain efficiency
	 */
	public void setTileDimensions()
	{
		widthInTiles = getWidth() / Tile.defaultWidth;
		heightInTiles = getHeight() / Tile.defaultHeight;
	}

}
