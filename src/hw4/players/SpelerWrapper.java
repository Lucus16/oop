package hw4.players;

import hw4.BoardInfo;
import hw4.Color;
import hw4.Player;
import hw4.dutch.Bord;
import hw4.dutch.Speler;

public class SpelerWrapper implements Player {
	private Speler speler;
	private BoardInfo board;
	private Color color;
	
	public SpelerWrapper(Speler speler) {
		this.speler = speler;
	}

	@Override
	public void initGame(BoardInfo board, Color color) {
		this.board = board;
		this.color = color;
	}

	@Override
	public int getMove() {
		int move = speler.Speel(new Bord(board));
		return move;
	}

	@Override
	public void update() {
	}

}
