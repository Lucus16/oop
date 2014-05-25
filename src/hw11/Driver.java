package hw11;

public class Driver implements Runnable {
	private Car ride;
	private Controller timer;
	
	public Driver (Car car, Controller controller){
		if (!car.drive()){
			throw new IllegalArgumentException(
					"Car " + car.toString() + " is already being driven!");
		}
		ride = car;
		timer = controller;
	}
	
	@Override
	public void run() {
		while (true){
			timer.checkIn();
			try {
				timer.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(canMove()){
				ride.step();
			}
		}
	}

	private boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}

}
