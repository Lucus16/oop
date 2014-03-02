package hw4;

/**
 * Color enum offering basic properties of a given color.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public enum Color {
	NONE, YELLOW, RED;
	
	private Color other;
	private String string;
	private char ch;

	static {
		YELLOW.other = RED;
		RED.other = YELLOW;
		YELLOW.ch = 'O';
		RED.ch = 'X';
		NONE.ch = ' ';
		YELLOW.string = "Yellow";
		RED.string = "Red";
	}
	
	/**
	 * String representation of the color, "Red" or "Yellow"
	 */
	public String toString() {
		return string;
	}
	
	/**
	 * Character representation of the color, 'X' or 'O'
	 * @return
	 */
	public char toChar() {
		return ch;
	}
	
	/**
	 * Return the other of the color that was passed.
	 * @return
	 */
	public Color other() {
		return other;
	}
}
