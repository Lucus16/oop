package hw4;

/**
 * Immutable interface for the Board class in the hw4 (Connect Four) package.
 * Supplies info about the gamestate: raw data (no grid), a standard 
 * representation and some utilities for AI and printing.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public interface BoardInfo {
	/**
	 * Get the color of a slot on the board.
	 * @param col
	 * @param row
	 * @return the color, Color.NONE if it is unoccupied.
	 */
	public Color getSlot(int col, int row);
	
	/**
	 * @return whether the game has ended
	 */
	public boolean hasEnded();
	
	/**
	 * @return the color of the winner, or Color.NONE in case of a draw.
	 */
	public Color winner();
	
	/**
	 * Return whether making a move in column col is valid, that is, the column 
	 * number is valid and that column is not full.
	 * @param col
	 * @return whether the move is valid
	 */
	public boolean validMove(int col);
	
	/**
	 * Return whether col, row is a valid slot, that is, whether the column 
	 * number and the row number are in range of the board.
	 * @param col
	 * @param row
	 * @return
	 */
	public boolean validSlot(int col, int row);
	
	/**
	 * Return a readable string representation of the board in height + 1 lines
	 * @return
	 */
	public String toString();
	
	/**
	 * Return the column number of the most recent move.
	 * @return
	 */
	public int lastMove();
	
	/**
	 * Return the width of the board.
	 * @return
	 */
	public int getWidth();
	
	/**
	 * Return the height of the board.
	 * @return
	 */
	public int getHeight();
}
