package hw5;

/**
 * A question for the quiz.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public abstract class Question {
	/**
	 * String representation for the question including information about
	 * the form of the answer.
	 */
	public abstract String toString();
	
	/**
	 * Return if the given answer is the correct one.
	 * @param answer
	 * @return
	 */
	public boolean isCorrect(String answer) {
		return correctAnswer().equalsIgnoreCase(answer);
	}
	
	/**
	 * @return the correct answer.
	 */
	public abstract String correctAnswer();
	
	/**
	 * Return a duplicate of the question, with possible answers shuffled
	 * in case of a multiple choice question.
	 * @return
	 */
	public abstract Question duplicate();
}
