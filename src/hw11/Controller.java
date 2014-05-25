package hw11;

import java.util.Random;

/**
 * OO1route66 initial class
 * @author Pieter Koopman
 *
 * The initial controller runs as a single thread
 */
public class Controller
{
	/**
	 * Used to provide something for the Controller to wait() on while waiting
	 * for all drivers to attempt to take a step.
	 * 
	 * @author Sal Wolffs (s4064542)
	 * @author Lars Jellema (s4388747)
	 *
	 */
	private class WaitCounter{
		private final int total;
		private int wait;
		
		public WaitCounter(int amt){
			if(amt <= 0){
				throw new IllegalArgumentException(
						"Cannot count non-positive amount of object to be"
						+ " waited on.");
			}
			total = amt;
			wait = 0;
		}
		
		public void reset(){
			wait = total;
		}
		
		public synchronized void lower(){
			wait -= 1;
			if(wait < 0){
				System.err.println("Waiting lowered below 0!");
			}
			if(wait <= 0){
				notifyAll();
			}
		}
	}
	
    private int delay = 200;                // average sleep time
    private final Model model;              // the model
    private final Random random;            // a random generator
    private boolean run = true;             // car can run in simulation

    /**
     * The constructor of the controller
     * @param model holds the cars
     */
    public Controller(Model model) {
        this.model  = model;
        random      = new Random();
        WaitCounter patience    = new WaitCounter(Model.NUMBEROFCARS);
    }

    /**
     * the run method from Thread
     * forever:
     *      move all cars
     *      sleep some time
     */
    public void run() {
        while (true) {
            stepAllCars();
            pause();
        }
    }
    
    /**
     * wait some pseudo random time
     */
    private void pause() {
        try { // sleep can throw an exception 
            Thread.sleep(delay / 2 + random.nextInt(delay));
        }
        catch (InterruptedException e) { // catch the exception thrown by sleep
            System.out.println("An exception in Controller: " + e);
        }
    }
    
    /**
     * make one step with all cars and repaint views.
     */
    public void stepAllCars() {
        if (run) {
            for (int c = 0; c < Model.NUMBEROFCARS; c += 1) {
                model.getCar(c).step();
            }
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
     * @param d 
     */
    public void setDelay(int d) {
        delay = Math.max(50, Math.min (2000, d));
    }

	public synchronized void checkIn() {
		// TODO Auto-generated method stub
		
	}
}
