package items;

import creatures.Character;
import images.Pictures;
import stage.Stage;
import stage.Tile;

public class Shoes extends Item {

	
	public Shoes(int blocksRight, int blocksDown) {
		super(blocksRight * Tile.defaultWidth + Tile.defaultWidth/2
				- Pictures.shoes.getWidth() / 2, blocksDown
				* Tile.defaultHeight + Tile.defaultHeight/2
				- Pictures.shoes.getHeight() / 2, Pictures.shoes.getWidth()*2,
				Pictures.shoes.getHeight()*2, Pictures.shoes);

	}

	/**
	 * Behavior when touched my character
	 */
	@Override
	public void interact(Character c) {
		Stage.items.remove(this);
		c.startPowerUp(2);
		addScore(20);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
