package hw8;

public interface Painter {
    public static final int[] BLACK	= {0, 0, 0};
    public static final int[] RED	= {255, 0, 0};
    public static final int[] GREEN	= {0, 255, 0};
    public static final int[] BLUE	= {0, 0, 255};
    public static final int[] WHITE	= {255, 255, 255};
    
	public int[] getColor(int index);
}
