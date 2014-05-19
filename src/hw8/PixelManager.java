package hw8;

public interface PixelManager {
	/**
	 * Get the mandelbrot X coordinate from the screen X coordinate 
	 * @param x
	 * @return
	 */
	public double getXcoord(int screenX);
	
	/**
	 * Get the mandelbrot Y coordinate from the screen Y coordinate
	 * @param screenY
	 * @return
	 */
	public double getYcoord(int screenY);
	
	/**
	 * Get the view version. This increases by one every time the view changes
	 * and is used to check whether new coordinates need to be calculated
	 * @return
	 */
	public int getViewVersion();
}
