package hw2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Reads words from a file and can choose a word at random.
 * @author Pieter Koopman
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class WordReader
{
    private List<String> words = new ArrayList<String> ();
    Random rand = new Random ();
    private int wordCount = 0;     //number of read words
    /**
     * Constructor determines the number of words read from the file.
     * Read words are stored in an arrayList.
     * IOExceptions from reading are caught and displayed.
     * @param filename: filename for the file with words
     */
    public WordReader( String filename )
    {
        try
        {
            FileReader file = new FileReader( filename );
            Scanner scan = new Scanner( file );
            for ( ; scan.hasNext("\\S+"); wordCount += 1)
                words.add( scan.next("\\S+").toLowerCase() );
            scan.close();
            file.close();
        }
        catch ( IOException ioe )
        {
            System.out.println("Oeps, er ging iets mis bij lezen van de file " + filename + ioe.getMessage() );
        }
    }

    /**
     * @return the number of words read.
     */
    public int getWordCount()
    {
        return wordCount;
    }

    /**
     * Gives a pseudo random word from the list of saved words
     * @return a random word from the file
     */
    public String giveWord()
    {
        if ( wordCount > 0 )
            return words.get( rand.nextInt( wordCount ));
        else
            return "";
    }
}
