package game;

import java.awt.image.BufferedImage;

import creatures.Entity;

public class Button extends Entity{

	public Button(double x, double y, BufferedImage texture) {
		super(x, y, texture.getWidth(), texture.getHeight(), texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {	
	}

}
