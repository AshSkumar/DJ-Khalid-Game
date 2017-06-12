package items;

import creatures.Character;
import images.Pictures;
import stage.Tile;

public class Spikes extends Item{
	public Spikes(int blocksRight, int blocksDown) {
		super(blocksRight * Tile.defaultWidth + Tile.defaultWidth/2
				- Pictures.spike.getWidth() / 2-14.15, blocksDown
				* Tile.defaultHeight + Tile.defaultHeight/2
				- Pictures.spike.getHeight() / 2-12, Pictures.spike.getWidth()*2,
				Pictures.spike.getHeight()*2, Pictures.spike);
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
