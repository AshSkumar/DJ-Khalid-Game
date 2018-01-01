package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import creatures.Character;
import creatures.Enemy;
import creatures.Entity;
import images.Background;
import images.Pictures;
import images.Timer;
import images.Transition;
import images.Writer;
import items.Item;
import sound.SoundPlayer;
import stage.Stage;
import stage.Tile;

public class Game implements State {

	private static double xOffset = 0;
	private static double yOffset = 0;
	public static Character dj;
	private static Tile[][] stage;
	private static List<Item> items;
	private static List<Enemy> enemies;
	private static List<Writer> writers;
	public static int stageNumber = 1;
	public static boolean closing = false;
	private static int maxXOffset = 0;
	private static int scoreSave = 0;
	private static Timer timer;

	/**
	 * Initialize game and starts first level
	 */
	public void init() {
		
		writers = new ArrayList<>();
		Background.setImages(Stage.backgrounds);
		Stage.init(1);
		enemies = Stage.enemies;
		stage = Stage.stage; // Gets stage
		setItems(Stage.items);
		dj = new Character(Tile.defaultWidth * 2 + 30, Tile.defaultHeight * (stage.length - 3) - 30, 40, 90,
				Pictures.djSprites[1]);
		Enemy.setC(dj);
		for (Enemy e : enemies)
			e.init();
		SoundPlayer.playSound("mario.wav", true);
		timer = new Timer(600);
		
	}

	/**
	 * Starts/restarts the stage signified by stageNumber
	 */
	public static void loadStage() {
		
		Entity.score = scoreSave;
		
		writers.clear();
		closing = false;
		Background.setImages(Stage.backgrounds);
		Stage.init(stageNumber);
		enemies = Stage.enemies;
		stage = Stage.stage; // Gets stage
		setItems(Stage.items);
		dj = new Character(Tile.defaultWidth * 2 + 30, Tile.defaultHeight * (stage.length - 3) - 30, 40, 90,
				Pictures.djSprites[1]);
		Enemy.setC(dj);
		for (Enemy e : enemies)
			e.init();
		if(stageNumber == 1)
			SoundPlayer.playSound("mario.wav", true);
		if(stageNumber == 2)
			SoundPlayer.playSound("derezzed.wav", true);
		else if(stageNumber == 3)
			SoundPlayer.playSound("darude.wav", true);
		if(stageNumber==3){
			Game.setMaxXOffset(8);
		}
		timer = new Timer(600);
	}

	/**
	 * Changes stageNumber and then loads the stage
	 */
	public static void loadNextStage() {
		scoreSave = Entity.score;
		stageNumber++;
		loadStage();
	}

	
	/**
	 * Updates everything in game.
	 */
	public void tick() {

		if (closing) {
			Transition.closingTick();
			return;
		}

		dj.tick();
		stageTick();
		timer.tick();
	}	
	
	/**
	 * Updates everything in stage
	 */
	private void stageTick() {
		
		for(int x = 0; x < writers.size(); x++)
			writers.get(x).tick();

		for (Item i : items)
			if(i.getX()+i.getWidth() > xOffset && i.getX() < xOffset + 1500)
			i.tick();
		
		
		for (int x = 0; x < enemies.size(); x++)
			if(enemies.get(x).getX()+enemies.get(x).getWidth() > xOffset && enemies.get(x).getX() < xOffset + 1500)
				enemies.get(x).tick();

	}
	
	/**
	 * Renders game
	 * @param g - Game's Graphics object
	 */
	public void render(Graphics g) {	
			
		Background.render(g);
		stageRender(g);
		dj.render(g);
		Entity.writeScore(g);
		timer.render(g);
		
		for(Writer w: writers)
			w.render(g);
		
		
		if (closing)
			Transition.closingRender(g);
	}
	
	/**
	 * Renders everything in the stage
	 * @param g - Game's Graphics object
	 */
	private void stageRender(Graphics g) {

		for (Item i : items)
			i.render(g);
		for (Enemy e : enemies)
			e.render(g);

		int xStart = (int) (xOffset / Tile.defaultWidth);

		for (Tile[] row : stage) 
		{			
			for (int x = xStart; x < xStart + 23; x++) 
			{				
				if (x < row.length && row[x] != null) 
				{
					row[x].render(g);
				}
			}
		}

	}

	public static double getxOffset() {
		return xOffset;
	}

	public static void setxOffset(double xOffset) {
		if(xOffset < maxXOffset)
			Game.xOffset = xOffset;
	}

	public static double getyOffset() {
		return yOffset;
	}

	public static void setyOffset(double yOffset) {
		Game.yOffset = yOffset;
	}

	public static Tile[][] getStage() {
		return stage;
	}

	public static List<Item> getItems() {
		return items;
	}

	public static void setItems(List<Item> items) {
		Game.items = items;
	}

	public static List<Enemy> getEnemies() {
		return enemies;
	}

	public static void setMaxXOffset(int maxXOffset) {
		Game.maxXOffset = maxXOffset;
	}

	public static List<Writer> getWriters() {
		return writers;
	}

}
