package hw4;

/**
 * Abstract class to allow the Game class to interface in a uniform manner with
 * AI and human players.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public abstract class Player {
	protected BoardInfo board;
	protected Color color;
	protected String name;
	
	/**
	 * Default constructor for a player, storing the name and color
	 * @param name
	 * @param color
	 */
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	/**
	 * Give the player a read only reference to the board he can use to inspect 
	 * it while playing, as well as the color he will be playing. The player 
	 * should store both of these.
	 * @param board
	 */
	public void initGame(BoardInfo board) {
		this.board = board;
	}
	
	/**
	 * Get a move from the player.
	 * @return the column where the player wants to make a move.
	 */
	public abstract int getMove();
	
	/**
	 * Notify the player that the board has been updated. Does nothing by default.
	 */
	public void update() {
	}
	
	/**
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the color of the player
	 */
	public Color getColor() {
		return color;
	}
}
