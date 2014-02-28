package hw4;

/**
 * Read-only board wrapper
 * @author lars
 *
 */
public class BoardRO {
	private final Board board;
	
	public BoardRO(Board board) {
		this.board = board;
	}
	
	public Color getSlot(int col, int row) {
		return board.getSlot(col, row);
	}
	
	public boolean validMove(int col) {
		return board.validMove(col);
	}
	
	public boolean hasEnded() {
		return board.hasEnded();
	}
	
	public Color winner() {
		return board.winner();
	}
}
