package images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import runner.MainLoop;

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
			if(x>(int)(750*MainLoop.ratio))
			{
				blackScreen = true;
				x = (int)(350*MainLoop.ratio);
				y = 0;
				endTick = 60;
				endTick2 = endTick + 240;
				endTick3 = endTick2 + 60;
			}
			x+=(int)(750*MainLoop.ratio)/40;
			y+=(int)(400*MainLoop.ratio)/40;		
		}
		else
		{
			if(ticks > endTick)
				x+=(int)(1500*MainLoop.ratio)/60;
			if(ticks>endTick2)
				x2+= (int)(1500*MainLoop.ratio)/60;
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
			g.fillRect(0, 0, MainLoop.width, (int) y);
			g.fillRect(0, 0, (int) x, MainLoop.height);
			g.fillRect(MainLoop.width-(int)x, 0, (int) x, MainLoop.height);
			g.fillRect(0, MainLoop.height-(int)y, MainLoop.width, (int)y);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, MainLoop.width, MainLoop.height);
			g.setColor(Color.RED);
			g.setFont(new Font(null, Font.BOLD, 120));
			g.drawString("Stage " + (Game.stageNumber + 1), (int)(500*MainLoop.ratio), (int)(200*MainLoop.ratio));
			g.drawImage(Pictures.dj, (int)(400*MainLoop.ratio), (int)(300*MainLoop.ratio), null);
			g.setColor(Color.BLACK);
			g.fillRect((int)x, 0, (int) (MainLoop.width-x), MainLoop.height);
			g.fillRect(0, 0, (int) x2, MainLoop.height);
		}
		

	}

}
