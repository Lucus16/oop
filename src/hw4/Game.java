package hw4;

public class Game {
	public static final Color FIRST = Color.YELLOW; 
	private Board board;
	private Player yellow, red;
	
	public Game(Player yellow, Player red) {
		board = new Board();
		yellow.initGame(board);
		red.initGame(board);
		this.yellow = yellow;
		this.red = red;
	}
	
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
	
	private Player getPlayerByColor(Color color) {
		return (color == Color.YELLOW ? yellow : red);
	}
}

