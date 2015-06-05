package hw11;

/**
 * Used to provide something for the Controller to wait() on while waiting
 * for all drivers to attempt to take a step.
 * 
 * @author Sal Wolffs (s4064542)
 * @author Lars Jellema (s4388747)
 *
 */
class WaitCounter{
	private int wait;
	
	public WaitCounter(){
		wait = 0;
	}
	
	public void set(int amt){
		if(amt < 0){
			System.err.println(
					"Cannot count negative amount of objects to be "
					+ " waited on.");
		}
		wait = amt;
	}
	
	public synchronized void lower(){
		wait -= 1; 
		System.out.println("lowered wait to " + wait);
		if(wait < 0){
			System.err.println("Waiting lowered below 0!");
		}
		if(wait <= 0){
			notifyAll();
		}
	}
	
	public synchronized void increase(){
		wait += 1;
		System.out.println("increased wait to " + wait);
	}
	
	public synchronized boolean checkFree(){
		return wait == 0;
	}
	
	public synchronized int getWait(){
		return wait;
	}
}