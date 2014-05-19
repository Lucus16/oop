package hw8;

public class MandelPixel extends Mandelbrot {
	private PixelManager pm;
	private int screenX, screenY;
	private double originalX, originalY;
	private double currentX, currentY;
	private int mandelNumber;
	private int currentViewVersion;
	
	public MandelPixel(int screenX, int screenY, PixelManager pm) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.pm = pm;
		reset();
	}
	
	public void reset() {
		originalX = currentX = pm.getXcoord(screenX);
		originalY = currentY = pm.getYcoord(screenY);
		mandelNumber = 0;
		currentViewVersion = pm.getViewVersion();
	}
	
	public int getMandelNumber() {
		if (currentViewVersion != pm.getViewVersion()) {
			reset();
		}
		if (currentX * currentX + currentY * currentY <= 4) {
			return mandelNumber;
		}
		double newX = currentX * currentX - currentY * currentY + originalX;
		double newY = 2 * currentX * currentY + originalY;
		currentX = newX;
		currentY = newY;
		mandelNumber += 1;
//		if (currentX * currentX + currentY * currentY <= 4) {
//			return mandelNumber;
//		}
		return -1;
	}
}
