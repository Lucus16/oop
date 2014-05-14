package hw7.client;

import java.util.Scanner;

import hw7.server.ServerFront;
import hw7.server.ServerInit;

/**
 * 
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class CLI extends FrontEnd {
	private Scanner scanner;
	
	public CLI(ServerInit serverInit) {
		serverFront = serverInit.getServerFront();
		scanner = new Scanner(System.in);
	}
	
	public int run() {
		return loginMenu();
	}
	
	private int loginMenu() {
		while (true) {
			int choice = getMenuChoice("Login menu", new String[]
					{"Login", "Register", "Browse", "Exit"});
			switch (choice) {
			case 1:
				loginPrompt();
				break;
			case 2:
				registerPrompt();
				break;
			case 3:
				browseMenu();
				break;
			case 4:
				return 0;
			}
		}
	}

	private void browseMenu() {
		// TODO Auto-generated method stub
		
	}

	private void registerPrompt() {
		System.out.print("Desired username: ");
		String username = scanner.nextLine();
		System.out.print("Email address: ");
		String email = scanner.nextLine();
		int r = register(username, email);
		if (r != 0) {
			System.out.println("Registration failed.");
		}
	}

	private void loginPrompt() {
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		int r = login(username, password);
		if (r != 0) {
			System.out.println("Login failed.");
		}
	}
	
	private int getMenuChoice(String title, String[] choices) {
		showMenu(title, choices);
		return getChoice(choices.length);
	}

	private void showMenu(String title, String[] choices) {
		System.out.println(title);
		int i = 1;
		for (String choice : choices) {
			System.out.println("" + (i++) + ". " + choice);
		}
	}
	
	private int getChoice(int max) {
		int choice = -1;
		while (choice < 1 || choice > max) {
			System.out.print("Enter your choice (1-" + max + "): ");
			choice = scanner.nextInt();
		}
		scanner.nextLine();
		return choice;
	}
}
