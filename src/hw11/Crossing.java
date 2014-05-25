package hw11;

/**
 * @author Lars Jellema (s4388747)
 * @author Sal Wolffs (s4064542)
 * 
 *         English version of the Dutch "Regelaar"
 */
public class Crossing {
	private static final int entryloc = (RoadView.WINDOWSIZE / 2)
			- Car.CARWIDTH - 4;
	private static final int width = 2 * Car.CARWIDTH + 8;
	private static final int leaveloc = entryloc + width;
	private Model model;
	private Controller timer;
	private boolean horizontal;
	private int timeout;

	public Crossing(Model m, Controller c) {
		model = m;
		timer = c;
	}
	
	private void switchover() {
		
	}

	public static boolean unsafe(Car car) {
		// TODO Auto-generated method stub
		return false;
	}

	public void claim(Car car) {
		
	}
}
