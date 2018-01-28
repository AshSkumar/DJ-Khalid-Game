package creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Cycle;
import game.Game;
import images.Pictures;
import images.Writer;
import items.Item;
import keyboard_mouse.KeyManager;
import sound.SoundPlayer;
import stage.Tile;

public class Character extends Creature {

	private boolean[] move;
	private double upwardsVelocity = 0;
	public double maxSpeed = 5;
	private int jumpNumber = 0;
	private boolean extraJump = false;
	private Cycle runningCycle;
	private BufferedImage[] sprites = Pictures.djSprites;
	boolean facingRight = true;
	private int powerUpState = 0;
	private int ticks = 0, endTick = 600;
	private int healthTicks = 0;
	public Rectangle footBox;
	public Attack attack=new Attack(-10,-100,30,30,Pictures.attack);
	private int attackTicks=120;
	
	public Character(double x, double y, int width, int height,
			BufferedImage texture) {
		super(x, y, width, height, texture);
		move = KeyManager.getArrows();
		runningCycle = new Cycle(5, 8);
		footBox = new Rectangle((int)x, (int)y, 40, 15);
	}

	/**
	 * Updates Character
	 */
	@Override
	public void tick() {
		
		if(powerUpState != 0)
			powerUpTick();

		healthTicks++;
		selectSprite();

		move();
		checkItems();
		checkEnemies();

		setXOffset(); // Moves screen
		setYOffset();
		
		//restart level on press of 'r'
		if(move[6]){
			Game.loadStage();
			health=5;
			score=0;
		}
		if(getY()>1200){
			Game.loadStage();
			SoundPlayer.playSound("lose.wav", false);
			health=5;
			score=0;
		}	

	}

