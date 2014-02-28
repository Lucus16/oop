package hw4;

public class Game {
	public static final Color FIRST = Color.YELLOW; 
	private Board board;
	private Player yellow, red;
	
	public Game(Player yellow, Player red) {
		board = new Board();
		yellow.initGame(board, Color.YELLOW);
		red.initGame(board, Color.RED);
		this.yellow = yellow;
		this.red = red;
	}
	
	public Color play() {
		Color turn = FIRST;
		while (!board.hasEnded()) {
			int move = (turn == Color.YELLOW ? yellow : red).getMove();
			board.move(turn, move);
			red.update();
			yellow.update();
			turn = turn.other();
		}
		return board.winner();
	}
}

