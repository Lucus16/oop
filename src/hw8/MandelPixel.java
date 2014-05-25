package hw8;

/**
 * MandelPixel keeps track of one single pixel and calculates with more and
 * more depth each time it is polled.
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class MandelPixel {
	private PixelManager pm;
	private int screenX, screenY;
	private double originalX, originalY;
	private double currentX, currentY;
	private int mandelNumber;
	private int currentViewVersion;
	
	/**
	 * Create a pixel for coordinates screenX, screenY, with pixel manager pm
	 * @param screenX
	 * @param screenY
	 * @param pm
	 */
	public MandelPixel(int screenX, int screenY, PixelManager pm) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.pm = pm;
		reset();
	}
	
	/**
	 * Reset the pixel, starting calculation again at 0
	 */
	public void reset() {
		originalX = currentX = pm.getXcoord(screenX);
		originalY = currentY = pm.getYcoord(screenY);
		mandelNumber = 0;
		currentViewVersion = pm.getViewVersion();
	}
	
	/**
	 * Increase the depth by one and get the mandelnumber, or -1 if the
	 * mandelnumber has not been reached yet
	 * @return
	 */
	public int getMandelNumber() {
		if (currentViewVersion != pm.getViewVersion()) {
			reset();
		}
		if (currentX * currentX + currentY * currentY > 4) {
			return mandelNumber;
		}
		double newX = currentX * currentX - currentY * currentY + originalX;
		double newY = 2 * currentX * currentY + originalY;
		currentX = newX;
		currentY = newY;
		mandelNumber += 1;
		return -1;
	}
}
