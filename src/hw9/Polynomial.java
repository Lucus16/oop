package hw9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Polynomial implements Iterable<Term> {
	private ArrayList<Term> terms;
	
	public Polynomial() {
		terms = new ArrayList<Term>();
	}
	
	public Polynomial(Term t) {
		terms = new ArrayList<Term>();
		terms.add(t);
	}
	
	public Polynomial(Polynomial p) {
		terms = new ArrayList<Term>();
		for (Term t : p) {
			terms.add(t);
		}
	}

	/**
	 * A Constructor creating a polynomial from the argument string.
	 * @param s a String representing a list of terms which is
	 * converted to a scanner and passed to scanTerm for reading 
	 * each individual term
	 */
	public Polynomial(String s) {
		terms = new ArrayList<Term>();
		Scanner scan = new Scanner(s);

		for (Term t = Term.scanTerm(scan); t != null; t = Term.scanTerm(scan)) {
			terms.add(t);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Term t : terms) {
			if (sb.length() != 0) {
				sb.append(" + ");
			}
			sb.append(t);
		}
		return sb.toString();
	}

	@Override
	public Iterator<Term> iterator() {
		return terms.iterator();
	}
}
