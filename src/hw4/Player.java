package hw4;

public interface Player {
	/**
	 * Give the player a read only reference to the board he can use to inspect it while playing,
	 * as well as the color he will be playing. The player should store both of these.
	 * @param board
	 */
	public void initGame(BoardInfo board);
	
	/**
	 * Get a move from the player.
	 * @return the column where the player wants to make a move.
	 */
	public int getMove();
	
	/**
	 * Notify the player that the board has been updated.
	 */
	public void update();
	
	/**
	 * @return the name of the player
	 */
	public String getName();
	
	/**
	 * @return the color of the player
	 */
	public Color getColor();
}
