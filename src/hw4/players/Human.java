package hw4.players;

import utils.Input;
import hw4.Color;
import hw4.Player;

public class Human extends Player {
	public Human(String name, Color color) {
		super(name, color);
	}

	@Override
	public int getMove() {
		System.out.println(board);
		System.out.println(name + "'s turn.");
		System.out.println("Please enter the column you wish to play in.");
		int col = -1;
		while (true) {
			col = Input.getChoice(7) - 1;
			if (board.validMove(col)) {
				return col;
			}
			System.out.println("That column is full.");
		}
	}

	@Override
	public void update() {
		if (board.hasEnded()) {
			System.out.println(board);
			if (board.winner() == Color.NONE) {
				System.out.println("The game has ended in a draw.");
			} else {
				System.out.println(board.winner() + " has won! Congratulations!");
			}
		}
	}
}
