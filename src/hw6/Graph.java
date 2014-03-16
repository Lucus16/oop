package hw6;

import java.util.Collection;

/**
 * An interface for representing (abstract) constant graphs, i.e. graphs of
 * which the structure cannot be changed.
 * 
 * @author Sjaak Smetsers
 * @version 1.1
 * @date 28-02-2013
 */
public interface Graph extends Comparable<Graph> {
	/**
	 * To obtain the successors for a specific (node of a) graph
	 * 
	 * @return a collection of graphs containing the successors
	 */
	Collection<Graph> successors();

	/**
	 * For marking final / goal Graphs.
	 */
	boolean isGoal();
	
	int distToGoal();
	
}
