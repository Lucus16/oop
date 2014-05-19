package hw8;

import hw8.painters.ColorTable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * MandelView handles displaying the portion of the mandelbrot fractal
 * we're currently looking at
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class MandelView extends GridView implements PixelManager {
	private static final long serialVersionUID = -6339535732137830801L;
	private static final int NUM_WORKERS = 8;
	private Painter painter;
	private double minX, minY, maxX, maxY, scale;
	private Rectangle zoomRect;
	private ViewChangeListener viewChangeListener;
	private int viewVersion;
	private MandelPixel[][] pixels;
	private MandelWorker[] workers;
	
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
		viewVersion = 0;
		resetView();
		pixels = new MandelPixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixels[x][y] = new MandelPixel(x, y, this);
			}
		}
		workers = new MandelWorker[NUM_WORKERS];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new MandelWorker(this.getHeight() * i / NUM_WORKERS,
					this.getHeight() * (i + 1) / NUM_WORKERS, this);
			workers[i].start();
		}
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
	public void addRedrawListener(ViewChangeListener redrawListener) {
		this.viewChangeListener = redrawListener;
	}
	
	/**
	 * Redraw the screen
	 */
	public void redraw() {
		repaint();
		drawZoomRect();
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
	public double getXcoord(double screenX) {
		return minX + (maxX - minX) / getWidth() * screenX;
	}
	
	public double getXcoord(int screenX) {
		return getXcoord((double)screenX);
	}

	/**
	 * Get a mandelbrot Y coordinate from a screen Y coordinate
	 * @param y
	 * @return
	 */
	public double getYcoord(double screenY) {
		return minY + (maxY - minY) / getHeight() * screenY;
	}

	public double getYcoord(int screenY) {
		return getYcoord((double)screenY);
	}
	
	/**
	 * Set the view and redraw it
	 * @param centerX
	 * @param centerY
	 * @param scale
	 */
	public void setView(double centerX, double centerY, double scale) {
		if (viewChangeListener != null) {
			viewChangeListener.viewChanged((minX + maxX) / 2, (minY + maxY) / 2, scale);
		}
		this.scale = scale;
		minX = centerX - getWidth() / 2 / scale;
		minY = centerY - getHeight() / 2 / scale;
		maxX = centerX + getWidth() / 2 / scale;
		maxY = centerY + getHeight() / 2 / scale;
		viewVersion += 1;
		clear();
		redraw();
	}

	/**
	 * Get the scale
	 * @return
	 */
	public double getScale() {
		return scale;
	}

	@Override
	public int getViewVersion() {
		return viewVersion;
	}

	public MandelPixel[][] getPixels() {
		return pixels;
	}
}
