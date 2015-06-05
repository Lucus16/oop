package hw12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerListener implements ActionListener {
	private Model model;
	private Timer timer;
	
	public TimerListener(Model model) {
		this.model = model;
        timer = new Timer(Model.FOOD_TICK, this);
        timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.addFood(-1);
		timer.restart();
	}
	
}
