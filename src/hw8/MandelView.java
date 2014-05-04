package hw8;

public class MandelView {
	private int limit;
	private ColorTable colorTable;
	private GridView gridView;
	private double minX, minY, maxX, maxY;
	
	public MandelView(GridView gridView) {
		setLimit(1024);
		this.gridView = gridView;
		colorTable = new ColorTable(2);
		colorTable.setColor(0, ColorTable.BLACK);
		colorTable.setColor(1, ColorTable.WHITE);
		minX = -2;
		minY = -2;
		maxX = 2;
		maxY = 2;
	}
	
	public void redraw() {
		for (int x = 0; x < gridView.getWidth(); x++) {
			for (int y = 0; y < gridView.getHeight(); y++) {
				int mandelNum = Mandelbrot.mandelnumber(getXcoord(x), getYcoord(y), limit);
				if (mandelNum == -1) {
					gridView.setPixel(x, y, ColorTable.BLACK);
				} else {
					gridView.setPixel(x, y, colorTable.getColor(mandelNum));
					
				}
			}
		}
		gridView.repaint();
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

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public ColorTable getColorTable() {
		return colorTable;
	}

	public void setColorTable(ColorTable colorTable) {
		this.colorTable = colorTable;
	}
}
