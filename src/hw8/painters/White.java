package hw8.painters;

import hw8.Painter;

/**
 * Very simple painter that paints the entire mandelbrot white.
 * Only the edges look interesting now.
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class White implements Painter {
	@Override
	public int[] getColor(int index) {
		return new int[] {255, 255, 255}; 
	}
}
