package hw8;

import java.awt.Insets;

import javax.swing.JFrame;

public class Main {
    // the size of the window
    public static final int WIDTH = 800, HEIGHT = 600;
    
	public static void main(String[] args) {
	    JFrame mainFrame = new JFrame("Mandelbrot");
	    	
	    Insets insets = mainFrame.getInsets();
	    MandelView mandelView = new MandelView(WIDTH - insets.left -
	    		insets.right, HEIGHT - insets.top - insets.bottom);
	    
	    mainFrame.add(mandelView);
	    mainFrame.pack();

		mainFrame.setLocationRelativeTo(null);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	    
		mandelView.redraw();
		
		MouseHandler mh = new MouseHandler(mandelView);
	    mandelView.addMouseListener(mh);
	    mandelView.addMouseMotionListener(mh);
	}
}
