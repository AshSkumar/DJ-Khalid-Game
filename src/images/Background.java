package images;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import runner.MainLoop;

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
				g.drawImage(images.get(x), (int) (MainLoop.ratio*(MainLoop.width*x - Game.getxOffset())), 0, (int)(MainLoop.ratio*MainLoop.width), (int)(MainLoop.ratio*MainLoop.height), null);	
	}

	public static void setImages(List<BufferedImage> images) {
		Background.images = images;
	}

	public static void setImage(BufferedImage image) {
		images.add(0, image);	
	}

}
