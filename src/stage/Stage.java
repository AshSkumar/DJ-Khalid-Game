package stage;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import creatures.BlockEnemy;
import creatures.Enemy;
import creatures.Iguana;
import creatures.PlantEnemy;
import images.Pictures;
import items.Apple;
import items.Door;
import items.Dspikes;
import items.Item;
import items.Key;
import items.Lion;
import items.Shoes;
import items.Spikes;

public class Stage {

	public static Tile[][] stage;
	public static List<Item> items = new ArrayList<>();
	public static List<Enemy> enemies = new ArrayList<>();
	public static List<BufferedImage> backgrounds = new ArrayList<BufferedImage>();

	/**
	 * Reads txt file of signified stage and constructs it
	 * @param stage - number of stage
	 */
	public static void init(int stage) {
		
		Stage.stage = null;items.clear();enemies.clear(); backgrounds.clear();
		
		try {
			read("res/stages/stage" + stage + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads txt file
	 * @return file - file name
	 */
	private static void read(String file) throws FileNotFoundException, IOException {

		List<String> lines = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) { //Code to read txt from here: https://stackoverflow.com/questions/2049380/reading-a-text-file-in-java
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		}
		Stage.stage = null;
		Tile[][] stage = new Tile[lines.size()][lines.get(0).length()];

		for (int blocksDown = 0; blocksDown < lines.size(); blocksDown++) {
			for (int blocksRight = 0; blocksRight < lines.get(0).length(); blocksRight++) {
				switch (lines.get(blocksDown).charAt(blocksRight)) {
				case '0':
					// stage[blocksDown][blocksRight] = new Space(blocksRight,
					// blocksDown);
					break;
				case '6':
					backgrounds.add(Pictures.miami);
					break;
				case 'M':
					backgrounds.add(Pictures.miamiend);
					break;
				case 'N':
					backgrounds.add(Pictures.miamihole);
					break;
				case 'O':
					backgrounds.add(Pictures.miamistart);
					break;
				case 'V':
					backgrounds.add(Pictures.miaminight);
					break;
				case 'F':
					backgrounds.add(Pictures.f);
					break;
				case '1':
					stage[blocksDown][blocksRight] = new Platform(blocksRight, blocksDown, Pictures.sand);
					break;
				case '2':
					stage[blocksDown][blocksRight] = new Platform(blocksRight, blocksDown, Pictures.wall);
					break;
				case 'A':
					items.add(new Apple(blocksRight, blocksDown));
					break;
				case 'L':
					items.add(new Lion(blocksRight, blocksDown));
					break;
				case 'H':
					items.add(new Shoes(blocksRight, blocksDown));
					break;
				case 'K':
					items.add(new Key(blocksRight, blocksDown));
					break;
				case 'E':
					items.add(new Door(blocksRight, blocksDown));
					break;
				case 'P':
					enemies.add(new PlantEnemy(blocksRight*Tile.defaultWidth, blocksDown*Tile.defaultHeight, 85, 85, 3, Pictures.plantLeft, Pictures.plantRight));
					break;
				case 'p':
					enemies.add(new PlantEnemy(blocksRight*Tile.defaultWidth, blocksDown*Tile.defaultHeight, 70, 70, 3, Pictures.plantLeft, Pictures.plantRight));
					break;
				case '3':
					stage[blocksDown][blocksRight] = new Platform(blocksRight, blocksDown, Pictures.spike);
					items.add(new Spikes(blocksRight, blocksDown));
					break;
				case '4':
					stage[blocksDown][blocksRight] = new Platform(blocksRight, blocksDown, Pictures.dspike);
					items.add(new Dspikes(blocksRight, blocksDown));
					break;
				case 'B': 
					enemyBlock(blocksRight, blocksDown, Pictures.sand, 8, false);
					break;
				case 'b': 
					enemyBlock(blocksRight, blocksDown, Pictures.sand, 8, true);
					break;				
				case 'G':
					enemies.add(new Iguana(blocksRight*Tile.defaultWidth, blocksDown*Tile.defaultHeight, 200, 200, 3, Pictures.ghost, Pictures.ghost2));
					break;
				}
			}
		}
		Stage.stage = stage;
	}

	/**
	 * Creates a new blockEnemy
	 * @param pieces - Number of enemies that spans one side of the block
	 * @param fake 
	 */
		private static void enemyBlock(int blocksRight, int blocksDown, BufferedImage image, int pieces, boolean fake) {
		
		double width = (double)Tile.defaultWidth/pieces;
		double height = (double)Tile.defaultHeight/pieces;
		double imageWidth = image.getWidth()/pieces;
		double imageHeight = image.getHeight()/pieces;
		
		for(int y = 0; y < pieces; y++)
		{
			for(int x = 0; x < pieces; x++)
			{
				enemies.add(new BlockEnemy(blocksRight*Tile.defaultWidth+width*x, blocksDown*Tile.defaultHeight+height*y, (int)Math.ceil(width), (int)Math.ceil(height), Pictures.crop(image, (int)(x*imageWidth), (int)(y*imageHeight), (int)imageWidth, (int)imageHeight), pieces, pieces-y, fake));
			}
		}	
	}

}
