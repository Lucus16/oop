package hw8;

/**
 * Worker thread that continuously iterates over all the pixels in its domain
 * and redraws them, each time with a slightly higher limit
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class MandelWorker extends Thread {
	public int minY, maxY;
	public MandelPixel[][] pixels;
	public MandelView mv;
	
	/**
	 * Create a new MandelWorker that works from minY to maxY and redraws on
	 * MandelView mv.
	 * @param minY
	 * @param maxY
	 * @param mv
	 */
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
				sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}
}
