package hw4.botutils;

import utils.Range;
import java.util.Random;

/**
 * Keeps track of which moves are still open and which moves should definitely not be played.
 * 0 means blacklisted, 1 or higher means available
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class Blacklist {
	private int[] list;
	private Random generator;
	
	/**
	 * Create a new blacklist of size n
	 * @param n size
	 */
	public Blacklist(int n) {
		generator = new Random(System.currentTimeMillis());
		list = new int[n];
		for (int i : new Range(list.length)) {
			list[i] = 1;
		}
	}
	
	/**
	 * Apply another blacklist to this one by multiplication.
	 * This means if a move was blacklisted in either list, it is now blacklisted in this list.
	 * @param other
	 */
	public void apply(Blacklist other) {
		assert list.length == other.list.length;
		for (int i : new Range(list.length)) {
			list[i] *= other.list[i];
		}
	}
	
	/**
	 * Set an element of the blacklist to a specific value
	 * @param col
	 * @param val
	 */
	public void setElem(int col, int val) {
		list[col] = val;
	}
	
	/**
	 * Set element at position col to 1 if the condition is true, 0 otherwise
	 * @param col
	 * @param cond
	 */
	public void setElem(int col, boolean cond) {
		list[col] = (cond ? 1 : 0);
	}
	
	/**
	 * Get an element from the blacklist
	 * @param col
	 * @return
	 */
	public int getElem(int col) {
		return list[col];
	}
	
	/**
	 * Return if all elements are 0. This means no possible moves are left.
	 * @return
	 */
	public boolean isEmpty() {
		for (int x : list) {
			if (x != 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Return the sum of all elements.
	 * @return
	 */
	public int sum() {
		int sum = 0;
		for (int x : list) {
			sum += x;
		}
		return sum;
	}
	
	/**
	 * Return the number of non-zero elements.
	 * @return
	 */
	public int count() {
		int count = 0;
		for (int x : list) {
			if (x != 0) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * @return first non-zero item
	 */
	public int first() {
		for (int i : new Range(list.length)) {
			if (list[i] != 0) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Return a random index. The chance an index is chosen depends on the value for that index divided by the sum of all values.
	 * @return
	 */
	public int weightedRandom() {
		int choice = generator.nextInt(sum());
		int accum = 0;
		for (int i : new Range(list.length)) {
			accum += list[i];
			if (accum > choice) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Represent the list as a simple string.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int x : list) {
			sb.append(x);
			sb.append(' ');
		}
		return sb.toString();
	}
}
