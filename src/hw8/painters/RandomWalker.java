package hw8.painters;

import java.util.ArrayList;
import java.util.Random;

import hw8.Painter;

public class RandomWalker implements Painter {
	private ArrayList<int[]> colors;
	private Random gen;
	private int change;
	
	public RandomWalker(long seed, int change) {
		gen = new Random(seed);
		this.change = change;
		colors = new ArrayList<int[]>();
		colors.add(new int[] {gen.nextInt(256), gen.nextInt(256),
				gen.nextInt(256)});
	}
	
	public RandomWalker() {
		this(System.currentTimeMillis(), 32);
	}
	
	private void generate(int index) {
		while (colors.size() <= index) {
			int lastR = colors.get(colors.size() - 1)[0];
			int lastG = colors.get(colors.size() - 1)[1];
			int lastB = colors.get(colors.size() - 1)[2];
			colors.add(new int[] {
					Math.max(0, Math.min(255, lastR + gen.nextInt(2 * change + 1) - change)),
					Math.max(0, Math.min(255, lastG + gen.nextInt(2 * change + 1) - change)),
					Math.max(0, Math.min(255, lastB + gen.nextInt(2 * change + 1) - change)),
			});
		}
	}

	@Override
	public int[] getColor(int index) {
		if (index >= colors.size()) {
			generate(index);
		}
		return colors.get(index);
	}

}
