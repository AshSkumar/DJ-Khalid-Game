package images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import runner.MainLoop;

public class Writer{
	
	private double x, y;
	private int ticks = 0;
	private int score = 0;
	private boolean health = false;
	
	public Writer(double x, double y, int score)
	{
		this.x = x;
		this.y = y;
		this.score = score;
		Game.getWriters().add(this);
	}
	
	public Writer(double x, double y)
	{
		this.x = x;
		this.y = y;
		health = true;
		Game.getWriters().add(this);
	}
	
	/**
	 * Updates writer
	 */
	public void tick()
	{
		if(ticks > 60)
			Game.getWriters().remove(this);
		ticks++;
		y-=.5;
		
	}
	
	/**
	 * Renders
	 * @param g - Game's graphics object
	 */
	public void render(Graphics g)
	{
		
		if(health)
		{	
			g.setColor(Color.RED);
			g.drawString("-", (int)(x-Game.getxOffset()), (int)y);
			g.drawImage(Pictures.heart, (int) (x - Game.getxOffset()+15), (int)y-20, 25, 25, null);
		}
		else
		{
			g.setFont(g.getFont().deriveFont(Font.PLAIN, (int)(MainLoop.ratio*25)));
			g.setColor(Color.WHITE);
			g.drawString("+" + score, (int) (MainLoop.ratio*(x - Game.getxOffset())), (int) (MainLoop.ratio*y));
		}
		
			
	}
	
}
