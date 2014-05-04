package hw8;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
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
	public void mouseDragged(MouseEvent e) {
	}
}
