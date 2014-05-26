package hw11;

import java.util.ArrayList;

/**
 * @author Lars Jellema (s4388747)
 * @author Sal Wolffs (s4064542)
 * 
 *         English version of the Dutch "Regelaar"
 */
public class Crossing{
	private enum BiDirection{
		HORIZONTAL,VERTICAL;
		
		@SuppressWarnings("unused")
		public boolean waitedFor;
		
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
	private static int BASETIMEOUT = 20;
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
	
	private synchronized void switchover() {
		if(!switchlocked) return;
		cleanOccupiers();
		if(occupiers.isEmpty()){
			BiDirection tmp = active;
			active = inactive;
			inactive = tmp;
			timeout = BASETIMEOUT;
			switchlocked = false;
		}
	}

	private synchronized void cleanOccupiers() {
		ArrayList<Car> toRemove = new ArrayList<Car>();
		for(Car c : occupiers){
			toRemove.add(c);
		}
		for(Car c : toRemove){
			occupiers.remove(c);
		}
	}
	
	

	public static boolean unsafe(Car car) {
		return (car.getLocation() > entryloc && car.getLocation() - Car.CARLENGTH < leaveloc);
	}
	
	public void step(){
		if(timeout > 0) {
			--timeout;
		}
		else{
			if(inactive.waitedFor){
				cleanOccupiers();
				switchlocked = true;
				switchover();
			}
		}
	}
	
	public void claim(Car car) {
		if(Model.DIRECTIONS == 2) return;
		if(!unsafe(car)){
			return;
		}
		if(!occupiers.contains(car)){
			return;
		}
		//so the car really needs to claim the crossing.
		BiDirection cardir = BiDirection.directionToBi(car.getDir());
		while(true)
		synchronized (this){
			if(cardir == active && !switchlocked){
				occupiers.add(car);
				return;
			}
			else{
				cardir.waitedFor = true;
				timer.waitLess();
				synchronized (cardir){
					try {
						cardir.wait();
					} catch (InterruptedException e) { e.printStackTrace(); }
				}
				timer.waitMore();
			}
		}
	}
}
