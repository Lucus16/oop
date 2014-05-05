package hw8;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class MouseHandler extends MouseInputAdapter {
	private MandelView mandelView;
	private Point start, end;
	
	public MouseHandler(MandelView mandelView) {
		this.mandelView = mandelView;
	}

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

	@Override
	public void mousePressed(MouseEvent e) {
		start = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (end != null) {
			mandelView.doZoom();
		}		
		start = end = null;
	}

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
