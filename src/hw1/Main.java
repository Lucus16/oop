package hw1;

import java.util.Scanner;

/**
 * Main class for demonstrating Group and Student classes.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class Main {
	Group abel;
	Scanner scanner;

	/**
	 * Execute actual main in a constructor for some weird reason.
	 */
	public static void main(String[] args) {
		Main master = new Main();
	}
	
	/**
	 * Prompts user to create and populate a group, then prints the group once,
	 * then asks for changes and reprints the group after every change.
	 */
	public Main() {
		scanner = new Scanner(System.in);
		System.out.println("Hoe groot is de groep die je wil invoeren?");
		abel = new Group(scanner.nextInt());
		if (abel.getSize() <= 0) {
			System.out.println("De lege groep is leeg (en geen groep).");
			scanner.close();
			return;
		}
		populategroup(abel);
		listGroup();
		Integer requested;
		do {
			System.out.println("Geef een lidnummer op om de naam van " +
							"een student te veranderen. Geef een negatief " +
							"getal op om af te sluiten.");
				requested = new Integer(scanner.nextInt());
			if (requested < 0) break;
			editStudent(abel, requested, scanner);
			listGroup();
		} while (true);
		scanner.close();
		return;
	}
	
	/**
	 * Add a student to the group with name and student number received from system.in
	 * @param sylow: The group to add the student to
	 */
	private void addStudent(Group sylow) {
		sylow.addTo(new Student(scanner.next(), scanner.next(), scanner.nextInt()));
		return;
	}
	
	/**
	 * Populate the group by repeatedly asking for a name and student number
	 * @param dihedral: Group to be populated
	 */
	private void populategroup(Group dihedral) {
		System.out.println("Geef voornaam, achternaaam en studentnummer op voor " +
				((Integer)dihedral.getSize()).toString() + " studenten.");
		addStudent(dihedral);
		int space;
		while ((space = (dihedral.getSize() - dihedral.getFilled())) > 0) {
			System.out.println("Nog " + ((Integer)space).toString() + 
						" studenten. Voer gegevens in van de volgende student:");
			addStudent(dihedral);
		}
		System.out.println("De groep is gevuld.");
	}
	
	/**
	 * Request a new first and last name for a student in the group.
	 * @param dieder: Group the student is in
	 * @param nummer: Number of the student that will be changed
	 */
	private void editStudent(Group dieder, int nummer, Scanner scanner) {
		Student schmuck = dieder.getStudent(nummer);
		if (schmuck == null) {
			System.out.println("Zo veel studenten zijn er niet.");
			return;
		}
		System.out.println("Geef een nieuwe voornaam en achternaam op voor " +
							schmuck.toString());
		dieder.getStudent(nummer).setName(scanner.next(), scanner.next());
	}
	
	private void listGroup() {
		System.out.println("De groep bevat nu:");
		System.out.print(abel);
	}
}
