package hw4;

public interface Player {
	/**
	 * Give the player a read only reference to the board he can use to inspect it while playing,
	 * as well as the color he will be playing. The player should store both of these. 
	 * @param board
	 * @param color
	 */
	public void initGame(BoardRO board, Color color);
	
	/**
	 * Get a move from the player.
	 * @return the column where the player wants to make a move.
	 */
	public int getMove();
	
	/**
	 * Send the player the move from the enemy, in case he wants to apply some special handling,
	 * like printing it to the screen. The board that the player has a reference to will also
	 * be updated, so the player doesn't need to do anything here.
	 */
	public void sendMove(int col);
}
