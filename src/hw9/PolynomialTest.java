/**
 * 
 */
package hw9;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author sal
 *
 */
public class PolynomialTest {
	
	private ArrayList<Polynomial> polyPool;
	private static double[] xPool = new double[]{0,0.01,1,1.6,2,2.72,3,3.14,4};

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
	}
	
	/**
	 * Test method for {@link hw9.Polynomial#Polynomial()}.
	 */
	@Test
	public final void testPolynomial() {
		assertEquals(new Polynomial(),new Polynomial("0 0"));
	}

	/**
	 * Test method for {@link hw9.Polynomial#Polynomial(hw9.Term)}.
	 */
	@Test
	public final void testPolynomialTerm() {
		assertEquals(new Polynomial(),new Polynomial(new Term()));
		assertEquals(new Polynomial("0.5 2"),new Polynomial(new Term(0.5,2)));
		assertEquals(new Polynomial("7.5 256"),new Polynomial(new Term(7.5,256)));
		assertEquals(new Polynomial("2.72 0"),new Polynomial(new Term(2.72,0)));
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
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#add(hw9.Polynomial)}.
	 */
	@Test
	public final void testAddPolynomial() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#sub(hw9.Term)}.
	 */
	@Test
	public final void testSubTerm() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#sub(hw9.Polynomial)}.
	 */
	@Test
	public final void testSubPolynomial() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#mul(hw9.Term)}.
	 */
	@Test
	public final void testMulTerm() {
		fail("Not yet implemented"); // TODO
	}


	/**
	 * Test method for {@link hw9.Polynomial#mul(hw9.Polynomial)}.
	 */
	@Test
	public final void testMulPolynomial() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#div(hw9.Term)}.
	 */
	@Test
	public final void testDiv() {
		fail("Not yet implemented"); // TODO
	}
	/**
	 * Test method for {@link hw9.Polynomial#clear()}.
	 */
	@Test
	public final void testClear() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#apply(double)}.
	 */
	@Test
	public final void testApply() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#toString()}.
	 */
	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#iterator()}.
	 */
	@Test
	public final void testIterator() {
		fail("Not yet implemented"); // TODO
	}

}
