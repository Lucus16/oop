package hw4.botutils;

import utils.Range;
import java.util.Random;

/**
 * 0 means blacklisted, 1 means available
 * @author lars
 *
 */
public class Blacklist {
	private int[] list;
	private Random generator;
	
	public Blacklist(int n) {
		generator = new Random(System.currentTimeMillis());
		list = new int[n];
		for (int i : new Range(list.length)) {
			list[i] = 1;
		}
	}
	
	public void apply(Blacklist other) {
		assert list.length == other.list.length;
		for (int i : new Range(list.length)) {
			list[i] *= other.list[i];
		}
	}
	
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
	
	public int getElem(int col) {
		return list[col];
	}
	
	public boolean isEmpty() {
		for (int x : list) {
			if (x != 0) {
				return false;
			}
		}
		return true;
	}
	
	public int sum() {
		int sum = 0;
		for (int x : list) {
			sum += x;
		}
		return sum;
	}
	
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int x : list) {
			sb.append(x);
			sb.append(' ');
		}
		return sb.toString();
	}
}
