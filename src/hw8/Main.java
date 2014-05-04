package hw8;

import java.awt.Insets;

import javax.swing.JFrame;

public class Main {
    // the size of the window
    public static final int WIDTH = 800, HEIGHT = 600;
    
	public static void main(String[] args) {
	    JFrame mainFrame = new JFrame ("Mandelbrot");
	                      
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	    	
	    Insets insets = mainFrame.getInsets ();
	    GridView grid = new GridView (WIDTH - insets.left - insets.right, HEIGHT - insets.top - insets.bottom);
	        
	    mainFrame.add(grid);
		mainFrame.pack();
		
		MandelView mandelView = new MandelView(grid);
		mandelView.redraw();
	}
}
