package hw12;

import java.util.Observable;

public class Model extends Observable {
	public static final int MAX_FOOD = 300; //Max food
	public static final int FEED_SIZE = 5; //Food per meal
	public static final int FOOD_TICK = 1000; //Time between food ticks in ms
	public static final int START_FOOD = 100; //Food at start
	
	private int food;
	
	public Model() {
		food = START_FOOD;
		new TimerListener(this);
	}
    
    /**
     * Accepts negative numbers to remove food
     * @param added
     */
    public void addFood(int added) {
    	food = Math.max(0, Math.min(MAX_FOOD, food + added));
    	setChanged(); notifyObservers();
    }
    
    public int getFood() {
    	return food;
    }
    
    public int getMood() {
    	return Math.min(food / 100, 2);
    }
}
