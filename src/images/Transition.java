package images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;

public class Transition {

	private static double x = 0, y = 0, x2 = 0;
	private static boolean opening = true;
	static boolean blackScreen = false;
	private static int ticks = 0;
	private static int endTick, endTick2, endTick3;

	/**
	 * Updates transition between levels
	 */
	public static void closingTick() {
	
		if(!blackScreen)
		{
			if(x>750)
			{
				blackScreen = true;
				x = 350;
				y = 0;
				endTick = 60;
				endTick2 = endTick + 240;
				endTick3 = endTick2 + 60;
			}
			x+=750/40;
			y+=400/40;		
		}
		else
		{
			if(ticks > endTick)
				x+=1500/60;
			if(ticks>endTick2)
				x2+= 1500/60;
			ticks++;
		}
		
		if(ticks>endTick3)
		{
			ticks = 0; 
			blackScreen = false;	
			x = 0; y = 0; x2 = 0;	
			Game.loadNextStage();
		}
			
		
	}

	/**
	 * Renders transition
	 * @param g - Game's Graphics object
	 */
	public static void closingRender(Graphics g) {
		
		if(!blackScreen)
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, 1500, (int) y);
			g.fillRect(0, 0, (int) x, 800);
			g.fillRect(1500-(int)x, 0, (int) x, 800);
			g.fillRect(0, 800-(int)y, 1500, (int)y);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1500, 800);
			g.setColor(Color.RED);
			g.setFont(new Font(null, Font.BOLD, 120));
			g.drawString("Stage " + (Game.stageNumber + 1), 500, 200);
			g.drawImage(Pictures.dj, 400, 300, null);
			g.setColor(Color.BLACK);
			g.fillRect((int)x, 0, (int) (1500-x), 800);
			g.fillRect(0, 0, (int) x2, 800);
		}
		

	}

}
