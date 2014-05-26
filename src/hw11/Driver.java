package hw11;

/**
 * @author Lars Jellema (s4388747)
 * @author Sal Wolffs (s4064542)
 */
public class Driver implements Runnable {
	private Car car;
	private Controller timer;
	private Model model;
	private Crossing crossing;
	
	/**
	 * 
	 */
	public Driver (Car car, Controller controller, 
					Model model, Crossing crossing){
		if (!car.drive()){
			throw new IllegalArgumentException(
					"Car " + car.toString() + " is already being driven!");
		}
		this.car = car;
		timer = controller;
		this.model = model;
		this.crossing = crossing;
	}
	
	@Override
	public void run() {
		while (true){
			synchronized (timer){
				System.out.println("checkIn < " + car.getNumber());
				timer.checkIn();
				System.out.println("checkIn > " + car.getNumber());
				try {
					timer.wait();
				} catch (InterruptedException e) {e.printStackTrace();}
			}//unlock timer
			/* NOTE: The below is only safe if the only thing any other thread 
			 * can do to make this less safe is taking the crossing.
			 * Specifically, no car must back up.
			 */
			if(checkCars()){ 
				if(Crossing.unsafe(car)){
					synchronized(crossing){
					claimCrossing(); //
					car.step();
					}//unlock crossing
				}
				else{
					car.step();
				}
			}
		}
	}
	
	/**
	 * 
	 * @locks timer.patience, Car c : model.getCars, timer.patience
	 * @return
	 */
	private boolean checkCars() {
		boolean allClear = false;
		while(!allClear){
			allClear = true;
			for(Car c : model.getCars()){
				if (car.checkCollide(c)){
					allClear = false;
					System.out.println("waitLess > " + car.getNumber());
					timer.waitLess();
					System.out.println("waitLess < " + car.getNumber());
					while (car.checkCollide(c)){
						synchronized (c){
							try {
								c.wait();
							} catch (InterruptedException e) 
								{e.printStackTrace();}
						}
					}
					System.out.println("waitMore > " + car.getNumber());
					timer.waitMore();
					System.out.println("waitMore < " + car.getNumber());
					
				}
			}
		}
		return true;
	}

	private void claimCrossing() {
		crossing.claim(car);
	}

}
