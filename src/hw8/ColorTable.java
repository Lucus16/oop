package hw8;

import java.awt.Color;

/**
 * ColorTable is a Painter that paints stuff by cycling through a list of colors
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class ColorTable implements Painter {
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
     * Create a rainbow colortable by default
     */
    public ColorTable() {
    	rainbow();
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
     * Generate a table with rainbow colors
     */
    public void rainbow() {
    	colors = new int[90][3];
    	for (int n = 0; n < 3; n++) {
        	for (int i = 0; i < 15; i++) {
        		colors[i + 30 * n][n] = 255;
        		colors[i + 30 * n][(n + 1) % 3] = i * 17;
        		colors[i + 30 * n][(n + 2) % 3] = 0;
        	}
        	for (int i = 0; i < 15; i++) {
        		colors[i + 15 + 30 * n][n] = 255 - i * 17;
        		colors[i + 15 + 30 * n][(n + 1) % 3] = 255;
        		colors[i + 15 + 30 * n][(n + 2) % 3] = 0;
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