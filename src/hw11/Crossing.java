package hw11;

import java.util.ArrayList;

/**
 * @author Lars Jellema (s4388747)
 * @author Sal Wolffs (s4064542)
 * 
 *         English version of the Dutch "Regelaar"
 */
public class Crossing implements Runnable {
	private enum BiDirection{
		HORIZONTAL,VERTICAL;
		
		private boolean waitedFor;
		
		private BiDirection(){
			waitedFor = false;
		}
		
		public static BiDirection directionToBi(Direction dir){
			if(dir == Direction.North || dir == Direction.South){
				return VERTICAL;
			}
			else{
				return HORIZONTAL;
			}
		}
	}
	public static final int entryloc = (RoadView.WINDOWSIZE / 2)
			- Car.CARWIDTH - 4;
	public static final int width = 2 * Car.CARWIDTH + 8;
	public static final int leaveloc = entryloc + width;
	private static int BASETIMEOUT = 5;
	private Model model;
	private Controller timer;
	private BiDirection active,inactive;
	private int timeout;
	private ArrayList<Car> occupiers;
	private boolean switchlocked;
	

	public Crossing(Model m, Controller c) {
		model = m;
		timer = c;
		occupiers = new ArrayList<Car>();
		active = BiDirection.HORIZONTAL;
		inactive = BiDirection.VERTICAL;
		timeout = 0;
		switchlocked = false;
	}
	
	private void switchover() {
		if(!switchlocked) return;
		cleanOccupiers();
		
	}

	private void cleanOccupiers() {
		// TODO Auto-generated method stub
		
	}

	public static boolean unsafe(Car car) {
		return (car.getLocation() > entryloc && car.getLocation() - Car.CARLENGTH < leaveloc);
	}

	public void claim(Car car) {
		if(Model.DIRECTIONS == 2) return;
		if(!unsafe(car)){
			return;
		}
		synchronized (this){
			if(BiDirection.directionToBi(car.getDir()) == active && !switchlocked){
				
			}
		}
	}

	private boolean matchDirection(Car car) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
