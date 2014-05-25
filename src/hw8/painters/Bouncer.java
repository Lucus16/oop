package hw8.painters;

import java.util.Random;

import hw8.Painter;

/**
 * Painter that lets the red, green and blue channels bounce up and down
 * independently and at different rates.
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class Bouncer implements Painter {
	private int deltaR;
	private int deltaG;
	private int deltaB;
	private static final int[] primes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
			37, 41, 43, 47, 53};
	
	/**
	 * Create a new bouncer with random rates.
	 */
	public Bouncer() {
		this(System.currentTimeMillis());
	}
	
	/**
	 * Create a new bouncer with rates based on a seed.
	 * @param seed
	 */
	public Bouncer(long seed) {
		Random r = new Random(seed);
		deltaR = deltaG = deltaB = primes[r.nextInt(primes.length)];
		while (deltaG == deltaR) {
			deltaG = primes[r.nextInt(primes.length)];
		}
		while (deltaB == deltaR || deltaB == deltaG) {
			deltaB = primes[r.nextInt(primes.length)];
		}
	}

	@Override
	public int[] getColor(int index) {
		int red = (deltaR * index) % 512;
		int green = (deltaG * index) % 512;
		int blue = (deltaB * index) % 512;
		red = (red < 256 ? red : 511 - red);
		green = (green < 256 ? green : 511 - green);
		blue = (blue < 256 ? blue : 511 - blue);
		return new int[] {red, green, blue};
	}

}
