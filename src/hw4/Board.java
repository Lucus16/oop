package hw4;

import utils.Range;

/**
 * Major gamestate bookkeeping class for Connect Four. Allows 
 * manipulation of the gamestate through the move(Color,int) function only. 
 * Specifically does not allow game resets. Supplies info about the gamestate 
 * through the BoardInfo interface.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class Board implements BoardInfo {
	private final int width, height;
	private Color[][] slots;
	private boolean hasEnded;
	private Color winner;
	private int lastMove;
	
	/**
	 * Create a new, empty board of standard size 7*8.
	 */
	public Board() {
		this.width = 7;
		this.height = 6;
		empty();
	}
	
	/**
	 * Create a new, empty board of given width and height.
	 * @param width
	 * @param height
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		empty();
	}
	
	/**
	 * Make a copy of another board.
	 * @param other board to be copied.
	 */
	public Board(BoardInfo other) {
		this.width = other.getWidth();
		this.height = other.getHeight();
		slots = new Color[width][height];
		for (int col : new Range(width)) {
			for (int row : new Range(height)) {
				slots[col][row] = other.getSlot(col, row);
			}
		}
		hasEnded = other.hasEnded();
		winner = other.winner();
	}
	
	/**
	 * Empty the board.
	 */
	private void empty() {
		slots = new Color[width][height];
		for (int col : new Range(width)) {
			for (int row : new Range(height)) {
				slots[col][row] = Color.NONE;
			}
		}
	}

	@Override
	public Color getSlot(int col, int row) {
		assert col >= 0;
		assert col < width;
		assert row >= 0;
		assert row < height;
		return slots[col][row];
	}

	@Override
	public boolean validMove(int col) {
		if (col < 0 || col >= width) {
			return false;
		}
		return slots[col][height - 1] == Color.NONE;
	}

	@Override
	public boolean validSlot(int col, int row) {
		return col >= 0 && col < width && row >= 0 && row < height;
	}
	
	/**
	 * If an invalid move is sent, for example in a column that is full,
	 * the moving player loses. The validity of a move can be checked with 
	 * validMove() beforehand.
	 * @param color The color that makes the move
	 * @param col The column where the move is made
	 * @return whether the move could be made.
	 */
	public boolean move(Color color, int col) {
		assert col >= 0;
		assert col < width;
		if (hasEnded) {
			return false;
		}
		for (int row = 0; row < height; row++) {
			if (slots[col][row] == Color.NONE) {
				slots[col][row] = color;
				checkEnd(col, row);
				lastMove = col;
				return true;
			}
		}
		hasEnded = true;
		winner = color.other();
		return false;
	}
	
	/**
	 * @return if the board is completely filled with pieces.
	 */
	public boolean full() {
		boolean full = true;
		for (int col = 0; col < width; col++) {
			if (slots[col][height - 1] == Color.NONE) {
				full = false;
			}
		}
		return full;
	}
	
	/**
	 * Check whether the game has ended.
	 * @param col the column of the last played piece.
	 * @param row the row of the last played piece.
	 */
	public void checkEnd(int col, int row) {
		if (checkFour(col, row)) {
			hasEnded = true;
			winner = slots[col][row];
			return;
		}
		if (full()) {
			hasEnded = true;
			winner = Color.NONE;
		}
	}
	
	/**
	 * Check whether there's four in a row going through coordinates (x,y)
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkFour(int x, int y) {
		return  checkFour(x, y, 0, 1) ||
				checkFour(x, y, 1, 1) || 
				checkFour(x, y, 1, 0) ||
				checkFour(x, y, 1, -1);
	}
	
	/**
	 * Check whether there's four in a row going through coordinates (x,y) in 
	 * specific direction (dx,dy)
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @return
	 */
	public boolean checkFour(int x, int y, int dx, int dy) {
		int count = 0;
		Color c = slots[x][y]; 
		for (int i : new Range(-3, 4)) {
			int curx = x + dx * i;
			int cury = y + dy * i;
			if (validSlot(curx, cury)) {
				if (slots[curx][cury] == c) {
					count++;
					if (count >= 4) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean hasEnded() {
		return hasEnded;
	}

	@Override
	public Color winner() {
		return winner;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row : new Range(height - 1, -1, -1)) {
			sb.append('\n');
			for (int col : new Range(width)) {
				sb.append("[" + slots[col][row].toChar() + "]");
			}
		}
		sb.append('\n');
		for (int i : new Range(width)) {
			sb.append((i == lastMove ? ">" + (i + 1) + "<" : " " + (i + 1) + " "));
		}
		return sb.toString();
	}

	@Override
	public int lastMove() {
		return lastMove;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
}
