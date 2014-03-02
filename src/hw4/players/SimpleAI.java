package hw4.players;

import java.util.Random;

import utils.Range;
import hw4.Board;
import hw4.BoardInfo;
import hw4.Color;
import hw4.Player;

public class SimpleAI extends Player {
	Random gen;
	
	public SimpleAI(String name, Color color) {
		super(name, color);
		gen = new Random();
	}

	@Override
	public int getMove() {
		//Can we win immediately?
		int col = canWinInOne(board, color);
		if (col >= 0) {
			return col;
		}
		//Do we need to prevent immediate loss?
		col = canWinInOne(board, color.other());
		if (col >= 0) {
			return col;
		}
		//Countering advanced tactic 1
		for (int col2 : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color.other(), col2);
			if (countWaysToWinInOne(temp, color.other()) > 1) {
				return col2;
			}
		}
		//Random move
		col = -1;
		while (!board.validMove(col)) {
			col = gen.nextInt(board.getWidth());
		}
		return col;
	}
	
	public int canWinInOne(BoardInfo board, Color color) {
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			if (temp.hasEnded() && temp.winner() == color) {
				return col;
			}
		}
		return -1;
	}
	
	public int countWaysToWinInOne(BoardInfo board, Color color) {
		int count = 0;
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			if (temp.hasEnded() && temp.winner() == color) {
				count++;
			}
		}
		return count;
	}
}
