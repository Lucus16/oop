package hw4.players;

import utils.Range;
import hw4.Board;
import hw4.BoardInfo;
import hw4.Color;
import hw4.Player;
import hw4.botutils.Blacklist;
/**
 * Better AI can handle most traps. If it sees its opponent creating such a trap, it will counter it
 * and if it happens to be able to create a trap in a single move, it will do so. Otherwise
 * it will play randomly and favor the center.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class BetterAI extends Player {

	public BetterAI(String name, Color color) {
		super(name, color);
	}

	@Override
	public int getMove() {
		Blacklist master = new Blacklist(board.getWidth());
		Blacklist templist = new Blacklist(board.getWidth());
		for (int col : new Range(board.getWidth())) {
			master.setElem(col, Math.min(col + 1, board.getWidth() - col));
		}
		//Filter valid moves
		for (int col : new Range(board.getWidth())) {
			templist.setElem(col, board.validMove(col));
		}
		master.apply(templist);
		if (master.count() == 1) { return master.first(); }
		//If I can win in one move, filter all non-winning moves
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			templist.setElem(col, temp.hasEnded() && temp.winner() == color);
		}
		if (!templist.isEmpty()) { master.apply(templist); }
		if (master.count() == 1) { return master.first(); }
		//If the opponent can win in one move, filter all non-preventing moves
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color.other(), col);
			templist.setElem(col, temp.hasEnded() && temp.winner() == color.other());
		}
		if (!templist.isEmpty()) { master.apply(templist); }
		if (master.count() == 1) { return master.first(); }
		//If the opponent can win by making the same move I will make, filter all such moves.
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			temp.move(color.other(), col);
			templist.setElem(col, !(temp.hasEnded() && temp.winner() == color.other()));
		}
		if (!templist.isEmpty()) { master.apply(templist); }
		if (master.count() == 1) { return master.first(); }
		//If I can make a move which will give me two winning moves, filter all other moves.
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			templist.setElem(col, countWinOptions(temp, color) > 1 || hasStackTrap(temp, color));
		}
		if (!templist.isEmpty()) { master.apply(templist); }
		if (master.count() == 1) { return master.first(); }
		//Filter all moves that don't prevent the opponent from making a trap.
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			templist.setElem(col, !canTrap(temp, color.other()));
		}
		if (!templist.isEmpty()) { master.apply(templist); }
		return master.weightedRandom();
	}
	
	/**
	 * Check if color can make a trap, a trap is either a stack trap or a trap where
	 * color makes a move that gives him more than one option to win,
	 * which means the opponent can't block both of them.
	 * @param board
	 * @param color
	 * @return
	 */
	public boolean canTrap(BoardInfo board, Color color) {
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			if (countWinOptions(temp, color) > 1 || hasStackTrap(temp, color)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if color has a stack trap, that is a column where he can make a move and win,
	 * regardless of whether the opponent makes a move there.
	 * @param board
	 * @param color
	 * @return
	 */
	public boolean hasStackTrap(BoardInfo board, Color color) {
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			Board temp2 = new Board(board);
			temp2.move(color.other(), col);
			temp2.move(color, col);
			if (temp.hasEnded() && temp.winner() == color && temp2.hasEnded() && temp2.winner() == color) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Count the number of ways color can win in a single move.
	 * @param board
	 * @param color
	 * @return
	 */
	public int countWinOptions(BoardInfo board, Color color) {
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
