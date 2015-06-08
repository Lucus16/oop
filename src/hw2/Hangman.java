package hw2;

/**
 * 
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */

public class Hangman {
	public enum State {
		PLAYING, GUESSER_WON, GUESSER_LOST
	}
	
	private String target;
	private StringBuilder wordState;
	private State state;
	private boolean[] guessed;
	private WordReader wr;
	private int guessesLeft;
	private int guessedChars;
	private static final int INITIAL_GUESSES = 7;
	
	public Hangman() {
		this(null);
	}
	
	public Hangman(String s) {
		wr = new WordReader("words.txt");
		initGame(s);
	}
	
	public void initGame() {
		initGame(null);
	}
	
	public void initGame(String s) {
		if (s == null) {
			s = wr.giveWord();
		}
		target = s;
		wordState = new StringBuilder();
		for (int i = 0; i < target.length(); i++) {
			wordState.append('.');
		}
		state = State.PLAYING;
		guessed = new boolean[26];
		for (int i = 0; i < 26; i++) {
			guessed[i] = false;
		}
		guessedChars = 0;
		guessesLeft = INITIAL_GUESSES;
	}
	
	/**
	 * Updates the state to reflect a new character having been guessed.
	 * @return true if the character had not been guessed before and the game has not ended.
	 */
	public boolean guessChar(char c) {
		if (state != State.PLAYING) {
			return false;
		}
		int charnum = (int)c % 32 - 1;
		if (guessed[charnum]) {
			return false;
		}
		guessed[charnum] = true;
		int count = 0;
		for (int i = 0; i < target.length(); i++) {
			if (target.charAt(i) == c) {
				count++;
				wordState.setCharAt(i, c);
			}
		}
		if (count == 0) {
			if (state == State.PLAYING) {
				guessesLeft--;
			}
			if (guessesLeft == 0) {
				state = State.GUESSER_LOST;
			}
		}
		guessedChars += count;
		if (guessedChars == target.length()) {
			state = State.GUESSER_WON;
		}
		return true;
	}
	
	public State getState() {
		return state;
	}
	
	public String getWordState() {
		return wordState.toString();
	}
	
	public String getGuessedChars() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (guessed[i]) {
				sb.append((char)(i + 97));
			} else {
				sb.append('.');
			}
		}
		return sb.toString();
	}
	
	public String toString() {
		switch (state) {
		case PLAYING:
			return getGallowState() + "What you have found so far: " + getWordState() + "\n" +
				"Letters you have guessed so far: " + getGuessedChars() + "\n";
		case GUESSER_WON:
			return getGallowState() + "You have won. You found: " + target + "\n";
		default: //case GUESSER_LOST:
			return getGallowState() + "You have lost. The word was: " + target + "\n";
		}
	}

	public String getGallowState() {
		StringBuilder sb = new StringBuilder();
		switch (guessesLeft) {
		case 0:
			sb.append("  ________\n");
			sb.append("  |      |\n");
			sb.append("  |     _|_\n");
			sb.append("  |    /   \\\n");
			sb.append("  |   | x x |\n");
			sb.append("  |    \\_o_/\n");
			sb.append("  |     /|\\\n");
			sb.append("  |    / | \\\n");
			sb.append("  |      |\n");
			sb.append("  |     / \\\n");
			sb.append("  |    /   \\\n");
			sb.append("__|_____________\n");
			break;
		case 1:
			sb.append("  ________\n");
			sb.append("  |     _)_\n");
			sb.append("  |    /   \\\n");
			sb.append("  |   |     |\n");
			sb.append("  |    \\___/\n");
			sb.append("  |     /|\\\n");
			sb.append("  |    / | \\\n");
			sb.append("  |      |\n");
			sb.append("  |     / \\\n");
			sb.append("  |   _/___\\_\n");
			sb.append("  |   |     |\n");
			sb.append("__|___|_____|___\n");
			break;
		case 2:
			sb.append("  ________\n");
			sb.append("  |     _)_\n");
			sb.append("  |    /   \\\n");
			sb.append("  |   |     |\n");
			sb.append("  |    \\___/\n");
			sb.append("  |     /|\\\n");
			sb.append("  |    / | \\\n");
			sb.append("  |      |\n");
			sb.append("  |\n");
			sb.append("  |   _______\n");
			sb.append("  |   |     |\n");
			sb.append("__|___|_____|___\n");
			break;
		case 3:
			sb.append("  ________\n");
			sb.append("  |     _)_\n");
			sb.append("  |    /   \\\n");
			sb.append("  |   |     |\n");
			sb.append("  |    \\___/\n");
			sb.append("  |      |\n");
			sb.append("  |      |\n");
			sb.append("  |      |\n");
			sb.append("  |\n");
			sb.append("  |   _______\n");
			sb.append("  |   |     |\n");
			sb.append("__|___|_____|___\n");
			break;
		case 4:
			sb.append("  ________\n");
			sb.append("  |     _)_\n");
			sb.append("  |    /   \\\n");
			sb.append("  |   |     |\n");
			sb.append("  |    \\___/\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |   _______\n");
			sb.append("  |   |     |\n");
			sb.append("__|___|_____|___\n");
			break;
		case 5:
			sb.append("  ________\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |\n");
			sb.append("  |   _______\n");
			sb.append("  |   |     |\n");
			sb.append("__|___|_____|___\n");
			break;
		case 6:
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("      _______\n");
			sb.append("      |     |\n");
			sb.append("______|_____|___\n");
			break;
		default: //case 7:
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");
			sb.append("________________\n");
			break;
		}
		sb.append("Guesses left: " + guessesLeft + "/" + INITIAL_GUESSES + "\n");
		return sb.toString();
	}
}
