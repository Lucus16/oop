package hw4;

public interface BoardInfo {
	public Color getSlot(int col, int row);
	public boolean hasEnded();
	public Color winner();
	public boolean validMove(int col);
	public boolean validSlot(int col, int row);
	public String toString();
	public int lastMove();
	public int getWidth();
	public int getHeight();
}
