package runner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import game.Display;
import game.Game;
import game.Menu;
import game.State;
import images.Pictures;
import keyboard_mouse.KeyManager;
import keyboard_mouse.MouseManager;
import sound.SoundPlayer;

public class MainLoop{ 
	/*
	 * Most of the code in this class is from this series: 
	 * https://www.youtube.com/watch?v=dEKs-3GhVKQ&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ
	 * 
	 */
	
	private Display display;
	public static final int width = 1500;
	public static final int height = 800;
	public static final double ratio = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1500;
	
	private BufferStrategy bs;
	private Graphics g;
	private State state;
	
	//States
	private KeyManager keyManager;	
	private MouseManager mouseManager;
		
	/**
	 * Initializes everything
	 */
	private void init() 
	{
		
		state = new Menu();
		//Sound.musicBack();
		keyManager = new KeyManager();
		
		display = new Display("Major Key", (int)(ratio*width), (int)(ratio*height));
		display.getFrame().addKeyListener(keyManager);
		Pictures.init();
		state.init();
		mouseManager = new MouseManager(display.getCanvas());
		SoundPlayer.playSound("DJ!.wav", false);
	}
	
	/**
	 * Updates, keyboard, mouse, and current state
	 */
	private void tick(){
		
		//if(State.getState() == null)
		//	return;
		keyManager.tick();
		mouseManager.tick();
		//State.getState().tick();
		state.tick();
		
	}
	
	/**
	 * Gets buffer stradegy and its Graphics object, then renders state
	 */
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear screen
		g.clearRect(0, 0 , width, height);
		
		//Draw here
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);


		state.render(g);
		//End draw
		bs.show();
		g.dispose();
	}
	
	/**
	 *  Loads state from game and vice versa
	 *  @param newState - Name of state. Not case sensitive
	 */
	public void loadState(String newState)
	{
		if(newState.equalsIgnoreCase("Game"))
			state = new Game();
		else if(newState.equalsIgnoreCase("Menu"))
			state = new Menu();
		
		state.init();
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
		
	/**
	 * Starts mainloop of game
	 */
	public void play() {
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(true){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now -lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000)
			{
				ticks = 0;
				timer = 0;
				delta=0;
			}
			
		}
	}
	
}
