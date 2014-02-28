package hw4;

public enum Color {
	YELLOW, RED;
	
	public Color other() {
		return this == YELLOW ? RED : YELLOW;
	}
	
	public String toString() {
		return this == YELLOW ? "O" : "X";
	}
}
