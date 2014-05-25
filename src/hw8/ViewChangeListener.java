package hw8;

/**
 * A view change listener is used by mandel view to indicate its
 * view has changed.
 * Other things can use this to update at the same time
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public interface ViewChangeListener {
	/**
	 * Called when the mandel view is redrawn.
	 * @param x
	 * @param y
	 * @param scale
	 * @param depth
	 */
	void viewChanged(double x, double y, double scale);
}
