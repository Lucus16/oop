package hw4;

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
	
	public String toString() {
		return string;
	}
	
	public char toChar() {
		return ch;
	}
	
	public Color other() {
		return other;
	}
}
