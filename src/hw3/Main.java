package hw3;

/**
 * Single-static-method class Main, contains main() method.
 * As "Main" class, also contains the required output in comments, below 
 * the actual code.
 * @author Sal Wolffs(s4064542)
 * @author Lars Jellema(s4388747)
 */
public class Main {
	/**
	 * Sets up both possible graphics interfaces, gives them a Loipe to draw,
	 * then calls the method to draw a representation on stdout in ASCII
	 * or in a seperate window with normal graphics.
	 * @param args ignored.
	 */
	public static void main(String[] args) {
		TekenLoipe ASCII = new ASCIILoipe();
		ASCII.setLoipe(new Loipe(
				"sssssrrslslsrrssssrrslslsslssssl" + 
				"sssssrsssrrssllssrrssllssrrssllssssl" +
				"sssssrrssslssrrssllssssl" +
				"sssssrrssslssrrssllssssl" +
				"sssssrssssrsssrsssrssrssrsssrssllsssssl"));
		ASCII.teken();
		TekenLoipe GUI = new LoipePlaatje();
		GUI.setLoipe(new Loipe(
				"sssssrrslslsrrssssrrslslsslssssl" + 
				"sssssrsssrrssllssrrssllssrrssllssssl" +
				"sssssrrssslssrrssllssssl" +
				"sssssrrssslssrrssllssssl" +
				"sssssrssssrsssrsssrssrssrsssrssllsssssl"));
		GUI.teken();
	}
	/*
	 * The output for the path (output given path follows own path)
		"sssssrrslslsrrssssrrslslsslssssl" + 
		"sssssrsssrrssllssrrssllssrrssllssssl" +
		"sssssrrssslssrrssllssssl" +
		"sssssrrssslssrrssllssssl" +
		"sssssrssssrsssrsssrssrssrsssrssllsssssl"
		(note crossing in O) :
/\ /\ /---\ /\    /\    /----\  
|| || |/--/ ||    ||    |/--\|  
|\-/| |\--\ ||    ||    ||  ||  
|/-\| |/--/ ||    ||    ||  ||  
|| || |\--\ |\--\ |\--\ |\--+/  
|| \/ |/--/ |/--/ |/--/ |/--/   
 \----/\----/\----/\----/\-----/

	 */
	/* The output for the given path "srrsslssllrsrrrslssslsllsslrrsss". 
	 * Ugly, narrow, tall, but there's no fixing that within specification. 
	 * Alternative drawing method provides a sort of fix in the form of 
	 * tekenWide, but that's not much better.
	/\ /\
	||/+/
	|||\\
	|\+-/
	|/+-\
	\/\-/
	*/
	/* Scribble
	 * "ssllssssllsrssrsllssssllsls"
	 * "sssssrrslslsrrssssrrslslsslssssl" //H
	 * "sssssrsssrrssllssrrssllssrrssllssssl" //E
	 * "sssssrrssslssrrssllssssl" //L
	 * "sssssrssssrsssrsssrssrssrsssrssllsssssl" //O
	 * "ssrsssrsrrssssrrsrsssrsrrssssslssssl" //alt H, with crossings
	 */
}
