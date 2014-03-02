package hw4;

public class Game {
	public static final Color FIRST = Color.YELLOW; 
	private Board board;
	private Player yellow, red;
	
	/**
	 * Create a new game with two players.
	 * @param yellow
	 * @param red
	 */
	public Game(Player yellow, Player red) {
		board = new Board();
		yellow.initGame(board);
		red.initGame(board);
		this.yellow = yellow;
		this.red = red;
	}
	
	/**
	 * Play the game. This routine polls the players for moves in turns until the game has ended.
	 * It also handles displaying the board, the turns and the winner.
	 * @return the color of the winner of the game
	 */
	public Color play() {
		Color turn = FIRST;
		while (!board.hasEnded()) {
			System.out.println(board);
			System.out.println(getPlayerByColor(turn).getName() + "'s turn.");
			int move = getPlayerByColor(turn).getMove();
			board.move(turn, move);
			red.update();
			yellow.update();
			turn = turn.other();
		}
		System.out.println(board);
		if (board.winner() == Color.NONE) {
			System.out.println("The game ended in a draw.");
		} else {
			System.out.println(getPlayerByColor(board.winner()).getName() + " has won! Congratulations!");
		}
		return board.winner();
	}
	
	/**
	 * Get the player object based on the color of that player.
	 * @param color
	 * @return the player with that color.
	 */
	private Player getPlayerByColor(Color color) {
		return (color == Color.YELLOW ? yellow : red);
	}
}

