package hw5;

import java.util.Random;

import utils.Range;

/**
 * A multiple choice question.
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class MultipleChoiceQuestion extends Question {
	private final String question;
	private String[] answers;
	private int correctAnswer;
	
	/**
	 * Create a new multiple choice question.
	 * @param question
	 * @param answers
	 * @param correctAnswer
	 */
	public MultipleChoiceQuestion(String question, String[] answers, int correctAnswer) {
		assert answers.length <= 26;
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(question);
		for (int i : new Range(answers.length)) {
			sb.append("\n " + (char)('A' + i) + ". " + answers[i]);
		}
		return sb.toString();
	}

	@Override
	public String correctAnswer() {
		return "" + (char)('A' + correctAnswer);
	}

	@Override
	public Question duplicate() {
		Random r = new Random(System.currentTimeMillis());
		String[] newAnswers = answers.clone();
		int newCorrectAnswer = correctAnswer;
		for (int i : new Range(newAnswers.length)) {
			int j = r.nextInt(newAnswers.length - i) + i;
			String tmp = newAnswers[i];
			newAnswers[i] = newAnswers[j];
			newAnswers[j] = tmp;
			if (newCorrectAnswer == i) {
				newCorrectAnswer = j;
			} else if (newCorrectAnswer == j) {
				newCorrectAnswer = i;
			}
		}
		return new MultipleChoiceQuestion(question, newAnswers, newCorrectAnswer);
	}
}
