package keyboard_mouse;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	private static boolean[] arrows;
	private boolean up, left, right, down;
	private Boolean spaceReleased;
	
	public KeyManager(){
		keys = new boolean[256];
		arrows=new boolean[7];
	}
	
	/**
	 * Updates booleans for whether each key is being pressed this tick
	 */
	public void tick(){
		arrows[0]=keys[KeyEvent.VK_UP];
		arrows[1]=keys[KeyEvent.VK_LEFT];
		arrows[2]=keys[KeyEvent.VK_RIGHT];
		arrows[3]=keys[KeyEvent.VK_DOWN];
		arrows[4]=keys[KeyEvent.VK_SPACE];
		arrows[5]=keys[KeyEvent.VK_A];
		arrows[6]=keys[KeyEvent.VK_R];
	}
	
	/**
	 * Keycode of any pressed keys becomes true
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/**
	 * Keycode of unpressed keys becomes false
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public static boolean[] getArrows()
	{
		return arrows;
	}
	
	

}
