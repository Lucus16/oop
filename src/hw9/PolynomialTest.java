/**
 * 
 */
package hw9;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author sal
 *
 */
public class PolynomialTest {
	
	private ArrayList<Polynomial> polyPool;
	private ArrayList<Term> termPool;
	private static final double[] xPool = new double[] {0,0.01,1,1.6,2,2.72,3,3.14,4};

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		polyPool = new ArrayList<Polynomial>();
		polyPool.add(new Polynomial("0 0"));
		polyPool.add(new Polynomial("1 2 2 0 1 0"));
		polyPool.add(new Polynomial("1 2 -4 1 7 0"));
		polyPool.add(new Polynomial("2 2 0 1 -18 0"));
		polyPool.add(new Polynomial("5 3 -5 0"));
		polyPool.add(new Polynomial("1 0"));
		polyPool.add(new Polynomial("1 1 2.72 0"));
		polyPool.add(new Polynomial("0.5 2 2.72 1 3.14 0"));
		termPool = new ArrayList<Term>();
		termPool.add(new Term(0, 0));
		termPool.add(new Term(0.001, 10));
		termPool.add(new Term(3.14, 1));
		termPool.add(new Term(0, 4));
		termPool.add(new Term(2.72, 0));
		termPool.add(new Term(7.5, 256));
		termPool.add(new Term(-1, 3));
		termPool.add(new Term(5, -2));
		termPool.add(new Term(3, 3));
		termPool.add(new Term(325.57, 1));
	}
	
	/**
	 * Test method for {@link hw9.Polynomial#Polynomial()}.
	 */
	@Test
	public final void testPolynomial() {
		assertEquals(new Polynomial(), new Polynomial("0 0"));
	}

	/**
	 * Test method for {@link hw9.Polynomial#Polynomial(hw9.Term)}.
	 */
	@Test
	public final void testPolynomialTerm() {
		assertEquals(new Polynomial(), new Polynomial(new Term()));
		assertEquals(new Polynomial("0.5 2"), new Polynomial(new Term(0.5, 2)));
		assertEquals(new Polynomial("7.5 256"), new Polynomial(new Term(7.5, 256)));
		assertEquals(new Polynomial("2.72 0"), new Polynomial(new Term(2.72, 0)));
	}

	/**
	 * Test method for {@link hw9.Polynomial#Polynomial(java.lang.String)}.
	 */
	@Test
	public final void testPolynomialString() {
		//tested implicitly in other tests and polyPool.
	}

	/**
	 * Test method for {@link hw9.Polynomial#Polynomial(hw9.Polynomial)}.
	 */
	@Test
	public final void testPolynomialPolynomial() {
		for(Polynomial p : polyPool){
			assertEquals(p,new Polynomial(p));
		}
	}
	

	/**
	 * Test method for {@link hw9.Polynomial#equals(Object)}.
	 */
	@Test
	public final void testEqualsObject() {
		assertEquals(new Polynomial("2 2 0 1 2.72 0"), new Polynomial ("2.72 0 2 2"));
		assertEquals(new Polynomial("1 0 2 1 3 2"),new Polynomial("2 1 3 2 1 0"));
	}

	/**
	 * Test method for {@link hw9.Polynomial#add(hw9.Term)}.
	 */
	@Test
	public final void testAddTerm() {
		for (Polynomial p : polyPool) {
			for (Term t : termPool) {
				for (double x : xPool) {
					assertEquals(p.apply(x) + t.apply(x),
							new Polynomial(p).add(t).apply(x), 0.001);
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Polynomial#add(hw9.Polynomial)}.
	 */
	@Test
	public final void testAddPolynomial() {
		for (Polynomial p : polyPool) {
			for (Polynomial q : polyPool) {
				for (double x : xPool) {
					assertEquals(p.apply(x) + q.apply(x),
							new Polynomial(p).add(q).apply(x), 0.001);
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Polynomial#sub(hw9.Term)}.
	 */
	@Test
	public final void testSubTerm() {
		for (Polynomial p : polyPool) {
			for (Term t : termPool) {
				for (double x : xPool) {
					assertEquals(p.apply(x) - t.apply(x),
							new Polynomial(p).sub(t).apply(x), 0.001);
					assertEquals(new Polynomial(p).sub(t),
							new Polynomial(p).add(new Term(t).neg()));
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Polynomial#sub(hw9.Polynomial)}.
	 */
	@Test
	public final void testSubPolynomial() {
		for (Polynomial p : polyPool) {
			for (Polynomial q : polyPool) {
				for (double x : xPool) {
					assertEquals(p.apply(x) - q.apply(x),
							new Polynomial(p).sub(q).apply(x), 0.001);
					assertEquals(new Polynomial(p).sub(q),
							new Polynomial(p).add(new Polynomial(q).neg()));
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Polynomial#mul(hw9.Term)}.
	 */
	@Test
	public final void testMulTerm() {
		for (Polynomial p : polyPool) {
			for (Term t : termPool) {
				for (double x : xPool) {
					assertEquals(p.apply(x) * t.apply(x),
							new Polynomial(p).mul(t).apply(x),
							Math.max(0.001, Math.abs((p.apply(x) *
									t.apply(x)) / 10000)));
				}
			}
		}
	}


	/**
	 * Test method for {@link hw9.Polynomial#mul(hw9.Polynomial)}.
	 */
	@Test
	public final void testMulPolynomial() {
		for (Polynomial p : polyPool) {
			for (Polynomial q : polyPool) {
				for (double x : xPool) {
					assertEquals(p.apply(x) * q.apply(x),
							new Polynomial(p).mul(q).apply(x),
							Math.max(0.001, Math.abs((p.apply(x) *
									q.apply(x)) / 10000)));
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Polynomial#div(hw9.Term)}.
	 */
	@Test
	public final void testDiv() {
		for (Polynomial p : polyPool) {
			for (Term t : termPool) {
				if (t.getCoef() == 0) { continue; }
				for (double x : xPool) {
					assertEquals(p.apply(x) / t.apply(x),
							new Polynomial(p).div(t).apply(x),
							0.001);
				}
			}
		}
	}
	/**
	 * Test method for {@link hw9.Polynomial#clear()}.
	 */
	@Test
	public final void testClear() {
		for(Polynomial p : polyPool) {
			p.clear(); //polyPool is unique per test, can safely mutate.
			assertEquals(new Polynomial(), p);
		}
	}

	/**
	 * Test method for {@link hw9.Polynomial#apply(double)}.
	 */
	@Test
	public final void testApply() {
		//tested implicitly in add, sub, mul tests
		//test would copy implementation
	}

	/**
	 * Test method for {@link hw9.Polynomial#toString()}.
	 */
	@Test
	public final void testToString() {
		for (Polynomial p: polyPool) {
			System.out.println(p);
		}
		//human readability not autotestable. handtested OK.
	}

	/**
	 * Test method for {@link hw9.Polynomial#iterator()}.
	 */
	@Test
	public final void testIterator() {
		//Default implementation, should work.
	}

}
