package hw4.players;

import io.Input;
import hw4.BoardInfo;
import hw4.Color;
import hw4.Player;

public class Human implements Player {
	private BoardInfo board;
	private Color color;
	private String name;
	
	public Human(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	@Override
	public void initGame(BoardInfo board) {
		this.board = board;
	}

	@Override
	public int getMove() {
		System.out.println(board);
		System.out.println(color + "'s turn.");
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
			if (board.winner() == Color.NONE) {
				System.out.println("The game has ended in a draw.");
			} else {
				System.out.println(board.winner() + " has won! Congratulations!");
			}
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Color getColor() {
		return color;
	}

}
