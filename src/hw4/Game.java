package hw4;

public class Game {
	public static final Color FIRST = Color.YELLOW; 
	private Board board;
	private BoardRO boardRO;
	private Player yellow, red;
	
	public Game(Player yellow, Player red) {
		board = new Board();
		boardRO = new BoardRO(board);
		yellow.initGame(boardRO, Color.YELLOW);
		red.initGame(boardRO, Color.RED);
		this.yellow = yellow;
		this.red = red;
	}
	
	public Color play() {
		Color turn = FIRST;
		while (!board.hasEnded()) {
			int move = (turn == Color.YELLOW ? yellow : red).getMove();
			board.move(turn, move);
			(turn == Color.YELLOW ? red : yellow).sendMove(move);
			turn = (turn == Color.YELLOW ? Color.RED : Color.YELLOW);
		}
		return board.winner();
	}
}

