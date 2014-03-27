package hw7.shared;

public class Price {
	private int cents;
	
	public Price(int euros) {
		cents = euros * 100;
	}
	
	public Price(int euros, int cents) {
		this.cents = euros * 100 + cents;
	}
	
	public Price(double price) {
		cents = (int)(price * 100);
	}
	
	public Price(Price o) {
		cents = o.cents;
	}
	
	public String toString() {
		String centString = (cents < 10 ? "0" + cents % 100 : "" + cents % 100);
		return "€" + cents / 100 + "," + centString;
	}
}
