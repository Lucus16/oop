package hw3;

/**
 * Provides a general implementation of a classic 2-member tuple.
 * Intended as a near-primitive and as such not encapsulated.
 * Has two public member fields, left and right, 
 * not necessarily of the same type.
 * @author Sal
 *
 */
public class Tuple <L,R> {
	public L left;
	public R right;
	/**
	 * Initializes a tuple to given values.
	 * @param left
	 * @param right
	 */
	Tuple (L left, R right){
		this.left=left;
		this.right=right;
	}
	/**
	 * Initializes both fields to null.
	 */
	Tuple (){
		left=null;
		right=null;
	}
}
