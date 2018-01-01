package images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import runner.MainLoop;

public class Timer {
	
	private int seconds;
	private int ticks = 0;

	public Timer(int seconds) {
		this.seconds = seconds;
	}

	public void tick() {
		
		ticks++;
		if(ticks == 60)
		{
			ticks = 0;
			
			if(seconds > 0)
				seconds--;
			
		}
		
	}

	public void render(Graphics g) {
		
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.BOLD);
		g.setFont(newFont);
		
		if(seconds < 60)
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLACK);
		
		g.drawString(Integer.toString(seconds), (int)(MainLoop.ratio*MainLoop.width) - 100, 50);
	}

}
