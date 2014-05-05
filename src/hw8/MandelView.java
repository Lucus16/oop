package hw8;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * MandelView handles displaying the portion of the mandelbrot fractal
 * we're currently looking at
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class MandelView extends GridView {
	private static final long serialVersionUID = -6339535732137830801L;
	private int limit;
	private Painter painter;
	private double minX, minY, maxX, maxY, scale;
	private Rectangle zoomRect;
	private RedrawListener redrawListener;
	
	/**
	 * Initialize the mandelview
	 * @param width
	 * @param height
	 * @param painter
	 */
	public MandelView(int width, int height, Painter painter) {
		super(width, height);
		this.painter = painter;
		setZoomRect(null);
		setLimit(256);
		resetView();
	}
	
	/**
	 * Initialize the mandelview with a default rainbow color table
	 * @param width
	 * @param height
	 */
	public MandelView(int width, int height) {
		this(width, height, new ColorTable());
	}
	
	/**
	 * Reset the mandelview to showing the entire mandelbrot set
	 */
	public void resetView() {
		setView(0, 0, Math.min(getWidth(), getHeight()) / 4);
	}
	
	/**
	 * Add a listener to update other stuff when the screen is redrawn
	 * @param redrawListener
	 */
	public void addRedrawListener(RedrawListener redrawListener) {
		this.redrawListener = redrawListener;
	}
	
	/**
	 * Redraw the screen pixel by pixel
	 */
	public void redraw() {
		if (redrawListener != null) {
			redrawListener.redrawn((minX + maxX) / 2, (minY + maxY) / 2, scale, limit);
		}
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
	
	/**
	 * Draw the zoom rectangle
	 */
	public void drawZoomRect() {
		if (zoomRect != null) {
			Graphics g = getGraphics();
			g.setXORMode(Color.WHITE);
			g.drawRect(zoomRect.x, zoomRect.y, zoomRect.width, zoomRect.height);
		}
	}
	
	/**
	 * Zoom in to the zoom rectangle
	 */
	public void doZoom() {
		double cx = getXcoord(zoomRect.getCenterX());
		double cy = getYcoord(zoomRect.getCenterY());  
		setView(cx, cy, scale * getWidth() / zoomRect.getWidth());
		zoomRect = null;
	}
	
	/**
	 * Get the painter that is in use
	 * @return the painter
	 */
	public Painter getPainter() {
		return painter;
	}
	
	/**
	 * Set the zoom rectangle and redraw it.
	 * @param rect
	 */
	public void setZoomRect(Rectangle rect) {
		drawZoomRect();
		zoomRect = rect;
		drawZoomRect();
	}
	
	/**
	 * Set the painter
	 * @param painter
	 */
	public void setPainter(Painter painter) {
		this.painter = painter;
	}

	/**
	 * Get a mandelbrot X coordinate from a screen X coordinate
	 * @param x
	 * @return
	 */
	public double getXcoord(double x) {
		return minX + (maxX - minX) / getWidth() * x;
	}

	/**
	 * Get a mandelbrot Y coordinate from a screen Y coordinate
	 * @param y
	 * @return
	 */
	public double getYcoord(double y) {
		return minY + (maxY - minY) / getHeight() * y;
	}
	
	/**
	 * Set the view and redraw it
	 * @param centerX
	 * @param centerY
	 * @param scale
	 */
	public void setView(double centerX, double centerY, double scale) {
		this.scale = scale;
		minX = centerX - getWidth() / 2 / scale;
		minY = centerY - getHeight() / 2 / scale;
		maxX = centerX + getWidth() / 2 / scale;
		maxY = centerY + getHeight() / 2 / scale;
		redraw();
	}

	/**
	 * Get the number of steps the mandelbrot calculation will go at most
	 * @return
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Set the number of steps the mandelbrot calculation will go at most
	 * @param limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * Get the scale
	 * @return
	 */
	public double getScale() {
		return scale;
	}
}
