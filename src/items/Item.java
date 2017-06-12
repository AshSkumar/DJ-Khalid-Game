package items;

import java.awt.image.BufferedImage;

import creatures.Character;
import creatures.Entity;

public abstract class Item extends Entity{

	public Item(double x, double y, int width, int height, BufferedImage texture) {
		super(x, y, width, height, texture);
	}

	public abstract void interact(Character c);
}
