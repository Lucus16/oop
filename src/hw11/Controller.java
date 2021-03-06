package hw11;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Lars Jellema (s4388747)
 * @author Sal Wolffs (s4064542)
 * 
 *         The initial controller runs as a single thread
 */
public class Controller implements Runnable {
	private int delay = 200; // average sleep time
	private final Model model; // the model
	private final Random random; // a random generator
	private boolean run = true; // car can run in simulation
	private WaitCounter patience;
	private int temper;
	private ExecutorService driverPool;
	
	private Crossing crossing;
	/* crossing is logically part of the model, but needs to be controlled,
	 * and the model does not know about the controller. Restructuring other
	 * people's code to be sane is overkill for a < 1 week project.
	 */
	
	/**
	 * The constructor of the controller
	 * 
	 * @param model
	 *            holds the cars
	 */
	public Controller(Model model) {
		this.model = model;
		random = new Random();
		patience = new WaitCounter();
		temper = Model.NUMBEROFCARS;
		patience.set(temper);
		crossing = new Crossing(model,this);
		driverPool = new ThreadPoolExecutor
				(Model.NUMBEROFCARS,Integer.MAX_VALUE,5,TimeUnit.SECONDS,
						new LinkedBlockingQueue());
		
	}

	/**
	 * the run method from Thread forever: move all cars sleep some time
	 */
	public void run() {
		for (Car c : model.getCars()){
			driverPool.execute(new Driver(c,this,model,crossing));
		}
		while (true) {
			stepAllCars();
			crossing.step();
			pause();
		}
	}

	/**
	 * wait some pseudo random time
	 */
	private void pause() {
		try { // sleep can throw an exception
			Thread.sleep((delay / 2 + random.nextInt(delay))/1000);
		} catch (InterruptedException e) { // catch the exception thrown by
											// sleep
			System.out.println("An exception in Controller: " + e);
		}
	}

	/**
	 * make one step with all cars and repaint views.
	 */
	public void stepAllCars() {
		if (run) {
			synchronized (patience){
			if(!patience.checkFree()){
				try {
					System.out.println("Waiting on " + patience.getWait());
					patience.wait();
					System.out.println("Waited on " + patience.getWait());
				} catch (InterruptedException e) { e.printStackTrace(); } 
			}
			synchronized (this) {
				patience.set(temper); 
				notifyAll(); 
				}
			}//sync this
		}
		model.update(); // update only after all cars have stepped
	}

	/**
	 * stop all cars by setting boolean run to false
	 */
	public void stopCars() {
		run = false;
	}

	/**
	 * start all cars by setting boolean run to true
	 */
	public void resumeCars() {
		run = true;
	}

	public int getDelay() {
		return delay;
	}

	/**
	 * set delay between maximum and minimum bounds
	 * 
	 * @param d
	 */
	public void setDelay(int d) {
		delay = Math.max(50, Math.min(2000, d));
	}

	public synchronized void checkIn() {
		patience.lower();
	}

	public synchronized void waitLess() {
		temper -= 1;
		patience.lower();
	}

	public synchronized void waitMore() {
		temper += 1;
		patience.increase();

	}
}
