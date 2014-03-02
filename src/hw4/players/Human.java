package hw4.players;

import utils.Input;
import hw4.Color;
import hw4.Player;

/**
 * Human communicates with a human via System.in and System.out to ask him what move he wants to make.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
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
