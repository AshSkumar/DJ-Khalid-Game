package stage;

import java.awt.image.BufferedImage;

public class Platform extends Tile{
	
	public Platform(int blocksRight, int blocksDown, BufferedImage texture) {
		super(blocksRight, blocksDown, texture);
	}

	public Platform(int x, int y, int width, int height, BufferedImage texture) {
		super(x, y, width, height, texture);
	}

	/**
	 * @return returns 1 to signify that the block is solid
	 */
	@Override
	public int isSolid() {
		return 1;
	}


	
	

}
