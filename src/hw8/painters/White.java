package hw8.painters;

import hw8.Painter;

public class White implements Painter {
	@Override
	public int[] getColor(int index) {
		return new int[] {255, 255, 255}; 
	}
}
