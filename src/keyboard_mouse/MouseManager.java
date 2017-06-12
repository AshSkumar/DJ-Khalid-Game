package keyboard_mouse;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener{
	
	private static Point pointClicked = new Point();
	
	public MouseManager(Canvas canvas)
	{
		canvas.addMouseListener(this);
	}

	public void tick() {
		
		
		
	}

	/**
	 * If mouse clicked, finds point clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		pointClicked.setLocation(e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}


	public static Point getPointClicked() {
		return pointClicked;
	}

}
