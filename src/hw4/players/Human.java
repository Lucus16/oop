package hw4.players;

import hw4.BoardInfo;
import hw4.Color;
import hw4.Player;

public class Human implements Player {
	private BoardInfo board;
	private Color color;

	@Override
	public void initGame(BoardInfo board, Color color) {
		this.board = board;
		this.color = color;
	}

	@Override
	public int getMove() {
		System.out.print(board);
		return 0;
	}

	@Override
	public void update() {
	}

}
