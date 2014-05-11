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
	
	public Polynomial add(Polynomial p) {
		for (Term t : p.terms) {
			add(t);
		}
		return this;
	}
	
	public Polynomial sub(Term t) {
		return add(new Term(t).neg());
	}
	
	public Polynomial sub(Polynomial p) {
		for (Term t : p.terms) {
			sub(t);
		}
		return this;
	}

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
	
	public Polynomial mul(Polynomial p) {
		Polynomial op = new Polynomial(this);
		clear();
		for (Term t : p.terms) {
			add(new Polynomial(op).mul(t));
		}
		return this;
	}

	public Polynomial div(Term t) {
		assert t.getCoef() != 0;
		for (Term x : terms) {
			x.div(t);
		}
		return this;
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
			terms.add(t);
		}
	}
	
	public void clear() {
		terms = new ArrayList<Term>();
	}
	
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
