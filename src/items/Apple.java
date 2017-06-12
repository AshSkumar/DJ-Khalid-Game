package items;

import creatures.Character;
import images.Pictures;
import sound.SoundPlayer;
import stage.Stage;
import stage.Tile;

public class Apple extends Item{

	public Apple(int blocksRight, int blocksDown) {
		super(blocksRight * Tile.defaultWidth + Tile.defaultWidth/2
				- Pictures.apple.getWidth() / 2, blocksDown
				* Tile.defaultHeight + Tile.defaultHeight/2
				- Pictures.apple.getHeight() / 2, Pictures.apple.getWidth()*2,
				Pictures.apple.getHeight()*2, Pictures.apple);

	}

	@Override
	public void tick() {
		
		
	}

	
	/**
	 * Behavior when touched my character
	 */
	@Override
	public void interact(Character c) {

		Stage.items.remove(this);
		addScore(10);
		SoundPlayer.playSound("apple_sound.wav", false);
	}
}
