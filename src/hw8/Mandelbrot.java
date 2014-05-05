package hw8;

/**
 * Mandelbrot class calculates mandel numbers for given coordinates
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class Mandelbrot {
	/**
	 * Calculate the mandelbrot number for coordinates (a, b)
	 * If it's higher than limit, return -1
	 * @param a
	 * @param b
	 * @param limit
	 * @return the mandelnumber or -1 for infinity;
	 */
	public static int mandelnumber(double a, double b, int limit) {
		double x = a, y = b;
		int n = 0;
		while (x * x + y * y <= 4) {
			if (n == limit) {
				return -1;
			}
			n += 1;
			double nx = x * x - y * y + a;
			double ny = 2 * x * y + b;
			x = nx;
			y = ny;
		}
		return n;
	}
	
	/**
	 * Calculate mandelbrot number with default limit of 100
	 * @param a
	 * @param b
	 * @return
	 */
	public static int mandelnumber(double a, double b) {
		return mandelnumber(a, b, 100);
	}
}
