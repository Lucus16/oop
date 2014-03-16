package hw6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import utils.Range;

/**
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.1
 * @date 28-02-2013 A template implementation of a sliding game also
 *       implementing the Graph interface
 */
public class SlidingGame implements Graph {
	public static final int N = 3, SIZE = N * N, HOLE = SIZE;
	public static final SlidingGame goal = new SlidingGame(new Range(1,N*N+1));
	/**
	 * The bord is represented by a 2-dimensional arrray; the position of the
	 * hole is kept in 2 variables holeX and holeY
	 */
	private int board[][];
	private int holeX, holeY;
	private int incorrectness;

	/**
	 * A constructor that initializes the board with the specified array
	 * 
	 * @param start
	 *            : a one dimensional array containing the intial board. The
	 *            elements of start are stored row-wise.
	 */
	public SlidingGame(int[] start) {
		board = new int[N][N];
		assert start.length == N * N : "Length of specified board incorrect";
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				board[row][col] = start[row * N + col];
				if (board[row][col] == HOLE) {
					holeX = col;
					holeY = row;
				}
			}
		}
		incorrectness = manhattanToGoal();
	}
	
	public SlidingGame(Iterable<Integer> flatConfig) {
		board = new int[N][N];
		Iterator<Integer> walker = flatConfig.iterator();
		for (int i : new Range(N)) {
			for (int j : new Range(N)) {
				assert walker.hasNext() : "Specified board too short.";
				board[i][j] = walker.next();
				if (board[i][j] == HOLE) {
					holeX = j;
					holeY = i;
				}
			}
		}
		assert !walker.hasNext() : "Specified board too long.";
		incorrectness = manhattanToGoal();
	}
	
	
	
	private int manhattanToGoal() {
		int accu=0;
		int fieldDistance;
		for(int i : new Range(N)){
			for(int j : new Range(N)){
				if(board[i][j] == HOLE) break;
				fieldDistance = 0;
				fieldDistance = Math.abs((board[i][j]-1 / N) - i);
				//TODO: complete and verify math
			}
		}
		return accu;
	}

	/**
	 * Converts a bord into a printable representation. The hole is displayed as
	 * a space
	 * 
	 * @return the string representation
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int puzzel = board[row][col];
				buf.append(puzzel == HOLE ? "  " : puzzel + " ");
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	/**
	 * a standard implementation of equals checking whether 2 boards are filled
	 * in exactly the same way
	 * 
	 * @return a boolean indicating equality
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass()) {
			return false;
		} else {
			SlidingGame other_puzzle = (SlidingGame) o;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (board[row][col] != other_puzzle.board[row][col]) {
						return false;
					}
				}
			}
			return true;
		}
	}

	@Override
	public boolean isGoal() {
		return incorrectness == 0;
	}

	@Override
	public Collection<Graph> successors() {
		throw new UnsupportedOperationException(
				"successors : not supported yet.");
	}

	@Override
	public int compareTo(Graph g) {
		return Integer.compare(this.distToGoal(), g.distToGoal());
	}

	@Override
	public int distToGoal() {
		return incorrectness;
	}

}
