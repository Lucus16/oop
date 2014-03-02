package hw4.players;

import utils.Range;
import hw4.Board;
import hw4.BoardInfo;
import hw4.Color;
import hw4.Player;
import hw4.botutils.Blacklist;

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
		//If the opponent can make a move which gives him two winning moves, filter all moves that don't prevent this.
//		for (int col : new Range(board.getWidth())) {
//			Board temp = new Board(board); 
//			temp.move(color.other(), col);
//			Blacklist templist2 = new Blacklist(board.getWidth());
//			for (int col2 : new Range(board.getWidth())) {
//				Board temp2 = new Board(temp);
//				temp2.move(color, col2);
//				templist2.setElem(col2, temp2.hasEnded() && temp2.winner() == color.other());
//			}
//			if (templist2.count() > 1) {
//				
//			}
//			templist2.setElem(col, 2);
//			
//			templist.setElem(col, countWinOptions(board, color.other()) > 1);
//		}
		//Filter all moves that don't prevent the opponent from making a trap.
		for (int col : new Range(board.getWidth())) {
			Board temp = new Board(board);
			temp.move(color, col);
			templist.setElem(col, !canTrap(temp, color.other()));
		}
		if (!templist.isEmpty()) { master.apply(templist); }
		return master.weightedRandom();
	}
	
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
