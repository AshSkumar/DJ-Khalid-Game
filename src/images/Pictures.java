package images;

import java.awt.image.BufferedImage;

public class Pictures{

	public static BufferedImage exp, f, d, shoes, attack, f1, f2, f3, sand, ghost2, wall, dj, lionReverse, lionPowerup, miami, miamiend, miamihole, miamistart, miaminight, heart, halfHeart, plantLeft, plantRight, Aenemy, ghost, apple, nebula, menuScreen, menuButton, startButton, instructionsButton, e, cb, shwin, dspike, spike, Key, lion;
	public static BufferedImage[] djSprites = new BufferedImage[12];
	public static BufferedImage[] fAttack = new BufferedImage[3];

	/**
	 * Loads in all pictures
	 */
	public static void init() {
		
		for(int x = 0; x < 5; x ++)
			djSprites[x] = ImageLoader.loadImage("/textures/dj" + (x+1) + ".png");

		for(int x = 5; x < 10; x++)
			djSprites[x] = ImageLoader.loadImage("/textures/dj" + (x-4) + "Reverse.png");
		
		djSprites[10] = djSprites[4];
		djSprites[11] = djSprites[9];
		
		sand = ImageLoader.loadImage("/textures/BigBlock.png");
		f = ImageLoader.loadImage("/textures/final.png");
		d = ImageLoader.loadImage("/textures/DoorF.png");
		dj = ImageLoader.loadImage("/textures/BlackDJ.jpg");
		wall = ImageLoader.loadImage("/textures/wall1.jpg");
		miami = ImageLoader.loadImage("/textures/miami.jpg");
		miamiend = ImageLoader.loadImage("/textures/miamiend.jpg");
		miamihole = ImageLoader.loadImage("/textures/miamihole.jpg");
		miamistart = ImageLoader.loadImage("/textures/miamistart.jpg");
		miaminight = ImageLoader.loadImage("/textures/miaminight.jpg");
		apple = ImageLoader.loadImage("/textures/apple.png");
		menuScreen = ImageLoader.loadImage("/textures/main.png");
		menuButton= ImageLoader.loadImage("/textures/Main Screen.png");
		startButton= ImageLoader.loadImage("/textures/Play.png");
		instructionsButton = ImageLoader.loadImage("/textures/instructionsB.png");
		lionPowerup = ImageLoader.loadImage("/textures/lionPowerup.png");
		lion = ImageLoader.loadImage("/textures/lion.png");
		lionReverse = ImageLoader.loadImage("/textures/lionReverse.png");
		cb = ImageLoader.loadImage("/textures/cbutter.png");
		e = ImageLoader.loadImage("/textures/apple.png");
		shwin = ImageLoader.loadImage("/textures/instructions.png");
		spike = ImageLoader.loadImage("/textures/spike.png");
		dspike = ImageLoader.loadImage("/textures/dspike.png");
		nebula = ImageLoader.loadImage("/textures/nebula.jpg");
		plantLeft = ImageLoader.loadImage("/textures/GoombaLeft.png");
		plantRight = ImageLoader.loadImage("/textures/GoombaRight.png");
		heart = ImageLoader.loadImage("/textures/heart.png");
		halfHeart = crop(heart, 0, 0, heart.getWidth()/2, heart.getHeight());
		Key = ImageLoader.loadImage("/textures/key.png");
		ghost = ImageLoader.loadImage("/textures/Iggy.png");
		ghost2 = ImageLoader.loadImage("/textures/Iggy2.png");
		Aenemy = ImageLoader.loadImage("/textures/Aenemy.png");
		attack = ImageLoader.loadImage("/textures/record.png");
		shoes = ImageLoader.loadImage("/textures/shoe.png");
		fAttack[0] = ImageLoader.loadImage("/textures/white flower.png");
		fAttack[1] = ImageLoader.loadImage("/textures/red flower.png");
		fAttack[2] = ImageLoader.loadImage("/textures/blue flower.png");
		exp = ImageLoader.loadImage("/textures/ExplosionFinal.png");
		
	}
	
	/**
	 * Crops image (Unused)
	 * @param image - image to create new image from
	 * @param x - x to start at
	 * @param y - y to start at
	 * @param width - width of section being removed
	 * @param y - height of section being removed
	 * @return a new BufferedImage cropped from original
	 */
	public static BufferedImage crop(BufferedImage image, int x, int y, int width, int height){
		
		return image.getSubimage(x, y, width, height);
		
	}

}
