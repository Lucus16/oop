package utils;

import java.util.Scanner;

/**
 * More easily usable input functions.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class Input {
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Ask the user for a choice between n items. Assumes the items have already been listed or are otherwise known to the user.
	 * @param n
	 * @return the chosen number
	 */
	public static int getChoice(int n) {
		while (true) {
			System.out.print("Enter a choice (1-" + n + "): ");
			int choice = scanner.nextInt();
			if (choice > 0 && choice <= n) {
				return choice;
			}
			System.out.println("Invalid choice: " + choice);
		}
	}
}
