package hw8;

public class MandelView {
	private int limit;
	private ColorTable colorTable;
	private GridView gridView;
	private double x, y, width, height;
	
	public MandelView() {
		setLimit(1024);
		colorTable = new ColorTable(2);
		colorTable.setColor(0, ColorTable.BLACK);
		colorTable.setColor(1, ColorTable.WHITE);
	}
	
	public void redraw() {
		for (int x = 0; x < gridView.getWidth(); x++) {
			for (int y = 0; y < gridView.getHeight(); y++) {
				gridView.setPixel(x, y, colorTable.getColor(
						Mandelbrot.mandelnumber(a, b, limit)));
			}
		}
		gridView.repaint();
	}
	
	public void setView(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
