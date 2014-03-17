package hw6;

import java.util.*;

/**
 * A class that implements a breadth-first search algorithm for finding the
 * Graphs for which the isGoal predicate holds
 * 
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.3
 * @date 28-02-2013
 */
public class Solver {
	// A queue for maintaining graphs that are not visited yet.
	PriorityQueue<Node<Graph>> toExamine;
	HashSet<Graph> examined;

	public Solver(Graph g) {
		examined = new HashSet<Graph>();
		toExamine = new PriorityQueue<Node<Graph>>();
		toExamine.add(new Node<Graph>(null,g));
	}

	/**
	 * A skeleton implementation of the solver
	 * 
	 * @return a string representation of the solution
	 */
	public List<Graph> solve() {
		while (!toExamine.isEmpty()) {
			Node<Graph> next = toExamine.remove();
			//System.out.println("Trying \n" + next.getItem().toString());
			if (next.getItem().isGoal()) return next.path();
			if (examined.contains(next.getItem())) {
				//System.out.println("Already seen, skipping.");
				continue;
			}
			for (Graph succ : next.getItem().successors()) {
				//System.out.println("Adding \n" + succ.toString());
				toExamine.add(new Node<Graph>(next,succ));
			}
			examined.add(next.getItem());
		}
		return null;
	}

}
