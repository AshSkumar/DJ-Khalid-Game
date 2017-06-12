package items;

import creatures.Character;
import game.Game;
import images.Pictures;
import sound.SoundPlayer;
import stage.Stage;
import stage.Tile;

public class Door extends Item{

	public Door(int blocksRight, int blocksDown) {
		super(blocksRight * Tile.defaultWidth, blocksDown
				* Tile.defaultHeight, Tile.defaultWidth,
				Tile.defaultHeight*2, Pictures.d);

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
		addScore(100);
		health = 5;
		Game.closing = true;
		SoundPlayer.stopMusic();
		SoundPlayer.playSound("victory.wav", true);
		
	}
}