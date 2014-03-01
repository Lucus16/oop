package hw4.players;

import hw4.BoardInfo;
import hw4.Color;
import hw4.Player;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import tuple.Tuple;

public class MinMaxAI extends Player {
	public MinMaxAI(String name, Color color) {
		super(name, color);
	}

	private Comparator<BoardInfo> valuator;

	@Override
	public int getMove() {
		return 0;
	}
}
