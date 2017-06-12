package game;

import java.awt.Graphics;


public interface State {
	
	void init();
	void tick();
	void render(Graphics g);

}
