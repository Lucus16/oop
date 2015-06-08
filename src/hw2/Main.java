package hw2;

import java.util.Scanner;

/**
 * 
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */

public class Main {
	private static Scanner scanner;
	
	public Main() {
		scanner = new Scanner(System.in);
		mainLoop();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void mainLoop() {
		boolean done = false;
		while (!done) {
			showMenu();
			print("Enter your choice (rcq): ");
			char c = getChar();
			switch (c) {
			case 'r':
				playLoop(new Hangman());
				break;
			case 'c':
				print("Enter the word you want to guess: ");
				playLoop(new Hangman(getString().toLowerCase()));
				break;
			case 'q':
				done = true;
				break;
			default:
				println("Invalid choice " + c + ".");
			}
		}
	}
	
	public void playLoop(Hangman h) {
		while (h.getState() == Hangman.State.PLAYING) {
			print(h);
			print("Enter a character to guess: ");
			char c = (char)((int)getChar() % 32 + 96);
			if (c < 'A' || c > 'Z' && c < 'a' || c > 'z') {
				println("Character out of range");
			} else {
				h.guessChar(c);
			}
		}
		print(h);
	}

	public void println(Object o) {
		System.out.println(o);
	}

	public void print(Object o) {
		System.out.print(o);
	}
	
	public char getChar() {
		return scanner.next().charAt(0);
	}
	
	public String getString() {
		return scanner.next();
	}
	
	public void showMenu() {
		println("Hangman main menu:");
		println("r: New game with a random word");
		println("c: New game with a chosen word");
		println("q: Quit game");
	}
}
