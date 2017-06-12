package stage;

import java.awt.image.BufferedImage;

public class Space extends Tile{

	public Space(int blocksRight, int blocksDown, BufferedImage texture) {
		super(blocksRight, blocksDown, texture);
	}

	/**
	 * @return returns 0 to signify that the block is not solid
	 */
	@Override
	public int isSolid() {
		return 0;
	}

}
