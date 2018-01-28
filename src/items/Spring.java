package items;

import java.awt.Rectangle;

import creatures.Character;
import images.Pictures;
import stage.Tile;


public class Spring extends Item{
	
	private int xOffset, yOffset;
	private Rectangle headBox;
	
	
	public Spring(int blocksRight, int blocksDown) {
		super(blocksRight * Tile.defaultWidth + Tile.defaultWidth/2 - Pictures.spring.getWidth() / 2-14.15, 
				blocksDown * Tile.defaultHeight + Tile.defaultHeight/2 - Pictures.spring.getHeight() / 2-20,
				Pictures.spring.getWidth()*2-8,
				Pictures.spring.getHeight()*2-8,
				Pictures.spring);
		
		xOffset = (int)(Tile.defaultWidth);
		yOffset = (int)(Tile.defaultWidth);
		headBox = new Rectangle((int)getX() + 4, (int)getY(), getWidth()-8, 30);
		
	}
	
	
	@Override
	public void tick() {
	}

	/**
	 * Behavior when touched my character
	 */
	@Override
	public void interact(Character c) {
		//c.springJump();
		
		if(c.getUpwardsVelocity() > 0 && headBox.intersects(c.footBox))
		{
			c.setY(getY()-c.getHeight()+8);
			c.springJump();
		}
		
	}

}
