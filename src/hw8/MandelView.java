package hw8;

public class MandelView {
	private int limit;
	private Painter painter;
	private GridView gridView;
	private double minX, minY, maxX, maxY, scale;
	
	public MandelView(GridView gridView) {
		setLimit(256);
		this.gridView = gridView;
		painter = new ColorTable();
		setView(0, 0, 150);
	}
	
	public void redraw() {
		for (int x = 0; x < gridView.getWidth(); x++) {
			for (int y = 0; y < gridView.getHeight(); y++) {
				int mandelNum = Mandelbrot.mandelnumber(getXcoord(x), getYcoord(y), limit);
				if (mandelNum == -1) {
					gridView.setPixel(x, y, Painter.BLACK);
				} else {
					gridView.setPixel(x, y, painter.getColor(mandelNum));
				}
			}
		}
		gridView.repaint();
	}
	
	public Painter getPainter() {
		return painter;
	}

	public void setPainter(Painter painter) {
		this.painter = painter;
	}

	public double getXcoord(int x) {
		return minX + (maxX - minX) / gridView.getWidth() * x;
	}
	
	public double getYcoord(int y) {
		return minY + (maxY - minY) / gridView.getHeight() * y;
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
		minX = centerX - gridView.getWidth() / 2 / scale;
		minY = centerY - gridView.getHeight() / 2 / scale;
		maxX = centerX + gridView.getWidth() / 2 / scale;
		maxY = centerY + gridView.getHeight() / 2 / scale;
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
