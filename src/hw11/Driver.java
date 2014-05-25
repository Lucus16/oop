package hw11;

public class Driver implements Runnable {
	private Car car;
	private Controller timer;
	private Model model;
	private Crossing crossing;
	
	public Driver (Car car, Controller controller, Model model){
		if (!car.drive()){
			throw new IllegalArgumentException(
					"Car " + car.toString() + " is already being driven!");
		}
		this.car = car;
		timer = controller;
		this.model = model;
	}
	
	@Override
	public void run() {
		while (true){
			synchronized (timer){
			timer.checkIn();
			try {
				timer.wait();
			} catch (InterruptedException e) {e.printStackTrace();}
			}
			// NOTE: Only safe if the only thing any other thread can do
			// to make this less safe is taking the crossing.
			if(checkCars()){ 
				if(Crossing.unsafe(car)){
					synchronized(crossing){
					claimCrossing();
					car.step();
					}
				}
				else{
					car.step();
				}
			}
		}
	}

	private boolean checkCars() {
		boolean allClear = false;
		while(!allClear){
			allClear = true;
			for(Car c : model.getCars()){
				if (car.checkCollide(c)){
					allClear = false;
					timer.waitLess();
					while (car.checkCollide(c)){
						synchronized (c){
							try {
								c.wait();
							} catch (InterruptedException e) 
								{e.printStackTrace();}
						}
					}
					timer.waitMore();
				}
			}
		}
		return true;
	}

	private void claimCrossing() {
		crossing.claim(car);
	}

}
