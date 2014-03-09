package hw5;

/**
 * An open question where any answer is valid.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class OpenQuestion extends Question {
	private final String question;
	private final String answer;
	
	/**
	 * Create a new open question.
	 * @param question
	 * @param answer
	 */
	public OpenQuestion(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	@Override
	public String toString() {
		return question;
	}

	@Override
	public String correctAnswer() {
		return answer;
	}

	@Override
	public Question duplicate() {
		return this;
	}

}
