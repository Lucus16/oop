package hw8;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MandelView extends GridView {
	private static final long serialVersionUID = -6339535732137830801L;
	private int limit;
	private Painter painter;
	private double minX, minY, maxX, maxY, scale;
	private Rectangle zoomRect;
	
	public MandelView(int width, int height, Painter painter) {
		super(width, height);
		setZoomRect(null);
		setLimit(256);
		this.painter = painter;
		setView(0, 0, Math.min(getWidth(), getHeight()) / 4);
	}
	
	public MandelView(int width, int height) {
		this(width, height, new ColorTable());
	}
	
	public void redraw() {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				int mandelNum = Mandelbrot.mandelnumber(getXcoord(x),
						getYcoord(y), limit);
				if (mandelNum == -1) {
					setPixel(x, y, Painter.BLACK);
				} else {
					setPixel(x, y, painter.getColor(mandelNum));
				}
			}
		}
		drawZoomRect();
		repaint();
	}
	
	public void drawZoomRect() {
		if (zoomRect != null) {
			Graphics g = getGraphics();
			g.setXORMode(Color.WHITE);
			g.drawRect(zoomRect.x, zoomRect.y, zoomRect.width, zoomRect.height);
		}
	}
	
	public void doZoom() {
		double cx = getXcoord(zoomRect.getCenterX());
		double cy = getYcoord(zoomRect.getCenterY());  
		setView(cx, cy, scale * getWidth() / zoomRect.getWidth());
		zoomRect = null;
	}
	
	public Painter getPainter() {
		return painter;
	}
	
	public void setZoomRect(Rectangle rect) {
		drawZoomRect();
		zoomRect = rect;
		drawZoomRect();
	}

	public void setPainter(Painter painter) {
		this.painter = painter;
	}

	public double getXcoord(double x) {
		return minX + (maxX - minX) / getWidth() * x;
	}
	
	public double getYcoord(double y) {
		return minY + (maxY - minY) / getHeight() * y;
	}
	
	public void setView(double minX, double minY, double maxX, double maxY) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		redraw();
	}
	
	public void setView(double centerX, double centerY, double scale) {
		this.scale = scale;
		minX = centerX - getWidth() / 2 / scale;
		minY = centerY - getHeight() / 2 / scale;
		maxX = centerX + getWidth() / 2 / scale;
		maxY = centerY + getHeight() / 2 / scale;
		redraw();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public double getScale() {
		return scale;
	}
}
