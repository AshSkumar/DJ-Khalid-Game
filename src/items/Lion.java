package items;

import creatures.Character;
import images.Pictures;
import stage.Stage;
import stage.Tile;

public class Lion extends Item {

	public Lion(int blocksRight, int blocksDown) {
		super(blocksRight * Tile.defaultWidth + Tile.defaultWidth/2
				- Pictures.lionPowerup.getWidth() / 2, blocksDown
				* Tile.defaultHeight + Tile.defaultHeight/2
				- Pictures.lionPowerup.getHeight() / 2, Pictures.lionPowerup.getWidth()*2,
				Pictures.lionPowerup.getHeight()*2, Pictures.lionPowerup);

	}

	/**
	 * Behavior when touched my character
	 */
	@Override
	public void interact(Character c) {
		
		Stage.items.remove(this);
		c.startPowerUp(1);
		addScore(20);
		//SoundPlayer.playSound("lion.wav", false);

	}

	@Override
	public void tick() {


		
	}

	

}
