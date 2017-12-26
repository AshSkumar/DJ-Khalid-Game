package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import images.Background;
import images.Pictures;
import keyboard_mouse.MouseManager;
import runner.Runner;
import sound.SoundPlayer;

public class Menu implements State {
	
	private Button menu, play, instructions;
	private Point pointClicked;
	private boolean instructionsScreen;
	
	/**
	 * Initializes everything necessary for menu
	 */
	@Override
	public void init() {
		Background.setImage(Pictures.menuScreen);
		pointClicked = MouseManager.getPointClicked();
		play = new Button(920, 260, Pictures.startButton);
		instructions = new Button(920, 420, Pictures.instructionsButton);
		menu = new Button(300, 400, Pictures.menuButton);
		SoundPlayer.playSound("Overture.wav", true);
	}
	
	/**
	 * Checks if button, and if so activates it
	 */
	@Override
	public void tick() {
		
		if(instructionsScreen)
		{
			if(menu.getHitbox().contains(pointClicked))
			{
				instructionsScreen = false;
				Background.setImage(Pictures.menuScreen);
			}
		}
		else
		{
			if(play.getHitbox().contains(pointClicked))
				Runner.loop.loadState("Game");
			if(instructions.getHitbox().contains(pointClicked))
			{
				instructionsScreen = true;
				Background.setImage(Pictures.shwin);
			}
		}
		
		
		
		
		pointClicked.setLocation(0, 0);
	}

	/**
	 * Renders everything on menu
	 * @param g - Game's Graphics object
	 */
	@Override
	public void render(Graphics g) {
		Background.render(g);
		
		if(!instructionsScreen)
		{
			play.render(g);
			instructions.render(g);
		}
		else
			menu.render(g);
		
		g.setColor(Color.RED);
		g.drawOval(pointClicked.x-2, pointClicked.y-2, 4, 4);
		
	}

}
