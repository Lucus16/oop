package hw12;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Sjaak
 */
public class MoodFrame extends JFrame {
	private static final long serialVersionUID = -772378795104775829L;
	private ImageIcon [] moods = 
        { new ImageIcon( "images/happy.png" ), 
          new ImageIcon( "images/normal.png" ), 
          new ImageIcon( "images/sad.png" ) };
    private int mood = 1;
    
    public MoodFrame() {
        super("M");
        setSize(190,170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, getWidth(), getHeight());
        moods[mood].paintIcon(null, g, 27, 38);
    }
    
    public void setMood(int mood) {
    	this.mood = mood;
    }
}