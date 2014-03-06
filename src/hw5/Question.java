package hw5;

public abstract class Question {
	public abstract String toString();
	public boolean isCorrect(String answer) {
		return correctAnswer().equalsIgnoreCase(answer);
	}
	public abstract String correctAnswer();
	public abstract Question duplicate();
}
