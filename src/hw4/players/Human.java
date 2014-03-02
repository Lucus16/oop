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
		System.out.println("Please enter the column you wish to play in.");
		int col;
		while (true) {
			col = Input.getChoice(7) - 1;
			if (board.validMove(col)) {
				return col;
			}
			System.out.println("That column is full.");
		}
	}
}
