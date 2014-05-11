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
		polyPool.add(new Polynomial("0 0"));//TODO: add variety
		polyPool.add(new Polynomial("0 0"));
		polyPool.add(new Polynomial("0 0"));
		polyPool.add(new Polynomial("0 0"));
		polyPool.add(new Polynomial("0 0"));
		polyPool.add(new Polynomial("0 0"));
	}
	
	/**
	 * Test method for {@link hw9.Polynomial#Polynomial()}.
	 */
	@Test
	public final void testPolynomial() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#Polynomial(hw9.Term)}.
	 */
	@Test
	public final void testPolynomialTerm() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#Polynomial(java.lang.String)}.
	 */
	@Test
	public final void testPolynomialString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Polynomial#Polynomial(hw9.Polynomial)}.
	 */
	@Test
	public final void testPolynomialPolynomial() {
		fail("Not yet implemented"); // TODO
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
