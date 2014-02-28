package hw4;

public enum Color {
	YELLOW, RED;
	
	private Color other;
	private String string;

	static {
		YELLOW.other = RED;
		RED.other = YELLOW;
		YELLOW.string = "O";
		RED.string = "X";
	}
	
	public String toString() {
		return string;
	}
	
	public Color other() {
		return other;
	}
}
