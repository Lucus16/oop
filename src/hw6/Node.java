package hw6;

/**
 * For maintaining lists of T-elements enabling a structure suited for backwards
 * traversal
 * 
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.1
 * @date 28-02-2013
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
	// the data field
	private T item;
	// a reference to the predecessor
	private Node<T> previous;

	/*
	 * A constructor that initializes each node with the specified values
	 * 
	 * @param from the node preceding the current node
	 * 
	 * @param item the initial data item
	 */
	public Node(Node<T> from, T item) {
		this.previous = from;
		this.item = item;
	}

	/*
	 * a getter for the item
	 * 
	 * @return item
	 */
	public T getItem() {
		return item;
	}

	/*
	 * a getter for the predecessor
	 * 
	 * @return previous
	 */
	public Node<T> getPrevious() {
		return previous;
	}

	/*
	 * deteremine the length of the current list
	 * 
	 * @return the length as an integer
	 */
	public int length() {
		int length = 1;
		Node<T> prev = previous;
		while (prev != null) {
			prev = prev.previous;
			length++;
		}
		return length;
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("toString : not supported yet.");
	}

	@Override
	public int compareTo(Node<T> t) {
		return this.item.compareTo(t.item);
	}
}
