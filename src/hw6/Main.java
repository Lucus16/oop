package hw6;

import java.util.List;

/**
 * Main class.
 * @author Pieter Koopman, Sjaak Smetsers
 */
public class Main {
	/**
	 * Test the sliding game solver by solving a random puzzle
	 * @param args
	 */
	public static void main(String[] args) {
		int[] game = { 4, 7, 6, 3, 8, 2, 9, 1, 5 };

		SlidingGame s = new SlidingGame(game);
		System.out.println(s);
		System.out.println(SlidingGame.goal);
		System.out.println(SlidingGame.goal.distToGoal());
		Solver solver = new Solver(s);
		System.out.println(solver.solve());
	}
}
