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
	/**
	 * enum of the Cardinal directions, with offsets
	 * @author Sal
	 *
	 */
	public enum Direction {
		N(0,-1), E(1,0), S(0,1), W(-1,0);
		
		private final int dx;
		private final int dy;
		
		Direction(int dx,int dy){
			this.dx=dx;
			this.dy=dy;
		}
	}
	public static final int N = 3, SIZE = N * N, HOLE = SIZE;
	public static final SlidingGame goal = new SlidingGame(new Range(1,N*N+1));
	/**
	 * The bord is represented by a 2-dimensional arrray; the position of the
	 * hole is kept in 2 variables holeX and holeY
	 */
	private int board[][];
	private int holeX, holeY;
	private int incorrectness;
	private int hash;

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
		calculateHash();
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
		calculateHash();
	}
	
	public SlidingGame(SlidingGame original){
		board = new int[N][N];
		copy(original);
	}
	
	public SlidingGame(SlidingGame original, Direction d){
		board = new int[N][N];
		copy(original);
		move(d);
		calculateHash();
	}
	
	private void copy(SlidingGame original){
		for(int i : new Range(N)){
			for(int j : new Range(N)){
				board[i][j]=original.board[i][j];
			}
		}
		holeX = original.holeX;
		holeY = original.holeY;
		incorrectness = original.incorrectness;
		hash = original.hash;
	}
	
	private int manhattanToGoal() {
		int accu=0;
		int tileDistance;
		for(int i : new Range(N)){
			for(int j : new Range(N)){
				tileDistance = 0;
				if(board[i][j] == HOLE) continue;
				tileDistance = Math.abs(((board[i][j]-1) / N) - i);
				tileDistance += Math.abs(((board[i][j]-1) % N) - j);
				accu+=tileDistance;
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
		Collection<Graph> accu = new ArrayList<Graph>();
		for (Direction d : Direction.values()){
			if (canMove(d)){
				accu.add(new SlidingGame(this,d));
			}
		}
		return accu;
	}
	
	public boolean canMove(Direction d){
		return holeX+d.dx >= 0 && holeX+d.dx < N && 
				holeY+d.dy >= 0 && holeY+d.dy < N;
	}
	
	private void move(Direction d){
		if(!canMove(d)) throw new IllegalArgumentException();
		board[holeY][holeX] = board[holeY+d.dy][holeX+d.dx];
		board[holeY+d.dy][holeX+d.dx] = HOLE;
		incorrectness = manhattanToGoal();
	}
	
	@Override
	public int compareTo(Graph g) {
		return this.distToGoal() - g.distToGoal();
	}

	@Override
	public int distToGoal() {
		return incorrectness;
	}
	
	@Override
	public int hashCode() {
		return hash;
	}
	
	private void calculateHash() {
		hash = 0;
		for (int x : new Range(N)) {
			for (int y : new Range(N)) {
				hash += board[y][x] * (y * N + x);
			}
		}
	}
}
