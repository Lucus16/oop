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

public class Main implements RedrawListener {
	public class GoButtonHandler implements ActionListener {
		private JFormattedTextField xSource, ySource, scaleSource, depthSource;
		private MandelView model;
		
		public GoButtonHandler(
				JFormattedTextField xSource,
				JFormattedTextField ySource,
				JFormattedTextField scaleSource,
				JFormattedTextField depthSource,
				MandelView model) {
			this.xSource = xSource;
			this.ySource = ySource;
			this.scaleSource = scaleSource;
			this.depthSource = depthSource;
			this.model = model;
		}
		
		
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				xSource.commitEdit();
				ySource.commitEdit();
				scaleSource.commitEdit();
				depthSource.commitEdit();
				
				model.setLimit(parser.parse(depthSource.getText()).intValue());
				
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
	
	public Main() {
		parser.setMaximumFractionDigits(40);
		JFrame mainFrame = new JFrame("Mandelbrot");
			
		Insets insets = mainFrame.getInsets();
		mandelView = new MandelView(WIDTH - insets.left -
				insets.right, HEIGHT - insets.top - insets.bottom);
		
		JPanel controls = new JPanel();
		//controls.setOpaque(false);
		
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
		
		controls.add(new JLabel("depth:"));
		calcDepthField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		calcDepthField.setColumns(6);
		controls.add(calcDepthField);
		
		JButton recalcButton = new JButton("Go");
		recalcButton.addActionListener(
				new GoButtonHandler(xField, yField,
						scaleField, calcDepthField, mandelView));
		controls.add(recalcButton);

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
	
	public static void main(String[] args){
		new Main();
	}

	@Override
	public void redrawn(double x, double y, double scale, int depth) {
		xField.setValue(x);
		yField.setValue(y);
		scaleField.setValue(scale);
		calcDepthField.setValue(depth);
	}
}
