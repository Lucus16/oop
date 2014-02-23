package hw3;

public class Main {

	public static void main(String[] args) {
		TekenLoipe ASCII = new ASCIILoipe();
		ASCII.setLoipe(new Loipe(
				"srrsslssllrsrrrslssslsllsslrrsss"));
		ASCII.teken();
		TekenLoipe GUI = new LoipePlaatje();
		GUI.setLoipe(new Loipe(
				"srrsslssllrsrrrslssslsllsslrrsss"));
		GUI.teken();
	}
}
