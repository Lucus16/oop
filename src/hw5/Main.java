package hw5;

public class Main {
	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		quiz.addQuestion(new OpenQuestion("What is the first letter of the alphabet?", "A"));
		quiz.addQuestion(new MultipleChoiceQuestion("What is the last letter of the alphabet?",
				new String[] {"A", "Q", "X", "Z"}, 3));
		quiz.addQuestion(new TwoChoiceQuestion("Is 'D' a letter in the alphabet?", "Yes", "No", 0));
		quiz.addQuestion(new OpenQuestion("Wat zegt de kikker als hij van de trap af valt?", "kwak"));
		quiz.addQuestion(new OpenQuestion("Wat zegt de kip als hij tegen de muur aan loopt?", "tok"));
		quiz.addQuestion(new OpenQuestion("Wat is de 'agrarische' naam van gassen die uit je maag ontsnappen?", "boer"));
		quiz.addQuestion(new OpenQuestion("What is the name of pokemon 1?", "Bulbasaur"));
		quiz.addQuestion(new OpenQuestion("Hoe heet de STEEL-type gymleader in de Johto regio?", "Jasmine"));
		quiz.addQuestion(new OpenQuestion("Hoe heet the gym-badge die Misty aan Ash geeft?", "Cascade badge"));
		quiz.addQuestion(new OpenQuestion("What is the nickname of the creator of the popular Minecraft game?", "notch"));
		quiz.addQuestion(new OpenQuestion("What is the word smurf in french?", "Schtroumpf"));
		quiz.addQuestion(new OpenQuestion("How do you feel today", "good"));
		quiz.addQuestion(new OpenQuestion("Welke nederlandse zanger heeft een naam die lijkt te bestaan uit twee talen?", "Frans Duijts"));
		quiz.addQuestion(new TwoChoiceQuestion("Can you answer this question correctly?", "Yes", "No", 0));
		quiz.addQuestion(new OpenQuestion("What's the name of the god of balance in RuneScape?", "Guthix"));
		quiz.addQuestion(new OpenQuestion("What is the name of the daedric prince of madness in Skyrim?", "Sheogorath"));
		quiz.addQuestion(new OpenQuestion("What is the name of the cat that leaves a rainbow behind as it flies through space?", "Nyan Cat"));
		quiz.addQuestion(new OpenQuestion("How many bottles of beer on the wall?", "99"));
		quiz.takeQuiz();
	}
}
