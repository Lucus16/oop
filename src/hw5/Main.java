package hw5;

public class Main {
	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		quiz.addQuestion(new OpenQuestion("What is the first letter of the alphabet?", "A"));
		quiz.addQuestion(new MultipleChoiceQuestion("What is the last letter of the alphabet?",
				new String[] {"A", "Q", "X", "Z"}, 3));
		quiz.addQuestion(new TwoChoiceQuestion("Is 'D' a letter in the alphabet?", "Yes", "No", 0));
		quiz.takeQuiz();
	}
}
