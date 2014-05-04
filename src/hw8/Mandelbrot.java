package hw8;

public class Mandelbrot {
	/**
	 * 
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
	
	public static int mandelnumber(double a, double b) {
		return mandelnumber(a, b, 100);
	}
}
