package utils;

import java.util.Scanner;

public class Input {
	private static Scanner scanner = new Scanner(System.in);
	
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
