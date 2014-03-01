package hw4;

import hw4.dutch.Bot;
import hw4.dutch.Kleur;
import hw4.players.*;
import io.Input;

/**
 * 
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.mainLoop();
	}

	public void mainLoop() {
		boolean done = false;
		while (!done) {
			System.out.println("Connect Four Main Menu:");
			System.out.println("1. Play");
			System.out.println("2. Help");
			System.out.println("3. Quit");
			int choice = Input.getChoice(3);
			switch (choice) {
			case 1:
				makeGame();
				break;
			case 2:
				showHelp();
				break;
			case 3:
				done = true;
				break;
			}
		}
	}
	
	private void makeGame() {
		Game game = new Game(getPlayer(Color.YELLOW), getPlayer(Color.RED));
		game.play();
	}
	
	private void showHelp() {
		System.out.println("This is a game of connect four. It is played by dropping pieces");
		System.out.println("into columns on the board. The first player to create a row of");
		System.out.println("four pieces horizontally, vertically or diagonally, wins.");
		System.out.println("");
	}
	
	private Player getPlayer(Color c) {
		System.out.println("Please choose player " + c);
		System.out.println("1. Human");
		System.out.println("2. Computer");
		System.out.println("3. Dutch Bot");
		int choice = Input.getChoice(3);
		switch (choice) {
		case 1:
			return new Human();
		case 2:
			return new SimpleAI();
		case 3:
			return new SpelerWrapper(new Bot("DutchBot" + c, Kleur.fromColor(c)));
		default:
			return null;
		}
	}
}
