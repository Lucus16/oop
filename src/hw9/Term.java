package hw9;

import java.util.Scanner;

public class Term implements Comparable<Term> {
	private double coef;
	private int exp;
	
	public Term() {
		coef = 0;
		exp = 0;
	}
	
	public Term(double coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}
	
	public Term(Term t) {
		coef = t.coef;
		exp = t.exp;
	}
	
	public double apply(double x) {
		return coef * Math.pow(x, exp);
	}
	
	public Term add(Term t) {
		assert(exp == t.exp);
		coef += t.coef;
		return this;
	}
	
	public Term sub(Term t) {
		assert(exp == t.exp);
		coef -= t.coef;
		return this;
	}
	
	public Term mul(Term t) {
		exp += t.exp;
		coef *= t.coef;
		return this;
	}
	
	public Term div(Term t) {
		assert(t.coef != 0);
		exp -= t.exp;
		coef /= t.coef;
		return this;
	}
	
	public Term neg() {
		coef = -coef;
		return this;
	}
	
	public Term pow(int p) {
		coef = Math.pow(coef, p);
		exp *= p;
		return this;
	}
	
	public double getCoef() {
		return coef;
	}
	
	public void setCoef(double coef) {
		this.coef = coef;
	}
	
	public int getExp() {
		return exp;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}

	/**
	 * A static method for converting scanner input into
	 * a term. The expected format is two numbers (coef, exp)
	 * separated by whitespaces. The coef should be either
	 * an integer or a decimal number. The exp is an integer.
	 * This format is specified by the regular expression named coef_patt
	 * @param s the scanner providing the input
	 * @return null if no term could be found, the found term otherwise 
	 */
	public static Term scanTerm (Scanner s) {
		String coef_patt = "\\-?\\d+(\\.\\d+)?";
		if (s.hasNext (coef_patt)) {
			String coef = s.next(coef_patt);
			int exp  = s.nextInt();
			return new Term (Double.parseDouble(coef), exp);
		} else {
			return null;
        }
	}

	@Override
	public int compareTo(Term t) {
		if (exp > t.exp) { return 1; }
		if (exp < t.exp) { return -1; }
		if (coef > t.coef) { return 1; }
		if (coef < t.coef) { return -1; }
		return 0;
	}

	public String toString() {
		return coef + "*x^" + exp; 
	}
}
