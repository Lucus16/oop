package hw12;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Sjaak
 */
public class Tamagotchi extends JFrame implements Observer {
	private static final long serialVersionUID = 8430959158517665953L;
	public static final int MAX_FOOD = 300;
	public static final int FEED_SIZE = 5;
	public static final int FOOD_TICK = 1000; //Time between food ticks in ms
	
	private MoodFrame moodFrame;
	private Model model;
    
    private JButton foodButton;
    private JLabel foodLabel;

    public static void main(String[] args) {
        new Tamagotchi();
    }
    
    public Tamagotchi() {
    	model = new Model();
    	model.addObserver(this);
    	
    	moodFrame = new MoodFrame();
    	 
        foodButton = new JButton("Food"); 
        foodButton.addActionListener(new ButtonListener(model));
        
        foodLabel = new JLabel(Integer.toString(model.getFood()));
        
        setLayout(new GridLayout(2, 1));
        add(foodLabel); 
        add(foodButton); 
        pack();
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

	public void updateGraphics() {
        foodLabel.setText(Integer.toString(model.getFood())); 
        if (model.getFood() > 0) {
            moodFrame.setMood(model.getMood());
            moodFrame.repaint();
        } else {
            System.exit(0);
        }
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateGraphics();
	}
}