package images;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import game.Game;

public class Background {
	
	private static List<BufferedImage> images = new ArrayList<BufferedImage>();
	static boolean transitioning = false;
	private static int yOffset = 0;
	

	public static void init()
	{
		
	}

	/**
	 * Renders background
	 * @param g - Game's Graphics object
	 */
	public static void render(Graphics g) {
			for(int x = 0; x < images.size(); x++)
				g.drawImage(images.get(x), (int) (1500*x - Game.getxOffset()), 0, 1500, 800, null);	
	}

	public static void setImages(List<BufferedImage> images) {
		Background.images = images;
	}

	public static void setImage(BufferedImage image) {
		images.add(0, image);	
	}

}
