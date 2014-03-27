package hw7.server.engine;

import hw7.shared.Price;

public class SaleItem {
	private static int nextId;
	
	private int id;
	private String name;
	private String description;
	private Price price;
	
	public SaleItem(String name, String description, Price price) {
		id = nextId++;
		this.name= name;
		this.description = description;
		this.price = price;
	}
}
