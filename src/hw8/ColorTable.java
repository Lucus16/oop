package hw8;

import java.awt.Color;

public class ColorTable {
    public static final int[] BLACK	= {0, 0, 0};
    public static final int[] RED	= {255, 0, 0};
    public static final int[] GREEN	= {0, 255, 0};
    public static final int[] BLUE	= {0, 0, 255};
    public static final int[] WHITE	= {255, 255, 255};
 
    private int[][] colors;
    
   /**
    * converts specified color to an rgb array 
    * @param color the color to be converted
    * @return the correspoding array of rgb values
    */
    public static int[] colorToRGB(Color color) {
    	int[] rgb = {color.getRed(), color.getGreen(), color.getBlue()};
    	return rgb;
    }
    
    /**
     * creates and fills the table with the specified size
     * @param size the size of the table
     */
    public ColorTable(int size) {
        this.colors = new int[size][3];
    }
    
    /**
     * fills the table randomly
     */
    public void randomize() {
    	for (int[] color : colors) {
    		for (int i = 0; i < 3; i++) {
    			color[i] = (int)(Math.random() * 256);
    		}
    	}        
    }

    /**
     * converts an index into an rgb value
     * @param index to be converted
     * @return the resulting rgb value
     */
    public int[] getColor(int index) {
       return colors[index % colors.length];
    }
    
    public void setColor(int index, int[] color) {
    	colors[index] = color; 
    }
}