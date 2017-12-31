package items;

import creatures.Character;
import game.Game;
import images.Pictures;
import sound.SoundPlayer;
import stage.Stage;
import stage.Tile;

public class Key extends Item{

	public Key(int blocksRight, int blocksDown) {
		super(blocksRight * Tile.defaultWidth + Tile.defaultWidth/2
				- Pictures.apple.getWidth() / 2, blocksDown
				* Tile.defaultHeight + Tile.defaultHeight/2
				- Pictures.Key.getHeight() / 2, Pictures.Key.getWidth()*2,
				Pictures.Key.getHeight()*2, Pictures.Key);
		
		Game.setMaxXOffset(((blocksRight + 5)* Tile.defaultWidth - 1500));

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
		addScore(1000);
		SoundPlayer.playSound("power_upp.wav", false);
		
		
	}
}