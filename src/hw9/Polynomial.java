package hw9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Polynomial class stores a polynomial with an arbitrary number of terms
 * and defines arithmatic on them and polynomials with terms.
 * Most methods return the class itself for easy method chaining.
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class Polynomial implements Iterable<Term> {
	private ArrayList<Term> terms;
	
	/**
	 * Zero constructor
	 */
	public Polynomial() {
		terms = new ArrayList<Term>();
	}
	
	/**
	 * Single term constructor
	 * @param t
	 */
	public Polynomial(Term t) {
		terms = new ArrayList<Term>();
		terms.add(t);
	}
	
	/**
	 * A Constructor creating a polynomial from the argument string.
	 * @param s a String representing a list of terms which is
	 * converted to a scanner and passed to scanTerm for reading 
	 * each individual term
	 */
	public Polynomial(String s) {
		clear();
		Scanner scan = new Scanner(s);
		for (Term t = Term.scanTerm(scan); t != null; t = Term.scanTerm(scan)) {
			add(t);
		}
	}
	
	/**
	 * Copy constructor
	 * @param p
	 */
	public Polynomial(Polynomial p) {
		terms = new ArrayList<Term>();
		for (Term t : p) {
			terms.add(t);
		}
	}
	
	public boolean equals(Object o){
		if (o.getClass() != getClass()){
			return false;
		}
		Polynomial p = (Polynomial) o;
		if (terms.size() != p.terms.size()) { return false; }
		for (int i = 0; i < terms.size(); i++) {
			if (!terms.get(i).equals(p.terms.get(i))) {
				return false;
			}
		}
		return true; //no difference found.
	}
	
	/**
	 * Add a term to the polynomial, keeping the terms list sorted
	 * and removing terms that have coefficient zero
	 * @param t
	 * @return
	 */
	public Polynomial add(Term t) {
		for (int i = 0; i < terms.size(); i++) {
			if (terms.get(i).getExp() == t.getExp()) {
				terms.get(i).add(t);
				if (terms.get(i).getCoef() == 0) {
					terms.remove(i);
				}
				return this;
			} else if (terms.get(i).getExp() > t.getExp()) {
				terms.add(i, t);
				return this;
			}
		}
		terms.add(t);
		return this;
	}
	
	/**
	 * Add another polynomial to this one
	 * @param p
	 * @return
	 */
	public Polynomial add(Polynomial p) {
		for (Term t : p.terms) {
			add(t);
		}
		return this;
	}
	
	/**
	 * Subtract a term from the polynomial
	 * @param t
	 * @return
	 */
	public Polynomial sub(Term t) {
		return add(new Term(t).neg());
	}
	
	/**
	 * Subtract another polynomial from this one
	 * @param p
	 * @return
	 */
	public Polynomial sub(Polynomial p) {
		for (Term t : p.terms) {
			sub(t);
		}
		return this;
	}

	/**
	 * Multiply the polynomial by a term
	 * @param t
	 * @return
	 */
	public Polynomial mul(Term t) {
		if (t.getCoef() == 0) {
			clear();
		} else {
			for (Term x : terms) {
				x.mul(t);
			}
		}
		return this;
	}
	
	/**
	 * Multiply this polynomial by another one
	 * @param p
	 * @return
	 */
	public Polynomial mul(Polynomial p) {
		Polynomial op = new Polynomial(this);
		clear();
		for (Term t : p.terms) {
			add(new Polynomial(op).mul(t));
		}
		return this;
	}
	
	/**
	 * Divide this polynomial by a term
	 * @param t
	 * @return
	 */
	public Polynomial div(Term t) {
		assert t.getCoef() != 0;
		for (Term x : terms) {
			x.div(t);
		}
		return this;
	}


	
	/**
	 * Clear the polynomial, making it the zero polynomial
	 */
	public void clear() {
		terms = new ArrayList<Term>();
	}
	
	/**
	 * Apply value x to the polynomial
	 * @param x
	 * @return
	 */
	public double apply(double x) {
		double r = 0;
		for (Term t : terms) {
			r += t.apply(x);
		}
		return r;
	}
	
	public String toString() {
		if (terms.size() == 0) {
			return "0";
		}
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