	/**
	 * Updates position
	 */
	@Override
	public void move() {
		
		//if lion power up gained, switch movement to lion
		if(powerUpState == 1)
		{
			lionMove();
			return;		
		}
		
		if(move[1] ^ move[2])
		{
		if (move[1]) { // Move left
			if(speed > -maxSpeed)
				speed -= .65;
		} else if (move[2]) { // Move right
			if(speed < maxSpeed)
			speed += .65;			
		}} else {
			if (speed > 1)
				speed -= .65;
			else if (speed < -1)
				speed += .65;
			else
				speed = 0;
		}


		if (canMove((int) Math.ceil(speed), 0)
				&& canMove((int) (speed / Math.abs(speed)), 0)) {
			setX(getX() + speed);
		} else if (speed > 0) {
			while (canMove(1, 0)) {
				setX(getX() + 1);
			}
			speed = 0;
		} else if (speed < 0) {
			while (canMove(-1, 0)) {
				setX(getX() - 1);
			}
			speed = 0;
		}

		if (!move[4] && jumpNumber < 2) {
			extraJump = true;
		}

		if (move[4] && (!canMove(0, 1) || extraJump)) {
			if(jumpNumber == 0)
			SoundPlayer.playSound("jump.wav", false);
			extraJump = false;
			jumpNumber++;
			upwardsVelocity = -15;
		}
		
		//timing to allow/remove attacks every 1 second 
		if(attackTicks>60){	
			attack.setX(-100);
			if(move[5]){
				attack.facingRight = facingRight;
				attack.setX(getX());
				attack.setY(getY()+35);
				attackTicks=0;
			}	
		}
		attackTicks++;	
		
		attack.tick();
		
		boolean intersectsTile = false;
		
//		for(int x = xStart; x < xStart+2; xStart++)
//			for(int y = yStart; y < yStart + 2; y++)
//				if(x < 0 && y < 0 && x > Game.getStage()[0].length && y > Game.getStage().length && attack.getHitbox().intersects(Game.getStage()[x][y].getHitbox()))
//					intersectsTile = true;
		
		if(attack.canMove(0, 1) && !attack.canMove((int) attack.speed, 0))
			intersectsTile = true;
			
			
		if(intersectsTile)
		{
			attackTicks = 130;
		}
		
		if (canMove(0, (int) Math.ceil(upwardsVelocity))) {
			setY(getY() + upwardsVelocity);
		} else if (upwardsVelocity > 0) {
			while (canMove(0, 1)) {
				setY(getY() + 1);
			}
			upwardsVelocity = 0;
			jumpNumber = 0;
		} else if (upwardsVelocity < 0) {
			while (canMove(0, -1)) {
				setY(getY() - 1);
			}
			upwardsVelocity = 0;
		}

		upwardsVelocity += .85;

		getHitbox().setLocation((int) getX(), (int) getY()); // Moves hitbox
		footBox.setLocation((int) getX(), (int) getY()+75);
		
		
	}

	
	/**
	 * Updates movement when lion powerup is active
	 */
	private void lionMove() {
		
		if (move[1]) { // Move left
			speed -= .65;
		} else if (move[2]) { // Move right
			speed += .65;
		} else {
			if (speed > 1)
				speed -= .65;
			else if (speed < -1)
				speed += .65;
			else
				speed = 0;
		}
		if (speed > maxSpeed)
			speed = maxSpeed;
		else if (speed < -maxSpeed)
			speed = -maxSpeed;

		if (canMove((int) Math.ceil(speed), 0)
				&& canMove((int) (speed / Math.abs(speed)), 0)) {
			setX(getX() + speed);
		} else if (speed > 0) {
			while (canMove(1, 0)) {
				setX(getX() + 1);
			}
			speed = 0;
		} else if (speed < 0) {
			while (canMove(-1, 0)) {
				setX(getX() - 1);
			}
			speed = 0;
		}
		
		if (move[0]) { // Move left
			upwardsVelocity -= .65;
		} else if (move[3]) { // Move right
			upwardsVelocity += .65;
		} else {
			if (upwardsVelocity > 1)
				upwardsVelocity -= .65;
			else if (upwardsVelocity < -1)
				upwardsVelocity += .65;
			else
				upwardsVelocity = 0;
		}
		if (upwardsVelocity > maxSpeed)
			upwardsVelocity = maxSpeed;
		else if (upwardsVelocity < -maxSpeed)
			upwardsVelocity = -maxSpeed;

		if (canMove(0, (int) Math.ceil(upwardsVelocity))
				&& canMove(0,(int) (upwardsVelocity / Math.abs(upwardsVelocity)))) {
			setY(getY() + upwardsVelocity);
		} else if (upwardsVelocity > 0) {
			while (canMove(0, 1)) {
				setY(getY() + 1);
			}
			upwardsVelocity = 0;
		} else if (upwardsVelocity < 0) {
			while (canMove(0, -1)) {
				setY(getY() - 1);
			}
			upwardsVelocity = 0;
		}

		getHitbox().setLocation((int) getX(), (int) getY()); // Moves hitbox
		

		
	}

	
	public void springJump(){
		int up = 24;
		upwardsVelocity = 0;
		for(int x = 0; x < 26; x++){
			upwardsVelocity += up;
			up = up - 2;
		}
	}
	
	/**
	 * Updates sprite
	 */
	@Override
	public void selectSprite() {
		
		runningCycle.tick();
	
		if(move[1] ^ move[2])
		{
			if(move[2])
				facingRight = true;
			else if(move[1])
				facingRight = false;
			
		}
		
		if(powerUpState == 1)
		{
			if(facingRight)
				setTexture(Pictures.lion);
			else
				setTexture(Pictures.lionReverse);
			return;
		}
		
		if(!(move[1] ^ move[2]))
		{
			if(facingRight)
				setTexture(sprites[0]);
			else
				setTexture(sprites[5]);
			return;
		}
		
		if(canMove(0,1))
		{
			if(facingRight)
				setTexture(sprites[10]);
			else
				setTexture(sprites[11]);
		}
		else
		{

			if(move[2])
				setTexture(sprites[runningCycle.getStepNumber()]);
			else if(move[1])
				setTexture(sprites[5 + runningCycle.getStepNumber()]);
			else if (facingRight)
				setTexture(sprites[0]);
			else
				setTexture(sprites[5]);
		}
	}

