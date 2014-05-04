package hw8;

public class MandelView extends GridView {
	private static final long serialVersionUID = -6339535732137830801L;
	private int limit;
	private Painter painter;
	private double minX, minY, maxX, maxY, scale;
	
	public MandelView(int width, int height) {
		super(width, height);
		setLimit(256);
		painter = new ColorTable();
		setView(0, 0, 150);
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
		repaint();
	}
	
	public Painter getPainter() {
		return painter;
	}

	public void setPainter(Painter painter) {
		this.painter = painter;
	}

	public double getXcoord(int x) {
		return minX + (maxX - minX) / getWidth() * x;
	}
	
	public double getYcoord(int y) {
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
