package utils;

import java.util.Iterator;

/**
 * Range implements a simple way of looping over a range of integers.
 * It begins at start and then increments with step every time until it reaches end, not including end.
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class Range implements Iterable<Integer> {
	private class RangeIterator implements Iterator<Integer> {
		private int current;
		private int end;
		private int step;
		private boolean sign;
		
		/**
		 * Create a new range iterator.
		 * @param start
		 * @param end
		 * @param step
		 */
		public RangeIterator(int start, int end, int step) {
			current = start;
			this.end = end;
			this.step = step;
			sign = step < 0;
		}

		@Override
		public boolean hasNext() {
			return (sign ? current > end : current < end);
		}

		@Override
		public Integer next() {
			int tmp = current;
			current += step;
			return tmp;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private int start;
	private int end;
	private int step;

	/**
	 * Create a new range starting at 0, ending at end and with step 1
	 * @param end
	 */
	public Range(int end) {
		this(0, end, 1);
	}
	
	/**
	 * Create a new range starting at start, ending at end and with step 1
	 * @param start
	 * @param end
	 */
	public Range(int start, int end) {
		this(start, end, 1);
	}
	
	/**
	 * Create a new range starting at start, ending at end and with step step.
	 * @param start
	 * @param end
	 * @param step
	 */
	public Range(int start, int end, int step) {
		if (step == 0) {
			throw new IllegalArgumentException("step must not be zero");
		}
		this.start = start;
		this.end = end;
		this.step = step;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator(start, end, step);
	}
}
