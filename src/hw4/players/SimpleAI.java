package hw4.players;

import java.util.Random;

import utils.Range;
import hw4.Board;
import hw4.BoardInfo;
import hw4.Color;
import hw4.Player;

/**
 * Simple AI will win when he can do so in a single move, and prevent loss if the opponent could otherwise win in a single move.
 * It can also counter a slightly more advanced tactic where its opponent threatens to make a move that would
 * give it two options to win, normally, only one option can be blocked, but simple AI can see this coming and prevent it.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
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
		//Random valid move
		col = -1;
		while (!board.validMove(col)) {
			col = gen.nextInt(board.getWidth());
		}
		return col;
	}
	
	/**
	 * Check if color can win the game in one move
	 * @param board
	 * @param color
	 * @return the move if it could be found, -1 otherwise
	 */
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
	
	/**
	 * Count the number of ways the game can be won in a single move.
	 * @param board
	 * @param color
	 * @return
	 */
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
