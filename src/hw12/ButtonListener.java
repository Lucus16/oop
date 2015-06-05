package hw12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
	private Model model;
	
	public ButtonListener(Model model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.addFood(Tamagotchi.FEED_SIZE);
	}
}
