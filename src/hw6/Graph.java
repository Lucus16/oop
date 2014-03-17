package hw6;

import java.util.Collection;

/**
 * An interface for representing (abstract) constant graphs, i.e. graphs of
 * which the structure cannot be changed.
 * 
 * @author Lars Jellema s4388747
 * @author Sal Wolffs s4064542
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
	
	/**
	 * returns the approximate distance to the goal.
	 * @return
	 */
	int distToGoal();
}
