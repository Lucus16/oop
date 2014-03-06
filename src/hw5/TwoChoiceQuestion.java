package hw5;

public class TwoChoiceQuestion extends Question {
	private final String question;
	private final String answerA;
	private final String answerB;
	private final int correctAnswer;
	
	public TwoChoiceQuestion(String question, String answerA, String answerB, int correctAnswer) {
		this.question = question;
		this.answerA = answerA;
		this.answerB = answerB;
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public String toString() {
		return answerA + " or " + answerB + ": " + question;
	}

	@Override
	public String correctAnswer() {
		return (correctAnswer == 0 ? answerA : answerB);
	}

	@Override
	public Question duplicate() {
		return this;
	}

}
