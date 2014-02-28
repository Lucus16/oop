package hw4;

public interface Player {
	/**
	 * Give the player a read only reference to the board he can use to inspect it while playing,
	 * as well as the color he will be playing. The player should store both of these.
	 */
	public void initGame(BoardInfo board, Color color);
	
	/**
	 * Get a move from the player.
	 * @return the column where the player wants to make a move.
	 */
	public int getMove();
	
	/**
	 * Notify the player that the board has been updated.
	 */
	public void update();
}
