package hw8;

public class MandelWorker extends Thread {
	public int minY, maxY;
	public MandelPixel[][] pixels;
	public MandelView mv;
	
	public MandelWorker(int minY, int maxY, MandelView mv) {
		this.minY = minY;
		this.maxY = maxY;
		this.mv = mv;
		this.pixels = mv.getPixels();
	}
	
	@Override
	public void run() {
		while (true) {
			for (int y = minY; y < maxY; y++) {
				for (int x = 0; x < pixels.length; x++) {
					int mandelNumber = pixels[x][y].getMandelNumber();
					if (mandelNumber >= 0) {
						mv.setPixel(x, y, mv.getPainter().getColor(mandelNumber));
					}
				}
			}
			mv.redraw();
			try {
				sleep(20);
			} catch (InterruptedException e) {
			}
		}
	}
}
