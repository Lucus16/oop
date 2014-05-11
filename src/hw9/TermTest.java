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
public class TermTest {
	
	private ArrayList<Term> termPool;
	private static double[] xPool = new double[]{0,0.01,1,1.6,2,2.72,3,3.14,4};
	
	@Before
	public final void setup(){
		termPool = new ArrayList<Term>();
		termPool.add(new Term(0,0));
		termPool.add(new Term(0.001,10));
		termPool.add(new Term(3.14,1));
		termPool.add(new Term(0,4));
		termPool.add(new Term(2.72,0));
		termPool.add(new Term(7.5,256));
		termPool.add(new Term(-1, 3));
		termPool.add(new Term(5,-2));
		termPool.add(new Term(3,3));
		termPool.add(new Term(325.57,1));
	}
	
	/**
	 * Test method for {@link hw9.Term#Term()}.
	 */
	@Test
	public final void testTerm() {
		assertEquals(new Term(),new Term(0,0));
	}

	/**
	 * Test method for {@link hw9.Term#Term(double, int)}.
	 */
	@Test
	public final void testTermDoubleInt() {
		//tested indirectly in other tests.
	}

	/**
	 * Test method for {@link hw9.Term#Term(hw9.Term)}.
	 */
	@Test
	public final void testTermTerm() {
		for (Term t : termPool){
			assertEquals(t,new Term(t));
		}
	}

	/**
	 * Test method for {@link hw9.Term#apply(double)}.
	 */
	@Test
	public final void testApply() {
		for (Term t : termPool){
			for (double x : xPool){
				assertEquals("Inaccurate evaluation on " + t.toString(),
						t.getCoef()*Math.pow(x,t.getExp()),t.apply(x),0.001);
			}
		}
	}

	/**
	 * Test method for {@link hw9.Term#add(hw9.Term)}.
	 */
	@Test
	public final void testAdd() {
		for (Term t : termPool){
			for (Term u : termPool){
				if (t.getExp() == u.getExp()){
					for (double x : xPool){
						assertEquals("Evaluation should preserve addition",
							t.apply(x)+u.apply(x),
							t.add(u).apply(x),
							0.001);
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Term#sub(hw9.Term)}.
	 */
	@Test
	public final void testSub() {
		for (Term t : termPool){
			for (Term u : termPool){
				if (t.getExp() == u.getExp()){
					for (double x : xPool){
						assertEquals("Evaluation should preserve subtraction",
							t.apply(x)-u.apply(x),
							new Term(t).sub(u).apply(x),
							0.001);
						assertEquals("t+(-1)u==t-u",
								new Term(t).sub(u),
								new Term(t).add(u.mul(new Term(-1,0))));
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Term#mul(hw9.Term)}.
	 */
	@Test
	public final void testMul() {
		for (Term t : termPool){
			for (Term u : termPool){
				for (double x : xPool){
					if (x == 0 && (u.getExp() < 0 || t.getExp() < 0)){
						continue; //cannot properly evaluate 0^-1
					}
					assertEquals(
							"Evaluation should preserve multiplication for " +
							t.toString() + " and " + u.toString() + " at x = " +
							Double.toString(x) + " with intermediate results " +
							Double.toString(t.apply(x)) + " and " + 
							Double.toString(u.apply(x)),
						(t.apply(x))*(u.apply(x)),
						new Term(t).mul(u).apply(x),
						Math.max(0.001, 
								Math.abs((t.apply(x)*u.apply(x))/10000)));
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Term#div(hw9.Term)}.
	 */
	@Test
	public final void testDiv() {
		for (Term t : termPool){
			for (Term u : termPool){
				for (double x : xPool){
					if (u.apply(x) == 0){
						continue; //DIV0 on the expected value.
					}
					if (x == 0 && (u.getExp() < 0 || t.getExp() < 0)){
						continue; //cannot properly evaluate 0^-1
					}
					assertEquals(
							"Evaluation should preserve division for " +
							t.toString() + " and " + u.toString() + " at x = " +
							Double.toString(x) + " with intermediate results " +
							Double.toString(t.apply(x)) + " and " + 
							Double.toString(u.apply(x)),
						t.apply(x)/u.apply(x),
						new Term(t).div(u).apply(x),
						Math.max(0.001, 
								Math.abs((t.apply(x)/u.apply(x))/10000)));
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Term#neg()}.
	 */
	@Test
	public final void testNeg() {
		for (Term t : termPool){
			assertEquals("-t should be (-1*x^0)*t",t.mul(new Term(-1,0)),t.neg());
		}
	}

	/**
	 * Test method for {@link hw9.Term#pow(int)}.
	 */
	@Test
	public final void testPow() {
		for (Term t : termPool){
			for (int i = 0; i < 10; ++i){
				for (double x : xPool){
					assertEquals("Evaluation should preserve exponentiation",
							Math.pow(t.apply(x),i),
							new Term(t).pow(i).apply(x),
							Math.max(0.001, 
									Math.abs(Math.pow(t.apply(x),i)/10000)));
				}
			}
		}
	}

	/**
	 * Test method for {@link hw9.Term#getCoef()}.
	 */
	@Test
	public final void testGetCoef() {
		//trivial
	}

	/**
	 * Test method for {@link hw9.Term#setCoef(double)}.
	 */
	@Test
	public final void testSetCoef() {
		//trivial
	}

	/**
	 * Test method for {@link hw9.Term#getExp()}.
	 */
	@Test
	public final void testGetExp() {
		//trivial
	}

	/**
	 * Test method for {@link hw9.Term#setExp(int)}.
	 */
	@Test
	public final void testSetExp() {
		//trivial
	}

	/**
	 * Test method for {@link hw9.Term#scanTerm(java.util.Scanner)}.
	 */
	@Test
	public final void testScanTerm() {
		//given by assignment
	}

	/**
	 * Test method for {@link hw9.Term#compareTo(hw9.Term)}.
	 */
	@Test
	public final void testCompareTo() {
		for(Term t : termPool){
			for(Term u : termPool){
				if(t.equals(u)){
					assertTrue(t.compareTo(u) == 0);
				}
				if(t.getExp() < u.getExp()){
					assertTrue(t.compareTo(u) < 0);
				}
			}
		}
	}
	
	/**
	 * Test method for {@link hw9.Term#equals(hw9.Term)}.
	 */
	@Test
	public final void testEquals(){
		//test would be copy paste of implementation.
	}

	/**
	 * Test method for {@link hw9.Term#toString()}.
	 */
	@Test
	public final void testToString() {
		for(Term t : termPool){
			System.out.println(t);
		}
		//human readability not autotestable. Handtested OK.
	}

}
