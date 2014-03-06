package hw5;

public class OpenQuestion extends Question {
	private final String question;
	private final String answer;
	
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