	/**
	 * Updates how far left everything must move to be shown in the window
	 */
	public void setXOffset() {
		if (getX() - Game.getxOffset() < (600 - getWidth() / 2)) {
			Game.setxOffset(getX() - (600 - getWidth() / 2));
		}
		if (getX() - Game.getxOffset() > (900 - getWidth() / 2)) {
			Game.setxOffset(getX() - (900 - getWidth() / 2));
		}

		if (Game.getyOffset() > Game.getStage()[0].length * Tile.defaultWidth
				- 1500) {
			Game.setyOffset(Game.getStage()[0].length * Tile.defaultWidth
					- 1500);
		}

		if (Game.getxOffset() < 0) {
			Game.setxOffset(0);
		}

	}
	
	
	/**
	 * Updates how far down everything must move to be shown in the window
	 */
	public void setYOffset() {

		Game.setyOffset(Game.getStage().length * Tile.defaultHeight - 800 + 100);
	}


	/**
	 * Checks if character has touched an item and if so activates it
	 */
	private void checkItems() {
		
		Item touchedItem = null;

		for(Item i: Game.getItems())
			if(getHitbox().intersects(i.getHitbox()))
				touchedItem = i;
		
		if(touchedItem == null)
			return;
		else
			touchedItem.interact(this);
	}
	

	/**
	 * Checks if character has touched an enemy, and if so calls enemy's collision method
	 */
	private void checkEnemies() {
		
		Enemy touchedEnemy = null;

		for(Enemy e: Game.getEnemies())
			if(getHitbox().intersects(e.getHitbox()))
				touchedEnemy = e;
		
		if(touchedEnemy == null)
			return;
		else
			touchedEnemy.interact(this);
	}


	/**
	 * Initializes powerup
	 * @param i - number id for powerup
	 */
	public void startPowerUp(int i) {

		powerUpState = i;
		ticks = 0;
		
		if(i == 1)
		{
			maxSpeed = 10;
			endTick = 300;
			setWidth(100);
			setHeight(120);
			getHitbox().setSize(getWidth(), getHeight());
			setTileDimensions();
			while(!canMove(0, 0))
				setY(getY()-1);
		}
		else if(i == 2)
		{
			endTick = 300;
			maxSpeed = 20;
		}
			
	}
	public double getUpwardsVelocity(){
		return upwardsVelocity;
	}
	

	/**
	 * Countdown for powerup. Resets character when exceeds max tick
	 */
	public void powerUpTick()
	{
		ticks++;
		if(ticks > endTick)
		{
			powerUpState = 0;
			maxSpeed = 5;
			setWidth(40);
			setHeight(90);
			getHitbox().setSize(getWidth(), getHeight());
			setTileDimensions();
		}
			
	}
	

	/**
	 * Overriden render method to render Character health
	 * @param g - Game's Graphics object
	 */
	@Override
	public void render(Graphics g)
	{
		if(!(powerUpState==1 && ticks%30>14 && ticks>200))
			super.render(g);
//		g.setColor(Color.GREEN);
//		g.drawRect((int) (footBox.x - Game.getxOffset()), footBox.y, footBox.width, footBox.height);
		for(int x = 0; x < health; x++)
		{
			g.drawImage(Pictures.heart, 50 + x*20, 60, 20, 20, null);
		}
		attack.render(g);	
	}
	

	/**
	 * Decreases health and restes game when health hits zero
	 */
	public void decreaseHealth() {
		
		if(healthTicks > 60){
			health--;
			healthTicks = 0;
			new Writer(getX(), getY());
			
			//restart stage if you lose all of your health
			if(health==0){
				SoundPlayer.playSound("lose.wav", false);
				Game.loadStage();
				health=5;
			}
		}
		
	}
	

	/**
	 * bounce after jumping on enemy
	 */
	public void bounce() {

		upwardsVelocity = -15;
		
	}


}
