package hw8;

/**
 * A redraw listener is used by mandel view to indicate it has redrawn
 * Other things can use this to update at the same time
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public interface RedrawListener {
	/**
	 * Called when the mandel view is redrawn.
	 * @param x
	 * @param y
	 * @param scale
	 * @param depth
	 */
	void redrawn(double x, double y, double scale);
}
