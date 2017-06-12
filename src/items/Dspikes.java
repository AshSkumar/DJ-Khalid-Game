package items;

import creatures.Character;
import images.Pictures;
import stage.Tile;


public class Dspikes extends Item{
	public Dspikes(int blocksRight, int blocksDown) {
		super(blocksRight * Tile.defaultWidth + Tile.defaultWidth/2
				- Pictures.dspike.getWidth() / 2-14.15, blocksDown
				* Tile.defaultHeight + Tile.defaultHeight/2
				- Pictures.dspike.getHeight() / 2-20, Pictures.dspike.getWidth()*2,
				Pictures.dspike.getHeight()*2, Pictures.dspike);
	}
	
	@Override
	public void tick() {
	}
	

	/**
	 * Behavior when touched my character
	 */
	@Override
	public void interact(Character c) {
		c.decreaseHealth();
	}

}
