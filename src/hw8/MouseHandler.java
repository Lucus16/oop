package hw8;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
	private MandelView mandelView;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
