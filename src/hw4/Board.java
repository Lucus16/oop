package hw4;

public class Board {
	public static final int WIDTH = 7, HEIGHT = 6;
	private Color[][] slots;
	private boolean hasEnded;
	private Color winner;
	
	public Board() {
		slots = new Color[WIDTH][HEIGHT];
	}
	
	/**
	 * Make a copy of another board.
	 */
	public Board(Board old) {
		slots = new Color[WIDTH][HEIGHT];
		for (int col = 0; col < WIDTH; col++) {
			for (int row = 0; row < HEIGHT; row++) {
				slots[col][row] = old.slots[col][row];
			}
		}
	}
	
	public Color getSlot(int col, int row) {
		assert col >= 0;
		assert col < WIDTH;
		assert row >= 0;
		assert row < HEIGHT;
		return slots[col][row];
	}
	
	public boolean validMove(int col) {
		assert col >= 0;
		assert col < WIDTH;
		return slots[col][HEIGHT - 1] == null;
	}
	
	/**
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
				checkEnd();
				return true;
			}
		}
		return false;
	}
	
	private void checkEnd() {
		Color c = checkFour(0, 1);
		if (c == null) {
			c = checkFour(1, 1);
		}
		if (c == null) {
			c = checkFour(1, 0);
		}
		if (c == null) {
			c = checkFour(1, -1);
		}
		if (c != null) {
			hasEnded = true;
			winner = c;
			return;
		}
		boolean full = true;
		for (int col = 0; col < WIDTH; col++) {
			if (slots[col][HEIGHT - 1] == null) {
				full = false;
			}
		}
		if (full) {
			hasEnded = true;
			winner = null;
		}
	}
	
	private Color checkFour(int dx, int dy) {
		for (int x = Math.max(0, -3 * dx); x < Math.min(WIDTH, WIDTH - 3 * dx); x++) {
			for (int y = Math.max(0, -3 * dy); y < Math.min(HEIGHT, HEIGHT - 3 * dy); y++) {
				Color c = checkFour(x, y, dx, dy);
				if (c != null) {
					return c;
				}
			}
		}
		return null;
	}
	
	private Color checkFour(int x, int y, int dx, int dy) {
		Color c = slots[x][y];
		for (int i = 1; i < 4; i++) {
			if (slots[x + i * dx][y + i * dy] != c) {
				return null;
			}
		}
		return c;
	}
	
	public boolean hasEnded() {
		return hasEnded;
	}
	
	/**
	 * Check who the winner is.
	 * @return color of the winner or null in case of draw or when polled before the end of the game.
	 */
	public Color winner() {
		return winner;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				sb.append(" [" + slots[col][row] + "]");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
