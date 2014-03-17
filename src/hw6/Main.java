package hw6;

import java.util.List;

/**
 * 
 * @author Pieter Koopman, Sjaak Smetsers
 */
public class Main {

	public static void main(String[] args) {
		int[] game = { 1, 2, 3, 4, 9, 6, 7, 5, 8 };

		SlidingGame s = new SlidingGame(game);
		System.out.println(s);
		System.out.println(SlidingGame.goal);
		System.out.println(SlidingGame.goal.distToGoal());
		Solver solver = new Solver(s);
		System.out.println(solver.solve());
	}
}
