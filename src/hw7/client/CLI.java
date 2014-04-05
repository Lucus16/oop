package hw7.client;

import java.util.Scanner;

import hw7.server.ServerFront;
import hw7.server.ServerInit;

public class CLI extends FrontEnd {
	private Scanner scanner;
	
	public CLI(ServerInit serverInit) {
		serverFront = serverInit.getServerFront();
		scanner = new Scanner(System.in);
		
	}
	
	private void loginMenu() {
		String[] choices = {"Login", "Register", "Browse"};
		showMenu("Login menu", choices);
		int choice = getChoice(choices.length);
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
		}
	}
	
	private void browseMenu() {
		// TODO Auto-generated method stub
		
	}

	private void registerPrompt() {
		// TODO Auto-generated method stub
		
	}

	private void loginPrompt() {
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
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
		return choice;
	}
}
