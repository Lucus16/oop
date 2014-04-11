package hw7.server.engine;

import hw7.shared.Price;

/**
 * 
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
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
