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
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			synchronized (model){
			if(canMove()){ // atomic: check legality and commit.
				car.step(); 
			}
			}
		}
	}

	private boolean canMove() {
		boolean accu = true;
		accu = accu && checkCrossing();
		accu = accu && checkCars();
		return accu;
	}

	private boolean checkCars() {
		for(Car c : model.getCars()){
			if (car.checkCollide(c)){
				return false;
			}
		}
		return false;
	}

	private boolean checkCrossing() {
		// TODO Auto-generated method stub
		return false;
	}

}
