package hw8;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

/**
 * 
 * @author Lars Jellema s4388747
 *
 */
public class GridView extends JPanel {
	private BufferedImage gridImage;
    private WritableRaster gridRaster;
    
    private int gridWidth, gridHeight;
    
    private static final int REPAINT_NUMBER = 2000;
    private int nrOfPixelsSet;
    
    /**
     * creates a drawing panel with specified size
     * @param width of the panel
     * @param height of the panel
     */
    public GridView (int width, int height) {
    	nrOfPixelsSet = 0;
    	gridWidth = width;
    	gridHeight = height;
		gridImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		gridRaster = gridImage.getRaster();
        setPreferredSize(new Dimension(width, height));
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
		g.drawImage(gridImage, 0, 0, null);
    }
    
    /**
     * draws the specified pixel with the indicated color
     * @param x coordinate of the location
     * @param y coordinate of the location
     * @param rgb array with length 3 containing the rgb values 
     */
    public void setPixel(int x, int y, int[] rgb) {
        gridRaster.setPixel (x, y, rgb);
        nrOfPixelsSet++;
        if (nrOfPixelsSet > REPAINT_NUMBER) {
        	nrOfPixelsSet = 0;
        	repaint();
        }
    }

    /**
     * a getter for width
     * @return width of the drawing panel
     */
    @Override
    public int getWidth() {
        return gridWidth;
    }

     /**
     * a getter for height
     * @return height of the drawing panel
     */
   @Override
    public int getHeight() {
        return gridHeight;
    }
}