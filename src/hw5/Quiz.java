package hw5;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
	List<Question> questions;
	
	public Quiz() {
		questions = new LinkedList<Question>();
	}
	
	public void addQuestion(Question q) {
		questions.add(q);
	}
	
	public void takeQuiz() {
		Scanner scanner = new Scanner(System.in);
		List<Question> questions = this.questions;
		List<Question> wrong = new LinkedList<Question>();
		while (!questions.isEmpty()) {
			Collections.shuffle(questions);
			for (Question q : questions) {
				System.out.println(q);
				String answer = scanner.nextLine();
				if (q.isCorrect(answer)) {
					System.out.println("That answer is correct!");
				} else {
					System.out.println("That was not correct.\nThe correct answer is: " + q.correctAnswer());
					wrong.add(q.duplicate());
				}
			}
			questions = wrong;
			wrong = new LinkedList<Question>();
		}
		scanner.close();
		System.out.println("You've answered all questions correctly.");
	}
}
