package hw8;

/**
 * A Painter has one function which is used to convert a mandel number to
 * a color. This describes how the mandelbrot figure ends up being rendered
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public interface Painter {
    public static final int[] BLACK	= {0, 0, 0};
    public static final int[] RED	= {255, 0, 0};
    public static final int[] GREEN	= {0, 255, 0};
    public static final int[] BLUE	= {0, 0, 255};
    public static final int[] WHITE	= {255, 255, 255};
    
    /**
     * Get a color based on a mandel number.
     * @param index
     * @return
     */
	public int[] getColor(int index);
}
