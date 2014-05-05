package hw8;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

/**
 * Handles mouse events
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class MouseHandler extends MouseInputAdapter {
	private MandelView mandelView;
	private Point start, end;
	
	/**
	 * Give the handler the view where it needs to apply the actions.
	 * @param mandelView
	 */
	public MouseHandler(MandelView mandelView) {
		this.mandelView = mandelView;
	}

	/**
	 * Zoom in centered on the click with a factor 2 or zoom out when shift is
	 * pressed.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.isShiftDown()) {
			mandelView.setView(mandelView.getXcoord(e.getX()),
					mandelView.getYcoord(e.getY()), mandelView.getScale() / 2);
		} else {
			mandelView.setView(mandelView.getXcoord(e.getX()),
					mandelView.getYcoord(e.getY()), mandelView.getScale() * 2);
		}
		
	}

	/**
	 * Initialize a drag
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		start = e.getPoint();
	}

	/**
	 * End a drag and apply it.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (end != null) {
			mandelView.doZoom();
		}		
		start = end = null;
	}

	/**
	 * Update the zoom rectangle and the coordinates to be zoomed in on
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		end = e.getPoint();
		int width = Math.max(Math.abs(end.x - start.x), Math.abs(
				(end.y - start.y) *	mandelView.getWidth() /
				mandelView.getHeight()));
		int height = Math.max(Math.abs(end.y - start.y), Math.abs(
				(end.x - start.x) *	mandelView.getHeight() /
				mandelView.getWidth()));
		int x = start.x, y = start.y;
		if (start.x > end.x) {
			x -= width;
		}
		if (start.y > end.y){
			y -= height;
		}
		mandelView.setZoomRect(new Rectangle(x, y, width, height));
	}
}
