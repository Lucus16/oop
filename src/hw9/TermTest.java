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
	
	ArrayList<Term> termPool;
	
	@Before
	public final void setup(){
		termPool = new ArrayList<Term>();
		termPool.add(new Term(0,0));
		termPool.add(new Term(0.001,10));
		termPool.add(new Term(3.14,1));
		termPool.add(new Term(0,4));
		termPool.add(new Term(2.72,0));
		termPool.add(new Term(7.5,21321));
		termPool.add(new Term(-1, 3));
		termPool.add(new Term(5,-2));
	}
	
	/**
	 * Test method for {@link hw9.Term#Term()}.
	 */
	@Test
	public final void testTerm() {
		assertEquals("Term() != Term(0,0)",new Term(),new Term(0,0));
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
			assertEquals("Copied Term unequal to original",t,new Term(t));
		}
	}

	/**
	 * Test method for {@link hw9.Term#apply(double)}.
	 */
	@Test
	public final void testApply() {
		for (Term t : termPool){
			for (int i = 0; i<=4 ; ++i ){
				assertEquals("Inaccurate evaluation on " + t.toString(),
						t.getCoef()*Math.pow(i,t.getExp()),t.apply(i),0.001);
			}
		}
	}

	/**
	 * Test method for {@link hw9.Term#add(hw9.Term)}.
	 */
	@Test
	public final void testAdd() {
		
	}

	/**
	 * Test method for {@link hw9.Term#sub(hw9.Term)}.
	 */
	@Test
	public final void testSub() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#mul(hw9.Term)}.
	 */
	@Test
	public final void testMul() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#div(hw9.Term)}.
	 */
	@Test
	public final void testDiv() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#neg()}.
	 */
	@Test
	public final void testNeg() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#pow(int)}.
	 */
	@Test
	public final void testPow() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#getCoef()}.
	 */
	@Test
	public final void testGetCoef() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#setCoef(double)}.
	 */
	@Test
	public final void testSetCoef() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#getExp()}.
	 */
	@Test
	public final void testGetExp() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#setExp(int)}.
	 */
	@Test
	public final void testSetExp() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#scanTerm(java.util.Scanner)}.
	 */
	@Test
	public final void testScanTerm() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#compareTo(hw9.Term)}.
	 */
	@Test
	public final void testCompareTo() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link hw9.Term#toString()}.
	 */
	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
