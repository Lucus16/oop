package hw1;

import hw1.Student;

/**
 * Group class holds a group of Students.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */

public class Group {
	private Student[] students;
	private int studentcount;
	
	public Group(int size) {
		students = new Student[size];
		studentcount = 0;
	}
	
	/**
	 * Add a student to the group if there is space left
	 * @return whether the student has been added or not
	 */
	public boolean addTo(Student student) {
		if (studentcount >= students.length) {
			return false;
		}
		students[studentcount++] = student;
		return true;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < studentcount; i++) {
			s = s + students[i] + "\n"; 
		}
		return s;
	}
	
	/**
	 * Get the ith student in the group.
	 */
	public Student getStudent(int i) {
		if (i >= studentcount) {
			return null;
		}
		return students[i];
	} 
	
	public int getSize() {
		return students.length;
	}
	
	public int getFilled() {
		return studentcount;
	}
}
