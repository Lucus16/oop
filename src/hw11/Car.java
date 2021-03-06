package hw11;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * @author Lars Jellema (s4388747)
 * @author Sal Wolffs (s4064542)
 * 
 * OO1route66 initial class
 * 
 * @author Pieter Koopman
 * 
 *         Class for an individual car
 */
public class Car {
	public static final int 
		CARWIDTH    = 20, 
		CARLENGTH   = 40, 
		MINCARSPACE = 5,
		MINCARSPEED = 2, 
		MAXCARSPEED = 4;
	public static final Color[] carColors = { 
			Color.red, Color.blue, Color.black, Color.gray, Color.green, 
			Color.pink, Color.orange,Color.magenta, Color.lightGray
			};
	
	private final int speed, number; // speed and car-number
	private final Direction direction; // driving direction, used by paint
	private final Color color; // color of this car
	private final Random random; // here to ensure that every car gets a new

	private int location; // current location on the road
	private boolean nodriver;

	/**
	 * The constructor
	 * 
	 * @param number
	 *            of the car
	 */
	public Car(int number) {
		this.number = number;
		random = new Random();
		speed = MINCARSPEED + random.nextInt(MAXCARSPEED - MINCARSPEED + 1);
		direction = Direction.intToDirection(number);
		location = RoadView.WINDOWSIZE - 2 - (number / Model.DIRECTIONS)
				* (CARLENGTH + MINCARSPACE);
		color = carColors[number % carColors.length];
		nodriver = true;
	}

	/**
	 * move this car one step
	 */
	public synchronized void step() {
		location = (location + speed) % RoadView.WINDOWSIZE;
		notifyAll();
	}

	/**
	 * paint this car
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		int x, y, w, h;
		switch (direction) {
		case North:
			x = RoadView.WINDOWSIZE / 2 + 1;
			y = RoadView.WINDOWSIZE - location;
			w = Car.CARWIDTH;
			h = Car.CARLENGTH;
			break;
		case East:
			x = location - Car.CARLENGTH;
			y = RoadView.WINDOWSIZE / 2 + 1;
			w = Car.CARLENGTH;
			h = Car.CARWIDTH;
			break;
		case South:
			x = RoadView.WINDOWSIZE / 2 - Car.CARWIDTH - 1;
			y = location - Car.CARLENGTH;
			w = Car.CARWIDTH;
			h = Car.CARLENGTH;
			break;
		case West:
			x = RoadView.WINDOWSIZE - location - 1;
			y = RoadView.WINDOWSIZE / 2 - Car.CARWIDTH - 1;
			w = Car.CARLENGTH;
			h = Car.CARWIDTH;
			break;
		default:
			x = y = w = h = 10;
		}
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(number), x + w / 6, y + h / 2 + 4);
	}

	/**
	 * yield the current location of this car
	 * 
	 * @return
	 */
	public int getLocation() {
		return location;
	}

	public synchronized boolean drive() {
		boolean tmp = nodriver;
		nodriver = false;
		return tmp;
	}

	public boolean checkCollide(Car c) {
		if(c.direction != direction){
			return false;
		}
		Car first, second; //lock ordering;
		if (c.number < number){
			first = c;
			second = this;
		}
		else{
			first = this;
			second = c;
		}
		synchronized (first){synchronized (second){
			if(c.location > location && 
					(location + speed) % RoadView.WINDOWSIZE > 
					c.location - CARLENGTH - MINCARSPACE){
				return true;
			}
			else{
				return false;
			}
		}}//release second, first.
	}
	
	public Direction getDir(){
		return direction;
	}
	
	public int getNumber(){
		return number;
	}
}
