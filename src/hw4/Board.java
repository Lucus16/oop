package hw4;

import range.Range;

public class Board implements BoardInfo, Cloneable {
	public static final int WIDTH = 7, HEIGHT = 6;
	private Color[][] slots;
	private boolean hasEnded;
	private Color winner;
	private int lastMove;
	
	public Board() {
		slots = new Color[WIDTH][HEIGHT];
	}
	
	/**
	 * Make a copy of another board.
	 */
	public Board(BoardInfo other) {
		slots = new Color[WIDTH][HEIGHT];
		for (int col : new Range(WIDTH)) {
			for (int row : new Range(HEIGHT)) {
				slots[col][row] = other.getSlot(col, row);
			}
		}
		hasEnded = other.hasEnded();
		winner = other.winner();
	}

	@Override
	public Color getSlot(int col, int row) {
		assert col >= 0;
		assert col < WIDTH;
		assert row >= 0;
		assert row < HEIGHT;
		return slots[col][row];
	}

	@Override
	public boolean validMove(int col) {
		assert col >= 0;
		assert col < WIDTH;
		return slots[col][HEIGHT - 1] == null;
	}

	@Override
	public boolean validSlot(int col, int row) {
		return col >= 0 && col < WIDTH && row >= 0 && row < HEIGHT;
	}
	
	/**
	 * If an invalid move is sent, for example in a column that is full,
	 * the moving player loses. The validity of a move can be checked with validMove() beforehand.
	 * @return whether the move could be made. 
	 */
	public boolean move(Color color, int col) {
		assert col >= 0;
		assert col < WIDTH;
		if (hasEnded) {
			return false;
		}
		for (int row = 0; row < HEIGHT; row++) {
			if (slots[col][row] == null) {
				slots[col][row] = color;
				checkEnd(col, row);
				lastMove = col;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean full() {
		boolean full = true;
		for (int col = 0; col < WIDTH; col++) {
			if (slots[col][HEIGHT - 1] == null) {
				full = false;
			}
		}
		return full;
	}
	
	public void checkEnd(int x, int y) {
		if (checkFour(x, y)) {
			hasEnded = true;
			winner = slots[x][y];
			return;
		}
		if (full()) {
			hasEnded = true;
			winner = null;
		}
	}
	
	public boolean checkFour(int x, int y) {
		return  checkFour(x, y, 0, 1) ||
				checkFour(x, y, 1, 1) || 
				checkFour(x, y, 1, 0) ||
				checkFour(x, y, 1, -1);
	}
	
	public boolean checkFour(int x, int y, int dx, int dy) {
		int count = 0;
		Color c = slots[x][y]; 
		for (int i : new Range(-3, 4)) {
			int curx = x + dx * i;
			int cury = y + dy * i;
			if (validSlot(curx, cury)) {
				if (slots[x][y] == c) {
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
	
	/**
	 * Check who the winner is.
	 * @return color of the winner or null in case of draw or when polled before the end of the game.
	 */
	@Override
	public Color winner() {
		return winner;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row : new Range(HEIGHT)) {
			for (int col : new Range(WIDTH)) {
				sb.append(" [" + slots[col][row] + "]");
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	public int lastMove() {
		return lastMove;
	}
}
