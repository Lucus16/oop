package hw8;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Main class starting the view and adding the interface 
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class Main implements RedrawListener {
	/**
	 * Handler for the apply button
	 */
	public class GoButtonHandler implements ActionListener {
		private JFormattedTextField xSource, ySource, scaleSource;
		private MandelView model;
		
		/**
		 * Initialize the handler by storing references to the text fields
		 * and the model
		 * @param xSource
		 * @param ySource
		 * @param scaleSource
		 * @param model
		 */
		public GoButtonHandler(
				JFormattedTextField xSource,
				JFormattedTextField ySource,
				JFormattedTextField scaleSource,
				MandelView model) {
			this.xSource = xSource;
			this.ySource = ySource;
			this.scaleSource = scaleSource;
			this.model = model;
		}
		
		/**
		 * Applies the values from the text fields to the model
		 * @param arg0 the action event
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				xSource.commitEdit();
				ySource.commitEdit();
				scaleSource.commitEdit();
				
				model.setView(parser.parse(xSource.getText()).doubleValue(),
					parser.parse(ySource.getText()).doubleValue(),
					parser.parse(scaleSource.getText()).doubleValue());
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	static NumberFormat parser = NumberFormat.getInstance();
	// the size of the window
	public static final int WIDTH = 800, HEIGHT = 600;
	
	private MandelView mandelView;
	
	JFormattedTextField xField,yField,scaleField,calcDepthField;
	
	/**
	 * Initialize the main class by creating the window, the view and
	 * the controls
	 */
	public Main() {
		parser.setMaximumFractionDigits(40);
		JFrame mainFrame = new JFrame("Mandelbrot");
			
		Insets insets = mainFrame.getInsets();
		mandelView = new MandelView(WIDTH - insets.left -
				insets.right, HEIGHT - insets.top - insets.bottom);
		
		JPanel controls = new JPanel();
		
		controls.add(new JLabel("X:"));
		xField = new JFormattedTextField(parser);
		xField.setColumns(10);
		controls.add(xField);
		
		controls.add(new JLabel("Y:"));
		yField = new JFormattedTextField(parser);
		yField.setColumns(10);
		controls.add(yField);
		
		controls.add(new JLabel("zoom:"));
		scaleField = new JFormattedTextField(parser);
		scaleField.setColumns(10);
		controls.add(scaleField);
		
		JButton recalcButton = new JButton("Apply");
		recalcButton.addActionListener(
				new GoButtonHandler(xField, yField,
						scaleField, mandelView));
		controls.add(recalcButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mandelView.resetView();
			}
		});
		controls.add(resetButton);
		
		mandelView.add(controls);
		mandelView.addRedrawListener(this);
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
	
	/**
	 * Start the mandelbrot viewer by creating a main
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void redrawn(double x, double y, double scale) {
		xField.setValue(x);
		yField.setValue(y);
		scaleField.setValue(scale);
	}
}
