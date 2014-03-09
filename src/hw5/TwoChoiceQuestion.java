package hw5;

/**
 * A question with two choices.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class TwoChoiceQuestion extends Question {
	private final String question;
	private final String answerA;
	private final String answerB;
	private final int correctAnswer;
	
	/**
	 * Create a new question with two choices
	 * correctAnswer==0 means answerA is the correct answer.
	 * Otherwise answerB is the correct answer.
	 * @param question
	 * @param answerA
	 * @param answerB
	 * @param correctAnswer
	 */
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
