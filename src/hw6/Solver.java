package hw6;

import java.util.*;

/**
 * A class that implements a breadth-first search algorithm for finding the
 * Graphs for which the isGoal predicate holds
 * 
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
 */
public class Solver {
	// A queue for maintaining graphs that are not visited yet.
	PriorityQueue<Node<Graph>> toExamine;
	HashSet<Graph> examined;

	/**
	 * Create a new solver with starting Graph g.
	 * @param g
	 */
	public Solver(Graph g) {
		examined = new HashSet<Graph>();
		toExamine = new PriorityQueue<Node<Graph>>();
		toExamine.add(new Node<Graph>(null,g));
	}

	/**
	 * A solver using a priority queue.
	 * 
	 * @return a string representation of the solution
	 */
	public List<Graph> solve() {
		while (!toExamine.isEmpty()) {
			Node<Graph> next = toExamine.remove();
			if (next.getItem().isGoal()) return next.path();
			if (examined.contains(next.getItem())) {
				continue;
			}
			for (Graph succ : next.getItem().successors()) {
				toExamine.add(new Node<Graph>(next,succ));
			}
			examined.add(next.getItem());
		}
		return null;
	}

}
