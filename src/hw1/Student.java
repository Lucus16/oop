package hw1;

/**
 * Student class holds data on a single student.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */

public class Student {
	private String first;
	private String last;
	private int number;
	
	public Student(String first, String last, int number) {
		this.first = first;
		this.last = last;
		this.number = number;
	}
	
	public String toString() {
		return first + " " + last + " s" + number;
	}
	
	public void setName(String first, String last) {
		this.first = first;
		this.last = last;
	}
}
