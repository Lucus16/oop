package hw4.players;

import hw4.Player;
import hw4.dutch.Bord;
import hw4.dutch.Speler;

public class SpelerWrapper extends Player {
	private Speler speler;
	
	public SpelerWrapper(Speler speler) {
		super(speler.getNaam(), speler.getKleur().toColor());
		this.speler = speler;
	}

	@Override
	public int getMove() {
		int move = speler.Speel(new Bord(board));
		return move;
	}
}
